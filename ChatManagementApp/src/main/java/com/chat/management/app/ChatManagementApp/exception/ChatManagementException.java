package com.chat.management.app.ChatManagementApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatManagementException extends Exception{
    private String errorMessage;
    private String errorCode;

}
