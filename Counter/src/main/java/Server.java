import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

import java.util.Enumeration;


public class Server extends AbstractVerticle {
    private int counter = 0;
    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            // This handler gets called for each request that arrives on the server
            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");
            String method = request.getParam("method");
            request.bodyHandler(body -> System.out.println(body));
            response.putHeader("content-type", "text/plain");
            response.putHeader("Access-Control-Allow-Origin", "*");
            if ("reset".equals(method)) {
                reset();
                response.end("Reset Success");
            } else if ("get".equals(method)) {
                final int localCounter = this.counter;
                response.end(String.valueOf(localCounter));
            } else if ("add".equals(method)){
                add();
                response.end("Add Success");
            }

        });
        server.listen(8080);
    }

    public void reset() {
        this.counter = 0;
    }

    public void add() {
        this.counter++;
    }
}