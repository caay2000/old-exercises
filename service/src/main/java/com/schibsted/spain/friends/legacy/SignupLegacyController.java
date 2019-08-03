package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.api.SignUpApi;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/signup")
public class SignupLegacyController extends AbsctractLegacyController {

    private final SignUpApi signUpApi;

    @Inject
    public SignupLegacyController(SignUpApi signUpApi) {
        this.signUpApi = signUpApi;
    }

    @PostMapping
    void signUp(@RequestParam("username") String username, @RequestHeader("X-Password") String password) {
        signUpApi.signUp(username, password);
    }

}
