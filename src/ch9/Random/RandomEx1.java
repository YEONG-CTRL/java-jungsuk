package ch9.Random;

import java.util.Arrays;
import java.util.Random;

public class RandomEx1 {
    public static void main(String[] args) {
        Random rand = new Random(1);
        Random rand2 = new Random(1); // 같은 seed사용하면  같은 값 같은 순서

        System.out.println("= rand = ");
        for(int i=0; i<5; i++)
            System.out.println(i + ":" + rand.nextInt());

        System.out.println();
        System.out.println("= rand2 = ");
        for(int i=0; i<5; i++)
            System.out.println(i + ":" + rand2.nextInt());
    }
}

class RandomEx2 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] number = new int[100];
        int[] counter = new int[10];

        for (int i = 0; i < number.length; i++) {
            System.out.print(number[i] = rand.nextInt(10));
        }
        System.out.println();

        for (int i=0; i< number.length; i++)
            counter[number[i]]++;

        for (int i=0; i< number.length; i++)
            System.out.println(i +"의 개수 :" + printGraph('#', counter[i]) + " " + counter[i]);
    }

    public static String printGraph(char ch, int value) {
        char[] bar = new char[value];

        for(int i=0; i < bar.length; i++)
            bar[i] = ch;

        return new String(bar);
    }
}

class RandomEx3 {
    public static void main(String[] args) {
        for (int i=0; i<10; i++)
            System.out.print(getRand(5,10)+", ");
        System.out.println();

        int[] result = fillRand(new int[10], new int[]{2,3,7,5});

        System.out.println(Arrays.toString(result));
    }

    public static int[] fillRand(int[] arr, int from, int to) {  // from to 범위의 값들로 arr채워서 반환
        for(int i=0; i < arr.length; i++)
            arr[i] = getRand(from,to);

        return arr;
    }

    public static int[] fillRand(int[] arr, int[] data) { // arr을 data에 있는 값들로 채워서 반환
        for(int i=0; i<arr.length; i++)
            arr[i] = data[getRand(0,data.length-1)];
        return arr;
    }

    public static int getRand(int from, int to) { // from to 범위의 정수 값을 반환한다
        return (int)(Math.random() * (Math.abs(to-from)+1)) + Math.min(from,to);
    }
}


