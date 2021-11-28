<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css"> 
table tr td {text-align: left;}
.addr input(:last-child) {margin-bottom: 5px;}
.addr input[name=addr] {width: calc(100% - 25px)}
.valid, .invalid { font-size : 13px; font-weight: bold; font-style: italic;}
.valid { color : #142850;}
.invalid { color: red; }
</style>
</head>
<!-- 저장 후 member_detail로 넘어가기 -->
<body>
<h3>회원 정보 변경</h3>
	<form name="input" action="detail"  method="post">
		<table class='w-pct40'>
			<tr>
				<th class='w-px120'>성명</th>
				<td >${loginInfo.name }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${loginInfo.email}</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td><input type="text" name="nickname"></td>
			</tr>
			
			<tr>
				<th>회원유형</th>
				<td>${loginInfo.type }</td>
			</tr>
			
			<tr>
				<th>가입날짜</th>
				<td>${loginInfo.joindate }</td>
			</tr>
			<tr>
				<th>*주소</th>
				<td class='addr'><a class='btn-fill-s' onclick='daum_post()'>우편번호 찾기</a>
					<input type="text" name='post' class = 'w-px60' /> <br/>				
					<input type="text" name='addr' /> <br/>				
					<input type="text" name='addr' />				
				</td>
			</tr>
		</table>
	</form>
	
	<div class='btnSet'>
		<a class='btn-fill' onclick = ' if ( emptyCheck() ) $("form").submit() '>수정 완료</a>
		<a class='btn-empty' href='<c:url value="detail.my" />'>취소</a>		
	</div>
	
	<!-- 다음 주소 검색 API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<!-- DatePicker 사용하기 위한 js -->
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/join-check.js"></script> 
	<script type="text/javascript">
	
	function go_modify(){
		if(confirm("회원 정보를 수정을 완료하시겠습니까?")){
			location.href="member_detail";
			return true;
		}else{
			return false;
		}
	}
	
	function daum_post() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 조회된 우편번호를 입력하기 위한 선언
	            // name 이 post 인 태그의 val(값)을 받아온 변수 (data) 내 zonecode 값을 담음
	            $('[name=post]').val( data.zonecode);
	            
	            // 지번 주소 : J  도로명 주소 : R
	            var addr =data.userSelectedType == 'J' ? data.jibunAddress : data.roadAddress;
	            
	            // 건물명이 있을 경우 추가
	            if ( data.buildingName != '') addr += ' ('+ data.buildingName + ')'; 
	            	$('[name=addr]').eq(0).val( addr );
	        }
	    }).open();
	}
	
	
	
	</script>

	
</body>
</html>