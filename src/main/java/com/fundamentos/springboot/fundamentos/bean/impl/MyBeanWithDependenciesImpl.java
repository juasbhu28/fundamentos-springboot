package com.fundamentos.springboot.fundamentos.bean.impl;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependenci;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;


public class MyBeanWithDependenciesImpl implements MyBeanWithDependenci {

    private MyOperation myOperation;

    public MyBeanWithDependenciesImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
        int numero = 1;
        System.out.println( myOperation.sumOne(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia ");
    }
}
