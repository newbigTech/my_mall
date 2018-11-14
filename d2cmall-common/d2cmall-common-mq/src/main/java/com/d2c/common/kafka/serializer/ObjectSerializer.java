package com.d2c.common.kafka.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.d2c.common.base.utils.BeanUt;

public class ObjectSerializer implements Serializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Object data) {
        return BeanUt.toByte(data);
    }

    @Override
    public void close() {
    }
    
}
