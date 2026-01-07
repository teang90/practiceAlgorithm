package study.ryuhosuk.practice.tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1068 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, root, erased;
    static List<Integer>[] child;
    static int[] leaf;
    static void input() throws Exception {
        N = Integer.parseInt(br.readLine());
        child = new ArrayList[N];
        leaf = new int[N];
        for (int i = 0; i < N; i++) child[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent==-1){
                root = i;
                continue;
            }
            child[parent].add(i);
        }
        erased = Integer.parseInt(br.readLine());
    }
    // dfs(x, parent) := 정점 x의 부모가 parent이고, subtree(x)의 개수를 세는 함수
    static void dfs(int x, int parent){
        if(child[x].isEmpty()) {
            leaf[x]++;
        }else{
            for (int y : child[x]) {
                if(y == parent) continue;
                dfs(y, x);
                leaf[x] += leaf[y];
            }
        }

    }
    static void sol() {
        for (int i = 0; i < N; i++) {
            if (child[i].contains(erased)){
                child[i].remove(child[i].indexOf(erased)); // 지워주기
            }
        }
        if(root != erased) dfs(root, -1);

        System.out.println(leaf[root]);

    }


    public static void main(String[] args) throws Exception {
        input();
        sol();
    }
}
