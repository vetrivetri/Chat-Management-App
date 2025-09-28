package com.chat.management.app.chatManagementApp.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDataUpdateReq {
    @JsonAlias("sessionId")
    @NotBlank(message = "Session Id id Mandatory to Update Chat Messages")
    private String chatSessionId;
    @JsonAlias("chatMessages")
    List<ChatMessageInfo> chatMessageInfoList;
}
