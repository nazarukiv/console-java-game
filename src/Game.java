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
     private ArrayList<Enemy> enemyArrayList = new ArrayList<Enemy>();
     private Random randomNumber = new Random();
     private Player player;
     private Scanner scanner = new Scanner(System.in);
     private int triesToRegenerate = 10;



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
             computerTurn();
             checkIfGameNotFinished();
         }
    }

    private void placeFlowers() {
        generateFlowers();
    }

    private void placeEnemies() {
        for (int i = 0; i < amountOfEnemies; i++) {

            int row = randomNumber.nextInt(sizeX);
            int col = randomNumber.nextInt(sizeY);

            if (field.getFieldable(row, col) instanceof Empty) {
                Enemy enemy = new Enemy(row, col);
                field.setFieldable(row, col, enemy);
                enemyArrayList.add(enemy);
            } else {
                i--;
            }
        }
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
        enemyMove();
        generateFlowers();
    }

    private void playerTurn() {
        System.out.print("Move (w-> up/ a->left/ s->down/ d-right): ");
        String command = scanner.nextLine().trim().toLowerCase();
        player.makeMove(command);
        movesLeft--;
    }

    private void enemyMove() {
        for (Enemy enemy : enemyArrayList) {

            int r = enemy.getRowIndex();
            int c = enemy.getColumnIndex();

            int dRow = randomNumber.nextInt(3) - 1;
            int dCol = randomNumber.nextInt(3) - 1;

            int newRow = r + dRow;
            int newCol = c + dCol;

            if (!field.isInside(newRow, newCol)) continue;

            Fieldable target = field.getFieldable(newRow, newCol);

            if (target instanceof Enemy || target instanceof Player) continue;

            if (target instanceof Flower) {
                flowerArrayList.remove((Flower) target);
            }

            field.setFieldable(r, c, new Empty());
            field.setFieldable(newRow, newCol, enemy);

            enemy.setRowIndex(newRow);
            enemy.setColumnIndex(newCol);
        }
    }

    private void swapEnemy(int oldRow, int oldCol, int newRow, int newCol, Enemy enemy) {
        field.setFieldable(oldRow, oldCol, new Empty());
        field.setFieldable(newRow, newCol, enemy);
        enemy.setRowIndex(newRow);
        enemy.setColumnIndex(newCol);
    }

    private void generateFlowers() {
        int needToSpawn = amountOfFlowers - flowerArrayList.size();
        int attempts = 0;

        while (needToSpawn > 0 && attempts < 1000) {
            attempts++;

            int t = randomNumber.nextInt(9) + 1;
            int r = randomNumber.nextInt(sizeX);
            int c = randomNumber.nextInt(sizeY);

            Fieldable cell = field.getFieldable(r, c);

            if (cell instanceof Empty) {
                Flower flower = new Flower(t, r, c);
                field.setFieldable(r, c, flower);
                flowerArrayList.add(flower);
                needToSpawn--;
            }
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
