package com.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FirstItemProcessor implements ItemProcessor<Integer,Long> {

    @Override
    public Long process(Integer val) throws Exception {
        System.out.println("processed int "+val);
        return Long.valueOf(val*10);
    }
}
