package com.example.batch.reader;

import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemReader implements org.springframework.batch.item.ItemReader<Integer> {

    List<Integer> data = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
     int index = 0;
    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(index >= data.size()){
            index = 0;
            return null;
        }
        return data.get(index++);
    }
}
