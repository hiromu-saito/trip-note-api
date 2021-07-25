package com.example.trip_note_api.controller.Memory;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemoryForm {

    @NotNull
    private String hotelName;

    @Length(max=30)
    private String impression;

    private String hotelImage;

    @NotNull
    private String accommodationDate;

}
