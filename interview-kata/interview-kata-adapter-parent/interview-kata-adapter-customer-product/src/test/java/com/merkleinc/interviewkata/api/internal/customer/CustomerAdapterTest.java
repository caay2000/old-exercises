package com.merkleinc.interviewkata.api.internal.customer;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;
import com.merkleinc.interviewkata.api.internal.customer.model.Gender;
import com.merkleinc.interviewkata.repository.CustomerRepositoryApi;
import com.merkleinc.interviewkata.repository.exception.RepositoryException;
import com.merkleinc.interviewkata.repository.model.Address;
import com.merkleinc.interviewkata.repository.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAdapterTest {

    private static final String DOES_NOT_EXIST = "DOES NOT EXISTS";
    private static final String VALID_CUSTOMER = "EXISTS";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String GENDER = "Male";
    private static final LocalDate TODAY = LocalDate.now();
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ACCOUNT_NUMBER = "account";
    private static final String ADDRESS_1 = "address1";
    private static final String ADDRESS_2 = "address2";
    private static final String CITY = "city";
    private static final String POST_CODE = "postCode";
    private static final String COUNTRY = "United Kingdom";

    @Mock
    private CustomerRepositoryApi customerRepository;

    private CustomerInternalApi testee;

    @Before
    public void setUp() {
        testee = new CustomerInternalAdapter(customerRepository);
    }

    @Test
    public void nonExistingCustomer() throws RepositoryException {
        when(customerRepository.getCustomer(DOES_NOT_EXIST)).thenThrow(new RepositoryException("error"));

        Assert.assertFalse(testee.getCustomer(DOES_NOT_EXIST).isPresent());
    }

    @Test
    public void validCustomer() throws RepositoryException {
        when(customerRepository.getCustomer(VALID_CUSTOMER)).thenReturn(aCustomer());

        Optional<com.merkleinc.interviewkata.api.internal.customer.model.Customer> result = testee.getCustomer(VALID_CUSTOMER);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expetedCustomer(), result.get());
    }

    private Customer aCustomer() {
        return new Customer(ID, NAME, GENDER, LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")), EMAIL, PHONE, ACCOUNT_NUMBER,
                new Address(ADDRESS_1, ADDRESS_2, CITY, POST_CODE, COUNTRY));
    }

    private com.merkleinc.interviewkata.api.internal.customer.model.Customer expetedCustomer() {
        return com.merkleinc.interviewkata.api.internal.customer.model.Customer.builder()
                .id(ID)
                .name(NAME)
                .gender(Gender.from(GENDER))
                .birthday(TODAY)
                .email(EMAIL)
                .phoneNumber(PHONE)
                .accountNumber(ACCOUNT_NUMBER)
                .address(com.merkleinc.interviewkata.api.internal.customer.model.Address.builder()
                        .addressLine1(ADDRESS_1)
                        .addressLine2(ADDRESS_2)
                        .city(CITY)
                        .postCode(POST_CODE)
                        .country(Country.from(COUNTRY))
                        .build())
                .build();
    }
}