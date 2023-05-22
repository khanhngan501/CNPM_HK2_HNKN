package com.group08.onlineShop.controller;

import com.group08.onlineShop.dto.requestDTO.PasswordDto;
import com.group08.onlineShop.exception.UserNotFoundException;
import com.group08.onlineShop.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @Slf4j
@RequestMapping("api/account")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class AccountController {

    private final AuthenticationService authenticationService;

    @PostMapping("/password/reset")
    public ResponseEntity<?> resetPassword(@RequestParam(name = "username") String username,
                                           HttpServletRequest request) throws UserNotFoundException {

        log.info("Reset password");

        authenticationService.forgotPasswordForAccount(username, request);
        // UI must notify user checking email, after forward to HomePage
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/password/reset")
    public ResponseEntity<?> verifyTokenResetPassword(
            @RequestParam(name = "token") String code) {

        String result = authenticationService.validatePasswordResetToken(code);

        // updating new password
        if (result != null) {
            // forward to UpdatePassword Page
        }

        return ResponseEntity.ok("Checking token " + result);
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> updatingNewPassword(@RequestBody PasswordDto passwordDto) {

        log.info("Password DTO: {}", passwordDto.getNewPassword());
        authenticationService.changeAccountPassword(passwordDto);

        return ResponseEntity.ok("OK");
    }

}
