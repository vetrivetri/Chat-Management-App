package com.chat.management.app.ChatManagementApp.controller;

import com.chat.management.app.ChatManagementApp.beans.CreateSessionRequest;
import com.chat.management.app.ChatManagementApp.beans.CreateSessionResponse;
import com.chat.management.app.ChatManagementApp.beans.MessageDataUpdateReq;
import com.chat.management.app.ChatManagementApp.beans.UpdateChatSessionInfoReq;
import com.chat.management.app.ChatManagementApp.exception.ChatManagementException;
import com.chat.management.app.ChatManagementApp.service.ChatManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController("/v1/chat/")
public class ChatManagementControllerV1 {

    @Autowired
    ChatManagementService chatManagementService;

    @PostMapping("create-new-session")
    public ResponseEntity<?> createChatSession(@RequestBody CreateSessionRequest createSessionRequest) throws ChatManagementException {
        CreateSessionResponse resp = chatManagementService.createChatSession(createSessionRequest);
        return new ResponseEntity<>(resp, HttpStatusCode.valueOf(200));
    }

    @PutMapping("update-chat-session")
    public ResponseEntity<?> updateChatSession(@RequestBody MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException{
        chatManagementService.updateChatSession(messageDataUpdateReq);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("delete-chat-session/{chat-session-id}")
    public ResponseEntity<?> deleteChatSession(@PathVariable String chatSessionId ) throws ChatManagementException{
        chatManagementService.deleteChatSession(chatSessionId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("retrieve-chat-session/{chat-session-id}")
    public Page<?> retrieveChatSession(@PathVariable String chatSessionId ,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size
    ) throws ChatManagementException {
        PageRequest pageable = PageRequest.of(page, size);
        return chatManagementService.retrieveChatSession(chatSessionId,pageable);
    }

    @PutMapping("update-session-messages")
    public ResponseEntity<?> updateMsgSession(@RequestBody UpdateChatSessionInfoReq updateChatSessionInfoReq)throws ChatManagementException{
        chatManagementService.updateMsgSession(updateChatSessionInfoReq);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
