package ru.kata.spring.boot_security.demo.entities;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Component
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @Column(nullable = false)
    private String username;

    @Column
    private String password;

    @Column
    @NotNull
    private Integer enabled;

    @Column
    private String name;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    @Transient
    private String role;

    @OneToOne
    @Transient
    @JoinColumn(name = "roles")
    private Role roles;

    public Users() {
    }

    public Role getRoles() {
        return roles;
    }

    @Autowired
    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Autowired
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return username != null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return username != null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return password != null;
    }

    public boolean isEnabled() {
        return username != null;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
