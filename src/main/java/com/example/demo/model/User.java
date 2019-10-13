package com.example.demo.model;

import lombok.Data;

// 用户实体类
@Data
public class User {

    private int id;
    private String name; // 姓名
    private String phone; // 手机号
    private int age; // 年龄
    private String gender; // 性别
    private String rangeName; // 等级
    private String userName; // 用户名
    private String passWord; // 密码
    private String remark; // 备注
    private String partArray; // 权限菜单
}
