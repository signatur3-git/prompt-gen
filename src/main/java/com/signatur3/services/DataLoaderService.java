package com.signatur3.services;

import com.signatur3.data.model.*;

import java.util.List;

public interface DataLoaderService {

    Namespace getNamespace(String name);
    Rulebook getRulebook(String rulebookName);
    PromptSection getPromptSection(String sectionName);
    DataType getDataType(String dataTypeName);

    List<Namespace> getAllNamespaces();
    List<Rulebook> getAllRulebooks();
    List<DataType> getAllDataTypes();
    List<PromptSection> getAllPromptSections();
    List<Multiple> getAllSeparatorSets();
}
