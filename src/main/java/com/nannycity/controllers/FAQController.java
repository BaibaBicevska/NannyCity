package com.nannycity.controllers;

import com.nannycity.entities.FAQ;

import com.nannycity.entities.NannyUser;
import com.nannycity.repositories.FAQRepository;
import com.nannycity.services.FAQService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.nannycity.repositories.FAQRepository.*;

@Controller
public class FAQController {


    private final FAQService faqService;

    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    public FAQController(FAQService faqService, FAQRepository faqRepository) {
//            this.parentService = parentService;
        this.faqService = faqService;
        this.faqRepository = faqRepository;
    }

    @GetMapping("/question")
    public String displayQuestionRoom(Model model) {
        try {
            Iterable<FAQ> questions = faqRepository.findAll();
            model.addAttribute("questions", faqService.getAllChats());
            return "question";
        } catch (Exception e) {
            return "redirect:/index?message=user_not_found";
        }
    }

    @GetMapping("/FAQ1")
    public String basicFAQ() {

        return "FAQ1";
    }

    @PostMapping("/FAQ1")
    public String addQuestion(@Valid FAQ faq, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.hasErrors());
            return "/FAQ1";
        }
        faqRepository.save(faq);
        return "question";
    }
}

//    @PostMapping("FAQ")
//    public String sendQuestion( FAQ faq){
//        try {
//            faqService.creatChat(faq);
//            return "redirect:/question/;
//        } catch (Exception e){
//            return "redirect:/FAQ1/" + "?message=send_chat_failed";
//        }
//    }


//    @GetMapping("/question")
//    public String showQuestion(Model model) {
//        Iterable<FAQ> questions = faqRepository.findAll();
//        model.addAttribute("questions", questions);
//        return "question";
//    }
//}



//    @PostMapping("FAQ/{userId}")
//        public String sendChat(@PathVariable Long userId, FAQ faq){
//            try {
//                faqService.creatChat(faq);
//                return "redirect:/FAQ/" + userId;
//            } catch (Exception e){
//                return "redirect:/FAQ/" + userId + "?message=send_chat_failed";
//            }
//        }
//    }

//    @PostMapping("FAQ1")
//    public String addQuestion(
//            @RequestParam String message,
//            Modelaa model){
//        FAQ faq = new FAQ(message);
//
//
//            return "redirect:/FAQ1/";
//
//    }