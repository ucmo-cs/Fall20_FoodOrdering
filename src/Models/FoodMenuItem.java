package Models;

public class FoodMenuItem {
    public String foodID;
    public String restaurantID;
    public String name;
    public float price;
    public String type;
    public boolean available;


    public FoodMenuItem(String foodID, String restaurantID, String name, String price, String type, String available)
    {
        this.foodID = foodID;
        this.restaurantID = restaurantID;
        this.name = name;
        this.price = Float.parseFloat(price);
        this.type = type;
        this.available = available.equals("1");
    }
}
