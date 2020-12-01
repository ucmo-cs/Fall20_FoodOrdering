package Controllers;

import Models.User;

public class EmployeeViewPointController {
    User user;

    public void setUser(User user) { this.user = user; }
    public void showUser(){
        System.out.printf("Logged in as %s %s %s\n", this.user.getID(), this.user.getLname(), this.user.getFname());
    }
}
