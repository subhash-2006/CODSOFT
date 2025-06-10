import java.util.*;

class NumberGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        int randomNo = rand.nextInt(100) + 1;

        System.out.println("\n\n\t\t Welcome to the NUMBER GAME ");
        System.out.println("\t\t-----------------------------------\n");

        int chanceRemaining = 3;
        int inputNumber;
        boolean nextRound = false;

        System.out.println(" Round 1 - Easy Mode ");
        System.out.println(" Guess the number between 1 - 100");
        System.out.println(" You have 3 chances!\n");

        while (chanceRemaining > 0) {
            chanceRemaining--;
            System.out.print(" Enter your guess: ");
            inputNumber = in.nextInt();

            if (randomNo == inputNumber) {
                System.out.println("\n That Was Awesome! You Guessed the Correct Number!");
                System.out.println(" Let's move to the next round!\n");
                nextRound = true;
                break;
            }

            if (randomNo != inputNumber) {
                nextRound = false;
                if (chanceRemaining == 0) {
                    System.out.println("\n Score = 0");
                    System.out.println(" Sorry! You lost...");
                    System.out.println(" Well Played!! Better Luck Next Time.\n");
                    return;
                } else if (randomNo > inputNumber) {
                    System.out.println(" Your Guess is Too Low!");
                } else {
                    System.out.println(" Your Guess is Too High!");
                }
            }

            System.out.println(" Attempts left: " + chanceRemaining + "\n");
        }

        if (nextRound) {
            randomNo = rand.nextInt(100) + 1;

            System.out.println("------------------------------------------------------------");
            System.out.println("\n\t Round 2 - Medium Mode \n");
            System.out.println(" Rules:");
            System.out.println("   - Number of attempts: 2");
            System.out.println("   - Bonus: Remaining attempts from Round 1 carry over!\n");

            chanceRemaining += 2;

            while (chanceRemaining > 0) {
                chanceRemaining--;
                System.out.print(" Enter your guess: ");
                inputNumber = in.nextInt();

                if (randomNo == inputNumber) {
                    System.out.println("\n That Was Awesome! You Guessed the Correct Number!");
                    System.out.println(" Let's move to the FINAL round!\n");
                    nextRound = true;
                    break;
                }

                if (randomNo != inputNumber) {
                    nextRound = false;
                    if (chanceRemaining == 0) {
                        System.out.println("\n Score = 50");
                        System.out.println(" Sorry! You lost...");
                        System.out.println(" Well Played!! Better Luck Next Time.\n");
                        return;
                    } else if (randomNo > inputNumber) {
                        System.out.println(" Your Guess is Too Low!");
                    } else {
                        System.out.println(" Your Guess is Too High!");
                    }
                }

                System.out.println(" Attempts left: " + chanceRemaining + "\n");
            }
        }

        if (nextRound) {
            randomNo = rand.nextInt(100) + 1;

            System.out.println("------------------------------------------------------------");
            System.out.println("\n\t FINAL ROUND - HARD MODE \n");
            System.out.println(" Rules:");
            System.out.println("   - Number of attempts: 1\n");

            chanceRemaining += 1;

            while (chanceRemaining > 0) {
                chanceRemaining--;
                System.out.print(" Enter your guess: ");
                inputNumber = in.nextInt();

                if (randomNo == inputNumber) {
                    System.out.println("\n That Was Epic! You Guessed the Correct Number!");
                    System.out.println(" CONGRATULATIONS! YOU WON THE GAME!\n");
                    break;
                } else {
                    System.out.println("\n Sorry! You lost...");
                    System.out.println(" Great effort! Better Luck Next Time.\n");
                }
            }
        }
    }
}
