package org.example.typinglab.dto;

import java.time.LocalDate;

public class UserDTO {
    private String id;
    private String login;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate birthdate;
    private String country;

    public UserDTO(String id, String login, boolean isAdmin, String firstName, String lastName, String username, String email, LocalDate birthdate, String country) {
        this.id = id;
        this.login = login;
        this.isAdmin = isAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.country = country;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { this.isAdmin = admin; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}