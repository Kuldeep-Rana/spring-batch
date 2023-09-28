package com.example.batch.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobParamDTO {
    private String paramName;
    private String paramValue;
}
