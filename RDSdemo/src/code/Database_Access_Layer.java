package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_Access_Layer {

	public Database_Access_Layer()
	{
		try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://database2.ckdivooppvdf.us-west-2.rds.amazonaws.com:1433;DatabaseName=data;";
            con = DriverManager.getConnection(url, "admin", "ttt0701.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
	public ResultSet search(String context,String start,String end)
    {
		try {
			Statement stmt = con.createStatement();
			String sql = "select "+context+", 日期   "+"from data where data.日期>="+start+" and data.日期<="+end;
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    Connection con;
}
