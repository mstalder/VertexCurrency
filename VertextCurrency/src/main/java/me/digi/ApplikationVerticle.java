package me.digi;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;


public class ApplikationVerticle extends io.vertx.core.AbstractVerticle {
	HttpServer http;
	@Override
	public void start(Future<Void> future) throws Exception {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.route().handler(StaticHandler.create());
		http = vertx.createHttpServer()
				.requestHandler(router::accept)
				.listen(config().getInteger("http.port", 8081),
				result -> {
					if (result.succeeded()) {
						future.complete();
					} else {
						future.fail(result.cause());	
					}
				});
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		http.close(closed -> {
			if (closed.succeeded()) {
				stopFuture.complete();
			} else {
				stopFuture.fail(closed.cause());
			}
		});
	}
}
