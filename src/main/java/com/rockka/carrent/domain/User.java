package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "user_role", nullable = false, length = 10)
    private String userRole;
    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;
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
    @Column(name = "image_name")
    private String image_name;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "is_deleted", nullable = false, length = 1)
    private int isDeleted;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;
    @JsonBackReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarOrder> carOrders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public User setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public User setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public User setDeleted() {
        this.isDeleted = 1;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public User setModifiedAt(Date modifiedAt) {
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

    public String getImage_name() {
        return image_name;
    }

    public User setImage_name(String image_name) {
        this.image_name = image_name;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public List<CarOrder> getOrders() {
        return carOrders;
    }

    public void setOrders(List<CarOrder> carOrders) {
        this.carOrders = carOrders;
    }
}
