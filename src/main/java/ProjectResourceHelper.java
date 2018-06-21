
import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import io.dropwizard.setup.Environment;
import org.bson.Document;

import javax.ws.rs.Consumes;
import java.util.ArrayList;
import java.util.List;


public class ProjectResourceHelper {

    private ProjectConfiguration configuration;
    private Environment environment;
    private ProjectDao dao;

    public ProjectResourceHelper(ProjectConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;
        this.dao = new ProjectDao(configuration, environment);
    }


    public ApiData addProject(Project project) {
        Gson gson = new Gson();
        String json = gson.toJson(project);
        try {
            this.dao.addProject(new Document(BasicDBObject.parse(json)));
        } catch (NullPointerException e) {

        }
        ApiData data = new ApiData();
        data.setMessage("Project created successfully");
        return data;
    }

    public ApiData getProjects() {
        List<Document> projects = this.dao.getProjects();
        ArrayList<Project> projectList = convertDocumentListToProjectList(projects);

        ApiData data = new ApiData();
        data.setProjects(projectList);
        return data;
    }

    public ApiData getProjectByDeveloper(String developerName) {

        List<Document> projects = this.dao.getProjectByKey(developerName);
        ArrayList<Project> projectList = convertDocumentListToProjectList(projects);
        ApiData data = new ApiData();
        data.setProjects(projectList);
        return data;
    }

    public ApiData getProjectsByDeveloper(String developerName) {

        List<Document> projects = this.dao.getProjects();
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        for (Document document : projects) {
            if (document.get(Constants.DEVELOPER_KEY).toString().toLowerCase().contains(developerName.toLowerCase())) {
                Project project = new Project();
                project.setDeveloper(document.get(Constants.DEVELOPER_KEY).toString());
                project.setName(document.get(Constants.PROJECT_NAME_KEY).toString());
                project.setPlatform(document.get(Constants.PROJECT_PLATFORM_KEY).toString());
                projectArrayList.add(project);
            }
        }
        ApiData data = new ApiData();
        data.setProjects(projectArrayList);
        return data;

    }

    private ArrayList<Project> convertDocumentListToProjectList(List<Document> documents) {
        ArrayList<Project> projectArrayList = new ArrayList<Project>();
        for (Document doc : documents) {
            Project project = new Project();
            project.setDeveloper(doc.get(Constants.DEVELOPER_KEY).toString());
            project.setName(doc.get(Constants.PROJECT_NAME_KEY).toString());
            project.setPlatform(doc.get(Constants.PROJECT_PLATFORM_KEY).toString());
            projectArrayList.add(project);
        }
        return projectArrayList;
    }


}
