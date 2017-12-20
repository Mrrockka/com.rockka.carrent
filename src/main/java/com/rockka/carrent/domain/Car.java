package com.rockka.carrent.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;
    @Column(name = "country")
    private String country;
    @Column(name = "color")
    private String color;
    @Column(name = "description")
    private String description;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "price")
    private double price;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;
    @Column(name = "is_deleted")
    private char isDeleted;
    @Column(name = "is_free")
    private char isFree;
    @JsonBackReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getMark() {
        return mark;
    }

    public Car setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Car setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Car setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public Car setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Car setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Car setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Car setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Car setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public Car setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public char getIsFree() {
        return isFree;
    }

    public Car setIsFree(char isFree) {
        this.isFree = isFree;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Car setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
