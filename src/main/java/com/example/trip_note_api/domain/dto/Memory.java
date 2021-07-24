package com.example.trip_note_api.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name = "memories")
@Entity
@Getter
@Setter
public class Memory {

    @Id
    @Column(name = "user_id")
    Integer userId;

    @Id
    @Column(name = "id")
    Integer id;

    @Column(name = "hotel_name")
    String hotelName;

    @Column(name = "hotel_image")
    String hotelImage;

    @Column(name = "impression")
    String impression;

    @Column(name = "accommodation_date")
    Date accommodationDate;

    @Column(name = "delete_flag")
    Integer deleteFlag;

}
