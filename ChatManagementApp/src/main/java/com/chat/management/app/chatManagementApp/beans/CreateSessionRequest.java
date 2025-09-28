package com.chat.management.app.chatManagementApp.beans;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSessionRequest {
    @JsonAlias("sessionId")
    private String chatSessionId;
    @Min(3)
    @Max(100)
    @JsonAlias("userName")
    private String senderName;
    @NotNull
    @Email
    @JsonAlias("userEmail")
    @NotBlank(message = "User Email Cannot Be Empty")
    @Email(message = "Email must be valid")
    private String senderEmail;
    @JsonAlias("markFavorite")
    private boolean isFavChat = false;
    @JsonAlias("sessionName")
    private String sessionName;
}
