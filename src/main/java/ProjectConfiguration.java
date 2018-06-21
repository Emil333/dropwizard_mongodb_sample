import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class ProjectConfiguration extends Configuration {

    @JsonProperty
    public String appName;
    @JsonProperty
    public String mongoHost = "localhost";
    @JsonProperty
    public int mongoPort = 27017;

    public String mongoDb = "dexlock";

    public String collectionName = "dexlock-projects";

    public String getAppName() {
        return appName;
    }

    public String getMongoHost() {
        return mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public String getMongoDb() {
        return mongoDb;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
