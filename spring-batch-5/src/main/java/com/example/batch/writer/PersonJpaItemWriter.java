package com.example.batch.writer;

import com.example.batch.request.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonJpaItemWriter extends JpaItemWriter<Person> {
  //  private final EntityManagerFactory localEntityManagerFactory;
    private final ApplicationContext applicationContext;
    @Override
    public void write(List<? extends Person> items) {
        super.setEntityManagerFactory(applicationContext.getBean(EntityManagerFactory.class));
        super.write(items);
    }
}
