package com.schibsted.spain.friends.service.legacy;

import com.schibsted.spain.friends.application.common.ApplicationException;
import com.schibsted.spain.friends.model.api.SignUpApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SignupLegacyControllerTest {

    @Mock
    private SignUpApi signUpApi;

    @Test
    public void noPasswordReturns400() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new SignupLegacyController(signUpApi)).build();

        testee.perform(post("/signup?username=username"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void illegalArgumentExceptionReturns400() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("error"))
                .when(signUpApi).signUp(any(String.class), any(String.class));
        MockMvc testee = MockMvcBuilders.standaloneSetup(new SignupLegacyController(signUpApi)).build();

        testee.perform(post("/signup?username=username")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void applicationExceptionReturns400() throws Exception {
        Mockito.doThrow(new ApplicationException("error"))
                .when(signUpApi).signUp(any(String.class), any(String.class));
        MockMvc testee = MockMvcBuilders.standaloneSetup(new SignupLegacyController(signUpApi)).build();

        testee.perform(post("/signup?username=username")
                .header("X-Password", "password"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void noExceptionReturns200() throws Exception {
        MockMvc testee = MockMvcBuilders.standaloneSetup(new SignupLegacyController(signUpApi)).build();

        testee.perform(post("/signup?username=username")
                .header("X-Password", "password"))
                .andExpect(status().isOk());

        verify(signUpApi).signUp(eq("username"), eq("password"));
    }
}