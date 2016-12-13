package com.example;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class AnagramFinder {

  public static HashMap<String, Set<String>> findAnagrams(List<String> words) {
    final HashMap<String, Set<String>> anagrams = new HashMap<>();

    words.forEach(word -> {
      String key = orderString(word);

      if (!anagrams.containsKey(key)) {
        anagrams.put(key, new HashSet<>());
      }

      anagrams.get(key).add(word);
    });

    return anagrams;
  }

  protected static String orderString(String word) {
    return Stream.of(word.split("")).sorted().collect(joining());
  }
}
