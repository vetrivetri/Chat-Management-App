package com.chat.management.app.ChatManagementApp.beans;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChatSessionInfoReq {
    @NotBlank(message = "Session Id id Mandatory to Update Chat Data")
    private String sessionId;
    private boolean isFavChat = false;
    private String sessionName;
}
