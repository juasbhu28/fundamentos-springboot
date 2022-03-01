package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //La anotación transacción nos ayuda a que si existe algún error en el flujo se hará un rollback y no se afectará
    //las bases de datos.
    @Transactional
    public void saveTransactional(List<User> users){
        //Un peek me ayuda a mostrar en pantalla cada iteración
        //Los dos puntos son referencias. EN Java 8 podemos hacer uso de los metodos apartir de referncias.
        users.stream()
                .peek( user ->  LOG.info("Uusario insertado: " + user))
                .forEach(userRepository::save);

    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setName(newUser.getName());
                            user.setBirthDate(newUser.getBirthDate());
                            return userRepository.save(user);
                        }
                ).get();
    }
}
