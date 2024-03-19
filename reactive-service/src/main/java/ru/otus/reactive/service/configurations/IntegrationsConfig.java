package ru.otus.reactive.service.configurations;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class IntegrationsConfig {

    private static final String BASE_URL = "http://localhost:8190";
    private static final int CONNECT_TIMEOUT_MILLIS = 2000;
    private static final int WRITE_TIMEOUT_MILLIS = 2500;
    private static final int READ_TIMEOUT_MILLIS = 30000;

    @Bean
    public WebClient productDetailsServiceWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(createHttpConnector())
                .filter(logRequest())
                .build();
    }

    private ReactorClientHttpConnector createHttpConnector() {
        return new ReactorClientHttpConnector(createHttpClient());
    }

    private HttpClient createHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .doOnConnected(connection -> connection
                        .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                );
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            return next.exchange(clientRequest);
        };
    }
}