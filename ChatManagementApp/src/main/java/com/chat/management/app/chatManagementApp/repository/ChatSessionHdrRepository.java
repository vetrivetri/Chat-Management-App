package com.chat.management.app.chatManagementApp.repository;

import com.chat.management.app.chatManagementApp.entity.ChatManagementMessageData;
import com.chat.management.app.chatManagementApp.entity.ChatSessionHdr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface ChatSessionHdrRepository extends JpaRepository<ChatSessionHdr,Long> {

    Optional<ChatSessionHdr> findByChatSessionId(String sessionId) throws SQLException;

    void deleteByChatSessionId(String sessionId) throws SQLException;

    //Page<ChatManagementMessageData> findBySessionMessages_ChatSessionHdr(ChatSessionHdr sessionId, Pageable pageable) throws SQLException;

}
