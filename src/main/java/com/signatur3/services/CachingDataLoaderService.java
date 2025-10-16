package com.signatur3.services;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.signatur3.data.model.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CachingDataLoaderService implements DataLoaderService {
    private final DataLoaderService databaseDataLoaderService;

    private LoadingCache<@NonNull String, Rulebook> rulebookLoadingCache;
    private LoadingCache<@NonNull String, PromptSection> promptSectionLoadingCache;
    private LoadingCache<@NonNull String, DataType> dataTypeLoadingCache;

    private List<Namespace> allNamespaces = null;
    private List<Rulebook> allRulebooks = null;
    private List<DataType> allDataTypes = null;
    private List<PromptSection> allPromptSections = null;
    private List<Multiple> allMultiples = null;

    public void init() {
        rulebookLoadingCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterAccess(Duration.ofHours(1))
                .build(databaseDataLoaderService::getRulebook);
        promptSectionLoadingCache = Caffeine.newBuilder()
                .maximumSize(100_000)
                .expireAfterAccess(Duration.ofHours(1))
                .build(databaseDataLoaderService::getPromptSection);
        dataTypeLoadingCache = Caffeine.newBuilder()
                .maximumSize(100_000)
                .expireAfterAccess(Duration.ofHours(1))
                .build(databaseDataLoaderService::getDataType);
    }

    @Override
    public Namespace getNamespace(@NonNull String namespaceName) {
        return databaseDataLoaderService.getNamespace(namespaceName);
    }

    @Override
    public Rulebook getRulebook(@NonNull String rulebookName) {
        return rulebookLoadingCache.get(rulebookName);
    }

    @Override
    public PromptSection getPromptSection(@NonNull String sectionName) {
        return promptSectionLoadingCache.get(sectionName);
    }

    @Override
    public DataType getDataType(@NonNull String dataTypeName) {
        return dataTypeLoadingCache.get(dataTypeName);
    }

    @Override
    public List<Namespace> getAllNamespaces() {
        if (allNamespaces == null) {
            allNamespaces = databaseDataLoaderService.getAllNamespaces();
        }
        return allNamespaces;
    }

    @Override
    public List<Rulebook> getAllRulebooks() {
        if (allRulebooks == null) {
            allRulebooks = databaseDataLoaderService.getAllRulebooks();
        }
        return allRulebooks;
    }

    @Override
    public List<DataType> getAllDataTypes() {
        if (allDataTypes == null) {
            allDataTypes = databaseDataLoaderService.getAllDataTypes();
        }
        return allDataTypes;
    }

    @Override
    public List<PromptSection> getAllPromptSections() {
        if (allPromptSections == null) {
            allPromptSections = databaseDataLoaderService.getAllPromptSections();
        }
        return allPromptSections;
    }

    @Override
    public List<Multiple> getAllSeparatorSets() {
        if (allMultiples == null) {
            allMultiples = databaseDataLoaderService.getAllSeparatorSets();
        }
        return allMultiples;
    }
}
