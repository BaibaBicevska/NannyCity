package com.nannycity.repositories;

import com.nannycity.entities.NannyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface NannyRepository extends CrudRepository<NannyUser, Long> {
    NannyUser findByUserNameAndPassword(String userName, String password);
    @Override
    ArrayList<NannyUser> findAll();

}