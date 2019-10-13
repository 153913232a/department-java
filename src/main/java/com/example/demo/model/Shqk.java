package com.example.demo.model;

import lombok.Data;

import java.util.Date;

// 损耗情况
@Data
public class Shqk {

    private int id;
    private int appartId;
    private String reason;
    private String remark;
    private Date createdTs;
    private Date updatedTs;
    private int approveId;
    private String approveRe;
    private String createdBy;
    private String updatedBy;
}
