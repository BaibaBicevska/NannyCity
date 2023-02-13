package com.nannycity.repositories;

import com.nannycity.entities.NannyUser;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NannyRepository extends CrudRepository<NannyUser, Long> {
    NannyUser findByUserNameAndPassword(String userName, String password);
    @Override
    ArrayList<NannyUser> findAll();

}
