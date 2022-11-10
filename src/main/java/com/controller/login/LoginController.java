package com.controller.login;

import com.repository.jwt.IAppRoleRepository;
import com.service.jwt.appUser.IAppUserService;
import com.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAppUserService userService;

    @Autowired
    private IAppRoleRepository


}
