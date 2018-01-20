package com.rockka.carrent.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt = new Date();
    @Column(name = "is_deleted", nullable = false)
    private int isDeleted = 0;
    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> carOrders;

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

    public int getIsDeleted() {
        return isDeleted;
    }

    public Car setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public Car setDeleted(){
        isDeleted = 1;
        return this;
    }

    public List<Order> getCarOrders() {
        return carOrders;
    }

    public Car setCarOrders(List<Order> carOrders) {
        this.carOrders = carOrders;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Double.compare(car.price, price) == 0 &&
                isDeleted == car.isDeleted &&
                Objects.equals(name, car.name) &&
                Objects.equals(country, car.country) &&
                Objects.equals(color, car.color) &&
                Objects.equals(description, car.description) &&
                Objects.equals(releaseDate, car.releaseDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, country, color, description, price, releaseDate, isDeleted);
    }
}
