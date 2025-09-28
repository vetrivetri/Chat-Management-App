package com.chat.management.app.chatManagementApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="CHAT_MANAG_MSG_DATA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatManagementMessageData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_MSG_SEQ")
    @SequenceGenerator(name = "CHAT_MSG_SEQ", sequenceName = "CHAT_MANAGE_MSG_DATA_SEQ", allocationSize = 1,initialValue = 1)
    @Column(name = "MSG_DATA_PK_ID")
    private Long msgDataPkId;
    @Column(name = "MSG_FROM")
    private String msgFrom;
    @Column(name = "MSG_SENT_DATE")
    private LocalDate msgSentDate;
    @Column(name = "MESSAGE_CONTENT")
    private String messageContent;
    @Column(name = "MESSAGE_LANG")
    private String messageLang;
    @Column(name = "MESSAGE_CONTEXT")
    private String messageContext;
    @ManyToOne
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "SESSION_ID")
    @JsonIgnoreProperties("sessionMessages")
    private ChatSessionHdr chatSessionHdr;

}
