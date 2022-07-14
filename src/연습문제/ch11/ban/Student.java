package 연습문제.ch11.ban;

import java.util.*;
class Student implements Comparable {
    String name;
    int ban;
    int no;
    int kor;
    int eng;
    int math;
    int total;
    int schoolRank; // 전교등수
    int classRank; // 반등수
    Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        total = kor+eng+math;
    }
    int getTotal() {
        return total;
    }
    float getAverage() {
        return (int)((getTotal()/ 3f)*10+0.5)/10f;
    }
    public int compareTo(Object o) {
        if(o instanceof Student) {
            Student tmp = (Student)o;
            return tmp.total - this.total;
        } else {
            return -1;
        }
    }
    public String toString() {
        return name
                +","+ban
                +","+no
                +","+kor
                +","+eng
                +","+math
                +","+getTotal()
                +","+getAverage()
                +","+schoolRank
                +","+classRank // 새로추가
                ;
    }
} // class Student
class ClassTotalComparator implements Comparator {
    public int compare(Object o1, Object o2) {
            Student s1 = (Student) o1;
            Student s2 = (Student) o2;

            int result = s1.ban -s2.ban; // ban 기준 오름차순 정렬

            if (result == 0)
                return s2.total - s1.total; // ban이 같으면 total기준 내림차순 정렬

            return result;
        }
    }

class Exercise11_9 {
    public static void calculateClassRank(List list) {
// 먼저 반별 총점기준 내림차순으로 정렬한다.
        Collections.sort(list, new ClassTotalComparator());
        int prevBan = -1;
        int prevRank = -1;
        int prevTotal = -1;
        int length = list.size();


        for (int i=0, tmp =0; i<length;i++, tmp++) {
            Student s = (Student) list.get(i);

            if (s.ban != prevBan) { // 반이 달라지면 이전 등수와, 이전 총점 초기화
                prevRank  = -1;
                prevTotal = -1;
                tmp       =  0; // 반 내부 등수도
            }

            if (s.total == prevTotal) {
                s.classRank = prevRank;
            } else {
                s.classRank = tmp +1;
            }
            prevRank  = s.classRank;
            prevBan   = s.ban;
            prevTotal = s.total;

        }

    } // public static void calculateClassRank(List list) {
    public static void calculateSchoolRank(List list) {
        Collections.sort(list); // 먼저 list를 총점기준 내림차순으로 정렬한다.
        int prevRank = 0; // 이전 전교등수
        int prevTotal = -1; // 이전 총점
        int length = list.size();

        for (int i=0; i<length; i++) {
            Student s = (Student)list.get(i);
            if (s.total == prevTotal) {
                s.schoolRank = prevRank;
            } else {
                s.schoolRank = i+1; // 이미 내림차순 정렬이 돼있으니, 동점인 경우만 아니면 인덱스+1로 등수 매기면 된다
            }
            prevTotal = s.total;
            prevRank  = s.schoolRank;
        }
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("김자바",1,2,80,80,90));
        calculateSchoolRank(list);
        calculateClassRank(list);
        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}