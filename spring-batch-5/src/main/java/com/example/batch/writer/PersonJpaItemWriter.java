package com.example.batch.writer;

import com.example.batch.request.Person;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class PersonJpaItemWriter extends JpaItemWriter<Person> {
    @Autowired
    public PersonJpaItemWriter(EntityManagerFactory entityManagerFactory){
                super.setEntityManagerFactory(entityManagerFactory);
    }
    @Override
    public void write(List<? extends Person> items) {
        super.write(items);
    }
}
