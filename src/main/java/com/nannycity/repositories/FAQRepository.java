package com.nannycity.repositories;

import com.nannycity.entities.FAQ;
import com.nannycity.entities.ParentUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FAQRepository extends CrudRepository<FAQ, Long> {
    @Override
    ArrayList<FAQ> findAll();
}
