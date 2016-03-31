import ratpack.server.RatpackServer;

public class RatpackHelloWorldApp {
  public static void main(String[] args) throws Exception {
    RatpackServer.start(serverSpec -> serverSpec
      .handlers(chain -> chain
        .get(ctx -> ctx.render("Hello, World!"))
      )
    );
  }
}
