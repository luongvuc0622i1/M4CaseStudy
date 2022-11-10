package com.controller.login;

import com.model.DTO.UserToken;
import com.model.jwt.AppRole;
import com.model.jwt.AppUser;
import com.repository.jwt.IAppRoleRepository;
import com.service.jwt.appRole.IAppRoleService;
import com.service.jwt.appUser.IAppUserService;
import com.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

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
    private IAppRoleService appRoleService;

    @PostMapping("/login")
    public ResponseEntity<UserToken> login(@RequestBody AppUser appUser) {
        try {
            //Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getName(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.createToken(authentication);
            AppUser appUser1 = userService.findUserByName(appUser.getName());
            UserToken userToken = new UserToken(appUser1.getId(), appUser.getName(), token, appUser1.getRoleSet());
            return new ResponseEntity<>(userToken, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Login Fail!!!!!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {
        if (userService.findUserByName(appUser.getName()) == null) {
            Set<AppRole> roles = new  HashSet<>();
            roles.add(appRoleService.findById(3L).get());
            appUser.setRoleSet(roles);
            return new ResponseEntity<>(userService.save(appUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
