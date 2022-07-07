package 연습문제.ch5;

import java.util.Arrays;

public class Exercise {}

/*  1번
a. int[] arr[]; 문제없다 2차원배열
b. int[] arr = {1,2,3,}; => 마지막의 쉼표는 있어도 상관없음. 문제없다
c. 문제없음
d. int[] arr = new int[5]{1,2,3,4,5}; => { } 안의 데이터의 개수에 따라 자동적으로 배열의 크기가 결정되기에 []안에 숫자 넣으면 안된다
e. int arr[5] => 배열을 선언할때는 배열의 크기를 지정할 수 없다 "=" 우변에서 생성할때 크기 지정
f. int[] arr[] = new int[3][]; 문제없다.
 */

// 2번 : 2


class Exercise3 {
    public static void main(String[] args)
    {
        int[] arr = {10, 20, 30, 40, 50};
        int sum = 0;

        for (int i : arr)
            sum += i;

        System.out.println("sum="+sum);
    }
}

class Exercise4
{
    public static void main(String[] args)
    {
        int[][] arr = {
                { 5, 5, 5, 5, 5},
                {10,10,10,10,10},
                {20,20,20,20,20},
                {30,30,30,30,30}
        };
        int total = 0;
        float average = 0;

        int len = 0;
        for (int i = 0; i< arr.length; i++){
            for (int j = 0; j<arr[i].length; j++) {
                total += arr[i][j];
            }
        }

        average = total / (float) (arr.length*arr[0].length);

        System.out.println("total="+total);
        System.out.println("average="+average);
    } // end of main
} // end of class


class Exercise5 {
    public static void main(String[] args) {
        int[] ballArr = {1,2,3,4,5,6,7,8,9};
        int[] ball3 = new int[3];
// 배열 ballArr의 임의의 요소를 골라서 위치를 바꾼다.
        for(int i=0; i< ballArr.length;i++) {
            int j = (int)(Math.random() * ballArr.length);
            int tmp = 0;

            tmp = ballArr[0];
            ballArr[0] = ballArr[j];
            ballArr[j] = tmp;
        }

// 배열 ballArr의 앞에서 3개의 수를 배열 ball3로 복사한다.
        System.arraycopy(ballArr, 0, ball3,0,3); // ballArr의 0번째 인덱스 - ball3의 0번째 인덱스 - 3만큼 복사
//        for (int i=0; i<3; i++){
//            ball3[i] = ballArr[i];
//        }

        for(int i=0;i<ball3.length;i++) {
            System.out.print(ball3[i]);
        }
    } // end of main
} // end of class