package study.self_repeat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ15970_1 {
    static Scanner sc = new Scanner(System.in);
    static int N;
    static List<Integer>[] points;
    void input(){
        N = Integer.parseInt(sc.nextLine().trim());
        points = new ArrayList[N];
        for(int i=0; i<N; i++){

            String[] params = sc.nextLine().trim().split(" ");
            int coord = Integer.parseInt(params[0]);
            int color = Integer.parseInt(params[1]);
            if(points[color]==null){
                points[color] = new ArrayList<>();
            }
            points[color].add(coord);
        }
    }
    void sol(){ // 점 집합에서 원소 p는 가장 인접한 원소 q와 연결할 수 있으며, 연결했을떄의 p와 q 사이의 거리의 합을 모든 원소를 대상으로 구하여라.
        int distSum = 0;
        for(int i=0; i<N; i++){
            List<Integer> coordsByColor = points[i];
            if(coordsByColor==null) continue;

            Collections.sort(coordsByColor);

            for(int j=0; j < coordsByColor.size(); j++){
                // 좌측 끝이냐? // 우측 끝이냐?
                int leftDist = leftSideDist(j, coordsByColor);
                int rightDist = rightSideDist(j, coordsByColor);
                distSum += Math.min(leftDist, rightDist);
            }
        }
        System.out.println(distSum);
    }
    int leftSideDist(int idx, List<Integer> coords){     // 좌측에 점이 있으면 해당 점과의 거리를 반환
        if(idx==0) return Integer.MAX_VALUE;
        else return coords.get(idx)-coords.get(idx-1);
    }
    int rightSideDist(int idx, List<Integer> coords){     // 우측에 점이 있으면 해당 점과의 거리를 반환
        if(idx+1 == coords.size()) return Integer.MAX_VALUE;
        else return coords.get(idx+1)-coords.get(idx);
    }
    public static void main(String[] args) {
        BOJ15970_1 boj159701 = new BOJ15970_1();
        boj159701.input();
        boj159701.sol();
    }
//    static class Point implements Comparable<Point> {
//        int coord, color;
//        public Point(int coord, int color){
//            this.coord = coord;
//            this.color = color;
//        }
//
//        @Override
//        public String toString() {
//            return ""+this.coord;
//        }
//
//        @Override
//        public int compareTo(@NotNull Point o) {
//            if(this.coord > o.coord) return 1;
//            else if(this.coord < o.coord) return -1;
//            else return 0;
//        }
//    }
}
