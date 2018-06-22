package auth;

import io.dropwizard.auth.Authorizer;

public class AppAutheriser implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
