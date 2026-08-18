package com.jandcode.mycv.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SheetParserUtils {

    private static final String SEPARATOR_ARRAY  = ",";
    private static final String SEPARATOR_OBJECT = "\\|";
    private static final String SEPARATOR_FIELD  = "~";

    private SheetParserUtils() {}

    // ─── Array simple: "a, b, c" → ["a", "b", "c"] ──────────────────────────
    public static List<String> parseSimpleList(String raw) {
        return Arrays.stream(raw.split(SEPARATOR_ARRAY))
                .map(String::trim)
                .toList();
    }

    // ─── Objeto plano: "val1 | val2 | val3" → {key1: val1, key2: val2, ...} ─
    public static Map<String, String> parseMap(String raw, String... keys) {
        String[] parts = raw.split(SEPARATOR_OBJECT);
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], i < parts.length ? parts[i].trim() : "");
        }
        return map;
    }

    // ─── Lista de objetos: "t1~d1 | t2~d2" → [{title,description}, ...] ─────
    public static List<Map<String, String>> parseObjectList(String raw, String... keys) {
        String[] items = raw.split(SEPARATOR_OBJECT);
        List<Map<String, String>> list = new ArrayList<>();
        for (String item : items) {
            String[] fields = item.trim().split(SEPARATOR_FIELD);
            Map<String, String> map = new LinkedHashMap<>();
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], i < fields.length ? fields[i].trim() : "");
            }
            list.add(map);
        }
        return list;
    }

    // ─── Languages: "java~9~0~% | js~8~5~%" → [{title, score, value}, ...] ──
    public static List<Map<String, Object>> parseLanguages(String raw) {
        String[] items = raw.split(SEPARATOR_OBJECT);
        List<Map<String, Object>> list = new ArrayList<>();
        for (String item : items) {
            String[] fields = item.trim().split(SEPARATOR_FIELD);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("title", fields.length > 0 ? fields[0].trim() : "");
            map.put("score", parseScore(fields));
            map.put("value", fields.length > 3 ? fields[3].trim() : "");
            list.add(map);
        }
        return list;
    }

    // ─── Score helper: ["java","9","0","%"] → [9, 0] ────────────────────────
    private static List<Integer> parseScore(String[] fields) {
        List<Integer> score = new ArrayList<>();
        try {
            if (fields.length > 1) score.add(Integer.parseInt(fields[1].trim()));
            if (fields.length > 2) score.add(Integer.parseInt(fields[2].trim()));
        } catch (NumberFormatException e) {
            score.add(0);
            score.add(0);
        }
        return score;
    }
}