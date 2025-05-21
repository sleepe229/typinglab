package org.example.typinglab.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "confirmation_table")
public class SecretCode {

    @Id
    @Column(name = "email")
    private String id;

    @Column(name = "secret_code")
    private String secretCode;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    public SecretCode() {}

    public SecretCode(String id, String secretCode, Timestamp creationDate) {
        this.id = id;
        this.secretCode = secretCode;
        this.creationDate = creationDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSecretCode() { return secretCode; }
    public void setSecretCode(String secretCode) { this.secretCode = secretCode; }
    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
}
