package com.example.demo.common;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {

        private Integer code;

        private String message;
        private Integer total;

        private List<T> data;

}
