package com.rockka.carrent.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    @Column(name = "country", length = 15)
    private String country;
    @Column(name = "color", length = 20)
    private String color;
    @Column(name = "description")
    private String description;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;
    @Column(name = "is_deleted", nullable = false)
    private char isDeleted;
    @JsonBackReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarOrder> carOrders;

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Car setName(String name) {
        this.name = name;
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

    public Car setDeleted(){
        isDeleted = 'y';
        return this;
    }

    public List<CarOrder> getCarOrders() {
        return carOrders;
    }

    public Car setCarOrders(List<CarOrder> carOrders) {
        this.carOrders = carOrders;
        return this;
    }
}
