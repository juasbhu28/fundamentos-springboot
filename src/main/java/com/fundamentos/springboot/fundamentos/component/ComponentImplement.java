package com.fundamentos.springboot.fundamentos.component;

import com.fundamentos.springboot.fundamentos.component.dependency.ComponentDependency;
import org.springframework.stereotype.Component;

@Component
public class ComponentImplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println( "Hola Mundo desde mi componente");
    }
}
