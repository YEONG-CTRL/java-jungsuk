package ch6;


import jdk.nashorn.internal.ir.Block;

import javax.print.Doc;

class Data1 {
    int value;
}

class DataC {
    int value;

    DataC(int x) {
        value = x;
    }
}

public class Constructor {
    public static void main(String[] args) {
        Data1 d1 = new Data1();
//        Data2 d2 = new DataC(); // 컴파일 에러 발생
        // Data2에 Data2()생성자가 정의돼 있지 않아서 그렇다.
        // 이미 매개변수를 받는 Data2(int x)가 정의돼 있기에, 기본 생성자가 추가돼있지 않기 때문.
    }
}

class Car {
    String color;
    String gearType;
    int door;

    Car() {} // 생성자1

    Car(String c, String g, int d) { // 생성자2
        color = c;
        gearType = g;
        door = d;
    }
}

class CarTest {
    public static void main(String[] args) {
        Car c1 = new Car(); // 인스턴스 생성 후
        c1.color = "white"; // 별도 초기화
        c1.gearType = "auto";
        c1.door = 4;

        Car c2 = new Car("white", "auto", 4); // 인스턴스 생성 과 함께 초기화

        System.out.println("c1의 color =" + c1.color + ", gearType=" + c1.gearType + ", door="+ c1.door);
    }
}

class Mobile {
    String color;
    String gearType;
    int door;

    Mobile() {
        this("white", "auto", 4); // Mobile(String color, String gearType, int door) 호출
        // 자동차의 기본값은 흰색에 자동 기어, 문 개수 4개라는 뜻
    }

    Mobile(String color) {
        this(color, "auto", 4); // 이 this는 생성자
    }

//    Mobile(Mobile m) { // 인스턴스의 복사를 위한 생성자
//        color = m.color;
//        gearType = m.gearType;
//        door = m.door;
//    }
    Mobile(Mobile m) { // 위의 생성자를 this()로 다른 생성자 호출하여 간단하게 변경
        this(m.color, m.gearType, m.door);
    }

    Mobile(String color, String gearType, int door) {
        this.color = color; // 이 this는 인스턴스 가리키는 참조변수
        this.gearType = gearType;
        this.door = door;
    }
}

class MobileTest {
    public static void main(String[] args) {
        Mobile m1 = new Mobile();
        Mobile m2 = new Mobile("blue");

        System.out.println("m1의 color =" + m1.color + ", gearType=" + m1.gearType + ", door="+ m1.door);
        System.out.println("m2의 color =" + m2.color + ", gearType=" + m2.gearType + ", door="+ m2.door);
    }
}

class MobileTest2 {
    public static void main(String[] args) {
        Mobile m1 = new Mobile();
        Mobile m2 = new Mobile(m1);

        System.out.println("m1의 color =" + m1.color + ", gearType=" + m1.gearType + ", door="+ m1.door);
        System.out.println("m2의 color =" + m2.color + ", gearType=" + m2.gearType + ", door="+ m2.door); // 복사본

        m1.door = 100; // m1인스턴스변수 door값 변경
        System.out.println("m1.door = 100; 수행후");
        System.out.println("m1의 color =" + m1.color + ", gearType=" + m1.gearType + ", door="+ m1.door);
        // 복사이후에는 별도의 메모리공간에 존재하는 별도의 인스턴스
        System.out.println("m2의 color =" + m2.color + ", gearType=" + m2.gearType + ", door="+ m2.door);
    }
}

class BlockTest {
    static {
        System.out.println("static { }"); // 클래스 초기화 블럭
    }

    {
        System.out.println("{ }"); // 인스턴스 초기화 블럭
    }

    public BlockTest() {
        System.out.println("생성자");// 생성자
    }

    public static void main(String[] args) {
        System.out.println("BlockTest bt = new BlockTest();");
        BlockTest bt = new BlockTest();

        System.out.println("BlockTest bt2 = new BlockTest();");
        BlockTest bt2 = new BlockTest();
    }
}

class StaticBlockTest {
    static int[] arr = new int[10];//클래스변수

    static { // 클래스 초기화 블럭
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10 + 1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr["+i+"] :" + arr[i] );
        }
    }
}

class Product {
    static int count = 0;
    int serialNo;

    {
        ++count;
        serialNo = count;
    }
}

class ProductTest {
    public static void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        System.out.println("p1의 제품번호(serial no)는 " + p1.serialNo);
        System.out.println("p2의 제품번호(serial no)는 " + p2.serialNo);
        System.out.println("p3의 제품번호(serial no)는 " + p3.serialNo);
        System.out.println("생산된 제품의 수는 모두 " + Product.count + "개 입니다");
    }
}

class Document {
    static int count = 0;
    String name;

    Document () {
        this("제목없음" + ++count);// 문서명 지정하지 않았을때, 밑의 생성자 호출
    }

    Document(String name) { // 문서명 지정했을때
        this.name = name;
        System.out.println("문서 " + this.name + "가 생성되었습니다.");
    }
}

class DocumentTest {
    public static void main(String[] args) {
        Document d1 = new Document();
        Document d2 = new Document("자바.txt");
        Document d3 = new Document();
        Document d4 = new Document();
    }
}