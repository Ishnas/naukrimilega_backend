package com.naukrimilega.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class CustomDateSerializer extends JsonSerializer<Long> {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void serialize(Long longDate, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        if (longDate == null) {
            gen.writeNull();
        } else {
            gen.writeString(formatter.format(longDate));
        }
    }
}
