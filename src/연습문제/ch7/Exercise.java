package 연습문제.ch7;

class SutdaDeck {
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];
    SutdaDeck() {
        for (int i=0; i< cards.length; i++) {
//            if (i==0 || i==2 || i == 7) {
//                cards[i] = new SutdaCard(i+1, true);
//            } else {
//                cards[i] = new SutdaCard(i + 1, false);
//            }
            int num = i % 10 + 1;
            boolean isKwang = (i<10) && (num==1||num==3||num==8);

            cards[i] = new SutdaCard(num, isKwang);
        }
    }

    public void shuffle() {
        for (int i=0; i< cards.length; i++) {
            int rand = (int)(Math.random() * cards.length);
            SutdaCard tmp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = tmp;
        }
    }

    public SutdaCard pick(int index) {
        if(index<0 || index >= cards.length) // index의 유효성 검사
            return null;
        return cards[index];
    }

    public SutdaCard pick() {
        return cards[(int)(Math.random() * cards.length)];
    }


}
class SutdaCard {
    int num;
    boolean isKwang;
    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }
    // info()대신 Object클래스의 toString()을 오버라이딩했다.
    public String toString() {
        return num + ( isKwang ? "K":"");
    }
}
class Exercise {
    public static void main(String args[]) {
        SutdaDeck deck = new SutdaDeck();
        for(int i=0; i < deck.cards.length;i++)
            System.out.print(deck.cards[i]+",");
    }
}

/* 오버라이딩은 조상 클래스로부터 상속받은 메서드의 내용을 변경하는 것이고, 이는 상속받은 메서드를 자손클래스에 맞게 변경해 사용하기 위함이다 */

class Product
{
    int price; // 제품의 가격
    int bonusPoint; // 제품구매 시 제공하는 보너스점수

    Product() {}

    Product(int price) {
        this.price = price;
        bonusPoint =(int)(price/10.0);
    }
}
class Tv extends Product {
    Tv() {} /* 컴파일러가 super() 를 호출 */ // super()는 부모의 기본 생성자를 호출
    // Product(부모클래스)에는 기본생성자가 없는데, 이를 오버라이딩해서 사용해서 컴파일ㅇ에러가 난다
    // Product클래스에 기본 생성자 Product() {}를 추가해줘야 한다
    // 아니면 Tv(int price) { super(price); }로 이미 있는 생성자를 호출하게 함

    public String toString() {
        return "Tv";
    }
}
class Exercise5 {
    public static void main(String[] args) {
        Tv t = new Tv();
    }
}
// 자손 클래스의 인스턴스를 생성하면, 자손의 멤버와 조상의 멤버가 모두 합쳐진 하나의 인스턴스가 생성된다
// 이때 조상 클래스 멤버의 초기화 작업을 위하여 자손 클래스의 생성자에서 조상 클래스의 생성자가 호출돼야 함

class Parent { //7번
    int x=100;
    Parent() { // 3
        this(200); // parent클래스의 인스턴스 변수 x는 200이 됨
    }
    Parent(int x) { // 4 -> Object(): 조상인 Object클래스의 기본 생성자를 호출하는 super()를 컴파일러가 자동 추가
        this.x = x;
    }
    int getX() {
        return x;
    }
}
class Child extends Parent {
    int x = 3000;
    Child() { // 1
        this(1000); // child 클래스의 인스턴스 변수 x는 1000
    }
    Child(int x) { // 2
        this.x = x;
    }
}
class Exercise7_7 {
    public static void main(String[] args) {
        Child c = new Child();
        System.out.println("x="+c.getX()); // getX는 Parent클래스의 인스턴스 변수 x를 의미
    }
}

class MyTv2 {
    private boolean isPowerOn;
    private int channel;
    private int volume;
    private int prevChannel;

    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;

    public int getChannel() {
        return channel;
    }

    public void setChannel(int ch) {
        if (MIN_CHANNEL> ch || ch > MAX_CHANNEL) {
            return;
        }
        prevChannel = this.channel;
        this.channel = ch;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int vo) {
        if (MIN_VOLUME> vo || vo > MAX_VOLUME) {
            return;
        }
        this.volume = vo;
    }

    public void gotoPrevChannel() {
        setChannel(prevChannel);
    }
}
class Exercise7_10 {
    public static void main(String args[]) {
        MyTv2 t = new MyTv2();
        t.setChannel(10);
        System.out.println("CH:"+t.getChannel());
        t.setVolume(20);
        System.out.println("VOL:"+t.getVolume());
    }
}