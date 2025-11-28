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
        ExpenseMenu em = new ExpenseMenu(uc, dbc, ec);
        BudgetMenu bm = new BudgetMenu(uc, dbc, ec);
        UserMenu um = new UserMenu(uc, dbc);
        InsightMenu im = new InsightMenu(uc, dbc, ec);
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

            if(em.getStatus()){
                em.setVisible(false);
                em.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            if(bm.getStatus()){
                bm.setVisible(false);
                bm.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            if(um.getStatus()){
                um.setVisible(false);
                um.setStatus(false);
                mm.setUserLabel(uc.getCurrentUser().getFullName());
                mm.setVisible(true);
            }

            if(im.getStatus()){
                im.setVisible(false);
                im.setStatus(false);
                mm.reloadTables();
                mm.setVisible(true);
            }

            if(mm.isVisible() && mm.getSwitchStatus() && !um.isVisible()){

                System.out.println("Switching to " + mm.getSwitchDestination() + "!");

                switch(mm.getSwitchDestination()){

                    case "manageUsers": um.setVisible(true); break;
                    case "manageExpenses": mm.setVisible(false); em.setVisible(true); em.refreshTable(); break;
                    case "manageBudgets": mm.setVisible(false); bm.setVisible(true); bm.refreshTable(); break;
                    case "viewInsights": mm.setVisible(false); im.setVisible(true); im.refreshTables(); break;
                    
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
