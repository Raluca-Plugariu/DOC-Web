package com.project.raluca.dto;

import java.util.Objects;

public class UserDTO {
    private int id;

    private String username;

    private String password;

    private Boolean enabled;

    public UserDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public UserDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return getId() == userDTO.getId() &&
                Objects.equals(getUsername(), userDTO.getUsername()) &&
                Objects.equals(getPassword(), userDTO.getPassword()) &&
                Objects.equals(getEnabled(), userDTO.getEnabled());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getUsername(), getPassword(), getEnabled());
    }
}
