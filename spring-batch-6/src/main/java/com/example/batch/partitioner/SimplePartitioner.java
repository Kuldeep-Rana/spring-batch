package com.example.batch.partitioner;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimplePartitioner implements Partitioner {
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new HashMap<>();
        var inputData = List.of( "item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8"); // Replace with your dataset

        int chunkSize = inputData.size() / gridSize;

        for (int i = 0; i < gridSize; i++) {
            ExecutionContext context = new ExecutionContext();
            int start = i * chunkSize;
            int end = (i == gridSize - 1) ? inputData.size() : (i + 1) * chunkSize;
            context.put("startIndex", start);
            context.put("endIndex", end);
            partitions.put("partition" + i, context);
        }
        return partitions;
    }
}

