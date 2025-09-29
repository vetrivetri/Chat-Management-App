package com.chat.management.app.chatManagementApp.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageInfo {
    @NotBlank(message = "Message From AI-ASSISTANCE/USER Cannot Be Empty")
    private String msgFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in the future")
    @JsonIgnore
    private LocalDate msgSentDate;
    @NotBlank(message = "Message Contant Cannot Be Blank Or Empty")
    private String messageContent;
    @NotBlank(message = "Message Language Cannot Be Empty")
    private String messageLang;
    private String messageContext;
}
