package util.json;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 */
public abstract class JSONUtil {

    /**
     * Json object mapper.
     */
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     *
     * @param json -
     * @param valueType -
     * @param <T> -
     * @return -
     */
    public static <T> T jsonToObject(final String json, final Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing: " + json + "\nException message: " + e.getMessage());
        }
    }

    /**
     *
     * @param object -
     * @param <T> -
     * @return -
     */
    public static <T> String objectTOJSON(final T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing: " + object + "\nException message: " + e.getMessage());
        }
    }

    /**
     *
     * @param object -
     * @param <T> -
     * @return -
     */
    public static <T> String objectListTOJSON(final List<T> object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing: " + object + "\nException message: " + e.getMessage());
        }
    }

}
