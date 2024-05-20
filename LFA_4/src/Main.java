public class Main {
    public static void main(String[] args) {
        String rule1 = "O(P|Q|R)+2(3|4)";
        System.out.println("Final string: " + StringGenerator.generateString(rule1));
        System.out.println("---");
        String rule2 = "A*B(C|D|E)F(G|H|i){2}";
        System.out.println("Final string: " + StringGenerator.generateString(rule2));
        System.out.println("---");
        String rule3 = "J+K(L|M|N)*O?(P|Q){3}";
        System.out.println("Final string: " + StringGenerator.generateString(rule3));
    }
}