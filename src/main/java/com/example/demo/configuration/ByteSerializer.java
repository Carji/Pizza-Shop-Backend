package com.example.demo.configuration;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import lombok.NoArgsConstructor;

public @NoArgsConstructor class ByteSerializer implements RedisSerializer<byte[]> {

    @Override
    public byte[] serialize(byte[] t) throws SerializationException {
        return t;
    }

    @Override
    public byte[] deserialize(byte[] bytes) throws SerializationException {
        return bytes;
    }

    
}
