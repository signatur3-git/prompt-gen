package com.signatur3.services;

import com.signatur3.data.model.PromptSection;
import com.signatur3.data.model.Rulebook;
import com.signatur3.utils.PromptGeneratorUtils;
import com.signatur3.utils.PromptSectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromptGeneratorService {
    private final DataLoaderService dataLoaderService;
    private final RenderService renderService;

    public List<String> suggestPrompts(
            Rulebook rulebook, Integer amount,
            Map<String, List<String>> queryParams) {
        PromptGeneratorUtils.Query query = PromptGeneratorUtils.buildQuery(queryParams, rulebook);
        query.initContext();
        List<PromptSection> candidates = rulebook.entrySections().stream()
                .map(dataLoaderService::getPromptSection)
                .filter(section -> PromptSectionUtils.testSection(section, query))
                .toList();

        return IntStream.range(0, amount).mapToObj(index -> PromptGeneratorUtils.getRandom(candidates))
                .map(section -> renderService.renderSection(section, query))
                .toList();
    }
}
