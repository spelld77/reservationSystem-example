package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DBConnection;
import vo.ReservationVO;

/**
 * Servlet implementation class MyReservationServlet
 */
@WebServlet("/myReservation")
public class MyReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		DBConnection db = (DBConnection) request.getServletContext().getAttribute("db");
		String user = String.valueOf(session.getAttribute("user"));
		
		//予約目録
		List<ReservationVO> reservations = db.getAllReservationByUser(user);
		
		//曜日のset
		reservations.forEach( e -> {
			LocalDate date = LocalDate.parse(e.getReserveDate().toString());
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			String dayOfWeekStr = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.JAPANESE);
			e.setDayOfWeek(dayOfWeekStr);
		});
		
		request.setAttribute("reservations", reservations);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("my_reserve.jsp");
		dispatcher.forward(request, response);
		
	}

}
