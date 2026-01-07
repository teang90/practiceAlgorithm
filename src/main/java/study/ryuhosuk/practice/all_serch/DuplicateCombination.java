package study.ryuhosuk.practice.all_serch;


public class DuplicateCombination {
    // 서로 다른 n개에서 순서 없이, 중복이 가능하게 r개를 뽑는 경우의 수
    public static void combination(int[] arr, int[] out, int start, int depth, int r){
        if(depth == r){
            for(int num : out) System.out.print(num);
            System.out.println();
            return;
        }
        for(int i=start; i<arr.length; i++){
            out[depth] = arr[i];
            combination(arr, out, i, depth+1, r);
        }
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        int r = 2;
        combination(arr, new int[r], 0, 0, r);
    }
}
