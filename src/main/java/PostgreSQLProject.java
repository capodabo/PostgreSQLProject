import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLProject {
    public static void main(String args[]) {

        insertRow(14, "Mihaita", "Manole", 42, "Male");

        insertRow(18,"Mustar", 203,14);

        deleteRow(3);
    }

    private static void insertRow(int id, String streetName, int streetNumber, int fk_person) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mytestdb",
                            "postgres", "postgres");
            System.out.println("Database opened successfully");

            String address = "(" + id + ", " + "'"+ streetName + "'" +", " + streetNumber + ", " + fk_person + ")" + ";";
            stmt = c.createStatement();
            String newRow = "INSERT INTO ADDRESS (ID,STREETNAME,STREETNUMBER,FK_PERSON) "
                + "VALUES " + address;
            stmt.executeUpdate(newRow);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully, row inserted in address table");
    }

    private static void insertRow(int id, String firstName, String lastName, int age, String gender) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mytestdb",
                            "postgres", "postgres");
            System.out.println("Database opened successfully");

            String person = "(" + id + ", " + "'"+ firstName + "'" +", " + "'" + lastName + "'" + ", " + age + ", " + "'" + gender + "'" + ")" + ";";
            stmt = c.createStatement();
            String newRow = "INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME,AGE,GENDER) "
                + "VALUES " + person;
            stmt.executeUpdate(newRow);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully, row inserted in person table");
    }

    private static void deleteRow(int streetNumber) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mytestdb",
                            "postgres", "postgres");
            System.out.println("Database opened successfully");

            stmt = c.createStatement();
            ResultSet reslt = stmt.executeQuery( "SELECT * FROM ADDRESS;" );
            while (reslt.next()) {
                int number = reslt.getInt("STREETNUMBER");
                if (number == streetNumber) {
                    stmt = c.createStatement();
                    String sql = "DELETE FROM ADDRESS WHERE STREETNUMBER = " + streetNumber + ";";
                    stmt.executeUpdate(sql);
                    System.out.println("Records updated successfully, row removed from address table");
                }
            }
            reslt.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
