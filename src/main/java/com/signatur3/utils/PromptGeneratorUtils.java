package com.signatur3.utils;

import com.signatur3.data.model.Rulebook;
import com.signatur3.services.SuggestionContext;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@UtilityClass
public class PromptGeneratorUtils {

    public static Query buildQuery(Map<String, List<String>> queryParams, Rulebook rulebook) {
        return new Query(queryParams, rulebook, new SuggestionContext());
    }

    public static <T> T getRandom(List<T> candidates) {
        int index = (int) (Math.random() * candidates.size());
        return candidates.get(index);
    }

    public record Query(Map<String, List<String>> queryParams, Rulebook rulebook, SuggestionContext suggestionContext) {
        public void initContext() {
        }
    }

    public record ScopedName(String namespace, String name) {
        public static ScopedName of(String scopedName) {
            String[] pair = scopedName.split("::");
            return new ScopedName(pair[0], pair[1]);
        }

        @Override
        @NonNull
        public String toString() {
            return namespace + "::" + name;
        }
    }
}
