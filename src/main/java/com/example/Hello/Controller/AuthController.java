package com.example.Hello.Controller;

import com.example.Hello.Service.UserService;
import com.example.Hello.config.CustomUserDetailsService;
import com.example.Hello.config.JwtTokenUtil;
import com.example.Hello.dto.UserDTO;
import com.example.Hello.dto.request.AuthRequest;
import com.example.Hello.dto.respon.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthRequest authRequest) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(authRequest.getUsername());
        userDTO.setEmail(authRequest.getEmail());
        userDTO.setRole(authRequest.getRole());
        return ResponseEntity.ok(userService.createUser(userDTO, authRequest.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // JWT không cần logout server-side, chỉ cần client xóa token
        return ResponseEntity.ok("Logged out successfully");
    }
}