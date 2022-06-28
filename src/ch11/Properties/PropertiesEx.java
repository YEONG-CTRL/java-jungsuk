package ch11.Properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesEx {
    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.setProperty("timeout", "30");
        prop.setProperty("language", "kr"); // 해시테이블의 put메서드 호출
        prop.setProperty("size", "10");
        prop.setProperty("capacity", "10");

        Enumeration e = prop.propertyNames();

        while (e.hasMoreElements()) {
            String element = (String) e.nextElement();
            System.out.println(element + "=" + prop.getProperty(element));
        }

        System.out.println();
        prop.setProperty("size", "20");
        System.out.println("size =" + prop.getProperty("size"));
        System.out.println("capacity =" + prop.getProperty("capacity", "20")); // 읽어오려는 키가 없으면 기본값 반환
        System.out.println("loadfactor =" + prop.getProperty("loadfactor", "0.75"));
        System.out.println(prop);
        prop.list(System.out);
    }
}

class PropertiesEx2 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("USAGE: java propertiesEx2 INPUTFILENAME");
            System.exit(0);
        }

        Properties prop = new Properties();

        String inputFile = args[0];

        try {
            prop.load(new FileInputStream(inputFile));
        } catch (IOException e) {
            System.out.println("지정된 파일을 찾을 수 없습니다");
            System.exit(0);
        }

        String name = prop.getProperty("name");
        String[] data = prop.getProperty("data").split(",");
        int max = 0, min = 0;
        int sum = 0;

        for (int i = 0; i < data.length; i++) {
            int intValue = Integer.parseInt(data[i]);

            if (i==0) max = min = intValue;

            if (max < intValue)
                max = intValue;
            else if(min > intValue)
                min = intValue;
            sum += intValue;
        }

        System.out.println("이름 :" + name);
        System.out.println("최대값 :" + max);
        System.out.println("최소값 :" + min);
        System.out.println("합계 :" + sum);
        System.out.println("평균 :" + (sum*100.0/data.length)/100);
    }
}

class PropertiesEx3 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty("timeout", "30");
        prop.setProperty("language", "한글");
        prop.setProperty("size", "10");
        prop.setProperty("capacity", "10");

        try { // properties에 저장된 데이터를 파일로 저장
            prop.store(new FileOutputStream("output.txt"), "Properties Example"); //뒤는 주석
            prop.storeToXML(new FileOutputStream("output.xml"), "properties Example");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

class PropertiesEx4 {
    public static void main(String[] args) {
        Properties sysProp = System.getProperties(); // 시스템클래스의 getProperties()로 시스템과 관련된 속성을 가져온다
        System.out.println("java.version :" + sysProp.getProperty("java.version")); // 그 중 원하는 속성을 getProperty로 출력
        System.out.println("user.language :" + sysProp.getProperty("user.language"));
        sysProp.list(System.out);
    }
}

