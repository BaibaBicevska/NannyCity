package com.nannycity.services;


import com.nannycity.entities.FAQ;
import com.nannycity.entities.NannyUser;
import com.nannycity.entities.ParentUser;
import com.nannycity.repositories.FAQRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FAQService {

    private FAQRepository faqRepository;

    public FAQService(FAQRepository faqRepository){
        this.faqRepository = faqRepository;
    }
    public ArrayList<FAQ> getAllChats() {
        return (ArrayList<FAQ>) this.faqRepository.findAll();
    }

    public void creatChat(FAQ faq) throws Exception {
        this.faqRepository.save(faq);
    }

}
