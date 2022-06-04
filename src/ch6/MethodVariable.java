package ch6;

public class MethodVariable {
    public static void main(String[] args) {
        System.out.println("Card.width = " + Card.width);
        System.out.println("Card.height = " + Card.height); // 객체 생성 없이 클래스이름.클래스변수로 직접 사용

        Card c1 = new Card();
        c1.kind = "Heart";
        c1.number = 7;

        Card c2 = new Card();
        c2.kind = "Spade";
        c2.number = 4;

        System.out.println("c1은" + c1.kind + ", " + c1.number + "이며 , 크기는 (" + c1.width + ", " +c1.height + ")");
        System.out.println("c2은" + c2.kind + ", " + c2.number + "이며 , 크기는 (" + c2.width + ", " +c2.height + ")");
        System.out.println("c1의 width와 height를 각각 50, 80으로 변경합니다");

        c1.width = 50;
        c1.height = 80; // 클래스변수의 값 변경

        System.out.println("c1은" + c1.kind + ", " + c1.number + "이며 , 크기는 (" + c1.width + ", " +c1.height + ")");
        System.out.println("c2은" + c2.kind + ", " + c2.number + "이며 , 크기는 (" + c2.width + ", " +c2.height + ")");
    }
}

class Card {
    String kind;
    int number;
    static int width = 100; // 클래스변수
    static int height = 250; // 클래스변수
}

class MathTest {
    public static void main(String[] args) {
        MyMath mm =new MyMath();
        long result1 = mm.add(5,3); // int가 long으로 자동변환
        long result2 = mm.subtract(5L,3L);
        long result3 = mm.multiply(5L,3L);
        double result4 = mm.divide(5L,3L); // long값으로 호출했는데, long인자들은 매개변수에 들어가며 double로 자동변환된다

        System.out.println("add(5L,3L) = "+result1);
        System.out.println("subtract(5L,3L) = "+result2);
        System.out.println("multiply(5L,3L) = "+result3);
        System.out.println("divide(5L,3L) = "+result4);
    }
}

class MyMath {
    long add(long a, long b) { return a+b; }
    long subtract(long a, long b) { return a - b;}
    long multiply(long a, long b) { return a * b;}
    double divide(double a, double b) { return a / b;}
}

class CallStackTest {
    public static void main(String[] args) {
        firstMethod(); // static 메서드는 객체 생성없이 호출 가능하다.
    }
    static void firstMethod() {
        secondMethod ();
    }

    static void secondMethod() {
        System.out.println("secondMethod()");
    }
}
// main호출 -> 콜스택에 메모리공간 할당 -> first -> second -> println 순으로 call stack에 쌓인다. 
// 쌓인 순서대로 종료되며 콜스택에서 빠져나간다. main까지 돌아왔는데 더이상 수행할 코드가 없으므로, 콜스택은 완전히 비워지고 프로그램은 종료된다

class CallStackTest2 {
    public static void main(String[] args) {
        System.out.println("main(String[] args)이 시작됐음.");
        firstMethod();
        System.out.println("main(String[] args)이 끝났음.");
    }

    static void firstMethod() {
        System.out.println("firstMethod() 이 시작됐음");
        SecondMethod();
        System.out.println("firstMethod() 이 끝났음");
    }

    static void SecondMethod() {
        System.out.println("SeondMethod()가 시작됐음");
        System.out.println("SeondMethod()가 끝났음");
    }
}

class Data { int x; }

class PrimitiveParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d.x);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = " + d.x);
    }

    static void change (int x) { // 기본형 매개변수
        x = 1000; // change 메서드의 매개변수 x의 값이 변경된 것일뿐(복사본이 변경된 것)
        System.out.println("change() : x = " + x);
    }
}

class Data2 { int x; }

class ReferenceParamEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x = " + d.x);

        change(d);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = " + d.x);
    }

    static void change(Data d) {
        d.x = 1000;
        System.out.println("change() : x = " +d.x);
    }
}