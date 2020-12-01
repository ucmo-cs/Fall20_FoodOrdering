package Controllers;

import Models.MenuModel;
import Models.SQLCommands;
import Models.User;
import Queries.RestaurantQueries;

import javax.sql.rowset.CachedRowSet;

public class EmployeeViewPointController {
    private User user;

    public void setUser(User user) { this.user = user; }
    public void showUser(){ System.out.printf("Logged in as %s", this.user.toString()); }

    void showNewOrders() throws Exception {
        System.out.println("\nNEW ORDERS\n");
        String getNewOrdersQuery = RestaurantQueries.getNewOrdersQuery(this.user.getID());
        showOrderHistory(getNewOrdersQuery,
                RestaurantBaseController.buildStaticMenu(Integer.parseInt(this.user.getID())));
    }

    void showReadyOrders() throws Exception {
        System.out.println("\nORDERS READY FOR PICKUP\n");
        String getNewOrdersQuery = RestaurantQueries.getReadyOrdersQuery(this.user.getID());
        showOrderHistory(getNewOrdersQuery,
                RestaurantBaseController.buildStaticMenu(Integer.parseInt(this.user.getID())));
    }

    static void makeOrderReady(int orderID) throws Exception {
        String makeOrderReadyQuery = RestaurantQueries.makeOrderReadyQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderReadyQuery);
    }

    static void makeOrderComplete(int orderID) throws Exception {
        String makeOrderCompleteQuery = RestaurantQueries.completeOrderQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderCompleteQuery);
    }

    static private void showOrderHistory(String query, MenuModel menu) throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet orders = sqlCommands.readDataBase(1, query);
        while(orders.next())
        {
            String orderID =    orders.getString(1);
            String studentID =  orders.getString(2);
            String fname =      orders.getString(3);
            String lname =      orders.getString(4);
            String date =       orders.getString(5);
            String items =      orders.getString(6);
            String total =      orders.getString(7);

            System.out.println("===================================");
            System.out.printf(
                    "Order No: %s\n" +
                    "Order Total: $%s\n" +
                    "Name: %s, %s %s\n" +
                    "Order Placed: %s\n" +
                    "Order Items:\n",
                    orderID, total, lname, fname, studentID, date);

            String[] itemsAsList = items.split("\\s*,\\s*");
            for(String item:itemsAsList) {
                System.out.printf("\t%s\n", menu.getFoodByID(Integer.parseInt(item)).name);
            }
        }
    }
}
