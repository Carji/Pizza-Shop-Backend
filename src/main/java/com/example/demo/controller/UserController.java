package com.example.demo.controller;

import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.example.demo.application.userApplication.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserApplication userApplication;

    @Autowired
    public UserController(final UserApplication userApplication) {
        this.userApplication = userApplication;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDTO dto) {
        UserDTO userDTO = this.userApplication.add(dto);

        // return ResponseEntity.status(201).body(userDTO);
        return ResponseEntity.status(201).body(getJWTToken(userDTO));
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable UUID id) {
        UserDTO userDTO = this.userApplication.get(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable UUID id, @RequestBody UpdateUserDTO dto) {
        UserDTO newUserDTO = this.userApplication.update(id, dto);
        return ResponseEntity.ok(newUserDTO);
    }

    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable UUID id) {
        this.userApplication.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(@RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(200).body(this.userApplication.getAll(email, page, size));
    }

    private String getJWTToken(UserDTO user) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().toString());

        String token = Jwts.builder().setId(user.getId().toString()).setSubject(user.getName())
                .claim("authorities",
                        grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
                
        return token;
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/login")
    public ResponseEntity<?> create(@Valid @RequestBody loginDTO dto) {
        UserDTO userDTO = this.userApplication.login(dto);
        return ResponseEntity.status(201).body(getJWTToken(userDTO));
    }
}
