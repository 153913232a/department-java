package com.example.demo.model;

import lombok.Data;

import java.util.Date;

// 历史
@Data
public class History {

    private int id;
    private String name;
    private String ip;
    private String district;
    private Date createdTs;
    private String createdBy;
    private String isValid;
}
