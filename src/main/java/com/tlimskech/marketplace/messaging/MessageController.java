package com.tlimskech.marketplace.messaging;


import com.tlimskech.marketplace.core.data.SearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/messages/create")
    public ResponseEntity<?> addMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.create(message));
    }

    @PostMapping("/api/messages/getAllUserMessages")
    public ResponseEntity<?> getUserMessages(@RequestBody SearchRequest request) {
        return ResponseEntity.ok(messageService.findUserMessages(request));
    }
}
