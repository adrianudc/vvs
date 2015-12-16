package util.http;

import com.google.common.collect.Maps;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

    /**
     *
     * @param query -
     * @return -
     */
    public static Map<String, String> queryToMap(final String query) {
        Map<String, String> result = Maps.newHashMap();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }

    /**
     *
     * @param httpExchange -
     * @param key -
     * @return -
     */
    public static String getQueryValueFromHttpExchange(final HttpExchange httpExchange, final String key) {
        String query = httpExchange.getRequestURI().getQuery();
        String value = HttpUtil.queryToMap(query).get(key);
        return value;
    }

    /**
     *
     * @param url -
     * @return -
     * @throws IOException -
     */

    public static HttpURLConnection sendGet(final String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod(HttpGet.METHOD_NAME);
        return connection;
    }

    /**
     *
     * @param url -
     * @param params -
     * @return -
     * @throws IOException -
     */

    public static HttpURLConnection sendPost(String url, final Map<String, String> params) throws IOException {
        url = addQueryParamsToUrl(url, params);
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod(HttpPost.METHOD_NAME);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setConnectTimeout(150000);
        connection.setReadTimeout(150000);
        return connection;
    }

    /**
     *
     * @param url -
     * @param params -
     * @return -
     * @throws UnsupportedEncodingException -
     */

    private static String addQueryParamsToUrl(String url, final Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), Charsets.UTF_8.name()));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), Charsets.UTF_8.name()));
        }

        return result.toString().isEmpty() ? url : url + "?" + result.toString();
    }
}
