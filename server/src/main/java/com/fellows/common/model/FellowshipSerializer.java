package com.fellows.common.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FellowshipSerializer extends StdSerializer<Fellowship> {

    protected FellowshipSerializer(Class<Fellowship> t) {
        super(t);
    }

    protected FellowshipSerializer(JavaType type) {
        super(type);
    }

    protected FellowshipSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected FellowshipSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(Fellowship fellowship, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("url", fellowship.getUrl());
        map.put("name", fellowship.getName());
        map.put("id", fellowship.getId().toString());

        Fellowship fellowship1 = new Fellowship();
        fellowship1.setId(fellowship.getId());
        fellowship1.setName(fellowship.getName());
        fellowship1.setUrl(fellowship.getUrl());
        jsonGenerator.writeObject(fellowship1);
    }
}
