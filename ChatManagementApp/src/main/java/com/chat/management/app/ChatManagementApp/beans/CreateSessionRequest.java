package com.chat.management.app.ChatManagementApp.beans;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSessionRequest {
    @JsonProperty("sessionId")
    private String chatSessionId;
    @Min(3)
    @Max(100)
    @JsonProperty("userName")
    private String senderName;
    @NotNull
    @Email
    @JsonProperty("userEmail")
    @NotBlank(message = "User Email Cannot Be Empty")
    @Email(message = "Email must be valid")
    private String senderEmail;
    @JsonProperty("markFavorite")
    private Boolean isFavChat = false;
    @JsonProperty("sessionName")
    private String sessionName;
}
