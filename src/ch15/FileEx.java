package ch15;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class FileEx1 {
    public static void main(String[] args) throws IOException {
        File f = new File("c:\\jdk1.8\\work\\ch15\\FileEx1.java");
        String fileName = f.getName();
        int pos = fileName.lastIndexOf(".");

        System.out.println("경로를 제외한 파일이름 - " + f.getName());
        System.out.println("확장자를 제외한 파일이름 - " + fileName.substring(0,pos));
        System.out.println("확장자 - " + fileName.substring(pos+1));

        System.out.println("경로를 포함한 파일이름 - " + f.getPath());
        System.out.println("파일의 절대경로 - " + f.getAbsolutePath());
        System.out.println("파일의 정규경로 - " + f.getCanonicalPath());
        System.out.println("파일이 속해있는 디렉토리 - " + f.getParent()); // getParent()로 디렉토리 반환
        System.out.println();
        System.out.println("File.pathSeparator - " + File.pathSeparator); // os마다 경로구분자가 달라서 멤버변수를 통해 구분자를 이용
        System.out.println("File.PathSeparatorChar - " + File.pathSeparatorChar);
        System.out.println();
        System.out.println("user.dir=" +System.getProperty("user.dir")); // 현재 프로그램이 실행중인 디렉토리
        System.out.println("sun.boot.class.path = " +System.getProperty("sun.boot.class.path")); // 기본적인 classpath
    }
}

class FileEx2 {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("USAGE : java FileEx2 DIRECTORY");
            System.exit(0);
        }

        File f = new File(args[0]);

        if(!f.exists() || !f.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }

        File[] files = f.listFiles(); // 주어진 디렉토리의 파일목록(디렉토리포함)을 File 배열로 반환

        for(int i=0; i<files.length; i++) {
            String fileName = files[i].getName();
            System.out.println(files[i].isDirectory() ? "["+fileName+"]" : fileName);
        }
    }
}
class FileEx3 {
    static int totalFiles = 0;
    static int totalDirs  = 0;

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("USAGE : java FileEx2 DIRECTORY");
            System.exit(0);
        }

        File dir = new File(args[0]);

        if(!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }

        printFileList(dir);

        System.out.println();
        System.out.println("총 " + totalFiles + "개의 파일");
        System.out.println("총 " + totalDirs + "개의 디렉토리");
    }

    public static void printFileList(File dir) {
        System.out.println(dir.getAbsolutePath() + " 디렉토리");
        File[] files = dir.listFiles();

        ArrayList subDir = new ArrayList();

        for(int i=0; i<files.length; i++) {
            String filename = files[i].getName();

            if(files[i].isDirectory()) { // 만약 File배열안에 있는 것이 디렉토리라면
                filename = "[" + filename + "]";
                subDir.add(i + ""); // 인덱스를 subDir에 넣어준다
            }
            System.out.println(filename);
        }

        int dirNum = subDir.size();
        int fileNum = files.length - dirNum; // 파일배열 길이 - 디렉토리가 담긴 서브배열 길이

        totalFiles += fileNum;
        totalDirs += dirNum;

        System.out.println(fileNum + "개의 파일, " + dirNum + "개의 디렉토리");
        System.out.println();

        for(int i =0; i<subDir.size(); i++) {
            int index = Integer.parseInt((String) subDir.get(i));
            printFileList(files[index]); // 재귀호출로 서브디렉토리 탐색
       }
    }
}

class FileEx4 {
    public static void main(String[] args) {
        String currDir = System.getProperty("user.dir"); // 현재디렉토리
        File dir = new File(currDir);

        File[] files = dir.listFiles();

        for (int i=0; i < files.length; i++) {
            File f = files[i];
            String name = f.getName();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mma");
            String attribute = "";
            String size = "";

            if (files[i].isDirectory()) { // 서브 디렉토리인 경우
                attribute = "DIR";
            } else { // 파일인 경우
                size = f.length() + "";
                attribute = f.canRead() ? "R" : " ";
                attribute += f.canWrite() ? "W" : " ";
                attribute = f.isHidden() ? "H" : " ";
            }

            System.out.printf("%s %3s %6s %s\n",
                    df.format(new Date(f.lastModified())), attribute, size,name);
            // lastModified()는 마지막으로 수정된 시간을 반환
        }
    }
}

class FileEx5 {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].length() != 1
                        || "tTILnN".indexOf(args[0]) == -1) {
            System.out.println("USAGE : java FileEx5 SORT_OPTION    ");
            System.out.println("    SORT_OPTION :                   ");
            System.out.println("    t    Time ascending sort.       ");
            System.out.println("    T    Time descending sort.      ");
            System.out.println("    l    Length ascending sort.     ");
            System.out.println("    L    Length descending sort.    ");
            System.out.println("    n    Name ascending sort.       ");
            System.out.println("    N    Name descending sort.      ");
            System.exit(0);
        }

        final char option = args[0].charAt(0); // 정렬옵션

        String currDir = System.getProperty("user.dir");
        File dir = new File(currDir);
        File[] files = dir.listFiles();

        Comparator comp = new Comparator() {
            public int compare(Object o1, Object o2) {
                long time1 = ((File)o1).lastModified();
                long time2 = ((File)o2).lastModified();

                long length1 = ((File) o1).length();
                long length2 = ((File) o2).length();

                String name1 = ((File)o1).getName().toLowerCase();
                String name2 = ((File)o2).getName().toLowerCase();

                int result = 0;

                switch (option) {
                    case 't' :
                        if(time1-time2 > 0) result = 1;
                        else if(time1-time2 == 0) result = 0;
                        else if(time1-time2 < 0) result = -1;
                        break;
                    case 'T' :
                        if(time1-time2 > 0) result = -1;
                        else if(time1-time2 == 0) result = 0;
                        else if(time1-time2 < 0) result = 1;
                        break;
                    case 'l' :
                        if(length1-length2 > 0) result = 1;
                        else if(length1-length2 == 0) result = 0;
                        else if(length1-length2 < 0) result = -1;
                        break;
                    case 'L' :
                        if(length1-length2 > 0) result = -1;
                        else if(length1-length2 == 0) result = 0;
                        else if(length1-length2 < 0) result =  1;
                        break;
                    case 'n' :
                        result = name1.compareTo(name2);
                        break;
                    case 'N' :
                        result = name2.compareTo(name1);
                        break;
                }
                return result;
            }
            public boolean equals(Object o) {return false;}
        };

        Arrays.sort(files, comp); // Comparator comp를 기준으로 files를 정렬한다
        for (int i=0; i < files.length; i++) {
            File f = files[i];
            String name = f.getName();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mma");
            String attribute = "";
            String size = "";

            if (files[i].isDirectory()) { // 서브 디렉토리인 경우
                attribute = "DIR";
            } else { // 파일인 경우
                size = f.length() + "";
                attribute = f.canRead() ? "R" : " ";
                attribute += f.canWrite() ? "W" : " ";
                attribute = f.isHidden() ? "H" : " ";
            }

            System.out.printf("%s %3s %6s %s\n",
                    df.format(new Date(f.lastModified())), attribute, size,name);
            // lastModified()는 마지막으로 수정된 시간을 반환
        }
    }
}

class FileEx6 {
    static int found = 0;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("USAGE : java FileEx6 DIRECTORY KEYWORD");
            System.exit(0);
        }

        File dir = new File(args[0]);
        String keyword = args[1];

        if(!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }

        try {
            findInFiles(dir,keyword);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("총 " + found + "개의 라인에서 '" + keyword + "'을/를 발견하였습니다.");
    }

    public static void findInFiles(File dir, String keyword) throws IOException{
        File[] files = dir.listFiles();

        for(int i=0; i < files.length; i++) {
            if(files[i].isDirectory()) { // 디렉토리인경우
                findInFiles(files[i], keyword); // 재귀호출로 디렉토리 내부를 탐색
            } else { // 파일인경우
                String filename = files[i].getName();
                String extension = filename.substring(filename.lastIndexOf(".") + 1);
                extension = "," + extension + "," ;

                if(",java,txt,bak,".indexOf(extension) == -1) continue; // 1. 파일 확장자가 java , txt, bak 중 하나인지 확인
                // 양쪽에 , , 를 붙여줌으로써 ,jav 와 같이 일부만 일치하는 것을 걸러낸다

                filename = dir.getAbsolutePath() + File.separator + filename;

                FileReader fr = new FileReader(files[i]); // 2. 파일을 찾았으면, 파일을 fr과 br을 이용해서 읽는다
                BufferedReader br = new BufferedReader(fr);

                String data = "";
                int lineNum = 0;

                while((data =br.readLine()) != null) { // 3. 파일을 한줄씩 읽어서
                    lineNum++;

                    if(data.indexOf(keyword)!=-1) { // 4. 만약 인자로 받은 키워드가 읽은 라인에 있다면
                        found++;
                        System.out.println("["+filename+"("+lineNum+")"+"]"+data);
                    }
                }
                br.close();
            }
        }
    }
}

class FileEx7 {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.out.println("USAGE : java FileEx7 pattern");
            System.exit(0);
        }

        String currDir = System.getProperty("user.dir"); // 현재위치

        File dir = new File(currDir);
        final String pattern = args[0];

        String[] files = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.indexOf(pattern) != -1;
            }
        });

        for(int i=0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }
}

class FileEx9 {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java FileEx9 DIRECTORY");
            System.exit(0);
        }

        File dir = new File(args[0]);

        if(!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다");
            System.exit(0);
        }

        File[] list = dir.listFiles();

        for(int i=0; i<list.length; i++) {
            String fileName = list[i].getName();

            String newFileName = "0000" + fileName;
            newFileName = newFileName.substring(newFileName.length() - 7);
            list[i].renameTo(new File(dir, newFileName));
        }
    }
}

class FileSplit {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("USAGE : java FileSplit filename SIZE_KB");
            System.exit(0);
        }

        final int VOLUME = Integer.parseInt(args[1]) * 1000;

        try {
            String filename = args[0];
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);

            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            int data = 0;
            int i    = 0 ;
            int number = 0;

            while ((data = bis.read()) != -1) {
                if (i%VOLUME ==0) {
                    if (i!=0) {
                        bos.close();
                    }

                    fos = new FileOutputStream(filename + "_." + ++number);
                    bos = new BufferedOutputStream(fos);
                }
                bos.write(data);
                i++;
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}