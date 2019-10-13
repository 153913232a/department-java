package com.example.demo.model;

import lombok.Data;

import java.util.Date;

// 寝室分数
@Data
public class AppartRate {
    private int id;
    private String name;
    private int rateNum;
    private Date createdTs;
    private String createdBy;
    private int appartId;
}
