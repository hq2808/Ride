package com.example.postservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "Badges", schema = "dbo")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 40)
    private String name;

    @Column(name = "UserId", nullable = false)
    private Integer userId;

    @Column(name = "\"Date\"", nullable = false)
    private Instant date;

}