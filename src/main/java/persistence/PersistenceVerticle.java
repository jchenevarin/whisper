package persistence;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.serviceproxy.ProxyHelper;
import persistence.mongo.MongoPersistenceService;
import persistence.mongo.MongoPersistenceServiceImpl;

public class PersistenceVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(PersistenceVerticle.class);
    MongoPersistenceService mongoPersistenceService;

    @Override
    public void start() throws Exception {

        logger.info("PersistenceVerticle starting");

        // creating a mongodb client
        JsonObject mongoConfig = new JsonObject()
                .put("connection_string", "mongodb://localhost:27017")
                .put("db_name", "whisper");
        MongoClient mongoClient = MongoClient.createShared(vertx, mongoConfig);
        Vertx.currentContext().put("mongoClient", mongoClient);

        // Create and register services
        mongoPersistenceService = new MongoPersistenceServiceImpl();
        ProxyHelper.registerService(MongoPersistenceService.class, vertx, mongoPersistenceService, "persistence.mongo.MongoPersistenceService");

    }


}

