package Queries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantQueries {
    public static String getFoodsByRestaurantIDQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "   *\n" +
                "FROM\n" +
                "   `restaurant`.`foods`\n" +
                "WHERE\n" +
                "   `restaurant_id` = '%s';", id);
    }
    public static String submitNewOrderQuery(String studentID, String items, String total, String restaurantID)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timestamp = new Date();
        return String.format(
                "INSERT INTO\n" +
                "   `restaurant`.`history`\n" +
                "VALUES\n" +
                "   (\n" +
                "       NULL,\n" +
                "       '%s',\n" +
                "       '%s',\n" +
                "       '%s',\n" +
                "       '%s',\n" +
                "       '%s',\n" +
                "       0,\n" +
                "       0\n" +
                "   );", studentID, sdf.format(timestamp), items, total, restaurantID);
    }
    public static String getReadyOrdersQuery(String restaurantID)
    {
        return String.format(
                "SELECT\n" +
                "    h.order_id,\n" +
                "    h.student_id,\n" +
                "    s.first_name,\n" +
                "    s.last_name,\n" +
                "    h.order_date,\n" +
                "    h.order_items,\n" +
                "    h.order_total,\n" +
                "    h.restaurant_id,\n" +
                "    h.ready,\n" +
                "    h.picked_up\n" +
                "FROM\n" +
                "    `restaurant`.`history` as h,\n" +
                "    `student`.`student_information` as s\n" +
                "WHERE(\n" +
                "    h.ready = 1\n" +
                "AND\n" +
                "   h.picked_up = 0\n" +
                "AND\n" +
                "    h.restaurant_id = %s\n" +
                "AND\n" +
                "    h.student_id = s.id\n" +
                ");", restaurantID);
    }
    public static String getNewOrdersQuery(String restaurantID)
    {
        return String.format(
                "SELECT\n" +
                "    h.order_id,\n" +
                "    h.student_id,\n" +
                "    s.first_name,\n" +
                "    s.last_name,\n" +
                "    h.order_date,\n" +
                "    h.order_items,\n" +
                "    h.order_total,\n" +
                "    h.restaurant_id,\n" +
                "    h.ready,\n" +
                "    h.picked_up\n" +
                "FROM\n" +
                "    `restaurant`.`history` as h,\n" +
                "    `student`.`student_information` as s\n" +
                "WHERE(\n" +
                "    h.ready = 0\n" +
                "AND\n" +
                "    h.restaurant_id = %s\n" +
                "AND\n" +
                "    h.student_id = s.id\n" +
                ");", restaurantID);
    }
    public static String makeOrderReadyQuery(String order)
    {
        return String.format(
                "UPDATE\n" +
                "   `restaurant`.`history`\n" +
                "SET\n" +
                "   `ready` = '1'\n" +
                "WHERE\n" +
                "   (`order_id` = '%s');", order);
    }
    public static String completeOrderQuery(String order)
    {
        return String.format(
                "UPDATE\n" +
                "   `restaurant`.`history`\n" +
                "SET\n" +
                "   `picked_up` = '1'\n" +
                "WHERE\n" +
                "   (`order_id` = '%s');", order);
    }
}
