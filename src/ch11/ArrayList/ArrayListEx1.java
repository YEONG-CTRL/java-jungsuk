package ch11.ArrayList;

import java.util.*;

public class ArrayListEx1 {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList(10);
        list1.add(new Integer(5)); // 리스트의 마지막에 Integer 객체 5를 추가
        list1.add(new Integer(4));
        list1.add(new Integer(2));
        list1.add(new Integer(0));
        list1.add(new Integer(1));
        list1.add(new Integer(3));

        ArrayList list2 = new ArrayList(list1.subList(1, 4));
        print(list1, list2);

        Collections.sort(list1); // list1을 정렬한다
        Collections.sort(list2); // collections.sort(List l)

        System.out.println("list1.containsAll(list2):" + list1.containsAll(list2)); // list2가 ArrayList에 포함돼있는지 확인

        list2.add("B");
        list2.add("C");
        list2.add(3, "A"); // 3번 인덱스에 A를 추가
        print(list1, list2);

        list2.set(3, "AA"); // "AA"를 3번 인덱스에 저장
        print(list1, list2);

        System.out.println("list1.retainAll(list2): " + list1.retainAll(list2)); // list1에서 list2와 겹치는 부분만남기고 나머지는 삭제한다

        print(list1, list2);

        for (int i = list2.size() - 1; i >= 0; i--) {
            if (list1.contains(list2.get(i))) {
                list2.remove(i);
            }
        }

        print(list1, list2);
    }

    static void print(ArrayList list1, ArrayList list2) {
        System.out.println("list1:" + list1);
        System.out.println("list2:" + list2);
        System.out.println();
    }
}

class ArrayListEx2 {
    public static void main(String[] args) {
        final int LIMIT = 10; // 자르려는 글자의 개수
        String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ"; // 43
        int length = source.length();

        List list = new ArrayList(length / LIMIT + 10);
        // 실제 저장할 개수(length/LIMIT + 1 보다 더 여유롭게(10) 공간을 잡는다)

        for (int i = 0; i < length; i += LIMIT) {
            if (i + LIMIT < length)
                list.add(source.substring(i, i + LIMIT)); // i~i+10
            else
                list.add(source.substring(i)); // i가 40일때
        }

        for (int i=0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

class VectorEx1{
    public static void main(String[] args) {
        Vector v = new Vector(5);
        v.add("1");
        v.add("2");
        v.add("3");
        print(v);

        v.trimToSize(); // capacity 5인 벡터의 capacity를 size에 맞춘다
        // 배열의 크기를 변경할 수 없기에, 새로운 배열을 생성해서 그 주소값을 변수 v에 할당한다
        System.out.println("===After trimToSize()===");
        print(v);

        v.ensureCapacity(6); //  v의 capacity가 최소 6이 되게 한다
        // 현재 capa가 3이므로, 크기 6인 새로운 배열 생성해서 v의 내용을 복사한다
        System.out.println("===After ensureCapacity(6)===");
        print(v);

        v.setSize(7); // v의 size가 7 이 되도록한다. 현재 v의 capacity(6)으로는 size를 맞출 수없다
        // vector는 capacity가 부족할때 기존크기보다 2배 크기로 증가되므로,
        // 12의 capacity를 가진 배열이 생성된다
        // size의 빈공간에는 null이 들어간다
        System.out.println("===After setSize(7)===");
        print(v);

        v.clear(); // v의 모든 요소를 삭제한다
        System.out.println("===After clear()===");
        print(v);
    }

    public static void print(Vector vector) {
        System.out.println(vector);
        System.out.println("size :" + vector.size());
        System.out.println("capacity :" + vector.capacity());
    }
}

class MyVector implements List {
    Object[] data = null; // Object배열에 값 저장
    int capacity = 0;
    int size = 0;

    public MyVector(int capacity) { // 생성자
        if (capacity < 0)
            throw new IllegalArgumentException("유효하지 않은 값입니다 :" + capacity);

        this.capacity = capacity;
        data = new Object[capacity];
    }

    public MyVector() {
        this(10); // 위의 생성자 호출
    }

    public void ensureCapacity(int minCapacity) { // 최소한의 저장공간 확보
        if (minCapacity - data.length > 0)
            setCapacity(minCapacity);
    }

    public boolean add(Object obj) {
        ensureCapacity(size + 1);  // 객체 저장하기 전에 저장할 공간 확보
        data[size++] = obj;
        return true;
    }

    public Object get(int index) { //특정 인덱스의 값 가져오는 메서드
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("범위를 벗어났습니다");
        return data[index];
    }

    public Object remove(int index) { // 지정된 위치에 있는 객체를 삭제하고, 삭제한 객체를 반환
        // 삭제할 객체의 바로 아래에 있는 데이터를 한칸씩 위로 복사해서, 삭제할 객체를 덮어쓴다
        // 삭제할 객체가 마지막 데이터라면 복사하지 않고 단순히 null로 변경
        Object oldObj = null;

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("범위를 벗어났습니다");

        oldObj = data[index];

        if (index != size - 1) { // 마지막 데이터가 아니라면
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            // data[index+1]에서 data[index]로 size-index-1 개의 데이터를 복사하라는 의미
        }

        data[size-1] = null; // 데이터가 모두 한칸씩 당겨졌으므로 마지막 데이터 null로 처리
        size--; // size 하나 줄여준다
        return oldObj;
    }

    public boolean remove(Object obj) {
        for(int i=0; i<size; i++) {
            if (obj.equals(data[i])) { // 만약 data[i]가 obj와 같다면 삭제하고 true
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void trimToSize() {
        setCapacity(size); // capacity를 size로 설정
    }

    private void setCapacity(int capacity) {
        if(this.capacity == capacity) return;

        Object[] tmp = new Object[capacity]; // capacity의 용량 가진 tmp 배열 생성
        System.arraycopy(data, 0, tmp, 0, size); // data[0]에서 tmp[0]으로 size개의 데이터 복사
        data = tmp; // data가 tmp를 가리키게 한다
        this.capacity = capacity;
    }

    public void clear() {
        for (int i =0; i < size ; i++)
            data[i] = null;
        size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(data, 0, result, 0, size);

        return result; // 복사한 result배열을 리턴한다
    }

    public boolean isEmpty() { return size == 0; }
    public int capacity() { return capacity; }
    public int size() { return size; }

    /* List 인터페이스로부터 상속받은 메서드들 */

    public boolean contains(Object o) { return false; }
    public Iterator iterator() { return null;}
    public Object[] toArray(Object a[]) {return null;}
    public boolean containsAll(Collection c) { return false; }
    public boolean addAll(Collection c) { return false; }
    public boolean addAll(int index, Collection c) { return false; }
    public boolean removeAll(Collection c) { return false; }
    public boolean retainAll(Collection c) { return false; }
    public boolean equals(Object o) { return false; }

    public Object set(int index, Object element) { return null; }
    public void add(int index, Object element) { }
    public int indexOf(Object o) { return -1; }
    public int lastIndexOf(Object o) { return -1; }
    public ListIterator listIterator() { return null; }
    public ListIterator listIterator(int index) { return null; }
    public List subList(int fromIndex, int toIndex) { return null;}

//    default void sort(Comparator c) { /* 내용생략 */ }
//    default Spliterator spliterator() { /* 내용생략 */ }
}

class MyVector2 extends MyVector implements Iterator {
    int cursor = 0; // 앞으로 읽어 올 요소의 위치를 저장
    int lastRet = -1; // 마지막으로 읽어 온 요소의 위치를 저장(항상 커서보다 1작은 값 저장된다)

    public MyVector2(int capacity) { // 생성자
        super(capacity);
    }

    public MyVector2() { // 생성자
        this(10);
    }

    public String toString() { // 문자열로 변환
        String tmp = "";
        Iterator it = iterator();

        for (int i=0; it.hasNext(); i++) {
            if(i!=0) tmp+=", ";
            tmp += it.next();
        }

        return "[" + tmp + "]";
    }

    public Iterator iterator() {
        cursor = 0;
        lastRet = -1;
        return this;
    }

    public boolean hasNext() {
        return cursor != size(); // cursor(앞으로 읽어올 요소)가 size()가 아니라면(인덱스 벗어남)
    }

    public Object next() {
        Object next = get(cursor); // 다음 요소 next에 담는다
        lastRet = cursor++; // cursor를 lastRet에 대입하고, cursor에 1더한다
        return next;
    }

    public void remove() {
        if (lastRet == -1) { // 마지막으로 읽어온 요소가 -1이면(없으면)
            throw new IllegalStateException();
        } else {
            remove(lastRet); // 이전 것 삭제
            cursor--;      // 객체들이 빈 공간 채우기 위해 자동적으로 이동하기 때문에 cursor의 위치도 같이 이동시킨다
            lastRet = -1; // 읽어온 요소 삭제했으므로 초기화한다 (읽어온 값이 없다)
        }
    }
}

class MyVector2Test{
    public static void main(String[] args) {
        MyVector2 v = new MyVector2();
        v.add("0");
        v.add("1");
        v.add("2");
        v.add("3");
        v.add("4");

        System.out.println("삭제 전: " + v);
        Iterator it = v.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();

        System.out.println("삭제 후: " + v);
    }
}