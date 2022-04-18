# fundamentos-springboot

# Curso de Fundamentos de Java Spring Boot

Creation date: February 25, 2022 11:20 AM
Director: Michael García Abelló
Label: Platzi
Last edition: March 1, 2022 6:20 AM


Prerequisitos

Java 8 - POO - Java Funcional

- Active Recall
    - Que es Spring?
        - Spring boot - Es un proyecto basado en Spring, Spring es un core con muchas más aplicaciones.
        - Tiene como base simplificar las configuraciones o correr como un servidor web embebido. Sin preocupaciones de compilaciones de tereceros.
        - Es independiente, está incrustado TomCat . Jetty o Udertow es a eleccion propia.
        - Proporciona dependencias muy facilmente.
        - No hay generaciones XML
        - Métricas de salud del aplicativo.
    - Que es una dependencia?
        - Objetivo que define una funcionalidad.
        - En java se usan dependencias, estas trabajan entre sí para cumplir un Objetivo.
        - También se puede pensar como componentes o partes.
        - Parten con el principio SOLID de Simple Responsability Principle.
    - Inversión de control (IoC) y Patron de inyección de dependencias.
        - Inversión de Control - Patrón Hollywood. (No nos llames, nosotros le llamaremos)
            - Tranfiere el control a.
                - Principio que transfiere el control de los objetos de un programa al contenedor o Framework - no lleva la responsabilidad como un flujo de un programa de manera tradicional.  (Un contenedor puede ser un usuario o un framework)
                - La aplicación no necesita nuevos objetos relacionados directamente en el código, esto se ensambla con el contenedor IoC.
                - La dependencia o contendor es responsable de crear las instancias, ubicar, configurar objetos en la aplicación y establecer dependencias entre estos.
            - Ventajas
                - Desacoplamiento por qué los objetos cuentan con sus dependencias. Cada objeto es una pequeña parte a nivel de dependencia
                - Oculta la implementación de las dependencias y da el  beneficio de **segregación de interfaz.**
                - Facilita el testing por componentes o mocks de dependencia.
                - Mayor modularidad
            - **@bean -** Son objetos que componen su aplicación por el contenedor IoC
                - Son inicializados, ensamblados y administrador por Spring a estos se les domina Beans.
                - Un bean es un objetivo que es instanciado, ensamblado y administrado por un contenedor Spring IoC.

        - Inyección de dependencias. **DI PATTERN El 5 PATRON EN SOLID**
            - Este patrón se encarga de implementar el IoC. define los otros objetos y sus dependencias
            - Es el proceso con los objetos definen sus dependencias.
            - Produce código limpio y desacoplamiento más efectivo cuando cada objeto cuenta con su dependencia.
        - Inversión de dependencias es la implementación de Inversión de Control
    - Autoconfiguración y Runtime
        - Spring configura automáticamente tus aplicaciones basadas en en dependencias Jar aunqué también uno se puede encargar de ponerlas
    - Anotaciones **@Component**  hereda **@Controller -  @Service - @Reposityory**
        - Grafico

          ![Untitled](Curso%20de%20F%20055f0/Untitled.png)

        - @Component - Es una anotación muy génerica - una clase abstracta
        - @Controller  - Al igual que en el patrón MVC el controllador se encargar de recibir todo lo que viene de una interfaz grafica. Metodos HTTP - o cualquier petición del servidor.
        - @Service - Sirve para poner toda la lógica de negocio adicional de nuestro sistema, validaciones lógicas
        - @Repository - Es la capa dónde está toda la persistencia de datos de nuestra aplicación. Capa de datos.

    - Proyecto
        - Iniciarlizar un proyecto mediante spring Initializr.io
        - Se crea la estructura de carpetas según la anotaciones
        - @Beans . @Configuration - @Component
          Podemos crear los componentes (ESTEREOTIPO) y que hereden de una interfaz
            - **IMPORTANTE!** dos componente que hereden de la misma interfaz si no se especifica cual ejecutar saltara un error por que tienen la misma dependencia por lo que se puede agregar un calificador @Primary o @Qualifier(”ComponenteQueNecesito”)
            - Podemos crear una propia dependencia sin necesidad de crearse con estereotipo. Centramos la implementación en una clase @Configuration, dónde retorno el nuevo objeto - el Bean. En la clase de aplicación hago llamado a la clase y la  con la inyección de dependencias trabaja solo.
                - Este componente de configuración me permite delegar la responsabilidad de que clase se debería ejecutar, por ejemplo en caso
            - Se pueden tener implementaciones dentro de otras implementaciones.
                - La implementación de un Bean es inyectada en el constructor del bean que será finalmente llamada en el flujo, es un método trabajo lo métodos de la dependencia del bean inyectado y el fujo solo inyecto el bean final.
        - Path - Puertos -  .propierties
            - Desde propiesties podemos agregar valores para configurar cualquier archivo o beans en todo el proyecto. Una vez se inserta podemos correrlo como una dependencia.
        - Logs
            - Los logs nos ayudan a debugear la aplicacion, nos ayuda a entender que está pasando la información en el flujo del programa.
            - Usando la librería LogFactory en mi proyecto y los diferentes niveles . Error - Info - Debug
        - Modelando **JPA**
            - Using JPA- hibernate es una implementación de JPA
            - `@Entity` para indicar que es una entidad - objeto - tabla DB.
            - Tablas
                - `@Table(name = “NombreTabla”)` - Indicao el nombre de mi tabal
            - Columnas
                - Para crea una **llave de identidad uso lo siguiente. para tipo automatico se puede asignar `GenerationType.AUTO`**

                    ```java
                    @Id
                    @GeneratedValue(strategy=GenerationType.IDENTITY)
                    @Column(name="id_post", nullable=false, unique=true)
                    ```

                - Para tamaños `@Column(length = 50)`
                - Para relaciones de 1 - * , 1 - 1 , * - * se usan anotaciones `@ManyToOne , @ManyToMany,`
                - Las relaciones de llaves foraneas van acompañadas de las anotaciones
                  `@ManyToOne`
                  *`@JoinColumn(name = “nombreRelacion_id”)
                  private MiOtraEntidadRelacion miEntity;`*
                - `@JsonManagedReference` Si usamos esta referencia al momento de mapear los datos en una API no tendremos error de buffer overflow
        - Configurando la base de datos desde .propierties o con inyección de dependencias en un bean.


        - Registros con JPARepository
            - Una vez creada cada identidad
            - Cuando creamos una interfaz que hereda de JpaRepository obtenemos en herencia una seríe de methodos que me pueden ayudar a trabajar la base de datos.
            - Usaremos la anotación de estereotipo de repository `@Respository`
            - `/h2-console` -Puedes acceder a la base de datos a través de una URL, entras al puerto de la aplicación y llenas con la data de tu base de datos `jdbc:h2:mem:testDB`
            
        - JPQL - QUERY
            - Es un lenguaje de consulta definido por JPA similar a SQL pero opera a partir de objetos. SQL trabaja sobre tablas y columnas.
            - Ejemplo
            
            ```java
            @Query("SELECT u FROM USER u WHERE u.email=?1")
            Optional<User>findByUserEmail(Stringemail);
            ```
            
            - Diferencias
                
                JPQL = Java Persistence Query Language,
                HQL = Hibernate Query Languaje,
                
                Hibernate es una implementacion de JPA.
                
        - New file.propierties
            - Podemos unsar un archivo nuevo de propiedades para obtener sus valores y setearlos dentro de una clase
            - `@PropertySource("classpath:connection.propierties")`
            - Esto es para efectos de la práctica pero en la vida real se usan variables de entorno
        - **Query Methods**
            - Los query method son una definición de una consulta manualmente como una cadena o derivarla del nombre del método
            - Esto se ve reflejado dentro de una clase JAVA - herendan de JpaRepository -
            - Los query methodos nos ayudan con necesidades especiales
            - `OR - OrderBy - And - OrderBy -Between - Sort`
            - Using `@Param` and DTO
                - Ejemplo
                    
                    ```java
                    @Query("SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate)"+
                    "FROM User u "+
                    "WHERE "+
                    "u.birthDate = :parametroDate"+
                    "AND u.email = :parametroEmail")
                    Optional<UserDto>getAllByBirthDateAndEmail(@Param("parametroDate")LocalDatedate,
                                                                @Param("parametroEmail")Stringemail);
                    ```
                    
                - **Un DTO  es data transfer object - No**s ayuda con la transferencia de objetos a nivel de la aplicación.
            - Using `@Transacction`
                - La anotación transacción nos ayuda a que si existe algún error en el flujo se hará un rollback y no se afectará las bases de datos.
                - Existen dos tipos - Javax y Spring link above
                    
                    [javax.transaction.Transactional vs org.springframework.transaction.annotation.Transactional](https://stackoverflow.com/questions/26387399/javax-transaction-transactional-vs-org-springframework-transaction-annotation-tr)
                    
                - Pueden ocurrir errores cuando se repitan por ejemplos columnas con valor unico
                - **Sin la anotación** `@transactional` lo que va a suceder es que se van a grabar cada uno de los campos hasta el último donde se generó el error.
                
            - Using @RestContorller
                - Hace que todos lo metodos se mappen en formato json
        - CRUD - REST - API
            - `@Componente`  -  Nos sirve para hacer cosas muy generales”.. Pendiente entender por compoleto la funcionalidad.
            - Creamos en nuestra carpeta caseuse los diferentes componentes para el crud. uno por cada uno pará el tema práctico.
            - `@JsonBackReference`, `@JsonManagedReference`, `@JsonIgnore`
            - `@GetMapping("/")`
                
                ```java
                @GetMapping("/")
                List<User>get(){
                returngetUser.getAll();
                }
                ```
                
            - `@PostMapping("/")`
                
                ```java
                @PostMapping("/")
                ResponseEntity<User>newUser(@RequestBodyUsernewUser){
                return newResponseEntity<>(createUser.save(newUser),HttpStatus.CREATED);
                }
                ```
                
            - `@DeleteMapping("/{id}")` - Return 204
                
                ```java
                @DeleteMapping("/{id}")
                ResponseEntitydeleteUser(@PathVariableLongid){
                deleteUser.delete(id);
                return newResponseEntity(HttpStatus.NO_CONTENT);
                }
                ```
                
            - `@PutMapping("/{id}")`
                
                ```java
                @PutMapping("/{id}")
                ResponseEntityupdateUser(@RequestBodyUsernewUser, @PathVariableLongid){
                return newResponseEntity<>(updateUser.update(newUser,id),HttpStatus.OK);
                }
                ```
                
            
        - Pagination -  `**extends PagingAndSortingRepository<User**, **Long>**`
            - Page endn - Get localhost:8081/app/api/users/paginable?page=0&size=2
            - 
            
            ```java
            @GetMapping("/paginable")
            List<User>getUserPageable(@RequestParamintpage, @RequestParamintsize){
            returnuserRepository.findAll(PageRequest.of(page,size)).getContent();
            }
            ```
        
  - **Install how to windows service**
      - Para instalar cómo servicio hay que modificar los parametos del archivo .xml en la carpeta installWServicie
      - Para instalar `**miServicio.exe install**`
      - Iniciar `**net start miServicio.exe**`
      - Detener `**net stop miServicio.exe**`
