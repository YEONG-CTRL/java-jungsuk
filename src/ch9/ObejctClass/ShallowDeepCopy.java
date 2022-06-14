package ch9.ObejctClass;

class Circle implements Cloneable {
    Pointt p;
    double r;

    Circle(Pointt p, double r) {
        this.p = p;
        this.r = r;
    }

    public Circle shallowCopy() { //얕은 복사:원본과 복제본이 같은 객체를 공유
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
        }
        return (Circle) obj;
    }

    public Circle deepCopy() {//  깊은 복사
        Object obj = null;

        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
        }

        Circle c = (Circle) obj;
        c.p = new Pointt(this.p.x, this.p.y);  // 복제된 객체가 새로운 Point 객체 참조하도록 했다.

        return c;
    }

    public String toString() {
        return "[p=" + p + ", r =" + r + "]";
    }
}
class Pointt {
    int x, y;

    Pointt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + " ,"+ y + ")";
    }
}

class ShallowDeepCopy {
    public static void main(String[] args) {
        Circle c1 = new Circle(new Pointt(1, 1), 2.0);
        Circle c2 = c1.shallowCopy();
        Circle c3 = c1.deepCopy();

        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        System.out.println("c3="+c3);

        c1.p.x = 9;
        c1.p.y = 9;
        System.out.println("c1 변경 이후");
        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        System.out.println("c3="+c3);
    }
}
