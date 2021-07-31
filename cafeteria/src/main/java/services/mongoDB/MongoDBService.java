package services.mongoDB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoDBService {
    private final String mongodbURI = "mongodb://localhost:27017";
    MongoClient client = MongoClients.create(mongodbURI);

    private final String databaseName = "cafeteriaDB";
    MongoDatabase database = client.getDatabase(databaseName);

    private final String collectionName = "recibos";
    MongoCollection<Document> recibosCollection = database.getCollection(collectionName);

    public static Document mapearPOJOaJSON(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String cadenaJSON = "";

        // JSON a String
        try {
            cadenaJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

        return Document.parse(cadenaJSON);
    }

    public void cargarRecibo(Document documento) {
        this.recibosCollection.insertOne(documento);
    }

    /* Getters y Setters */

    public MongoClient getClient() {
        return client;
    }

    public void setClient(MongoClient client) {
        this.client = client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }

    public  MongoCollection<Document> getRecibosCollection() {
        return recibosCollection;
    }

    public void setRecibosCollection(MongoCollection<Document> recibosCollection) {
        this.recibosCollection = recibosCollection;
    }
}
