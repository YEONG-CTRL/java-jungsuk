package ch14.Stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        };

        Stream<Student> stuStream = Stream.of(stuArr);

        stuStream.sorted(Comparator.comparing(Student::getBan)
                .thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        stuStream = Stream.of(stuArr); // 스트림 재생성
        IntStream stuScoreStream = stuStream.mapToInt(Student::getTotalScore); // IntStream으로 변경

        IntSummaryStatistics stat = stuScoreStream.summaryStatistics(); // 통계를 만드는 스트림과 최종연산 제공해주는 메서드
        System.out.println("count = " + stat.getCount());
        System.out.println("sum = " + stat.getSum());
        System.out.println("average = %.2f%n" + stat.getAverage());
        System.out.println("min = " + stat.getMin());
        System.out.println("max = " + stat.getMax());
    }
}

