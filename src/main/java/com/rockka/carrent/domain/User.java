package com.rockka.carrent.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "is_deleted", nullable = false, length = 1)
    private char isDeleted;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modifiedAt;
    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public User setClient(Client client) {
        this.client = client;
        return this;
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

    public char getIsDeleted() {
        return isDeleted;
    }

    public User setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }
    public User setDeleted() {
        this.isDeleted = 'y';
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
}
