public class Main {
    public static void main(String args[]){
        ConsoleTable ct = new ConsoleTable();
        ct.load("/Users/henrylai/Desktop/test.txt");
        System.out.println(ct.whiteToString());
        System.out.println(ct.blackToString());
    }
}
