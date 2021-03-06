package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    //Take care with the name of object
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE ?1%")
    List<User> findAndSort(String name, Sort sort);


    //Using query Methodos
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    //Using query Method special sentences

    //Sentence like  TAMBIÉN existe la propiedad containing que es similiar al like
    List<User> findByNameLike(String name);
    List<User> findByNameContaining(String name);

    //Sentence OR
    List<User> findByNameOrEmail(String name, String Email);

    //Setence between
    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    //OrderBy
    List<User> findByNameLikeOrderByIdDesc(String name);


    //namedParameters
    //@Query(" SELECT new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id, u.name, u.birthDate) " +
    //        " FROM User u " +
    //        " WHERE u.birthDate=:parametroDate" +
    //        " AND u.email=:parametroEmail")
    //Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroDate") LocalDate date,
    //                                            @Param("parametroEmail") String email);


    //Sobreescribimos el método para que no nos salga error.
    List<User> findAll();

}
