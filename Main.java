import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        //board game
        char[][] gameBoard = {{' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '},
                {'-','+','-','+','-'},
                {' ','|',' ','|',' '}};

        printGameBoard(gameBoard);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your placement: ");
            int position = sc.nextInt();
            while (playerPositions.contains(position)|| cpuPositions.contains(position)){
                System.out.println("Position taken, try another");
                position = sc.nextInt();
            }

            placePiece(gameBoard, position, "player");

            String winning = checkWinner();
            if (winning.length()>0){
                System.out.println(winning);
                break;
            }

            Random rd = new Random();
            int cpuPos = rd.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos)|| cpuPositions.contains(cpuPos)){
                cpuPos = rd.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            String winnning = checkWinner();
            if (winnning.length()>0){
                System.out.println(winnning);
                break;
            }
            System.out.println(winnning);
        }

    }

    public static void printGameBoard(char[][] gameBoard){
        for (char[] row: gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] gameBoard, int position, String user){

        char symbol = ' ';

        if(user.equals("player")){
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")){
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List>winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List l : winningConditions){
            if (playerPositions.containsAll(l)){
                return "Congrats, you won!";
            } else if (cpuPositions.contains(l)){
                return  "Sorry, Mac has beaten ur ass!";
            } else if (playerPositions.size() + cpuPositions.size() == 9){
                return "Tie!";
            }
        }
        return "";
    }
}
