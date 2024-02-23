package ru.coffelenko.caffeinecache.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig {
    @Value("${r2dbc.connection.host}")
    private String host;
    @Value("${r2dbc.connection.port}")
    private int port;
    @Value("${r2dbc.connection.username}")
    private String username;
    @Value("${r2dbc.connection.password}")
    private String password;
    @Value("${r2dbc.connection.database}")
    private String database;
    @Value("${r2dbc.connection.schema}")
    private String schema;
    @Value("${r2dbc.connection.applicationName}")
    private String appName;

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "postgresql")
                .option(HOST, host)
                .option(PORT, port)
                .option(DATABASE, database)
                .option(USER, username)
                .option(PASSWORD, password)
                .build());
    }
}
