import java.util.*;

class ATM 
{
    Scanner s = new Scanner(System.in);
    String username;
    int accountno;
    int pin;

    double deposit;
    double withdraw;
    double balance;
    String reason;

    ArrayList<String> transactionhistory = new ArrayList<>();

    
    ATM(String un, int an, int p, double initialBalance) {
        username = un;
        accountno = an;
        pin = p;
        balance = initialBalance;
    }

    void Deposit() {
        System.out.print("Enter amount to deposit: Rs.");
        deposit = s.nextDouble();
        balance += deposit;
        transactionhistory.add("Deposited: Rs." + deposit);
        System.out.println("Amount Deposited Successfully.");
    }

    void Withdraw() {
        System.out.print("Enter amount to withdraw: Rs.");
        withdraw = s.nextDouble();
        System.out.println("Enter the Reason: ");
        s.nextLine();
        reason = s.nextLine();
        if (withdraw <= balance) {
            balance -= withdraw;
            transactionhistory.add("Reason: "+ reason +" Withdraw Amount: Rs." + withdraw);
            System.out.println("Amount Withdrawn Successfully.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    void Checkbalance() {
        System.out.println("User Name: " + username);
        System.out.println("Account Number: " + accountno);
        System.out.println("Available Balance: Rs." + balance);
    }

    void History() {
        System.out.println();
        System.out.println("---- Transaction History ----");
        if (transactionhistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String txn : transactionhistory) {
                System.out.println(txn);
            }
        }
        System.out.println("-----------------------------");
    }
}

public class ATMinterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your User Name: ");
        String name = sc.nextLine();

        System.out.print("Enter your Account Number: ");
        int acn = sc.nextInt();

        System.out.print("Enter four-digit PIN: ");
        int pin = sc.nextInt();

        System.out.print("Enter initial balance: Rs.");
        double initialBal = sc.nextDouble();

        ATM a = new ATM(name, acn, pin, initialBal);

        while (true) {
            System.out.println();
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("You selected: Check Balance");
                    a.Checkbalance();
                    break;
                case 2:
                    System.out.println("You selected: Deposit");
                    a.Deposit();
                    break;
                case 3:
                    System.out.println("You selected: Withdraw");
                    a.Withdraw();
                    break;
                case 4:
                    System.out.println("You selected: View Transaction History");
                    a.History();
                    break;
                case 5:
                    System.out.println("You selected: Exit");
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return; 
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
