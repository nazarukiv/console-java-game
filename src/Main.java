import java.util.Scanner;

public class Main{

    public static int sizeX = 10;
    public static int sizeY = 12;



    public static int amountOfEnemies = 12;
    public static int amountOfFlowers = 18;


    public static int transistorsNeeded = 120;
    public static int moves = 80;

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Welcome to Java Game v 1.0. Please make your choice and press 'Enter'.");
            System.out.println("1: start new game");
            System.out.println("2: options");
            System.out.println("3: credits");
            System.out.println("4: exit\n");
            System.out.print("Your choice: ");

            command = scanner.nextLine();
            switch (command){
                case "1":
                    startNewGame();
                    break;
                case "2":
                    OptionsMenu.showOptionsMenu(scanner);
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    break;

                default:
                    System.out.println("Command not recognised! Please try again");
            }
        }
        while (!command.equals("4"));


    }


    private static void startNewGame() {
        Game game = new Game(sizeX, sizeY, amountOfEnemies, transistorsNeeded,
                moves, amountOfFlowers);

        game.fillFieldWithEmptyObjects();
        game.startGame();
    }

    private static void showCredits() {
        System.out.println("__________________________________________________________\n" +
                "Created by Ivan Nazaruk\n" +
                "version 1.0, last modified on ???\n" +
                "contact me at nazaruk7649@ukr.net\n"+
                "__________________________________________________________\n");
    }

}
