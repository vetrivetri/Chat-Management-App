package com.chat.management.app.ChatManagementApp.beans;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageInfo {
    @NotBlank(message = "Message From AI-ASSISTANCE/USER Cannot Be Empty")
    private String msgFrom;
    private String msgSentDate;
    @NotBlank(message = "Message Contant Cannot Be Blank Or Empty")
    private String messageContent;
    @NotBlank(message = "Message Language Cannot Be Empty")
    private String messageLang;
    private String messageContext;
}
