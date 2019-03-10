package us.marseilles.fitocracy.model.v1;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FitoV1Entity
{
    @Key
    private long id;
}