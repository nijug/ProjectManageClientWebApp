package com.example.projectmanageclientmvc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class TokenHolder {

    @Getter
    @Setter
    private static OAuth2AccessToken token;

    public TokenHolder() {
    }



}
