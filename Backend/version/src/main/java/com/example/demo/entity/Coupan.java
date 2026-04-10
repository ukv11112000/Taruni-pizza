package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Coupan")
public class Coupan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int coupanid;

    private String coupanName;
    private int coupanType;
    private String coupanDescription;
    private int coupanPizzaid;

    public Coupan() {
    }

    public Coupan(int coupanid, String coupanName, int coupanType, String coupanDescription, int coupanPizzaid) {
        this.coupanid = coupanid;
        this.coupanName = coupanName;
        this.coupanType = coupanType;
        this.coupanDescription = coupanDescription;
        this.coupanPizzaid = coupanPizzaid;
    }

    public int getCoupanid() {
        return coupanid;
    }

    public void setCoupanid(int coupanid) {
        this.coupanid = coupanid;
    }

    public String getCoupanName() {
        return coupanName;
    }

    public void setCoupanName(String coupanName) {
        this.coupanName = coupanName;
    }

    public int getCoupanType() {
        return coupanType;
    }

    public void setCoupanType(int coupanType) {
        this.coupanType = coupanType;
    }

    public String getCoupanDescription() {
        return coupanDescription;
    }

    public void setCoupanDescription(String coupanDescription) {
        this.coupanDescription = coupanDescription;
    }

    public int getCoupanPizzaid() {
        return coupanPizzaid;
    }

    public void setCoupanPizzaid(int coupanPizzaid) {
        this.coupanPizzaid = coupanPizzaid;
    }
}
