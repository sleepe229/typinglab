package org.example.typinglab.dto;

import jakarta.validation.constraints.NotBlank;

public class UserIdRequest {
    private int userId;
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
