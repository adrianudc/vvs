package util.servidor;

import java.util.Map;

import com.google.common.collect.Maps;

public class ServidorTestUtil {

    public static Map<String, String> getTokenParams(String token) {
        Map<String, String> params = Maps.newHashMap();
        params.put("token", token);
        return params;
    }

}
