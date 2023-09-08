package com.luizmatias.findadev.db.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserEntity clientUserEntity;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private UserEntity developerUserEntity;
    private LocalDateTime matchedAt;
    private LocalDateTime unmatchedAt;

}