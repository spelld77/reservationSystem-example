package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConnection;

/**
 * Servlet implementation class DeleteReserveServlet
 */
@WebServlet("/delete")
public class DeleteReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("main.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		DBConnection db = (DBConnection) request.getServletContext().getAttribute("db");
		String user = String.valueOf(session.getAttribute("user"));
		
		Date reserveDate = Date.valueOf(request.getParameter("dateToDelete"));
		int startTime = Integer.parseInt(request.getParameter("timeToDelete"));
		String seatId = request.getParameter("seatToDelete");
			
		boolean result = db.deleteReserve(user, reserveDate, startTime, seatId);
		
		response.sendRedirect("myReservation");
		
		
	}

}
