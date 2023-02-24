package com.nannycity.services;

import com.nannycity.entities.Chat;
import com.nannycity.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatService {

    private ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }
    public ArrayList<Chat> getAllChats() {
        return (ArrayList<Chat>) this.chatRepository.findAll();
    }

    public void creatChat(Chat chat) throws Exception {
        this.chatRepository.save(chat);
    }

    public Chat getChat(Long userId) {
        return this.chatRepository.getChatByNannyUser_Id(userId);
    }
}
