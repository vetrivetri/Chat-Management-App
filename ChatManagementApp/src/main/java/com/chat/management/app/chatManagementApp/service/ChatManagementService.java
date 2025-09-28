package com.chat.management.app.chatManagementApp.service;

import com.chat.management.app.chatManagementApp.beans.CreateSessionRequest;
import com.chat.management.app.chatManagementApp.beans.CreateSessionResponse;
import com.chat.management.app.chatManagementApp.beans.MessageDataUpdateReq;
import com.chat.management.app.chatManagementApp.beans.UpdateChatSessionInfoReq;
import com.chat.management.app.chatManagementApp.entity.ChatManagementMessageData;
import com.chat.management.app.chatManagementApp.exception.ChatManagementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface ChatManagementService {
    CreateSessionResponse createChatSession(CreateSessionRequest createSessionRequest) throws ChatManagementException;
    void updateChatSession(MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException;
    void deleteChatSession(String chatSessionId) throws ChatManagementException;
    Page<ChatManagementMessageData> retrieveChatSession(String chatSessionId, Pageable pageReq) throws ChatManagementException;
    void updateMsgSession(UpdateChatSessionInfoReq updateChatSessionInfoReq) throws ChatManagementException;
}
