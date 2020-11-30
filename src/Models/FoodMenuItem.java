package Models;

public class FoodMenuItem {
    public String foodID;
    public String restaurantID;
    public String name;
    public float price;
    public String type;
    public boolean available;
    public String quantity;

    public FoodMenuItem(){

    }

    public FoodMenuItem(String foodID, String restaurantID, String name, String price, String type, String available)
    {
        this.foodID = foodID;
        this.restaurantID = restaurantID;
        this.name = name;
        this.price = Float.parseFloat(price);
        this.type = type;
        this.available = available.equals("true");
    }
    public String getFoodID(){ return foodID; }
    public String getRestaurantID(){ return restaurantID; }
    public String getName(){ return (name); }
    public String getType(){ return type; }
    public float getPrice(){ return price; }
    public boolean isAvailable(){ return available; }
    public String toString(){
        return foodID+" : "+name+" : "+price+" : "+available;
    }
}
