package com.schibsted.spain.friends.legacy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SignupLegacyController.class)
@ComponentScan(basePackages = {"com.schibsted.spain.getAllFriends"})
public class SignupLegacyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void noPasswordFails() throws Exception {
        mvc.perform(post("/signup?username=john"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test() throws Exception {
        mvc.perform(post("/signup?username=john_doe")
                .header("X-Password", "j12345678"))
                .andExpect(status().isBadRequest());
    }

    /*
    given_request "POST $service/signup?username=john"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/signup?username=john_doe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12-45678 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j1234 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j1234567890123 $service/signup?username=johndoe"
response_status_code_should_be 4..
given_request "POST -H X-Password:j12345678 $service/signup?username=johnisaniceguy"
response_status_code_should_be 4..
     */

}