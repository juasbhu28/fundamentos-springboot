package com.fundamentos.springboot.fundamentos.component;

import com.fundamentos.springboot.fundamentos.component.dependency.ComponentDependency;
import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void saludar() {
    System.out.println("Un saludo desde mi componente dos");
    }
}
