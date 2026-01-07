package study.ryuhosuk.practice.all_serch;

// 1182 부분수열의 합
public class PartialSum {
    static int N, S, ans;   // 정답 : ans
    static int[] nums;

    // k번쨰 원소를 포함시킬지 정하는 함수
    // val:= k-1번째 원소까지의 합
    static void recur(int k, int val){
        if(k==N+1){
            if(val==S){
                ans++;
            }
        }else{
            // k번쨰 원소를 포함시킬지 결정 및 재귀
            // k번쨰 원소 포함
            recur(k+1, val+=nums[k]);

            // k번쨰 원소 포함 X
            recur(k+1, val);
        }
    }

    public static void main(String[] args) {
        recur(1, 0);
        // ans가 정말 진부분집합만 다루는지 확인필요!
        if(S==0) ans--; //S가 진 부분집합없이, 즉 아무 원소도 없는 공집합의 합인경우 경우
    }
}
