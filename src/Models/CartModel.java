package Models;

import java.util.ArrayList;
import java.util.Iterator;

public class CartModel {
    private static CartModel instance;
    private ArrayList<FoodMenuItem> cart=new ArrayList<>();
    Iterator<FoodMenuItem> iterator=cart.iterator();

    public static CartModel getInstance() {
        if(instance==null){
            instance=new CartModel();
        }
        return instance;
    }
    public void appendCart(FoodMenuItem item) {this.cart.add(item);}
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
    public int getNumberOfItems(){
        return getCart().size();
    }
    public void emptyCart(){
        getCart().clear();
    }
    public void promptUser(){
        if(getNumberOfItems()>1){

        }
    }
}
