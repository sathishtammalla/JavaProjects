package com.sathish.learn;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordProcessor {
	public static void main(String args[]) throws IOException {
		WordProcessor wp = new WordProcessor();
		System.out.println(System.getProperty("java.class.path"));
		Path currentDir = Paths.get(".");
		System.out.println(currentDir.toAbsolutePath());
		wp.process();
		
		Map<String, Long> wordCount = wp.countWithStream();
		
		System.out.println("Printing from Streams....");	
		wordCount.forEach((k,v)->System.out.printf("Word : %s Count : %d %n",k,v));
		
	}
	
	public void process() throws IOException {
		System.out.println("I am pinging from process method");
		Map<String,Integer> wordMap = countTheOldWay();
		
		wordMap.forEach((k,v)->System.out.printf("Word : %s Count : %d %n",k,v));
		
		
	}
	
	public Map<String, Integer> countTheOldWay() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("TaleOfTwoCities.txt"));
		Map<String, Integer> result = new HashMap<>();
		for(String line : lines) {
			if(line.length() > 0) { //noempty lines
				String [] words = line.split("\\W");
				for(String word : words) {
					Integer count = result.get(word);
					if(count == null) {
						count = new Integer(1);
					}
					else {
						count = new Integer(count.intValue() + 1);
					}
					result.put(word, count);
				}
			}
		}
		
		return result;
	}
	
	public Map<String,Long> countWithStream() throws IOException {
		//List<String> lines = Files.readAllLines(Paths.get("TaleOfTwoCities.txt"));
		
		Stream<String> stream = Files.lines(Paths.get("TaleOfTwoCities.txt"));
		
		
		Map<String,Long> result = new HashMap<>();
		
		//stream.forEach(System.out::println);
		
		result = stream.flatMap(w -> Arrays.stream(w.split("\\W")))
										.collect(Collectors.groupingBy(Function.identity(),() -> new TreeMap<>(),Collectors.counting()));
		
		/*
		
		 Path path = Paths.get("TaleOfTwoCities.txt");
		 Map<String, Long> wordCount = Files.lines(path).flatMap(line -> Arrays.stream(line.split("\\W")))
		            .map(word -> new SimpleEntry<>(word, 1))
		            .collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
		 
		 Map<String, Long> wordCount2 = Files.lines(path).flatMap(line -> Arrays.stream(line.split("\\W")))
		           // .map(word -> new SimpleEntry<>(word, 1))
		            //.collect(Collectors.groupingBy(SimpleEntry::getKey, Collectors.counting()));
		 			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		//return wordCount2;
		// stream.close();
		 * 
		 */
		return result;
		
	}

}

