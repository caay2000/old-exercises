package com.merkleinc.interviewkata.application;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import com.merkleinc.interviewkata.api.CustomerApi;
import com.merkleinc.interviewkata.api.internal.customer.CustomerInternalApi;
import com.merkleinc.interviewkata.api.internal.customer.model.Address;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;
import com.merkleinc.interviewkata.api.internal.customer.model.Customer;
import com.merkleinc.interviewkata.api.internal.customer.model.Gender;
import com.merkleinc.interviewkata.application.exception.NotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CustomerApplicationTest {

    private static final String FIRST_NAME = "firstName";
    private static final String MIDDLE_NAME = "middleName";
    private static final String LAST_NAME = "lastName";
    private static final String SECOND_LAST_NAME = "endName";
    private static final String NO_MIDDLE_NAME = String.format("%s %s", FIRST_NAME, LAST_NAME);
    private static final String FULL_NAME = String.format("%s %s %s %s", FIRST_NAME, MIDDLE_NAME, LAST_NAME, SECOND_LAST_NAME);
    private static final Gender GENDER = Gender.MALE;
    private static final LocalDate BIRTHDAY = LocalDate.now().minusYears(20);
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("d MMMM uuuu");
    private static final String CONTACT_NUMBER = "number";
    private static final String EMAIL = "email";
    private static final String ADDRESS_1 = "Address 1";
    private static final String ADDRESS_2 = "Address 2";
    private static final String POST_CODE = "postCode";
    private static final String CITY = "city";
    private static final Country COUNTRY = Country.UK;

    @Mock
    private CustomerInternalApi customerInternalApi;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CustomerApi testee;

    @Before
    public void setUp() {
        testee = new CustomerApplication(customerInternalApi);

        when(customerInternalApi.getCustomer("customerId")).thenReturn(Optional.of(aCustomer()));
    }

    @Test
    public void customerNotFound() {

        when(customerInternalApi.getCustomer(eq("invalid"))).thenReturn(Optional.empty());

        expectedException.expect(NotFoundException.class);
        expectedException.expectMessage("customer invalid not found");

        testee.get("invalid");
    }

    @Test
    public void customerFirstNameMapped() {

        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(FIRST_NAME, customer.getFirstName());
    }

    @Test
    public void customerMiddleNameMapped() {

        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(MIDDLE_NAME, customer.getMiddleName());
    }

    @Test
    public void customerMiddleNameNull() {
        Customer aCustomer = aCustomer();
        when(customerInternalApi.getCustomer("customerId")).thenReturn(Optional.of(aCustomer));
        ReflectionTestUtils.setField(aCustomer, "name", NO_MIDDLE_NAME);

        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertNull(customer.getMiddleName());
    }

    @Test
    public void customerLastNameMapped() {

        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(String.format("%s %s", LAST_NAME, SECOND_LAST_NAME), customer.getLastName());
    }

    @Test
    public void customerGenderMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(GENDER.getValue(), customer.getGender());
    }

    @Test
    public void customerBirthdayMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(BIRTHDAY.format(DATE_PATTERN), customer.getBirthday());
    }

    @Test
    public void customerAgeMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals("20", customer.getAge());
    }

    @Test
    public void customerAddressMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(String.format("%s %s %s-%s (%s)", ADDRESS_1, ADDRESS_2, POST_CODE, CITY, COUNTRY.getValue()), customer.getAddress());
    }

    @Test
    public void customerAddressNoAddressLine2Mapped() {
        Customer aCustomer = aCustomer();
        when(customerInternalApi.getCustomer("customerId")).thenReturn(Optional.of(aCustomer));
        ReflectionTestUtils.setField(aCustomer.getAddress(), "addressLine2", null);

        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(String.format("%s %s-%s (%s)", ADDRESS_1, POST_CODE, CITY, COUNTRY.getValue()), customer.getAddress());
    }

    @Test
    public void customerContactNumberMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(String.format("%s %s", Country.UK.getPhonePrefix(), CONTACT_NUMBER), customer.getContactNumber());
    }

    @Test
    public void customerContactEmailMapped() {
        com.merkleinc.interviewkata.api.model.Customer customer = testee.get("customerId");

        Assert.assertEquals(EMAIL, customer.getContactEmail());
    }

    private Customer aCustomer() {
        return Customer.builder()
                .name(FULL_NAME)
                .gender(GENDER)
                .birthday(BIRTHDAY)
                .phoneNumber(CONTACT_NUMBER)
                .email(EMAIL)
                .address(Address.builder()
                        .addressLine1(ADDRESS_1)
                        .addressLine2(ADDRESS_2)
                        .postCode(POST_CODE)
                        .city(CITY)
                        .country(COUNTRY)
                        .build())
                .build();
    }
}