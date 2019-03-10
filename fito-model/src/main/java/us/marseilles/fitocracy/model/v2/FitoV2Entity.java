package us.marseilles.fitocracy.model.v2;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FitoV2Entity
{
    @Key
    private long id;
}