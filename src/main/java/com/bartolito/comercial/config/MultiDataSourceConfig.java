package com.bartolito.comercial.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MultiDataSourceConfig {

    // ========== LOLFAR (PRINCIPAL) ==========
    @Bean(name = "lolfarDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.lolfar")
    public DataSource lolfarDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "lolfarJdbcTemplate")
    @Primary
    public JdbcTemplate lolfarJdbcTemplate(
            @Qualifier("lolfarDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ========== LOLCLI ==========
    @Bean(name = "lolcliDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.lolcli")
    public DataSource lolcliDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "lolcliJdbcTemplate")
    public JdbcTemplate lolcliJdbcTemplate(
            @Qualifier("lolcliDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ========== LOLCLI_PRUEBA ==========
    @Bean(name = "lolcli2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.lolcli2")
    public DataSource lolcli2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "lolcli2JdbcTemplate")
    public JdbcTemplate lolcli2JdbcTemplate(
            @Qualifier("lolcli2DataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}