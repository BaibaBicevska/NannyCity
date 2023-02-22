package com.nannycity.services;

import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {
    private final ParentRepository parentRepository;

    @Autowired // dependency injection
    public ParentService(ParentRepository parentRepository) {

        this.parentRepository = parentRepository;
    }
    public void createUser(ParentUser parentUser) throws Exception {
        this.parentRepository.save(parentUser);
    }

    public ParentUser verifyUserParent(ParentUser userLoginRequest) throws Exception {
        ParentUser foundUserParent = this.parentRepository.findByUserNameAndPassword(userLoginRequest.getUserName(), userLoginRequest.getPassword());

        if (foundUserParent == null) {throw new Exception("UserName or password incorrect");}

        return foundUserParent;
    }

    public ParentUser findUserById(Long userId) throws Exception {
        return this.parentRepository.findById(userId).orElseThrow();
    }


}
