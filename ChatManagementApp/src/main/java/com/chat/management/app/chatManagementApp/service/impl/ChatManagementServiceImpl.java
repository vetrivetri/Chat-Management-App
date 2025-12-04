package com.chat.management.app.chatManagementApp.service.impl;

import com.chat.management.app.chatManagementApp.beans.*;
import com.chat.management.app.chatManagementApp.config.ChatManagementConstants;
import com.chat.management.app.chatManagementApp.entity.ChatManagementMessageData;
import com.chat.management.app.chatManagementApp.entity.ChatSessionHdr;
import com.chat.management.app.chatManagementApp.exception.ChatManagementException;
import com.chat.management.app.chatManagementApp.repository.ChatManageMsgDataRepo;
import com.chat.management.app.chatManagementApp.repository.ChatSessionHdrRepository;
import com.chat.management.app.chatManagementApp.service.ChatManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatManagementServiceImpl implements ChatManagementService {
    private static final Logger logger = LoggerFactory.getLogger(ChatManagementServiceImpl.class);
    public static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChatSessionHdrRepository chatSessionHdrRepository;
    @Autowired
    ChatManageMsgDataRepo chatManageMsgDataRepo;
    @Override
    public CreateSessionResponse createChatSession(CreateSessionRequest createSessionRequest) throws ChatManagementException {
       try {
           var retVal= chatSessionHdrRepository.findByChatSessionId(createSessionRequest.getChatSessionId());
           if(!retVal.isEmpty()){
               throw new ChatManagementException(LocalServiceEnum.SESSION_AVAILABLE_EXP.retVal, ChatManagementConstants.NO_DATA_FOUND);
           }
           if (StringUtils.isEmpty(createSessionRequest.getChatSessionId())) {
               createSessionRequest.setChatSessionId(UUID.randomUUID().toString());
           }


           var chatHdr=  mapper.convertValue(createSessionRequest, ChatSessionHdr.class);
           chatHdr.setCreatedDt(LocalDate.now());
           chatSessionHdrRepository.save(chatHdr);
       }catch(Exception ex){
           logger.error("Failed to process request for create Chat session", ex);
        throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
       }
    return new CreateSessionResponse(createSessionRequest.getChatSessionId(),
            new GenericResponse
                    (ChatManagementConstants.DB_GENERIC_EXCEPTION,"Chat Session Created Succesfully For the User"));
}

    @Override
    public void updateChatSession(MessageDataUpdateReq messageDataUpdateReq) throws ChatManagementException {
        try {


           var retVal= chatSessionHdrRepository.findByChatSessionId(messageDataUpdateReq.getChatSessionId());
            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            final ChatSessionHdr chatHeaderData = retVal.get();


            var messageDataUpdate =messageDataUpdateReq.getChatMessageInfoList().stream()
                    .map(x -> {
                        ChatManagementMessageData dataBean=  mapper.convertValue(x, ChatManagementMessageData.class);
                        dataBean.setMsgSentDate(x.getMsgSentDate()==null ?LocalDate.now():x.getMsgSentDate());
                        dataBean.setChatSessionHdr(chatHeaderData);
                        return dataBean;
                    }).collect(Collectors.toList());
            chatManageMsgDataRepo.saveAll(messageDataUpdate);
        }catch (ChatManagementException chEx){
            throw  chEx;
        }catch (SQLException sqEx){
            logger.error("Db failure", sqEx.getMessage());
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }catch(Exception ex){
            logger.error("Failed to process request for create Chat session", ex);
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }

    }

    @Override
    @Transactional(rollbackOn = ChatManagementException.class)
    public void deleteChatSession(String chatSessionId) throws ChatManagementException {
        try{
            var retVal= chatSessionHdrRepository.findByChatSessionId(chatSessionId);
            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            chatSessionHdrRepository.deleteByChatSessionId(chatSessionId);
        }catch (ChatManagementException chEx){
            throw  chEx;
        }catch (SQLException sqEx){
            logger.error("Db failure", sqEx.getMessage());
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }catch(Exception ex){
            logger.error("Failed to process request for create Chat session", ex);
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    @Override
    public Page<ChatManagementMessageData> retrieveChatSession(String chatSessionId, Pageable pageReq) throws ChatManagementException {
        try{
            var retVal= chatSessionHdrRepository.findByChatSessionId(chatSessionId);

            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            final ChatSessionHdr chatHeaderData = retVal.get();
          return  chatManageMsgDataRepo.findByChatSessionHdr_ChatSessionId(chatHeaderData.getChatSessionId(),pageReq);
        }catch (ChatManagementException chEx){
           throw  chEx;
        }
        catch (SQLException sqEx){
            logger.error("Db failure", sqEx.getMessage());
            throw new ChatManagementException(LocalServiceEnum.DATABASE_ERROR.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
        catch(Exception ex){
            logger.error("Failed to process request for create Chat session", ex);
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    @Override
    public void updateMsgSession(UpdateChatSessionInfoReq updateChatSessionInfoReq) throws ChatManagementException {
        try{
            var retVal= chatSessionHdrRepository.findByChatSessionId(updateChatSessionInfoReq.getSessionId());
            if(retVal.isEmpty()){
                throw new ChatManagementException(LocalServiceEnum.NO_DATA_FOUND.retVal, ChatManagementConstants.NO_DATA_FOUND);
            }
            ChatSessionHdr chatHeaderData = retVal.get();
          if(  StringUtils.isNotEmpty( updateChatSessionInfoReq.getSessionName()))
              chatHeaderData.setSessionName(updateChatSessionInfoReq.getSessionName()) ;

            chatHeaderData.setFavChat(updateChatSessionInfoReq.isFavChat());
            chatSessionHdrRepository.save(chatHeaderData);
        }catch (ChatManagementException chEx){
            throw  chEx;
        }catch (Exception ex){
            logger.error("Failed to process request for create Chat session", ex);
            throw new ChatManagementException(LocalServiceEnum.GENERIC_EXCEPTION.retVal, ChatManagementConstants.DB_GENERIC_EXCEPTION);
        }
    }

    enum LocalServiceEnum{

        NO_DATA_FOUND("No Data Found For the Given Identifier"),
        GENERIC_EXCEPTION("Generic Exception Occured During API Call, Kindly contact SYSTEM ADMIN"),
        DATABASE_ERROR("Database Error Kindly contact DBA"),
        SESSION_AVAILABLE_EXP("User Session is already available");
        private String retVal;
        private LocalServiceEnum(String data){
            this.retVal=data;
        }
    }
}
