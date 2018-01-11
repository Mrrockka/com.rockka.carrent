package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "carorder")
public class CarOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column
    private String description;
    @Column
    private double price;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;
    @Column(name = "starts_at")
    @Temporal(TemporalType.DATE)
    private Date startsAt;
    @Column(name = "expire")
    @Temporal(TemporalType.DATE)
    private Date expiresAt;
    @Column(name ="is_active")
    private char isActive;
    @Column(name = "is_closed")
    private char isClosed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public char getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(char isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOrder carOrder = (CarOrder) o;
        return id == carOrder.id &&
                Double.compare(carOrder.price, price) == 0 &&
                isActive == carOrder.isActive &&
                isClosed == carOrder.isClosed &&
                Objects.equals(user, carOrder.user) &&
                Objects.equals(car, carOrder.car) &&
                Objects.equals(description, carOrder.description) &&
                Objects.equals(startsAt, carOrder.startsAt) &&
                Objects.equals(expiresAt, carOrder.expiresAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, car, description, price, startsAt, expiresAt, isActive, isClosed);
    }
}
