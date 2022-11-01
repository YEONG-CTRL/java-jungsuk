package ch14.Stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class StreamEx8 {
    public static void main(String[] args) {
        Student1[] stuArr = {
                new Student1("이자바", true,3, 1 ,300),
                new Student1("김자바", false, 1, 2,300),
                new Student1("안자바", false,2, 2,100),
                new Student1("박자바", false,2, 2,150),
                new Student1("소자바", true,1, 3,180),
                new Student1("나자바", true, 3, 1,290),
                new Student1("감자바", false,3,2, 180)
        };

        System.out.printf(" 1. 단순그룹화(성적별로 그룹화)%n ");
        Map<Integer, List<Student1>> stuByBan = Stream.of(stuArr).collect(groupingBy(Student1::getBan));

        for(List<Student1> ban : stuByBan.values()) {
            for(Student1 s : ban) {
                System.out.println(s);
            }
        }

        System.out.printf("%n2. 단순그룹화(성적별로 그룹화)%n ");
        Map<Student1.Level , List<Student1>> stuByLevel = Stream.of(stuArr).collect(groupingBy(s -> {
                if (s.getScore() >= 200) return Student1.Level.HIGH;
                else if (s.getScore() >= 100) return Student1.Level.MID;
                else return Student1.Level.LOW; }
                ));

        TreeSet<Student1.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student1.Level key : keySet) {
            System.out.println("[" + key + "]");

            for(Student1 s : stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }

        System.out.printf("%n3. 단순그룹화 + 통계 (성적별 학생수 ) %n ");
        Map<Student1.Level , Long> stucntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if(s.getScore() >= 200) return Student1.Level.HIGH;
                    else if (s.getScore() >= 100) return Student1.Level.MID;
                    else return Student1.Level.LOW;
                }, counting()));

        for(Student1.Level key : stucntByLevel.keySet())
            System.out.printf("[%s]- %d명, " , key, stucntByLevel.get(key));
        System.out.println();

        System.out.printf("%n4. 다중그룹화(학년별, 반별) ");
        Map<Integer, Map<Integer, List<Student1>>> stuByHakAndBan =
                Stream.of(stuArr)
                        .collect(groupingBy(Student1::getHak,
                                groupingBy(Student1::getBan)));

        for(Map<Integer, List<Student1>> hak : stuByHakAndBan.values()) {
            for(List<Student1> ban : hak.values()) {
                System.out.println();
                for(Student1 s : ban)
                    System.out.println(s);
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n ");
        Map<Integer, Map<Integer, Student1>> topstuByHakAndBan =
                Stream.of(stuArr)
                        .collect(groupingBy(Student1::getHak,
                                 groupingBy(Student1::getBan,
                                        collectingAndThen(
                                                maxBy(comparingInt(Student1::getScore))
                                                , Optional::get
                                        ))));

        for(Map<Integer, Student1> ban : topstuByHakAndBan.values())
            for (Student1 s : ban.values())
                System.out.println(s);
    }
}
