package ch11.StackQue;

import java.util.*;

public class StackQueEx {
    public static void main(String[] args) {
        Stack st = new Stack(); // Stack은 Stack클래스가 있다
        Queue q = new LinkedList(); // Queue 인터페이스의 구현체인 LinkedList

        st.push("0");
        st.push("1");
        st.push("2");

        q.offer("0");
        q.offer("1");
        q.offer("2");

        System.out.println("= Stack =");
        while (!st.empty()) {
            System.out.println(st.pop());
        }

        System.out.println("= Queue =");
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }
}

class MyStack extends Vector {  // 스택을 직접 구현
    public Object push(Object item) {
        addElement(item);
        return item;
    }

    public Object pop() {
        Object obj = peek(); // 스택 맨 위의 요소 읽어온다

        removeElementAt(size() - 1); // 마지막 요소 삭제
        return obj;
    }

    public Object peek() {
        int len = size();
        if (len ==0)
            throw new EmptyStackException(); // 스택이 비어있으면 peek()메서드가 EmptyStackException 발생시킨다

        return elementAt(len - 1); // 마지막 요소
    }

    public boolean empty() {
        return size() == 0;
    }

    public int search(Object o) {
        int i = lastIndexOf(o); // 끝에서부터 객체를 찾는다

        if (i >= 0) { // 객체를 찾았다면
            return size() -i; // 객체의 인덱스를 반환한다
            // stack은 맨위 객체의 인덱스가 1이다. 따라서 stack의 3인덱스는 vector의 2 인덱스와 같다
        }
        return - 1; // 객체를 찾지못하면 -1 반환
    }
}

class StackEx1 {
    public static Stack back = new Stack();
    public static Stack forward = new Stack();

    public static void main(String[] args) {
        goURL("1.네이트");
        goURL("2.야후");
        goURL("3.네이버");
        goURL("4.다음");

        printStatus();

        goBack();
        System.out.println("= '뒤로' 버튼을 누른 후");
        printStatus();

        goBack();
        System.out.println("= '뒤로' 버튼을 누른 후");
        printStatus();

        goForward();
        System.out.println("= '앞으로' 버튼을 누른 후");
        printStatus();

        goURL("www.google.com");
        System.out.println("= 새로운 주소로 이동 후");
        printStatus();
    }

    public static void printStatus() {
        System.out.println("back:" + back);
        System.out.println("forward:" + forward);
        System.out.println("현재화면은 '" + back.peek() + "' 입니다");
        System.out.println();
    }

    public static void goURL(String url) {
        back.push(url);
        if(!forward.empty())
            forward.clear();
    }

    public static void goForward() {
        if (!forward.empty()) {
            back.push(forward.pop());
        }
    }

    public static void goBack() {
        if (!back.empty()) {
            forward.push(back.pop());
        }
    }
}

class ExpValidCheck {
    public static void main(String[] args) {
        if (args.length != 1) { // 입력 없다면
            System.out.println("Usage:java ExpValidCheck \"EXPRESSION\"");
            System.out.println("Example:java ExpValidCheck \"((2+3)*1)+3\"");
            System.exit(0);
        }

        Stack st = new Stack();
        String expression = args[0]; // 입력받은 String

        System.out.println("expression : " + expression);

        try {
            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == '(') {
                    st.push(ch + "");
                } else if (ch == ')') {
                    st.pop();
                }
            }

            if (st.isEmpty()) { // (를 만나면 스택에 (를 넣고, ) 를 만나면 스택에서 pop() 한다
                System.out.println("괄호가 일치합니다"); // 괄호개수 일치한다면 넣은만큼 pop돼서 비어있다
            } else {
                System.out.println("괄호가 일치하지 않습니다");
            }
        } catch (EmptyStackException e) { // )를 만나서 ( 를 꺼내려하는데 스택이 비었으면 exception발생
            System.out.println("괄호가 일치하지 않습니다");
        }
    }
}

class QueueEx1 {
    static Queue q = new LinkedList();
    static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        System.out.println("help를 입력하면 도움말을 볼 수 있습니다");

        while(true) {
            System.out.println(">>");
            try {
                Scanner s = new Scanner(System.in); // 라인단위로 입력받는다
                String input = s.nextLine().trim();

                if ("".equals(input)) continue; // 아무것도 입력받지 않음

                if (input.equalsIgnoreCase("q")) { // q또는 Q면 프로그램 종료
                    System.exit(0);
                } else if (input.equalsIgnoreCase("help")) { // 도움말 호출
                    System.out.println(" help - 도움말을 보여줍니다");
                    System.out.println(" q또는Q - 프로그램을 종료합니다");
                    System.out.println(" history - 최근에 입력한 명령어를 " + MAX_SIZE + " 개 보여줍니다");
                } else if (input.equalsIgnoreCase("history")) { // history보여달라는 명령어
                    int i = 0;
                    save(input); // 입력받은 명령어를 저장

                    LinkedList tmp = (LinkedList) q; // q를 연결리스트로 만든다
                    ListIterator it = tmp.listIterator(); // tmp의 이터레이터

                    while (it.hasNext())
                        System.out.println(++i + "." + it.next()); // 1.이터레이터의 다음 값 ~~ 2.다음값~
                } else { // 그냥 명령어가 입력된다면
                    save(input);
                    System.out.println(input);
                }
            } catch (Exception e) {
                System.out.println("입력오류입니다");
            }
        }
    }

    public static void save(String input) {
        if(!"".equals(input))
            q.offer(input); // 빈문자열이 아니면 큐에 저장

        if(q.size() > MAX_SIZE)
            q.remove(); // 큐의 최대 크기(5) 를 넘으면 제일 처음 입력된 것을 삭제
    }
}

class PriorityQueue {
    public static void main(String[] args) {
        Queue pq = new java.util.PriorityQueue();
        pq.offer(3);
        pq.offer(1); // 우선순위 가장 높음
        pq.offer(5);
        pq.offer(2);
        pq.offer(4);
        System.out.println(pq); // pq의 내부 배열을 출력
        // 힙 자료구조 형태로 저장되서 저장 순서와 다르게 저장된다

        Object obj = null;

        while ((obj = pq.poll()) != null) { //obj에 pq에서 꺼낸 값을 넣고, 그것이 null이 아니면 print
            System.out.println(obj); // 우선순위 순으로 나옴
        }
    }
}

