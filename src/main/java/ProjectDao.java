
import io.dropwizard.setup.Environment;
import org.bson.Document;
import java.util.List;

public class ProjectDao {

    private MongoService service;

    public ProjectDao(ProjectConfiguration configuration, Environment environment) {

       this.service = new MongoService(configuration, environment);
    }


    public List<Document> getProjects(){
        return this.service.findProjects();
    }

    public List<Document> getProjectByKey(String name){
        return this.service.findProjectByKey("developer", name);
    }

    public void addProject(Document document){
        this.service.insertOne(document);
    }

    public void addProjects(List<Document> documents){
        this.service.insertMany(documents);
    }

}
