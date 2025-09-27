package com.chat.management.app.ChatManagementApp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="CHAT_MANAG_MSG_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatManagementMessageData {

    @Id
    @SequenceGenerator(name = "chatMsgSeq", sequenceName = "CHAT_MSG_DTL_SEQ")
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
    @JoinColumn(name = "CHAT_HDR_DATA_FK", insertable = false, updatable = false)
    private ChatSessionHdr chatHdrPkId;

}
