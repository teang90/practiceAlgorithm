package study.self_repeat;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110_2 {
    static int N; //집의 갯수 N: [2, 200000]
    // C
    static int C; // 공유기 갯수 C: [2, N]
    // D // 인접한 두 공유기 사이의 거리이며 이 값을 가능한 크게 설치
    static int[] devices;   // 공유기의 좌표값을 갖고있는 배열    좌표 범위: [0 ≤ xi ≤ 1,000,000,000]
    static FastReader scan = new FastReader();
    static void input() {
        N = scan.nextInt();
        C = scan.nextInt();
        devices = new int[N];
        for (int i = 0; i < N; i++) devices[i] = scan.nextInt();
    }
    static boolean determin(int dist){
        // 설치 해보자!
        int cntOfSetDevice =1; // 설치된 공유기의 갯수
        int lastCoord = devices[0];
        for(int i=1; i<N; i++){     // 최초 설치는 이미 lastCoord에서 했음, (already setDeviceCnt = 1)
            // 공유기 사이의 거리가 dist 내/외인지 판단하면서 공유기를 설치하자
            if(devices[i] - lastCoord >= dist){
                cntOfSetDevice++;
                lastCoord = devices[i];
            }
            if(cntOfSetDevice == C) return true;
        }

        return cntOfSetDevice >= C;
    }
    static void sol() {
        Arrays.sort(devices);
        // 갖고있는 C개의 공유기로, 공유기 사이의 거리를 D로 했을떄 설치가 가능한가? 그리고 D의 최대값은?
        int L=0, R= devices[N-1], D=0;
//        int L=0, R= 1000000000, D=0;
        while (L <= R){
            int mid = (L+R)/2;
            if(determin(mid)){
                L = mid + 1;
                D = Math.max(D, mid);
            }else{
                R = mid - 1;
            }
        }
        System.out.println(D);
    }
    public static void main(String[] args) {
        input();
        sol();
    }
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
