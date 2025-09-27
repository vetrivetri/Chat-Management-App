package com.chat.management.app.ChatManagementApp.beans;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private String responseCode;
    private String responseMessage;
}
