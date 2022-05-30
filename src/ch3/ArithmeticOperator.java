package ch3;

public class ArithmeticOperator {
    public static void main(String[] args) {
        byte a = 10;
        byte b = 20;
        byte c = (byte)(a+b); //  (byte)없을 시 컴파일 에러 발생
        // a와 b가 int형으로 변환돼 연산되기에, int형을 byte변수에 넣으려 하다보니 에러가 발생
        // byte c = (byte)(a+b)로 명시적 형변환 필요
        System.out.println(c);
    }
}

class ArthimeticEx2 {
    public static void main(String[] args) {
        byte a = 10;
        byte b = 30;
        byte c = (byte)(a*b); //a*b는 300인데, 이는 byte의 범위를 넘기에, byte형으로 변환하는 과정에서 값 손실 발생
        System.out.println(c);
    }
}

class ArthimeticEx3 {
    public static void main(String[] args) {
        int a = 1_000_000; // 1백만
        int b = 2_000_000; // 2백만
        long c = a*b; // a*b= 2,000,000,000,000 인가?
        System.out.println(c);
        // int인 a와 b의 연산결과인 a*b도 int인데, 이는 int의 범위를 넘어간다, 이를 long으로 변환해도 원하는 값이 출력되지 않는다
        // 올바른 결과를 얻기 위해서는 연산과정에서 a혹은 b를 long으로 형변환해야 한다.
    }
}

class ArithmeticEx4 {
    public static void main(String[] args) {
        long a = 1_000_000 * 1_000_000; // int와 int를 계산하기에, 계산과정에서 오버플로우발생했기에 long타입에 저장해도 원하는 값 x
        long b = 1_000_000 * 1_000_000L; // long으로 변환되어 계산되기에 올바른 계산이 나온다

        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }
}

class ArithmeticEx5 {
    public static void main(String[] args) {
        int a = 1000000;

        int result1 = a * a / a; // a*a계산할때 이미 int의 범위를 벗어나서 원하는 결과와 다른 결과 나온다
        int result2 = a / a * a;

        System.out.printf("%d * %d / %d = %d%n", a, a, a, result1);
        System.out.printf("%d * %d / %d = %d%n", a, a, a, result2);
    }
}

class ArithmeticEx6 {
    public static void main(String[] args) {
        char a = 'a';
        char d = 'd';
        char zero = '0';
        char two = '2';

        System.out.printf("'%c' - '%c' = %d%n", d, a, d -a);
        System.out.printf("'%c' - '%c' = %d%n", two, zero, two-zero);
        System.out.printf("'%c'=%d%n", a, (int)a);
        System.out.printf("'%c'=%d%n", d, (int)d);
        System.out.printf("'%c'=%d%n", zero, (int)zero);
        System.out.printf("'%c'=%d%n", two, (int)two);
        // 0~9 문자 연속적 배치, a~z 문자 연속적 배치
    }
}

class ArithmeticEx7 {
    public static void main(String[] args) {
        char c1 = 'a'; // 'a'의 코드값인 97
        char c2 = c1; 
        char c3 = ' '; 

        int i = c1 + 1; // 97+1

        c3 = (char)(c1 + 1); // 계산결과가 int이므로 이를 char로 형변환
        c2++;
        c2++;

        System.out.println("i = " + i);
        System.out.println("c2 = " + c2); //99
        System.out.println("c3 = " + c3); //98
    }
}

class ArithmeticEx8 {
    public static void main(String[] args) {
        char c1 = 'a';

//        char c2 = c1 + 1; // int형을 char변수에 담으려 하니 컴파일 에러가 발생함
        char c2 = 'a' + 1; // 여기서 컴파일에러가 발생하지 않는 이유는 'a' +1 이 '리터럴' 간의 연산이기 때문
        // 상수나 리터럴간의 연산은 실행과정동안 변하는 값이 아니고, 컴파일시에 컴파일러가 '미리' 연산하여 그 결과로 대체함
        // 따라서 실행시에는 덧셈연산이 수행되지 않는다
        // 95라인은 수식에 변수가 들어있어서 컴파일러가 미리 계산할수 없어서 형변환이 필요함

        System.out.println(c2);
    }
}

class ArithmeticEx9 {
    public static void main(String[] args) {
        char c = 'a'; // 97
        for(int i=0; i<26; i++) {
            System.out.print (c++);
        }
        System.out.println();

        c = 'A'; // 65
        for(int i=0; i<26; i++) {
            System.out.print (c++);
        }
        System.out.println();

        c='0'; // 48
        for(int i=0; i<10; i++) {
            System.out.print (c++);
        }
        System.out.println();
    }
}

class ArithmeticEx10 {
    public static void main(String[] args) {
        char lowerCase = 'a';
        char upperCase = (char) (lowerCase - 32); // A의 코드값이 a보다 32작으므로, 이를빼주면 소문자를 대문자로 변경가능하다
        System.out.println(upperCase);
    }
}

class ArithmeticEx11 {
    public static void main(String[] args) {
        float pi = 3.141592f;
        float shortPi = (int) (pi*1000) / 1000f; // float -> int로 형변환(소수점이하 버려짐) -> float과 float의 연산
        System.out.println(shortPi); // 3.141
    }
}

class ArithmeticEx12 {
    public static void main(String[] args) {
        double pi = 3.141592;
        double shortPi = Math.round(pi * 1000) / 1000.0 ;

        // Math.round(3141.592) / 1000.0
        // 3142 / 1000.0
        // 3.142

        System.out.println(shortPi);
    }
}

class ArithmeticEx13 {
    public static void main(String[] args) {
        System.out.println(10 % 8);
        System.out.println(10 % -8); // %는 나누는 수로 음수도 허용되지만, 부호는 무시됨 -> 결과는 음수의 절댓값으로 나눈 나머지와 결과가 같다
    }
}