$(function(){
	$("#btn_book").click(function(){
		
		document.location.href = "${rootPath}/book/"
		
	})
	
	$("#btn_insert").click(function(){
		
		document.location.href = "${rootPath}/book/insert"
		
	})
	
	
	
	$("#btn_report").click(function(){
		
		if("${userDTO}" == "") {
			document.location.href = "${rootPath}/member/login"
		} else {
			document.location.href = "${rootPath}/report/"
		}
		
	})
	
	$("#btn_report_insert").click(function(){
		
		if("${userDTO}" == "") {
			document.location.href = "${rootPath}/member/login"
		} else {
			document.location.href = "${rootPath}/report/insert"
		}
		
	})
	
	$("#btn_login").click(function(){
		
		document.location.href = "${rootPath}/member/login"
	})
	
	$("#btn_logout").click(function(){
		
		document.location.href = "${rootPath}/member/logout"
	})
	
	
	
	
	$(".list-body").click(function(){
		let id = $(this).attr("data-id")
		
		//alert(id)
		if("${BODY}" == 'BOOK') {
			document.location.href = "${rootPath}/book/view?id=" + id
		}
		if("${BODY}" == 'REPORT') {
			document.location.href = "${rootPath}/report/view?id=" + id
		}
	})
})