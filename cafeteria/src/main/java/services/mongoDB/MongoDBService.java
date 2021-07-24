package services.mongoDB;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class MongoDBService {
    private final String mongodbURI = "mongodb://localhost:27017";
    MongoClient client = MongoClients.create(mongodbURI);

    MongoDatabase database = client.getDatabase("cafeteriaDB");
    MongoCollection<Document> recibosCollection = database.getCollection("recibos");


    public static Document crearDocumento(Recibo recibo) {
        Document nuevoDocumento = new Document();

        nuevoDocumento.append("date", recibo.getDate())
                      .append("articulos", recibo.getArticulos())
                      .append("moneda", "Dolar Estadounidense")
                      .append("metodoPago", "Tarjeta")
                      .append("subtotal", recibo.getSubtotal())
                      .append("total", recibo.getTotal());

        return nuevoDocumento;
    }

    public void insertarDocumento(Document documento) {
        database.getCollection("recibos").insertOne(documento);
    }

}
