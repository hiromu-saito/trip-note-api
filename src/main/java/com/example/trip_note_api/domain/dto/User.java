package com.example.trip_note_api.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "mail_address")
    String mailAddress;

    @Column(name = "password")
    String password;

    @Column(name = "token")
    String token;

    @Column(name = "delete_flag")
    Integer deleteFlag;

}
