/**
 * 
 */

function deleteCheck(delBtn){
	
	let answer = window.confirm("ご予約を取り消します。\nよろしいでしょうか？")
	
	
	if(answer){
		let form = delBtn.parentNode;
		console.log(form);
		form.submit();
	}
	
	
}