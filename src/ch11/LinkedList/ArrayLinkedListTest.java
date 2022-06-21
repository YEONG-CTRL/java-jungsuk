package ch11.LinkedList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayLinkedListTest {
    public static void main(String[] args) {
        ArrayList al = new ArrayList(2000000);
        LinkedList ll = new LinkedList();

        System.out.println("= 순차적으로 추가하기 ="); // 순차적으로 추가/삭제하는 경우 ArrayList가 LinkedList보다 빠르다
        System.out.println("ArrayList : " + add1(al));
        System.out.println("LinkedList : " + add1(ll));
        System.out.println();

        System.out.println("= 중간에 추가하기 ="); // 중간에 추가/삭제하는 경우는 LinkedList가 ArrayList보다 빠르다
        System.out.println("ArrayList : " + add2(al));
        System.out.println("LinkedList : " + add2(ll));
        System.out.println();

        System.out.println("= 중간에서 삭제하기 =");
        System.out.println("ArrayList : " + remove2(al));
        System.out.println("LinkedList : " + remove2(ll));
        System.out.println();

        System.out.println("= 순차적으로 삭제하기 =");
        System.out.println("ArrayList : " + remove1(al));
        System.out.println("LinkedList : " + remove1(ll));
    }

    public static long add1(List list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) list.add(i + "");
        long end = System.currentTimeMillis();
        return end-start;
    }

    public static long add2 (List list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) list.add(500, "X");
        long end = System.currentTimeMillis();
        return end-start;
    }

    public static long remove1(List list) {
        long start = System.currentTimeMillis();
        for (int i = list.size() - 1; i >= 0; i--) list.remove(i);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long remove2(List list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i<10000; i++) list.remove(i);
        long end = System.currentTimeMillis();
        return end - start;
    }
}

class ArrayLinkedList2 {
    public static void main(String[] args) {
        ArrayList al = new ArrayList(1000000);
        LinkedList ll = new LinkedList();
        add(al);
        add(ll);

        System.out.println("접근시간 테스트");
        System.out.println("ArrayList :" + access(al));
        System.out.println("LinkedList :" + access(ll));
        // LInkedlist는 불연속적 요소들이 서로 연결된 것이라 처음부터 n번째까지 차례대로 따라가야해서 접근시간이 길어진다
    }

    public static void add(List list) {
        for (int i = 0; i < 100000; i++) list.add(i + "");
    }

    public static long access (List list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) list.get(i);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
