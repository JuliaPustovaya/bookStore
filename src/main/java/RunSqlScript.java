import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//https://www.mkyong.com/jdbc/how-to-run-a-mysql-script-using-java/

import com.ibatis.common.jdbc.ScriptRunner;

/**
 * @author Dhinakaran Pragasam
 */
public class RunSqlScript {
	/**
	 * @param args the command line arguments
	 */
	public static void main() throws ClassNotFoundException, SQLException {
		String aSQLScriptFilePath = "/home/ypustova/Desktop/bookStore/src/main/resources/db/02-insert.sql";
		// Create MySql Connection
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/BOOK_STORE", "root", "root");



		Statement stmt = null;
		try {
			// Initialize object for ScripRunner
			ScriptRunner sr = new ScriptRunner(con, false, false);
			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));
			// Exctute script
			sr.runScript(reader);
		}
		catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath
					+ " The error is " + e.getMessage());
		}
	}
}