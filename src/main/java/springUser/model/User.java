package springUser.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "userRole_id") })
    private Set<UserRole> userRoles = new HashSet<>();

    public User() {

    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, Set<UserRole> userRoles) {
        this.name = name;
        this.password = password;
        this.userRoles = userRoles;
    }

    public User(String name, String password, UserRole role) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


}
