package com.zgh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataBean {

    private String name;

    private int nowConfirm;

    private int confirm;

    private int dead;

    private int heal;

}
