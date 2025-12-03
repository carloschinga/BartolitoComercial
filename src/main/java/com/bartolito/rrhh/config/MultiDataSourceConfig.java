package com.bartolito.rrhh.config;


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

    // ========== SIGOLD (PRINCIPAL) ==========
    @Bean(name = "sigoldDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.sigold")
    public DataSource sigoldDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sigoldJdbcTemplate")
    @Primary
    public JdbcTemplate sigoldJdbcTemplate(
            @Qualifier("sigoldDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    // ========== WDMS ==========
    @Bean(name = "wdmsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.wdms")
    public DataSource wdmsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "wdmsJdbcTemplate")
    public JdbcTemplate wdmsJdbcTemplate(
            @Qualifier("wdmsDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}