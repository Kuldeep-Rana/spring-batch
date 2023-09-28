package com.example.batch.processor;

import com.example.batch.request.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person,Person> {
    @Override
    public Person process(Person person) throws Exception {
          person.setProcessed(Boolean.TRUE);
        return person;
    }
}
