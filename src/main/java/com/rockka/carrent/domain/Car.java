package com.rockka.carrent.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rockka.carrent.enums.CarStatus;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//TODO: change java.util.Date to JodaTime
@Entity
@Table(name = "car")
public class Car implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "car_id")
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
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate releaseDate;
	@Column(name = "created_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdAt = new LocalDateTime();
	@Column(name = "modified_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime modifiedAt = new LocalDateTime();
	@Column(name = "status", nullable = false)
	private int status = 1;
	@Transient
	private CarStatus carStatus;
	@JsonIgnore
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice> carOrders;

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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public Car setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Car setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public Car setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}

	public CarStatus getCarStatus() {
		if (carStatus == null) {
			carStatus = CarStatus.get(status);
		}

		return carStatus;
	}

	public Car setCarStatus(int i) {
		carStatus = CarStatus.get(i);
		status = carStatus.toInt();
		return this;
	}

	public List<Invoice> getCarOrders() {
		return carOrders;
	}

	public Car setCarOrders(List<Invoice> carOrders) {
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
				status == car.status &&
				Objects.equals(name, car.name) &&
				Objects.equals(country, car.country) &&
				Objects.equals(color, car.color) &&
				Objects.equals(description, car.description) &&
				Objects.equals(releaseDate, car.releaseDate);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, country, color, description, price, releaseDate, status);
	}
}
