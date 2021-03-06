package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rockka.carrent.converters.UserStatusConverter;
import com.rockka.carrent.enums.UserStatus;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
/*
 ** Invoice entity
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	@Column(name = "username", nullable = false, length = 30)
	private String username;
	@Column(name = "roles", nullable = false, length = 10)
	private String roles = "ROLE_USER";
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;
	@Column(name = "second_name", nullable = false, length = 30)
	private String secondName;
	@Column(name = "last_name", length = 30)
	private String lastName;
	@Column(nullable = false)
	private String address;
	@Column(name = "about_me")
	private String aboutMe;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate birthday;
	@Column(name = "status", nullable = false)
	@Convert(converter = UserStatusConverter.class)
	private UserStatus status;
	@Column(name = "created_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdAt = new LocalDateTime();
	@Column(name = "modified_at", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime modifiedAt = new LocalDateTime();
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Invoice> carOrders;

	public String getRoles() {
		return roles;
	}

	public User setRoles(String roles) {
		this.roles = roles;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public UserStatus getStatus() {
		return status;
	}

	public User setStatus(int i) {
		status = UserStatus.get(i);
		return this;
	}
	public User setStatus(UserStatus status) {
		this.status = status;
		return this;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public User setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getSecondName() {
		return secondName;
	}

	public User setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public User setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public User setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
		return this;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public User setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public List<Invoice> getOrders() {
		return carOrders;
	}

	public void setOrders(List<Invoice> carOrders) {
		this.carOrders = carOrders;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return status == user.status &&
				Objects.equals(roles, user.roles) &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password) &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(secondName, user.secondName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(address, user.address) &&
				Objects.equals(birthday, user.birthday);
	}

	@Override
	public int hashCode() {

		return Objects.hash(roles, username, password, firstName, secondName, lastName, address, birthday, status);
	}

}
