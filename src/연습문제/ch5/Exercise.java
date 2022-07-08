package 연습문제.ch5;

import java.util.Arrays;
import java.util.Scanner;

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

class Exercise6 {
    public static void main(String args[]) {
// . 큰 금액의 동전을 우선적으로 거슬러 줘야한다
        int[] coinUnit = {500, 100, 50, 10};
        int money = 2680;
        System.out.println("money="+money);
        for(int i=0;i<coinUnit.length;i++) {
            if (money / coinUnit[i] != 0) {
                System.out.printf("%d원 : %d", coinUnit[i], money / coinUnit[i]);
                System.out.println();
                money %= coinUnit[i];
            }
        }
    } // main
}

class Exercise7
{
    public static void main(String args[])
    {
        if(args.length!=1) {
            System.out.println("USAGE: java Exercise5_7 3120");
            System.exit(0);
        }
// . . 문자열을 숫자로 변환한다 입력한 값이 숫자가 아닐 경우 예외가 발생한다
        int money = Integer.parseInt(args[0]);
        System.out.println("money="+money);
        int[] coinUnit = {500, 100, 50, 10 }; // 동전의 단위
        int[] coin     = {5, 5, 5, 5}; // 단위별 동전의 개수
        for(int i=0;i<coinUnit.length;i++) {
            int coinNum = 0;
            coinNum = (coin[i] - money/coinUnit[i] >= 0) ? money/coinUnit[i] : coin[i];

            if (coin[i] - coinNum < 0) {
                coin[i] = 0;
            } else {
                coin[i] = coin[i] - coinNum;
            }

            money -= coinUnit[i] * coinNum;

            System.out.println(coinUnit[i]+" 원: "+coinNum);
        }
        if(money > 0) {
            System.out.println("거스름돈이 부족합니다."); //거스름돈이 부족합니다
            System.exit(0); // . 프로그램을 종료한다
        }
        System.out.println("=남은 동전의 개수=");
        for(int i=0;i<coinUnit.length;i++) {
            System.out.println(coinUnit[i]+" 원:"+coin[i]);
        }
    } // main
}

class Exercise8 {
    public static void main(String[] args) {
        int[] answer = { 1,4,4,3,1,4,4,2,1,3,2 };
        int[] counter = new int[4];
        for(int i=0; i < answer.length;i++) {
            counter[answer[i]-1] += 1;
        }
        for(int i=0; i < counter.length;i++) {
            System.out.print(counter[i]);
            for (int j=0; j<counter[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    } // end of main
} // end of class

class Exercise9 {
    public static void main(String[] args) {
        char[][] star = {
                {'*','*',' ',' ',' '},
                {'*','*',' ',' ',' '},
                {'*','*','*','*','*'},
                {'*','*','*','*','*'}
        };

        char[][] result = new char[star[0].length][star.length]; // 세로 5 가로 4

        for(int i=0; i < star.length;i++) {
            for(int j=0; j < star[i].length;j++) {
                System.out.print(star[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        for(int i=0; i < star.length;i++) { // 4
            for(int j=0; j < star[i].length;j++) { // 5
                int x = j;
                int y = star.length - i -1;
                result[x][y] = star[i][j];
            }
        }
        for(int i=0; i < result.length;i++) {
            for(int j=0; j < result[i].length;j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    } // end of main
} // end of class

class Exercise10 {
    public static void main(String[] args) {
        char[] abcCode =
                { '`','~','!','@','#','$','%','^','&','*',
                        '(',')','-','_','+','=','|','[',']','{',
                        '}',';',':',',','.','/'};

        // 0 1 2 3 4 5 6 7 8 9
        char[] numCode = {'q','w','e','r','t','y','u','i','o','p'};
        String src = "abc123";
        String result = "";

        for(int i=0; i < src.length();i++) {
            char ch = src.charAt(i);
            if ('a'<=ch && ch <= 'z') {
                result += abcCode[ch - 97];
            } else if ('0'<=ch&&ch<='9') {
                result += numCode[ch - 48];
            }

        }
        System.out.println("src:"+src);
        System.out.println("result:"+result);
    } // end of main
} // end of class

class Exercise11
{
    public static void main(String[] args)
    {
        int[][] score = {
                {100, 100, 100}
                , {20, 20, 20}
                , {30, 30, 30}
                , {40, 40, 40}
                , {50, 50, 50}
        };
        int[][] result = new int[score.length+1][score[0].length+1];
        for(int i=0; i < score.length;i++) {
            for(int j=0; j < score[i].length;j++) {
                result[i][j]                = score[i][j];
                result[i][score[0].length] += score[i][j];
                result[score.length][j]    += score[i][j];
                result[score.length][score[0].length] += score[i][j];
            }
        }

        for(int i=0; i < result.length;i++) {
            for(int j=0; j < result[i].length;j++) {
                System.out.printf("%4d",result[i][j]);
            }
            System.out.println();
        }
    } // main
}

class Exercise12 {
    public static void main(String[] args) {
        String[][] words = {
                {"chair","의자"},
                {"computer","컴퓨터"},
                {"integer","정수"}
        };

        Scanner scanner = new Scanner(System.in);

        int count = 0;

        for (int i=0; i<words.length; i++) {
            System.out.printf("Q%d. %s의 뜻은?", i + 1, words[i][0]);

            String tmp = scanner.nextLine();

            if (tmp.equals(words[i][1])) {
                System.out.printf("정답입니다. %n%n");
                count += 1;
            } else {
                System.out.printf("틀렸습니다, 정답은 %s입니다. %n%n", words[i][1]);
            }
        }
        System.out.println("전체 " + words.length + "문제 중" + count + "문제 맞추셨습니다.");

    }
}

class Exercise13 {
    public static void main(String args[]) {
        String[] words = { "television", "computer", "mouse", "phone" };
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<words.length;i++) {
            char[] question = words[i].toCharArray(); // String을 char[]로 변환

            for (int j = 0; j< question.length; j++) {
                int rand = (int) (Math.random() * question.length);
                char tmp = question[0];
                question[0]    = question[rand];
                question[rand] = tmp;
            }

            System.out.printf("Q%d. %s의 정답을 입력하세요.>", i+1, new String(question));
            String answer = scanner.nextLine();

// trim()으로 answer의 좌우 공백을 제거한 후, equals로 word[i]와 비교
            if(words[i].equals(answer.trim()))
                System.out.printf("맞았습니다.%n%n");
            else
                System.out.printf("틀렸습니다.%n%n");
        }
    } // main의 끝
}