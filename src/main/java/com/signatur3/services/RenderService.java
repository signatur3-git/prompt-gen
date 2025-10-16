package com.signatur3.services;

import com.signatur3.data.model.*;
import com.signatur3.utils.MultipleUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static com.signatur3.utils.PromptGeneratorUtils.Query;
import static com.signatur3.utils.PromptGeneratorUtils.ScopedName;

@Slf4j
@Service
@RequiredArgsConstructor
public class RenderService {
    private static final Pattern PATTERN_DATATYPE = Pattern.compile("\\{}");
    private static final Pattern PATTERN_SECTION = Pattern.compile("\\[]");

    private final DataLoaderService dataLoaderService;

    public String renderSection(PromptSection currentSection, Query query) {
        String template = currentSection.template();
        ScopedName scopedName = new ScopedName(currentSection.namespace(), currentSection.name());
        log.debug("Rendering prompt section: {}", scopedName);
        int index = 0;
        String result = template;
        Matcher matcher = PATTERN_DATATYPE.matcher(result);
        while (matcher.find()) {
            DataTypeReference dataTypeReference = currentSection.dataTypeReferences().get(index);
            DataType type = dataLoaderService.getDataType(dataTypeReference.type());
            String dataReferenceRenderResult = IntStream.rangeClosed(
                            1, MultipleUtils.getRandomAmount(dataTypeReference))
                    .mapToObj(number -> renderDataType(type, query))
                    .collect(MultipleUtils.buildJoinMultipleCollector(dataTypeReference));
            result = matcher.replaceFirst(Matcher.quoteReplacement(dataReferenceRenderResult));
            matcher = PATTERN_DATATYPE.matcher(result);
            index++;
        }

        index = 0;
        matcher = PATTERN_SECTION.matcher(result);
        while (matcher.find()) {
            PromptSectionReference promptSectionReference = currentSection.sectionReferences().get(index);
            PromptSection subSection = dataLoaderService.getPromptSection(promptSectionReference.type());
            String sectionReferenceRenderResult = IntStream.rangeClosed(
                            1, MultipleUtils.getRandomAmount(promptSectionReference))
                    .mapToObj(number -> renderSection(subSection, query))
                    .collect(MultipleUtils.buildJoinMultipleCollector(promptSectionReference));
            result = matcher.replaceFirst(sectionReferenceRenderResult);
            matcher = PATTERN_SECTION.matcher(result);
            index++;
        }
        return result;
    }

    public String renderDataType(DataType dataType, Query ignoredQuery) {
        ScopedName scopedName = new ScopedName(dataType.namespace(), dataType.name());
        log.debug("Rendering datatype: {} ({})",
                scopedName,
                dataType.values().size());
        if (dataType.values().isEmpty()) {
            return "?";
        }
        return getRandomValue(dataType.values());
    }

    private String getRandomValue(List<DataValue> values) {
        int index = (int) (Math.random() * values.size());
        return values.get(index).value();
    }
}
