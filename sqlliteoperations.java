package jdbc;

import java.sql.*;

class dbOpsSqllite {
	
	public void printingLine() {
		System.out.println("--------------------------------- SQLite Operations - Your Favorite Movies -------------------------------------------------------------\n");		
	}
	
	public void creatingDB(String dbName) {

		String url = "jdbc:sqlite:" + dbName + ".db";
		try {
			Connection conn = DriverManager.getConnection(url);

            if (conn != null) 
			{  
                System.out.println("Creating Database");
            }
            conn.close();
        }

        catch (SQLException e) {  
        	System.out.println(e.getMessage());
        }

	}
	
	public void creatingTable(String dbName, String tableName) {

		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoftTraining.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(15);
	        
	        String query = "CREATE TABLE IF NOT EXISTS movies(id integer PRIMARY KEY AUTOINCREMENT,hero_name text NOT NULL, movie_name text NOT NULL, UNIQUE(hero_name, movie_name));";
	        statement.execute(query);
	        System.out.println("Table Creation.");
		}

		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertingData(String hero_name, String movie_name) {

		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoftTraining.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(30);
	        String query = "INSERT INTO movies(hero_name, movie_name) VALUES(?,?)";
	        
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setString(1, hero_name);
	        pstmt.setString(2, movie_name);     
		}

		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void retrievingData(String hero_name) {

		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoftTraining.db");
			Statement statement = con.createStatement();
	        statement.setQueryTimeout(10);
	        String query = "SELECT hero_name, movie_name FROM movies";

	        if(hero_name != null) 
			{
	        	query = query + " where hero_name='" + hero_name + "'";
	        }

	        ResultSet rs = statement.executeQuery(query);
	        String format = "%-10s-\t%s\n";

	        System.out.printf(format, "Actor Name", "Movie Acted");

	        while (rs.next()) {
	        	System.out.printf(format,rs.getString(1),rs.getString(2));
            }			
		}

		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}

public class mulesoftTraining {
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			
			Connection con = DriverManager.getConnection("jdbc:sqlite:mulesoftTraining.db");   // Database Connection Section
            System.out.println("SQLite Database Connected successfully");
            dbOpsSqllite dbo = new dbOpsSqllite();
            dbo.printingLine();
            
            
            dbo.creatingDB("Anudeep");  // Database Creation Section
            dbo.printingLine();
            
            
            
            dbo.creatingTable("Anudeep", "movies"); // Table Creation Section
            dbo.printingLine();
            
            
            
            dbo.insertingData("dhanush", "asuran");   // Data Insertion Section
            dbo.insertingData("surya", "soorarai pottru");
            dbo.insertingData("kamal hassan", "vikram");
            dbo.insertingData("sivakarthikeyan", "doctor");
            dbo.insertingData("alluarjun", "aarya");
            dbo.insertingData("rajinikanth", "kabali");
            dbo.insertingData("prabhas", "bahuballi");
            dbo.insertingData("ayushman", "andhadhun");
			
            System.out.println("Data Inserted Into The Table.");
            dbo.printingLine();
            
            
            

            System.out.println("Querying Details....:");   // Data Querying Section
            dbo.retrievingData(null);
            dbo.printingLine();
            System.out.println("Querying Details with a Actor Name:");
            dbo.retrievingData("rajinikanth");
            dbo.printingLine();
            
            con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}