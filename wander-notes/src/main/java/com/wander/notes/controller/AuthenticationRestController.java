 	package com.wander.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wander.notes.model.RegistrationApiRequest;
import com.wander.notes.security.JwtAuthenticationRequest;
import com.wander.notes.security.JwtAuthenticationResponse;
import com.wander.notes.security.JwtTokenUtil;
import com.wander.notes.service.definition.RegisterService;

@CrossOrigin
@RestController
public class AuthenticationRestController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
    	
    	System.out.println("ANMOL 1 :: " + authenticationRequest.getUsername() + "::" + authenticationRequest.getPassword());
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("ANMOL 2");
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("Token:" + token);
        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /*@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }*/
    
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegistrationApiRequest registrationApiRequest) {
    	try {
    		if(registrationApiRequest == null || registrationApiRequest.getEmail() == null || registrationApiRequest.getEmail().trim().equals("")
    				|| registrationApiRequest.getPassword() == null || registrationApiRequest.getPassword().trim().equals("")
    				|| registrationApiRequest.getName() == null || registrationApiRequest.getName().trim().equals("")) {
    			System.out.println("registrationApiRequest" + registrationApiRequest);
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please form request correctly");
    		}
    		int status = registerService.registerUser(registrationApiRequest);
    		return ResponseEntity.status(HttpStatus.OK).body(status);
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }
}
