import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.setup.Environment;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoService implements ProjectIntf{

    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoService(ProjectConfiguration configuration, Environment environment) {

        MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);

        this.db = mongoClient.getDatabase(configuration.getMongoDb());
        this.collection = this.db.getCollection(configuration.getCollectionName());
    }

    public void insertOne(Document document) {
        collection.insertOne(document);
    }

    public void insertMany(List<Document> documents) {
        collection.insertMany(documents);
    }

    public List<Document> findProjects() {

        List<Document> projects = collection.find().into(new ArrayList<Document>());

        return projects;
    }

    public List<Document> findProjectByKey(String key, String value) {
        List<Document> projects = collection.find(eq(key, value)).into(new ArrayList<Document>());
        return projects;
    }

//    public List<Document> findProjectsBySearchQuery(String query){
////        String rawQuery = "$text : { $search : " + query + ", $language : en, $caseSensitive: false, $diacriticSensitive: false";
////        List<Document> projects = collection.find({})
//    }

    public void updateProject() {

    }

    public void deleteProject(String key, String value) {

    }
}
