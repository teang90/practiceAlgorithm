package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2805_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] trees;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        trees = new int[N];
        for (int i = 0; i < N; i++) trees[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(trees);
    }
    static boolean possible(int H){
        boolean res = false;
        long sum=0;
        for (int i = 0; i < N; i++) {
            sum+= (trees[i] - H >=0)? (trees[i] - H) : 0;
        }

        return sum>=M;
    }
    static void sol() {
        // 얼만큼의 H를 설정해야 M을 얻을 수 있을까?
        int H = 0, L=1, R=2000000000;// H: 절단기에 설장할 수 있는 최대값
//        int H = Integer.MIN_VALUE, L=trees[0], R=trees[N-1];// H: 절단기에 설장할 수 있는 최대값 -> 이렇게하니까 틀림
        // H도 Integer의 min 값으로 설정시 틀림 -> 아마 할당 안되는 케이스가있어서 INteger min 값으로 출력되서? 그런듯?
        while (L<R){
            int mid = (L+R)/2;
            if(possible(mid)){  // M을 넘는 경우
                L = mid+1;
                H = mid;
//                H = Math.max(H, mid);       // -> 이거 안됨...

            }else{
                R = mid - 1;
            }
        }
        System.out.println(H);

    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}