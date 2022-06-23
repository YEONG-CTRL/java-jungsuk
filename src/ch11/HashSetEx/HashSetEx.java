package ch11.HashSetEx;

import java.util.*;

public class HashSetEx {
    public static void main(String[] args) {
        Object[] objArr = {"1", new Integer(1), "2", "2", "3", "3", "4", "4", "4"};
        Set set = new HashSet();

        for (int i = 0; i < objArr.length; i++) {
            set.add(objArr[i]); // String인스턴스인 "1"과 Ingetger 인스턴스인 1이 저장돼 중복처럼보이지만, 중복값은 저장않는다
        }

        System.out.println(set);
    }
}

class HashSetLotto {
    public static void main(String[] args) {
        Set set = new HashSet();

        for (int i=0; set.size() < 6; i++) {
            int num = (int)(Math.random()*45) + 1;
            set.add(new Integer((num)));
        }

        List list = new LinkedList(set);  // 밑의 sort() 메서드는 인자로 List인터페이스타입을 필요로하기 떼문에
        // LinkedList클래스의 생성자를 통해서 HashSet에 저장된 객체들을 LinkedList에 담아서 처리
        Collections.sort(list); // 번호를 크기순으로 정렬 // Integer클래스에 정의된 기본정렬이 사용된다
        System.out.println(list);
    }
}

class Bingo{
    public static void main(String[] args) {
        Set set = new HashSet();
        int [][] board = new int[5][5];

        for (int i=0; set.size() < 25; i++) {
            set.add((int) (Math.random() * 50) + 1 + "");
        }

        Iterator it = set.iterator();

        for(int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                board[i][j] = Integer.parseInt((String) it.next()); // 반환값이 Object타입인 next()를 변환
                System.out.print((board[i][j] < 10 ? " " : " ") + board[i][j]); // 조건문 ? 참 : 거짓
            }
            System.out.println();
        }
    }
}

class HashSetEx3 {
    public static void main(String[] args) {
        HashSet set = new HashSet();

        set.add("abc");
        set.add("abc");
        set.add(new Person("David", 10)); // 얘랑
        set.add(new Person("David", 10)); // 얘를 다른 사람으로 인식하고 있음
        set.add(new Person2("David", 10));
        set.add(new Person2("David", 10)); // 밑의 두개는 같은 사람으로 인식함


        System.out.println(set);
    }
}

class Person2 {
    String name;
    int age;

    Person2(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    public String toString() {
        return name + ":" + age;
    }

    public boolean equals(Object obj){ // HashSet의 add메서드가 새로운 요소 추가하기 전 equals()와 hashCode() 호출하기에 오버라이딩하기
        if (obj instanceof Person2) {
            Person2 tmp = (Person2) obj;
            return name.equals(tmp.name) && age == tmp.age; // 두 인스턴스의 name과 age같으면 true 반환
        }
        return false;
    }

    public int hashCode() {
        return (name+age).hashCode();
    }
    // 1. 실행중인 어플리케이션 내의 동일한 객체에 대해서 여러번 hashCode() 호출해도 동일한 int값 반환해야 한다
    // 2. equals메서드를 이용한 비교에 의해서 true를 얻은 두 객체에 대해 각각 hashCode()호출한 결과 같아야 한다
    // 3. equals 메서드를 호출했을 때 false를 반환하는 두 객체는 hashCode() 호출에 대해 같은 int값을 반환하는 경우가 있어도 괜찮지만, 해싱을 사용하는 컬렉션의 성능향상을 위해 가능한 다른 int값 반환하는 것이 좋다
}

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    public String toString() {
        return name + ":" + age;
    }
}

class HashSetEx5 {
    public static void main(String[] args) {
        HashSet setA   = new HashSet();
        HashSet setB   = new HashSet();
        HashSet setHab = new HashSet();
        HashSet setKyo = new HashSet();
        HashSet setCha = new HashSet();

        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");
        System.out.println("A = " + setA);

        setB.add("4");
        setB.add("5");
        setB.add("6");
        setB.add("7");
        setB.add("8");
        System.out.println("B = " + setB);

        Iterator it = setB.iterator();
        while(it.hasNext()) {
            Object tmp = it.next();
            if (setA.contains(tmp)) { // 교집합
                setKyo.add(tmp);
            }
        }

        it = setA.iterator();
        while (it.hasNext()) {
            Object tmp = it.next();
            if (!setB.contains(tmp)) {
                setCha.add(tmp); // 차집합
            }
        }

        it = setA.iterator();
        while (it.hasNext()) {
            setHab.add(it.next()); // 합집합
        }

        it = setB.iterator();
        while (it.hasNext()) {
            setHab.add(it.next());
        }

        System.out.println("A n B = " + setKyo);
        System.out.println("A U B = " + setHab);
        System.out.println("A - B = " + setCha);

    }
}