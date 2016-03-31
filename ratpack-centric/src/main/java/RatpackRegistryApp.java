import ratpack.registry.Registry;
import ratpack.server.RatpackServer;

import java.time.Instant;

public class RatpackRegistryApp {
  public static void main(String[] args) throws Exception {
    RatpackServer.start(serverSpec -> serverSpec
      .registry(Registry.of(registrySpec -> registrySpec
        .add(MessageService.class, () -> "My message service")
      ))
      .handlers(chain -> chain
        .all(ctx ->
          ctx.next(Registry.single(Instant.now()))
        )
        .get(ctx -> {
          Instant requestStart = ctx.get(Instant.class);
          MessageService messageService = ctx.get(MessageService.class);
          ctx.render(requestStart + " " + messageService.send());
        })
      )
    );
  }
  interface MessageService {
    String send();
  }
}
