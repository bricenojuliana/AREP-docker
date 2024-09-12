package edu.escuelaing.arep.logservice.controller;

import edu.escuelaing.arep.logservice.model.Message;
import edu.escuelaing.arep.logservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message postMessage(@RequestBody String text) {
        return messageService.saveMessage(text);
    }

    @GetMapping("/last10")
    public List<Message> getLastTenMessages() {
        return messageService.getLastTenMessages();
    }
}

