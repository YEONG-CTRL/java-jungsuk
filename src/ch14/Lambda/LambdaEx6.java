package ch14.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/* 5번예제를 래퍼클래스가 아닌 기본형을 사용하는 함수형 인터페이스를 사용하여 바꿔줌 */

public class LambdaEx6 {
    public static void main(String[] args) {
        IntSupplier s = () -> (int) (Math.random() * 100) + 1;
        IntConsumer c = i -> System.out.print(i + ", ");
        IntPredicate p = i -> i %2 ==0;
        IntUnaryOperator op = i -> i/10*10; // 여기에 조상인 Function을 사용하면 에러가 발생한다, i의 타입을 알 수 없기때문
        // Function쓸거면 Function<Integer,Integer> 이렇게 타입을 지정해주어야 되는데, 그럴빠에 기본형 사용한다 이거임

        int[] arr = new int[10];

        makeRandomList(s, arr);
        System.out.println(Arrays.toString(arr));
        printEvenNum(p,c, arr);
        int[] newArr = doSomething(op, arr);
        System.out.println(Arrays.toString(newArr));
    }

    static int[] doSomething(IntUnaryOperator op, int[] arr) {
        int[] newArr = new int[arr.length];

        for(int i = 0; i < newArr.length; i++) {
            newArr[i] = op.applyAsInt(arr[i]);
        }
        return newArr;
    }

    static void printEvenNum(IntPredicate p , IntConsumer c, int[] arr) {
        System.out.print("[");
        for(int i : arr) {
            if (p.test(i)) // test도
                c.accept(i); // acceppt도 그냥 각각 함수형인터페이스를 구현한 람다식을 실행하는 메서드인듯
        }
        System.out.println("]");
    }

    static void makeRandomList(IntSupplier s, int[] arr) {
        for(int i =0; i<arr.length ; i++) {
            arr[i] = s.getAsInt();
        }
    }
}
