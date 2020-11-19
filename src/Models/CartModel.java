package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartModel {
    private static CartModel intsance=null;

    static {
        intsance=new CartModel();
    }

//    public static CartModel getInstance(){
//        return intsance;
//    }

    private List<FoodMenuItem> cart=new ArrayList<>();
    Iterator<FoodMenuItem> iterator=cart.iterator();

    public void appendCart(FoodMenuItem item) {this.cart.add(item);}
    public void setCart(List<FoodMenuItem> cart) {this.cart=cart;}
    public List<FoodMenuItem> getCart() {return  this.cart;}
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
}
