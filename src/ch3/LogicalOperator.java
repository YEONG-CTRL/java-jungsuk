package ch3;

import java.util.Scanner;

public class LogicalOperator {
    public static void main(String[] args) {
        int x = 0;
        char ch = ' ';

        x = 15;
        System.out.printf("x=%2d, 10 <x && x< 20 = %b%n", x,10 <x && x< 20);

        x = 6;
        System.out.printf("x=%2d, x%%2 ==0 || x%%3==0 && x%%6!=0 = %b%n",x ,x%2==0 || x%3==0 && x%6!=0);
        // x는 2의 배수 또는 3의 배수지만, 6의 배수는 아니다
        // &&가 || 보다 먼저 계산됨. 따라서 and에서 false지만, or에서 true가 됨
        System.out.printf("x=%2d, (x%%2 ==0 || x%%3==0) && x%%6!=0 = %b%n",x, (x%2==0 || x%3==0) && x%6!=0);
        // 괄호를 통해 ||가 &&보다 먼저 계산됨

        ch = '1';
        System.out.printf("'ch='%c', '0' <= ch && ch <= '9' =%b%n", ch, '0' <= ch && ch <= '9');

        ch = 'a';
        System.out.printf("ch='%c', 'a' <= ch && ch <= 'z' = %b%n", ch, 'a' <= ch && ch <= 'z');

        ch = 'A';
        System.out.printf("ch='%c', 'A' <= ch && ch <= 'Z' = %b%n", ch , 'A' <= ch && ch <= 'Z');

        ch = 'q';
        System.out.printf("ch = '%c', ch =='q' || ch == 'Q' = %b%n", ch, ch=='q' || ch=='Q');
    }
}

class LogicalOperator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char ch = ' ';

        System.out.printf("문자를 하나 입력하세요.>");

        String input = scanner.nextLine();
        ch = input.charAt(0); // String 문자열에서 한글자만 선택해서 char타입으로 변환

        if ('0' <= ch && ch <= '9') {
            System.out.printf("입력하신 문자는 숫자입니다.%n");
        }
        if (('a'<=ch && ch <= 'z') || ('A'<=ch && ch <='Z')) {
            System.out.printf("입력하신 문자는 영문자입니다.%n");
        }
    }
}

class LogicalOperator3 {
    public static void main(String[] args) {
        int a = 5;
        int b = 0;

        System.out.printf("a=%d, b=%d%n", a, b);
        System.out.printf("a!=0 || ++b!=0 = %b%n", a!=0 || ++b!=0); // 좌측이 참이기에 우측 피연산자 평가x
        System.out.printf("a=%d, b=%d%n", a, b);
        System.out.printf("a==0 && ++b!=0 = %b%n", a==0 && ++b !=0); // 좌측이 거짓이기에 우측 피연산자 평가x
        System.out.printf("a=%d, b=%d%n", a, b); // 따라서 b는 여전히 0으로 남아있음
    }
}

