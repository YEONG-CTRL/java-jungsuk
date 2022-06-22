package ch11.Arrays;

import java.util.*;

public class ArraysEx {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4};
        int[][] arr2d = {{11, 12, 13}, {21, 22, 23}};

        System.out.println("arr=" + Arrays.toString(arr));
        System.out.println("arr2D=" + Arrays.deepToString(arr2d));

        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, 3);
        int[] arr4 = Arrays.copyOf(arr, 7); // 빈자리에 0이 들어간다
        int[] arr5 = Arrays.copyOfRange(arr, 2,4);
        int[] arr6 = Arrays.copyOfRange(arr, 0, 7);

        System.out.println("arr2="+Arrays.toString(arr2));
        System.out.println("arr3="+Arrays.toString(arr3));
        System.out.println("arr4="+Arrays.toString(arr4));
        System.out.println("arr5="+Arrays.toString(arr5));
        System.out.println("arr6="+Arrays.toString(arr6));

        int[] arr7 = new int[5];
        Arrays.fill(arr7, 9); //9로 배열을 채운다
        System.out.println("arr=7" + Arrays.toString(arr7));

        for(int i : arr7) { // 9~9
            char[] graph = new char[i]; // char배열
            Arrays.fill(graph, '*'); // *로 채운다
            System.out.println(new String(graph) + i);
        }

        String[][] str2D = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};
        String[][] str2D2 = new String[][]{{"aaa", "bbb"}, {"AAA", "BBB"}};

        System.out.println(Arrays.equals(str2D, str2D2)); // 배열에 저장된 주소를 비교
        System.out.println(Arrays.deepEquals(str2D, str2D2));

        char[] chArr = {'A','D','C','B','E'} ;

        System.out.println("chArr=" + Arrays.toString(chArr));
        System.out.println("index of B=" + Arrays.binarySearch(chArr, 'B')); // 정렬하지 않으면 잘못된 결과가 나온다
        System.out.println("= After sorting =");
        Arrays.sort(chArr);
        System.out.println("chArr=" + Arrays.toString(chArr));
        System.out.println("index of B="+Arrays.binarySearch(chArr,'B'));
    }
}


