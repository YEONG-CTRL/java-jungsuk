package ch15;

import java.io.*;
import java.util.ArrayList;

public class UserInfo implements java.io.Serializable {
    String name;
    String password;
    int age;

    public UserInfo() {
        this("Unknown", "1111", 0);
    }

    public UserInfo(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age  = age;
    }

    public String toString() {
        return "(" + name + "," + password + "," + age + ")";
    }
}

class SerialEx1 {
    public static void main(String[] args) {
        try {
            String fileName = "UserInfo.ser"; // 객체 직렬화하여 여기에 저장
            FileOutputStream fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            ObjectOutputStream out = new ObjectOutputStream(bos); // 보조스트림인 ObjectOutputStream이기에 입출력할 스트림 지정

            UserInfo u1 = new UserInfo("JavaMan", "1234", 30);
            UserInfo u2 = new UserInfo("JavaWoman", "4321", 26);

            ArrayList<UserInfo> list = new ArrayList<>();
            list.add(u1);
            list.add(u2);

            out.writeObject(u1);
            out.writeObject(u2);
            out.writeObject(list);
            out.close();
            System.out.println("직렬화가 잘 끝났습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SerialEx2 {
    public static void main(String[] args) { // 위에서 직렬화한 객체를 역직렬화
        try {
            String fileName = "UserInfo.ser";
            FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            ObjectInputStream in = new ObjectInputStream(bis);

            UserInfo u1 = (UserInfo) in.readObject(); // readObject()의 반환타입이 Object이기에 형변환이 필요하다
            UserInfo u2 = (UserInfo) in.readObject(); // 역직렬화의 순서는 직렬화할때의 순서와 일치해야 한다
            ArrayList list = (ArrayList) in.readObject();
            // 그래서 직렬화할 객체가 많을때에는 ArrayList하나만 역직렬화할 수 있게 컬렉션에 저장해서 직렬화하는것이 좋다

            System.out.println(u1);
            System.out.println(u2);
            System.out.println(list);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

