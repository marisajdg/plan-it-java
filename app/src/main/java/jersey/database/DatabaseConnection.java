package jersey.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnection {
    private static MongoClient mongoClient;

    public static MongoClient getMongoClient(){
        if (mongoClient != null){
            return mongoClient;
        }

        //todo: other location? trycatchhh, research best way to do this
        Properties props = new Properties();
        try {
            // fix the path to current file and relative -- current dir is plan-it-java
            FileInputStream fileInputStream = new FileInputStream("app/src/main/java/jersey/database/database.properties");
            props.load(fileInputStream);
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.err.println("Error: File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //todo: horrible connection string
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(props.getProperty("db.url") + props.getProperty("db.username") + props.getProperty("db.password") + props.getProperty("db.url2")))
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
