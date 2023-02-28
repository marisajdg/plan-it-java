package jersey.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DatabaseConnection {
    private static MongoClient mongoClient;

    public static MongoClient getMongoClient(){
        if (mongoClient != null){
            return mongoClient;
        }

        // connection string in const file
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(DatabaseConfig.CONNECTION_STRING))
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        try{
            mongoClient = MongoClients.create(settings);
            return mongoClient;
        }
        catch(Exception error){
            // todo: handle the try catch error by logging the error, retrying the connection, or returning a custom error message
            System.out.printf("Error: %s", error);
            return null;
        }
    }
}
