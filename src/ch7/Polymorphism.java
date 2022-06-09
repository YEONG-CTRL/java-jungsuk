package ch7;

import org.omg.CORBA.Object;

import java.util.Vector;

public class Polymorphism {
    public static void main(String[] args) {
        Car car = null;
        FireEngine fe = new FireEngine();
        FireEngine fe2 = null;

        fe.water();
        car = fe; // car = (Car)fe 의 형태(자식에서 조상으로 형변환하기에 생략가능)
//        car.water(); // Car타입으로는 water 호출 불가
        fe2 = (FireEngine) car; // 조상에서 자손으로 갈때는 형변환 생략 불가능
        fe2.water();
    }
}

class Car {
    String color;
    int door;

    void drive() {
        System.out.println("drive, Brrrr~");
    }

    void stop() {
        System.out.println("stop!!!");
    }
}

class FireEngine extends Car {
    void water() {
        System.out.println("water!!!");
    }
}

class CastingTest2 {
    public static void main(String[] args) {
        Car car = new Car();
        Car car2 = null;
        FireEngine fe = null;

        car.drive();
        fe = (FireEngine) car; // 컴파일이후 실행시 에러 발생.
//         캐스트 연산자로 조상타입의 참조변수를 자손타입의 참조변수로 형변환한 것이기에 문제 없어 보이지만, 참조변수 car가 참조하고 있는 인스턴스가 Car타입의 인스턴스이기 때문,
//         조상타입의 인스턴스를 자손타입의 참조변수로 참조하는 것은 불가능하기때문이다.
        fe.drive();
        car2 = fe;
        car2.drive();
    }
}

class InstanceofTest {
    public static void main(String[] args) {
        FireEngine fe  = new FireEngine();

        if (fe instanceof FireEngine) {
            System.out.println("This is a FireEngine instance");
        }

        if (fe instanceof Car) { // 조상타입의 instanceof에도 true를 결과로 얻는다, 이는 형변환해도 아무 문제가 없다는 것이다.
            System.out.println("This is a Car instance");
        }

        if (fe instanceof Object) {
            System.out.println("This is an Object instance");
        }

        System.out.println(fe.getClass().getName());
    }
}

class BindingTest {
    public static void main(String[] args) {
        ParentClass p = new ChildClass();
        ChildClass c  = new ChildClass();

        System.out.println("p.x = " + p.x); // 조상타입의 참조변수를 사용했을때에는 조상클래스에 선언된 멤버변수가 사용됨
        p.method(); // 메서드는 참조변수 타입에 관계없이 항상 실제 인스턴스 타입인 ChildClass에 정의된 메서드가 호출된다

        System.out.println("c.x = " + c.x); // 인스턴스변수인 x는 참조변수의 타입에 따라 달라진다
        c.method();
    }
}

class ParentClass {
    int x = 100;

    void method() {
        System.out.println("Parent Method");
    }
}

class ChildClass extends ParentClass {
    int x = 200;

    void method() {
        System.out.println("Child Method");
    }
}

class BindingTest2 {
    public static void main(String[] args) {
        ParentClass2 p = new ChildClass2();
        ChildClass2 c  = new ChildClass2();

        System.out.println("p.x = " + p.x);
        p.method();

        System.out.println("c.x = " + c.x);
        c.method();
    }
}

class ParentClass2 {
    int x = 100;

    void method() {
        System.out.println("Parent Method");
    }
}

class ChildClass2 extends ParentClass2 { }

class BindingTest3 {
    public static void main(String[] args) {
        ParentClass3 p = new ChildClass3();
        ChildClass3 c = new ChildClass3();

        System.out.println("p.x = " + p.x); // 변수는 참조변수의 타입에 맞는 변수 선택
        p.method(); // 메서드는 둘다 인스턴스의 메서드 사용
        System.out.println();
        System.out.println("c.x = " + c.x);
        c.method();
    }
}

class ParentClass3 {
    int x = 100;

    void method() {
        System.out.println("Parent Method");
    }
}

class ChildClass3 extends ParentClass3 {
    int x = 200;

    void method() {
        System.out.println("x =" + x);
        System.out.println("super.x =" + super.x);
        System.out.println("this.x =" + this.x);
    }
}

class PolyArgumentTest { // 제품
    int price; // 제품의 가격
    int bonusPoint; // 제품 구매시 보너스 점수

    PolyArgumentTest(int price) {
        this.price = price;
        bonusPoint = (int) (price / 10.0);
    }

    PolyArgumentTest() {
        price = 0;
        bonusPoint = 0;
    }
}

class Telv extends PolyArgumentTest {
    Telv() {
        // 조상 클래스의 생성자를 호출한다
        super(100); // (가격은 100만원으로 한다)
    }

    public String toString() { return "Telv"; }
}

class Com extends PolyArgumentTest {
    Com() { super(200); }

    public String toString() { return "Com"; }
}

class Audio extends PolyArgumentTest {
    Audio() { super(50); }
    public String toString() { return "Audio"; }
}

class Buyer {
    int money = 1000;
    int bonusPoint = 0;

//    PolyArgumentTest[] item = new PolyArgumentTest[10]; // 구입한 제품을 저장하기 위한 배열
     Vector item = new Vector();  // Object타입의 동적 배열인 Vector를 사용하여 유연하게 item을 관리할 수도 있다.
//    int i = 0; // 배열에 사용될 카운터

    void buy(PolyArgumentTest p) {
        if (money < p.price) {
            System.out.println("잔액이 부족해서 물건을 살 수 없습니다");
            return;
        }

        money -= p.price;
        bonusPoint += p.bonusPoint;
//        item[i++] = p;
        item.add(p); // Vector 사용시 제품 객체 더하는 법
        System.out.println(p + "을 구입하셨습니다 ");
    }

    void refund(PolyArgumentTest p) { // => Vector 사용시 환불 기능 추가
        if (item.remove(p)) { // vector에서 제품 제거
            money += p.price;
            bonusPoint  -= p.bonusPoint;
            System.out.println(p + "을/를 반품하셨습니다");
        } else {
            System.out.println("구입하신 제품 중 해당 제품이 없습니다");
        }
    }

    void summary() {
        int sum = 0;
        String itemList = "";

        if(item.isEmpty()) { // 만약 벡터가 비었다면
            System.out.println("구입하신 제품이 없습니다");
            return;
        }
        for (int i = 0; i < item.size(); i++) {
            PolyArgumentTest p = (PolyArgumentTest) (item.get(i)); // 벡터의 i번째에 있는 객체를 얻어온다(PolyArgumentTest로 형변환)(조상Object에서 자손으로 가는 것이니 형변환 필수)
            sum += p.price;
            itemList += (i==0) ? "" + p : ", " + p; // 첫번째면 바로, 아니면 쉼표 더해서
        }
        System.out.println("구입하신 물품의 총 금액은" + sum + "만원입니다");
        System.out.println("구입하신 제품은" + itemList + "입니다");
        }
    }


class PolyArg {
    public static void main(String[] args) {
        Buyer b = new Buyer();
        Telv tv = new Telv();
        Com com = new Com();
        Audio audio = new Audio();

        b.buy(tv);
        b.buy(com);
        b.buy(audio);
        b.summary();
        System.out.println();
        b.refund(audio);
        b.summary();
    }
}

