package ch2;

import java.util.*;

class ScannerEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("두자리 정수를 하나 입력해주세요");
        String input = scanner.nextLine(); // 문자열을 받아서
        int num = Integer.parseInt(input); // 숫자로 변환하거나(권장됨)

        System.out.println("두자리 정수를 하나 더 입력해주세요");
        int num2 = scanner.nextInt(); // 정수를 바로 입력받아 저장

        System.out.println("입력 내용 : " + input);
        System.out.printf("num=%d%n", num);

        System.out.printf("num1=%d%n", num2);

    }
}
