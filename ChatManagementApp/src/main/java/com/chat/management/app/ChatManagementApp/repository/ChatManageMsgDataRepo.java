package com.chat.management.app.ChatManagementApp.repository;

import com.chat.management.app.ChatManagementApp.entity.ChatManagementMessageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface ChatManageMsgDataRepo extends JpaRepository<ChatManagementMessageData,Long> {

    Page<ChatManagementMessageData> findByChatHdrPkId(Long chatHdrPkId, PageRequest pageable) throws SQLException;

}
