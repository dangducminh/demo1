package com.example.demo.Repository;

import com.example.demo.Entity.QueryByField;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

public interface CustomizeRepository<User, Integer extends Serializable> extends Repository {
    List<User> findAllByConditions(QueryByField queryByField);
}
