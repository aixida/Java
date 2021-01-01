package com.zgh.util;

import java.io.File;

public class PathUtil {

    public static void main(String[] args) {

        // 类加载路径的不同使用方式
        System.out.println(ClassLoader.getSystemResource(""));

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));

        System.out.println(PathUtil.class.getResource("/"));

        System.out.println(PathUtil.class.getResource(""));

        System.out.println("=======================");

        // 当前项目根路径
        String path = System.getProperty("user.dir");
        System.out.println(path);

        System.out.println(new File("").getAbsolutePath());

        // 当前所在磁盘
        System.out.println(new File("/").getAbsolutePath());


        //   '/' 代表根目录  './' 代表当前目录  '../' 代表上级目录
    }
}
