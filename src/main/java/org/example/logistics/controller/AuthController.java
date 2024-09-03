//package org.example.logistics.controller;
//
//;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.logistics.security.JwtUtils;
//import org.example.logistics.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
////    @Autowired
////    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@RequestParam String username, @RequestParam String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            String token = jwtUtils.generateJwtToken(username);
//
//            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Authentication successful");
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Authentication failed");
//        }
//    }
//}
