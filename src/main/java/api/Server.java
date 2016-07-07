package api;

import api.routes.TopicResource;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class Server extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(Server.class);

    @Override
    public void start() throws Exception {

        logger.info("Server starting..");
        EventBus eb = vertx.eventBus();

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));


        Route route = router.route(HttpMethod.GET, "/api/hello/:name").produces("plain/text");
        route.handler(routingContext -> {

            eb.publish("api", "Resource called : " + route.getPath());

            String personName = routingContext.request().getParam("name");
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "plain/text");


            response.end("Hello " + personName);

        });

        TopicResource accountResource = new TopicResource(vertx);
        accountResource.register(router);

        server.requestHandler(router::accept).listen(8080);
    }

}