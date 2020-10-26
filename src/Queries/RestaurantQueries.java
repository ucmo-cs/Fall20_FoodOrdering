package Queries;

public class RestaurantQueries {
    public static String getFoodsByID(String id)
    {
        return String.format(
                "SELECT\n" +
                "   *\n" +
                "FROM\n" +
                "   restaurant.foods\n" +
                "WHERE\n" +
                "   restaurant_id = '%s';", id);
    }
}
