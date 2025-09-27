package com.chat.management.app.ChatManagementApp.repository;

import com.chat.management.app.ChatManagementApp.entity.ChatSessionHdr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChatSessionHdrRepository extends JpaRepository<ChatSessionHdr,Long> {

    Optional<ChatSessionHdr> findByChatSessionId(String sessionId) throws SQLException;

    void deleteByChatSessionId(String sessionId) throws SQLException;

}
