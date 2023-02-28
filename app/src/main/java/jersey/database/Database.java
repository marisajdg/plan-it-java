package jersey.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import jersey.models.Guide;

public class Database implements IDatabase{
    @Override
    public void InsertGuide(Guide guide) {
        MongoClient mongoClient = DatabaseConnection.getMongoClient();
        MongoDatabase database = mongoClient.getDatabase("sample_restaurants");
    }
}

    //MongoDatabase database = mongoClient.getDatabase("sample_restaurants");
//            MongoCollection<Document> collection = database.getCollection("restaurants");
//            Document doc = collection.find(eq("restaurant_id", "40356018")).first();
//            if (doc != null) {
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No matching documents found.");
//            }
