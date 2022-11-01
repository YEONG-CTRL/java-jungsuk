package ch14;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorEx {
    public static void main(String[] args) {
        String[] strArr = {"aaa", "bbb", "ccc" };
        Stream<String> strSream = Stream.of(strArr);

        String result = strSream.collect(new ConcatCollector());

        System.out.println(Arrays.toString(strArr));
        System.out.println("result = " + result);
    }
}

class ConcatCollector implements Collector<String, StringBuilder, String> {
    @Override
    public Supplier<StringBuilder> supplier() { /* 작업 결과 저장할 공간 */
        return () -> new StringBuilder();
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() { /* 스트림 요소를 수집할 방법 */
        return (sb, s) -> sb.append(s);
    }

    @Override
    public Function<StringBuilder, String> finisher() { /* 결과를 최종적으로 변환할 방법 */
        return StringBuilder::toString;
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() { /* 두 저장공간 병합할 방법 (병렬 스트림의 경우) */
        return StringBuilder::append;
        // return (sb1,sb2) -> sb1.append(sb2)
    }

    @Override
    public Set<Characteristics> characteristics() { /* 컬렉터가 수행하는 작업의 속성 */
        return Collections.emptySet();
    }
}