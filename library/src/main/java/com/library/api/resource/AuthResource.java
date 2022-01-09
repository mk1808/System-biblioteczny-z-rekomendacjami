package com.library.api.resource;

import java.security.Principal;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AppUserDto;
import com.library.dto.BookDto;
import com.library.dto.LoginDto;
import com.library.model.AppUser;
import com.library.response.Response;

@RestController
@RequestMapping("/api/auth")
public interface AuthResource {
	
	@PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginDto loginUser, HttpServletResponse response) throws AuthenticationException;
	
	@PostMapping("/register")
    public AppUser saveUser(@RequestBody AppUserDto user);
	
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userping")
    public String userPing();
    
    @GetMapping("/whoami")
    public Principal whoAmI(Principal principal);
    
    @PostMapping("/logout")
    public ResponseEntity<Object> whoAmI(HttpServletResponse response);
}
