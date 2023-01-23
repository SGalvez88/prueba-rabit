package pruebarabit;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IFC
 */
public class PruebaRabit {

    private final static String QUEUE_NAME = "Pedidos";
    private final static String SERVER_NAME = "localhost";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(SERVER_NAME);
        factory.setRequestedHeartbeat(30);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            
            System.out.println(" [x] Received '" + message + "'");
            System.out.println(" convertir");
            
            try {

                Gson gson = new Gson();
                Pedido pedido = gson.fromJson(message, Pedido.class);
                System.out.println(pedido.toString());
                
            } catch (Exception e) {
                System.err.println("salta "+e.getMessage());
            }
            
            
            System.out.println(" imprimir ");
            
            //System.out.println("objeto "+pedido.toString());
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
        
    }

//    public static void createJson() {
//        Pedido pedido = new Pedido("1");
//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        String jsonPedido = gson.toJson(pedido);
//        System.out.println(jsonPedido);
//        sendToRabit(jsonPedido);
//    }

    public static void sendToRabit(String jsonPedido) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, jsonPedido.getBytes(StandardCharsets.UTF_8));
            //System.out.println(" [x] Sent '" + jsonPedido + "'");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void getToRabit(){
        
    }

}
