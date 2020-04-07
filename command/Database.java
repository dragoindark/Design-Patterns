package command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Database {
	DatabaseFunctions dbfunc=new DatabaseFunctions();
	Connection conn=dbfunc.connectDB();
	
	public void insert(char op, int operand) throws SQLException {
		Calendar cal=Calendar.getInstance();
		PreparedStatement stmt=null;
		String sql="INSERT INTO commandStore "+"(operator,operand,timestamp)VALUES "+"(?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, String.valueOf(op));
		stmt.setInt(2, operand);
		stmt.setTimestamp(3, (Timestamp) cal.getTime());
		stmt.executeUpdate();
		stmt.close();		
	}
	
	/*public Command load() throws SQLException {
		PreparedStatement stmt=null;
		Calculator calculator = new Calculator();
		String sql="SELECT operator,operand,ts from commandStore ORDER BY ts DESC";
		stmt=conn.prepareStatement(sql);
		ResultSet rs=stmt.executeQuery();
		char c =rs.getString("operator").charAt(0);
		for(int i=1; i<rs.getString("operator").length();i++){  
	        c += rs.getString("operator").charAt(i);  
	        System.out.println("char at "+i+" index is: "+c);  
	} 
	    //Command loadedCommand=new CalculatorCommand(calculator,c,rs.getInt("operand"));
	    return loadedCommand;
		
	}*/

}
