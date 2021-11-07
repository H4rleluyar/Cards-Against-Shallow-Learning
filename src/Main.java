public class Main {
    public static void main(String args[]){
        ConsoleTable ct = new ConsoleTable();
        ct.load("test.txt");
        System.out.println(ct.whiteToString());
        System.out.println(ct.blackToString());

        Player player1 = new Player("Bill"), player2 = new Player("HenryL");
        ct.addPlayers(player1);
        ct.addPlayers(player2);
        ct.dealCards();
        System.out.println("Bill's card:" + player1.getHand().toString());
    }
}
