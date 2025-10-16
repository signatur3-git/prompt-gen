package com.signatur3.resources;

import com.signatur3.services.DataLoaderService;
import com.signatur3.services.DemoDataCreator;
import com.signatur3.services.PromptGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.signatur3.config.PromptGenProperties.Defaults;
import static com.signatur3.data.errors.ApplicationError.NotFound;

@Slf4j
@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused" /* Spring-Boot MVC Controller is used by Spring magic */)
public class PromptGenController {
    private final DemoDataCreator demoDataCreator;
    private final DataLoaderService dataLoaderService;
    private final PromptGeneratorService promptGeneratorService;
    private final Defaults defaults;

    @GetMapping({"/", "/hello"})
    public String hello(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        demoDataCreator.deleteAll();
        demoDataCreator.createDemoData();
        model.addAttribute("name", name);
        model.addAttribute("namespaces", dataLoaderService.getAllNamespaces());
        model.addAttribute("rulebooks", dataLoaderService.getAllRulebooks());
        model.addAttribute("datatypes", dataLoaderService.getAllDataTypes());
        model.addAttribute("promptsections", dataLoaderService.getAllPromptSections());
        model.addAttribute("multiples", dataLoaderService.getAllSeparatorSets());
        return "hello";
    }

    @GetMapping("/generate/{rulebookId}")
    public String generate(@PathVariable(value = "rulebookId", required = false) String rulebookId,
                           @RequestParam(value = "amount", required = false) Integer amount,
                           Model model) {
        try {
            List<String> prompts = promptGeneratorService.suggestPrompts(
                    dataLoaderService.getRulebook(
                            Optional.ofNullable(rulebookId).orElse(defaults.getRulebook())),
                    Optional.ofNullable(amount).orElse(defaults.getPromptCount()),
                    new HashMap<>());
            model.addAttribute("prompts", prompts);
            return "generate";
        } catch (NotFound notFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }
}
