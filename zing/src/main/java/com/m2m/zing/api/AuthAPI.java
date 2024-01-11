//package com.m2m.zing.api;
//
//import com.m2m.zing.dto.LoginRequest;
//import com.m2m.zing.dto.LoginResponse;
//import com.m2m.zing.service.AuthenticationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthAPI {
//
//    private final AuthenticationService authenticationService;
//    @PostMapping("/sign-in")
//    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest auLoginRequest){
//        return ResponseEntity.ok(authenticationService.authentication(auLoginRequest));
//    }
//}
