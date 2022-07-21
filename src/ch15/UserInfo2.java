package ch15;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class SuperUserInfo {
    String name;
    String password;

    SuperUserInfo() {
        this("Unknown", "1111");
    }

    SuperUserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }
}

public class UserInfo2 extends SuperUserInfo implements java.io.Serializable {
    int age;

    public UserInfo2() {
        this("Unknown", "1111", 0);
    }

    public UserInfo2(String name, String password, int age) {
        super(name, password);
        this.age = age;
    }

    public String toString() {
        return "(" + name + "," + password + "," + age + ")";
    }

    private void writeObject(ObjectOutputStream out) throws IOException {  // 직렬화시 해당메서드 호출, 접근자가 private인것은 그냥 규칙
        out.writeUTF(name); // 조상으로부터 상속받은 name과 password가 렬화되도록 해야함
        out.writeUTF(password);
        out.defaultWriteObject(); // 클래스 자신에 정의된 age의 직렬화를 수행
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        password = in.readUTF();
        in.defaultReadObject();
    }
}
