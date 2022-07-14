package 연습문제.ch11;

import java.util.*;

import static java.lang.Math.min;

class Exercise11_1 {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList kyo = new ArrayList(); // 교집합
        ArrayList cha = new ArrayList(); // 차집합
        ArrayList hap = new ArrayList(); // 합집합
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);


        kyo = (ArrayList) list1.clone();
        kyo.retainAll(list2);

        cha = (ArrayList) list1.clone();
        cha.removeAll(list2);

        hap.addAll(cha);
        hap.addAll(list2);

        System.out.println("list1="+list1);
        System.out.println("list2="+list2);
        System.out.println("kyo="+kyo);
        System.out.println("cha="+cha);
        System.out.println("hap="+hap);
    }
}

class Exercise11_2 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(7);
        HashSet set = new HashSet(list);
        TreeSet tset = new TreeSet(set); // TreeSet은 정렬기준 따로 없으면 오름차순 저장
        Stack stack = new Stack();
        stack.addAll(tset);
        while(!stack.empty())
            System.out.println(stack.pop());
        // 7 6 3 2
    }
}

class Student implements Comparable { // Collections.sort()를 호출하면 쉽게 정렬이 되지만, 사실은 ArrayList에 저장된 요소들은 모두 Comparable인터페이스를 구현한 것이어야 한다
    String name;
    int ban;
    int no;
    int kor, eng, math;
    Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
    int getTotal() {
        return kor+eng+math;
    }
    float getAverage() {
        return (int)((getTotal()/ 3f)*10+0.5)/10f;
    }
    public String toString() {
        return name +","+ban +","+no +","+kor +","+eng +","+math
                +","+getTotal() +","+getAverage();
    }

    public int compareTo(Object o) {
        if (o instanceof Student) {
        Student compareObj = (Student)o;

        return name.compareTo(compareObj.name); }  // String클래스의 compareTo를 호출
        else {
            return -1;
        }
    }
}
class Exercise11_5 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Student("홍길동",1,1,100,100,100));
        list.add(new Student("남궁성",1,2,90,70,80));
        list.add(new Student("김자바",1,3,80,80,90));
        list.add(new Student("이자바",1,4,70,90,70));
        list.add(new Student("안자바",1,5,60,100,80));
        Collections.sort(list);
        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}

class Exercise11_6 {
    static int getGroupCount(TreeSet tset, int from, int to) {
        Student s1 = new Student("",0,0,from,from,from);
        Student s2 = new Student("",0,0,to,to,to);

        return tset.subSet(s1,true,s2,false).size();
    }

    public static void main(String[] args) {
        TreeSet set = new TreeSet(new Comparator() { // 익명클래스 : Comparator인터페이스를 평균을 기준으로 정렬하는 클래스로 구현하고, 이를
            // TreeSet(Compartor c)의 매개변수로 넘김
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Student && o2 instanceof Student){
                    Student s1 = (Student) o1;
                    Student s2 = (Student) o2;

                    return (int) (s1.getAverage() - s2.getAverage());
                }

                return -1;
            }
        }) /*매개변수*/ ;

        set.add(new Student("홍길동",1,1,100,100,100));
        set.add(new Student("남궁성",1,2,90,70,80));
        set.add(new Student("김자바",1,3,80,80,90));
        set.add(new Student("이자바",1,4,70,90,70));
        set.add(new Student("안자바",1,5,60,100,80));
        Iterator it = set.iterator();
        while(it.hasNext())
            System.out.println(it.next());
        System.out.println("[60~69] :"+getGroupCount(set,60,70));
        System.out.println("[70~79] :"+getGroupCount(set,70,80));
        System.out.println("[80~89] :"+getGroupCount(set,80,90));
        System.out.println("[90~100] :"+getGroupCount(set,90,101));
    }
}

class Exercise11_10 {
    public static void main(String[] args) {
//        LinkedHashSet set = new LinkedHashSet();
        HashSet set = new HashSet();
        int[][] board = new int[5][5];
        for(int i=0; set.size() < 25; i++) {
            set.add((int)(Math.random()*30)+1+"");
        }

        ArrayList list = new ArrayList(set);
        Collections.shuffle(list);

        Iterator it = list.iterator();
        for(int i=0; i < board.length; i++) {
            for(int j=0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt((String)it.next());
                System.out.print((board[i][j] < 10 ? "  " : " ") // 칸수 채우기용
                        + board[i][j]);
            }
            System.out.println();
        }
    } // main
}
/* HashSet은 저장된 순서를 보장하지 않고,해싱알고리즘에 따라 자체적으로 순서를 결정,
 이 경우에는 1. LinkedHashSet이 더 나은 선택 */
// 2. List 인터페이스를 구현한 컬렉션 클래스를 사용하도록 변경


class SutdaCard {
    int num;
    boolean isKwang;
    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }
    public boolean equals(Object obj) {
        if(obj instanceof SutdaCard) {
            SutdaCard c = (SutdaCard)obj;
            return num==c.num && isKwang==c.isKwang;
        } else {
            return false;
        }
    }
    public String toString() {
        return num + ( isKwang ? "K":"");
    }

    public int hashCode() {
        return Objects.hashCode(this.toString());
        // 다른 객체라도 인스턴스 변수 값이 같으면 같은 객체로 인식하게 해야함
        // HashSet의 add 메서드는 새로운 요소 추가하기 전, 기존에 저장된 요소와 같은 것인지 판별하기 위해 추가하려는 요쇼의 equals()와 hashCode()를 호출
        // java.util.objects 클래스의 hash() 이용하여 hashCode() 오버라이딩
    }
}
class Exercise11_11 {
    public static void main(String[] args) {
        SutdaCard c1 = new SutdaCard(3,true);
        SutdaCard c2 = new SutdaCard(3,true);
        SutdaCard c3 = new SutdaCard(1,true);
        HashSet set = new HashSet();
        set.add(c1);
        set.add(c2);
        set.add(c3);
        System.out.println(set);
    }
}

