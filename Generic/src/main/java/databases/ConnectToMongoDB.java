package databases;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrahman on 09/09/18.
 */

public class ConnectToMongoDB {

    public static MongoDatabase mongoDatabase = null;
    public static MongoDatabase connectToMongoDB(){
        MongoClient mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("PNT");
        System.out.println("Database Connected");
        return mongoDatabase;
    }
    public String insertToMongoDB(String menu){
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document>  collection = mongoDatabase.getCollection("CategoryTable");
        //table names: infoMenu
        //using getCollection(String s) method.
        Document doc = new Document().append("CategoryList",menu);
        collection.insertOne(doc);
        return menu + " added.";
    }
    public List<String> readFromMongoDB(String tableName,String columnName){
        List<String> list = new ArrayList<>();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection(tableName);
        //table names: CategoryTable
        BasicDBObject basicDBObject = new BasicDBObject();
        FindIterable<Document> iterable = collection.find(basicDBObject);
        for(Document doc:iterable){
            String spanText = (String) doc.get(columnName);
            list.add(spanText);
        }
        return list;
    }
}
