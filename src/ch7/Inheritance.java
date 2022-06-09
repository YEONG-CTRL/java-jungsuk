package ch7;


import static java.lang.System.out;
import static java.lang.Math.*;

class Tv {
    boolean power;
    int channel;

    void power()  { power = !power; }
    void channelUp() { ++channel; }
    void channelDown() { --channel; }
}

class CaptionTv extends Tv {
    boolean caption;
    void displayCaption(String text) {
        if (caption) {
            System.out.println(text);
        }
    }
}
public class Inheritance {
    public static void main(String[] args) {
        CaptionTv ctv = new CaptionTv();
        ctv.channel = 10;
        ctv.channelUp();
        System.out.println(ctv.channel);
        ctv.displayCaption("Hello, World");
        ctv.caption = true;
        ctv.displayCaption("Hello, World");
    }
}


class VCR {
    boolean power;
    int counter = 0;
    void power() { power = ! power; }
    void play() { /*내용 생략*/}
    void stop()  { /*내용 생략*/}
    void rew()  { /*내용 생략*/}
    void ff()  { /*내용 생략*/}
}

class TVCR extends Tv {
    VCR vcr = new VCR(); // vcr클래스를 TVCR에 포함시킴

    void play() {
        vcr.play(); // VCR클래스의 메서드 호출해서 사용
    }

    void stop() {
        vcr.stop();
    }

    void rew() {
        vcr.rew();
    }

    void ff() {
        vcr.ff();
    }
}

class SuperTest {
    public static void main(String[] args) {
        Child c = new Child();
        c.method();
    }
}

class Parent {
    int x = 10;
}

class Child extends Parent {
    void method() { // 조상클래스로 부터 상속받은 멤버 x도 Child의 멤버이다
        System.out.println("x=" + x); // 그냥 x와,
        System.out.println("this.x=" + this.x);  // Super와,
        System.out.println("super.x=" + super.x); // this는 같은 변수를 의미한다
    }
}

class SuperTest2 {
    public static void main(String[] args) {
        Child c = new Child();
        c.method();
    }
}
class Child2 extends Parent {
    int x = 20; // 멤버변수 x가 조상클래스인 Parent에도 있고, 자손클래스인 Child클래스에도 있다

    void method() { // 조상클래스로 부터 상속받은 멤버 x도 Child의 멤버이다
        System.out.println("x=" + x); // 자기 자신의 x
        System.out.println("this.x=" + this.x);  // 자손인 CHild에 선언된 x
        System.out.println("super.x=" + super.x); // Parent에서 상속받은 x
    }
}

class PointTest {
    public static void main(String[] args) {
        Point3D p3  = new Point3D(1,2,3);
        System.out.println(p3.getLocation());
    }
}

class Point2 {
    int x = 10;
    int y = 20;



    Point2(int x, int y) { // point2 의 생성자
        // 조상인 Object 클래스의 생성자 Object()를 호출
        this.x = x;
        this.y = y;
    }

    String getLocation() {
        return "x :" + x + ", y :" + y;
    }
}

class Point3D extends Point2 {
    int z = 30;

    Point3D() {
        this(100,200,300); // 밑의 Point3D(int x, int y, int z)를 호출한다.
    }

    Point3D(int x, int y, int z) {
        // super 가 없다면, 생성자 첫줄에서 다른 생성자를 호출하지 않기에 컴파일러가 super()를 삽입한다.
        // 이는 Point2() 를 호출하지만, 문제는Point()가 정의돼 있지 않기 때문에 에러가 발생한다
        super(x,y); // 따라서 super(x,y)로 Point2(int x, int y)를 호출한다
        this.z = z;
    }

    String getLocation() { // 오버라이딩
        return "x :" + x + ", y :" + y + ", z : " + z;
    }
}

class PointTest2 {
    public static void main(String[] args) {
        Point3D ppp = new Point3D();
        System.out.println("ppp.x =" + ppp.x);
        System.out.println("ppp.y =" + ppp.y);
        System.out.println("ppp.z =" + ppp.z);
    }
}

// import static java.lang.System.out;
//import static java.lang.Math.*;

class StaticImportEx {
    public static void main(String[] args) {
        out.println(random()); // static import문 사용하면 static멤버 호출시 클래스 이름을 생략할 수 있다.

        out.println("Math.PI :" + PI);
    }
}