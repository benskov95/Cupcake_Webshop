package FunctionLayer;

/**
 * The purpose of User is to...
 * @author kasper
 */
public class Customer {

    public Customer(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Customer(String name, String email, int credit) {
        this.name = name;
        this.email = email;
        this.credit = credit;
    }

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String name;
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private int credit;

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
