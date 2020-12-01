package Queries;

public class LoginQueries {
    public static String StudentUserExistsQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "  COUNT(1)\n" +
                "FROM\n" +
                "  `login`.`user`\n" +
                "WHERE\n" +
                "   `id` = '%s';", id);
    }
    public static String RestaurantUserExistsQuery(String login_name)
    {
        return String.format(
                "SELECT\n" +
                "  COUNT(1)\n" +
                "FROM\n" +
                "  `login`.`restaurant`\n" +
                "WHERE\n" +
                "   `login_name` = '%s';", login_name);
    }
    public static String getStudentPasswordQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "   `password`\n" +
                "FROM\n" +
                "   `login`.`user`\n" +
                "WHERE\n" +
                "`id` = '%s';", id);
    }
    public static String getRestaurantPasswordQuery(String login_name)
    {
        return String.format(
                "SELECT\n" +
                "   `password`\n" +
                "FROM\n" +
                "   `login`.`restaurant`\n" +
                "WHERE\n" +
                "`login_name` = '%s';", login_name);
    }
    public static String getUserInfoQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "   *\n" +
                "FROM\n" +
                "   `student`.`student_information`\n" +
                "WHERE\n" +
                "   `id` = '%s';",id);
    }
    public static String getRestaurantUserInfoQuery(String name)
    {
        return String.format(
                "SELECT\n" +
                "   r.*\n" +
                "FROM\n" +
                "   `restaurant`.`restaurant_info` AS r,\n" +
                "   `login`.`restaurant` AS l\n" +
                "WHERE\n" +
                "   r.id = l.restaurant_id\n" +
                "   AND\n" +
                "   l.login_name = '%s';", name);
    }
}
