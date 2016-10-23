package util.servidor;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * The type Servidor test util.
 */
public abstract class ServidorTestUtil {

    /**
     * Gets token params.
     *
     * @param token -
     * @return -
     */
    public static Map<String, String> getTokenParams(final String token) {
        Map<String, String> params = Maps.newHashMap();
        params.put("token", token);
        return params;
    }

}
