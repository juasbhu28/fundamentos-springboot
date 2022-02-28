package com.fundamentos.springboot.fundamentos.bean.impl;

import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependenci;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class MyBeanWithDependenciesImpl implements MyBeanWithDependenci {

    Log LOGGER = LogFactory.getLog(MyBeanWithDependenciesImpl.class);

    private MyOperation myOperation;

    public MyBeanWithDependenciesImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependecy() {
        LOGGER.info("Dentro del método prinWithDependecy");
        int numero = 1;
        LOGGER.debug("El número envaido al método sumONe es" + numero);
        System.out.println( myOperation.sumOne(numero));
        System.out.println("Hola desde la implementación de un bean con dependencia ");
    }
}
