package jersey.database;

import com.mongodb.client.MongoClient;
import com.mongodb.connection.ClusterDescription;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {
    //todo: @before, @after?

    @Test
    public void getMongoClient() {
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        //todo: what if there are no dbs??
        assertNotNull(mongoClient.getDatabase("sample_restaurants"));
    }
}