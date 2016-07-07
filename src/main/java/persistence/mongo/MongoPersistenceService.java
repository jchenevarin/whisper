package persistence.mongo;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

import java.util.List;

@ProxyGen
public interface MongoPersistenceService {

    // A couple of factory methods to create an instance and a proxy
    static MongoPersistenceService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(MongoPersistenceService.class, vertx, address);
        // Alternatively, you can create the proxy directly using:
        // return new ProcessorServiceVertxEBProxy(vertx, address);
        // The name of the class to instantiate is the service interface + `VertxEBProxy`.
        // This class is generated during the compilation
    }
    // The service methods
    void findOne(String collection, JsonObject query, JsonObject fields, Handler<AsyncResult<JsonObject>> resultHandler);
    void find(String collection, JsonObject query, Handler<AsyncResult<List<JsonObject>>> resultHandler);
    void save(String collection, JsonObject document, Handler<AsyncResult<JsonObject>> resultHandler);
    void update(String collection, JsonObject query, JsonObject update, Handler<AsyncResult<JsonObject>> resultHandler);
    void replace(String collection, JsonObject query, JsonObject replace, Handler<AsyncResult<JsonObject>> resultHandler);
    void remove(String collection, JsonObject query, Handler<AsyncResult<JsonObject>> resultHandler);
}
