package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//TODO: change java.util.Date to JodaTime
@Entity
@Table(name = "carorder")
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date();
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt = new Date();
    @Column(name = "starts_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startsAt;
    @Column(name = "expires_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiresAt;
    @Column(name ="status", length = 10, nullable = false)
    private int status = 0;

    public long getId() {
        return id;
    }

    public Order setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Order setCar(Car car) {
        this.car = car;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Order setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Order setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Order setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public Order setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public Order setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Order setStatus(int status) {
        this.status = status;
        return this;
    }

    public Order setDeleted(){
        status = -1;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order carOrder = (Order) o;
        return id == carOrder.id &&
                Double.compare(carOrder.price, price) == 0 &&
                status == carOrder.status &&
                Objects.equals(user, carOrder.user) &&
                Objects.equals(car, carOrder.car) &&
                Objects.equals(description, carOrder.description) &&
                Objects.equals(startsAt, carOrder.startsAt) &&
                Objects.equals(expiresAt, carOrder.expiresAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, car, description, price, startsAt, expiresAt, status);
    }
}
