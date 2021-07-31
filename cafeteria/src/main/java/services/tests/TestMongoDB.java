package services.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import domain.caja.Moneda;
import domain.caja.strategy.Efectivo;
import domain.caja.strategy.Tarjeta;

import org.bson.Document;
import services.mongoDB.Articulo;
import services.mongoDB.Recibo;

import java.util.ArrayList;
import java.util.List;

public class TestMongoDB {
    /*public static void main(String[] args) {
        Articulo articulo1 = new Articulo("Espresso", 200, 2);
        Articulo articulo2 = new Articulo("Americano", 250, 3);
        Articulo articulo3 = new Articulo("Latte", 300, 4);

        List<Articulo> articulos = new ArrayList<Articulo>();
        articulos.add(articulo1);
        articulos.add(articulo2);
        articulos.add(articulo3);

        //Recibo recibo = new Recibo(Moneda.DOLAR, new Tarjeta(), articulos);

        // Crear cliente MongoDB
        MongoClient client = MongoClients.create("mongodb://localhost:27017");

        // Conectar con la base de datos
        MongoDatabase database = client.getDatabase("cafeteriaDB");

        ObjectMapper mapper = new ObjectMapper();

        // JSON a String

        try {
            String cadenaJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(recibo);
            Document documento = Document.parse(cadenaJSON);
            database.getCollection("recibos").insertOne(documento);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
    }*/
}
