//package Models;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class CartModel {
//    private static CartModel instance;
//    private ArrayList<FoodMenuItem> cart=new ArrayList<>();
//    public List<FoodMenuItem> getInstance() {
//        if(instance==null){
//            instance=new CartModel();
//        }
//        return instance;
//    }
//
//    public void appendCart(FoodMenuItem item) {this.cart.add(item);}
//
//    public void showCart() {
//        for(FoodMenuItem food : cart)
//        {
//            System.out.println(String.format("%s %s %s %s %s",
//                    food.foodID, food.name, food.price, food.type, food.available));
//        }
//    }
//    public FoodMenuItem getCartItemsByID(int id) {
//        FoodMenuItem item = null;
//        for (FoodMenuItem food : this.cart)
//        {
//            if (food.foodID.equals(String.valueOf(id)))
//                item = food;
//        }
//        return item;
//    }
//}
