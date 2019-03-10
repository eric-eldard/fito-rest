package us.marseilles.fitocracy.client.model.v2;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v2
 * <p>
 * The root element of an API v2 response
 *
 * @param <T> the type of wrapped data
 */
@Getter
@Setter
public class ResponseWrapper<T>
{
    @Key
    private T data;

    @Key
    private String error;

    @Key
    private boolean success;
}