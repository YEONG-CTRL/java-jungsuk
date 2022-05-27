class OverflowEx {
    public static void main(String[] args) {
        short sMin = -32768;
        short sMax = 32767;
        char cMin = 0;
        char cMax = 65535;

        System.out.println("sMin = " + sMin);
        System.out.println("sMin-1 = " + (short)(sMin-1));
        System.out.println("sMax = " + sMax);
        System.out.println("sMax+1 = " + (short)(sMin+1));
        System.out.println("cMin = " + (int)cMin);
        System.out.println("cMin-1 = " + (int)--cMin);
        System.out.println("cMax = " + (int)cMax);
        System.out.println("cMax+1 = " + (int)++cMax);
        System.out.println("cMax+1 = " + (int)(cMax+3));
    }
}

/*
결과
sMin = -32768
sMin-1 = 32767
sMax = 32767
sMax+1 = -32767
cMin = 0
cMin-1 = 65535
cMax = 65535
cMax+1 = 0
cMax+1 = 3
 */
