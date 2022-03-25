package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBConnection;

/**
 * Servlet implementation class AddReserveServlet
 */
@WebServlet("/add")
public class AddReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dateStr = request.getParameter("inputDate");
		LocalDate inputDate = LocalDate.parse(dateStr);

		String dayOfWeekStr;
		dayOfWeekStr = inputDate
						.getDayOfWeek()
						.getDisplayName(TextStyle.NARROW, Locale.JAPAN);
		String dateFullStr = dateStr + "(" + dayOfWeekStr + ")";
		
		// DBから入力した日の予約状況を受ける
		DBConnection db = (DBConnection) request.getServletContext().getAttribute("db");
		Date date = Date.valueOf(inputDate);
		
		//予約時間テーブルの目録
		List<Integer> reserveTimes = db.getReserveTimeList();
		
		// 各時間に空いている全席番号を求める（時間、席番号リスト）
		LinkedHashMap<Integer, List<String>> vacantTimeAndSeat;
		vacantTimeAndSeat = db.getAllVacantTimeAndSeat(date, reserveTimes);
				
		request.setAttribute("vacantTimeAndSeat", vacantTimeAndSeat);
		request.setAttribute("dateFullStr", dateFullStr);
		request.setAttribute("reserveDate", date);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = String.valueOf(request.getSession().getAttribute("user"));		
		Date reserveDate = Date.valueOf(request.getParameter("reserveDate"));
		int reserveTime = Integer.parseInt(request.getParameter("reserveTime"));
		String seat = request.getParameter("seatNo");
				
		//データベースに予約追加
		DBConnection db = (DBConnection) request.getServletContext().getAttribute("db");
		int result = db.addReservation(reserveDate, reserveTime, seat, userId);
		
		response.sendRedirect("main.jsp");
		
	}

}
