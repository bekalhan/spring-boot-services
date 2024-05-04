package com.bas.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String phNo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user_id")
    private List<Address> addressList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id")
    private List<Card>cardList;


}
