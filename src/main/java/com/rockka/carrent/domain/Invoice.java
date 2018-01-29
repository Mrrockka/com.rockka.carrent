package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rockka.carrent.enums.InvoiceStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//TODO: change java.util.Date to JodaTime
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
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
    private int status = 2;
    @Transient
    private InvoiceStatus invoiceStatus;
/*    @OneToMany()
    private List<Invoice> invoices;*/


    public long getId() {
        return id;
    }

    public Invoice setId(long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Invoice setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Invoice setCar(Car car) {
        this.car = car;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Invoice setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Invoice setPrice(double price) {
        this.price = price;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Invoice setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Invoice setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public Invoice setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public Invoice setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public Invoice setInvoiceStatus(int i){
        invoiceStatus = InvoiceStatus.get(i);
        status = invoiceStatus.toInt();
        return this;
    }

    public Invoice setInvoiceStatus(InvoiceStatus invoiceStatus){
        this.invoiceStatus = invoiceStatus;
        status = invoiceStatus.toInt();
        return this;
    }

    public InvoiceStatus getInvoiceStatus(){
        if(invoiceStatus == null){
            invoiceStatus = InvoiceStatus.get(status);
        }
        return invoiceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                Double.compare(invoice.price, price) == 0 &&
                status == invoice.status &&
                Objects.equals(user, invoice.user) &&
                Objects.equals(car, invoice.car) &&
                Objects.equals(description, invoice.description) &&
                Objects.equals(startsAt, invoice.startsAt) &&
                Objects.equals(expiresAt, invoice.expiresAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, car, description, price, startsAt, expiresAt, status);
    }
}
