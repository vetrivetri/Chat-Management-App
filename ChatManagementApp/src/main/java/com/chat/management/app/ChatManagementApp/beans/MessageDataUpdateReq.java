package com.chat.management.app.ChatManagementApp.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("sessionId")
    @NotBlank(message = "Session Id id Mandatory to Update Chat Messages")
    private String chatSessionId;
    @JsonProperty("chatMessageList")
    List<ChatMessageInfo> chatMessageInfoList;
}
