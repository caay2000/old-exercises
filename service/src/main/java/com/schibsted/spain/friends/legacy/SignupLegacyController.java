package com.schibsted.spain.friends.legacy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.schibsted.spain.friends.model.api.SignUpApi;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController {

    private final SignUpApi signUpApi;

    public SignupLegacyController(SignUpApi signUpApi) {
        this.signUpApi = signUpApi;
    }

    @PostMapping
    void signUp(@RequestParam("username") String username, @RequestHeader("X-Password") String password) {
        signUpApi.signUp(username, password);
    }
}
