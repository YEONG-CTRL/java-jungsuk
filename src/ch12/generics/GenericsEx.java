package ch12.generics;

import java.util.ArrayList;

class Fruit implements Eatable { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}
class Toy { public String toString() { return "Toy";}}

interface Eatable {}

public class GenericsEx {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<Fruit>();
        Box<Apple> appleBox = new Box<Apple>();
        Box<Toy> toyBox = new Box<Toy>();
//        Box<Grape> grapeBox = new Box<Apple>(); 에러: 타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple()); // void add(Fruit item)

        appleBox.add(new Apple());
        appleBox.add(new Apple());
//        appleBox.add(new Toy()); Apple만 담을 수 있음. 에러

        toyBox.add(new Toy());

        System.out.println(fruitBox);
        System.out.println(appleBox);
        System.out.println(toyBox);
    }
}
class Box<T> {
    ArrayList<T> list = new ArrayList<T>();
    void add(T item) { list.add(item); }
    T get(int i)     { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size()       { return list.size(); }
    public String toString() { return list.toString(); }
}

class FruitBoxEx2{
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
//        appleBox.add(new Grape()); Grape는 Apple의 자손이 아니므로 에러난다
        grapeBox.add(new Grape());

        System.out.println("fruitBox-"+fruitBox);
        System.out.println("appleBox-"+appleBox);
        System.out.println("grapeBox-"+grapeBox);
    }
}

class FruitBox<T extends Fruit & Eatable> extends Box<T> {} // Fruit의 자손이면서 Eatable을 구현한 타입으로 종류를 제한


class Juice {
    String name;

    Juice (String name) { this.name = name + "Juice"; }
    public String toString() { return name; }
}

class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) { // 와일드카드로 Fruit의 자손들로 타입 제한
        String tmp = "";
        for (Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }
}

