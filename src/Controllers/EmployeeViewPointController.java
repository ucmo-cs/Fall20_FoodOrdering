package Controllers;

import Models.*;
import Queries.RestaurantQueries;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sql.rowset.CachedRowSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EmployeeViewPointController {
    @FXML public TableView<Order> pickup_table;
    @FXML public TableView<Order> new_order_table;
    @FXML public TableColumn columnNewOrderID;
    @FXML public TableColumn columnNewOrderItems;
    @FXML public TableColumn columnReadyID;
    @FXML public TableColumn columnReadyName;


    private User user;

    public void setUser(User user) { this.user = user; }
    public void showUser(){ System.out.printf("Logged in as %s", this.user.toString()); }

    @FXML
    void refreshTables() throws Exception {
        System.out.println("refresh");
        pickup_table.getItems().clear();
        new_order_table.getItems().clear();
        showNewOrders();
        showReadyOrders();
    }

    @FXML
    void markComplete() throws Exception {
        int order_id = Integer.parseInt(pickup_table.getSelectionModel().getSelectedItem().getOrder_id());
        System.out.printf("Order %d marked as complete\n", order_id);
        makeOrderComplete(order_id);
        refreshTables();
    }

    @FXML
    void markReady() throws Exception {
        int order_id = Integer.parseInt(new_order_table.getSelectionModel().getSelectedItem().getOrder_id());
        System.out.printf("Order %d marked as ready\n", order_id);
        makeOrderReady(order_id);
        refreshTables();
    }

    public void showNewOrders() throws Exception {
        //System.out.println("\nNEW ORDERS\n");
        String getNewOrdersQuery = RestaurantQueries.getNewOrdersQuery(this.user.getID());

        List<Order> orders = getOrdersAsList(getNewOrdersQuery,
                RestaurantBaseController.buildStaticMenu(Integer.parseInt(this.user.getID())));

        columnNewOrderID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        columnNewOrderItems.setCellValueFactory(new PropertyValueFactory<>("order_items"));
        for(Order o : orders) {
            //System.out.println(o.toString());
            new_order_table.getItems().add(o);
        }
    }

    void showReadyOrders() throws Exception {
        //System.out.println("\nORDERS READY FOR PICKUP\n");
        String getNewOrdersQuery = RestaurantQueries.getReadyOrdersQuery(this.user.getID());

        List<Order> orders = getOrdersAsList(getNewOrdersQuery,
                RestaurantBaseController.buildStaticMenu(Integer.parseInt(this.user.getID())));

        columnReadyID.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        columnReadyName.setCellValueFactory(new PropertyValueFactory<>("order_display_name"));

        for(Order o : orders) {
            //System.out.println(o.toString());
            pickup_table.getItems().add(o);
        }
    }

    static void makeOrderReady(int orderID) throws Exception {
        String makeOrderReadyQuery = RestaurantQueries.makeOrderReadyQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderReadyQuery);
    }

    static void makeOrderComplete(int orderID) throws Exception {
        String makeOrderCompleteQuery = RestaurantQueries.completeOrderQuery(String.valueOf(orderID));
        SQLCommands sqlCommands = new SQLCommands();
        sqlCommands.readDataBase(1, makeOrderCompleteQuery);
    }

    static private List<Order> getOrdersAsList(String query, MenuModel menu) throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        List<Order> ordersAsList = new ArrayList<>();
        CachedRowSet orders = sqlCommands.readDataBase(1, query);
        while(orders.next()) {
            StringBuilder s = new StringBuilder();

            String orderID = orders.getString(1);
            String studentID = orders.getString(2);
            String fname = orders.getString(3);
            String lname = orders.getString(4);
            String date = orders.getString(5);
            String items = orders.getString(6);
            float total = Float.parseFloat(orders.getString(7));
            String restaurant_id = orders.getString(8);
            boolean ready = orders.getBoolean(9);
            boolean complete = orders.getBoolean(10);


            List<String> itemsAsList = Arrays.asList(items.split("\\s*,\\s*"));
            Iterator<String> iterator = itemsAsList.iterator();
            while(iterator.hasNext()) {
                s.append(String.format("%s", menu.getFoodByID(Integer.parseInt(iterator.next())).name));
                if(iterator.hasNext())
                    s.append(", ");
            }

            Order order = new Order(orderID, studentID, fname, lname, date, s.toString(), total, restaurant_id, ready, complete);
            ordersAsList.add(order);
        }
        return ordersAsList;
    }

    static private void showOrderHistory(String query, MenuModel menu) throws Exception {
        SQLCommands sqlCommands = new SQLCommands();
        CachedRowSet orders = sqlCommands.readDataBase(1, query);
        while(orders.next())
        {
            String orderID      =   orders.getString(1);
            String studentID    =   orders.getString(2);
            String fname        =   orders.getString(3);
            String lname        =   orders.getString(4);
            String date         =   orders.getString(5);
            String items        =   orders.getString(6);
            float total        =   Float.parseFloat(orders.getString(7));

            System.out.println("===================================");
            System.out.printf(
                    "Order No: %s\n" +
                    "Order Total: $%.2f\n" +
                    "Name: %s, %s %s\n" +
                    "Order Placed: %s\n" +
                    "Order Items:\n",
                    orderID, total, lname, fname, studentID, date);

            String[] itemsAsList = items.split("\\s*,\\s*");
            for(String item:itemsAsList) {
                System.out.printf("\t%s\n", menu.getFoodByID(Integer.parseInt(item)).name);
            }
        }
    }
}
