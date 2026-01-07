package study.self_repeat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M , V;    // N: 정점[1,1000], M: 간선 수 [1, 10000], V 시작점
    static List<Integer>[] adjList;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static void input() throws Exception {
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        V = Integer.parseInt(arr[2]);
        adjList = new ArrayList[N+1];
        for (int i = 0; i < N; i++)
            adjList[i+1] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] vertexs = br.readLine().split(" ");
            int ver1 =Integer.parseInt(vertexs[0]);
            int ver2 =Integer.parseInt(vertexs[1]);
            adjList[ver1].add(ver2);
            adjList[ver2].add(ver1);
        }

        for (int i = 1; i <= N; i++)
            Collections.sort(adjList[i]);

        visit = new boolean[N+1];
    }
    static void dfs(int start){
        visit[start] = true;
        sb.append(start).append(" ");

        for(int v: adjList[start]){
            if(visit[v]) continue;

            dfs(v);
        }
    }
    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        visit[x] = true;
        q.add(x);
        sb.append(x).append(" ");

        while (!q.isEmpty()){
            int y = q.poll();

            for(int v: adjList[y]){
                if(visit[v]) continue;
                visit[v] = true;
                sb.append(v).append(" ");
                q.add(v);
            }
        }
    }
    static void sol(){
        dfs(1);
        for (int i = 0; i <= N; i++)
            visit[i] = false;

        sb.append("\n");
        bfs(1);
        System.out.println(sb.toString());
    }

//    static void input() {
//        ProblemInputter ipt = new ProblemInputter();
//        ipt.saveOneLine();
//        int[] arr = ipt.getIntArr();
//        N = arr[0];
//        M = arr[1];
//        V = arr[2];
//        ipt.saveOneLine();
//    }
    public static void main(String[] args) throws Exception {
        input();
        sol();
    }

}
class ProblemInputter{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String inputStr = "";
    public void saveOneLine(){
        try{
            this.inputStr = br.readLine();
        }catch (Exception e){
        }
    }
    // 스트링으로 문자열을 받는 경우, 공백으로 구분하는 경우 -> a b c d e f, abc def...
    String getSplitedString() throws Exception{
        return this.getSplitedString(" ");
    }
    String getSplitedString(String delim) throws Exception{
        try{
            StringTokenizer st = new StringTokenizer(this.inputStr, delim);
            String returnStr = st.nextToken();
            this.inputStr = inputStr.replace(returnStr, "").trim();
            return returnStr;
        }catch (Exception e){
            return null;
        }
    }
    // 스트링으로 문자열을 받는데, 문자열이 그냥 다 붙어있는경우  -> abcdef
    String getStr() throws Exception{
        this.inputStr = br.readLine();
        return this.inputStr;
    }
    void resetInput(){
        this.inputStr = "";
    }
    // 스트링으로 문자열을 받았는데, space로 구분되어있으며 숫자로 구성 -> 1 2 3 4
    int[] getIntArr() {
        try{
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int len = 0;
            int[] arr = new int[st.countTokens()];
            while (st.hasMoreTokens()){
                arr[len] = Integer.parseInt(st.nextToken());
                len++;
            }
            return arr;
        }catch (Exception e){
            return null;
        }
    }
}