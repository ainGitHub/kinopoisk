package ru.dz.entity;

import javax.persistence.*;

/**
 * Created by Adel on 22.09.2016.
 */
@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {"role", "userInfo"}))
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            unique = true, nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInfo", referencedColumnName = "id", nullable = false)
    private UserInfo userInfo;

    @Column(name = "role", nullable = false, length = 45)
    private String role;


    public UserRoles() {

    }

    public UserRoles(String role) {
        this.role = role;
    }

    public UserRoles(String role, UserInfo userInfo) {
        this.userInfo = userInfo;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
