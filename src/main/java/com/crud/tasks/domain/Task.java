package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

}

