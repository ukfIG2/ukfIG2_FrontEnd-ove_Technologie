import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Admin_servlet
 */
public class Admin_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static String databaza = "E-shop";
	public final static String URL = "jdbc:mysql://localhost/" + databaza;
	public final static String username = "root";
	public final static String password = "";
	private Connection con;
		
	private String pozicia = "Neprihláseny zákaznik";

    public Admin_servlet() {
        super();
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			if(con==null) {
				out.println("Neni pripojena databaza.");
				System.err.println("Pripojenie k databaze " + databaza + " NIE-je.");
				return;
			} else {System.out.println("Pripojenie k databaze " + databaza + " je.");}
		
			try {
			if(session.getAttribute("JeAdmin").equals("1")) {
				 String operacia = request.getParameter("operacia");

				    if ("ZmenaAdminPrav".equals(operacia)) {
				        ZmenaAdminPrav(out, request, response);
				    }
				head(out);
				header(out, request);
				main(out);
				bottom(out);
				
	            out.close();}
	            else {
					out.println("Nie si Admin. Zmizni.");
					return;
	            }}
			catch (NullPointerException e) {
				out.println("Nie si Admin. Zmizni..");
			}

		} catch (Exception e) {
			System.err.println("Servlet doGet " + e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//nepis nemaz
		doGet(request, response);
	}

	public void head(PrintWriter out) {
		out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>E-shop NOOTEBUKY</title>");
        out.println("    <link rel='stylesheet' href='style.css'>");
        out.println("    <script src='script.js' defer></script>");
        out.println("</head>");
        out.println("<body>");
	}
	
	public void bottom(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
	}
	
	public void header(PrintWriter out, HttpServletRequest request) {
		HttpSession session = request.getSession();
		out.println("<header>");
		out.println("	<div class='pozdrav'>");
		if(session.getAttribute("meno") == null) {
			out.println("		<h1>Ahoj " + pozicia + "</h1>");
		} else {
			pozicia = "Prihlaseny používateľ";
		out.println("		<h1>Ahoj " + pozicia + " " + session.getAttribute("meno") + "</h1>");
		}
		out.println("	</div>");

		if(pozicia.equals("Neprihláseny zákaznik")) {
	    out.println("    <div class='button-container'>");
	    out.println("        <form action='login.html'>");
	    out.println("            <button type='submit'>Prihlás sa</button>");
	    out.println("        </form>");
	    out.println("    </div>");
		} else if(pozicia.equals("Prihlaseny používateľ")) {
		    out.println("    <div class='button-container'>");
		    out.println("		<form method='post' action='Main_servlet'>");
			out.println("			<input type='hidden' name='operacia' value='logout'>");
			out.println("			<input type='submit' value='logout'>");
		    out.println("        </form>");
		    out.println("    </div>");
		}
	    
		    out.println("    <div class='button-container'>");
		    out.println("        <form action='Main_servlet'>");
		    out.println("            <button type='submit'>Naspak na tovar </button>");
		    out.println("        </form>");
		    out.println("    </div>");
			out.println("</header>");   			
	}
	
	private void main(PrintWriter out) {
		try {
			Statement stmt = con.createStatement();
	        String sql = "SELECT * FROM Users";
	        ResultSet rs = stmt.executeQuery(sql);
		out.println("<div class='main'>");
		out.println("	<div class='Tabulka'>");
		out.println("		<h2>Tabulka pouzivatelov</h2>");
		out.println("		<table>");
		out.println("			<tr>");
		out.println("				<th>" + "ID" + "</th>");
		out.println("				<th>" + "Meno" + "</th>");
		out.println("				<th>" + "Priezvisko" + "</th>");
		out.println("				<th>" + "E_mail" + "</th>");
		out.println("				<th>" + "Adresa" + "</th>");
		out.println("				<th>" + "Je Admin?" + "</th>");
		out.println("				<th>" + "Má zľavu?" + "</th>");
		out.println("				<th>" + "Poznámka k použivateľovi" + "</th>");
		out.println("			</tr>");
		while (rs.next()) {
			out.println("			<tr>");
			out.println("				<th>" + rs.getString("idUsers") + "</th>");
			out.println("				<th>" + rs.getString("Meno") + "</th>");
			out.println("				<th>" + rs.getString("Priezvisko") + "</th>");
			out.println("				<th>" + rs.getString("E_mail") + "</th>");
			out.println("				<th>" + rs.getString("Adresa") + "</th>");
			out.println("				<th>" + ((rs.getString("Admin").equals("1")) ? "Je Admin" : "Je zakaznik") + "</th>");
			out.println("				<th>" + (rs.getDouble("Zlava") == 0 ? "Nema zlavu" : rs.getDouble("Zlava") + "%") + "</th>");
			out.println("				<th>" + (rs.getString("Poznamky_k_pouzivatelovi") == null ? "Zatedy nič" : rs.getString("Poznamky_k_pouzivatelovi")) + "</th>");
	        out.println("               <th>");
	        out.println("                    <form action='Admin_servlet' method='post'>");
	        out.println("                        <input type='hidden' name='operacia' value='ZmenaAdminPrav'>");
	        out.println("                        <input type='hidden' name='userId' value='" + rs.getString("idUsers") + "'>");
	        out.println("                        <button type='submit'>Toggle Admin</button>");
	        out.println("                    </form>");
	        out.println("               </th>");
			out.println("			</tr>");
		}
		out.println("		</table>");
		out.println("	</div>");
		//out.println("</main>");
		rs.close();
		stmt.close();
		} catch (Exception e) {
			System.out.println("V doGet 01: " + e);
			}
		
		try {
			Statement stmt = con.createStatement();
	        String sql = "SELECT * FROM Tovar";
	        ResultSet rs = stmt.executeQuery(sql);
		//out.println("<main>");
		out.println("	<div class=Tabulka>");
		out.println("		<h2>Tabulka tovaru</h2>");
		out.println("		<table>");
		out.println("			<tr>");
		out.println("				<th>" + "ID" + "</th>");
		out.println("				<th>" + "Značka" + "</th>");
		out.println("				<th>" + "Modelova rada" + "</th>");
		out.println("				<th>" + "Nazov" + "</th>");
		out.println("				<th>" + "Procesor" + "</th>");
		out.println("				<th>" + "Velkost operačnej pamäte" + "</th>");
		out.println("				<th>" + "Uhlopriečka displeja" + "</th>");
		out.println("				<th>" + "Cesta k fotke" + "</th>");
		out.println("				<th>" + "Počet kusov" + "</th>");
		out.println("				<th>" + "Cena za kus" + "</th>");
		out.println("			</tr>");
		while (rs.next()) {
			out.println("			<tr>");
			out.println("				<th>" + rs.getString("idTovaru") + "</th>");
			out.println("				<th>" + rs.getString("Znacka") + "</th>");
			out.println("				<th>" + rs.getString("Modelova_rada") + "</th>");
			out.println("				<th>" + rs.getString("Nazov") + "</th>");
			out.println("				<th>" + rs.getString("Procesor") + "</th>");
			out.println("				<th>" + rs.getString("Velkost_operacnej_pamate") + "</th>");
			out.println("				<th>" + rs.getString("Uhlopriecka_displeja") + "</th>");
			out.println("				<th>" + rs.getString("Fotka") + "</th>");
			out.println("				<th>" + rs.getInt("Pocet_kusov") + "</th>");
			out.println("				<th>" + rs.getDouble("Cena") + "</th>");
			
			
			out.println("			</tr>");
		}
		out.println("		</table>");
		out.println("	</div>");
		//out.println("</main>");
		
		rs.close();
		stmt.close();
		
		} catch (Exception e) {
			System.out.println("V doGet 01: " + e);
			}
		
		try {
			Statement stmt = con.createStatement();
	        String sql = "SELECT * FROM Zoznam_objednavok INNER JOIN Users ON Users.idUsers=Zoznam_objednavok.idUsers";//v praxi sa to takto nerobí. Som si vedomí.
	        ResultSet rs = stmt.executeQuery(sql);
		//out.println("<main>");
		out.println("	<div class='Tabulka'>");
		out.println("		<h2>Tabulka objednávok</h2>");
		out.println("		<table>");
		out.println("			<tr>");
		out.println("				<th>" + "Číslo objednávky" + "</th>");
		out.println("				<th>" + "id + Meno + Priezvisko zákazníka" + "</th>");
		out.println("				<th>" + "Dátum objednávky" + "</th>");
		out.println("				<th>" + "Suma objednávky(po zlave)" + "</th>");
		out.println("				<th>" + "Stav objednávky" + "</th>");
		out.println("			</tr>");
		while (rs.next()) {
			out.println("			<tr>");
			out.println("				<th>" + rs.getString("Cislo_objednavky") + "</th>");
			out.println("				<th>" + rs.getString("idUsers") + " " + rs.getString("Meno") + " " + rs.getString("Priezvisko") + "</th>");
			out.println("				<th>" + rs.getDate("Datum_objednavky") + "</th>");
			out.println("				<th>" + rs.getString("suma") + "</th>");
			out.println("				<th>" + rs.getString("Stav_objednavky") + "</th>");

			
			out.println("			<tr>");
		    out.println("				<td colspan='7'>");
		    out.println("					<form method='post' action='Objednavky_servlet'>");
		    out.println("						<label for='novyStav_" + rs.getString("Cislo_objednavky") + "'>Nový stav:</label>");
		    out.println("						<select id='novyStav_" + rs.getString("Cislo_objednavky") + "' name='novyStav'>");
		    out.println("							<option value='Spracuvava sa'>Spracuvava sa</option>");
		    out.println("							<option value='Odoslane'>Odoslane</option>");
		    out.println("							<option value='Vybavene'>Vybavene</option>");
		    out.println("						</select>");
		    out.println("						<input type='hidden' name='cisloObjednavky' value='" + rs.getString("Cislo_objednavky") + "'>");
		    out.println("						<input type='hidden' name='operacia' value='zmenStav'>");
		    out.println("						<button type='submit'>Zmeniť stav</button>");
		    out.println("					</form>");
		    out.println("				</td>");
		    out.println("			</tr>");
			out.println("			</tr>");
		}
		out.println("		</table>");
		out.println("	</div>");
		out.println("</div>");
		
		rs.close();
		stmt.close();
		
		} catch (Exception e) {
			System.out.println("V doGet 01: " + e);
			}
		
	}
	
	private void ZmenaAdminPrav(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
	        try {
	            String userId = request.getParameter("userId");
	            if (userId != null) {
	                String updateQuery = "UPDATE Users SET Admin = (CASE WHEN Admin = 0 THEN 1 ELSE 0 END) WHERE idUsers = ?";
	                try (PreparedStatement pstmt = con.prepareStatement(updateQuery)) {
	                    pstmt.setString(1, userId);
	                    
	                    int rowsAffected = pstmt.executeUpdate();

	                    if (rowsAffected > 0) {
	                        response.getWriter().println("Admin status updated successfully.");
	                    } else {
	                        response.getWriter().println("User not found or admin status not updated.");
	                    }
	                }
	            } else {
	                response.getWriter().println("Invalid user ID.");
	            }
	        } catch (Exception e) {
	            try {
					response.getWriter().println("Error updating admin status: " + e.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
	    }
	
	
	
}
