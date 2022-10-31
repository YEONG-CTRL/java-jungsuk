package ch14.Stream;

import java.io.File;
import java.util.stream.Stream;

public class StreamEx2 {
    public static void main(String[] args) {
        File[] fileArr = {new File("Ex1.java"), new File("Ex2.py")
                , new File("Ex2.java"), new File("Ex1"), new File("Ex1.txt")
        };

        Stream<File> fileStream =Stream.of(fileArr);

//        Stream<String> filenameStream = fileStream.map(File::getName);
//        filenameStream.forEach(System.out::println);

        fileStream = Stream.of(fileArr);

        fileStream.map(File::getName)
                .filter(s -> s.indexOf('.') != -1)
                .map(s -> s.substring(s.indexOf('.') + 1))
                .peek(s -> System.out.printf("확장자 = %s || ", s)) // 중간연산이기에 forEach실행될때 함께 실행
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::print);

        System.out.println();
    }
}
