package ch13;

import javax.swing.*;
import java.util.Iterator;
import java.util.Map;

public class ThreadEx1 {
    public static void main(String[] args) {
        ThreadEx1_1 t1 = new ThreadEx1_1(); // Thread자손 클래스의 인스턴스 생성

//        Runnable r = new ThreadEx1_2(); // Runnable을 구현한 클래스의 인스턴스를 생성
//        Thread t2  = new Thread(r); // 생성자 Thread
        Thread t2 = new Thread(new ThreadEx1_2()); // 위의 두 줄을 한줄로

        t1.start(); // 쓰레드 실행 -> 하나의 쓰레드에 대해 start()가 한번만 호출될 수 있다
        t2.start(); // start()는 새로운 호출스택을 생성하여 run()을 첫번째로 올린다
    }
}

class ThreadEx1_1 extends Thread { // Thread클래스 상속
    public void run() {
        for (int i=0; i<5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}

class ThreadEx1_2 implements Runnable { // Runnable 인터페이스 구현
    public void run() {
        for (int i =0; i<5; i++) {
            System.out.println(Thread.currentThread().getName()); // Thread.currentTread()는 현재 실행중인 Thread의 참조를 반환한다
        }
    }
}

class ThreadEx2 {
    public static void main(String[] args) throws Exception {
        ThreadEx2_1 t1 = new ThreadEx2_1();
        t1.start();
    }
}

class ThreadEx2_1 extends Thread {
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception(); // 예외발생시킴
        } catch (Exception e) {
            e.printStackTrace(); // 호출스택의 첫번째 메서드가 main메서드가 아닌 run()메서드이다. main쓰레드는 이미 종료됨
        }
    }
}

class ThreadEx3 {
    public static void main(String[] args) throws Exception {
        ThreadEx3_1 t1 = new ThreadEx3_1();
        t1.run();
    }
}

class ThreadEx3_1 extends Thread {
    public void run() {
        throwException();
    }
    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace(); // start()로 새로운 쓰레드를 실행시킨것이 아니라 run()메서드만 호출했기에, main쓰레드의 호출스택안에있다.
        }
    }
}

class ThreadEx4 {
    public static void main(String[] args) { // - 를 출력하는 작업과 | 를 출력하는 작업을 "하나의 쓰레드"가 연속적으로 처리하는 시간을 측정
        long startTime = System.currentTimeMillis();

        for (int i=0; i<300; i++)
            System.out.printf("%s", new String("-"));
        System.out.println("소요시간1:" + (System.currentTimeMillis()-startTime)); // 1:30 소요

        for (int i=0; i<300; i++)
            System.out.printf("%s", new String("|"));

        System.out.println("소요시간2:" + (System.currentTimeMillis()-startTime)); // 2:33 소요
    }
}

class ThreadEx5 { //위의 작업을 멀티쓰레딩으로 처리하여 시간 측정
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx5_1 th1 = new ThreadEx5_1();
        th1.start();
        startTime = System.currentTimeMillis();

        for(int i = 0; i < 300; i ++)
            System.out.printf("%s", new String("-"));

        System.out.println("소요시간1:" + (System.currentTimeMillis()- ThreadEx5.startTime)); // 1:29
    }
}

class ThreadEx5_1 extends Thread {
    public void run() {
        for (int i=0; i<300; i++)
            System.out.printf("%s", new String("|"));
        System.out.println("소요시간1:" + (System.currentTimeMillis()- ThreadEx5.startTime)); // 1:36 - 번갈아가면서 실행하여서 거의 동시에 끝남
    }
}

class ThreadEx6 {
    public static void main(String[] args) throws Exception {
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다");

        for (int i=10; i>0 ; i--) { // 하나의 쓰레드로 입력받고, 숫자 출력하기에 사용자가 입력 마친 이후에야 화면에 숫자가 출력된다
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1초간 시간지연
            } catch (Exception e) {}
        }
    }
}

class ThreadEx7 {
    public static void main(String[] args) throws Exception {
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start(); // 사용자로부터 입력받는 부분과 화면에 숫자 출력하는 부분을 두개의 쓰레드로 나눠서 처리했기에,
        // 사용자가 입력 마치지 않았어도 화면에 숫자 출력

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다");
    }
}
class ThreadEx7_1 extends Thread {
    public void run() {
        for (int i = 10; i > 0; i--) { // 하나의 쓰레드로 입력받고, 숫자 출력하기에 사용자가 입력 마친 이후에야 화면에 숫자가 출력된다
            System.out.println(i);
            try {
                sleep(1000); // 1초간 시간지연
            } catch (Exception e) {
            }
        }
    }
}

class ThreadEx8 {
    public static void main(String[] args) {
        ThreadEx8_1 th1 = new ThreadEx8_1();
        ThreadEx8_2 th2 = new ThreadEx8_2();
        th2.setPriority(7); // th1과 2 모두 main메서드의 우선순위 5 상속, 이후 th2의 우선순위를 7로 변경한 다음 start()를 호출해서 쓰레드를 실행

        System.out.println("Priority of th1(-) : " + th1.getPriority());
        System.out.println("Priority of th2(|) : " + th2.getPriority());
        th1.start();
        th2.start();
    }
}

class ThreadEx8_1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("-");
            for (int x=0; x < 10000000; x++);
        }
    }
}
class ThreadEx8_2 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("|");
            for(int x=0; x < 10000000; x++); // 우선순위가 높아지면 작업이 한번에 끝날 수 있기에, 아무것도 하지 않는 반복문 추가하여 작업시간 증가시킴
        }
    }
}

class ThreadEx9 {
    public static void main(String[] args) throws Exception {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup grp1 = new ThreadGroup("Group1");
        ThreadGroup grp2 = new ThreadGroup("Group1");

        ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");

        grp1.setMaxPriority(3);

        Runnable r = new Runnable() {
            public void run() {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {}
            }
        };

        new Thread(grp1,    r, "th1").start();
        new Thread(subGrp1, r, "th2").start();
        new Thread(grp2,    r, "th3").start();

        System.out.println(">>List of ThreadGroup : " + main.getName() + ", Active ThreadGroup :" + main.activeGroupCount() + ", Active Thread: " + main.activeCount());
        main.list();
    }
}

class ThreadEx10 implements Runnable {
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadEx10());
        t.setDaemon(true); // 이 부분이 없으면 종료되지 않는다
        t.start();

        for (int i=1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(i);

            if (i==5)
                autoSave = true;
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(3 * 1000); // 3초간 쉬고
            } catch (InterruptedException e) {}

            if (autoSave) {
                autoSave(); // auto save의 값이 true이면 autoSave()를 호출한다
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장됐습니다.");
    }
}

class ThreadEx11 {
    public static void main(String[] args) throws Exception {
        ThreadEx11_1 t1 = new ThreadEx11_1("Thread1");
        ThreadEx11_2 t2 = new ThreadEx11_2("Thread2");
        t1.start();
        t2.start();
    }
}

class ThreadEx11_1 extends Thread {
    ThreadEx11_1(String name) {
        super(name);
    }

    public void run() {
        try {
            sleep(5*1000);
        } catch (InterruptedException e) {}
    }
}

class ThreadEx11_2 extends Thread {
    ThreadEx11_2(String name) {
        super(name);
    }

    public void run() {
        Map map     = getAllStackTraces();
        Iterator it = map.keySet().iterator();

        int x=0;
        while(it.hasNext()) {
            Object obj = it.next();
            Thread t = (Thread)obj;
            StackTraceElement[] ste = (StackTraceElement[]) (map.get(obj));

            System.out.println("[" + ++x + "] name : " + t.getName() + ", group : " + t.getThreadGroup().getName() + ", daemon : " + t.isDaemon());

            for (int i=0 ; i < ste.length; i++) {
                System.out.println(ste[i]);
            }

            System.out.println();
        }
    }
}