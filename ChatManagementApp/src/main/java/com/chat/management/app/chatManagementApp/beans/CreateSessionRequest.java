package com.chat.management.app.chatManagementApp.beans;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Session ID must be alphanumeric no special characters allowed")
    private String chatSessionId;
    @Size(min = 5, max = 100, message = "User Name must be between 5 and 100 characters")
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
