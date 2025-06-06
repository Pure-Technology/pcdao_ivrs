package com.ivrs.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.ivrs.repositories",},
        entityManagerFactoryRef = "falconEntityManagerFactory",
        transactionManagerRef = "falconTransactionManager"
)
public class HibernateConfig {

    @Primary
    @Bean(name = "falconDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.falcon")
    public DataSource falconDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pcdaoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pcdao-ivrs")
    public DataSource pcdaoDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "falconEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(falconDataSource())
                .packages("com.ivrs.entities")
                .persistenceUnit("falconPU")
                .build();
        factoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return factoryBean;
    }

    @Bean(name = "pcdaoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pcdaoEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(pcdaoDataSource())
                .packages("com.ivrs.entities")
                .persistenceUnit("pcdaoPU")
                .build();
        factoryBean.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return factoryBean;
    }

    @Primary
    @Bean(name = "falconTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("falconEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "pcdaoTransactionManager")
    public PlatformTransactionManager pcdaoTransactionManager(
            @Qualifier("pcdaoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
