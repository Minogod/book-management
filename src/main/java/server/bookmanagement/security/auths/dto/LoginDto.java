package server.bookmanagement.security.auths.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private String email;
    private String password;
}
