package com.d2c.common.kafka.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.d2c.common.base.utils.BeanUt;

public class ObjectDeserializer implements Deserializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Object deserialize(String topic, byte[] data) {
        return BeanUt.toObject(data);
    }

    @Override
    public void close() {
    }
    
}
