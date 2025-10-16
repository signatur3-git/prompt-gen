package com.signatur3.utils;

import com.signatur3.data.model.DataTypeReference;
import com.signatur3.data.model.Multiple;
import com.signatur3.data.model.PromptSectionReference;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class MultipleUtils {

    public static Integer getRandomAmount(DataTypeReference dataTypeReference) {
        return getRandomAmount(dataTypeReference.multiple());
    }

    public static Integer getRandomAmount(PromptSectionReference promptSectionReference) {
        return getRandomAmount(promptSectionReference.multiple());
    }

    public static Multiple getOnceInstance() {
        return new Multiple("com.signatur3", null, null,
                ",", null, null);
    }

    public static Collector<String, ?, String> buildJoinMultipleCollector(DataTypeReference dataTypeReference) {
        return buildJoinMultipleCollector(dataTypeReference.multiple());
    }

    public static Collector<String, ?, String> buildJoinMultipleCollector(PromptSectionReference promptSectionReference) {
        return buildJoinMultipleCollector(promptSectionReference.multiple());
    }

    private static String getTwoValueSeparator(Multiple multiple) {
        return Optional
                .ofNullable(multiple.separatorTwoElements())
                .orElse(applyTwoElementModification(getLastValueSeparator(multiple)));
    }

    private static String getLastValueSeparator(Multiple multiple) {
        return Optional
                .ofNullable(multiple.separatorLast())
                .orElse(getRegularSeparator(multiple));
    }

    private static String getRegularSeparator(Multiple multiple) {
        return Optional
                .ofNullable(multiple.separator())
                .orElse(", ");
    }

    private String applyTwoElementModification(String separatorFinal) {
        if (separatorFinal.equals(", and ")) {
            return " and ";
        }
        return separatorFinal;
    }

    private static Integer getRandomAmount(Multiple multiple) {
        Multiple internalMultiple = Optional
                .ofNullable(multiple)
                .orElse(getOnceInstance());
        Optional<Integer> min = Optional.ofNullable(internalMultiple.min());
        Optional<Integer> max = Optional.ofNullable(internalMultiple.max());
        if (min.isPresent()) {
            int minValue = min.get();
            return max
                    .map(maxValue -> minValue + (int) (Math.random() * (maxValue - minValue + 1)))
                    .orElseGet(min::get);
        }
        return max.orElse(1);
    }

    private static Collector<String, ?, String> buildJoinMultipleCollector(Multiple multiple) {
        Multiple internalMultiple = Optional.ofNullable(multiple).orElse(getOnceInstance());
        return Collectors.collectingAndThen(
                Collectors.toList(),
                l -> {
                    if (l.isEmpty()) return "";
                    if (l.size() == 1) return l.get(0);
                    if (l.size() == 2) return l.get(0) + getTwoValueSeparator(internalMultiple) + l.get(1);
                    return String.join(getRegularSeparator(internalMultiple), l.subList(0, l.size() - 1))
                            + getLastValueSeparator(internalMultiple) + l.getLast();
                });
    }
}
