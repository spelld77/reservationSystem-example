package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import vo.ReservationVO;

/**
 * DBアクセスするためのクラス
 *
 * @author spelld77
 */
public class DBConnection implements AutoCloseable {
	private Connection conn;
	
	/**
	 * DBに接続、トランザクション開始
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void connect() throws SQLException, ClassNotFoundException  {
		final String serverurl = "jdbc:mysql://localhost:3306/reserve";
		final String id = "root";
		final String pw = "root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(serverurl, id, pw);
		// オートコミットOFF
		conn.setAutoCommit(false);
	}
	
	/**
	 * DBから切断処理（自動リリース）
	 * {@link java.lang.AutoCloseable}の実装
	 */
	@Override
	public void close() {

	}
	
	/**
	 * トランザクションコミット
	 * 変更をDBに反映する
	 * @throws SQLException 
	 */
	public void commit() throws SQLException {
		conn.commit();
	}
	
	/**
	 * ロールバック処理
	 * @throws SQLException 
	 */
	public void rollback() throws SQLException {
		conn.rollback();
	}

	/**
	 * @param inputId
	 * @param inputPw
	 * @return 認証可否
	 * @throws SQLException
	 */
	public boolean checkUser(String inputId, String inputPw) throws SQLException {
		
		
		String sql = "SELECT pw FROM user WHERE id = ?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, inputId);
		ResultSet rs = psmt.executeQuery();
		
		
		if(rs.next()) {
			String realPw = rs.getString(1);
			
			//id, pw OK
			if(realPw.equals(inputPw)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * /予約時間テーブルの目録をを求める
	 * @return　予約時間の目録
	 * @throws SQLException
	 */
	public List<Integer> getReserveTimeList() {
		
		ArrayList<Integer> reserveTimeList = new ArrayList<>(); 
		String sql = "SELECT start_time FROM reserve_time";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				reserveTimeList.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reserveTimeList;
	}

	
	/**
	 *　予約されている席数を求める
	 * @param date　予約日付
	 * @param reserve_time　予約時間
	 * @return　予約されている席数
	 * @throws SQLException
	 */
	public int getReservedSeatNumber(Date date, int reserve_time) throws SQLException {
			
		int reservedSeat = 0;
		String sql = "SELECT count(*) FROM reserve WHERE reserve_date = ? AND start_time = ?";
		PreparedStatement psmt;
		ResultSet rs;
		psmt = conn.prepareStatement(sql);
		psmt.setDate(1, date);
		psmt.setInt(2, reserve_time);
		rs = psmt.executeQuery();
		if(rs.next()) {
			reservedSeat = rs.getInt(1);
		}
		
		return reservedSeat;
	}


	/**
	 * 空いている予約時間と座席を求める
	 * @param date
	 * @param reserveTimes 予約時間テーブルの目録
	 * @return
	 */
	public LinkedHashMap<Integer, List<String>> getAllVacantTimeAndSeat(Date date, List<Integer> reserveTimes) {		
		
		LinkedHashMap<Integer, List<String>> vacantTimeSeat = new LinkedHashMap<>();
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = String.join("",
			 "select id, start_time ",
				"from all_seat_time ",
				"where (id, start_time) ", 
					"not in ",
						"(select seat_id, start_time ",
						 "from reserve where reserve_date= ?",
						") ",
					"and ",
						"start_time = ? ",
				"order by id asc"
		);
	
		try{
			psmt = conn.prepareStatement(sql);
			//各時間から席番号を得る
			for(Integer reserveTime : reserveTimes) {
				ArrayList<String> seatNumbers = new ArrayList<String>();
				
				psmt.setDate(1, date);
				psmt.setInt(2, reserveTime);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					seatNumbers.add(rs.getString("id"));
				}
				vacantTimeSeat.put(reserveTime, seatNumbers);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return vacantTimeSeat;
	}

	/**
	 * 新しい予約を追加する
	 * @param reserveDate 予約日
	 * @param reserveTime　予約時間
	 * @param seat　座席
	 * @param userId　予約者
	 * @return
	 */
	public int addReservation(Date reserveDate, int reserveTime, String seat, String userId) {

		int result = 0;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO reserve(reserve_date, start_time, seat_id, user_id) VALUES(?, ?, ?, ?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setDate(1, reserveDate);
			psmt.setInt(2, reserveTime);
			psmt.setString(3, seat);
			psmt.setString(4, userId);
			result = psmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * ユーザの全予約を取得、リターン
	 * @param user　検索ユーザID
	 * @return
	 */
	public List<ReservationVO> getAllReservationByUser(String user) {
		
		List<ReservationVO> reservations = new ArrayList<ReservationVO>();
		
		String sql = String.join(" ",
				"SELECT reserve_date, start_time, seat_id",
				"FROM reserve WHERE user_id = ?",
				"ORDER BY reserve_date, start_time, seat_id"
				);
		
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setString(1, user);
			try(ResultSet rs = psmt.executeQuery()){
				while(rs.next()) {
					ReservationVO reservation = new ReservationVO();
					reservation.setReserveDate(rs.getDate(1));
					reservation.setStartTime(rs.getInt(2));
					reservation.setSeatId(rs.getString(3));
					//listに追加
					reservations.add(reservation);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return reservations;
	}

	public boolean deleteReserve(String user, Date reserveDate, int startTime, String seatId) {
		//削除したrow数
		int cntResult = 0;
		String sql = String.join(" ",
				"DELETE FROM reserve",
				"WHERE user_id = ?",
				"AND",
				"reserve_date = ?",
				"AND",
				"start_time = ?",
				"AND",
				"seat_id = ?"
				);
		
		try(PreparedStatement psmt = conn.prepareStatement(sql)) {
			psmt.setString(1, user);
			psmt.setDate(2, reserveDate);
			psmt.setInt(3, startTime);
			psmt.setString(4, seatId);
			cntResult = psmt.executeUpdate();
			commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cntResult > 0){
			return true;
		}
		
		return false;
	}


	

}
