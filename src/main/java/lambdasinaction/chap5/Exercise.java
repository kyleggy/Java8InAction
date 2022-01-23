package lambdasinaction.chap5;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Exercise {

    //Quiz 5.2: Mapping
    @Test
    public void testMap() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares =
                numbers.stream()
                        .map(n -> n * n)
                        .collect(toList());
        System.out.println(squares);
    }

    @Test
    public void testFlatMap() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        for (int i = 0; i < pairs.size(); i ++) {
            System.out.println(pairs.get(i)[0] + "," + pairs.get(i)[1]);
            System.out.println("---");
        }

        List<Stream<int[]>> mapPairs =
                numbers1.stream()
                        .map(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        List<Integer> numbers11 = Arrays.asList(1, 2, 3);
        List<Integer> numbers12 = Arrays.asList(3, 4);
        List<int[]> pairs1 =
                numbers11.stream()
                .flatMap(i ->
                        numbers12.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                )
                .collect(toList());
    }

    @Test
    public void findFirst() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }


    @Test
    public void StringNull() {
        String a = null;
        System.out.println((String) a);
    }
}
