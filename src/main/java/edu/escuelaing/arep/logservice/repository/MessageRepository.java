package edu.escuelaing.arep.logservice.repository;

import edu.escuelaing.arep.logservice.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findTop10ByOrderByDateDesc();
}
