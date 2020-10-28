package Queries;

public class LoginQueries {
    public static String IDExistsQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "  COUNT(1)\n" +
                "FROM\n" +
                "  `login`.`user`\n" +
                "WHERE\n" +
                "   `id` = '%s';", id);
    }
    public static String getPasswordQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "   `password`\n" +
                "FROM\n" +
                "   `login`.`user`\n" +
                "WHERE\n" +
                "`id` = '%s';", id);
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
}
