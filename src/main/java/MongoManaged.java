import com.mongodb.Mongo;
import io.dropwizard.lifecycle.Managed;

public class MongoManaged implements Managed{

    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    public void start(){

    }

    public void stop(){
        this.mongo.close();
    }
}
