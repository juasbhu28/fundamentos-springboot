package com.fundamentos.springboot.fundamentos.bean.impl;

import com.fundamentos.springboot.fundamentos.bean.MyBean;

public class MyBeanImplement implements MyBean {

    @Override
    public void print() {
        System.out.println( "Hola desde la implementación de mi propio bean");
    }
}
