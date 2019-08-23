package com.github.caay2000.metropolis.model.provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caay2000.metropolis.model.exception.MetropolisException;

public class JacksonJsonSerializer implements JsonSerializer {

    private final ObjectMapper mapper;

    public JacksonJsonSerializer() {
        this.mapper = new ObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new MetropolisException("error serializing to JSON", e);
        }
    }
}
