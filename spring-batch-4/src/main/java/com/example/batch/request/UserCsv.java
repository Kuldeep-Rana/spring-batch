package com.example.batch.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCsv {
    private long id;
    private String name;
    private String email;
    private String dob;
    private String phone;
    private String address;

}
