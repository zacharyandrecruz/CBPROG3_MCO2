package com.cbprog3.View;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.ExpenseController;
import com.cbprog3.Controller.UserController;

/**
 * The App class serves as the main entry point and application controller
 * for the Expense Tracker system. It manages the application lifecycle and
 * coordinates between different GUI components and controllers.
 * 
 * <p>This class implements a state-based navigation system that handles
 * transitions between different application screens (menus) and maintains
 * the overall application flow.
 * 
 * <p>Key responsibilities include:
 * <ul>
 *   <li>Initializing controllers and database connections</li>
 *   <li>Managing menu visibility and state transitions</li>
 *   <li>Coordinating data flow between different application components</li>
 *   <li>Maintaining the main application loop</li>
 * </ul>
 * 
 * @author Cruz, Zachary Andre A.
 * @author Magdaluyo, Alaine Carlo R.
 * @version 0.6
 */
public class App 
{
    /**
     * The main entry point for the Expense Tracker application.
     * Initializes all controllers, loads the initial user data, and starts
     * the application lifecycle with the login menu.
     * 
     * <p>Application Flow:
     * <ol>
     *   <li>Initialize database connection and controllers</li>
     *   <li>Load user data from database</li>
     *   <li>Display login menu</li>
     *   <li>Manage state transitions between different menus</li>
     *   <li>Handle menu visibility and data refresh operations</li>
     * </ol>
     * 
     * <p>The method implements a continuous loop that monitors the state
     * of various menus and triggers transitions based on user interactions.
     * 
     * @param args command line arguments (not used)
     * 
     */
    public static void main( String[] args )
    {
        boolean running = true;

        // Initialize controllers
        DatabaseController dbc = new DatabaseController();
        UserController uc = new UserController(dbc.loadUser("1"));
        ExpenseController ec = new ExpenseController();

        // Initialize menu components
        LoginMenu lm = new LoginMenu(uc);
        MainMenu mm = new MainMenu(uc, dbc);
        ExpenseMenu em = new ExpenseMenu(uc, dbc, ec);
        BudgetMenu bm = new BudgetMenu(uc, dbc, ec);
        UserMenu um = new UserMenu(uc, dbc);
        InsightMenu im = new InsightMenu(uc, dbc, ec);
        
        // Start with login menu
        lm.setVisible(true);
        System.out.println("Login Menu Opened!");

        // Main application loop
        while(running){
            // Handle login completion
            if(lm.getLoginStatus() && lm.isVisible()){
                System.out.println("Switching to Main Menu!");
                lm.setVisible(false);
                mm.setUserLabel(uc.getCurrentUser().getFullName());
                mm.reloadTables();
                mm.setVisible(true);
            }

            // Handle expense menu completion
            if(em.getStatus()){
                em.setVisible(false);
                em.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            // Handle budget menu completion
            if(bm.getStatus()){
                bm.setVisible(false);
                bm.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            // Handle user menu completion
            if(um.getStatus()){
                um.setVisible(false);
                um.setStatus(false);
                mm.setUserLabel(uc.getCurrentUser().getFullName());
                mm.setVisible(true);
            }

            // Handle insight menu completion
            if(im.getStatus()){
                im.setVisible(false);
                im.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            // Handle navigation from main menu to other menus
            if(mm.isVisible() && mm.getSwitchStatus() && !um.isVisible()){
                System.out.println("Switching to " + mm.getSwitchDestination() + "!");
                
                switch(mm.getSwitchDestination()){
                    case "manageUsers": 
                        um.setVisible(true); 
                        break;
                    case "manageExpenses": 
                        mm.setVisible(false); 
                        em.setVisible(true); 
                        em.refreshTable(); 
                        break;
                    case "manageBudgets": 
                        mm.setVisible(false); 
                        bm.setVisible(true); 
                        bm.refreshTable(); 
                        break;
                    case "viewInsights": 
                        mm.setVisible(false); 
                        im.setVisible(true); 
                        im.refreshTables(); 
                        break;
                }
            }

            // Small delay to prevent excessive CPU usage
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}