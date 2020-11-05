import Models.FoodMenuItem;
import Models.MenuModel;
import Queries.RestaurantQueries;

import javax.sql.rowset.CachedRowSet;

class RestaurantBaseController {

    MenuModel menuModel = new MenuModel();

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
    double calculateOrderTotal(int[] items)
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
}
