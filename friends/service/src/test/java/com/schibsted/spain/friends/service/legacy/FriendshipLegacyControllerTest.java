package com.schibsted.spain.friends.service.legacy;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.api.FriendshipApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FriendshipLegacyControllerTest {

    @Mock
    private FriendshipApi friendshipApi;

    @Test
    public void anyNoPasswordReturn400() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(get("/friendship/list?username=username"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void anyNoUsernameReturns400() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(get("/friendship/list")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void anyNoUsernameFromReturns400() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/decline?usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void anyNoUsernameToReturns400() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/decline?usernameFrom=username")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void anyIllegalArgumentExceptionReturns400() throws Exception {
        doThrow(new IllegalArgumentException("error"))
                .when(friendshipApi).accept(any(String.class), any(String.class));
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/accept?usernameFrom=username&usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void anyApplicationExceptionReturns400() throws Exception {
        doThrow(new ApplicationException("error"))
                .when(friendshipApi).accept(any(String.class), any(String.class));
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/accept?usernameFrom=username&usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validRequestReturns200() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/request?usernameFrom=username&usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isOk());

        verify(friendshipApi).request(eq("username"), eq("usernameTo"));
    }

    @Test
    public void validAcceptReturns200() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/accept?usernameFrom=username&usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isOk());

        verify(friendshipApi).accept(eq("username"), eq("usernameTo"));
    }

    @Test
    public void validDeclineReturns200() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(post("/friendship/decline?usernameFrom=username&usernameTo=usernameTo")
                .header("X-Password", "password"))
                .andExpect(status().isOk());

        verify(friendshipApi).decline(eq("username"), eq("usernameTo"));
    }

    @Test
    public void validListReturns200() throws Exception {
        when(friendshipApi.friends(eq("username"))).thenReturn(new HashSet<>(Arrays.asList("Friend1", "Friend2")));
        MockMvc testee = MockMvcBuilders.standaloneSetup(new FriendshipLegacyController(friendshipApi)).build();

        testee.perform(get("/friendship/list?username=username")
                .header("X-Password", "password"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("[\"Friend1\",\"Friend2\"]"));

        verify(friendshipApi).friends(eq("username"));
    }
}