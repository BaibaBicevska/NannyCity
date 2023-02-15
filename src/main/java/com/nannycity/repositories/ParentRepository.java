package com.nannycity.repositories;

import com.nannycity.entities.NannyUser;
import com.nannycity.entities.ParentUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ParentRepository extends CrudRepository<ParentUser, Long> {

    ParentUser findByUserNameAndPassword(String userName, String password);

    @Override
    ArrayList<ParentUser> findAll();
}
