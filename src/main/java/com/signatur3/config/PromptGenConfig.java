package com.signatur3.config;

import com.signatur3.data.jpa.repositories.*;
import com.signatur3.services.CachingDataLoaderService;
import com.signatur3.services.DataLoaderService;
import com.signatur3.services.DatabaseDataLoaderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.signatur3.config.PromptGenProperties.Defaults;

@Configuration
@EnableConfigurationProperties({PromptGenProperties.class})
@SuppressWarnings("unused" /* Spring-Boot Configuration annotation (Auto-Configure) */)
public class PromptGenConfig {

    @Bean
    @Primary
    DataLoaderService dataLoaderService(@Qualifier("databaseDataLoaderService")
                                        DataLoaderService databaseDataLoaderService) {
        CachingDataLoaderService cachingDataLoaderService = new CachingDataLoaderService(databaseDataLoaderService);
        cachingDataLoaderService.init();
        return cachingDataLoaderService;
    }

    @Bean
    Defaults defaults(PromptGenProperties promptGenProperties) {
        return promptGenProperties.getDefaults();
    }

    @Bean(name = "databaseDataLoaderService")
    DataLoaderService databaseDataLoaderService(
            final NamespaceRepository namespaceRepository,
            final RulebookRepository rulebookRepository,
            final DataTypeRepository dataTypeRepository,
            final DataTypeReferenceRepository typeReferenceRepository,
            final DataValueRepository dataValueRepository,
            final PromptSectionRepository promptSectionRepository,
            final PromptSectionReferenceRepository sectionReferenceRepository,
            final SepararorSetRepository separarorSetRepository) {
        return new DatabaseDataLoaderService(namespaceRepository,
                rulebookRepository,
                dataTypeRepository,
                typeReferenceRepository,
                dataValueRepository,
                promptSectionRepository,
                sectionReferenceRepository,
                separarorSetRepository);
    }
}
