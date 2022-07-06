package ch13;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinEx {
    static final ForkJoinPool pool = new ForkJoinPool(); // 쓰레드 풀 생성

    public static void main(String[] args) {
        long from = 1L, to = 100_000_000L;

        SumTask task = new SumTask(from, to);

        long start = System.currentTimeMillis(); // 시작시간 초기화
        Long result = pool.invoke(task);  // fork&join으로 계산
        System.out.println("Elapsed time(4 core):" + (System.currentTimeMillis() - start));

        System.out.printf("sum of %d~%d = %d%n", from, to, result);
        System.out.println();
        result = 0L;
        start = System.currentTimeMillis(); // 시작시간 초기화
        for (long i = from; i<=to; i++)
            result += i; // for문으로 계산

        System.out.println("Elapsed time(4 core):" + (System.currentTimeMillis() - start));
        System.out.printf("sum of %d~%d = %d%n", from, to, result);
    }
}

class SumTask extends RecursiveTask<Long> {
    long from, to;

    SumTask(long from, long to) {
        this.from = from;
        this.to   = to;
    }

    public Long compute() { // 범위를 반으로 나눠서 두개의 작업 생성
        long size = to - from + 1;

        if (size <= 5)
            return sum();

        long half = (from + to) /2;

        SumTask leftSum = new SumTask(from, half);
        SumTask rightSum = new SumTask(half+1, to);

        leftSum.fork();

        return rightSum.compute() + leftSum.join();
    }

    long sum() { // from~to의 모든 숫자 더한 결과 반환
        long tmp = 0L;

        for (long i = from; i<=to; i++)
            tmp += i;

        return tmp;
    }
}