package com.priyana.repository;

import com.priyana.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByemailId(String email);

    User findByuserName(String userName);

    // get the list of all users.
    @Query(value = "select * from users",nativeQuery = true)
    List<User> getAllCustomers();


}
