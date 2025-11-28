package com.cbprog3.View;

import com.cbprog3.Controller.DatabaseController;
import com.cbprog3.Controller.ExpenseController;
import com.cbprog3.Controller.UserController;

public class App 
{
    public static void main( String[] args )
    {

        boolean running = true;

        DatabaseController dbc = new DatabaseController();
        UserController uc = new UserController(dbc.loadUser("1"));
        ExpenseController ec = new ExpenseController();

        LoginMenu lm = new LoginMenu(uc);
        MainMenu mm = new MainMenu(uc, dbc);
        lm.setVisible(true);

        System.out.println("Login Menu Opened!");

        while(running){

            if(lm.getLoginStatus() && lm.isVisible()){
                System.out.println("Switching to Main Menu!");
                lm.setVisible(false);
                mm.setUserLabel(uc.getCurrentUser().getFullName());
                mm.reloadTables();
                mm.setVisible(true);
            }

            if(mm.isVisible() && mm.getSwitchStatus()){

                System.out.println("Switching to " + mm.getSwitchDestination() + "!");

                switch(mm.getSwitchDestination()){

                    case "manageUsers": mm.setVisible(false); break;
                    case "manageExpenses": mm.setVisible(false); break;
                    case "manageBudgets": mm.setVisible(false); break;
                    case "viewInsights": mm.setVisible(false); break;
                    
                }

            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
