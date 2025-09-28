package com.chat.management.app.chatManagementApp.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private String responseCode;
    private String responseMessage;
}
