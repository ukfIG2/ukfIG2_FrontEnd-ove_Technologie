

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static String databaza = "E-shop";
	public final static String URL = "jdbc:mysql://localhost/" + databaza;
	public final static String username = "root";
	public final static String password = "";
	private Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Users WHERE E_mail='" + email + "' AND Password = '" + hashPassword(password) + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                // User exists, create JSON object with user data
                JsonObject userData = Json.createObjectBuilder()
                        .add("idUsers", rs.getInt("idUsers"))
                        .add("Meno", rs.getString("Meno"))
                        .add("Admin", rs.getString("Admin"))
                        .build();

                // Set content type to application/json
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Write JSON object to response
                response.getWriter().write(userData.toString());
            } else {
                // User does not exist, return 401 Unauthorized status
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("OverUsera " + e);
        }
    }
	


	public String hashPassword(String pswd) {
		String password = pswd;
		try {
            // Create a MessageDigest object
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Update the message digest with the bytes of the password
            md.update(password.getBytes());

            // Get the hash value
            byte[] hashedBytes = md.digest();

            // Convert the byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            String hashedPassword = sb.toString();
            //System.out.println("Hashed Password: " + hashedPassword);
            //System.out.println(hashedPassword.length());
            return hashedPassword;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return null;
	
	}
	
	public boolean isValidPassword(String password) {
	    // Define the regular expression for the password criteria
	    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!/\\-_*])(.{8,128})$";

	    // Create a Pattern object
	    Pattern pattern = Pattern.compile(regex);

	    // Create a Matcher object
	    Matcher matcher = pattern.matcher(password);

	    // Print the password
	    System.out.println("Password: " + password);

	    // Check if the password matches the criteria
	    if (matcher.matches()) {
	        System.out.println("Password matches criteria: true");
	    } else {
	        // Print messages for each individual criterion that the password fails to meet
	        if (!password.matches(".*[a-z].*")) {
	            System.err.println("Password must contain at least one lowercase letter.");
	        }
	        if (!password.matches(".*[A-Z].*")) {
	            System.err.println("Password must contain at least one uppercase letter.");
	        }
	        if (!password.matches(".*\\d.*")) {
	            System.err.println("Password must contain at least one digit.");
	        }
	        if (!password.matches(".*[@#$%^&+=!/\\-_*].*")) {
	            System.err.println("Password must contain at least one special character.");
	        }
	        if (!(password.length() >= 8 && password.length() <= 128)) {
	            System.err.println("Password must have a length between 8 and 128 characters.");
	        }

	        System.err.println("Password does not match criteria");
	    }

	    // Return true if the password matches the criteria, otherwise false
	    return matcher.matches();
	}
	
	protected void overUsera(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		try {
			String meno = request.getParameter("login");
			String heslo = request.getParameter("pwd");
			Statement stmt = con.createStatement();
			String sql = "SELECT MAX(idUsers) AS iid, COUNT(idUsers) AS pocet FROM Users WHERE E_mail='"+meno+"'AND Password = '"+ hashPassword(heslo) +"'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
						
			HttpSession session = request.getSession();
			if(rs.getInt("pocet")==1) {//existuje jeden, takze OK
				sql = "SELECT idUsers, Meno, Admin FROM Users WHERE E_mail = '"+meno+"'";
				rs = stmt.executeQuery(sql);
				rs.next();
				session.setAttribute("ID", rs.getInt("idUsers"));
				session.setAttribute("meno", rs.getString("Meno"));
				session.setAttribute("JeAdmin", rs.getString("Admin"));
				System.out.println("IduseraJe " + session.getAttribute("ID"));
				System.out.println("MenoUseraJe " + session.getAttribute("meno"));
				System.out.println("JeUserAdmin? " + session.getAttribute("JeAdmin"));
				pozicia = "Prihlaseny používateľ";
				
				response.sendRedirect(request.getContextPath() + "/Main_servlet");
				
			} else {
				out.println("Autorizacia sa nepodarila. Skontroluj prihlasovacie udaje.");
				session.invalidate(); 		//zmazem sedenie
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("OverUsera " + e);
		}
	}
}
