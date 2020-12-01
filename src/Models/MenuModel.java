package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuModel {
    private List<FoodMenuItem> foods = new ArrayList<>();
    Iterator<FoodMenuItem> iterator = foods.iterator();

    public void appendFood(FoodMenuItem item)
    {
        this.foods.add(item);
    }
    public void setFoods(List<FoodMenuItem> foods) {
        this.foods = foods;
    }
    public List<FoodMenuItem> getFoods()
    {
        return this.foods;
    }

    public void showFoods()
    {
        for(FoodMenuItem food : foods)
        {
            System.out.println(String.format("%s %s %s %s %s",
                    food.foodID, food.name, food.price, food.type, food.available));
        }
    }

    public FoodMenuItem getFoodByID(int id)
    {
        FoodMenuItem item = null;
        for (FoodMenuItem food : this.foods)
        {
            if (food.foodID.equals(String.valueOf(id)))
                item = food;
        }
        return item;
    }
}
