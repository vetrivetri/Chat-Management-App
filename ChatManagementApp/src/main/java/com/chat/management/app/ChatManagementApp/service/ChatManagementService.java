package com.chat.management.app.ChatManagementApp.service;

import com.chat.management.app.ChatManagementApp.beans.CreateSessionRequest;
import com.chat.management.app.ChatManagementApp.beans.CreateSessionResponse;
import com.chat.management.app.ChatManagementApp.beans.MessageDataUpdateReq;
import com.chat.management.app.ChatManagementApp.beans.UpdateChatSessionInfoReq;
import com.chat.management.app.ChatManagementApp.entity.ChatManagementMessageData;
import com.chat.management.app.ChatManagementApp.exception.ChatManagementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ChatManagementService {
    CreateSessionResponse createChatSession(CreateSessionRequest createSessionRequest) throws ChatManagementException;
    void updateChatSession(MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException;
    void deleteChatSession(String chatSessionId) throws ChatManagementException;
    Page<ChatManagementMessageData> retrieveChatSession(String chatSessionId, PageRequest pageReq) throws ChatManagementException;
    void updateMsgSession(UpdateChatSessionInfoReq updateChatSessionInfoReq) throws ChatManagementException;
}
