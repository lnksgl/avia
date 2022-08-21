package avia.cloud.gateway.filter;

import avia.cloud.gateway.exception.JwtKeyException;
import lombok.experimental.FieldDefaults;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE, makeFinal = true)
class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    WebClient.Builder webClientBuilder;

    public JwtAuthFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("test");
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new JwtKeyException("Missing auth information");
            }

            String authHeader = Objects.requireNonNull(exchange
                            .getRequest()
                            .getHeaders()
                            .get(org.springframework.http.HttpHeaders.AUTHORIZATION))
                    .get(0);

//            return webClientBuilder.build()
//                    .post()
//                    .uri("http://auth-service/auth/validateToken?token=" + authHeader)
//                    .retrieve().bodyToMono(UserRecord.class)
//                    .map(user -> {
//                        exchange.getRequest()
//                                .mutate()
//                                .header("X-auth-user-id", String.valueOf(user.id()));
//                        return exchange;
//                    }).flatMap(chain::filter);

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {

                    }));
        };
    }

    public static class Config {}
}
