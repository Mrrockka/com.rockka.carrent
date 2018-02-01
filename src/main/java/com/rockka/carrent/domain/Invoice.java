package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rockka.carrent.converters.InvoiceStatusConverter;
import com.rockka.carrent.enums.InvoiceStatus;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {
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
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdAt = new LocalDateTime();
	@Column(name = "modified_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime modifiedAt = new LocalDateTime();
	@Column(name = "starts_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime startsAt;
	@Column(name = "expires_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime expiresAt;
	@Column(name = "status", nullable = false)
	@Convert(converter = InvoiceStatusConverter.class)
	private InvoiceStatus status;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Invoice setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public Invoice setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}

	public LocalDateTime getStartsAt() {
		return startsAt;
	}

	public Invoice setStartsAt(LocalDateTime startsAt) {
		this.startsAt = startsAt;
		return this;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public Invoice setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}

	public Invoice setStatus(int i) {
		status = InvoiceStatus.get(i);
		return this;
	}

	public InvoiceStatus getStatus() {
		return status;
	}

	public Invoice setStatus(InvoiceStatus status) {
		this.status = status;
		return this;
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
