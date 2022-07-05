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

class ThreadEx12 {
    public static void main(String[] args) {
        ThreadEx12_1 th1 = new ThreadEx12_1();
        ThreadEx12_2 th2 = new ThreadEx12_2();
        th1.start();
        th2.start();

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}

        System.out.print("<<main 종료>>");
    }
}

class ThreadEx12_1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++)
            System.out.print("-");
        System.out.print("<<th1 종료>>");
    }
}
class ThreadEx12_2 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++)
            System.out.print("|");
        System.out.print("<<th2 종료>>");
    }
}


class ThreadEx13 {
    public static void main(String[] args) throws Exception {
        ThreadEx13_1 th1 = new ThreadEx13_1();
        th1.start();
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다");
        th1.interrupt(); // 사용자의 입력이 끝나면 interrupt
        System.out.println("isInterrupted(): " + th1.isInterrupted());
    }
}

class ThreadEx13_1 extends Thread {
    public void run() {
        int i = 10;
        while(i!=0 && !isInterrupted()) {
            System.out.println(i--);
            for(long x=0; x<2500000000L;x++); // 시간지연
        }
        System.out.println("카운트가 종료됐습니다"); // 입력끝나고 interrupt걸리면 카운트 끝남
    }
}

class ThreadEx14 {
    public static void main(String[] args) throws Exception {
        ThreadEx14_1 th1 = new ThreadEx14_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다");
        th1.interrupt(); // 사용자의 입력이 끝나면 interrupt, 근데 카운트가 종료되지 않는다
        System.out.println("isInterrupted(): " + th1.isInterrupted());
    }
}

class ThreadEx14_1 extends Thread {
    public void run() {
        int i = 10;

        while(i!=0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000); // 1초 지연
            } catch (InterruptedException e) { }  // interrupt(); 여기 추가하면 interrupted상태를 다시 true로 바꿔주기에 이 경우는 멈춘다
            // 인터럽트 호출하면 InterruptedException이 발생해서 쓰레드의 interrupted상태 false로 자동초기화
            // 따라서 카운트 끝나지 않고 이어짐
        }
        System.out.println("카운트가 종료됐습니다");
    }
}

class ThreadEx15 {
    public static void main(String[] args) {
        RunImplEx15 r = new RunImplEx15();
        Thread th1 = new Thread(r, "*");
        Thread th2 = new Thread(r, "**");
        Thread th3 = new Thread(r, "***");
        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend(); // 데드락 일으키기 쉬워서 Deprecated됨. 사용하지 않는 것이 좋다
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume(); // th1 다시 동작
            Thread.sleep(3000);
            th1.stop(); // th1 강제종료
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {}
    }
}

class RunImplEx15 implements Runnable {
    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}

class ThreadEx16 {
    public static void main(String[] args) {
        RunImplEx16 r1 = new RunImplEx16();
        RunImplEx16 r2 = new RunImplEx16();
        RunImplEx16 r3 = new RunImplEx16();
        Thread th1 = new Thread(r1, "*"); // 각 쓰레드가 각기 다른 실행상태 가질 수 있게
        Thread th2 = new Thread(r2, "**");  // 여러 RunImplEx16 객체를 사용(하나의 객체 공유 x)
        Thread th3 = new Thread(r3, "***");
        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            r1.suspend();
            Thread.sleep(2000);
            r2.suspend();
            Thread.sleep(3000);
            r1.resume();
            Thread.sleep(3000);
            r1.stop();
            r2.stop();
            Thread.sleep(2000);
            r3.stop();
        } catch (InterruptedException e) {}
    }
}
class RunImplEx16 implements Runnable {
    volatile boolean suspended  = false;
    volatile boolean stopped    = false;
    public void run() { // suspended와 stopped 인스턴스 변수를 통해 쓰레드의 작업을 중지 - 재개 - 종료하도록 함
        while(!stopped) {
            if(!suspended) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }
        System.out.println(Thread.currentThread().getName() + " - stopped");
    }
    public void suspend() { suspended = true; }
    public void resume() { suspended = false; }
    public void stop() { stopped = true; }
}

class ThreadEx17 {
    public static void main(String[] args) {
        ThreadEx17_1 th1 = new ThreadEx17_1("*");
        ThreadEx17_1 th2 = new ThreadEx17_1("**");
        ThreadEx17_1 th3 = new ThreadEx17_1("***");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {}
    }
}
class ThreadEx17_1 implements Runnable {
    volatile boolean suspended  = false;
    volatile boolean stopped    = false;

    Thread th;

    ThreadEx17_1(String name) {
        th = new Thread(this, name); // Thread(Runnable r, String name)
    }
    public void run() {
        while(!stopped) {
            if(!suspended) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }
        System.out.println(Thread.currentThread().getName() + " - stopped");
    }
    public void suspend() { suspended = true; }
    public void resume() { suspended = false; }
    public void stop() { stopped = true; }
    public void start() { th.start(); }
}

class ThreadEx18 {
    public static void main(String[] args) {
        ThreadEx18_1 th1 = new ThreadEx18_1("*");
        ThreadEx18_1 th2 = new ThreadEx18_1("**");
        ThreadEx18_1 th3 = new ThreadEx18_1("***");

        th1.start();
        th2.start();
        th3.start();

        try {
            Thread.sleep(2000);
            th1.suspend();
            Thread.sleep(2000);
            th2.suspend();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e) {}
    }
}

class ThreadEx18_1 implements Runnable {
    volatile boolean suspended  = false;
    volatile boolean stopped    = false;

    Thread th;

    ThreadEx18_1(String name) {
        th = new Thread(this, name); // Thread(Runnable r, String name)
    }
    public void run() {
        String name = th.getName();

        while(!stopped) {
            if(!suspended) {
                System.out.println(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " - interrupted");
                }
            } else {
                Thread.yield(); // yield()가 없다면, suspended값이 true일때 쓰레드는 while문을 의미없이 돌게된다(busy waiting)
            }
        }
        System.out.println(Thread.currentThread().getName() + " - stopped");
    }

    public void suspend() {
        suspended = true;
        th.interrupt(); // suspend()가 호출됐어도, 쓰레드가 sleep중이면 최대 1초의 지연시간이 걸린다.
        // 따라서 sleep()에서 InteeruptedException을 발생해서 바로 벗어날수 있게 해주면 응답성이 좋아진다
        System.out.println(th.getName() + " - interrupt() by suspend() ");
    }
    public void stop() {
        stopped = true;
        th.interrupt(); // 여기도 마찬가지
        System.out.println(th.getName() + " - interrupt() by stop()");
    }
    public void resume() { suspended = false; }
    public void start() { th.start(); }
}

class ThreadEx19 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx19_1 th1 = new ThreadEx19_1();
        ThreadEx19_2 th2 = new ThreadEx19_2();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("소요시간 :" + (System.currentTimeMillis() - ThreadEx19.startTime));
    }
}
class ThreadEx19_1 extends Thread {
    public void run() {
        for (int i=0; i < 300; i++) {
            System.out.println(new String("-"));
        }
    }
}

class ThreadEx19_2 extends Thread {
    public void run() {
        for (int i=0; i < 300; i++) {
            System.out.println(new String("|"));
        }
    }
}

class ThreadEx20 {
    public static void main(String[] args) {
        ThreadEx20_1 gc = new ThreadEx20_1();
        gc.setDaemon(true); // 데몬쓰레드로 설정
        gc.start();

        int requiredMemory = 0;

        for(int i=0; i<20; i++) {
            requiredMemory = (int)(Math.random() * 10) * 20;

            if(gc.freeMemory() < requiredMemory || gc.freeMemory() < gc.totalMemory() * 0.4) {
                gc.interrupt(); // 남은 메모리가 부족할시 interrupt호출하여 즉시 가비지 콜렉터 gc()수행(sleep중 exception걸림)
                try {
                    gc.join(100); // gc가 interrupt에 의해 깨어났음에도, gc()수행되기 전에 main쓰레드 작업 수행되어 메모리 사용함
                    // 따라서 gc를 개울뿐 아니라, join()을 이용하여 main쓰레드가 gc의 작업이 끝날때까지(0.1초) 기다리도록 함
                } catch (InterruptedException e) {}
            }

            gc.usedMemory += requiredMemory;
            System.out.println("usedMemory :" + gc.usedMemory);
        }
    }
}

class ThreadEx20_1 extends Thread {
    final static int MAX_MEMORY = 1000;
    int   usedMemory = 0;

    public void run() {
        while (true) {
            try {
                Thread.sleep(10*1000); // 10초마다 한번씩 가비지컬렉션 수행하는 쓰레드
            } catch (InterruptedException e) {
                System.out.println("Awaken by interrupt(). ");
            }

            gc(); // 가비지 콜렉션을 수행
            System.out.println("GarbageCollected. Free Memory : " + freeMemory());
        }
    }

    public void gc() {
        usedMemory -= 300;
        if(usedMemory < 0) usedMemory = 0;
    }
    public int totalMemory() { return MAX_MEMORY; }
    public int freeMemory() { return MAX_MEMORY - usedMemory; }
}