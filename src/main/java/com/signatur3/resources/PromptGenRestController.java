package com.signatur3.resources;

import com.signatur3.services.DataLoaderService;
import com.signatur3.services.PromptGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.signatur3.config.PromptGenProperties.Defaults;

@Slf4j
@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused" /* Spring-Boot REST Controller is used by Spring magic */)
public class PromptGenRestController {
    private final DataLoaderService dataLoaderService;
    private final PromptGeneratorService promptGeneratorService;
    private final Defaults defaults;

    @GetMapping("/rest/generate/{rulebookId}")
    public List<String> generate(@PathVariable("rulebookId") String rulebookId,
                                 @RequestParam(required = false, name = "amount") Integer amount) {
        return promptGeneratorService.suggestPrompts(
                dataLoaderService.getRulebook(
                        Optional.ofNullable(rulebookId).orElse(defaults.getRulebook())),
                Optional.ofNullable(amount).orElse(defaults.getPromptCount()),
                new HashMap<>());
    }
}
