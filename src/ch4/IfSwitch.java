package ch4;

import java.util.*;

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

class SwitchEx {
    public static void main(String[] args) {
        System.out.print("현재 월을 입력하세요.>");

        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();

        switch(month) {
            case 3:
            case 4:
            case 5:
                System.out.println("현재 계절은 봄입니다");
                break;
            case 6: case 7: case 8:
                System.out.println("현재 계절은 여름입니다");
                break;
            case 9: case 10: case 11:
                System.out.println("현재 계절은 가을입니다");
                break;
            case 12: case 1: case 2:
                System.out.println("현재 계절은 겨울입니다");
                break;
            default:
//            case 12: case 1: case 2:
                System.out.println("현재 계절은 겨울입니다");
        }
    }
}

class SwitchEx2 {
    public static void main(String[] args) {
        System.out.print("가위(1) 바위(2) 보(3) 중 하나를 입력하세요");

        Scanner scanner = new Scanner(System.in);
        int user = scanner.nextInt();
        int com  = (int)(Math.random() * 3) + 1;// 0.0<=math.random<1.0 사이의 double값 반환. 1 2 3 중 하나가 com에 저장됨

        System.out.println("당신은 " +user+ "입니다");
        System.out.println("컴은 " +com+ "입니다");

        switch (user-com) {
            case 2: case -1:
                System.out.println("당신이 졌습니다");
                break;
            case 1: case -2:
                System.out.println("당신이 이겼습니다");
                break;
            case 0:
                System.out.println("비겼습니다");
//                break           마지막 문장이므로 break 사용할 필요 없다
        }

    }
}

class SwitchEx3 {
    public static void main(String[] args) {
        System.out.print("당신의 주민번호를 입력하세요(011231-1111222)>");

        Scanner scanner = new Scanner(System.in);
        String regNo = scanner.nextLine();

        char gender = regNo.charAt(7);

        switch (gender) {
            case '1': case '3':
                System.out.println("당신은 남자입니다");
                break;
            case '2': case '4':
                System.out.println("당신은 여자입니다");
                break;
            default:
                System.out.println("유요하지 않은 주민번호입니다");
        }
    }
}

class SwitchEx4 {
    public static void main(String[] args) {
        char grade = ' ';

        System.out.print("당신의 점수를 입력하세요.(1~100)>");

        Scanner scanner = new Scanner(System.in);
        int score= scanner.nextInt();

        switch (score) {
            case 100: case 99: case 98: case 97: case 96:
            case 95: case 94: case 93: case 92: case 91: case 90:
                grade = 'A';
                break;
            case 89: case 88: case 87: case 86: case 85:
            case 84: case 83: case 82: case 81: case 80:
                grade = 'B';
                break;
            case 79: case 78: case 77: case 76: case 75:
            case 74: case 73: case 72: case 71: case 70:
                grade = 'C';
                break;
            default:
                grade = 'F';
        }
        System.out.println("당신의 학점은 " + grade+"입니다");
    }
}

class SwitchEx5 {
    public static void main(String[] args) {
        int score = 0;
        char grade = ' ';

        System.out.print("당신의 점수를 입력하세요.(1~100)>");

        Scanner scanner = new Scanner(System.in);
        String tmp = scanner.nextLine();
        score = Integer.parseInt(tmp);


        switch (score/10) {
            case 10:
            case 9:
                grade = 'A';
                break;
            case 8 : // 88/10 = 8
                grade = 'B';
                break;
            case 7:
                grade = 'C';
                break;
            default:
                grade = 'F';
                break;
        }
        System.out.println("당신의 학점은 " + grade+"입니다");
    }
}

class SwitchEx6 { // 중첩 switch문
    public static void main(String[] args) {
        System.out.print("당신의 주민번호를 입력하세요(011231-1111222)>");

        Scanner scanner = new Scanner(System.in);
        String regNo = scanner.nextLine();
        char gender = regNo.charAt(7);

        switch (gender) {
            case '1': case '3':
                switch (gender) {
                    case '1':
                        System.out.println("2000년 이전 출생 남자");
                        break;
                    case '3':
                        System.out.println("2000년 이후 출생 남자");
                }
                break; // 빼먹지 않도록 주의
            case '2': case '4':
                switch (gender) {
                    case '2':
                        System.out.println("2000년 이전 출생 여자");
                        break;
                    case '4':
                        System.out.println("2000년 이후 출생 여자");
                }
                break;
            default:
                System.out.println("유효하지 않은 주민번호");
        }

    }
}