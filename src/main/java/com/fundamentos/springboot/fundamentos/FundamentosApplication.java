package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependenci;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropierties;
import com.fundamentos.springboot.fundamentos.component.dependency.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

	//Inyectando repositorio como depedencia
	private UserRepository userRepository;

	//Inyectamos las dependencias
	public FundamentosApplication(
			@Qualifier("componentTwoImplement")	ComponentDependency componentDependency,
			MyBean myBean,
			MyBeanWithDependenci myBeanWithDependenci,
			MyBeanWithPropierties myBeanWithPropierties,
			UserPojo userPojo,
			UserRepository userRepository
			) {

		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependenci = myBeanWithDependenci;
		this.myBeanWithPropierties = myBeanWithPropierties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}




	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();

		//Saving users in database with JPA
		saveUsersInDatabase();

		//Gettin data base registir with JPQL
		getInformationJpqlFromUserExisting();

		//Trhowing runtime excpetion for no existing
		//getInformationJpqlFromUserNOTExisting();

	}

	private void getInformationJpqlFromUserExisting(){
		LOGGER.info("Método getInformationJpqlFromUser " +
				userRepository.findByUserEmail("john@domain.com")
					.orElseThrow(() -> new RuntimeException("No se encontró el usuario")));

		//Pueo usar el metodo ascending o descendig
		//userRepository.findAndSort("user", Sort.by("id").descending()).stream().forEach(user -> LOGGER.info("Usuario con metodo sort " + user ))
		userRepository.findAndSort("user", Sort.by("id").descending())
				.forEach(LOGGER::info);
	}



	private void getInformationJpqlFromUserNOTExisting(){
		LOGGER.info("Método getInformationJpqlFromUser " +
				userRepository.findByUserEmail("NOEXISTO@domain.com")
						.orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
	}



	//Metodo que me ayudará a persistir mi información
	private void saveUsersInDatabase() throws Exception{
		User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
		User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
		User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
		User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
		User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
		User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
		User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
		User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
		User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));

		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);
		//Op1 - Podemos grabar sin iterar a través de un stream
		//list.forEach(userRepository::save);

		//Opc2 - Iterando atráves de un Stream
		list.stream().forEach(userRepository::save);

		//Opc3 - Puedo enviar una lista completa con el metodo saveAll del JPA repository
		//userRepository.saveAll(list);
	}

	public void ejemplosAnteriores(){
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
