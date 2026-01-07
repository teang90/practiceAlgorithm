package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110_4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, C;
    static int[] coords;
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

    static void input() throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        coords = new int[N];
        for (int i = 0; i < N; i++) coords[i] = Integer.parseInt(br.readLine());
    }

    static void sol(){
        Arrays.sort(coords, 0, coords.length-1);
        int L=0, R=1_000_000_000, minDistance=Integer.MAX_VALUE;
        while (L<=R) {
            int mid = (L+R)/2;
            if(determine(mid)){
                minDistance = mid;
                L=mid+1;
            }else{
                R=mid-1;
            }
        }

        System.out.println(minDistance);
    }

    static boolean determine(int D){
        int cnt=1, lastPosition=coords[0];
        for (int i = 1; i < N; i++) {
            if(coords[i]-lastPosition>=D){
                cnt++;
                lastPosition=coords[i];
            }
        }
        return cnt >= C;
    }

}
