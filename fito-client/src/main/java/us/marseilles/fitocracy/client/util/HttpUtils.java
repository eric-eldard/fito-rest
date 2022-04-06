package us.marseilles.fitocracy.client.util;

import com.google.api.client.http.HttpResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class HttpUtils
{
    /**
     * Find first or throw exception
     */
    public String getResponseHeader(HttpResponse httpResponse, String headerName)
    {
        List<String> headerValues = (List<String>) httpResponse.getHeaders().get(headerName);
        String headerValue = null;
        if (headerValues != null)
        {
            headerValue = headerValues
                .stream()
                .findFirst()
                .orElse(null);
        }

        if (headerValue == null)
        {
            throw new RuntimeException("Header " + headerName + " not found");
        }

        return headerValue;
    }

    /**
     * Find first or throw exception
     */
    public String getResponseCookie(HttpResponse httpResponse, String cookieName)
    {
        List<String> cookies = (List<String>) httpResponse.getHeaders().get("Set-Cookie");
        String cookie = null;
        if (cookies != null)
        {
            cookie = cookies
                .stream()
                .filter(header -> header.startsWith(cookieName))
                .findFirst()
                .orElse(null);
        }

        if (cookie == null)
        {
            throw new RuntimeException("Cookie " + cookieName + " not found");
        }

        String cookieValue = cookie.substring(cookieName.length() + 1, cookie.indexOf(';'));
        return cookieValue;
    }
}
