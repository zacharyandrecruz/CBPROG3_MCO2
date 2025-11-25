/**
 * The ExpenseTracker class provides a comprehensive system for managing personal finances.
 * It allows users to set budgets, record expenses, track spending by category, 
 * and analyze expenses over daily, weekly, and monthly periods.
 * 
 * <p>Key features include:
 * <ul>
 *   <li>User authentication</li>
 *   <li>Budget setting with date ranges and categories</li>
 *   <li>Expense recording with optional digital transaction details</li>
 *   <li>Custom category management</li>
 *   <li>Daily, monthly, and categorical expense tracking and analysis</li>
 *   <li>Expense percentage breakdown by category</li>
 * </ul>
 * 
 * <p>The system maintains separate collections for budgets, expenses, and categories,
 * and provides various utility methods for financial analysis.
 * 
 * @author Your Name
 * @version 1.0
 * @since 2025
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenseTracker {
    private static ArrayList<Budget> budgets = new ArrayList<>();
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static ArrayList<String> categories = new ArrayList<>();
    private static User user;

    /**
     * Main file for the ExpenseTracker application.
     * Initializes default categories and sample expenses, then demonstrates
     * various expense analysis functionalities.
     * 
     * @param args command line arguments 
     */
    public static void main(String[] args) {
        
        // Set Defaults but you can create custom categories 
        categories.add("Food");
        categories.add("Transportation");
        categories.add("Entertainment");
        categories.add("Utilities");
        categories.add("Shopping");
        categories.add("Healthcare");
        categories.add("Education");
        categories.add("Other");
        expenses.add(new Expense("0", 10, "PHP", new DateTime("2025", "11", "1", "12", "00")));
        expenses.add(new Expense("1", 20, "PHP", new DateTime("2025", "11", "2", "12", "00")));
        expenses.add(new Expense("2", 10, "PHP", new DateTime("2025", "11", "3", "12", "00")));
        expenses.add(new Expense("3", 30, "PHP", new DateTime("2025", "12", "3", "12", "00")));
        expenses.add(new Expense("4", 50, "PHP", new DateTime("2025", "12", "3", "12", "00"), "Food"));
        expenses.add(new Expense("5", 100, "PHP", new DateTime("2025", "12", "3", "12", "00"), "Food"));

        boolean running = true;
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(running){

            System.out.println("\nMain Menu\n=========");
            System.out.println("\n[1] Set Budget\n[2] Record Expense\n[3] View Monthly Expenses\n[4] View Daily Expenses\n[5] View Total Expenses By Category\n[6] View Total Expenses\n[7] View Total Expenses By Percentage\n[8] Exit Program\n");
            System.out.print("Please input the number of your selected option: ");

            try{
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println("");
            }catch (InputMismatchException e){
                System.err.print("\nError! Please input a valid number: ");
                choice = 0;
            }

            switch(choice){

                case 1 : 

                setBudget(sc);
                choice = 0;

                break;

                case 2 : 

                recordExpense(sc);
                choice = 0;

                break;

                case 3 : 

                viewMonthlyExpense();
                choice = 0;

                break;

                case 4 : 

                viewDailyExpense();
                choice = 0;

                break;

                case 5 : 

                String categoryChoice = null;

                System.err.println("Please enter the name of the category you'd like to view (Press enter for Non-Categorized): ");
                categoryChoice = sc.nextLine();

                if(categories.contains(categoryChoice)){
                    viewTotalCategoryExpense(categoryChoice);
                }else if(categoryChoice == ""){
                    viewTotalCategoryExpense(null);
                }else{
                    System.out.println("Error! No category of that name exists.");
                }

                choice = 0;

                break;

                case 6 : 

                viewTotalExpense();
                choice = 0;

                break;

                case 7 : 

                viewCategoryExpensePercentage();
                choice = 0;

                break;

                case 8 : 

                System.out.println("Are you sure?\n[1] Yes\n[2] No");
                System.out.print("Please input the number of your selected option: ");
                choice = sc.nextInt();

                if(choice == 1){
                    running = false;
                }else{
                    choice = 0;
                }

                break;

                default: choice = 0;

            }

        }

        sc.close();

    }
    
    /**
     * Authenticates a user with the provided email and password.
     * 
     * @param email the user's email address
     * @param password the user's password
     * @return true if authentication is successful, false otherwise
     */
    public static boolean login(String email, String password) {
        if (user != null && 
            user.getUserEmail().equals(email) && 
            user.getUserPassword().equals(password)) {
            return true; // Login successful
        }
        return false; 
    }
    
    /**
     * Guides the user through setting a new budget with comprehensive parameters.
     * Collects budget amount, start and end dates, and optional category. Generates a unique
     * budget ID and creates a Budget object that is added to the budgets collection.
     * Displays a summary of the created budget including all specified parameters.
     * 
     * @param sc the Scanner object used to read user input throughout the budget setting process
     */
    public static void setBudget(Scanner sc) {

        System.out.println("Set Budget");
        System.out.println("==========");
        
        // Ask for total amount
        System.out.print("Enter total budget amount: ");
        float amount = sc.nextFloat();
        sc.nextLine(); 
        
        // Ask for start date
        DateTime startDate = getDate(sc, "Start");
        
        // Ask for end date
        DateTime endDate = getDate(sc, "End");
        
        // Ask for category
        displayCategories();
        String category = getCategory(sc);
        
        String budgetID = generateID("BUDG", budgets.size() + 1, 6);

        // Create and store the budget
        Budget budget;
        if (category != null && !category.isEmpty()) {
            budget = new Budget(budgetID, amount, startDate, endDate, category);
            System.out.println("Budget set successfully for category: " + category);
        } else {
            budget = new Budget(budgetID, amount, startDate, endDate);
            System.out.println("Budget set successfully (no category)");
        }
        
        budgets.add(budget);
        
        // Display the summary of the budget
        System.out.println("\nBudget Summary:");
        System.out.println("Amount: " + amount);
        System.out.println("Start: " + startDate.getDateTimeString());
        System.out.println("End: " + endDate.getDateTimeString());
        if (category != null) {
            System.out.println("Category: " + category);
        }

    }
    
    /**
     * Retrieves a budget at the specified index.
     * 
     * @param index the index of the budget to retrieve
     * @return the Budget object at the specified index, or null if index is invalid
     */
    public static Budget getBudget(int index) {
        if (!budgets.isEmpty()) {
            return budgets.get(index); // Return most recent budget
        }
        return null;
    }

    /**
     * Guides the user through recording a new expense with detailed transaction information.
     * Supports both cash expenses and digital transactions with bank details. Collects amount, date,
     * optional bank information (name, account numbers, reference number), and category. Creates
     * an appropriate Expense object (cash or digital) and adds it to the expenses collection.
     * Displays a comprehensive summary of the recorded expense.
     * 
     * @param sc the Scanner object used to read user input throughout the expense recording process
     */
    public static void recordExpense(Scanner sc) {

        System.out.println("Record Expense");
        System.out.println("==========");
        
        // Ask for total amount
        System.out.print("Enter expense amount: ");
        float amount = sc.nextFloat();
        sc.nextLine(); 
        
        // Ask for date
        DateTime expenseDate = getDate(sc, "");

        System.out.print("Enter the name of the bank the expense was recorded in (Press enter for none): ");
        String bankName = ""; 
        bankName = sc.nextLine();

        String bankAccNum = "";
        String refNum = "";
        String recAccNum = "";
        if(!bankName.isEmpty()){
            System.out.print("Enter your bank account number: ");
            bankAccNum = sc.nextLine();

            System.out.print("Enter your expense's reference number: ");
            refNum = sc.nextLine();

            System.out.print("Enter the reciever's account number: ");
            recAccNum = sc.nextLine();
        }

        // Ask for category
        displayCategories();
        String category = getCategory(sc);
        
        String expenseID = generateID("EXPN", expenses.size() + 1, 6);

        // Create and store the expense
        Expense expense;
        
        if(bankName.isEmpty()){

            if (category != null && !category.isEmpty()) {
                expense = new Expense(expenseID, amount, "PHP", expenseDate, category);
                System.out.println("Expense recorded successfully for category: " + category);
            } else {
                expense = new Expense(expenseID, amount, "PHP", expenseDate);
                System.out.println("Expense recorded successfully (no category)");
            }
            
        }else{

            if (category != null && !category.isEmpty()) {
                expense = new Expense(expenseID, bankName, bankAccNum, amount, "PHP", refNum, recAccNum, expenseDate, category);
                System.out.println("Digital Expense recorded successfully for category: " + category);
            } else {
                expense = new Expense(expenseID, bankName, bankAccNum, amount, "PHP", refNum, recAccNum, expenseDate);
                System.out.println("Digital Expense recorded successfully (no category)");
            }

        }
        
        expenses.add(expense);
        
        // Display the summary of the budget
        System.out.println("\nExpense Summary:");
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + expenseDate.getDateTimeString());
        if (category != null) {
            System.out.println("Category: " + category);
        }
        if(!bankName.isEmpty()){
            System.out.println("Bank Name: " + bankName);
            System.out.println("Bank Account Number: " + bankAccNum);
            System.out.println("Reference Number: " + refNum);
            System.out.println("Reciever's Account Number" + recAccNum);
        }

    }

    /**
     * Calculates the daily average of expenses.
     * 
     * @return the average daily expense amount as a float
     */
    public static float computeDailyAve() {
        
        float dailyAverage = 0;
        ArrayList<Float> expensePerDay;
        float numOfDays;

        expensePerDay = getDailyExpenses();
        numOfDays = expensePerDay.get(0);
        expensePerDay.remove(0);

        for(int i = 0; i < expensePerDay.size(); i++){
            dailyAverage += expensePerDay.get(i);
        }

        dailyAverage = dailyAverage/numOfDays;

        return dailyAverage;

    }
    
    /**
     * Calculates the monthly average of expenses.
     * 
     * @return the average monthly expense amount as a float
     */
    public static float computeMonthlyAverage() {
        
        float monthlyAverage = 0;
        ArrayList<Float> expensePerMonth;
        float numOfMonths;

        expensePerMonth = getMonthlyExpenses();
        numOfMonths = expensePerMonth.get(0);
        expensePerMonth.remove(0);

        for(int i = 0; i < expensePerMonth.size(); i++){
            monthlyAverage += expensePerMonth.get(i);
        }

        monthlyAverage = monthlyAverage/numOfMonths;

        return monthlyAverage;
        
    }

    /**
     * Displays all monthly expenses with their totals and calculates the monthly average.
     * Groups expenses by month and year, showing the total for each month.
     */
    public static void viewMonthlyExpense() {
        
        System.out.println("Viewing Monthly Expenses");
        System.out.println("==========");

        int totalMonths = 0;
        float currentTotal = -1;
        String currentMonth = "";
        String currentYear = "";
        String currency = "";
        for(Expense e : expenses){
            
            if(!e.getExpenseDateTime().getMonth().equals(currentMonth) || !e.getExpenseDateTime().getYear().equals(currentYear)){
                if(currentTotal != -1){
                    System.out.println(Integer.toString(totalMonths) + ". " + currentMonth + " / " + currentYear + " - " + currentTotal + " " + e.getExpenseCurrency());
                }
                currentMonth = e.getExpenseDateTime().getMonth();
                currentYear = e.getExpenseDateTime().getYear();
                currentTotal = 0;
                currentTotal += e.getExpenseAmount();
                totalMonths += 1;
            }else{

                currentTotal += e.getExpenseAmount();

            }

            if(currency.isEmpty()){
                currency = e.getExpenseCurrency();
            }

        }

        System.out.println(Integer.toString(totalMonths)  + ". " + currentMonth + " / " + currentYear + " - " + currentTotal + " " + currency);

        System.out.println("Computed Monthly Average: " + computeMonthlyAverage() + " " + currency);

    }
    
    /**
     * Displays all daily expenses with their totals and calculates the daily average.
     * Groups expenses by date, showing the total for each day.
     */
    public static void viewDailyExpense() {
        
        System.out.println("Viewing Daily Expenses");
        System.out.println("==========");

        int totalDays = 0;
        float currentTotal = -1;
        String currentDay = "";
        String currency = "";
        for(Expense e : expenses){
            
            if(!e.getExpenseDateTime().getDateString().equals(currentDay)){
                if(currentTotal != -1){
                    System.out.println(totalDays + ". " + currentDay + " - " + currentTotal + " " + e.getExpenseCurrency());
                }
                currentDay = e.getExpenseDateTime().getDateString();
                currentTotal = 0;
                currentTotal += e.getExpenseAmount();
                totalDays += 1;
            }else{

                currentTotal += e.getExpenseAmount();

            }

            if(currency.isEmpty()){
                currency = e.getExpenseCurrency();
            }

        }

        System.out.println(totalDays + ". " + currentDay + " - " + currentTotal + " " + currency);

        System.out.println("Computed Daily Average: " + computeDailyAve() + " " + currency);

    }

    /**
     * Displays expenses filtered by category and calculates the total for that category.
     * If category is null, displays all uncategorized expenses.
     * 
     * @param category the category to filter by, or null for uncategorized expenses
     */
    public static void viewTotalCategoryExpense(String category) {
        
        int counter = 0;
        float totalExpense = 0;
        String currency = "";

        if(category == null){

            System.out.println("Viewing Non-Categorized Expenses");
            System.out.println("==========");

            for(Expense e : expenses){

                if(e.getExpenseCategory() == null){

                    counter += 1;
                    totalExpense += e.getExpenseAmount();

                    System.out.println(counter + ". " + e.getExpenseDateTime().getDateString() + " - " + e.getExpenseAmount() + " " + e.getExpenseCurrency());


                }

                if(currency.isEmpty()){
                    currency = e.getExpenseCurrency();
                }

            }

            System.err.println("Total Expenses for Non-Categorized: " + totalExpense + " " + currency);

        }else{

            System.out.println("Viewing  Expenses in the " + category + " Category");
            System.out.println("==========");

            for(Expense e : expenses){

                if(e.getExpenseCategory() != null){

                    if(e.getExpenseCategory().equals(category)){

                        counter += 1;
                        totalExpense += e.getExpenseAmount();
    
                        System.out.println(counter + ". " + e.getExpenseDateTime().getDateString() + " - " + e.getExpenseAmount() + " " + e.getExpenseCurrency());
    
                    }

                }

                if(currency.isEmpty()){
                    currency = e.getExpenseCurrency();
                }

            }

            System.err.println("Total Expenses for " + category + " Category: " + totalExpense + " " + currency);

        }

    }
    
    /**
     * Displays all expenses recorded in the system with their individual details
     * and calculates the grand total of all expenses.
     */
    public static void viewTotalExpense() {
        
        int counter = 0;
        float totalExpense = 0;
        String currency = "";

        System.out.println("Viewing All Expenses");
        System.out.println("==========");

        for(Expense e : expenses){
            
            counter += 1;
            totalExpense += e.getExpenseAmount();

            System.out.println(counter + ". " + e.getExpenseDateTime().getDateString() + " - " + e.getExpenseAmount() + " " + e.getExpenseCurrency());

            if(currency.isEmpty()){
                currency = e.getExpenseCurrency();
            }

        }

        System.err.println("Total Expenses: " + totalExpense + " " + currency);

    }
    
    /**
     * Displays a percentage breakdown of expenses by category.
     * Shows each category's total expense and its percentage of the overall spending,
     * including uncategorized expenses.
     */
    public static void viewCategoryExpensePercentage() {
        
        ArrayList<Float> totalExpenses = new ArrayList<>();
        String currency = expenses.get(0).getExpenseCurrency();

        for(String category : categories){
            totalExpenses.add(getTotalCategoryExpense(category));
        }

        totalExpenses.add(getTotalCategoryExpense(null));

        for(int i = 0; i < categories.size() + 1; i++){
            
            float percentage = totalExpenses.get(i)/getTotalExpenses() * 100;

            if(i == categories.size()){
                System.out.println((i + 1) + ". Non-Categorized - " + totalExpenses.get(i) + " " + currency + " - " + percentage + "%");
            }else{
                System.out.println((i + 1) + ". " + categories.get(i) + " - " + totalExpenses.get(i) + " " + currency + " - " + percentage + "%");
            }

        }

    }

    //Helper Functions;

    /**
     * Generates a unique ID with the specified prefix and numerical component.
     * 
     * @param prefix the string prefix for the ID (e.g., "BUDG", "EXPN")
     * @param idNum the numerical component of the ID
     * @param numOfDigits the total number of digits for the numerical part (including leading zeros)
     * @return the generated ID string
     */
    public static String generateID(String prefix, int idNum, int numOfDigits){

        String id = "";

        id = id.concat(prefix);

        String numString = Integer.toString(idNum);

        for(int i = numString.length(); i < numOfDigits; i++){
            id = id.concat("0");
        }

        id = id.concat(numString);

        return id; 

    }

    /**
     * Displays all available expense categories to the user.
     */
    public static void displayCategories(){
        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }

    /**
     * Prompts the user to enter date and time information and creates a DateTime object.
     * Collects year, month, day, hour, and minute inputs from the user to construct a complete timestamp.
     * 
     * @param sc the Scanner object used to read user input
     * @param s a descriptive string used in prompts (e.g., "Start", "End", or empty for general date)
     * @return a DateTime object constructed from the user-provided date and time components
     */
    public static DateTime getDate(Scanner sc, String s){

        System.out.println("\nEnter " + s + " Date:");
        System.out.print("Year: ");
        String year = sc.nextLine();
        System.out.print("Month: ");
        String month = sc.nextLine();
        System.out.print("Day: ");
        String day = sc.nextLine();
        System.out.print("Hour: ");
        String hour = sc.nextLine();
        System.out.print("Minute: ");
        String minute = sc.nextLine();
        
        DateTime date = new DateTime(year, month, day, hour, minute);

        return date;

    }

    /**
     * Prompts the user to select an expense category from available options or create a new one.
     * Users can choose from the predefined categories by number, type a new category name to create it,
     * or press ENTER to skip categorization. New categories are automatically added to the available categories list.
     * 
     * @param sc the Scanner object used to read user input
     * @return the selected category name as a String, or null if no category was selected
     */
    public static String getCategory(Scanner sc){

        System.out.print("Choose a category (1-" + categories.size() + "), type a new category, or press ENTER to have no category: ");
        
        String categoryInput = sc.nextLine();
        String category = null;
        
        //this is for custom categories
        if (!categoryInput.isEmpty()) {
            try {
                int categoryChoice = Integer.parseInt(categoryInput);
                if (categoryChoice >= 1 && categoryChoice <= categories.size()) {
                    category = categories.get(categoryChoice - 1);
                }
            } catch (NumberFormatException e) {
                // If not a number, use the input as custom category
                category = categoryInput;
                // Add the custom category to the list
                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }
        }

        return category;

    }

    /**
     * Calculates total expenses for each day based on recorded expenses.
     * Groups expenses by date and sums amounts for each unique date.
     * The first element in the returned list is the total number of days.
     * 
     * @return an ArrayList of Float values where the first element is the number of days,
     *         followed by total expenses for each day
     */
    public static ArrayList<Float> getDailyExpenses(){

        ArrayList<Float> expensePerDay = new ArrayList<>();

        float totalDays = 0;
        float currentTotal = -1;
        String currentDay = "";
        for(Expense e : expenses){
            
            if(!e.getExpenseDateTime().getDateString().equals(currentDay)){
                currentDay = e.getExpenseDateTime().getDateString();
                if(currentTotal != -1){
                    expensePerDay.add(currentTotal);
                }
                currentTotal = 0;
                currentTotal += e.getExpenseAmount();
                totalDays += 1;
            }else{

                currentTotal += e.getExpenseAmount();

            }

        }
        expensePerDay.add(currentTotal);
        expensePerDay.add(0, totalDays);

        return expensePerDay;

    }

    /**
     * Calculates total expenses for each month based on recorded expenses.
     * Groups expenses by month and year, summing amounts for each unique month.
     * The first element in the returned list is the total number of months.
     * 
     * @return an ArrayList of Float values where the first element is the number of months,
     *         followed by total expenses for each month
     */
    public static ArrayList<Float> getMonthlyExpenses(){
        
        ArrayList<Float> expensePerMonth = new ArrayList<>();

        float totalMonths = 0;
        float currentTotal = -1;
        String currentMonth = "";
        String currentYear = "";
        for(Expense e : expenses){
            
            if(!e.getExpenseDateTime().getMonth().equals(currentMonth) || !e.getExpenseDateTime().getYear().equals(currentYear)){
                currentMonth = e.getExpenseDateTime().getMonth();
                currentYear = e.getExpenseDateTime().getYear();
                if(currentTotal != -1){
                    expensePerMonth.add(currentTotal);
                }
                currentTotal = 0;
                currentTotal += e.getExpenseAmount();
                totalMonths += 1;
            }else{

                currentTotal += e.getExpenseAmount();

            }

        }
        expensePerMonth.add(currentTotal);
        expensePerMonth.add(0, totalMonths);

        return expensePerMonth;

    }

    /**
     * Calculates the total expense amount for a specific category.
     * 
     * @param category the category to calculate total for, or null for uncategorized expenses
     * @return the total expense amount for the specified category as a float
     */
    public static float getTotalCategoryExpense(String category) {
        
        float totalExpense = 0;

        if(category == null){

            for(Expense e : expenses){

                if(e.getExpenseCategory() == null){

                    totalExpense += e.getExpenseAmount();

                }

            }

        }else{

            for(Expense e : expenses){

                if(e.getExpenseCategory() != null){

                    if(e.getExpenseCategory().equals(category)){

                        totalExpense += e.getExpenseAmount();
    
                    }

                }

            }

        }

        return totalExpense;

    }

    /**
     * Calculates the total of all expenses recorded in the system.
     * 
     * @return the grand total of all expenses as a float
     */
    public static float getTotalExpenses(){
        
        float totalExpense = 0;

        for(Expense e : expenses){
            
            totalExpense += e.getExpenseAmount();

        }

        return totalExpense;

    }

}

    /*
    
    

    public ArrayList<String> getCategories() {
        return categories;
    }
}*/