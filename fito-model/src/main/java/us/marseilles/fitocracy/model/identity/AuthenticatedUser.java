package us.marseilles.fitocracy.model.identity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticatedUser
{
    private String username;
    private String userId;
    private String sessionId;
    private String csrfToken;
}