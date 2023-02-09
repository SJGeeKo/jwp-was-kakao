package webserver;

import java.util.*;

public class HttpCookie {

    private final Map<String, String> cookieMap = new HashMap<>();

    public HttpCookie() {
        cookieMap.put("JSESSIONID", UUID.randomUUID().toString());
        cookieMap.put("Path", "/");
    }

    public HttpCookie(String cookie) {
        String[] datas = cookie.split(";");
        for (String data : datas) {
            String[] line = data.split("=");
            // TODO: 개선할 수 없을까?
            if (line.length == 2) {
                cookieMap.put(line[0], line[1]);
            }
        }
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(cookieMap.get(key));
    }

    public String parseString() {
        StringBuilder sb = new StringBuilder();
        for (String key : cookieMap.keySet()) {
            sb.append("Set-Cookie: ").append(key).append("=").append(cookieMap.get(key)).append(" \r\n");
        }
        return sb.toString();
    }
}
