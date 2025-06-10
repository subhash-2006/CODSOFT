import java.util.*;
import java.util.concurrent.*;

class Questions {
	int score = 0;
	Scanner s;

	Questions(Scanner scanner) {
		this.s = scanner;
	}

	void question1() {
		System.out.println("\n================== Question 1 ==================");
		System.out.println("What does the 'this' keyword refer to in Java?");
		System.out.println("A. The parent class");
		System.out.println("B. A static variable");
		System.out.println("C. The current object");
		System.out.println("D. A superclass method");
		System.out.print("Choose the correct option (A/B/C/D): ");

		handleAnswer('C', "The current object");
	}

	void question2() {
		System.out.println("\n================== Question 2 ==================");
		System.out.println("Which collection in Java doesn’t allow duplicates?");
		System.out.println("A. ArrayList");
		System.out.println("B. LinkedList");
		System.out.println("C. HashSet");
		System.out.println("D. Vector");
		System.out.print("Choose the correct option (A/B/C/D): ");

		handleAnswer('C', "HashSet");
	}

	void question3() {
		System.out.println("\n================== Question 3 ==================");
		System.out.println("Which of these data types has the highest precision?");
		System.out.println("A. float");
		System.out.println("B. int");
		System.out.println("C. double");
		System.out.println("D. short");
		System.out.print("Choose the correct option (A/B/C/D): ");

		handleAnswer('C', "double");
	}

	void question4() {
		System.out.println("\n================== Question 4 ==================");
		System.out.println("Which of the following is NOT a Java keyword?");
		System.out.println("A. static");
		System.out.println("B. Boolean");
		System.out.println("C. void");
		System.out.println("D. private");
		System.out.print("Choose the correct option (A/B/C/D): ");

		handleAnswer('B', "Boolean");
	}

	void question5() {
		System.out.println("\n================== Question 5 ==================");
		System.out.println("What is the default value of a boolean variable in Java (class level)?");
		System.out.println("A. true");
		System.out.println("B. false");
		System.out.println("C. 0");
		System.out.println("D. null");
		System.out.print("Choose the correct option (A/B/C/D): ");

		handleAnswer('B', "false");
	}

	void handleAnswer(char correctOption, String correctAnswer) {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Future<String> future = ex.submit(() -> s.nextLine().trim());

		try {
			String inputStr = future.get(5, TimeUnit.SECONDS);
			if (inputStr.isEmpty()) {
				throw new Exception("Empty input");
			}
			char input = Character.toUpperCase(inputStr.charAt(0));

			if (input == correctOption) {
				score++;
				System.out.println("\n Correct Answer!");
			} else {
				System.out.println("\n You chose the wrong option.");
				System.out.println(" Correct Answer: " + correctAnswer);
			}
		} catch (TimeoutException e) {
			System.out.println("\n You didn’t answer in time!");
			System.out.println(" Correct Answer: " + correctAnswer);
		} catch (Exception e) {
			System.out.println("\n Invalid input or no input received.");
			System.out.println(" Correct Answer: " + correctAnswer);
		} finally {
			System.out.println(" Current Score: " + score);
			ex.shutdownNow();
		}
	}
}

public class QuizzApplication {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Questions q = new Questions(scanner);

		System.out.println(" Welcome to the Java Quiz Challenge!");
		System.out.println(" You have 5 seconds to answer each question.\n");
		try { Thread.sleep(3000); } catch (Exception e) {}

		q.question1();
		waitBetweenQuestions();

		q.question2();
		waitBetweenQuestions();

		q.question3();
		waitBetweenQuestions();

		q.question4();
		waitBetweenQuestions();

		q.question5();

		System.out.println("\n\n================== Quiz Completed ==================");
		System.out.println(" Final Score: " + q.score + " out of 5");
		System.out.println(" Thank you for playing the quiz!");

		scanner.close();
	}

	private static void waitBetweenQuestions() {
		System.out.println("\n Get ready for the next question...\n");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted!");
		}
	}
}
