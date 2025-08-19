package com.example.javalearning.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HelloTest {
    
    @Test
    public void testCreateStreams() {
        // 从集合创建Stream
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream1 = list.stream();
        assertEquals(3, stream1.count());
        
        // 从数组创建Stream
        String[] array = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(array);
        assertEquals(3, stream2.count());
        
        // 直接创建Stream
        Stream<String> stream3 = Stream.of("a", "b", "c");
        assertEquals(3, stream3.count());
        
        // 创建数值范围Stream
        IntStream intStream = IntStream.range(1, 5); // 1, 2, 3, 4
        assertEquals(4, intStream.count());
        
        // 创建无限流并限制
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2)
                                             .limit(5); // 0, 2, 4, 6, 8
        assertEquals(5, iterateStream.count());
    }
    
    @Test
    public void testIntermediateOperations() {
        List<String> strings = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
        
        // filter - 过滤
        List<String> filtered = strings.stream()
                                       .filter(s -> !s.isEmpty())
                                       .collect(Collectors.toList());
        assertEquals(4, filtered.size());
        assertFalse(filtered.contains(""));
        
        // map - 映射
        List<Integer> lengths = strings.stream()
                                       .map(String::length)
                                       .collect(Collectors.toList());
        assertEquals(strings.size(), lengths.size());
        assertTrue(lengths.contains(0)); // 空字符串长度为0
        assertTrue(lengths.contains(3)); // "abc", "bcd"长度为3
        
        // flatMap - 扁平化映射
        List<List<String>> lists = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d")
        );
        List<String> flatList = lists.stream()
                                     .flatMap(Collection::stream)
                                     .collect(Collectors.toList());
        assertEquals(4, flatList.size());
        assertTrue(flatList.containsAll(Arrays.asList("a", "b", "c", "d")));
        
        // distinct - 去重
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4);
        List<Integer> distinctNumbers = numbers.stream()
                                               .distinct()
                                               .collect(Collectors.toList());
        assertEquals(4, distinctNumbers.size());
        assertEquals(Arrays.asList(1, 2, 3, 4), distinctNumbers);
        
        // sorted - 排序
        List<Integer> sortedNumbers = numbers.stream()
                                             .sorted()
                                             .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 2, 3, 3, 4), sortedNumbers);
        
        // limit - 限制数量
        List<Integer> limitedNumbers = numbers.stream()
                                              .limit(3)
                                              .collect(Collectors.toList());
        assertEquals(3, limitedNumbers.size());
        assertEquals(Arrays.asList(1, 2, 2), limitedNumbers);
        
        // skip - 跳过元素
        List<Integer> skippedNumbers = numbers.stream()
                                              .skip(2)
                                              .collect(Collectors.toList());
        assertEquals(4, skippedNumbers.size());
        assertEquals(Arrays.asList(2, 3, 3, 4), skippedNumbers);
    }
    
    @Test
    public void testTerminalOperations() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // count - 计数
        long count = numbers.stream().count();
        assertEquals(5, count);
        
        // reduce - 归约
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        assertTrue(sum.isPresent());
        assertEquals(15, sum.get());
        
        // min, max - 最值
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        assertTrue(min.isPresent());
        assertTrue(max.isPresent());
        assertEquals(1, min.get());
        assertEquals(5, max.get());
        
        // anyMatch, allMatch, noneMatch - 匹配检查
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 3);
        boolean allMatch = numbers.stream().allMatch(n -> n > 0);
        boolean noneMatch = numbers.stream().noneMatch(n -> n < 0);
        assertTrue(anyMatch);
        assertTrue(allMatch);
        assertTrue(noneMatch);
        
        // findFirst, findAny - 查找
        Optional<Integer> first = numbers.stream().findFirst();
        Optional<Integer> any = numbers.stream().findAny();
        assertTrue(first.isPresent());
        assertTrue(any.isPresent());
        assertEquals(1, first.get());
    }
    
    @Test
    public void testCollectorOperations() {
        List<String> strings = Arrays.asList("a", "bb", "ccc", "dd", "e");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // toList, toSet
        List<String> list = strings.stream().collect(Collectors.toList());
        Set<String> set = strings.stream().collect(Collectors.toSet());
        assertEquals(strings.size(), list.size());
        assertEquals(strings.size(), set.size());
        
        // joining - 连接字符串
        String joined = strings.stream().collect(Collectors.joining(", "));
        assertFalse(joined.isEmpty());
        assertTrue(joined.contains(", "));
        
        // groupingBy - 分组
        Map<Integer, List<String>> groupedByLength = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        assertNotNull(groupedByLength);
        assertTrue(groupedByLength.size() > 0);
        
        // partitioningBy - 分区
        Map<Boolean, List<String>> partitioned = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 2));
        assertNotNull(partitioned);
        assertEquals(2, partitioned.size()); // true 和 false 两个分区
        
        // summarizing - 统计
        IntSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingInt(Integer::intValue));
        assertEquals(numbers.size(), stats.getCount());
        assertEquals(1, stats.getMin());
        assertEquals(5, stats.getMax());
        assertEquals(15, stats.getSum());
    }
    
    @Test
    public void testParallelStreams() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 顺序流
        List<Integer> sequentialResult = numbers.stream()
                .map(n -> n * n)
                .filter(n -> n > 10)
                .collect(Collectors.toList());
        
        // 并行流
        List<Integer> parallelResult = numbers.parallelStream()
                .map(n -> n * n)
                .filter(n -> n > 10)
                .collect(Collectors.toList());
        
        // 结果应该相同
        assertEquals(sequentialResult, parallelResult);
        assertEquals(7, sequentialResult.size()); // 4,5,6,7,8,9,10的平方都大于10
    }
}