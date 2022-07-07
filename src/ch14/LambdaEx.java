package ch14;

@FunctionalInterface
interface MyFunction {
    void run();
}

public class LambdaEx {
    static void execute(MyFunction f) { // 매개변수의 타입이 MyFunction인 메서드
        f.run();
    }
    static MyFunction getMyFunction() {// 반환타입이 MyFunction인 메서드
        MyFunction f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현
        MyFunction f1 = () -> System.out.println("f1.run()");

        MyFunction f2 = new MyFunction() { // 익명클래스로 run()을 구현
            public void run() {  // public을 반드시 붙여야함
                System.out.println("f2.run()");
            }
        };

        MyFunction f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();
        execute(f1); // f1.run()
        execute( () -> System.out.println("run()")); // void run()구현
    }
}

@FunctionalInterface
interface MyFunction2 {
    void MyMethod();
}

class LambdaEx2 {
    public static void main(String[] args) {
        MyFunction f = () -> {}; // MyFunction f = (MyFunction) (()->{});
        Object obj = (MyFunction)(()->{}); // Object타입으로 형변환이 생략됨
        String str = ((Object)(MyFunction)(()->{})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);
        System.out.println((MyFunction)(()->{}));
        System.out.println(((Object)(MyFunction)(()->{})).toString());
    }
}

