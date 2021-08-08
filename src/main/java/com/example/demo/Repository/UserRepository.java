package com.example.demo.Repository;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User getById(Integer integer);

    List<User> findAllByRole(Role role);

    List<User> findAllByEmailAndPassword(String email,String password);

    User getByEmail(String email);

}
