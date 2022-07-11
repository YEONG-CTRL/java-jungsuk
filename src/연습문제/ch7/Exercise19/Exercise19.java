package 연습문제.ch7.Exercise19;

import java.util.Arrays;
import java.awt.*;
import java.awt.event.*;

class Buyer {
    int money = 1000;
    Product[] cart = new Product[3]; // 구입한 제품을 저장하기 위한 배열
    int i = 0; // Product배열 cart에 사용될 index
    void buy(Product p) {
        if (money< p.price ) {
            System.out.println("잔액이 부족하여 살 수 없습니다");
            return;
        } else {
            money -= p.price;
            add(p);
        }

    }
    void add(Product p) {
        if (i >= cart.length) {
            Product[] tmp = new Product[cart.length * 2];
            System.arraycopy(cart,0,tmp,0,cart.length);
            cart = tmp;
        }
        cart[i++] = p;
    } // add(Product p)
    void summary() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(cart,0,i))); // 뒤의 null없애기 위해 i 까지만 출력
        System.out.println("구매한 금액" + (1000-money));
        System.out.println("남은 금액" + money);
    } // summary()
}
class Product {
    int price; // 제품의 가격
    Product(int price) {
        this.price = price;
    }
}
class Tv extends Product {
    Tv() { super(100); }
    public String toString() { return "Tv"; }
}
class Computer extends Product {
    Computer() { super(200); }
    public String toString() { return "Computer";}
}
class Audio extends Product{
    Audio() { super(50); }
    public String toString() { return "Audio"; }
}
class Exercise19 {
    public static void main(String args[]) {
        Buyer b = new Buyer();
        b.buy(new Tv());
        b.buy(new Computer());
        b.buy(new Tv());
        b.buy(new Audio());
        b.buy(new Computer());
        b.buy(new Computer());
        b.buy(new Computer());
        b.summary();
    }
}

/* 22 */
abstract class Shape {
    Point p;
    Shape() {
        this(new Point(0,0));
    }
    Shape(Point p) {
        this.p = p;
    }
    abstract double calcArea(); // 도형의 면적을 계산해서 반환하는 메서드
    Point getPosition() {
        return p;
    }
    void setPosition(Point p) {
        this.p = p;
    }
}
class Point {
    int x;
    int y;
    Point() {
        this(0,0);
    }
    Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public String toString() {
        return "["+x+","+y+"]";
    }
}

class Circle extends Shape {
    double r;

    Circle(double r) {
        this(new Point(0,0),r);
    }

    Circle(Point p, double r) {
        super(p); // 부모 생성자 호출하여 초기화
        this.r = r;
    }

    double calcArea() {
        return r*r*Math.PI;
    }
}

class Rectangle extends Shape {
    double width;
    double height;

    Rectangle(double width, double height) {
        this(new Point(0, 0), width, height);
    }

    Rectangle(Point p, double width, double height) {
        super(p);
        this.width = width;
        this.height = height;
    }

    double calcArea() {
        return width * height;
    }

    boolean isSquare() {
        return width == height && width * height != 0;
    }
}

class Exercise23
{
    static double sumArea(Shape[] arr) {
        double sum = 0;
        for (int i=0; i<arr.length ; i++) {
            sum += arr[i].calcArea();
        }
        return sum;
    }

    public static void main(String[] args)
    {
        Shape[] arr = {new Circle(5.0), new Rectangle(3,4), new Circle(1)};
        System.out.println("면적의 합:"+sumArea(arr));
    }
}

class Outer {
    class Inner {
        int iv=100;
    }
}
class Exercise25 {
    public static void main(String[] args) {
        Outer o = new Outer();
        Outer.Inner ii = o.new Inner();
        System.out.println(ii.iv);

    }
}

class Outer2 {
    static class Inner {
        int iv=200;
    }
}
class Exercise7_26 {
    public static void main(String[] args) {
        Outer2.Inner si = new Outer2.Inner(); // 위의 인스턴스클래스와 달리, 외부 클래스의 인스턴스를 생성하지 않고도 사용할 수 있다.
        System.out.println(si.iv);
    }
}

class Outer3 {
    int value=10;
    class Inner {
        int value=20;
        void method1() {
            int value=30;
            System.out.println(value);
            System.out.println(this.value);
            System.out.println(Outer3.this.value); // 외부클래스.this.변수이름 : 내부 클래스에서 외부 클래스의 인스턴스 변수에 접근하는 법.
        }
    } // Inner클래스의 끝
} // Outer클래스의 끝

class Exercise27 {
    public static void main(String args[]) {
        Outer3 o3 = new Outer3(); // 외부 클래스의 인스턴스를 생성해서 내부 인스턴스 클래스에 접근
        Outer3.Inner inner = o3.new Inner();

        inner.method1();
    }
}


class Exercise7_28
{
    public static void main(String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().setVisible(false);
                e.getWindow().dispose();
                System.exit(0);
            }
        });
    }
}
//class EventHandler extends WindowAdapter
//{
//    public void windowClosing(WindowEvent e) {
//        e.getWindow().setVisible(false);
//        e.getWindow().dispose();
//        System.exit(0);
//    }
//}

class Exercise7_29
{
    public static void main(String[] args)
    {
        final int VALUE = 10; // 외부 클래스의 지역변수
        Thread t = new Thread(new Runnable() { // 익명 클래스(내부 클래스)
            public void run() {
                for(int i=0; i < 10;i++) { // 10번 반복한다.
                    try {
                        Thread.sleep(1*1000); // 1초간 멈춘다.
                    } catch(InterruptedException e) {}
                    System.out.println(VALUE); // 외부 클래스의 지역변수를 사용 -> 상수는 컨스턴트 풀에 저장돼있기에 메서드가 종료되도 사용가능
                }
            } // run()
        });
        t.start(); // 쓰레드를 시작한다.
        System.out.println("main() - 종료.");
    } // main
}