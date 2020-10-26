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
}
