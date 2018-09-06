package com.rw.numbered.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
   private long id;
   private String login;
   private String lastName;
   private String firstName;
   private String patronymic;
   private String sex;
   private String age;
   private String country;
   private String address;
   private String phone;
   private String email;
}
