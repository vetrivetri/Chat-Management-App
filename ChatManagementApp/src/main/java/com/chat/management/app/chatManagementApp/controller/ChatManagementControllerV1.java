package com.chat.management.app.chatManagementApp.controller;

import com.chat.management.app.chatManagementApp.beans.*;
import com.chat.management.app.chatManagementApp.config.ChatManagementConstants;
import com.chat.management.app.chatManagementApp.exception.ChatManagementException;
import com.chat.management.app.chatManagementApp.service.ChatManagementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/v1/chat")
public class ChatManagementControllerV1 {

    @Autowired
    ChatManagementService chatManagementService;

    @PostMapping("/chat-sessions")
    public ResponseEntity<?> createChatSession(@Valid @RequestBody CreateSessionRequest createSessionRequest) throws ChatManagementException {
        var resp = chatManagementService.createChatSession(createSessionRequest);
        return new ResponseEntity<>(resp, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/chat-sessions/messages")
    public ResponseEntity<?> updateChatSession(@Valid @RequestBody MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException{
        chatManagementService.updateChatSession(messageDataUpdateReq);
        return new ResponseEntity<>(new GenericResponse(ChatManagementConstants.DB_GENERIC_SUCCESS_CODE,ChatManagementConstants.API_CALL_SUCCESS),HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/chat-sessions/{chatSessionId}")
    public ResponseEntity<?> deleteChatSession(@NotBlank @PathVariable String chatSessionId ) throws ChatManagementException{
        chatManagementService.deleteChatSession(chatSessionId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/chat-sessions/{chatSessionId}")
    public Page<?> retrieveChatSession(@NotBlank @PathVariable String chatSessionId ,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size
    ) throws ChatManagementException {
        var pageable = PageRequest.of(page, size);
        return chatManagementService.retrieveChatSession(chatSessionId,pageable);

    }

    @PutMapping("/chat-sessions")
    public ResponseEntity<?> updateMsgSession(@Valid @RequestBody UpdateChatSessionInfoReq updateChatSessionInfoReq)throws ChatManagementException{
        chatManagementService.updateMsgSession(updateChatSessionInfoReq);
        return new ResponseEntity<>(new GenericResponse(ChatManagementConstants.DB_GENERIC_SUCCESS_CODE,ChatManagementConstants.API_CALL_SUCCESS),HttpStatusCode.valueOf(200));
    }

}
