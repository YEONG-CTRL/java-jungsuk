package ch14;

@FunctionalInterface
interface MyFunction3 {
    void myMethod();
}

class Outer {
    int val = 10; // Outer.this.val

    class Inner {
        int val = 20; // this.val

        void method(int i) {
            int val = 30;
//            i = 10;  // 에러, 상수의 값을 변경할 수 없음

            MyFunction3 f = () -> {
                System.out.println("      i : " + i); // 람다식 내에서 참조하는 지역변수는 final 붙지 않았어도 상수로 간주
                System.out.println("      val : " + val);
                System.out.println("      this.val : " + ++this.val);
                System.out.println("      Outer.this.val : " + Outer.this.val);
            };
            /* 람다식 내에서 외부에 선언된 변수에 접근하는 방법 */

            f.myMethod();
        }
    }
}

public class LambdaEx3 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}
