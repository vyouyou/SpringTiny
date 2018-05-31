package com.qiyouyou.dal.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private Integer id;

    private String name;

    private String contract;

    private String tele_phone;

    private String e_mail;

    private String remark;
}
