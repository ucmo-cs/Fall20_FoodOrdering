package Models;

import java.util.ArrayList;
import java.util.Iterator;

public class CartModel {
    private static CartModel instance;
    private User user;
    private int restaurant_id = -1;
    private ArrayList<FoodMenuItem> cart=new ArrayList<>();
    Iterator<FoodMenuItem> iterator=cart.iterator();

    public void setUser(User u) { this.user = u; }
    public void setRestaurant_id(int id) {this.restaurant_id = id; }
    public User getUser() { if(this.user != null) return this.user; else return new User();}
    public int getRestaurant_id() { return this.restaurant_id; }

    public static CartModel getInstance() {
        if(instance==null){
            instance=new CartModel();
        }
        return instance;
    }
<<<<<<< HEAD

    public void appendToCart(FoodMenuItem item) {this.cart.add(item);}

=======
>>>>>>> Josh
    public ArrayList<FoodMenuItem> getCart() { return this.cart; }

    public void showCart() {
        for(FoodMenuItem food : cart)
        {
            System.out.println(String.format("%s %s %s %s %s",
                    food.foodID, food.name, food.price, food.type, food.available));
        }
    }

    public FoodMenuItem getCartItemsByID(int id) {
        FoodMenuItem item = null;
        for (FoodMenuItem food : this.cart)
        {
            if (food.foodID.equals(String.valueOf(id)))
                item = food;
        }
        return item;
    }
<<<<<<< HEAD
=======
    public int getNumberOfItems(){
        return getCart().size();
    }
    public void appendCart(FoodMenuItem item) {this.cart.add(item);}
    public void emptyCart(){
        getCart().clear();
    }
    public void promptUser(){
        if(getNumberOfItems()>1){

        }
    }
>>>>>>> Josh
}
