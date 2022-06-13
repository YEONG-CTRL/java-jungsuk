package ch8;

class ChainedExceptionTest {
    public static void main(String[] args) {
        try {
            install();
        } catch (InstallException e) {
            e.printStackTrace();  // 4 ie 받아서 printStackTrace 실행
            System.out.println("ㅇㅇ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void install() throws InstallException {
        try {
            startInstall();
            copyFiles();
        } catch (SpaceException se) { // 2. SpaceException에 따른 예외처리
            InstallException ie = new InstallException("설치 중 예외발생, se에서 비롯된 ie"); // 3
            ie.initCause(se);
            throw ie;
        } catch (MemoryException me) {
            InstallException ie = new InstallException("설치 중 예외발생, me에서 비롯된 ie");
            ie.initCause(me);
            throw ie;
        } finally {
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace()) { // 충분한 설치공간 없으면
            throw new SpaceException("설치할 공간이 부족합니다"); // 1. SpaceException 생성
        }
        if (!enoughMemory()) {
            throw new MemoryException("메모리가 부족합니다");
        }
    }

    static void copyFiles() { /*파일 복사하는 코드*/ }
    static void deleteTempFiles() { /* 임시파일 삭제하는 코드*/ }

    static boolean enoughSpace() {
        // 설치하는데 필요한 공간 있는지 확인하는 코드
        return false;
    }

    static boolean enoughMemory() {
        // 설치하는데 필요한 메모리공간 있는지 확인하는 코드
        return true;
    }
}

class InstallException extends Exception {
    InstallException(String msg) {
        super(msg);
    }
}

class SpaceException extends Exception {
    SpaceException(String msg) {
        super(msg);
    }
}

class MemoryException extends Exception {
    MemoryException(String msg) {
        super(msg);
    }
}