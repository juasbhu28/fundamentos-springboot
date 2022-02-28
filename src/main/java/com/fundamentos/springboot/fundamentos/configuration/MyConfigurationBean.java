package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependenci;
import com.fundamentos.springboot.fundamentos.bean.MyOperation;
import com.fundamentos.springboot.fundamentos.bean.impl.MyBeanTwoImpl;
import com.fundamentos.springboot.fundamentos.bean.impl.MyBeanWithDependenciesImpl;
import com.fundamentos.springboot.fundamentos.bean.impl.MyOperationImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {

    //Implementing another configuration bean
    //@Bean
    //public MyBean beanOperation(){
    //       return new MyBeanImplement();
    // }
    @Bean
    public MyBean returnMyBean(){
        return new MyBeanTwoImpl();
    }


    //Un interfaz diferente
    @Bean
    public MyOperation returnMyOperation(){
        return new MyOperationImpl();
    }


    //esta dependencia es insertada en el flujo del proyecto
    @Bean
    public MyBeanWithDependenci retunrBeanWithDependenci(MyOperation myOperation){
        return new MyBeanWithDependenciesImpl(myOperation);
    }


}
