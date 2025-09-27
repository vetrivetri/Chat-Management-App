package com.chat.management.app.ChatManagementApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="CHAT_MNG_SESSION_HDR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatSessionHdr {

    @Id
    @SequenceGenerator(name = "chatMsgHdrSeq", sequenceName = "CHAT_MSG_SESSION_SEQ")
    @Column(name = "CHAT_SESS_HDR_PK_ID")
    private Long sessionHdrPkId;
    @Column(name = "SESSION_ID")
    private String chatSessionId;
    @Column(name = "SENDER_NAME")
    private String senderName;
    @Column(name = "SENDER_EMAIL")
    private String senderEmail;
    @Column(name = "IS_FAV_CHAT")
    private boolean isFavChat;
    @Column(name = "SESSION_CREATED_DATE")
    private LocalDate createdDt;
    @Column(name = "SESSION_MODIFED_DATE")
    private LocalDate modifiedDt;
    @Column(name = "SESSION_NAME")
    private String sessionName;
    @OneToMany
    @JoinColumn(name = "CHAT_HDR_DATA_FK")
    private List<ChatManagementMessageData> employees;


}
