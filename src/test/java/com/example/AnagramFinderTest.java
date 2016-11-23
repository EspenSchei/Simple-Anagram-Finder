package com.example;


import static java.util.stream.Collectors.joining;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import static com.example.AnagramFinder.orderString;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class AnagramFinderTest {
  final String file = "anagrams.txt";

  @Test
  public void shouldFindAnagrams() throws Exception {
    final List<String> words = readWordsFromFile();

    final HashMap<String, Set<String>> anagrams = AnagramFinder.findAnagrams(words);

    assertThat(anagrams.size(), is(4));
    assertThat(anagrams.get(orderString("test")), contains("test", "tset"));
    assertThat(anagrams.get(orderString("fisk")), contains("kisf", "fisk"));
    assertThat(anagrams.get(orderString("duplicate")), hasSize(1));
    assertThat(anagrams.get(orderString("random")), hasSize(1));
  }

  /**
   * Visual verification
   */
  @Test
  public void printAnagrams() throws Exception {
    AnagramFinder.findAnagrams(readWordsFromFile()).forEach((key, value) ->
        System.out.println(value.stream().collect(joining(", ")))
    );
  }

  public List<String> readWordsFromFile() throws Exception {
    return Files
        .lines(Paths.get(getClass().getClassLoader().getResource(file).toURI()))
        .collect(Collectors.toList());
  }
}