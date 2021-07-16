package lambdasinaction.chap5;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

public class FlatMappingSample {

    @Test
    public void test() {
        Optional<String> s = Optional.of("test");
        assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    @Test
    public void streamTest() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        // array to a stream
        Stream<String[]> stream1 = Arrays.stream(array);

        // x is a String[]
        List<String[]> result = stream1
                .filter(x -> {
                    for(String s : x){      // really?
                        if(s.equals("a")){
                            return false;
                        }
                    }
                    return true;
                }).collect(Collectors.toList());

        // print array
        result.forEach(x -> System.out.println(Arrays.toString(x)));
    }

    @Test
    public void StreamNothing() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        // convert array to a stream
        Stream<String[]> stream1 = Arrays.stream(array);

        List<String[]> result = stream1
                .filter(x -> !x.equals("a"))      // x is a String[], not String!
                .collect(Collectors.toList());

        System.out.println(result.size());    // 0

        result.forEach(System.out::println);  // print nothing?
    }

    @Test
    public void flatMapTest() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        // Java 8
        String[] result = Stream.of(array)  // Stream<String[]>
                .flatMap(Stream::of)        // Stream<String>
                .toArray(String[]::new);    // [a, b, c, d, e, f]

        for (String s : result) {
            System.out.println(s);
        }

        String[][] array02 = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        List<String> collect = Stream.of(array02)     // Stream<String[]>
                .flatMap(Stream::of)                // Stream<String>
                .filter(x -> !"a".equals(x))        // filter out the a
                .collect(Collectors.toList());      // return a List

        collect.forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        // Java 8
        String[] result = Stream.of(array)  // Stream<String[]>
                .flatMap(Stream::of)        // Stream<String>
                .toArray(String[]::new);    // [a, b, c, d, e, f]

        for (String s : result) {
            System.out.println(s);
        }
    }


}
