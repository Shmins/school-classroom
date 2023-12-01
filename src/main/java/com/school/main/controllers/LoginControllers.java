package com.school.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.utils.Login;
import com.school.main.services.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("login")
public class LoginControllers {
    private LoginService loginService;
    

    public LoginControllers(LoginService loginService){
        this.loginService = loginService;
    }
    @Operation(summary = "login using cpf and password and return JWT (token)", tags = "Login")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Login in system", 
            content = { @Content(mediaType = "application/json",
            schema = @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIyMjQuNjU1LjI3MC0yOSIsIm5hbWUiOiJQZWRybyBBbHlzb24iLCJpc3MiOiJST0xFX0NPT1JESU5BVE9SIiwiZXhwIjoxNzAxMjgzMzM5fQ.difONVTWgPpf0lG3bWxrsmo89ZCcC4KHT96UNkVrUVI"))}),
        @ApiResponse(responseCode = "401", description = "User or Password incorrect", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)})
    @PostMapping("/")
    public ResponseEntity<String> login(@RequestBody Login login){
        return ResponseEntity.ok(this.loginService.login(login));
    }
}
