package ch11.Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorEx {
    public static void main(String[] args) {
        ArrayList list = new ArrayList(); // List의 조상인 Collection인터페이스에 iterator()메서드를 정의해놓아서
        // Iterator인터페이스를 구현하여 사용할 수 있다.
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        Iterator it = list.iterator();

        while(it.hasNext()) {
            Object obj = it.next();
            System.out.println(obj);
        }
    }
}

class ListIteratorEx {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        ListIterator it = list.listIterator();

        while(it.hasNext()) {
            System.out.print(it.next());
        }
        System.out.println();

        while (it.hasPrevious()) {
            System.out.print(it.previous());
        }
        System.out.println();
    }
}

class IteratorEx2 {
    public static void main(String[] args) {
        ArrayList original = new ArrayList(10);
        ArrayList copy1 = new ArrayList(10);
        ArrayList copy2 = new ArrayList(10);

        for (int i=0; i <10; i++)
            original.add(i+"");

        Iterator it = original.iterator();

        while (it.hasNext())
            copy1.add(it.next());

        System.out.println("=Original에서 copy1로 복사 =");
        System.out.println("Original :" + original);
        System.out.println("copy1 :" + copy1);
        System.out.println();

        it = original.iterator();

        while (it.hasNext()) {
            copy2.add(it.next());
            it.remove(); // next로 읽어오고 이를 삭제
        }

        System.out.println("= Original에서 copy2로 이동=");
        System.out.println("Original :" + original);
        System.out.println("copy2 :" + copy2);
        System.out.println();
    }
}

