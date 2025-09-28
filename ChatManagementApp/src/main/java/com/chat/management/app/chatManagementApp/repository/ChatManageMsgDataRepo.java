package com.chat.management.app.chatManagementApp.repository;

import com.chat.management.app.chatManagementApp.entity.ChatManagementMessageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface ChatManageMsgDataRepo extends JpaRepository<ChatManagementMessageData,Long> {
    //@Query("SELECT o FROM ChatManagementMessageData o WHERE o.chatSessionHdr.chatSessionId = :chatSesionId")
    Page<ChatManagementMessageData> findByChatSessionHdr_ChatSessionId(String chatSessionId, Pageable pageable) throws SQLException;

}
