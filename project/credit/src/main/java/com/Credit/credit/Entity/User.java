package com.Credit.credit.Entity;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String first_name;
    @Column
    private String last_name;
    @Column
    private String middle_name;
    @Column
    private int perconal_code;

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public int getPerconal_code() {
        return perconal_code;
    }
}
