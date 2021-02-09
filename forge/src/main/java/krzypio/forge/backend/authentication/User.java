package krzypio.forge.backend.authentication;

import krzypio.forge.backend.entity.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    private String userName;
    private String password;
    private String roles;
    private boolean active;

    public User() {
    }

    public User(String userName, String password, String roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.active = true;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}