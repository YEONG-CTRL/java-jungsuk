package ch11;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetEx {
    public static void main(String[] args) {
        Set set = new TreeSet();

        for (int i=0; set.size() < 6; i++) {
            int num = (int)(Math.random()*45) + 1;
            set.add(num);
        }

        System.out.println(set); // 왼쪽 -> 오른쪽 순으로 정렬돼서 출력된다
    }
}

class TreeSetEx1 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();

        String from = "b";
        String to   = "d";

        set.add("abc");
        set.add("car");
        set.add("dance");
        set.add("elephant");
        set.add("flower");
        set.add("dZZZZ");

        System.out.println(set);
        System.out.println("range search : from " + from + " to " + to);
        System.out.println("result1 :  " + set.subSet(from,to)); // 시작범위(from)은 포함되지만 끝범위는 포함되지 않는다
        System.out.println("result2 :  " + set.subSet(from,to + "zzz")); // d로시작하는 단어중 dzzz 이전의 모든 단어가 포함된다
    }
}

class AsciiPrint {
    public static void main(String[] args) {
        char ch = ' ';

        for (int i=0; i<95;i++)
            System.out.println(ch++); // 공백 기호 숫자 대문자 소문자 순
    }
}

class TreeSetEx2 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        int[] score = {80, 95, 50, 35, 45, 65, 10, 100};

        for(int i=0; i< score.length; i++)
            set.add(new Integer(score[i]));

        System.out.println("50보다 작은 값 :" + set.headSet(new Integer((50))));// 50 왼쪽 밑의 값
        System.out.println("50보다 크거나 같은 값 :" + set.tailSet(new Integer((50)))); // 50 오른쪽의 값
    }
}

