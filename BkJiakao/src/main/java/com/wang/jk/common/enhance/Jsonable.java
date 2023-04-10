package com.wang.jk.common.enhance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface Jsonable {
    default String jsonString() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(this);
    }

    static <T> T object(String jsonString, Class<T> cls) throws Exception {
        return new ObjectMapper().readValue(jsonString, cls);
    }
}
