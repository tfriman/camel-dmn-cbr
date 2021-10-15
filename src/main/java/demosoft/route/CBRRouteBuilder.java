package demosoft.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class CBRRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/")
                .get("cbr/{choice}")
                  .route()
                    .log("${headers}")
                    .setBody(simple("{\"message\":  \"${header['choice']}\"}"))
                    .removeHeaders("*")
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
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
                .setBody(constant("yes!"))
                .otherwise()
                .setBody(constant("nopes"))
                ;
    }

    public static class Hello {
        public String hello(String name) {
            return "Hello " + name;
        }
    }
}
