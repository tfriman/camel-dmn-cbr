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
        rest("/")
                .get("greet/{name}")
                  .route()
                  .bean(Hello.class, "hello(${header['name']})")
                  .log("${body}")
                .endRest()
                .get("cbr/")
                  .route()
                    .log("${headers}")
                    .removeHeaders("*")
                    .setHeader("CamelHttpMethod", constant("GET"))
                    .to("http:{{rule-engine.url}}?bridgeEndpoint=true")
                    .log("${body}")
                    .unmarshal().json()
                .setBody(simple("${body['message']}"))
                .log("transformed to ${body}")
                .to("direct:cbr")
                .endRest()
        ;
        from("direct:cbr")
                .log("direct:cbr ${body}")
                .choice()
                .when(simple("${body} == 'option1'"))
                .setBody(constant("jee"))
                .otherwise()
                .setBody(constant("eih"))
                ;
    }

    public static class Hello {
        public String hello(String name) {
            return "Hello " + name;
        }
    }
}
