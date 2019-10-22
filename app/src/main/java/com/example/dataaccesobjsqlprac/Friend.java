package com.example.dataaccesobjsqlprac;

public class Friend {
    int id;
    String firstName;
    String lastName;
    String email;
    public Friend(){  }
    public Friend(int id, String fName,
                  String lName, String email){
        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
    }

    public int getId() { return id; }

    public void setId(int id){this.id = id;}

    public String getFirstName() { return firstName;}

    public void setFirstName(String fName)
    { firstName = fName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lName)
    { lastName = lName; }

    public String getEmail() { return email; }

    public void setEmail(String email)
    { this.email = email; }

}

