package com.example.batch.mappr;

import com.example.batch.request.Person;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class PersonFieldMapper implements org.springframework.batch.item.file.mapping.FieldSetMapper<com.example.batch.request.Person> {
    @Override
    public Person mapFieldSet(FieldSet fieldSet) {
        Person person = new Person();
        //id, name,email,dob,phone,address
        person.setId(fieldSet.readLong("id"));
        person.setName(fieldSet.readString("name"));
        person.setEmail(fieldSet.readString("email"));
        person.setPhone(fieldSet.readString("phone"));
        person.setAddress(fieldSet.readString("address"));

        String dobStr = fieldSet.readString("dob");
        if (dobStr != null && !dobStr.isEmpty()) {
            var formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
            LocalDate dob = LocalDate.parse(dobStr, formatter);
            person.setDob(dob);
        }
        return person;
    }
}
