package com.nannycity.repositories;

import com.nannycity.entities.NannyUser;
import org.springframework.data.repository.CrudRepository;

public interface NannyRepository extends CrudRepository<NannyUser, Long> {

}
