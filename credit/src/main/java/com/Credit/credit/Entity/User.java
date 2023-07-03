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
    private String last_name ;

    @Column
    private String middle_name;

    @Column
    private String perconal_code;
    public int getId() {
        return id;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setPerconal_code(String perconal_code) {
        this.perconal_code = perconal_code;
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

    public String getPerconal_code() {
        return perconal_code;
    }



}
