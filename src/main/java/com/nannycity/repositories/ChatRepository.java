package com.nannycity.repositories;

import com.nannycity.entities.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    Chat getChatByNannyUser_Id(Long userId);
}
