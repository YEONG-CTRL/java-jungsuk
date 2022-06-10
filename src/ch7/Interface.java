package ch7;


public class Interface {
    public static void main(String[] args) {
        Fighter f = new Fighter();

        if (f instanceof Unit)
            System.out.println("f는 Unit클래스의 자손입니다");
        if (f instanceof Fightable)
            System.out.println("f는 Fightable 인터페이스를 구현했습니다");
        if (f instanceof Movable)
            System.out.println("f는 Movable 인터페이스를 구현했습니다");
        if (f instanceof Attackable)
            System.out.println("f는 Attackable 인터페이스를 구현했습니다");
        if (f instanceof Object)
            System.out.println("f는 Object클래스의 자손입니다");
    }
}

class Fighter extends Unit implements Fightable { // unit 클래스로부터 상속받고, Fightable 인터페이스를 구현
    public void move(int x, int y) { /* 내용 생략 */} // Movable인터페이스의 move는 사실 public abstract void move와 같다.
    // 따라서 이를 구현하는 Fighter클래스에서는 접근제어자를 반드시 public으로 해야한다
    // (오버라이딩 할 때에는 조상의 메서드보다 넓은 범위의 접근 제어자를 지정해야 한다)
    public void attack(Unit u) { /* 내용 생략 */}
}

class Unit {
    int currentHP;
    int x;
    int y;
}

interface Fightable extends Movable, Attackable { }
interface Movable  { void move(int x, int y); } // public abastact 생략
interface Attackable  { void attack(Unit u); }



interface Parseable {
    public abstract void parse(String fileName);
    // 구문 분석을 수행한다
}

class ParserManager {
    public static Parseable getParser(String type) {  // 리턴타입은 Parsebale 인터페이스
        if(type.equals("XML")) {
            return new XMLParser();
        } else {
            Parseable p = new HTMLParser();
            return p;
            // return new HTMLParser();
        }
    }
}

class XMLParser implements Parseable {
    public void parse(String fileName) {
        // 구문 분석 수행하는 코드
        System.out.println(fileName + "-XML parsing completed");
    }
}

class HTMLParser implements Parseable {
    public void parse(String fileName) {
        // 구문 분석 수행하는 코드
        System.out.println(fileName + "-HTML parsing completed");
    }
}

class ParserTest {
    public static void main(String[] args) {
        Parseable parser = ParserManager.getParser("XML");
        parser.parse("document.xml");
        parser = ParserManager.getParser("HTML");
        parser.parse("document2.html");
    }
}

class RepairableTest {
    public static void main(String[] args) {
        Tank tank = new Tank();
        Dropship dropship = new Dropship();

        Marine marine = new Marine();
        SCV scv = new SCV();
        scv.repair(tank);
        scv.repair(dropship);
//        scv.repair(marine);
    }
}

interface Repairable {}

class Unitt {
    int hitPoint;
    final int MAX_HP;
    Unitt(int hp) {
        MAX_HP = hp;
    }
}

class GroundUnit extends Unitt {
    GroundUnit(int hp) {
        super(hp);
    }
}

class AirUnit extends Unitt {
    AirUnit(int hp) {
        super(hp);
    }
}

class Tank extends GroundUnit implements Repairable {
    Tank() {
        super(150);
        hitPoint = MAX_HP;
    }
    public String toString() {
        return "Tank";
    }
}

class Dropship extends AirUnit implements Repairable {
    Dropship() {
        super(125);
        hitPoint = MAX_HP;
    }
    public String toString() {
        return "Dropship";
    }
}

class Marine extends GroundUnit {
    Marine() {
        super(40);
        hitPoint = MAX_HP;
    }
}

class SCV extends GroundUnit implements Repairable {
    SCV() {
        super(60);
        hitPoint = MAX_HP;
    }

    void repair(Repairable r) {
        if (r instanceof Unitt) {
            Unitt u = (Unitt)r; // 형변환하여 Unitt클래스에 정의된 멤버 활용할 수 있게 함
            while (u.hitPoint != u.MAX_HP) {
                u.hitPoint++;
            }
            System.out.println(u.toString() + "의 수리가 끝났습니다");
        }
    }
}

class A { // 유저
    public void methodA(I i) { // B와 A는 연관관계 x
        // A는 B의 이름도 알필요 없고, B가 없어도 된다
        // 단지 I와만 직접적으로 연관될 뿐이다.
        i.methodB();
    }
}

class B implements I{
    public void methodB() {
        System.out.println("methodB in B class");
    }
}

interface I {
    public abstract void methodB();
}

class InterfaceTest {
    public static void main(String[] args) {
        A a = new A();
        a.methodA(new B()); // I를 구현한 B의 객체가 매개변수로 주어진다
    }
}



class InterfaceTest2 {
    public static void main(String[] args) {
        X x = new X();
        x.methodX();
    }
}

class X{
    void methodX() {
        T t = InstanceManager.getInstance(); // 인스턴스를 제공받기에, 나중에 다른 클래스의 인스턴스로 변경되어도 X클래스의 변경없이 getInstance()만 변경하면 된다.
        t.methodY();
        System.out.println(t.toString()); // i로 Object클래스의 메서드 호출 가능
    }
}

interface T {
    public abstract void methodY();
}

class Y implements T{
    public void methodY() {
        System.out.println("methodY in Y class");
    }

    public String toString() { return "class Y"; }
}

class InstanceManager {
    public static T getInstance() {
        return new Y();
    }
}

class DefaultMethodTest {
    public static void main(String[] args) {
        Son s = new Son();
        s.method1();
        s.method2();
        MyInterface.staticMethod(); // 조상클래스의 메서드 사용과 같다
        MyInterface2.staticMethod();
    }
}

class Son extends Father implements MyInterface, MyInterface2 {
    public void method1() {
        System.out.println("method1() in child");
    }
}
class Father {
    public void method2() {
        System.out.println("method2() in Father");
    }
}

interface MyInterface {
    default void method1() {
        System.out.println("method1 () in MyInterface");
    }

    default void method2() {
        System.out.println("method2() in MyInterface");
    }

    static void staticMethod() {
        System.out.println("staticMethod() in MyInterface");
    }
}

interface MyInterface2 {
    default void method1() {
        System.out.println("method1() in MyInterface2");
    }

    static void staticMethod() {
        System.out.println("staticMethod() in MyInterface2");
    }
}