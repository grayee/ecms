/*
 * Copyright (c) 2018. Tianyi AIDOC Company.Inc. All Rights Reserved.
 */

package com.qslion.framework.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

/**
 * server
 *
 * @author Gray.Z
 * @date 2018/4/14 10:13.
 */
public class JSONUtils {

    protected static final Logger logger = LogManager.getLogger(JSONUtils.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    private JSONUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value 对象
     * @return JSON字符串
     */
    public static String writeValueAsString(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("write object to json string occurred an exception", e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json JSON字符串
     * @param valueType 类型
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json, "json string empty");
        Assert.notNull(valueType, "to json value type class is null");

        try {
            return OBJECT_MAPPER.readValue(json, valueType);
        } catch (JsonParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json JSON字符串
     * @param typeReference 类型
     * @return 对象
     */
    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json, "json string empty");
        Assert.notNull(typeReference, "json to object type reference  is null");

        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    /**
     * 将JSON字符串转换为对象
     *
     * @param json JSON字符串
     * @param javaType 类型
     * @return 对象
     */
    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json, "json string is empty");
        Assert.notNull(javaType, "java type is null");

        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (JsonParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将JSON字符串转换为树
     *
     * @param json JSON字符串
     * @return 树
     */
    public static JsonNode toTree(String json) {
        Assert.hasText(json, "json String is null");

        try {
            return OBJECT_MAPPER.readTree(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 将对象转换为JSON流
     *
     * @param writer Writer
     * @param value 对象
     */
    public static void writeValue(Writer writer, Object value) {
        Assert.notNull(writer, "writer is null");
        Assert.notNull(value, "value is null");

        try {
            OBJECT_MAPPER.writeValue(writer, value);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 构造类型
     *
     * @param type 类型
     * @return 类型
     */
    public static JavaType constructType(Type type) {
        Assert.notNull(type, "construct type is null");

        return TypeFactory.defaultInstance().constructType(type);
    }

    /**
     * 构造类型
     *
     * @param typeReference 类型
     * @return 类型
     */
    public static JavaType constructType(TypeReference<?> typeReference) {
        Assert.notNull(typeReference,"type reference is null");

        return TypeFactory.defaultInstance().constructType(typeReference);
    }

}
