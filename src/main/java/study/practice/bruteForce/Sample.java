package study.practice.bruteForce;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sample {
    // recursiv function
    // 만약 M개를 전부 고름 -> 조건 맞는 탐색을 한가지(case) 성공한 것
    // M개를 고르지 않았다면 -> K번쨰부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.

    static void recFun(int k){
    }

    public static void main(String[] args) {
        // 1번쨰 원소부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해라
        recFun(1);

        List<Object> list = new ArrayList<>();
        list.subList(0, 2);

        BlockingQueue<String> q = new LinkedBlockingQueue();
        q.drainTo(new ArrayList<>(), 3000);




    }

}
