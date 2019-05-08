package com.merkleinc.interviewkata.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.merkleinc.interviewkata.rest.v1.CustomerV1Controller;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerV1Controller.class)
public class CustomerV1ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCustomer() throws Exception {
        mvc.perform(get("/com/merkleinc/interviewkata/api/internal/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ellsworth"))
                .andExpect(jsonPath("$.middleName").doesNotExist())
                .andExpect(jsonPath("$.lastName").value("Cicerone"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.birthday").value("3rd December 1998"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.address").value("67380 Fallview Way 8th V14-Rathdrum (Ireland)"))
                .andExpect(jsonPath("$.contactNumber").value("+353 509 454 8496"))
                .andExpect(jsonPath("$.contactEmail").value("ecicerone0@people.com.cn"));
    }
}

