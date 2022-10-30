package ch14.Lambda;


@FunctionalInterface
interface MyFunction {
    void run(); // 함수형 인터페이스의 추상메서드
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
            } // 클래스 밖에서 사용할것이기에 public 붙여야함
        };

        MyFunction f3 = getMyFunction(); // 함수형인터페이스를 구현한 객체 와 동일한 람다식 의 참조값을 반환받음

        f1.run();
        f2.run();
        f3.run();
        execute(f1); // f1.run()
        execute(() -> System.out.println("run()"));
    }
}


