package com.chat.management.app.ChatManagementApp.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSessionResponse {
    private String sessionId;
    private GenericResponse genericErrorResponse;
}
