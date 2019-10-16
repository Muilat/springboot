package com.example.demo.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AccessToken {

    public static String getAccessToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            OAuth2AuthenticationDetails auth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            return auth2AuthenticationDetails.getTokenType().concat(" ").concat(auth2AuthenticationDetails.getTokenValue());
//        }
    }
}
