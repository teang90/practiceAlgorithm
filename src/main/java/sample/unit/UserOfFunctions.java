package sample.unit;

public class UserOfFunctions {
    public static void main(String[] args) {
        int a = 3;
        int b = 2;
        FunctionGroups pluse1 = new FunctionGroups() {
            @Override
            public int puls(int a, int b) {
                return 0;
            }
        };

        FunctionGroups function = (n1, n2) -> (int) Math.pow(n1, n2);

        int w = function.puls(2, 2);
        System.out.println(w);



    }

}
