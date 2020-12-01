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

    static MenuModel buildStaticMenu(int restaurantID) throws Exception
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