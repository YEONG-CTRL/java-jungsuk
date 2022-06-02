package ch5;

public class CLI {
    public static void main(String[] args) { // 이클립스의 RUN에서 Configuration으로 매개변수 줄 수 있다
        System.out.println("매개변수의 개수:" + args.length);
        for (int i=0; i<args.length; i++) {
            System.out.println("args [" + i + "] = \"" + args[i] + "\"");
        }
    }
}

class CliEx2 {
    public static void main(String[] args) {
        if (args.length != 3) { // 입력된 값의 개수 3개 아니면
            System.out.println("usage : java CliEx2 NUM1 OP NUM2");
            System.exit(0); // 프로그램 종료
        }

        int num1 = Integer.parseInt(args[0]); // 첫번째 인자로 온 문자열을 숫자로
        char op = args[1].charAt(0); // 두번째 인자의 첫 문자를 char로
        int num2 = Integer.parseInt(args[2]); // 세번째 숫자로
        int result = 0;

        switch(op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case 'x':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                System.out.println("지원되지 않는 연산입니다");
        }
        System.out.println("결과 :" + result);
    }
}