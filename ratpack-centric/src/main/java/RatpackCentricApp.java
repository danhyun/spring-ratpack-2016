import ratpack.server.RatpackServer;
import ratpack.spring.Spring;

public class RatpackCentricApp {
  public static void main(String[] args) throws Exception {
    RatpackServer.start(serverSpec -> serverSpec
      .registry(Spring.spring(AcmeConfig.class))
      .handlers(chain -> chain
        .get(ctx -> ctx.render("Hello, World!"))
      )
    );
  }
}
