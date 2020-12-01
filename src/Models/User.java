package Models;

public class User {
    private String id;
    private String fname;
    private String lname;
    private Boolean employee;

    public User(){

    }
    public User(String id, String fname,String lname,Boolean employee){
        this.id=id;
        this.fname=fname;
        this.lname=lname;
        this.employee=employee;
    }

    public void setID(String id){ this.id=id;}
    public void setFname(String fname){ this.fname=fname;}
    public void setLname(String lname){ this.lname=lname;}
    public void setEmployee(Boolean employee){ this.employee=employee;}

    public String getID(){ return id;}
    public String getFname(){ return fname;}
    public String getLname(){ return lname;}
    public boolean getEmployee(){ return employee;}

    @Override
    public String toString(){
        return String.format("ID: %s, FName: %s, LName: %s, Employee User: %s\n",
                this.id, this.fname, this.lname, String.valueOf(this.employee));
    }
}
