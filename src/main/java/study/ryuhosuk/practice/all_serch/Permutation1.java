package study.ryuhosuk.practice.all_serch;

public class Permutation1 {
    // 서로 다른 n개에서 r개를 뽑아서 정렬하는 경우의 수
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int r = 2;
        permutation(arr, new int[r], new boolean[arr.length], 0, r);
    }

    private static void permutation(int[] arr, int[] out, boolean[] visit, int depth, int r) {
        if(depth==r){
            // stop
            for(int num: out) System.out.print(num);
            System.out.println();
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if(!visit[i]){
                visit[i] = true;
                out[depth] = arr[i];
                permutation(arr, out, visit, depth+1, r);
                visit[i] = false;
            }
        }
    }
}
