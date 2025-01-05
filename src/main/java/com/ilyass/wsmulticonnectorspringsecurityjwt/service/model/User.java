package com.ilyass.wsmulticonnectorspringsecurityjwt.service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String username;
    protected String firstname;
    protected String lastname;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<BankAccountTransaction> bankAccountTransactionList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE")

    private List<Role> authorities = new ArrayList<Role>();
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private String email;

    public User(String username) {
        this.username = username;
    }

}
