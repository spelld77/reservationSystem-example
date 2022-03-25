/**
 * date validation
 */



/**
 * 日付に問題無い場合、form.submit
 */
function validate(btnSelected){
	
	let form = document.forms["frm"];
	//遷移先
	let action = btnSelected.getAttribute("data-action");
	form.action = action;
	
	//日付と関連無いボタンの場合
	if(action == 'myReservation' || action == 'logout'){
		form.submit();
		return;
	}
	
	//検索する日
	let dateStr = document.getElementById("inputDate").value;
	let today = moment();
	let inputDate = moment(dateStr);
	
	//過去の場合、アラートメッセージ
	let isPastDate  = inputDate.isBefore(today);
	if(isPastDate){
		alert("過去の予約確認はできません。");	
		return;
	}
	
	//　form-methodがGETの場合
	let method = btnSelected.getAttribute("data-method");
	if(method === 'do'){
		form.method = 'do';
	}
	
	form.submit();

}