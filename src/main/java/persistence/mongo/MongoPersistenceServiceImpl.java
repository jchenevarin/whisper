package persistence.mongo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;

import java.util.List;


public class MongoPersistenceServiceImpl implements MongoPersistenceService {

    Logger logger = LoggerFactory.getLogger(MongoPersistenceServiceImpl.class);

    @Override
    public void findOne(String collection, JsonObject query, JsonObject fields, Handler<AsyncResult<JsonObject>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        mongoClient.findOne(collection, query, fields, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while looking for doc " + res.cause()));
            }
        });
    }

    @Override
    public void find(String collection, JsonObject query, Handler<AsyncResult<List<JsonObject>>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        mongoClient.find(collection, query, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while looking for docs " + res.cause()));
            }
        });
    }

    @Override
    public void save(String collection, JsonObject document, Handler<AsyncResult<JsonObject>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        mongoClient.save(collection, document, res -> {
            if (res.succeeded()) {
                String id = res.result();
                logger.info("Saved document with id " + id);
                resultHandler.handle(Future.succeededFuture(new JsonObject()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while saving doc " + res.cause()));
            }
        });
    }

    @Override
    public void update(String collection, JsonObject query, JsonObject update, Handler<AsyncResult<JsonObject>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        UpdateOptions options = new UpdateOptions().setMulti(true);
        JsonObject mongoUpdate = new JsonObject().put("$set", update);
        mongoClient.updateWithOptions(collection, query, mongoUpdate, options, res -> {
            if (res.succeeded()) {
                logger.info("Updated document(s) successfully");
                resultHandler.handle(Future.succeededFuture(new JsonObject()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while updating document(s) " + res.cause()));
            }
        });

    }

    @Override
    public void replace(String collection, JsonObject query, JsonObject replace, Handler<AsyncResult<JsonObject>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        mongoClient.replace(collection, query, replace, res -> {
            if (res.succeeded()) {
                logger.info("Replaced document(s) successfully");
                resultHandler.handle(Future.succeededFuture(new JsonObject()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while replacing document(s) " + res.cause()));
            }
        });
    }

    @Override
    public void remove(String collection, JsonObject query, Handler<AsyncResult<JsonObject>> resultHandler) {

        MongoClient mongoClient = Vertx.currentContext().get("mongoClient");
        mongoClient.remove(collection, query, res -> {
            if (res.succeeded()) {
                logger.info("Removed document(s) successfully");
                resultHandler.handle(Future.succeededFuture(new JsonObject()));
            } else {
                resultHandler.handle(Future.failedFuture("Error while removing document(s) " + res.cause()));
            }
        });
    }

}
