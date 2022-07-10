package 연습문제.ch6;

public class Student {
    String name;
    int    ban;
    int    no;
    int    kor;
    int    eng;
    int    math;

    Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    int getTotal() {
        return kor + eng + math;
    }

    float getAverage() {
        return (int) (getTotal() /3f * 10 + 0.5) / 10.0f;
    }

    String info() {
        return name + "," + ban + "," + no + "," + kor + "," + eng + "," + math + "," + getTotal() + "," + getAverage();
    }
}

class Exercise6_4 {
    public static void main(String args[]) {
        Student s = new Student("홍길동",1,1,100,60,76);
        System.out.println(s.info());
    }
}


class Exercise6_6 {
    // 두 점 (x,y)와 (x1,y1)간의 거리를 구한다.
    static double getDistance(int x, int y, int x1, int y1) {
        return Math.sqrt((x1-x) * (x1-x) + (y1-y) * (y1-y));
        // 제곱을 계산하기 위해 Math.pow(double a, double b) 메서드를 호출하는 것은 곱셈연산보다 비용이 많이드는 작업이다
    }
    public static void main(String args[]) {
        System.out.println(getDistance(1,1,2,2));
    }
}

class MyPoint {
    int x;
    int y;
    MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Exercise6_6의 static메서드를 인스턴스 메서드로 변경
    double getDistance(int x1, int y1) {  // 인스턴스 변수 x,y를 사용해서 작업하므로 x1과 y1만 제공받으면 된다 => 인스턴스 변수를 사용했기에 static 붙일 수 없다
        return Math.sqrt((x-x1) * (x-x1) + (y-y1) * (y-y1));
        // 제곱을 계산하기 위해 Math.pow(double a, double b) 메서드를 호출하는 것은 곱셈연산보다 비용이 많이드는 작업이다
    }
}
class Exercise6_7 {
    public static void main(String args[]) {
        MyPoint p = new MyPoint(1,1);
// p와 (2,2)의 거리를 구한다.
        System.out.println(p.getDistance(2,2));
    }
}

class PlayingCard { // 8번
    int kind; // 인스턴스 변수
    int num; // 인스턴스 변수
    static int width; // 클래스 변수
    static int height; // 클래스 변수
    PlayingCard(int k, int n) { // 객체를 초기화(생성 아님) 할때 사용할 목적으로 생성자 사용 / 생성은 new 연산자가 담당
        kind = k; // k 지역변수
        num = n;  // n 지역변수
    }
    public static void main(String args[]) {  // args 지역변수
        PlayingCard card = new PlayingCard(1,1); // card 지역변수
    }
}

class Marine { // 9번
    int x=0, y=0; // Marine의 위치좌표(x,y)
    int hp = 60; // 현재 체력
    static int weapon = 6; // 공격력
    static int armor = 0; // 방어력
    static void weaponUp() {
        weapon++;
    }
    static void armorUp() {
        armor++;
    }
    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Exercise20
{
    static int[] shuffle(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        for (int i=0; i< arr.length; i++) {
            int rand = (int) (Math.random() * arr.length);
            int tmp = arr[i];
            arr[i] = arr[rand];
            arr[rand] = tmp;
        }
        return arr;
    }
    public static void main(String[] args)
    {
        int[] original = {1,2,3,4,5,6,7,8,9};
        System.out.println(java.util.Arrays.toString(original));
        int[] result = shuffle(original);
        System.out.println(java.util.Arrays.toString(result));
    }
}

class MyTv { //21
    boolean isPowerOn;
    int channel;
    int volume;
    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;
    void turnOnOff() {
        isPowerOn = !isPowerOn;
    }

    void volumeUp() {
        if (volume < MAX_VOLUME) {
            volume += 1;
        }

    }
    void volumeDown() {
// (3) volume의 값이 MIN_VOLUME보다 클 때만 값을 1감소시킨다.
        if (volume > MIN_VOLUME) {
            volume -= 1;
        }
    }
    void channelUp() {
// (4) channel의 값을 1증가시킨다.
// 만일 channel이 MAX_CHANNEL이면, channel의 값을 MIN_CHANNEL로 바꾼다.
        if (channel == MAX_CHANNEL) {
            channel = MIN_CHANNEL;
        }
        channel += 1;
    }
    void channelDown() {
// (5) channel의 값을 1감소시킨다.
// 만일 channel이 MIN_CHANNEL이면, channel의 값을 MAX_CHANNEL로 바꾼다.
        if (channel == MIN_CHANNEL) {
            channel = MAX_CHANNEL;
        }
        channel -= 1;
    }
} // class MyTv
class Exercise6_21 {
    public static void main(String args[]) {
        MyTv t = new MyTv();
        t.channel = 100;
        t.volume = 0;
        System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
        t.channelDown();
        t.volumeDown();
        System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
        t.volume = 100;
        t.channelUp();
        t.volumeUp();
        System.out.println("CH:"+t.channel+", VOL:"+ t.volume);
    }
}

class Exercise22 {
    public static boolean isNumber(String str) {
        if (str == null || str.equals("")) // 유효성체크
            return false;

        for (int i=0; i < str.length(); i++) {
            if ('0' > str.charAt(i) || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String str = "123";
        System.out.println(str+"는 숫자입니까? "+isNumber(str));
        str = "1234o";
        System.out.println(str+"는 숫자입니까? "+isNumber(str));
    }
}

class Exercise6_23{
    public static int max(int[] arr) {
        if (arr == null || arr.length ==0)
            return -999999;

        int maxy = 0;
        for (int i=0; i<arr.length; i++) {
            if (maxy < arr[i])
                maxy = arr[i];
        }
        return maxy;
    }
    public static void main(String[] args)
    {
        int[] data = {3,2,9,4,7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최대값:"+max(data));
        System.out.println("최대값:"+max(null));
        System.out.println("최대값:"+max(new int[]{})); // 크기가 0인 배열
    }
}

class Exercise24
{
    public static int abs(int value) {
//        if (value == 0) {
//            return 0;
//        } else if (value < 0) {
//            return value * -1;
//        } else {
//            return value;
//        }
        return value >= 0 ? value : -value;
    }
    public static void main(String[] args)
    {
        int value = 5;
        System.out.println(value+"의 절대값:"+abs(value));
        value = -10;
        System.out.println(value+"의 절대값:"+abs(value));
    }
}