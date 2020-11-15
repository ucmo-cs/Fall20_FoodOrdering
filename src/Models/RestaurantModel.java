package Models;

import java.util.ArrayList;
import java.util.List;

public class RestaurantModel {
    private List<FoodMenuItem> foods = new ArrayList<>();

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

}
