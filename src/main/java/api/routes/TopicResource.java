package api.routes;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.serviceproxy.ProxyHelper;
import persistence.mongo.MongoPersistenceService;

public class TopicResource {

    Logger logger = LoggerFactory.getLogger(TopicResource.class);

    public static final String TOPIC_COLLECTION = "topics";
    private MongoPersistenceService mongoPersistenceService;
    private EventBus eb;

    public TopicResource(Vertx vertx) {

        mongoPersistenceService = ProxyHelper.createProxy(MongoPersistenceService.class,
                vertx,
                "persistence.mongo.MongoPersistenceService");
        eb = vertx.eventBus();

    }

    public void register(Router router) {

        router.route().handler(BodyHandler.create());

        router.route(HttpMethod.GET, "/api/topic/")
                .produces("application/json")
                .handler(rc -> {

                    JsonObject query = new JsonObject();
                    mongoPersistenceService.find(TOPIC_COLLECTION, query, res -> {
                        if (res.succeeded()) {
                            rc.response().end(res.result().toString());
                        } else if (res.failed()) {
                            rc.response().end("get entity failed" + res.cause());
                        }
                    });

                });

        router.route(HttpMethod.GET, "/api/topic/:id")
                .produces("application/json")
                .handler(rc -> {

                    JsonObject query = new JsonObject();
                    query.put("_id", rc.request().params().get("id"));
                    mongoPersistenceService.findOne(TOPIC_COLLECTION, query, null, res -> {
                        if (res.succeeded()) {
                            rc.response().end(res.result().toString());
                        } else if (res.failed()) {
                            rc.response().end("get entity failed" + res.cause());
                        }
                    });

                });

        router.route(HttpMethod.PUT, "/api/topic")
                .produces("application/json")
                .handler(rc -> {

                    mongoPersistenceService.save(TOPIC_COLLECTION, rc.getBodyAsJson(), res -> {
                        if (res.succeeded()) {
                            rc.response().end("create entity successfully");
                        } else if (res.failed()) {
                            rc.response().end("create entity failed" + res.cause());
                        }
                    });

                });

        router.route(HttpMethod.POST, "/api/topic/:id")
                .produces("application/json")
                .handler(rc -> {

                    JsonObject query = new JsonObject();
                    query.put("_id", rc.request().params().get("id"));
                    mongoPersistenceService.update(TOPIC_COLLECTION, query, rc.getBodyAsJson(), res -> {
                        if (res.succeeded()) {
                            rc.response().end("update entity successfully");
                        } else if (res.failed()) {
                            rc.response().end("update entity failed" + res.cause());
                        }
                    });

                });

        router.route(HttpMethod.DELETE, "/api/topic/:id")
                .produces("application/json")
                .handler(rc -> {

                    JsonObject query = new JsonObject();
                    query.put("_id", rc.request().params().get("id"));
                    mongoPersistenceService.remove(TOPIC_COLLECTION, query, res -> {
                        if (res.succeeded()) {
                            rc.response().end("delete entity successfully");
                        } else if (res.failed()) {
                            rc.response().end("delete entity failed" + res.cause());
                        }
                    });

                });
    }
}
