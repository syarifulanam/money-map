package com.syarifulanam.moneymap.controller;

import com.syarifulanam.moneymap.dto.AuthModel;
import com.syarifulanam.moneymap.dto.ErrorObject;
import com.syarifulanam.moneymap.dto.JwtResponse;
import com.syarifulanam.moneymap.dto.UserModel;
import com.syarifulanam.moneymap.security.CustomUserDetailsService;
import com.syarifulanam.moneymap.service.UserService;
import com.syarifulanam.moneymap.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody UserModel userModel) {
        if (userModel.getAge() < 10) {
            // note : cara 1
            //return new ResponseEntity<>("Umur minimal harus 10 tahun", HttpStatus.BAD_REQUEST);

            // note : cara 2
            ErrorObject errorObject = new ErrorObject();
            errorObject.setMessage("Umur minimal harus 10 tahun");
            errorObject.setStatusCode(400);
            errorObject.setTimestamp(new Date());

            return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.createUser(userModel), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {
        authenticate(authModel.getEmail(), authModel.getPassword());
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());

        // NOTE : Generate the JWT Token
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }
}
