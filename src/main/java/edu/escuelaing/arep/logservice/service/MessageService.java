package edu.escuelaing.arep.logservice.service;

import edu.escuelaing.arep.logservice.model.Message;
import edu.escuelaing.arep.logservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(String text) {
        Message message = new Message();
        message.setText(text);
        message.setDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getLastTenMessages() {
        return messageRepository.findTop10ByOrderByDateDesc();
    }
}
