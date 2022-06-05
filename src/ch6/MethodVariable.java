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

    static void change(Data d) { // 참조형 매개변수
        d.x = 1000;
        System.out.println("change() : x = " +d.x);
    }
}

class ReferenceParamEx2 {
    public static void main(String[] args) {
        int[] x = {10};
        System.out.println("main () : x =" + x[0]);

        change(x);
        System.out.println("After change(x)");
        System.out.println("main () : x =" + x[0]);
    }

    static void change(int [] x) { // 참조형 매개변수 (배열도 참조변수를 통해 데이터가 저장된 공간에 접근)
        x[0] = 1000;
        System.out.println("main () : x =" + x[0]);
    }
}

class ReferenceParamEx3 {
    public static void main(String[] args) {
        int[] arr = new int[] {3,2,1,6,5,4};

        printArr(arr);
        sortArr(arr);
        printArr(arr);
        System.out.println("sum=" + sumArr(arr));
    }

    static void printArr(int[] arr) {
        System.out.print("[");

        for (int i : arr)
            System.out.print(i + ",");
        System.out.println("]");
    }

    static int sumArr(int[] arr) {
        int sum = 0;

        for(int i=0; i<arr.length; i++)
            sum += arr[i];

        return sum;
    }

    static void sortArr(int[] arr) {
        for(int i=0; i<arr.length-1; i++) // 첫번째~마지막 하나 전
            for (int j=0; j<arr.length-1-i;j++)
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
    }
}

class ReturnTest {
    public static void main(String[] args) {
        ReturnTest r = new ReturnTest();

        int result = r.add(3,5);
        System.out.println(result);

        int[] result2 = {0};
        r.add(3,5,result2);
        System.out.println(result2[0]);
    }

    int add(int a , int b) {
        return a + b;
    }

    void add(int a, int b, int[] result) {
        result[0] = a +b; // 매개변수로 넘겨받은 배열에 연산결과 저장 : 참조형 매개변수 활용하여, 반환값 없어도 실행결과 얻어올 수 있다
    }
}

class ReferenceReturnEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;

        Data d2 = copy(d);
        System.out.println("d.x =" + d.x);
        System.out.println("d2.x =" + d2.x);
    }

    static Data copy(Data d) {
        Data tmp = new Data();
        tmp.x = d.x;

        return tmp;
    }
}

class FactorialTest {
    public static void main(String[] args) {
        int n = 21;
        int result = 0;

        for (int i=1; i<=n; i++) {
            result = factorial(i);

            if (result==-1) {
                System.out.printf("유효하지 않은 값입니다. (0<n<13): %d%n", n);
                break;
            }

            System.out.printf("%2d!=%20d%n",i,result);
        }
    }

    static int factorial(int n) {
        if(n<=0 || n > 12) return -1; // 매개변수 n의 유효성 검사(0이하는 스택오버플로, 13부터는 int의 범위 벗어남)
        if(n==1) return 1;

        return n * factorial(n-1);
    }
}

class MainTest {
    public static void main(String[] args) {
        main(null);
    }
}

class PowerTest {
    public static void main(String[] args) {
        int x = 2;
        int n = 5;
        long result = 0;

        for (int i=1; i<=n; i++) {
            result += power(x,i);
        }

        System.out.println(result);
    }

    static long power(int x , int n) {
        if(n==1) return x;
        return x * power(x, n-1);
    }
}

class MyMath2 {
    long a, b; 

    long add() { return a + b; } // 인스턴스 변수 a,b를 이용하여 작업한다, 따라서 매개변수가 필요없다
    long subtract() { return a - b; }
    long multiply() { return a * b; }
    double divide() { return a / b; }

    static long add(long a, long b) { return a + b; } // 인스턴스변수와 관계없이, 매개변수만으로 작업한다
    static long subtract(long a, long b) { return a - b; }
    static long multiply(long a, long b) { return a * b; }
    static double divide(double a, double b) { return a / b; }
}

class MyMathTest2 {
    public static void main(String[] args) {  
        System.out.println(MyMath2.add(200L, 100L)); // 클래스메서드
        System.out.println(MyMath2.subtract(200L, 100L));
        System.out.println(MyMath2.multiply(200L, 100L));
        System.out.println(MyMath2.divide(200L, 100L));

        MyMath2 mm = new MyMath2(); // 인스턴스 생성
        mm.a = 200L;
        mm.b = 100L;

        System.out.println(mm.add()); // 인스턴스 메서드
        System.out.println(mm.subtract());
        System.out.println(mm.multiply());
        System.out.println(mm.divide());
    }
}

class MemberCall {
    int iv = 10;
    static int cv = 20;

    int iv2 = cv;
    static int cv2 = new MemberCall().iv; // 클래스벼누가 인스턴스 변수를 사용하려면 객체를 생성해야 사용가능

    static void staticMethod1() {
        System.out.println(cv);
        MemberCall c = new MemberCall();
        System.out.println(c.iv); // 클래스 메서드에서 인스턴스 변수를 사용하려면 객체를 생성해야만 사용가능
    }
    void instanceMethod1() {
        System.out.println(iv); // 인스턴스메서드에선 인스턴스 변수 바로 사용가능
        System.out.println(cv);
    }

    static void staticMethod2() {
        staticMethod1();
        MemberCall c = new MemberCall();
        c.instanceMethod1(); // 클래스메서드에서 인스턴스 메서드 호출할려면 객체 생성해야만 호출 가능
    }

    void instanceMethod2() { // 인스턴스 메서드에서는 인스턴스메서드와 클래스메서드 모두 인스턴스 생송없이 호출 가능
        staticMethod1();
        instanceMethod1();
    }
}

class OverloadingTest {
    public static void main(String[] args) {
        MyMath3 mm = new MyMath3();
        System.out.println("mm.add(3, 3) 결과:"  + mm.add(3,3));
        System.out.println("mm.add(3L, 3) 결과:"  + mm.add(3L,3)); // 6L이 결과인데 왜 6이 출력? : 리터럴의 접미사는 출력되지 않는다
        System.out.println("mm.add(3, 3L) 결과:"  + mm.add(3,3L));
        System.out.println("mm.add(3L, 3L) 결과:"  + mm.add(3L,3L));

        int[] a = {100,200,300};
        System.out.println("mm.add(a) 결과:"  + mm.add(a));
    }
}

class MyMath3 {
    int add(int a, int b) {
        System.out.print("int add(int a, int b) - ");
        return a+b;
    }
    long add(int a, long b) {
        System.out.print("long add(int a,long b) - ");
        return a+b;
    }

    long add(long a, int b) {
        System.out.print("long add(long a,int b) - ");
        return a+b;
    }

    long add(long a, long b) {
        System.out.print("long add(long a,long b) - ");
        return a+b;
    }

    int add(int[] a) {
        System.out.println("int add(int[] a) -");
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }
}

class VarArgsEx {
    public static void main(String[] args) {
        String[] strArr = {"100", "200", "300"};

        System.out.println(concatenate("", "100", "200", "300"));
        System.out.println(concatenate("-", strArr));
        System.out.println(concatenate(",", new String[] {"1","2","3"}));
        System.out.println("[" + concatenate(",", new String[0]) + "]"); // 가변인자로 배열(아무것도 없는) 지정
        System.out.println("[" + concatenate(",") + "]"); // 가변인자 주지 않음
    }

    static String concatenate(String delim, String... args) {
        String result = "";

        for (String str : args) {
            result += str + delim;
        }

        return result;
    }

    // 가변인자를 선언한 메서드를 오버로딩 하면, 메서드를 호출했을 때 구별되지 못하는 경우가 발생하기 쉬움
//    static String concatenate(String ...args) {
//        return concatenate("", args);
//    }

}