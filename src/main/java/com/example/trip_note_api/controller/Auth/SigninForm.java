package com.example.trip_note_api.controller.Auth;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SigninForm {

    @NotNull
    String mailAddress;

    @NotNull
    @Length(min=8,max=20)
    String password;

}
