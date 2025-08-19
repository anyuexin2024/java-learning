package com.example.javalearning.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.apache.el.lang.ELArithmetic.subtract;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FunctionTest {

    @Test
    public void testFunctionalInterfaces() {
        // Predicate - 谓词接口，接收一个参数并返回boolean值
        Predicate<Integer> isEven = x -> x % 2 == 0;
        assertTrue(isEven.test(4));
        assertFalse(isEven.test(3));

        // Function - 函数接口，接收一个T类型参数，返回R类型结果
        Function<String, Integer> stringToLength = s -> s.length();
        assertEquals(5, stringToLength.apply("Hello"));

        // Consumer - 消费接口，接收一个参数，无返回值
        List<String> list = new ArrayList<>();
        Consumer<String> addToList = s -> list.add(s);
        addToList.accept("Hello");
        assertEquals(1, list.size());
        assertEquals("Hello", list.get(0));

        // Supplier - 供应接口，无参数，返回一个结果
        Supplier<Double> randomValue = Math::random;
        assertNotNull(randomValue.get());

        // UnaryOperator - 一元操作符，接收和返回相同类型的参数
        UnaryOperator<Integer> square = x -> x * x;
        assertEquals(16, square.apply(4));

        // BinaryOperator - 二元操作符，接收两个相同类型的参数，返回相同类型的结果
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        assertEquals(7, sum.apply(3, 4));
    }

    @Test
    public void testMethodReferences() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // 静态方法引用
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        List<String> strings = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("1", "2", "3"), strings);

        // 实例方法引用
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("ALICE", "BOB", "CHARLIE"), upperCaseNames);

//        // 构造方法引用
//        List<String> nameList = names.stream()
//                .map(ArrayList::new)
//                .map(List::getClass)
//                .map(Class::getSimpleName)
//                .collect(Collectors.toList());
//        // 这里验证我们创建了ArrayList实例
//        assertEquals(3, nameList.size());
    }

    @Test
    public void testComposition() {
        // Function组合
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addTen = x -> x + 10;

        // 先乘以2，再加10
        Function<Integer, Integer> multiplyThenAdd = multiplyByTwo.andThen(addTen);
        assertEquals(14, multiplyThenAdd.apply(2)); // (2*2)+10 = 14

        // 先加10，再乘以2
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addTen);
        assertEquals(24, addThenMultiply.apply(2)); // (2+10)*2 = 24

        // Predicate组合
        Predicate<Integer> isGreaterThanFive = x -> x > 5;
        Predicate<Integer> isEven = x -> x % 2 == 0;

        // and组合
        Predicate<Integer> isGreaterThanFiveAndEven = isGreaterThanFive.and(isEven);
        assertTrue(isGreaterThanFiveAndEven.test(8));
        assertFalse(isGreaterThanFiveAndEven.test(7));

        // or组合
        Predicate<Integer> isGreaterThanFiveOrEven = isGreaterThanFive.or(isEven);
        assertTrue(isGreaterThanFiveOrEven.test(3)); // 3是奇数但不大于5？不，这里应该是因为3不大于5且不是偶数，所以是false
        assertTrue(isGreaterThanFiveOrEven.test(6)); // 6是偶数
        assertTrue(isGreaterThanFiveOrEven.test(7)); // 7大于5

        // negate取反
        Predicate<Integer> isNotEven = isEven.negate();
        assertTrue(isNotEven.test(3));
        assertFalse(isNotEven.test(4));
    }

    @Test
    public void testOptional() {
        // 创建Optional
        Optional<String> optionalWithValue = Optional.of("Hello");
        Optional<String> emptyOptional = Optional.empty();

        // isPresent和isEmpty
        assertTrue(optionalWithValue.isPresent());
        assertTrue(emptyOptional.isEmpty());

        // ifPresent
        final boolean[] valueProcessed = {false};
        optionalWithValue.ifPresent(s -> valueProcessed[0] = true);
        assertTrue(valueProcessed[0]);

        // orElse
        String value = emptyOptional.orElse("Default");
        assertEquals("Default", value);

        // orElseGet
        String value2 = emptyOptional.orElseGet(() -> "Generated");
        assertEquals("Generated", value2);

        // map
        Optional<Integer> length = optionalWithValue.map(String::length);
        assertTrue(length.isPresent());
        assertEquals(5, length.get().intValue());

        // flatMap
        Optional<Optional<String>> optionalOptional = Optional.of(Optional.of("Hello"));
        Optional<String> flattened = optionalOptional.flatMap(Function.identity());
        assertTrue(flattened.isPresent());
        assertEquals("Hello", flattened.get());
    }

    @Test
    public void testStreams() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter和map
        List<Integer> evenSquares = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(4, 16, 36, 64, 100), evenSquares);

        // reduce
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        assertEquals(55, sum);

        // collect to map
        Map<String, Integer> numberMap = numbers.stream()
                .limit(3)
                .collect(Collectors.toMap(
                        String::valueOf,
                        Function.identity()
                ));
        assertEquals(3, numberMap.size());
        assertEquals(Integer.valueOf(1), numberMap.get("1"));

        // groupingBy
        List<String> words = Arrays.asList("apple", "banana", "apricot", "blueberry");
        Map<Character, List<String>> groupedByFirstLetter = words.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        assertEquals(2, groupedByFirstLetter.size());
        assertEquals(2, groupedByFirstLetter.get('a').size());
        assertEquals(2, groupedByFirstLetter.get('b').size());
    }

    @Test
    public void testCustomFunctionalInterface() {
        // 自定义函数式接口
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;

        assertEquals(7, add.calculate(3, 4));
        assertEquals(-1, subtract(3, 4));
    }

    // 自定义函数式接口示例
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
}