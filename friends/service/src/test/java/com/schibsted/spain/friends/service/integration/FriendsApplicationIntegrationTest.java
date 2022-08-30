package com.schibsted.spain.friends.service.integration;

import com.schibsted.spain.friends.service.legacy.FriendshipLegacyController;
import com.schibsted.spain.friends.service.legacy.SignupLegacyController;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({SignupLegacyController.class, FriendshipLegacyController.class})
@ComponentScan(basePackages = {"com.schibsted.spain.friends"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FriendsApplicationIntegrationTest {

    private final String USERNAME_1 = "username1";
    private final String USERNAME_2 = "username2";
    private final String USERNAME_3 = "username3";
    private final String VALID_PASSWORD = "abcd1234";

    private final String INVALID_USERNAME = "no_valid";
    private final String INVALID_PASSWORD = "no_valid";

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_01_registerWorks() throws Exception {
        mvc.perform(post("/signup?username=" + USERNAME_1)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());

        mvc.perform(post("/signup?username=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());

        mvc.perform(post("/signup?username=" + USERNAME_3)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());
    }

    @Test
    public void test_02_registerFails() throws Exception {
        //invalid username
        mvc.perform(post("/signup?username=" + INVALID_USERNAME)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());

        //invalid password
        mvc.perform(post("/signup?username=" + USERNAME_1)
                .header("X-Password", INVALID_PASSWORD))
                .andExpect(status().isBadRequest());

        //no password
        mvc.perform(post("/signup?username=" + USERNAME_1))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_03_requestsWorks() throws Exception {
        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_1 + "&usernameTo=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());

        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_1 + "&usernameTo=" + USERNAME_3)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());

        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_2 + "&usernameTo=" + USERNAME_3)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());
    }

    @Test
    public void test_04_requestsFails() throws Exception {
        //invalid url
        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_1)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());

        //request to self
        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_2 + "&usernameTo=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());

        //repeat request
        mvc.perform(post("/friendship/request?usernameFrom=" + USERNAME_1 + "&usernameTo=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_05_acceptWorks() throws Exception {
        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_2 + "&usernameTo=" + USERNAME_1)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());

        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_3 + "&usernameTo=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());
    }

    @Test
    public void test_06_acceptFails() throws Exception {
        // already accepted
        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_3 + "&usernameTo=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());

        //not registered user
        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_3 + "&usernameTo=" + INVALID_USERNAME)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_07_declineWorks() throws Exception {
        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_3 + "&usernameTo=" + USERNAME_1)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk());
    }

    @Test
    public void test_08_declineFails() throws Exception {
        //already declined
        mvc.perform(post("/friendship/accept?usernameFrom=" + USERNAME_3 + "&usernameTo=" + USERNAME_1)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_09_friendList() throws Exception {
        mvc.perform(get("/friendship/list?username=" + USERNAME_2)
                .header("X-Password", VALID_PASSWORD))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[\"username1\",\"username3\"]"));
    }


}