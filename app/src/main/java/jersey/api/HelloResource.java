package jersey.api;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jersey.database.DatabaseConnection;

@Path("/guides")
public class HelloResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(){
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("sample_restaurants");
        return "Hello World!";
    }


}
