package ch14;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx5 {
    public static void main(String[] args) {
        Supplier<Integer> s = () -> (int) (Math.random() * 100) + 1; // Supplier는 매개변수 없이 반환값만 있다
        Consumer<Integer> c = i -> System.out.print(i + ", "); // Consumer는 매개변수만 있고 반환값은 없다
        Predicate<Integer> p = i -> i %2 ==0; // Predicate는 하나의 매개변수를 받아서 boolean리턴, 조건식을 표현할때 사용
        Function<Integer, Integer> f = i -> i/10*10; // Function은 하나의 매개변수, 하나의 결과 반환

        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);
        printEvenNum(p,c,list);
        List<Integer> newList = doSomething(f,list);
        System.out.println(newList);
    }

    static <T> List<T> doSomething(Function<T,T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());

        for(T i : list) {
            newList.add(f.apply(i)); // apply도
        }
        return newList;
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c , List<T> list) {
        System.out.print("[");
        for(T i : list) {
            if (p.test(i)) // test도
                c.accept(i); // acceppt도 그냥 각각 함수형인터페이스를 구현한 람다식을 실행하는 메서드인듯
        }
        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i =0; i<10; i++) {
            list.add(s.get());
        }
    }
}
