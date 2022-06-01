package ch5;

import java.util.Arrays;

public class array {
    public static void main(String[] args) {
        int[] score = new int[5];
        int k = 1;

        score[0] = 50;
        score[1] = 60;
        score[k+1] = 70;
        score[3] = 80;
        score[4] = 90;

        int tmp = score[k+2] + score[4];

        for(int i=0; i <5 ; i++) {
            System.out.printf("score[%d] : %d%n", i, score[i]);
        }
        System.out.printf("tmp:%d%n", tmp);

        System.out.printf("score[%d] : %d%n",7, score[7]); // ArrayIndexOutOfBounds
    }
}

class ArrayEx2 {
    public static void main(String[] args) {
        int[] iArr1 = new int[10];
        int[] iArr2 = new int[10];
//        int[] iArr3 = new int[10];
        int[] iArr3 = {100,95,80,70,60};
        char[] chArr = {'a','b','c','d'};

    for (int i=0; i < iArr1.length; i++) {
        iArr1[i] = i +1; // 1~10까지 배열에
    }

    for (int i=0; i < iArr2.length; i++) {
        iArr2[i] = (int) (Math.random()*10) + i; // 1~10 랜덤으로 배열에 저장
    }

    for (int i=0; i < iArr1.length; i++) {
        System.out.print(iArr1[i] + ","); // iArr1 요소들 출력
    }

    System.out.println();
    System.out.println(Arrays.toString(iArr2)); // iArr2의 요소들 출력
    System.out.println(Arrays.toString(iArr3)); 
    System.out.println(Arrays.toString(chArr));
    System.out.println(iArr3); // iArr3 참조변수의 타입과, 배열의 내부 주소 출력
    System.out.println(chArr); // println메서드는 char배열은 그냥 abcd 이렇게 출력함
    }
}

class ArrayEx3 {
    public static void main(String[] args) {
        int[] arr = new int[5];

        for (int i=0; i < arr.length ; i++)
            arr[i] = i + 1;

        System.out.println("[변경전]");
        System.out.println("arr.length:" + arr.length);
        for(int i=0; i < arr.length;i++)
            System.out.println("arr[" +i+ "]:" +arr[i]);

        int[] tmp = new int[arr.length*2]; // 기존 두배 길이의 배열 생성

        for(int i=0; i< arr.length; i++)
            tmp[i] = arr[i]; // tmp에 값 넣어줌

        arr = tmp;

        System.out.println("[변경후]");
        System.out.println("arr.length:" + arr.length);
        for(int i=0; i < arr.length;i++)
            System.out.println("arr[" +i+ "]:" +arr[i]);
    }
}

class ArrayEx4 {
    public static void main(String[] args) {
        char[] abc = {'A','B','C','D'};
        char[] num = {'0','1','2','3','4','5','6','7','8','9'};
        System.out.println(abc);
        System.out.println(num);

        // abc와 num을 붙인 배열 result
        char[] result = new char[abc.length+num.length];
        System.arraycopy(abc, 0 , result, 0 , abc.length);
        System.arraycopy(num, 0 , result, abc.length , num.length);
        System.out.println(result);

        // abc배열을 num으로 4만큼 복사(0~3)
        System.arraycopy(abc, 0, num, 0, abc.length);
        System.out.println(num);

        // abc 배열을 6 인덱스부터 3길이만큼 또 복사
        System.arraycopy(abc, 0, num, 6, 3);
        System.out.println(num);
    }
}

class ArrayImplement1 { // 배열 모든 요소 더해서 총합과 평균 구하기
    public static void main(String[] args) {
        int sum = 0; // 총합 저장
        float average = 0f; // 평균 저장

        int[] score = {100,88,100,100,90};

        for (int i=0; i<score.length; i++) {
            sum += score[i];
        }

        average = sum / (float)score.length; // float으로 결과 얻기 위해 float으로 나눠주어 형변환

        System.out.println("총점 : " + sum);
        System.out.println("평균 : " + average);
    }
}

class ArrayImplement2 { // 배열의 요소 중 제일 큰 값과 제일 작은 값 찾기
    public static void main(String[] args) {
        int[] score = {79, 88, 91, 33, 100, 55, 95};

        int max = score[0];
        int min = score[0];

        for (int i=1; i < score.length; i++) { // 배열의 두번째 값부터 읽는다
            if (score[i] > max) {
                max = score[i];
            } else if (score[i] < min) {
                min = score[i];
            }
        }

        System.out.println("최대값 : " + max);
        System.out.println("최소값 : " + min);
    }
}

class ArrayImplement3 { // 배열의 요소의 순서를 반복해서 바꾼다(카드섞기)
    public static void main(String[] args) {
        int[] numArr = new int[10];

        for (int i=0; i< numArr.length; i++) {
            numArr[i] = i;
            System.out.print(numArr[i]);
        }
        System.out.println();

        for (int i=0; i < 100; i++) {
            int n = (int) (Math.random() * 10); // 0~9 사이의 임의의 한값
            int tmp = numArr[0]; // tmp는 numArr[0] 넣어주고
            numArr[0] = numArr[n]; // numArr[0] 값은 numArr[n]으로 값 바꿔줌
            numArr[n] = tmp;  // n번째는 아까 numArr[0] 저장해주었던 tmp 대입
            // numArr[0] 과 numArr[n] 값 바꿔준다
        }

        for (int i=0; i < numArr.length; i++) {
            System.out.print(numArr[i]);
        }
    }
}

class ArrayImplement4 { // 배열의 요소의 순서를 반복해서 바꾼다(로또번호)
    public static void main(String[] args) {
        int[] ball = new int[45];

        for(int i=0; i < ball.length; i++)
            ball[i] = i+1; // 1~45 배열에 저장

        int temp = 0;
        int j = 0;

        for (int i=0; i < 6; i++) { // 0~5까지 6개의 값만 바꿈
            j = (int) (Math.random() * 45); // 0~44 사이 임의 값
            temp = ball[i];
            ball[i] = ball[j];
            ball[j] = temp;
        }

        for (int i=0; i < 6; i++)
            System.out.printf("ball [%d]=%d%n", i , ball[i]);
    }
}

class ArrayImplement5 { //불연속적인 범위의 임의값으로 배열 채우기
    public static void main(String[] args) {
        int[] code = {-4, -1, 3 ,6 ,11}; // 불연속적인 값들로 채워진 code
        int[] arr = new int[10]; // 10만큼의 공간 가진 arr 생성

        for (int i=0; i< arr.length; i++) { // arr의 길이만큼 반복
            int tmp = (int) (Math.random() * code.length); // 0~4의 랜덤값 생성
            arr[i] = code[tmp]; // 해서, 이를 code의 인덱스로 활용하여 code에서 랜덤값 꺼내서 arr[1]에 넣는다
        }

        System.out.println(Arrays.toString(arr));
    }
}

class ArrayImplement6 { // 정렬하기 - bubble sort
    public static void main(String[] args) {
        int[] numArr = new int[10]; // 10 길이의 numArr 배열 생성

        for (int i=0; i < numArr.length; i++) {
            System.out.print(numArr[i] = (int) (Math.random() * 10));  // 0~9사이의 랜덤값으로 채움
        }
        System.out.println();

        for (int i=0; i < numArr.length-1; i++) { // 0~8만큼
            boolean changed = false;

            for (int j=0; j < numArr.length-1-i; j++) { // 원래는 배열길이 -1 번 비교해야하는데, 정렬진행하면서 큰 값이 오른쪽으로 가기에
                // 비교해야할 범위가 하나씩 줄어든다
                if (numArr[j] > numArr[j+1]) { // numArr의 j번째 값이 j+1번째 값보다 크다면
                    int temp = numArr[j]; 
                    numArr[j] = numArr[j+1];
                    numArr[j+1] = temp; // 둘이 자리바꿔주고 changed = true
                    changed = true;
                }
            }
            if (!changed) break; // 자리바꿈이 없으면 반복문(i)벗어난다

            for (int k=0; k<numArr.length; k++) 
                System.out.print(numArr[k]); // 정렬된 결과 출력
            System.out.println();
        }
    }
}

class ArrayImplement7 {
    public static void main(String[] args) {
        int[] numArr = new int[10];
        int[] counter = new int[10];

        for (int i=0; i < numArr.length; i++) {
            numArr[i] = (int) (Math.random() * 10);
            System.out.println(numArr[i]);
        }
        System.out.println();

        for (int i=0; i< numArr.length; i++) {
            counter[numArr[i]]++; // counter 배열의 numArr[i]번째 값을 1 증가시킨다
        }

        for (int i=0; i < numArr.length; i++) {
            System.out.println(i+"의 개수 :"+counter[i]);
        }
    }
}