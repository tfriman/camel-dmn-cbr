package demosoft.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class CBRRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                //.component("undertow")
                // use json binding mode so Camel automatic binds json <--> pojo
                .bindingMode(RestBindingMode.json)
                // and output using pretty print
                .dataFormatProperty("prettyPrint", "true");

        rest("/")
                .post("cbr")
                .type(IncomingOrder.class)
                  .route()
                    .log("Got body ${body}")
                .process(e -> {
                    IncomingOrder order = e.getIn().getBody(IncomingOrder.class);
                    e.getIn().setBody(new DecisionRequest(order.loyaltyLevel, order.priceInCents));
                })
                .marshal().json()
                    .removeHeaders("*")
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
                    .to("http:{{rule-engine.url}}?bridgeEndpoint=true")
                    .log("${body}")
                    .unmarshal().json()
                    .setBody(simple("${body['serviceLevel']}"))
                    .log("transformed to ${body}")
                    .to("direct:cbr")
                .endRest()
        ;

        from("direct:cbr")
                .log("direct:cbr ${body}")
                .choice()
                .when(simple("${body} == 'premium'"))
                .setBody(constant("yes, premium customer!"))
                .otherwise()
                .setBody(constant("nopes"))
                ;
    }
}
