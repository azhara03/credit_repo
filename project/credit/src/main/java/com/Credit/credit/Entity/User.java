package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String fio;

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }
}
