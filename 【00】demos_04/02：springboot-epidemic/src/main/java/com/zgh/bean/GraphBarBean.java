package com.zgh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphBarBean implements Comparable<GraphBarBean>{

    private String name;

    private int fromAbroad;

    @Override
    public int compareTo(GraphBarBean o) {
        return o.getFromAbroad() - this.getFromAbroad(); //递减
    }

}
