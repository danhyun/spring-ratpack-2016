import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import ratpack.registry.Registry;
import ratpack.server.RatpackServer;

public class RatpackBlockingIntegrationApp {
  public static void main(String[] args) throws Exception {
    RatpackServer.start(serverSpec -> serverSpec
      .registry(Registry.of(registrySpec -> registrySpec
        .add(BlockingMessageService.class, () -> {
          try {
            Thread.sleep(1000);
          } catch (Exception e) {
            // uh oh
          }
          return "My blocking message service";
        })
      ))
      .handlers(chain -> chain
        .get(ctx -> {
          BlockingMessageService messageService = ctx.get(BlockingMessageService.class);
          Promise<String> promise = Blocking.get(messageService::send);
          promise.then(ctx::render);
        })
      )
    );
  }
  interface BlockingMessageService {
    String send();
  }
}
