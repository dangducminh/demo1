package com.example.demo.Repository;

import com.example.demo.Entity.QUser;
import com.example.demo.Entity.QueryByField;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.util.List;

@Repository
public class CustomizeRepositoryImpl implements CustomizeRepository{
    @Autowired
    private EntityManager entityManager;

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public List findAllByConditions(QueryByField queryByField) {
        System.out.println( "repository"+ queryByField);
        BooleanBuilder where = build(queryByField);
        QUser quser = QUser.user;
        JPAQuery<User> query = new JPAQuery<>(entityManager);

        return query.from(quser).where(where).fetch();
    }

    private BooleanBuilder build(QueryByField queryByField ){
        QUser qUser = QUser.user;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String email = queryByField.getEmail();
        if (email != null) {
            booleanBuilder.and(qUser.email.eq(email));
        }

        String name = queryByField.getName();
        if (name != null) {
            booleanBuilder.and(qUser.name.eq(name));
        }

        String address = queryByField.getAddress();
        if (address != null) {
            booleanBuilder.and(qUser.address.eq(address));
        }

        String phone = queryByField.getPhone();
        if (phone != null) {
            booleanBuilder.and(qUser.phone.eq(phone));
        }

        Role role = queryByField.getRole();
        if (role != null) {
            booleanBuilder.and(qUser.role.eq(role));
        }

        return booleanBuilder;
    }
}
