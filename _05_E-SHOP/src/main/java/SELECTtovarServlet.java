

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;


/**
 * Servlet implementation class SELECTtovarServlet
 */
public class SELECTtovarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static String databaza = "E-shop";
	public final static String URL = "jdbc:mysql://localhost/" + databaza;
	public final static String username = "root";
	public final static String password = "";
	private Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SELECTtovarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		
    	try {
			super.init();//Treba toto?
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, username, password);
		} catch (Exception e) {
			System.err.println("V init: " + e);
		}
	}
    
	public void destroy() {
		try {con.close();} catch(Exception e) {System.err.println("V destroy: " + e);	
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM Tovar";
			ResultSet rs = stmt.executeQuery(sql);
		
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		
			while (rs.next()) {
				JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
		
				objectBuilder.add("ID", rs.getInt("ID"));
				objectBuilder.add("Značka", rs.getString("Značka"));
				objectBuilder.add("Modelova rada", rs.getString("Modelova rada"));
				objectBuilder.add("Nazov", rs.getString("Nazov"));
				objectBuilder.add("Procesor", rs.getString("Procesor"));
				objectBuilder.add("Velkost operačnej pamäte", rs.getString("Velkost operačnej pamäte"));
				objectBuilder.add("Uhlopriečka displeja", rs.getString("Uhlopriečka displeja"));
		
				arrayBuilder.add(objectBuilder);
			}
		
			JsonArray jsonArray = arrayBuilder.build();
		
			out.print(jsonArray.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
