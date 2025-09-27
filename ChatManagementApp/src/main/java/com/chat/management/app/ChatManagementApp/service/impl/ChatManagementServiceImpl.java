package com.chat.management.app.ChatManagementApp.service.impl;

import com.chat.management.app.ChatManagementApp.beans.*;
import com.chat.management.app.ChatManagementApp.config.ChatManagementConstants;
import com.chat.management.app.ChatManagementApp.entity.ChatManagementMessageData;
import com.chat.management.app.ChatManagementApp.entity.ChatSessionHdr;
import com.chat.management.app.ChatManagementApp.exception.ChatManagementException;
import com.chat.management.app.ChatManagementApp.repository.ChatManageMsgDataRepo;
import com.chat.management.app.ChatManagementApp.repository.ChatSessionHdrRepository;
import com.chat.management.app.ChatManagementApp.service.ChatManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatManagementServiceImpl implements ChatManagementService {
    @Autowired
    ChatSessionHdrRepository chatSessionHdrRepository;
    @Autowired
    ChatManageMsgDataRepo chatManageMsgDataRepo;
    @Override
    public CreateSessionResponse createChatSession(CreateSessionRequest createSessionRequest) throws ChatManagementException {
       try {
           if (StringUtils.isEmpty(createSessionRequest.getSessionName())) {
               createSessionRequest.setChatSessionId(UUID.randomUUID().toString());
           }
           final ObjectMapper mapper = new ObjectMapper();
           ChatSessionHdr chatHdr=  mapper.convertValue(createSessionRequest, ChatSessionHdr.class);
           chatHdr.setCreatedDt(LocalDate.now());
           chatSessionHdrRepository.save(chatHdr);
       }catch(Exception ex){
        throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
       }
    return new CreateSessionResponse(createSessionRequest.getChatSessionId(),
            new GenericResponse
                    (ChatManagementConstants.DB_GENERIC_EXCEPTION,"Chat Session Created Succesfully For the User"));
}

    @Override
    public void updateChatSession(MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException {
        try {
            final ObjectMapper mapper = new ObjectMapper();

           Optional<ChatSessionHdr> retVal= chatSessionHdrRepository.findByChatSessionId(messageDataUpdateReq.getChatSessionId());
            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            final ChatSessionHdr chatHeaderData = retVal.get();


            List<ChatManagementMessageData> messageDataUpdate =messageDataUpdateReq.getChatMessageInfoList().stream()
                    .map(x -> {
                        ChatManagementMessageData dataBean=  mapper.convertValue(x, ChatManagementMessageData.class);
                        dataBean.setMsgSentDate(LocalDate.now());
                        dataBean.setMsgDataPkId(chatHeaderData.getSessionHdrPkId());
                        return dataBean;
                    }).collect(Collectors.toList());
            chatManageMsgDataRepo.saveAll(messageDataUpdate);
        }catch (SQLException sqEx){
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }catch(Exception ex){
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }

    }

    @Override
    public void deleteChatSession(String chatSessionId) throws ChatManagementException {
        try{
            chatSessionHdrRepository.deleteByChatSessionId(chatSessionId);
        }catch (SQLException sqEx){
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }catch(Exception ex){
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    @Override
    public Page<ChatManagementMessageData> retrieveChatSession(String chatSessionId, PageRequest pageReq) throws ChatManagementException {
        try{
            Optional<ChatSessionHdr> retVal= chatSessionHdrRepository.findByChatSessionId(chatSessionId);

            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            final ChatSessionHdr chatHeaderData = retVal.get();
          return  chatManageMsgDataRepo.findByChatHdrPkId(chatHeaderData.getSessionHdrPkId(),pageReq);
        }catch (SQLException sqEx){
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
        catch(Exception ex){
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    @Override
    public void updateMsgSession(UpdateChatSessionInfoReq updateChatSessionInfoReq) throws ChatManagementException {
        try{
            Optional<ChatSessionHdr> retVal= chatSessionHdrRepository.findByChatSessionId(updateChatSessionInfoReq.getSessionId());
            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            ChatSessionHdr chatHeaderData = retVal.get();
          if(  StringUtils.isNotEmpty( updateChatSessionInfoReq.getSessionName()))
              chatHeaderData.setSessionName(updateChatSessionInfoReq.getSessionName()) ;

            chatHeaderData.setFavChat(updateChatSessionInfoReq.isFavChat());
            chatSessionHdrRepository.save(chatHeaderData);
        }catch (Exception ex){
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    enum LocalServiceEnum{

        NO_DATA_FOUND("No Data Found For the Given Identifier"),
        GENERIC_EXCEPTION("Generic Exception Occured During API Call, Kindly contact SYSTEM ADMIN"),
        DATABASE_ERROR("Database Error Kindly contact DBA");
        private String retVal;
        private LocalServiceEnum(String data){
            this.retVal=data;
        }
    }
}
