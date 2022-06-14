package ch9.ObejctClass;

import java.util.Arrays;

class Person {
    long id;

    public boolean equals(Object obj) { // equals메서드가 인스턴스 주소값아닌 멤버변수 id값 비교하도록 오버라이딩
        if (obj instanceof Person)
            return id == ((Person)obj).id; // obj는 Object타입이므로 id값 참조하기 위하여 Person타입으로 형변환
        else
            return false;
    }

    Person(long id) {
        this.id = id;
    }
}

public class ObjectCalss {
    public static void main(String[] args) {
        Person p1 = new Person(8011081111222L);
        Person p2 = new Person(8011081111222L);

        if (p1==p2)
            System.out.println("p1과 p2는 같은 사람입니다");
        else
            System.out.println("p1과 p2는 다른 사람입니다");

        if(p1.equals(p2))
            System.out.println("p1과 p2는 같은 사람입니다");
        else
            System.out.println("p1과 p2는 다른 사람입니다");
    }
}

class HashCodeEx {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");

        System.out.println(str1.equals(str2)); // String 클래스의 equals는 오버라이딩으로 문자열 값 비교하도록 돼 있다
        System.out.println(str1.hashCode()); // String클래스는 문자열의 내용이 같으면 동일한 해시코드 반환하도록 hashCode메서드가 오버라이딩 돼 있다
        System.out.println(str1.hashCode());
        System.out.println(System.identityHashCode(str1)); // System.identityHashCode(Object x)는 Object클래스의 hashcode메서드처럼 객체의 주소값으로 해시코드 생성하기에,
        // 모든 객체에 대해 항상 다른 해시코드 값 반환할 것을 보장한다.
        System.out.println(System.identityHashCode(str2));
    }
}

class Card{
    String kind;
    int number;

    Card() {
        this("SPADE", 1);
    }
    Card(String kind, int number) {
        this.kind = kind;
        this.number = number;
    }
}

class CardToString {
    public static void main(String[] args) {
        Card c1 = new Card();
        Card c2 = new Card();

        System.out.println(c1.toString()); // 오버라이딩 않으면 클래스이름+16진수 해시코드
        System.out.println(c2.toString());
    }
}

class ToStringTest {
    public static void main(String[] args) {
        String str = new String("KOREA");
        java.util.Date today = new java.util.Date();

        System.out.println(str);
        System.out.println(str.toString()); // String 인스턴스가 가진 문자열 반환하도록 오버라이딩 돼있음
        System.out.println(today);
        System.out.println(today.toString()); // Date 인스턴스가 갖는 날짜와 시간 문자열로 변환하여 반환하도록 오버라이딩 돼 있음
    }
}

class Point implements Cloneable { // clone()사용하려면 복제한 클래스가 Cloneable인터페이스 구현해야하고, clone()을 오버라이딩 하면서 접근제어자를 protected에서 public으로 변경해야 한다
    int x, y;

    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    public Object clone() { // 조상 클래스의 clone()을 호출하는 try-catch문
        Object obj = null;
        try {
            obj = super.clone(); // 조상클래스 호출
        } catch (CloneNotSupportedException e) {}
        return obj;
    }
}

class CloneEx1 {
    public static void main(String[] args) {
        Point original = new Point(3,5);
        Point copy = (Point)original.clone();// clone()의 반환타입 Object->Point
        System.out.println(original);
        System.out.println(copy);
    }
}

class CloneEx2 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5}; // 배열은 Object클래스를 상속받으며, Cloneable 인터페이스와 Serializable 인터페이스가 구현돼 있다.
        int[] arrClone = arr.clone(); // Object에서는 protected인 clone()을 배열은 public으로 오버라이딩함
        arrClone[0] = 6;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
    }
}

final class Card2 {
    String kind;
    int num;

    Card2() {
        this("SPADE", 1);
    }

    Card2 (String kind, int num) {
        this.kind = kind;
        this.num = num;
    }

    public String toString() {
        return kind + ":" + num;
    }
}

class ClassEx1 {
    public static void main(String[] args) throws Exception {
        Card2 c = new Card2("HEART", 3);
        Card2 c2 = Card2.class.newInstance(); // 클래스 객체 이용하여 객체 생성

        Class cobj = c.getClass();

        System.out.println(c);
        System.out.println(c2);
        System.out.println(cobj.getName());
        System.out.println(cobj.toGenericString());
        System.out.println(cobj.toString());
    }
}