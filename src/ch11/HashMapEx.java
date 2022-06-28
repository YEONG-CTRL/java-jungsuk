package ch11;

import java.util.*;

public class HashMapEx {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("myId", "1234");
        map.put("asdf", "1111");
        map.put("asdf", "1234"); // 키 asdf의 값을 1234가 덮어쓴다

        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("id와 password를 입력해주세요");
            System.out.println("id :");
            String id = s.nextLine().trim();

            System.out.println("password : ");
            String password = s.nextLine().trim();
            System.out.println();

            if (!map.containsKey(id)) {
                System.out.println("입력하신 id는 존재하지 않습니다" + "다시 입력해주세요");
                continue;
            }

            if(!(map.get(id)).equals(password)) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시입력해주세요");
            } else {
                System.out.println("id와 비밀번호가 일치합니다");
                break;
            }
        }
    }
}

class HashMapEx2 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("김자바", new Integer(100));
        map.put("이자바", new Integer(100));
        map.put("강자바", new Integer(80));
        map.put("안자바", new Integer(90));

        Set set = map.entrySet(); // entrySet()으로 키와 값을 함께 읽어온다
        Iterator it = set.iterator();

        while(it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            System.out.println("이름 : " + e.getKey() + ", 점수: " + e.getValue());
        }

        set = map.keySet(); // 키만
        System.out.println("참가자 명단 : " + set);

        Collection values = map.values(); // 값만
        it = values.iterator();

        int total = 0;
        while (it.hasNext()) {
            Integer i = (Integer)it.next();
            total += i.intValue();
        }

        System.out.println("총점 : " + total);
        System.out.println("평균 : " + (float)total/set.size());
        System.out.println("최고점수 : " + Collections.max(values));
        System.out.println("최저점수 : " + Collections.min(values));
    }
}

class HashMapEx3 {
    static HashMap phoneBook = new HashMap();

    public static void main(String[] args) {
        addPhoneNo("친구", "이지은", "010-1234-1234");
        addPhoneNo("친구", "김날두", "010-1214-1234");
        addPhoneNo("친구", "김자바", "010-1233-1234");
        addPhoneNo("회사", "이낙연", "010-5790-1234");
        addPhoneNo("회사", "배수지", "010-1234-1512");
        addPhoneNo("회사", "김덕배", "010-2534-1234");
        addPhoneNo("회사", "김수연", "010-1555-1234");
        addPhoneNo("세탁", "010-4444-1234");

        printList();
    }

    static void addPhoneNo(String groupName, String name, String tel) { // 그룹에 전화번호 추가
        addGroup(groupName); // 그룹명 추가
        HashMap group = (HashMap) phoneBook.get(groupName);  // 그룹에 해당하는 해쉬맵
        group.put(tel, name); // 키 전화번호 / 값 이름 으로 저장
    }

    static void addGroup(String groupName) {
        if (!phoneBook.containsKey(groupName)) {
            phoneBook.put(groupName, new HashMap()); // groupName : 해쉬맵
        }
    }

    static void addPhoneNo(String name, String tel) {
        addPhoneNo("기타", name, tel); // 그룹 없으면 기타 그룹에 추가
    }

    static void printList() {
        Set set = phoneBook.entrySet();
        Iterator it = set.iterator(); // 그룹 iteratre

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();

            Set subSet = ((HashMap) e.getValue()).entrySet(); // 그룹내부(e값의 entrySet())
            Iterator subIt = subSet.iterator();

            System.out.println(" * " + e.getKey() + "[" + subSet.size() + "]"); // 그룹 이름[그룹 크기]

            while (subIt.hasNext()) {
                Map.Entry subE = (Map.Entry) subIt.next();
                String telNo = (String)subE.getKey();
                String name = (String) subE.getValue();
                System.out.println(name + " " + telNo);
            }
            System.out.println();
        }
    }
}

class HashMapEx4 {
    public static void main(String[] args) {
        String[] data = {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};

        HashMap map = new HashMap();

        for(int i=0; i<data.length; i++) {
            if (map.containsKey(data[i])) {
                Integer value = (Integer) map.get(data[i]);
                map.put(data[i], new Integer(value.intValue() + 1)); // 이미 저장돼있는 문자열이면 값 1증가
            } else {
                map.put(data[i], new Integer(1));// 아니면 값으로 1저장
            }
        }
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int value = ((Integer) entry.getValue()).intValue();
            System.out.println(entry.getKey() + " : " + printBar('#',value) + " " + value);
        }
    }

    public static String printBar(char ch, int value) {
        char[] bar = new char[value]; // value만큼의 char배열 생성

        for (int i = 0; i < bar.length; i++) {
            bar[i] = ch; // 배열에 배열길이만큼 ch저장
        }
        return new String(bar);
    }
}



class TreeMapEx {
    public static void main(String[] args) {
        String[] data = {"A", "K", "A", "K", "D", "K", "A", "K", "K", "K", "Z", "D"};

        TreeMap map = new TreeMap();

        for(int i=0; i<data.length; i++) {
            if (map.containsKey(data[i])) {
                Integer value = (Integer) map.get(data[i]);
                map.put(data[i], new Integer(value.intValue() + 1)); // 이미 저장돼있는 문자열이면 값 1증가
            } else {
                map.put(data[i], new Integer(1));// 아니면 값으로 1저장
            }
        }
        Iterator it = map.entrySet().iterator();

        System.out.println("= 기본정렬 ="); // 키가 오름차순으로 정렬
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int value = ((Integer) entry.getValue()).intValue();
            System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value);
        }
        System.out.println();

        Set  set   = map.entrySet();
        List list  = new ArrayList(set);

        Collections.sort(list, new ValueComparator());

        it = list.iterator();

        System.out.println("=값의 크기가 큰 순서로 정렬=");
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            int value = ((Integer) entry.getValue()).intValue();
            System.out.println(entry.getKey() + " : " + printBar('#', value) + " " + value);
        }
    }

    static class ValueComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            if (o1 instanceof Map.Entry && o2 instanceof Map.Entry) {
                Map.Entry e1 = (Map.Entry)o1;
                Map.Entry e2 = (Map.Entry)o2;

                int v1 = ((Integer) e1.getValue()).intValue();
                int v2 = ((Integer) e2.getValue()).intValue();

                return v2 - v1;
            }
            return -1;
        }
    }


    public static String printBar(char ch, int value) {
        char[] bar = new char[value]; // value만큼의 char배열 생성

        for (int i = 0; i < bar.length; i++) {
            bar[i] = ch; // 배열에 배열길이만큼 ch저장
        }
        return new String(bar);
    }
}

