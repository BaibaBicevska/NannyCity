package com.nannycity.controllers;

import com.nannycity.entities.Chat;
import com.nannycity.services.ChatService;
import com.nannycity.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final UserService userService;
    public ChatController(UserService userService, ChatService chatService){
        this.userService = userService;
        this.chatService = chatService;
    }
    @GetMapping("nannyProfile/{userId}/chat")
    public String displayChatRoom(@PathVariable Long userId, Model model,
                                  @CookieValue(value = "userId") String userIdFromCookie){
        try {
            model.addAttribute("nannyUser", userService.findUserById(Long.valueOf(userIdFromCookie)));
            model.addAttribute("chats", chatService.getAllChats());
            return "groupchat";
        } catch (Exception e) {
            return "redirect:/login?message=user_not_found";
        }
    }

    @PostMapping("nannyProfile/{userId}/chat")
    public String sendChat(@PathVariable Long userId, Chat chat, Model model){
        try {

            /*Chat userChat = chatService.getChat(userId);
            if (userChat == null) {
                chatService.creatChat(chat);
            }*/
            chatService.creatChat(chat);
            model.addAttribute("nannyUser", userService.findUserById(Long.valueOf(userId)));
            model.addAttribute("chats", chatService.getAllChats());
            return "groupchat";
        } catch (Exception e){
            return "redirect:/groupchat" + "?message=send_chat_failed";
        }
    }
}