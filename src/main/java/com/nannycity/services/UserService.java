package com.nannycity.services;

import com.nannycity.entities.NannyUser;
import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.NannyRepository;
import com.nannycity.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final NannyRepository nannyRepository;

    @Autowired // dependency injection
    public UserService(NannyRepository nannyRepository) {
        this.nannyRepository = nannyRepository;

    }

    public void createUser(NannyUser nannyUser) throws Exception {
        this.nannyRepository.save(nannyUser);
    }

    public NannyUser verifyUser(NannyUser userLoginRequest) throws Exception {
        NannyUser foundUser = this.nannyRepository.findByUserNameAndPassword(userLoginRequest.getUserName(), userLoginRequest.getPassword());

        if (foundUser == null) {throw new Exception("Email or password incorrect");}

        return foundUser;
    }


    public NannyUser findUserById(Long userId) throws Exception {
        return this.nannyRepository.findById(userId).orElseThrow();
    }

}
