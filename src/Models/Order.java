package Models;

public class Order {
    public String order_id;
    public String student_id;
    public String fname;
    public String lname;
    public String order_date;
    public String order_items;
    public float order_total;
    public String restaurant_id;
    public boolean ready;
    public boolean complete;

    public Order() {}

    public Order(String o_id, String s_id, String fname, String lname, String date, String items, float tot,
                 String r_id, boolean ready, boolean complete) {
        this.order_id = o_id;
        this.student_id = s_id;
        this.fname = fname;
        this.lname = lname;
        this.order_date = date;
        this.order_items = items;
        this.order_total = tot;
        this.restaurant_id = r_id;
        this.ready = ready;
        this.complete = complete;
    }

    @Override
    public String toString() {
        return String.format(
                "Order %s, Restaurant %s: %s %s %s, %s\n" +
                        "Total: $%.2f, Ready? %s\n" +
                        "Items: %s\n",
                this.order_id, this.restaurant_id, this.lname, this.fname, this.student_id, this.order_date,
                this.order_total, String.valueOf(this.ready),
                this.order_items);
    }
}
