//package org.example.logistics.controller;
//
//import org.example.logistics.security.JwtUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtUtils jwtUtil;
//
//    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public String createToken(@RequestParam String username, @RequestParam String password) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//        } catch (Exception e) {
//            throw new Exception("Invalid username/password");
//        }
//        return jwtUtil.generateJwtToken(username);
//    }
//}
//
//class AuthRequest {
//    private String username;
//    private String password;
//
//    // Getters and Setters
//}
