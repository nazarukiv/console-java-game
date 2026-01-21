import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
     private int sizeX;
     private int sizeY;
     private int amountOfEnemies;
     private int transistorsNeeded;
     private int transistorsGathered;
     private int movesLeft;
     private int flowersGathered;
     private Field field;
     private boolean isGameFinished = false;
     private int amountOfFlowers;
     private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
     private Random randomNumber = new Random();
     private Player player;
     private Scanner scanner = new Scanner(System.in);


     public Game(int sizeX, int sizeY, int amountOfEnemies, int transistorsNeeded, int movesLeft, int amountOfFlowers) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorsNeeded = transistorsNeeded;
        this.movesLeft = movesLeft;
        this.amountOfFlowers = amountOfFlowers;
        field = new Field(sizeX, sizeY);
    }

    public void fillFieldWithEmptyObjects(){
         for (int i=0;i<sizeX;i++){
             for(int j=0;j<sizeY;j++){
                 field.setFieldable(i, j, new Empty());
             }
         }
    }

    public void startGame(){
         placePlayer();
         placeEnemies();
         placeFlowers();
         
         while (!isGameFinished){
             showField();
             playerTurn();
             //computerTurn();
             checkIfGameNotFinished();
         }
    }

    private void placeFlowers() {
        generateFlowers();
    }

    private void placeEnemies() {
    }

    private void placePlayer() {
         int playerRowPosition = randomNumber.nextInt(sizeX);
         int playerColumnPosition = randomNumber.nextInt(sizeY);
         player = new Player(playerRowPosition, playerColumnPosition, field, this);
    }

    private void checkIfGameNotFinished() {
        if (movesLeft <= 0) {
            isGameFinished = true;
            System.out.println("Game over! No moves left.");
        }

        if (transistorsGathered >= transistorsNeeded) {
            isGameFinished = true;
            System.out.println("You win! All transistors collected!");
        }
    }

    private void computerTurn() {
        movesLeft--;
    }

    private void playerTurn() {
        System.out.print("Please enter your command and press Enter: ");
        String command = scanner.nextLine();
        player.makeMove(command);
        movesLeft--;
    }

    private void generateFlowers(){
        for (int i=amountOfFlowers-flowerArrayList.size(); i > 0;){
            int flowerAmountOfTransistors = randomNumber.nextInt( 9)+1 ;
            int flowerRowPosition = randomNumber.nextInt(sizeX);
            int flowerColumnPosition =  randomNumber.nextInt(sizeY);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Player){
                transistorsGathered = transistorsGathered + flowerAmountOfTransistors;
                i--;
            }

            else  if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Empty){
                Flower flower =  new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;

            }
//                else  if(field.getFieldable(flowerRowPosition, flowerColumnPosition)
//                        instanceof Enemy ||field.getFieldable(flowerRowPosition, flowerColumnPosition)
//                        instanceof Flower){
//
//                }
        }
    }

    public Field getField(){
         return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList(){
         return this.flowerArrayList;
    }

    public void setTransistorsGathered(int transistorsToAdd){
         this.transistorsGathered += transistorsToAdd;
    }


    private void showField() {
        System.out.println(
                "Turns left: " + movesLeft +
                        ", transistors gathered: " + transistorsGathered + "/" + transistorsNeeded
        );
        field.showField();
    }

}
