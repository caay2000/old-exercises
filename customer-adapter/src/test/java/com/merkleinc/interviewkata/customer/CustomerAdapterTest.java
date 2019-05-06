package com.merkleinc.interviewkata.customer;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.merkleinc.interviewkata.customer.model.Country;
import com.merkleinc.interviewkata.customer.model.Gender;
import com.merkleinc.interviewkata.repository.CustomerRepository;
import com.merkleinc.interviewkata.repository.exception.RepositoryException;
import com.merkleinc.interviewkata.repository.model.Address;
import com.merkleinc.interviewkata.repository.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAdapterTest {

    public static final String DOES_NOT_EXIST = "DOES NOT EXISTS";
    public static final String VALID_CUSTOMER = "EXISTS";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerApi testee;

    @Before
    public void setUp() {
        testee = new CustomerAdapter(customerRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCustomerId() {
        testee.getCustomer(null);
    }

    @Test
    public void nonExistingCustomer() throws RepositoryException {
        when(customerRepository.getCustomer(DOES_NOT_EXIST)).thenThrow(new RepositoryException("error"));

        Assert.assertFalse(testee.getCustomer(DOES_NOT_EXIST).isPresent());
    }

    @Test
    public void validCustomer() throws RepositoryException {
        when(customerRepository.getCustomer(VALID_CUSTOMER)).thenReturn(aCustomer());

        Optional<com.merkleinc.interviewkata.customer.model.Customer> result = testee.getCustomer(VALID_CUSTOMER);

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expetedCustomer(), result.get());
    }

    private Customer aCustomer() {
        return new Customer("id", "name", "M", LocalDate.now().toString(), "email", "phone", "account",
                new Address("address1", "address2", "city", "postCode", "UK"));
    }

    private com.merkleinc.interviewkata.customer.model.Customer expetedCustomer() {
        return new com.merkleinc.interviewkata.customer.model.Customer.Builder()
                .withId("id")
                .withName("name")
                .withGender(Gender.MALE)
                .withBirthday(LocalDate.now())
                .withEmail("email")
                .withPhoneNumber("phone")
                .withAccountNumber("account")
                .withAddress(new com.merkleinc.interviewkata.customer.model.Address.Builder()
                        .withAddressLine1("address1")
                        .withAddressLine2("address2")
                        .withCity("city")
                        .withPostCode("postCode")
                        .withCountry(Country.UK)
                        .build())
                .build();
    }
}