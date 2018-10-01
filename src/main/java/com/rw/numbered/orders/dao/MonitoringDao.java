package com.rw.numbered.orders.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

@Repository
@Slf4j
public class MonitoringDao implements SQLQueries {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${monitoring.defaultAdminEmails}")
    String defaultEmails;

    public boolean checkMonitoringValue(Monitoring.Type par)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("MONITORING_PARAMETER", par.name());
        Boolean result = false;
        try {
            result = jdbcTemplate.queryForObject(
                    CHECK_MONITORING, params, (rs, rowNum) -> rs.getString("PARAMETER_VALUE").equals("1"));
        } catch (Exception e) {
            log.error("checkMonitoringValue", e);
        }
        return result;
    }

    public void sendEmail(Monitoring.Type monType,String paymentSystem,String orderExpressNumber,String user, String externalId,String agent,String textCommentName, boolean logIfNotFoundComment)
    {
        int monitoringType = monType.getIntValue();
        List<String> admins = getMonitoringAdmins();
        if(admins!=null && admins.size()>0)
        {
            /*String curDate = sdfFull.format(Calendar.getInstance().getTime());
            String title = lb.getMailString("monitoringTitle_"+monitoringType,adminLanguage);
            title=MessageFormat.format(title, orderExpressNumber);
            String text = lb.getMailString("monitoringText_"+monitoringType,adminLanguage);
            String fromTitle = lb.getMailString("monitoringFromTitle",adminLanguage);
            String textComment = StringUtils.isEmpty(textCommentName)?"":lb.getMailString(textCommentName, adminLanguage,logIfNotFoundComment);
            text= MessageFormat.format(text, orderExpressNumber,paymentSystem,curDate,externalId,user,agent,textComment);
            logger.info("send monitoring letter for order="+orderExpressNumber+", payment system="+paymentSystem+", monitoringType="+monType);
            logger.debug("Title: "+title+", text: \n"+text);
            SendOrderMail mail = new SendOrderMail(admins,fromTitle);
            mail.setParameters(title,text);
            new Thread(mail).start();*/
        }
    }

    private List<String> getMonitoringAdmins()
    {
        List<String> admins = new ArrayList<>();
        try {

            admins = jdbcTemplate.query(
                    MONITORING_ADMINS, (RowMapper) (rs, rowNum) -> {
                        String admin="";
                        try{
                            admin = rs.getString("ADMIN_EMAIL");
                        } catch(Exception e) {
                            log.error("error getMonitoringAdmins: ",e);
                        }
                        return admin;
                    });

        } catch (Exception e) {
            log.error("getMonitoringAdmins: "+e.getMessage(),e);
            if(!StringUtils.isEmpty(defaultEmails)) {
                for(String defaultEmail: defaultEmails.split(",")) {
                    admins.add(defaultEmail);
                }
            }
        }
        return admins;
    }

}
