import org.bson.Document;

import java.util.List;

public interface ProjectIntf {

    void insertOne(Document document);
    void insertMany(List<Document> documents);
    List<Document> findProjects();
    List<Document> findProjectByKey(String key, String value);
    void updateProject();
    void deleteProject(String key, String value);
}
