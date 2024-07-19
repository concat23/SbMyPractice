package com.dev.sbmypractice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;
    private String description;


    @Builder
    public Test(Long id, boolean status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }
}
