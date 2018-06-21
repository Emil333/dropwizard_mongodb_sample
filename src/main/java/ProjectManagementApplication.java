import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ProjectManagementApplication extends Application<ProjectConfiguration> {


    public void run(ProjectConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new ProjectResource(new ProjectResourceHelper(configuration, environment)));
    }

    public static void main(String[] args) throws Exception {
        new ProjectManagementApplication().run(args[0]);
    }
}
