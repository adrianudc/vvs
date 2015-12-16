package mocktests.servidor;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 */
public class ServidorMockTestUtil {

    /**
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
