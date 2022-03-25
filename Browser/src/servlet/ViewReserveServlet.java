package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
 * Servlet implementation class ViewReserveServlet
 */
@WebServlet("/view")
public class ViewReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReserveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateStr = request.getParameter("inputDate");
		LocalDate inputDate = LocalDate.parse(dateStr);

		int year, month, day;
		String dayOfWeekStr;
		year = inputDate.getYear();
		month = inputDate.getMonthValue();
		day = inputDate.getDayOfMonth();
		dayOfWeekStr = inputDate
						.getDayOfWeek()
						.getDisplayName(TextStyle.NARROW, Locale.JAPAN);
		
		
		// DBから入力した日の予約状況を受ける
		DBConnection db = (DBConnection) request.getServletContext().getAttribute("db");
		
		
		//予約時間の目録
		List<Integer> reserveTimes = null;	
		reserveTimes = db.getReserveTimeList();
		
		//特定時間の空き状況文字列
		String vacantStatusStr = "";

		//時間帯の空き状況（時間、空き状況記号）
		LinkedHashMap<Integer, String> vacantStatusMap = new LinkedHashMap<>();
		
		// 予約時間と席数の状況を文字列にして、
		for(int i=0; i < reserveTimes.size(); i++) {
			java.sql.Date date = Date.valueOf(inputDate);
			int reserve_time = reserveTimes.get(i);
			
			//予約されている席数
			int reservedSeat = 0;
			try {
				reservedSeat = db.getReservedSeatNumber(date, reserve_time);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//空の座席数を文字列にする
			if(reservedSeat >= 50) {
				vacantStatusStr = "ー";
			} else if(reservedSeat >= 40) {
				vacantStatusStr = "△";
			} else if(reservedSeat >= 30) {
				vacantStatusStr = "〇";
			} else {
				vacantStatusStr = "◎";
			}			
			vacantStatusMap.put(reserve_time, vacantStatusStr);
		}
				
		request.setAttribute("reserveStatus", vacantStatusMap);
		
		
		//日付String
		String dateFullStr = year + "/" + month + "/" + day + "("+ dayOfWeekStr + ")";
		request.setAttribute("dateFullStr", dateFullStr);	
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
		dispatcher.forward(request, response);

	}

}
