package com.chat.management.app.chatManagementApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_HDR_SEQ")
    @SequenceGenerator(name = "CHAT_HDR_SEQ", sequenceName = "CHAT_MNG_SESSION_SEQ", allocationSize = 1,initialValue = 1)
    @Column(name = "CHAT_SESS_HDR_PK_ID")
    private Long sessionHdrPkId;
    @Column(name = "SESSION_ID",unique = true)
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
    @OneToMany(mappedBy = "chatSessionHdr", cascade = CascadeType.ALL, orphanRemoval = true
    ,fetch = FetchType.LAZY)
    private List<ChatManagementMessageData> sessionMessages;


}
