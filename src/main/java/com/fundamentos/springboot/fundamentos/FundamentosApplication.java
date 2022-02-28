package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependenci;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropierties;
import com.fundamentos.springboot.fundamentos.component.dependency.ComponentDependency;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	//First: From file propiertie we config just error log show
	//Second config log of the class above
	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);


	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependenci myBeanWithDependenci;
	private MyBeanWithPropierties myBeanWithPropierties;
	private UserPojo userPojo;

	//Inyectamos las dependencias
	public FundamentosApplication(
			@Qualifier("componentTwoImplement")
					ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependenci myBeanWithDependenci,
			MyBeanWithPropierties myBeanWithPropierties,
			UserPojo userPojo) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependenci = myBeanWithDependenci;
		this.myBeanWithPropierties = myBeanWithPropierties;
		this.userPojo = userPojo;
	}




	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Este método sirve para imprimir un flujo de los componentes inyectados y / o generados.
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependenci.printWithDependecy();
		System.out.println(myBeanWithPropierties.myBeanWithPropiertiesFunciont());

		// Create a pojo from file propiresties
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		try{
			// set a error
			int value = 10/0 ;
			LOGGER.debug("MI valor " + value );
		}catch (Exception e){
			//Inyecto un log de error para demostrar que configure que solo puedo ver el log de error
			LOGGER.error("Esto es un error de mi app" + e.getMessage());
		}

	}
}
