package 연습문제.ch13;

import javax.swing.*;
import java.util.Scanner;
import java.util.Vector;

class Exercise13_1 {
    public static void main(String args[]) {
        /* Thread 클래스를 상속받아 쓰레드를 구현한 것을 Runnable 인터페이스를 구현하는 것으로 변경 */
        Thread  th1 = new Thread(new Thread1()); // Runnable r = new Thread1()을 매개변수로 넣음
//        Thread1 th1 = new Thread1();
        th1.start();
    }
}
//class Thread1 extends Thread {
//    public void run() {
//        for(int i=0; i < 300; i++) {
//            System.out.print('-');
//        }
//    }
//}
class Thread1 implements Runnable {
    @Override
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print('-');
        }
    }
}

class Exercise13_2 {
    public static void main(String[] args) {
        Thread2 t1 = new Thread2();
        t1.run();  // 쓰레드를 생성시킨것이 아니라, 단지 Thread2클래스의 메서드를 호출한 셈이 됨.
        // start()를 호출했다면 숫자가 섞여서 출력됐을 것.
        for(int i=0; i < 10; i++)
            System.out.print(i);
    }
}
class Thread2 extends Thread {
    public void run() {
        for(int i=0; i < 10; i++)
            System.out.print(i);
    }
}

class Exercise13_5
{
    public static void main(String[] args) throws Exception
    {
        Thread3 th1 = new Thread3();
        th1.start();
        try {
            Thread.sleep(5*1000); // main 쓰레드 5초간 잠듬
        } catch(Exception e) {}
        throw new Exception("꽝~!!!"); // main에서 예외 발생해서 호출스택 사라지고 종료됨
    }
}
class Thread3 extends Thread {
    public void run() {
        for(int i=0; i < 10; i++) {
            System.out.println(i); // 0 1 2 3 4 출력 후
            // main은 종료되지만, 그것이 th1 호출스택에 영향을 주지않기 때문에 계속 5 6 ... 출력
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    } // run()
}

class Exercise13_6
{
    public static void main(String[] args) throws Exception
    {
        Thread4 th1 = new Thread4();
        th1.setDaemon(true); // 데몬쓰레드는 일반쓰레드가 모두 종료되면 자동 종료
        th1.start();
        try {
            Thread.sleep(5*1000); // 5초간 기다린다
        } catch(Exception e) {}
        throw new Exception("꽝~!!!");
    }
}
class Thread4 extends Thread {
    public void run() {
        for(int i=0; i < 10; i++) {
            System.out.println(i); // 0 1 2 3 4  꽝
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    } // run()
}

class Exercise13_7
{
    static boolean stopped = false;
    public static void main(String[] args)
    {
        Thread5 th1 = new Thread5();
        th1.start();
        try {
            Thread.sleep(6*1000);
        } catch(Exception e) {}
        stopped = true; // 쓰레드를 정지시킨다. System.out.println("stopped");
        th1.interrupt(); // 2. 따라서 interrupt()를 호출해 자고 있는 Thread5를 깨워서 즉시 정지시켜야 한다
    }
}
class Thread5 extends Thread {
    public void run() {
// Exercise13_7.stopped의 값이 false인 동안 반복한다.
        for(int i=0; !Exercise13_7.stopped; i++) { // stopped가 false일동안
            System.out.println(i);
            try {
                Thread.sleep(3*1000);
                // 1 . stopped가 true가 되어도 Thread가 3초간 자고(일시정지)있으면 for문을 벗어나지 않음


            } catch(Exception e) {}
        }
    } // run()
}

class Exercise13_8 {
    Vector words = new Vector();
    String[] data = {"태연", "유리", "윤아", "효연", "수영", "서현", "티파니", "써니", "제시카"};
    int interval = 2 * 1000; // 2초
    WordGenerator wg = new WordGenerator();

    public static void main(String args[]) {
        Exercise13_8 game = new Exercise13_8();
        game.wg.start();
        Vector words = game.words;
        while (true) {
            System.out.println(words);
            String prompt = ">>";
            System.out.print(prompt);
// 화면으로부터 라인단위로 입력받는다.
            Scanner s = new Scanner(System.in);
            String input = s.nextLine().trim();
            int index = words.indexOf(input);
            if (index != -1) {
                words.remove(index);
            }
        }
    } // main

    class WordGenerator extends Thread {
        public void run() {

            while (true) {
                int rand = (int) (Math.random() * data.length);
                words.add(data[rand]);
                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                } // sleep()을 호출할때에는 InterruptedException을 처리하기 위해 항상 try-catch문으로 예외처리

            } // end of run()
        } // class WordGenerator
    } // Exercise13_9
}

class Exercise13_9 {
    public static void main(String[] args) throws Exception {
        Exercise13_9_1 th1 = new Exercise13_9_1();
        th1.start();
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt(); // 쓰레드에게 작업을 멈추라고 요청한다.
    }
}
class Exercise13_9_1 extends Thread {
    public void run() {
        int i = 10;
        while(i!=0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000); // 1초 지연
            } catch(InterruptedException e) {
                interrupt() ; // 2. 따라서 catch블럭에 interrupt 추가로 넣어줘서 interrupted 상태 true로 다시 바꿔줘야 함
            } // 1. 인터럽트 발생시InterruptedException이 발생하고, 쓰레드의 interrupted상태는 false로 자동 초기화
        }
        System.out.println("카운트가 종료되었습니다.");
    } // main
}
