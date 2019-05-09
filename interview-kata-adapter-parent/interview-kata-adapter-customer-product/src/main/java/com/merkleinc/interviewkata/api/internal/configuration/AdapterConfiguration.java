package com.merkleinc.interviewkata.api.internal.configuration;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.merkleinc.interviewkata.repository.CustomerApi;
import com.merkleinc.interviewkata.repository.CustomerRepository;

@Configuration
public class AdapterConfiguration {

    @Bean
    public CustomerApi customerRepository() throws IOException {
        return new CustomerRepository();
    }
}
