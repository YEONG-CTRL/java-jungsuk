package ch14.Stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.partitioningBy;

class Student1 {
    String name;
    boolean isMale;
    int hak;
    int ban;
    int score;

    public Student1(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public boolean isMale() {
        return isMale;
    }
    public int getHak() {
        return hak;
    }
    public int getBan() {
        return ban;
    }
    public int getScore() {
        return score;
    }

    public String toString() {
        return String.format("[%s, %s, %d학년, %d반, %3d점]", name, isMale ? "남" : "여", hak, ban, score);
    }

    enum Level {HIGH, MID, LOW}
}

public class StreamEx7 {
    public static void main(String[] args) {

        Student1[] stuArr = {
                new Student1("이자바", true,3, 1 ,300),
                new Student1("김자바", false, 1, 2,200),
                new Student1("안자바", false,2, 3,100),
                new Student1("박자바", false,2, 4,150),
                new Student1("소자바", true,1, 5,200),
                new Student1("나자바", true, 3, 6,290),
                new Student1("감자바", false,3,7, 180)
        };

        System.out.printf("1. 단순분할 (성별로 분할) %n");
        Map<Boolean, List<Student1>> stuBySex = Stream.of(stuArr).collect(partitioningBy(Student1::isMale));

        List<Student1> maleStudent = stuBySex.get(true);
        List<Student1> femaleStudent = stuBySex.get(false);

        for(Student1 s : maleStudent) System.out.println(s);
        for(Student1 s : femaleStudent) System.out.println(s);

        System.out.printf("%n2. 단순분할 + 통계(성별 학생수) %n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr).collect(partitioningBy(Student1::isMale, counting()));

        System.out.println("남학생 수 : " + stuNumBySex.get(true));
        System.out.println("여학생 수 : " + stuNumBySex.get(false));

        System.out.printf("%n3. 단순분할 + 통계(성별 1등) %n");
        Map<Boolean, Optional<Student1>> topScoreBySex = Stream.of(stuArr).collect(partitioningBy(Student1::isMale, maxBy(Comparator.comparingInt(Student1::getScore))));

        System.out.println("남학생 1등 : " + topScoreBySex.get(true));
        System.out.println("여학생 1등 : " + topScoreBySex.get(false));
        Map<Boolean, Student1> topScoreBySex2 = Stream.of(stuArr).collect(partitioningBy(Student1::isMale, collectingAndThen(maxBy(Comparator.comparingInt(Student1::getScore)), Optional::get))
        );

        System.out.println("남학생 1등 : " + topScoreBySex2.get(true));
        System.out.println("여학생 1등 : " + topScoreBySex2.get(false));

    }
}
