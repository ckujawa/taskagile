package com.ckujawa.taskagile.web.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegistrationPayload {
    
    @Size(min=2, max=50, message = "Username must be between 2 and 50 characters.")
    @NotNull
    private String userName;

    @Email(message = "Email address really should be an email address.")
    @Size(max = 100, message = "Email address can not be more than 100 characters.")
    @NotNull
    private String emailAddress;

    @Size(min=8,max=30,message="Password must be between 8 and 30 characters.")
    @NotNull
    private String password;
}
