package ch7;

public class Modifier {
    final int NUMBER; // final이 붙은 상수 선언과 함께 초기화하지 않고
    final String KIND; // 생성자에서 단 한번만 초기화할 수 있다
    static int width = 100;
    static int height = 250;

    Modifier (String kind, int num) {
        KIND = kind; // 매개변수로 넘겨받은 값으로 상수 KIND와 NUMBER초기화 한다(딱 한번!)
        NUMBER = num;
    }

    Modifier () {
        this("HEART", 1);
    }

    public String toString() {
        return KIND + " " + NUMBER;
    }
}

class FinalCardTest {
    public static void main(String[] args) {
        Modifier c = new Modifier("HEART", 10);
        System.out.println(c.KIND);
        System.out.println(c.NUMBER);
        System.out.println(c); // c.toString 호출과 같다
    }
}

class TimeTest {
    public static void main(String[] args) {
        Time t = new Time(12,35,30);
        System.out.println(t);
//        t.hour = 13;   => hour의 접근제어자 private이므로 접근할 수 없다.
        t.setHour(t.getHour()+1); // 멤버변수로 직접 접근은 불가능하고, setter 메서드를 통한 접근만이 허용된다
        t.setMinute(23); // 멤버변수로 직접 접근은 불가능하고, setter 메서드를 통한 접근만이 허용된다
        System.out.println(t);
    }
}

class Time {
    private int hour, minute, second;

    Time(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public int getHour() { return hour; }
    public void setHour(int hour) {
        if (hour < 0 || hour > 23) return;
        this.hour = hour;
    }
    public int getMinute() { return minute; }
    public void setMinute(int minute) {
        if (minute < 0 || minute >59) return;
        this.minute = minute;
    }

    public int getSecond() { return second; }
    public void setSecond(int second) {
        if (second < 0 || second >59) return;
        this.second = second;
    }
    public String toString() {
        return hour + ":" + minute + ":" + second;
    }
}

final class Singleton { // 생성자가 private인 경우, 자손클래스에서 조상클래스의 생성자를 호출할 수 없으므로, 다른 클래스의 조상이 될 수 없다. 따라서 클래스 앞에 final을 추가하여 상속할 수 없는 클래스임을 알린다.
    private static Singleton s = new Singleton();  // getInstance()에서 사용될 수 있또록, 인스턴스가 미리 생성돼야 하므로 static이어야 한다.

    private Singleton() { // 생성자의 접근제어자가 private이기 때문에, 외부에서 생성자에 접근할 수 없으므로 인스턴스를 생성할 수 없게 된다.
        // ... 그래도 클래스 내부에서는 인스턴스를 생성할 수 있다
    }

    public static Singleton getInstance() { // 인스턴스를 반환해주는 public 메서드를 제공(외부에서 생성자 접근 못하는 대신)
        // 인스턴스를 새성하지 않고도 호출할 수 있도록 static이어야 함
        if (s == null) {
            s = new Singleton();
        }
        return s;
    }
}

class SingletonTest {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        System.out.println(s);
    }
}


