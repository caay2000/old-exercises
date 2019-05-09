package com.merkleinc.interviewkata.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.merkleinc.interviewkata.rest.v1.CustomerV1Controller;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerV1Controller.class)
@ComponentScan(basePackages = {"com.merkleinc.interviewkata"})
public class CustomerV1ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void customer_1() throws Exception {
        String birthDay = "12 March 1998";
        mvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ellsworth"))
                .andExpect(jsonPath("$.middleName").doesNotExist())
                .andExpect(jsonPath("$.lastName").value("Cicerone"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.birthDay").value(birthDay))
                .andExpect(jsonPath("$.age").value(calculateAge(birthDay)))
                .andExpect(jsonPath("$.address").value("67380 Fallview Way 8th V14-Rathdrum (Ireland)"))
                .andExpect(jsonPath("$.contactNumber").value("+353 509 454 8496"))
                .andExpect(jsonPath("$.contactEmail").value("ecicerone0@people.com.cn"));
    }

    @Test
    public void customer_2() throws Exception {
        String birthDay = "1 December 1984";
        mvc.perform(get("/customer/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ora"))
                .andExpect(jsonPath("$.middleName").doesNotExist())
                .andExpect(jsonPath("$.lastName").value("Tickner"))
                .andExpect(jsonPath("$.gender").value("F"))
                .andExpect(jsonPath("$.birthDay").value(birthDay))
                .andExpect(jsonPath("$.age").value(calculateAge(birthDay)))
                .andExpect(jsonPath("$.address").value("7 Fordem Park La Follette D6W-Ballinteer (Ireland)"))
                .andExpect(jsonPath("$.contactNumber").value("+353 645 308 3605"))
                .andExpect(jsonPath("$.contactEmail").value("otickner1@sina.com.cn"));
    }

    @Test
    public void customer_3() throws Exception {
        String birthDay = "17 April 1964";
        mvc.perform(get("/customer/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Mason"))
                .andExpect(jsonPath("$.middleName").value("Ray"))
                .andExpect(jsonPath("$.lastName").value("Champneys"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.birthDay").value(birthDay))
                .andExpect(jsonPath("$.age").value(calculateAge(birthDay)))
                .andExpect(jsonPath("$.address").value("9 Golf View Park Haas BT2-Belfast (United Kingdom)"))
                .andExpect(jsonPath("$.contactNumber").value("+44 490 270 9258"))
                .andExpect(jsonPath("$.contactEmail").value("mchampneys2@plala.or.jp"));
    }

    @Test
    public void customer_4() throws Exception {
        String birthDay = "28 October 1964";
        mvc.perform(get("/customer/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Lennie"))
                .andExpect(jsonPath("$.middleName").doesNotExist())
                .andExpect(jsonPath("$.lastName").value("O'Concannon"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.birthDay").value(birthDay))
                .andExpect(jsonPath("$.age").value(calculateAge(birthDay)))
                .andExpect(jsonPath("$.address").value("992 Carey Street Old Shore D04-Booterstown (Ireland)"))
                .andExpect(jsonPath("$.contactNumber").value("+353 890 400 7048"))
                .andExpect(jsonPath("$.contactEmail").value("lo3@army.mil"));
    }

    @Test
    public void customer_5() throws Exception {
        String birthDay = "27 September 1960";
        mvc.perform(get("/customer/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Andrey"))
                .andExpect(jsonPath("$.middleName").doesNotExist())
                .andExpect(jsonPath("$.lastName").value("Crickmoor"))
                .andExpect(jsonPath("$.gender").value("X"))
                .andExpect(jsonPath("$.birthDay").value(birthDay))
                .andExpect(jsonPath("$.age").value(calculateAge(birthDay)))
                .andExpect(jsonPath("$.address").value("52 Monument Park Drewry GU32-Weston (United Kingdom)"))
                .andExpect(jsonPath("$.contactNumber").value("+44 398 972 4493"))
                .andExpect(jsonPath("$.contactEmail").value("acrickmoor4@amazonaws.com"));
    }

    private String calculateAge(String date) {
        return Integer.toString(Period.between(LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMMM uuuu")), LocalDate.now()).getYears());
    }
}

