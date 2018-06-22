import auth.AppAutheriser;
import auth.AppBasicAuthenticator;
import auth.User;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ProjectManagementApplication extends Application<ProjectConfiguration> {


    public void run(ProjectConfiguration configuration, Environment e) throws Exception {
        e.jersey().register(new ProjectResource(new ProjectResourceHelper(configuration, e)));
        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new AppBasicAuthenticator())
                .setAuthorizer(new AppAutheriser())
                .setRealm("BASIC-AUTH-REALM")
                .buildAuthFilter()));
        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    public static void main(String[] args) throws Exception {
        new ProjectManagementApplication().run(args[0]);
    }
}
