package com.rockka.carrent.domain;

import javax.persistence.*;

@Entity
@Table(name = "site_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Client client;

}
