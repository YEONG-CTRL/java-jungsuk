package ch4;

import java.util.Scanner;

public class IfSwitch {
    public static void main(String[] args) {
        int input;

        System.out.print("숫자를 하나 입력하세요");

        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        input = Integer.parseInt(tmp);

        if (input==0) {
            System.out.println("입력하신 숫자는 0 입니다");
        }

        if (input!=0)
            System.out.println("입력하신 숫자는 0이 아닙니다");
            System.out.printf("입력하신 숫자는 %d입니다", input); //블럭을 지정해주지 않았기에, 두번째 if문에 속하지 않고 항상 실행됨
    }
}

class NestedIf {
    public static void main(String[] args) {
        int score = 0;
        char grade = ' ', opt = '0';

        System.out.println("점수를 입력해주세요.>");

        Scanner scanner = new Scanner(System.in);
        score= scanner.nextInt();

        System.out.printf("당신의 점수는 %d 입니다. %n", score);

        if (score >= 90) {
            grade = 'A';
            if (score >= 98) {
                opt = '+';
            } else if (score < 94) {
                opt = '-';
            }
        } else if (score >= 80) {
            grade = 'B';
            if (score >= 88)
                opt = '+';
            else if (score < 84) {
                opt = '-';
            }
        } else {
            grade = 'C';
        }
        System.out.printf("당신의 학점은 %c%c입니다. %n", grade, opt);
    }
}
