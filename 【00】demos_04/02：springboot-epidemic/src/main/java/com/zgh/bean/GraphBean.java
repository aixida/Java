package com.zgh.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("graph")
public class GraphBean implements Serializable {

    private Long id;

    private String date;

    private int confirm;

    private int heal;

    private int dead;

}
