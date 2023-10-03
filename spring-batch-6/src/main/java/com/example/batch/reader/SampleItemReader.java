package com.example.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SampleItemReader implements org.springframework.batch.item.ItemReader<String> {
    List<String> data = List.of( "item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8"); // Replace with your dataset
    private int currentIndex = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (currentIndex < data.size()) {
            return data.get(currentIndex++);
        } else {
            return null;
        }
    }
}
