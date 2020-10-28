package Queries;

public class StudentQueries {
    public static String debitDiningDollarsQuery(String id, String amount)
    {
        return String.format(
                "UPDATE\n" +
                "   `student`.`dining_dollar_balances`\n" +
                "SET\n" +
                "   `balance` = `balance` - '%s'\n" +
                "WHERE\n" +
                "   (`student_id` = '%s');", amount, id);
    }

    public static String creditDiningDollarsQuery(String id, String amount)
    {
        return String.format(
                "UPDATE\n" +
                "   `student`.`dining_dollar_balances`\n" +
                "SET\n" +
                "   `balance` = `balance` + '%s'\n" +
                "WHERE\n" +
                "   (`student_id` = '%s');", amount, id);
    }
    public static String getBalanceQuery(String id)
    {
        return String.format(
                "SELECT\n" +
                "   `balance`\n" +
                "FROM\n" +
                "   `student`.`dining_dollar_balances`\n" +
                "WHERE\n" +
                "   `student_id` = %s;", id);
    }
}
