package study.self_repeat;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ2110_parametic_search {
    static int N;   // 집 갯수
    static int C;   // 와이파이 갯수
    static int[] A; // 주어진 와이파이들의 좌표 배열
    static Scanner sc = new Scanner(System.in);
    static void input() {
        String[] arrs = sc.nextLine().split(" ");
        N =Integer.parseInt(arrs[0]);
        C =Integer.parseInt(arrs[1]);
        A = new int[N];
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(sc.nextLine().trim());
    }

    static boolean determine(int D){    // 파라미터인 D
        int lastHome = A[0];
        int capacityOfShareEqup=1;
        for(int i=1; i<N; i++){
            if(A[i] - lastHome < D) continue;

            lastHome = A[i];
            capacityOfShareEqup++;
        }

        return capacityOfShareEqup >= C;
    }


    // Q : C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
    // parametic search: 거리 H를 최대로하는 C개의 공유기 N개를 가장 인접한 공유기에 설치하라?
    static void sol() {
        Arrays.sort(A);
        int L=0, R=1000000000;
        int D = Integer.MIN_VALUE;
        while (L <= R){ // 같아져도 상관없구나... 어차피 인접한 거리 D를 줄여나가는 거니까... L=R이어도 그 중간 거리를 잡고 이분탐색하는거임
            int mid = (L+R)/2;
            if(determine(mid)){ // D라는 거리단위에서 C개의 공유기를 설치할 수 있는가? true
                L = mid+1;
                D = Math.max(D, mid);
//                D = mid;
            }else{
                R = mid-1;
            }
        }
        System.out.println(D);
    }

    public static void main(String[] args) {
        input();
        sol();
    }
}
//
//class TestA{
//    PilotUser pilotUser = null;
//    try {
//        pilotUser = pilotUserRepository.findOne(user).copy();
//    } catch (Exception e) {
//        throw ErrorMessage.NOT_FOUND_USER.getException();
//    }
//    userPermissionRepository
//    .findAll(new UserPermission(){{setUserId(pilotUser.getUserId());}})
//    .stream()
//    .map(UserPermission::copy)
//    .toList();
//
//
//    PilotUser pilotUser = null;
//    try {
//        pilotUser = pilotUserRepository.findOne(user).copy();
//    } catch (Exception e) {
//        throw ErrorMessage.NOT_FOUND_USER.getException();
//    }
//    PilotUser finalPilotUser = pilotUser;
//    userPermissionRepository.findAll(new UserPermission(){{
//        setUserId(finalPilotUser.getUserId());
//    }})
//    .stream()
//    .map(UserPermission::copy)
//    .toList();
//
//
//
//}
