package com.merkleinc.interviewkata.api.internal.customer.translator;

import com.merkleinc.common.Translator;
import com.merkleinc.interviewkata.api.internal.customer.model.Address;
import com.merkleinc.interviewkata.api.internal.customer.model.Country;

public class AddressTranslator implements Translator<com.merkleinc.interviewkata.repository.model.Address, Address> {

    @Override
    public Address translate(com.merkleinc.interviewkata.repository.model.Address source) {
        return new Address.Builder()
                .withAddressLine1(source.getAddressLine1())
                .withAddressLine2(source.getAddressLine2())
                .withCity(source.getCity())
                .withPostCode(source.getPostCode())
                .withCountry(Country.from(source.getCountry()))
                .build();
    }
}
