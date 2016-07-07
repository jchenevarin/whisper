import api.Server;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Server());
        vertx.deployVerticle("service:vertx.persistence");

    }
}
