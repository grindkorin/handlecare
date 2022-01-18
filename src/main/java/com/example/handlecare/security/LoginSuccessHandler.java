package com.example.handlecare.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String redirectURL = request.getContextPath();
        System.out.println("\n\n SUCCESS \n\n");
        if (userDetails.hasRole("DELIVER")) {
            redirectURL = "/deliver/requests";
        } else if (userDetails.hasRole("RECIPIENT")) {
            redirectURL = "/personal/active";
        } else if (userDetails.hasRole("ADMIN")) {
            redirectURL = "/administration/allUsers";
        }

        response.sendRedirect(redirectURL);

    }

}