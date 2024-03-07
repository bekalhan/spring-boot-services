package com.authservice.authservice.entity;


import com.authservice.authservice.enumPackage.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Boolean mfaEnabled;
    private String secret;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    //kullanıcıların yetkilerini döndğrmek için bu metot kullanılır.Kullanıcının hangi rolleri olduğunu belirler
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    //kullanıcının kimliğinin(username veya email) döndğrmek için kullanılır.metot kullanıcın uygulama içinde
    //benzersiz kimliğini sağlar
    @Override
    public String getUsername() {
        return username;
    }

    //kullanıcının hesabının süresinin dolup dolmadığını söyler
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //kullanıcın hesabının kilitli olup olmadığını söyler
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //kullanıcın kimlik bilgilerinin(şifresinin) dolup dolmadığını söyler
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //kullanıcının etkin olup olmadığını söyler
    @Override
    public boolean isEnabled() {
        return true;
    }
}