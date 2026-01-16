import java.util.Scanner;

public class Main{

    public static int rows=3;
    public static int columns=4;
    public static int amountOfEnemies = 10;
    public static int transistorsNeeded = 100;
    public static int moves = 40;

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
    }

    private static void showCredits() {
        System.out.println("__________________________________________________________\n" +
                "Created by Ivan Nazaruk\n" +
                "version 1.0, last modified on ???\n" +
                "contact me at nazaruk7649@ukr.net\n"+
                "__________________________________________________________\n");
    }

}
