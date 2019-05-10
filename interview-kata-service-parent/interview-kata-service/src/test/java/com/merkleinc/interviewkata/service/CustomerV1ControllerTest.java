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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;
import com.merkleinc.interviewkata.rest.v1.CustomerV1Controller;
import lombok.Builder;
import lombok.Getter;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerV1Controller.class)
@ComponentScan(basePackages = {"com.merkleinc.interviewkata"})
public class CustomerV1ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void customer_invalid() throws Exception {
        mvc.perform(get("/customer/invalid"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("customer invalid not found"));
    }

    @Test
    public void customer_1() throws Exception {

        ResultActions resultActions = mvc.perform(get("/customer/1"))
                .andExpect(status().isOk());
        assertResponse(resultActions, AssertCustomer.builder()
                .firstName("Ellsworth")
                .lastName("Cicerone")
                .gender("M")
                .birthday("12 March 1998")
                .address("67380 Fallview Way 8th V14-Rathdrum (Ireland)")
                .contactNumber("+353 509 454 8496")
                .contactEmail("ecicerone0@people.com.cn")
                .build());
    }

    @Test
    public void customer_2() throws Exception {

        ResultActions resultActions = mvc.perform(get("/customer/2"))
                .andExpect(status().isOk());
        assertResponse(resultActions, AssertCustomer.builder()
                .firstName("Ora")
                .lastName("Tickner")
                .gender("F")
                .birthday("1 December 1984")
                .address("7 Fordem Park La Follette D6W-Ballinteer (Ireland)")
                .contactNumber("+353 645 308 3605")
                .contactEmail("otickner1@sina.com.cn")
                .build());
    }

    @Test
    public void customer_3() throws Exception {

        ResultActions resultActions = mvc.perform(get("/customer/3"))
                .andExpect(status().isOk());
        assertResponse(resultActions, AssertCustomer.builder()
                .firstName("Mason")
                .middleName("Ray")
                .lastName("Champneys")
                .gender("M")
                .birthday("17 April 1964")
                .address("9 Golf View Park Haas BT2-Belfast (United Kingdom)")
                .contactNumber("+44 490 270 9258")
                .contactEmail("mchampneys2@plala.or.jp")
                .build());
    }

    @Test
    public void customer_4() throws Exception {

        ResultActions resultActions = mvc.perform(get("/customer/4"))
                .andExpect(status().isOk());
        assertResponse(resultActions, AssertCustomer.builder()
                .firstName("Lennie")
                .lastName("O'Concannon")
                .gender("M")
                .birthday("28 October 1964")
                .address("992 Carey Street Old Shore D04-Booterstown (Ireland)")
                .contactNumber("+353 890 400 7048")
                .contactEmail("lo3@army.mil")
                .build());
    }

    @Test
    public void customer_5() throws Exception {

        ResultActions resultActions = mvc.perform(get("/customer/5"))
                .andExpect(status().isOk());
        assertResponse(resultActions, AssertCustomer.builder()
                .firstName("Andrey")
                .lastName("Crickmoor")
                .gender("X")
                .birthday("27 September 1960")
                .address("52 Monument Park Drewry GU32-Weston (United Kingdom)")
                .contactNumber("+44 398 972 4493")
                .contactEmail("acrickmoor4@amazonaws.com")
                .build()
        );
    }

    private void assertResponse(ResultActions result, AssertCustomer expected) throws Exception {

        ResultMatcher middleNameMatcher = StringUtils.isEmpty(expected.getMiddleName())
                ? jsonPath("$.middleName").doesNotExist()
                : jsonPath("$.middleName").value(expected.getMiddleName());

        result.andExpect(jsonPath("$.id").doesNotHaveJsonPath())
                .andExpect(jsonPath("$.firstName").value(expected.getFirstName()))
                .andExpect(middleNameMatcher)
                .andExpect(jsonPath("$.lastName").value(expected.getLastName()))
                .andExpect(jsonPath("$.gender").value(expected.getGender()))
                .andExpect(jsonPath("$.birthday").value(expected.getBirthday()))
                .andExpect(jsonPath("$.age").value(calculateAge(expected.getBirthday())))
                .andExpect(jsonPath("$.address").value(expected.getAddress()))
                .andExpect(jsonPath("$.contactNumber").value(expected.getContactNumber()))
                .andExpect(jsonPath("$.contactEmail").value(expected.getContactEmail()));
    }

    private String calculateAge(String date) {
        return Integer.toString(Period.between(LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMMM uuuu")), LocalDate.now()).getYears());
    }

    @Builder
    @Getter
    private static class AssertCustomer {
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final String gender;
        private final String birthday;
        private final String age;
        private final String address;
        private final String contactNumber;
        private final String contactEmail;
    }
}

