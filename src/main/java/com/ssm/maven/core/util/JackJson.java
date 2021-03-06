package com.ssm.maven.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/4/11.
 */
public class JackJson {
    private static final Logger logger = Logger.getLogger(JackJson.class);
    /**
     * 格式化时间的string
     */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * jackjson把json字符串转换为Java对象的实现方法
     *
     * <pre>
     * return JackJson.fromJsonToObject(this.answersJson, new TypeReference&lt;List&lt;StanzaAnswer&gt;&gt;() {
     * });
     * </pre>
     *
     * @param <T>           转换为的java对象
     * @param json          json字符串
     * @param typeReference jackjson自定义的类型
     * @return 返回Java对象
     */
    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * json转换为java对象
     *
     * <pre>
     * return JackJson.fromJsonToObject(this.answersJson, JackJson.class);
     * </pre>
     *
     * @param <T>       要转换的对象
     * @param json      字符串
     * @param valueType 对象的class
     * @return 返回对象
     */
    public static <T> T fromJsonToObject(String json, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error("JsonParseException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object Java对象
     * @return 返回字符串
     */
    public static String fromObjectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object     要转换的对象
     * @param filterName 过滤器的名称
     * @param properties 要过滤哪些字段
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String fromObjectToJson(Object object, String filterName, Set<String> properties) {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(properties));
        try {
            return mapper.setFilterProvider(filters).writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象转换为json字符串
     *
     * @param object     要转换的对象
     * @param filterName 过滤器的名称
     * @param property   要过滤的字段名称
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String fromObjectToJson(Object object, String filterName, String property) {
        ObjectMapper mapper = new ObjectMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, SimpleBeanPropertyFilter.serializeAllExcept(property));
        try {
            return mapper.setFilterProvider(filters).writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象(包含日期字段或属性)转换为json字符串
     *
     * @param object Java对象
     * @return 返回字符串
     */
    public static String fromObjectHasDateToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().with(new SimpleDateFormat(DATE_TIME_FORMAT));
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * java对象(包含日期字段或属性)转换为json字符串
     *
     * @param object               Java对象
     * @param dateTimeFormatString 自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return 返回字符串
     */
    public static String fromObjectHasDateToJson(Object object, String dateTimeFormatString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().with(new SimpleDateFormat(dateTimeFormatString));
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            logger.error("JsonGenerationException: ", e);
        } catch (JsonMappingException e) {
            logger.error("JsonMappingException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     *
     * @param jsonData
     * @param beanType
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonToList(String jsonData, Class<T> beanType) {
        ObjectMapper mapper = new ObjectMapper();

        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = mapper.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
