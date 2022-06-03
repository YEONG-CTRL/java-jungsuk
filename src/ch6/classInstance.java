package ch6;

class TV{
    String color;
    boolean power;
    int channel;

    void power() {power =! power;}
    void channelUp() { ++channel; }
    void channelDown() { --channel; }
}

public class classInstance {
    public static void main(String[] args) {
        TV t;
        t = new TV();
        t.channel = 7;
        t.channelDown();
        System.out.println("현재 채널은"+ t.channel + "입니다.");
    }
}

class TvTest2 {
    public static void main(String[] args) {
        TV t1 = new TV();
        TV t2 = new TV();
        System.out.println("t1의 channel값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel값은 " + t2.channel + "입니다.");

        t1.channel = 7;
        System.out.println("t1의 channel 값을 7로 변경하였습니다");

        System.out.println("t1의 channel 값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel 값은 " + t2.channel + "입니다.");
    }
}

class TvTest3 {
    public static void main(String[] args) {
        TV t1 = new TV();
        TV t2 = new TV();
        System.out.println("t1의 channel 값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel 값은 " + t2.channel + "입니다.");

        t2 = t1;
        t1.channel = 7;
        System.out.println("t1의 channel값을 7로 변경하였습니다");
        System.out.println("t1의 channel 값은 " + t1.channel + "입니다.");
        System.out.println("t2의 channel 값은 " + t2.channel + "입니다.");
    }
}

class TvTest4 {
    public static void main(String[] args) {
        TV [] tvArr = new TV[3];

        for (int i=0; i < tvArr.length; i++) {
            tvArr[i] =  new TV();
            tvArr[i].channel = i + 10;
        }

        for (int i=0; i < tvArr.length; i++) {
            tvArr[i].channelUp();
            System.out.printf("tvArr[%d].channel=%d%n", i , tvArr[i].channel);
        }
    }
}

class Time {
    private  int hour;
    private  int minute;
    private  float second;

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public float getSecond() { return second; }

    public void setHour(int h) {
        if (h < 0 || h > 23) return;
        hour = h;
    }

    public void setMinute(int m) {
        if (m < 0 || m > 59) return;
        minute = m;
    }

    public void setSecond(float s) {
        if (s < 0.0f || s > 59.99f) return;
        second = s;
    }
}
class TimeInstance {
    public static void main(String[] args) {
        Time t = new Time();
        t.setMinute(5);
        t.setHour(8);
        System.out.println(t.getMinute());
        System.out.println(t.getHour());
    }
}