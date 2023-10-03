package com.example.batch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        for (String item : items) {
            System.out.println("Writing: " + item);
        }
    }
}
