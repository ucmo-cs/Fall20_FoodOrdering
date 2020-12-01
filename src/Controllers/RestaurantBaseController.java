package Controllers;

import Models.*;
import Queries.RestaurantQueries;
import Queries.StudentQueries;

import javax.sql.rowset.CachedRowSet;
import java.util.Iterator;
import java.util.List;

class RestaurantBaseController {

    MenuModel menuModel = new MenuModel();
    User user;
    CartModel cart;


    public void setUser(User u) { this.user = u; }

    void showUser(){
        System.out.printf("Logged in as: %s, %s %s\n", this.user.getLname(), this.user.getFname(), this.user.getID());
    }

    void buildMenu(int restaurantID) throws Exception {
        this.menuModel = buildStaticMenu(restaurantID);
    }

    private static MenuModel buildStaticMenu(int restaurantID) throws Exception
    {
        MenuModel menu = new MenuModel();
        String getFoodsQuery = RestaurantQueries.getFoodsByRestaurantIDQuery(String.valueOf(restaurantID));
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet foods = sqlCommands.readDataBase(1, getFoodsQuery);
        while(foods.next()) {
            FoodMenuItem item = new FoodMenuItem(
                    foods.getString(1),
                    foods.getString(2),
                    foods.getString(3),
                    foods.getString(4),
                    foods.getString(5),
                    foods.getString(6));
            menu.appendFood(item);
        }
        return menu;
    }

    static String formatOrderForDB(List<FoodMenuItem> items)
    {
        StringBuilder s= new StringBuilder();
        Iterator<FoodMenuItem> itemsIterator = items.iterator();
        while(itemsIterator.hasNext())
        {
            s.append(itemsIterator.next().foodID);
            if(itemsIterator.hasNext())
                s.append(",");
        }
        return s.toString();
    }
    static double calculateOrderTotal(List<FoodMenuItem> items)
    {
        double subtotal = 0.0;
        for (FoodMenuItem f:items)
        {
            subtotal += f.price;
        }
        return Double.parseDouble(String.format("%.2f", subtotal));
    }

    static void showNewOrders(int restaurantID) throws Exception {
        System.out.println("\nNEW ORDERS\n");
        String getNewOrdersQuery = RestaurantQueries.getNewOrdersQuery(String.valueOf(restaurantID));
        showOrderHistory(getNewOrdersQuery, buildStaticMenu(restaurantID));
    }

    static void showReadyOrders(int restaurantID) throws Exception
    {
        System.out.println("\nORDERS READY FOR PICKUP\n");
        String getNewOrdersQuery = RestaurantQueries.getReadyOrdersQuery(String.valueOf(restaurantID));
        showOrderHistory(getNewOrdersQuery, buildStaticMenu(restaurantID));
    }
    static void makeOrderReady(int orderID) throws Exception
    {
        String makeOrderReadyQuery = RestaurantQueries.makeOrderReadyQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderReadyQuery);
    }

    static void makeOrderComplete(int orderID) throws Exception
    {
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
            for(String item:itemsAsList)
            {
                System.out.printf("\t%s\n", menu.getFoodByID(Integer.parseInt(item)).name);
            }
        }
    }
    static private void debitAmount(double orderTotal, User u) throws Exception {
        String query = StudentQueries.debitDiningDollarsQuery(u.getID(), String.valueOf(orderTotal));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(2, query);
    }
    static private double getUserDiningBalance(User u) throws Exception
    {
        String query = StudentQueries.getBalanceQuery(u.getID());
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet balance = sqlCommands.readDataBase(2, query);
        balance.next();
        return Double.parseDouble(String.format("%.2f", Double.parseDouble(balance.getString(1))));
    }
    static private void submitOrder(String items, String total, String restaurant_id, User u) throws Exception {
        String query = RestaurantQueries.submitNewOrderQuery(u.getID(), items, total, restaurant_id);
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(2, query);
    }
    static public void processOrder(List<FoodMenuItem> items, String restaurant_id, User u) throws Exception {
        double orderTotal = calculateOrderTotal(items);
        double userBalance = getUserDiningBalance(u);
        if(orderTotal > userBalance)
            throw new Exception("Insufficient Funds");
        else
        {
            debitAmount(orderTotal, u);
            submitOrder(formatOrderForDB(items), String.valueOf(orderTotal), restaurant_id, u);
        }

    }
}