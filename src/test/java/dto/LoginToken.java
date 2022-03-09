package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class LoginToken {
    private String email;
    private String password;
    private String domain;
}
