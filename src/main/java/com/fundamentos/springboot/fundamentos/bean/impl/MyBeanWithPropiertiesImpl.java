package com.fundamentos.springboot.fundamentos.bean.impl;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropierties;

public class MyBeanWithPropiertiesImpl implements MyBeanWithPropierties {
    private String nombre;
    private String apellido;

    public MyBeanWithPropiertiesImpl(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String myBeanWithPropiertiesFunciont() {
           return nombre + " " + apellido;
    }
}
