package com.codingdood.webapisecurity.controller;

import com.codingdood.webapisecurity.DTO.UserLoginDTO;
import com.codingdood.webapisecurity.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class BookController {

    @Value("${spring.security.user.name}")
    private String userName;

    @Value("${spring.security.user.password}")
    private String userPassword;

    private String secretKey = "krishna192168";

    @PostMapping("login")
    public ResponseEntity<?> loginAsUser(@RequestBody UserLoginDTO userLoginDTO) {
        String token = null;
        String name = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        if(Objects.equals(name, userName) && Objects.equals(password, userPassword)) {
            Map<String, String> userDetails = new HashMap<>();
            userDetails.put("name", userName);
            userDetails.put("role", "admin");
            token = JWTUtils.createJWTToken(userDetails);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("secure/books")
    public ResponseEntity<?> getBookDetails() {
            List<String> books = List.of("java","angular");
            return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
