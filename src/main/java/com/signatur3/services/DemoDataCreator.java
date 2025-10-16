package com.signatur3.services;

import com.signatur3.data.jpa.entities.*;
import com.signatur3.data.jpa.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoDataCreator {
    private final NamespaceRepository namespaceRepository;
    private final RulebookRepository rulebookRepository;
    private final PromptSectionRepository promptSectionRepository;
    private final PromptSectionReferenceRepository sectionReferenceRepository;
    private final DataTypeRepository dataTypeRepository;
    private final DataTypeReferenceRepository typeReferenceRepository;
    private final DataValueRepository dataValueRepository;
    private final SepararorSetRepository separarorSetRepository;

    public void deleteAll() {
        dataValueRepository.deleteAll();
        sectionReferenceRepository.deleteAll();
        typeReferenceRepository.deleteAll();
        separarorSetRepository.deleteAll();
        dataTypeRepository.deleteAll();
        promptSectionRepository.deleteAll();
        rulebookRepository.deleteAll();
        namespaceRepository.deleteAll();
    }

    public void createDemoData() {
        // Namespace
        NamespaceEntity namespace = new NamespaceEntity("com.signatur3");
        namespace = namespaceRepository.save(namespace);

        // Rulebook
        RulebookEntity rulebook = new RulebookEntity(namespace, "v7-showcase",
                "com.signatur3::void,com.signatur3::void-mood,com.signatur3::void-sref");
        rulebookRepository.save(rulebook);

        // PromptSections
        PromptSectionEntity promptSectionVoidMood = new PromptSectionEntity(namespace, "void-mood",
                ". --ar {} --p {}");
        promptSectionVoidMood = promptSectionRepository.save(promptSectionVoidMood);
        PromptSectionEntity promptSectionVoidSref = new PromptSectionEntity(namespace, "void-sref",
                ". --ar {} --sref {}");
        promptSectionVoidSref = promptSectionRepository.save(promptSectionVoidSref);

        PromptSectionEntity promptSectionVoid = new PromptSectionEntity(namespace, "void", ". []");
        promptSectionVoid = promptSectionRepository.save(promptSectionVoid);
        PromptSectionEntity promptSectionParams = new PromptSectionEntity(namespace, "params", "{}{}{}");
        promptSectionParams = promptSectionRepository.save(promptSectionParams);

        // DataTypes
        DataTypeEntity dataTypeAR = new DataTypeEntity(namespace, "aspect-ratio");
        dataTypeAR = dataTypeRepository.save(dataTypeAR);
        DataValueEntity ar1To1 = new DataValueEntity(dataTypeAR, "--ar 1:1");
        dataValueRepository.save(ar1To1);
        DataValueEntity ar2To1 = new DataValueEntity(dataTypeAR, "--ar 2:1");
        dataValueRepository.save(ar2To1);
        DataValueEntity ar16To9 = new DataValueEntity(dataTypeAR, "--ar 16:9");
        dataValueRepository.save(ar16To9);
        DataValueEntity ar3To2 = new DataValueEntity(dataTypeAR, "--ar 3:2");
        dataValueRepository.save(ar3To2);
        DataValueEntity ar4To3 = new DataValueEntity(dataTypeAR, "--ar 4:3");
        dataValueRepository.save(ar4To3);
        DataValueEntity ar3To4 = new DataValueEntity(dataTypeAR, "--ar 3:4");
        dataValueRepository.save(ar3To4);
        DataValueEntity ar2To3 = new DataValueEntity(dataTypeAR, "--ar 2:3");
        dataValueRepository.save(ar2To3);
        DataValueEntity ar9To16 = new DataValueEntity(dataTypeAR, "--ar 9:16");
        dataValueRepository.save(ar9To16);
        DataValueEntity ar1To2 = new DataValueEntity(dataTypeAR, "--ar 1:2");
        dataValueRepository.save(ar1To2);

        DataTypeEntity dataTypeArRaw = new DataTypeEntity(namespace, "aspect-ratio-raw");
        dataTypeArRaw = dataTypeRepository.save(dataTypeArRaw);
        ar1To1 = new DataValueEntity(dataTypeArRaw, "1:1");
        dataValueRepository.save(ar1To1);
        ar2To1 = new DataValueEntity(dataTypeArRaw, "2:1");
        dataValueRepository.save(ar2To1);
        ar16To9 = new DataValueEntity(dataTypeArRaw, "16:9");
        dataValueRepository.save(ar16To9);
        ar3To2 = new DataValueEntity(dataTypeArRaw, "3:2");
        dataValueRepository.save(ar3To2);
        ar4To3 = new DataValueEntity(dataTypeArRaw, "4:3");
        dataValueRepository.save(ar4To3);
        ar3To4 = new DataValueEntity(dataTypeArRaw, "3:4");
        dataValueRepository.save(ar3To4);
        ar2To3 = new DataValueEntity(dataTypeArRaw, "2:3");
        dataValueRepository.save(ar2To3);
        ar9To16 = new DataValueEntity(dataTypeArRaw, "9:16");
        dataValueRepository.save(ar9To16);
        ar1To2 = new DataValueEntity(dataTypeArRaw, "1:2");
        dataValueRepository.save(ar1To2);

        DataTypeEntity dataTypeSref = new DataTypeEntity(namespace, "sref");
        dataTypeSref = dataTypeRepository.save(dataTypeSref);
        DataValueEntity sref1 = new DataValueEntity(dataTypeSref, " --sref 2973008775");
        dataValueRepository.save(sref1);
        DataValueEntity sref2 = new DataValueEntity(dataTypeSref, " --sref 339073139");
        dataValueRepository.save(sref2);
        DataValueEntity sref3 = new DataValueEntity(dataTypeSref, " --sref 3747509314");
        dataValueRepository.save(sref3);
        DataValueEntity sref4 = new DataValueEntity(dataTypeSref, " --sref 307308201");
        dataValueRepository.save(sref4);
        DataValueEntity sref5 = new DataValueEntity(dataTypeSref, " --sref 3486792880");
        dataValueRepository.save(sref5);
        DataValueEntity sref6 = new DataValueEntity(dataTypeSref, " --sref 3783900001");
        dataValueRepository.save(sref6);
        DataValueEntity sref7 = new DataValueEntity(dataTypeSref, " --sref 3144851921");
        dataValueRepository.save(sref7);
        DataValueEntity sref8 = new DataValueEntity(dataTypeSref, " --sref 2670451553");
        dataValueRepository.save(sref8);
        DataValueEntity sref9 = new DataValueEntity(dataTypeSref, " --sref 739075487");
        dataValueRepository.save(sref9);

        DataTypeEntity dataTypeSrefRaw = new DataTypeEntity(namespace, "sref-raw");
        dataTypeSrefRaw = dataTypeRepository.save(dataTypeSrefRaw);
        sref1 = new DataValueEntity(dataTypeSrefRaw, "2973008775");
        dataValueRepository.save(sref1);
        sref2 = new DataValueEntity(dataTypeSrefRaw, "339073139");
        dataValueRepository.save(sref2);
        sref3 = new DataValueEntity(dataTypeSrefRaw, "3747509314");
        dataValueRepository.save(sref3);
        sref4 = new DataValueEntity(dataTypeSrefRaw, "307308201");
        dataValueRepository.save(sref4);
        sref5 = new DataValueEntity(dataTypeSrefRaw, "3486792880");
        dataValueRepository.save(sref5);
        sref6 = new DataValueEntity(dataTypeSrefRaw, "3783900001");
        dataValueRepository.save(sref6);
        sref7 = new DataValueEntity(dataTypeSrefRaw, "3144851921");
        dataValueRepository.save(sref7);
        sref8 = new DataValueEntity(dataTypeSrefRaw, "2670451553");
        dataValueRepository.save(sref8);
        sref9 = new DataValueEntity(dataTypeSrefRaw, "739075487");
        dataValueRepository.save(sref9);

        DataTypeEntity dataTypeMB = new DataTypeEntity(namespace, "moodboard");
        dataTypeMB = dataTypeRepository.save(dataTypeMB);
        DataValueEntity mb1 = new DataValueEntity(dataTypeMB, " --p m7279818262194421776 m7267662901505163267");
        dataValueRepository.save(mb1);

        DataTypeEntity dataTypeMbRaw = new DataTypeEntity(namespace, "moodboard-raw");
        dataTypeMbRaw = dataTypeRepository.save(dataTypeMbRaw);
        mb1 = new DataValueEntity(dataTypeMbRaw, "m7279818262194421776");
        dataValueRepository.save(mb1);
        DataValueEntity mb2 = new DataValueEntity(dataTypeMbRaw, "m7267662901505163267");
        dataValueRepository.save(mb2);
        DataValueEntity mb3 = new DataValueEntity(dataTypeMbRaw, "m7374848644811325448");
        dataValueRepository.save(mb3);
        DataValueEntity mb4 = new DataValueEntity(dataTypeMbRaw, "m7373762274609070081");
        dataValueRepository.save(mb4);
        DataValueEntity mb5 = new DataValueEntity(dataTypeMbRaw, "m7368643034453377061");
        dataValueRepository.save(mb5);
        DataValueEntity mb6 = new DataValueEntity(dataTypeMbRaw, "m7373395582041194531");
        dataValueRepository.save(mb6);
        DataValueEntity mb7 = new DataValueEntity(dataTypeMbRaw, "m7368585127414202372");
        dataValueRepository.save(mb7);
        DataValueEntity mb8 = new DataValueEntity(dataTypeMbRaw, "m7367621894998065178");
        dataValueRepository.save(mb8);
        DataValueEntity mb9 = new DataValueEntity(dataTypeMbRaw, "m7360612321967734816");
        dataValueRepository.save(mb9);

        SeparatorSetEntity separatorSet = new SeparatorSetEntity(namespace, ", ");
        separatorSet.setSeparatorLast(", and ");
        separatorSet.setSeparatorTwoElements(" and ");
        separarorSetRepository.save(separatorSet);
        separatorSet = new SeparatorSetEntity(namespace, " ");
        separarorSetRepository.save(separatorSet);

        PromptSectionReferenceEntity paramsReference = new PromptSectionReferenceEntity(promptSectionVoid,
                promptSectionParams, 0, null);
        sectionReferenceRepository.save(paramsReference);
        DataTypeReferenceEntity arReference = new DataTypeReferenceEntity(promptSectionParams,
                dataTypeAR, 0, null);
        typeReferenceRepository.save(arReference);
        DataTypeReferenceEntity srefReference = new DataTypeReferenceEntity(promptSectionParams,
                dataTypeSref, 1, null);
        typeReferenceRepository.save(srefReference);
        DataTypeReferenceEntity moodboardReference = new DataTypeReferenceEntity(promptSectionParams,
                dataTypeMB, 2, null);
        typeReferenceRepository.save(moodboardReference);

        DataTypeReferenceEntity arReferenceMood = new DataTypeReferenceEntity(promptSectionVoidMood,
                dataTypeArRaw, 0, null);
        typeReferenceRepository.save(arReferenceMood);
        DataTypeReferenceEntity arReferenceSref = new DataTypeReferenceEntity(promptSectionVoidSref,
                dataTypeArRaw, 0, null);
        typeReferenceRepository.save(arReferenceSref);
        srefReference = new DataTypeReferenceEntity(promptSectionVoidSref,
                dataTypeSrefRaw, 1, null);
        srefReference.setMin(1);
        srefReference.setMax(3);
        srefReference.setSeparatorSet(separatorSet);
        typeReferenceRepository.save(srefReference);
        moodboardReference = new DataTypeReferenceEntity(promptSectionVoidMood,
                dataTypeMbRaw, 1, null);
        moodboardReference.setMin(1);
        moodboardReference.setMax(3);
        moodboardReference.setSeparatorSet(separatorSet);
        typeReferenceRepository.save(moodboardReference);
    }
}
