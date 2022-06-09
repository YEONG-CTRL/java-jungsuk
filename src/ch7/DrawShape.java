package ch7;


class DrawShape {
    public static void main(String[] args) {
        Point[] p = {   new Point(100,100),
                new Point(140, 50),
                new Point(200, 100) };
        Triangle t = new Triangle(p);
        Circle   c = new Circle(new Point(150,150), 50);
        // Point p = new Point(150,150);
        // Circle c = new Circle(p,50)

        t.draw();
        c.draw();
    }
}

class Shape {
    String color = "black";
    void draw() {
        System.out.printf("[color =%s%n]", color);
    }
}

class Point {
    int x;
    int y;

    Point (int x, int y) { // 생성자
        this.x = x;
        this.y = y;
    }
    Point() {  // 매개변수없을때 기본값 생성자 -> 위의 생성자에 0,0 기본값
        this(0,0);
    }
    String getXY() {
        return "("+x+","+y+")"; // x와 y의 값을 문자열로 반환
    }
}

class Circle extends Shape { // circle과 shape는 상속관계
    Point center; // 원점좌표 // circle과 point는 포함관계
    int r;          // 반지름

    Circle() {
        this(new Point(0,0), 100);
    }
    Circle (Point center, int r) {
        this.center = center;
        this.r = r;
    }

    void draw() {  // Shape클래스에 정의된 draw()말고 새로운 draw() 오버라이딩 - 원의 정보 출력
        System.out.printf("[center=(%d, %d), r=%d, color=%s]%n", center.x, center.y, r, color);
    }
}

class Triangle extends Shape {
    Point[] p = new Point[3]; // 3개의 Point 인스턴스를 담을 배열을 생성

    Triangle(Point[] p) {
        this.p = p;
    }

    void draw() {
        System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]%n", p[0].getXY(), p[1].getXY(), p[2].getXY() , color);
    }
}

class DeckTest {
    public static void main(String[] args) {
        Deck d = new Deck(); // 카드 덱을 만든다
        Card c = d.pick(0); // 섞기 전 제일 위의 카드를 뽑는다
        System.out.println(c); // =  println(c.toString())

        d.shuffle(); // 카드를 섞는다
        c = d.pick(0); // 섞은 이후 제일 위의 카드를 뽑는다
        System.out.println(c);
    }
}

class Deck {
    final int CARD_NUM = 52; // 카드 개수
    Card cardArr[] = new Card[CARD_NUM]; // Card객체 배열

    Deck () { // Deck의 카드 초기화
        int i = 0;

        for (int k = Card.KIND_MAX; k > 0; k--) {
            for (int n = 0; n < Card.NUM_MAX; n++) {
                cardArr[i++] = new Card(k, n + 1);
            }
        }
    }

    Card pick(int index) { // 지정된 위치에 있는 카드 하나 꺼내서 반환
        return cardArr[index];
    }

    Card pick() { // Deck에서 카드 하나 선택
        int index = (int) (Math.random() * CARD_NUM);
        return pick(index);
    }

    void shuffle() {  // 카드의 순서 섞기
        for (int i = 0; i < cardArr.length; i++) {
            int r = (int) (Math.random() * CARD_NUM);

            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }
}

class Card {
    static final int KIND_MAX = 4; // 카드 무늬의 수
    static final int NUM_MAX = 13; // 무늬별 카드 수

    static final int SPADE = 4;
    static final int DIAMOND = 4;
    static final int HEART = 4;
    static final int CLOVER = 4;
    int kind;
    int number;

    Card() {
        this(SPADE, 1); // 기본값
    }

    Card(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    public  String toString() { // 인스턴스 정보 문자열로 반환, 없으면 그냥 객체주소 나옴 , Object클래스의 toString() 오버라이딩
        String[] kinds = {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
        String numbers = "012345678XJQK";

        return "kind : " + kinds[this.kind] + ", number : " + numbers.charAt(this.number);
    }
}

