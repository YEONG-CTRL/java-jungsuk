package ch7;

import java.awt.*;
import java.awt.event.*;


public class Innerclass {
    class InstanceInner {
        int iv = 100;
//        static int cv = 100;
        final static int CONST = 100; // 예외적으로, final과 static이 동시에 붙은 변수는 상수이므로 모든 내부클래스에서 정의 가능하다.
    }

    static class StaticInner { // 내부 클래스 중, 스태틱 클래스만 static 멤버를 가질 수 있다
        int iv = 200;
        static int cv= 200;
    }
    void myMethod() {
        class LocalInner {
            int iv = 300;
//            static int cv = 300;
            final static int CONST = 300;
        }
    }

    public static void main(String[] args) {
        System.out.println(InstanceInner.CONST);
        System.out.println(StaticInner.cv);
    }
}

class InnerEx2 {
    class InstanceInner {}
    static class StaticInner {}

    // 인스턴스 멤버 간에는 서로 직접 접근이 가능하다
    InstanceInner iv = new InstanceInner();
    // static 멤버간에는 서로 직접 접근이 가능하다
    static StaticInner cv = new StaticInner();

    static void staticMethod() {
        // static 멤버는 인스턴스 멤버에 직접 접근할 수 없다
        // InstanceInner obj1 = new InstanceInner();
        StaticInner obj2 = new StaticInner();

        // 굳이 접근하려면 아래와 같이 객체를 생성해야 한다
        // 인스턴스클래스는 외부 클래스를 먼저 생성해야만 생성할 수 있다
        InnerEx2 outer = new InnerEx2();
        InstanceInner obj1 = outer.new InstanceInner();
    }
    void instanceMethod() {
        // 인스턴스메서드에서는 인스턴스멤버와 static멤버 모두 접근 가능하다
        InstanceInner obj1 = new InstanceInner();
        StaticInner obj2 = new StaticInner();
        // 메서드 내에 지역적으로 선언된 내부 클래스는 외부에서 접근할 수 없다
        // LocalInner lv = new LocalInner();
    }

    void myMethod() {
        class LocalInner {}
        LocalInner lv = new LocalInner();
    }
}

class InnerEx3 {
    private int outerIv = 0;
    static int outerCv  = 0;

    class InstanceInner {
        int iiv = outerIv; // 외부 클래스의 private멤버 접근가능하다
        int iiv2 = outerCv;
    }

    static class StaticInner {
        // 스태틱클래스는 외부 클래스의 인스턴스 멤버에 접근할 수 없다
//        int siv = outerIv;
        static int scv = outerCv;
    }

    void myMethod() {
        int lv = 0;
        final int LV = 0;

        class LocalInner {
            int liv = outerIv;
            int liv2 = outerCv;
            int liv3 = lv;
            int liv4 = LV;
        }
    }
}

class Outer {
    class InstanceInner {
        int iv = 100;
    }

    static class StaticInner {
        int iv = 200;
        static int cv = 300;
    }

    void myMethod() {
        class LocalInner {
            int iv = 400;
        }
    }
}

class InnerEx4 {
    public static void main(String[] args) {
        // 인스턴스클래스의 인스턴스를 생성하려면 먼저 외부 클래스의 인스턴스를 생성해야 한다
        Outer oc = new Outer();
        Outer.InstanceInner ii = oc.new InstanceInner();

        System.out.println("ii.iv : " + ii.iv);
        System.out.println("Outer.StaticInner.cv : " + Outer.StaticInner.cv);

        Outer.StaticInner si = new Outer.StaticInner();
        System.out.println("si.iv :" + si.iv);
    }
}

class InnerEx6 {
    Object iv = new Object() { void method() {} }; // 익명클래스 (new 조상클래스이름() {} )
    static Object cv = new Object() { void method() {} }; // 익명클래스

    void myMethod() {
        Object lv = new Object() { void method(){} }; // 익명클래스
    }
}

//class InnerEx7 {
//    public static void main(String[] args) {
//        Button b = new Button("Start");
//        b.addActionListener(new EventHandler());
//    }
//}
//class EventHandler implements ActionListener {
//    public void actionPerformed(ActionEvent event) {
//        System.out.println("ActionEvent occured!");
//    }
//}

class InnerEx7 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent occured!");
            }
        } // 익명 클래스의 끝
        );
    }
}
