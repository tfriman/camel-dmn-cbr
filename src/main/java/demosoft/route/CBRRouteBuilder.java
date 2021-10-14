package demosoft.route;

import org.apache.camel.CamelContext;
import org.apache.camel.Header;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class CBRRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/cbr/")
                .produces("text/plain")
                .get("hello")
                .route()
                .transform().constant("Hello World!")
                .endRest()
                .get("hello/{name}")
                .route()
                .bean(Hello.class, "hello(${header['name']})")
                .log("${body}");
    }

    public static class Hello {

        public String hello(String name) {
            return "Hello " + name;
        }
    }
}
