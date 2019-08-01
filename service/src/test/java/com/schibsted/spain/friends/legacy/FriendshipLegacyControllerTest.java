package com.schibsted.spain.friends.legacy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SignupLegacyController.class)
@ComponentScan(basePackages = {"com.schibsted.spain.getAllFriends"})
public class FriendshipLegacyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void noPasswordFails() throws Exception {
        mvc.perform(post("friendship/request?usernameFrom=johndoe&usernameTo=samantha")
                .header("X-Password", "j12345678"))
                .andExpect(status().isBadRequest());

    }

}