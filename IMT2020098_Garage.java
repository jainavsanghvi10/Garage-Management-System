//Done by Jainav Sanghvi(IMT2020098)

//STEP 1. Import required packages
import java.util.Scanner;
import java.sql.*;

public class IMT2020098_Garage {
   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   // static final String DB_URL = "jdbc:mysql://localhost/companydb";
   static final String DB_URL = "jdbc:mysql://localhost/garagedb?useSSL=false";
   // Database credentials
   static final String USER = "root";// add your user
   static final String PASS = "password";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;

      // STEP 2. Connecting to the Database
      try {
         // STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         // STEP 2c: Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         Scanner scan = new Scanner(System.in); // Create a Scanner object
         clearScreen();
         System.out.println("\nWelcome to Jainav's Garage!\n");
         main_menu(stmt, scan);

         // STEP 5: Clean-up environment
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) { // Handle errors for Class.forName
         e.printStackTrace();
      } finally { // finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println("End of Code");
   } // end main

   static void main_menu(Statement stmt, Scanner scan) {
      System.out.println("Login as a :-");
      System.out.println("1) Customer");
      System.out.println("2) Employee");
      System.out.println("3) Owner");
      System.out.println("0) Exit");

      System.out.print("\n\nPLEASE ENTER YOUR CHOICE: ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();
      switch (choice) {
         case 0:
            System.out.println("Thank You for visiting our Garage! Have a good day.");
            System.exit(0);
         case 1:
            customer_menu(stmt, scan);
            break;
         case 2:
            employee_menu(stmt, scan);
            break;
         case 3:
            owner_menu(stmt, scan);
            break;
         default:
            clearScreen();
            System.out.println("Please enter a valid choice.\n");
            break;
      }
      main_menu(stmt, scan);
   }

   
   static void owner_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select an option:-");
      System.out.println("1) List of customers");
      System.out.println("2) List of employees");
      System.out.println("3) Add a customer");
      System.out.println("4) Remove a customer");
      System.out.println("5) Add an employee");
      System.out.println("6) Delete an employee");
      System.out.println("0) Exit");

      System.out.print("\n\nPLEASE ENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());

      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_customers(stmt, scan);
            break;
         case 2:
            list_of_employees(stmt, scan);
            break;
         case 3:
            add_customer(stmt, scan);
            break;
         case 4:
            delete_customer(stmt, scan);
            break;
         case 5:
            add_employee(stmt, scan);
            break;
         case 6:
            delete_employee(stmt, scan);
            break;
         default:
            System.out.println("Please Enter a Valid Choice!\n");
            break;
      }
      owner_menu(stmt, scan);
   }

   static void list_of_customers(Statement stmt, Scanner scan) {
      String sql = "select * from customer";
      ResultSet rs = executeSqlStmt(stmt, sql);
      clearScreen();
      try {
         System.out.println("List of customers:\n");
         while (rs.next()) {
            String customer_id = rs.getString("customer_id");
            String customer_name = rs.getString("customer_name");

            System.out.println("Customer Id : " + customer_id);
            System.out.println("Customer Name : " + customer_name);
            System.out.println("\n");
         }

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void list_of_employees(Statement stmt, Scanner scan) {
      String sql = "select * from employee";
      ResultSet rs = executeSqlStmt(stmt, sql);
      clearScreen();
      try {
         System.out.println("List of employees:\n");
         while (rs.next()) {
            String emp_id = rs.getString("emp_id");
            String emp_name = rs.getString("emp_name");

            System.out.println("Employee Id : " + emp_id);
            System.out.println("Employee Name: " + emp_name);
            System.out.println("\n");
         }

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   static void add_customer(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter customer id : ");
         String customer_id = scan.nextLine();
         System.out.print("Enter customer name : ");
         String customer_name = scan.nextLine();
         clearScreen();
         String sql = String.format("INSERT INTO customer VALUES('%s', '%s', NULL)", customer_id, customer_name);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Customer has been successfully added!!\n");
         else
            System.out.println("Something went wrong!  Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void add_employee(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter Employee ID : ");
         String emp_id = scan.nextLine();
         System.out.print("Enter Employee Name : ");
         String emp_name = scan.nextLine();

         clearScreen();
         String sql = String.format("INSERT INTO employee VALUES('%s', '%s')", emp_id, emp_name);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Employee has been successfully added!!\n");
         else
            System.out.println("Something went wrong!  Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void delete_customer(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter customer id : ");
         String customer_id = scan.nextLine();
         clearScreen();
         String sql = String.format("DELETE FROM customer where customer_id = '%s'", customer_id);
         int result = updateSqlStmt(stmt, sql);
         if (result != 0)
            System.out.println("Customer has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong! Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void delete_employee(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter Employee id : ");
         String emp_id = scan.nextLine();
         clearScreen();
         String sql = String.format("DELETE FROM employee where emp_id = '%s'", emp_id);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Employee has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong! Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
  
   
   static void customer_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select an option:-");
      System.out.println("1) List of available cars");
      System.out.println("0) Exit");

      System.out.print("\n\nPLEASE ENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();
      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_cars(stmt, scan, true);
            break;
         default:
         clearScreen();
            System.out.println("Please Enter a Valid Choice!\n");
            break;
      }
      customer_menu(stmt, scan);
   }

   static boolean list_of_cars(Statement stmt, Scanner scan, boolean checkAvailable) {

      String sql = "select * from car";
      ResultSet rs = executeSqlStmt(stmt, sql);
      boolean nocars = true;
      
      try {
         System.out.println("List of available cars:\n");
         while (rs.next()) {
            String Engine_SerialNumber = rs.getString("Engine_SerialNumber");
            String brand = rs.getString("brand");
            String model = rs.getString("model");
            Float price = rs.getFloat("price");
            String buyer = rs.getString("buyer");

            if (checkAvailable) {
               if (buyer == null) {
                  System.out.println("Engine_SerialNumber : " + Engine_SerialNumber);
                  System.out.println("Brand : " + brand);
                  System.out.println("Model : " + model);
                  System.out.println("Price : " + price);
                  System.out.println("");
                  nocars = false;
               }
            } else {
               System.out.println("Engine_SerialNumber : " + Engine_SerialNumber);
               System.out.println("Brand : " + brand);
               System.out.println("Model : " + model);
               System.out.println("Price : " + price);
               System.out.println("Car Buyer : " + buyer);
               System.out.println("");
               nocars = false;
            }
         }

         if (nocars)
            System.out.println("Sorry, no cars are available!\n");

         rs.close();
      } catch (SQLException e) {
         // e.printStackTrace();
      }
      return nocars;
   }

   
   static void employee_menu(Statement stmt, Scanner scan) {
      System.out.println("Please select an option:-");
      System.out.println("1) List of all cars");
      System.out.println("2) List of cars presently in store");
      System.out.println("3) Sell a car");
      System.out.println("4) Return a car");
      System.out.println("5) Add a car");
      System.out.println("6) Delete a car");
      System.out.println("0) Exit");
      System.out.print("\n\nENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();
      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_cars(stmt, scan, false);
            break;
         case 2:
            list_of_cars(stmt, scan, true);
            break;
         case 3:
            sell_cars(stmt, scan);
            break;
         case 4:
            return_cars(stmt, scan);
            break;
         case 5:
            add_cars(stmt, scan);
            break;
         case 6:
            delete_cars(stmt, scan);
            break;
         default:
         clearScreen();
            System.out.println("Please Enter a Valid Choice!\n");
            break;
      }
      employee_menu(stmt, scan);
   }

   static void sell_cars(Statement stmt, Scanner scan) {
      try {
         boolean noMedicines = list_of_cars(stmt, scan, true);
         if (!noMedicines) {
            System.out.print("\nEnter Engine_SerialNumber of car : ");
            String Engine_SerialNumber = scan.nextLine();

            System.out.print("Enter customer id : ");
            String customer_id = scan.nextLine();
            clearScreen();

            String sql = String.format("UPDATE car SET buyer = '%s' WHERE Engine_SerialNumber = '%s'", customer_id,
                  Engine_SerialNumber);
            int result = updateSqlStmt(stmt, sql);

            if (result != 0)
               System.out.println("Buyer has been succesfully updated\n");
            else
               System.out.println("Something went wrong!  Please try again. Please try again\n");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void return_cars(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter Engine_SerialNumber : ");
         String Engine_SerialNumber = scan.nextLine();
         clearScreen();
         String sql = String.format("UPDATE car SET buyer = NULL WHERE Engine_SerialNumber = '%s'",
               Engine_SerialNumber);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Car has been returned!!\n");
         else
            System.out.println("Something went wrong! Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void add_cars(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter Engine_SerialNumber : ");
         String Engine_SerialNumber = scan.nextLine();
         System.out.print("\nEnter car brand : ");
         String brand = scan.nextLine();
         System.out.print("\nEnter car model : ");
         String model = scan.nextLine();
         System.out.print("\nEnter car price : ");
         Integer price = Integer.parseInt(scan.nextLine());
         clearScreen();
         String sql = String.format("INSERT INTO car VALUES('%s', '%s', '%s', '%d', NULL);", Engine_SerialNumber, brand,
               model,
               price);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Car has been successfully added!!\n");
         else
            System.out.println("Something went wrong! Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static void delete_cars(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter Engine_SerialNumber : ");
         String Engine_SerialNumber = scan.nextLine();
         clearScreen();
         String sql = String.format("DELETE FROM car where Engine_SerialNumber = '%s'", Engine_SerialNumber);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Car has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong! Please try again.\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   static ResultSet executeSqlStmt(Statement stmt, String sql) {
      try {
         ResultSet rs = stmt.executeQuery(sql);
         return rs;
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   static int updateSqlStmt(Statement stmt, String sql) {
      try {
         int rs = stmt.executeUpdate(sql);
         return rs;
      } catch (SQLException se) {
         // se.printStackTrace();
      } catch (Exception e) {
         // e.printStackTrace();
      }
      return 0;
   }

   static void clearScreen() {
      System.out.println("\033[H\033[J");
      System.out.flush();
   }
} // end class

// Note : By default autocommit is on. you can set to false using
// con.setAutoCommit(false)
