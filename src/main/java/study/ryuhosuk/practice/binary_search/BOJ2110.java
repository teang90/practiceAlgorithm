package study.ryuhosuk.practice.binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, C;
    static int[] coords;
    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        coords = new int[N];
        for (int i = 0; i < N; i++) coords[i] = Integer.parseInt(br.readLine());
    }
    static boolean determin(int D){
        int cntOfDevice = 1;
        int lastCoord = coords[0];
        for (int i = 1; i < N; i++) {
            if(coords[i] - lastCoord >= D){
                cntOfDevice++;
                lastCoord = coords[i];
            }
        }

        return cntOfDevice>=D;
    }

    static void sol() {
        Arrays.sort(coords);
        int L=0, R=1000000000, dist=0;
        while (L<=R) {
            int mid = (L+R)/2;
            if(determin(mid)){
                dist = mid;
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        System.out.println(dist);
    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
