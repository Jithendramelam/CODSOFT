class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Current balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Current balance: $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Please try again.");
        } else {
            System.out.println("Invalid withdrawal amount. Please try again.");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("Welcome to the ATM");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void selectOption(int option) {
        switch (option) {
            case 1:
                account.checkBalance();
                break;
            case 2:
                System.out.println("Enter the amount to deposit:");
                double depositAmount = getUserInput();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.println("Enter the amount to withdraw:");
                double withdrawAmount = getUserInput();
                account.withdraw(withdrawAmount);
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private double getUserInput() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0); // Initial balance of $1000
        ATM atm = new ATM(account);
        
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int option = 0;

        while (option != 4) {
            atm.showMenu();
            System.out.println("Please select an option:");
            option = scanner.nextInt();
            atm.selectOption(option);
        }
    }
}
