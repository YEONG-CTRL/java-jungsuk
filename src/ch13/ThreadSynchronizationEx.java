package ch13;

import java.util.ArrayList;

public class ThreadSynchronizationEx {
    public static void main(String[] args) {
        Runnable r = new RunnableEx21();
        new Thread(r).start();
        new Thread(r).start(); //
    }
}

class Account {
    private int balance = 1000; // balance 인스턴스 변수 접근제어자 private 아니면, 외부에서 직접 접근할 수 있기에 동기화 하더라도 값의 변경 막을 수 없다

    public int getBalance() {
        return balance;
    }

    public synchronized void withdraw(int money) { // 쓰레드 두개돌면서 잔고 마이너스 나지 않게 임계영역 설정
        if(balance >= money) {
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            balance -= money;
        }
    }
}

class RunnableEx21 implements Runnable {
    Account acc = new Account();

    public void run() {
        while (acc.getBalance() > 0) {
            int money = (int)(Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance :" + acc.getBalance());
        }
    }
}

class ThreadWaitEx1 {
    public static void main(String[] args) throws Exception {
        Table table = new Table();

        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "donut"), "CUST1").start();
        new Thread(new Customer(table, "burger"), "CUST2").start();

        Thread.sleep(100); // 0.1초 후에 강제종료
        System.exit(0);   // 프로그램 전체 종료
    }
}

class Customer implements Runnable {
    private Table table;
    private String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food  = food;
    }

    public void run() {
        while(true) {
            try { Thread.sleep(10); } catch (InterruptedException e) {}
            String name = Thread.currentThread().getName();

            if (eatFood())
                System.out.println(name + " ate a " + food);
            else
                System.out.println(name + " failed to eat : ");
        }
    }
    boolean eatFood() { return table.remove(food) ; }
}

class Cook implements Runnable {
    private  Table table; // 여러 쓰레드가 table을 공유

    Cook(Table table) { this.table = table; }

    public void run() {
        while (true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);

            try { Thread.sleep(1) ; } catch (InterruptedException e) {}
        }
    }
}

class Table {
    String[] dishNames = {"donut", "donut", "burger"};
    final int MAX_FOOD = 6;

    private ArrayList<String> dishes = new ArrayList<>();

    public void add(String dish) {
        if ( dishes.size() >= MAX_FOOD)
            return;
        dishes.add(dish);
        System.out.println("Dishes : " + dishes.toString());
    }

    public boolean remove(String dishName) {
        for ( int i=0; i<dishes.size() ; i++)
            if (dishName.equals(dishes.get(i))) {
                dishes.remove(i);
                return true;
            }
        return false;
    }

    public int dishNum() { return dishNames.length ; }
}