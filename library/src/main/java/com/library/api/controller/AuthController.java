package com.library.api.controller;

import java.security.Principal;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.resource.AuthResource;
import com.library.config.TokenProvider;
import com.library.dto.AppUserDto;
import com.library.dto.LoginDto;
import com.library.dto.TokenDto;
import com.library.model.AppUser;
import com.library.response.Response;
import com.library.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthController implements AuthResource {
	
	public AuthController(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil,
			UserService userService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userService = userService;
	}

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginDto loginUser, HttpServletResponse response) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getMail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setMaxAge(7 * 24*60*60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok(new TokenDto(token));
    }

 /*   @RequestMapping(value="/register", method = RequestMethod.POST)
    public AppUser saveUser(@RequestBody AppUser user){
        return new AppUser();//userService.save(user);
    }*/

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public AppUser saveUser(@RequestBody AppUserDto user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "Any User Can Read This";
    }
    
    @RequestMapping(value="/whoami", method = RequestMethod.GET)
    public Principal whoAmI(Principal principal){
        return principal;
    }
    
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseEntity<Object> whoAmI(HttpServletResponse response){
    	 Cookie cookie = new Cookie("Authorization", "");
         cookie.setMaxAge(0);
         cookie.setHttpOnly(true);
         cookie.setPath("/");
         response.addCookie(cookie);
        return new ResponseEntity<Object> ("{\"message\":\"Logged out\"}", HttpStatus.OK);
    }
    
    
}
