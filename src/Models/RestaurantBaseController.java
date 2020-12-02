package Models;

import Models.FoodMenuItem;
import Models.MenuModel;
import Models.User;
import Models.SQLCommands;
import Queries.RestaurantQueries;
import Queries.StudentQueries;

import javax.sql.rowset.CachedRowSet;
import java.util.Arrays;
import java.util.List;

class RestaurantBaseController {

    MenuModel menuModel = new MenuModel();
    User user;

    public void setUser(User u) { this.user = u; }

    void showUser()
    {
        System.out.printf("Logged in as: %s, %s %s\n", this.user.getLname(), this.user.getFname(), this.user.getID());
    }

    void buildMenu(int restaurantID) throws Exception {
        String getFoodsQuery = RestaurantQueries.getFoodsByRestaurantIDQuery(String.valueOf(restaurantID));
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet foods = sqlCommands.readDataBase(1, getFoodsQuery);
        while(foods.next())
        {
            FoodMenuItem item = new FoodMenuItem(
                    foods.getString(1),
                    foods.getString(2),
                    foods.getString(3),
                    foods.getString(4),
                    foods.getString(5),
                    foods.getString(6));
            this.menuModel.appendFood(item);
        }
    }

    void createNewOrder(int[] items, int restaurant_id) throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        double subtotal = calculateOrderSubtotal(items);
        String submitOrderQuery = RestaurantQueries.submitNewOrderQuery(
                this.user.getID(),
                formatOrderForDB(items),
                String.valueOf(subtotal),
                String.valueOf(restaurant_id));
        System.out.println(submitOrderQuery);

        // do command
        sqlCommands.readDataBase(1, submitOrderQuery);

        // debit account
        String debitAccountQuery = StudentQueries.debitDiningDollarsQuery(this.user.getID(), String.valueOf(subtotal));
        System.out.println(debitAccountQuery);
        //sqlCommands.readDataBase(2, debitAccountQuery);
    }

    String formatOrderForDB(int[] items)
    {
        StringBuilder s= new StringBuilder();
        for(int i=0; i<items.length; i++)
        {
            s.append(items[i]);
            if(i+1 < items.length)
                s.append(",");
        }
        return s.toString();
    }
    double calculateOrderSubtotal(int[] items)
    {
        double subtotal = 0.0;
        for (int i:items)
        {
            FoodMenuItem item = this.menuModel.getFoodByID(i);
            if(item != null)
                subtotal += item.price;
        }
        return Double.parseDouble(String.format("%.2f", subtotal));
    }

    void showNewOrders(int restaurantID) throws Exception {
        System.out.println("\nNEW ORDERS\n");
        String getNewOrdersQuery = RestaurantQueries.getNewOrdersQuery(String.valueOf(restaurantID));
        showOrderHistory(getNewOrdersQuery);
    }

    void showReadyOrders(int restaurantID) throws Exception
    {
        System.out.println("\nORDERS READY FOR PICKUP\n");
        String getNewOrdersQuery = RestaurantQueries.getReadyOrdersQuery(String.valueOf(restaurantID));
        showOrderHistory(getNewOrdersQuery);
    }
    void makeOrderReady(int orderID) throws Exception
    {
        String makeOrderReadyQuery = RestaurantQueries.makeOrderReadyQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderReadyQuery);
    }

    void makeOrderComplete(int orderID) throws Exception
    {
        String makeOrderCompleteQuery = RestaurantQueries.completeOrderQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderCompleteQuery);
    }

    private void showOrderHistory(String query) throws Exception {
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
            for(String item:itemsAsList)
            {
                System.out.printf("\t%s\n", this.menuModel.getFoodByID(Integer.parseInt(item)).name);
            }
        }
    }
}
