package com.signatur3.services;

import com.signatur3.data.errors.ApplicationError;
import com.signatur3.data.jpa.entities.DataTypeEntity;
import com.signatur3.data.jpa.entities.NamespaceEntity;
import com.signatur3.data.jpa.entities.PromptSectionEntity;
import com.signatur3.data.jpa.entities.RulebookEntity;
import com.signatur3.data.jpa.repositories.*;
import com.signatur3.data.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static com.signatur3.utils.PromptGeneratorUtils.ScopedName;

@Slf4j
public record DatabaseDataLoaderService(NamespaceRepository namespaceRepository,
                                        RulebookRepository rulebookRepository,
                                        DataTypeRepository dataTypeRepository,
                                        DataTypeReferenceRepository typeReferenceRepository,
                                        DataValueRepository dataValueRepository,
                                        PromptSectionRepository promptSectionRepository,
                                        PromptSectionReferenceRepository sectionReferenceRepository,
                                        SepararorSetRepository separatorSetRepository) implements DataLoaderService {

    public Namespace getNamespace(String namespaceName) {
        return Namespace.of(getNamespaceEntity(namespaceName));
    }

    public Rulebook getRulebook(String rulebookName) {
        ScopedName scopedName = ScopedName.of(rulebookName);
        return Rulebook.of(
                getRulebookEntity(
                        getNamespaceEntity(scopedName.namespace()),
                        scopedName.name()));
    }

    public PromptSection getPromptSection(String sectionName) {
        ScopedName scopedName = ScopedName.of(sectionName);
        PromptSectionEntity promptSectionEntity =
                getPromptSection(
                        getNamespaceEntity(scopedName.namespace()),
                        scopedName.name());
        List<PromptSectionReference> sectionReferences = sectionReferenceRepository
                .findAllByPromptSectionOrderByIndexAsc(promptSectionEntity)
                .stream().map(PromptSectionReference::of).toList();
        List<DataTypeReference> dataTypeReferences = typeReferenceRepository
                .findAllByPromptSectionOrderByIndexAsc(promptSectionEntity)
                .stream().map(DataTypeReference::of).toList();

        return PromptSection.of(promptSectionEntity, sectionReferences, dataTypeReferences);
    }

    public DataType getDataType(String dataTypeName) {
        ScopedName scopedName = ScopedName.of(dataTypeName);
        DataTypeEntity dataTypeEntity = getDataTypeEntity(
                getNamespaceEntity(scopedName.namespace()),
                scopedName.name());
        List<DataValue> values = dataValueRepository.findAllByDataType(dataTypeEntity).stream()
                .map(DataValue::of)
                .toList();
        log.debug("Found {} values", values.size());
        return DataType.of(dataTypeEntity, values);
    }

    public List<Namespace> getAllNamespaces() {
        return namespaceRepository.findAll().stream().map(Namespace::of).toList();
    }

    public List<Rulebook> getAllRulebooks() {
        return rulebookRepository.findAll().stream().map(Rulebook::of).toList();
    }

    public List<DataType> getAllDataTypes() {
        return dataTypeRepository.findAll().stream().map(entity -> DataType.of(entity, List.of())).toList();
    }

    public List<PromptSection> getAllPromptSections() {
        return promptSectionRepository.findAll().stream()
                .map(section -> section.getNamespace().getName() + "::" + section.getName())
                .map(this::getPromptSection)
                .toList();
    }

    public List<Multiple> getAllSeparatorSets() {
        return separatorSetRepository.findAll().stream()
                .map(set -> Multiple.of(set, null, null))
                .toList();
    }

    private NamespaceEntity getNamespaceEntity(String namespaceName) {
        return Optional.ofNullable(namespaceRepository.findByName(namespaceName))
                .orElseThrow(ApplicationError.NotFound::new);
    }

    private RulebookEntity getRulebookEntity(NamespaceEntity namespace, String rulebookName) {
        return Optional.ofNullable(rulebookRepository.findByNamespaceAndName(namespace, rulebookName))
                .orElseThrow(ApplicationError.NotFound::new);
    }

    private PromptSectionEntity getPromptSection(NamespaceEntity namespace, String sectionName) {
        return Optional.ofNullable(promptSectionRepository.findByNamespaceAndName(namespace, sectionName))
                .orElseThrow(ApplicationError.NotFound::new);
    }

    private DataTypeEntity getDataTypeEntity(NamespaceEntity namespace, String dataTypeName) {
        return Optional.ofNullable(dataTypeRepository.findByNamespaceAndName(namespace, dataTypeName))
                .orElseThrow(ApplicationError.NotFound::new);
    }
}
