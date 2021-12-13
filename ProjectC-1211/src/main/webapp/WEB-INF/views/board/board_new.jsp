<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- DatePicker css 부분 -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<!-- TimePicker css 부분 -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
</head>
<body>
<h3>공연정보 글쓰기</h3>
<form method="post" enctype="multipart/form-data" action="insert.bo">
	<table>
		<tr>
			<th colspan="2">제목</th>
			<td class='left middle' colspan="3"><input type="text" name='eventnm' title='제목' class='chk' /></td>
			<th>공연유형</th>
			<td colspan="2"><input type="text"  name="eventco"/></td>
		</tr>
		<tr>
			<th>시작 날짜</th>
			<td><input type="text" size="8" name="eventstartdate" readonly="readonly"/></td>
			<th>종료 날짜</th>
			<td><input type="text" name="eventenddate" size="8" readonly="readonly"/></td>
			<th>시작 시간</th>
			<td><input type="text" size="5" name="eventstarttime" readonly="readonly"/></td>
			<th>종료 시간</th>
			<td><input type="text" size="5" name="eventendtime" readonly="readonly"/></td>
		</tr>
		<tr>
			<th >주소</th>
			<td class='addr' colspan="7">			
					<input type="text" name='rdnmadr' size="35" readonly="readonly"/>
					<a class='btn-fill-s' onclick='daum_post()'>주소 찾기</a>
			</td>
		</tr>
		<tr>
			<th>주관 기관</th>
			<td colspan="2"><input type="text" name="mnnst"/></td>
			<th>주최 기관</th>
			<td colspan="2"><input type="text" name="auspcinstt"/></td>
			<th>후원 기관</th>
			<td colspan="2"><input type="text" name="suprtinstt"/></td>
		</tr>
		<tr>
			<th>좌석수</th>
			<td colspan="2"><input type="text" name="seatnumber"/></td>
			<th>입장연령</th>
			<td colspan="2"><input type="text" name="entncage"/></td>
			<th>주차장</th>
			<td colspan="2">
				<label><input type="radio" name='prkplceyn' value='Y' checked/>Y</label>
				<label><input type="radio" name='prkplceyn' value='N'  />N</label>
			</td>
		</tr>
		<tr>
			<th>유/무료</th>
			<td colspan="2">
				<label><input type="radio" name='chrgeinfo' value='유료' checked/>유료</label>
				<label><input type="radio" name='chrgeinfo' value='무료'  />무료</label>
			</td>
			<th>입장료</th>
			<td colspan="2"><input type="text" name="vo.admfee" value=${vo.admfee }/></td>
			<th>온/오프</th>
			<td colspan="2">
				<label><input type="radio" name='on_offline' value='n' checked/>온라인</label>
				<label><input type="radio" name='on_offline' value='f'  />오프라인</label>
				<label><input type="radio" name='on_offline' value='d'  />둘다</label>
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td colspan="2"><input type="text" name="phonenumber"/></td>
			<th>할인정보</th>
			<td colspan="2"><input type="text" name="dscntinfo"/></td>
			<th>홈페이지</th>
			<td colspan="2"><input type="text" name="homepageurl"/></td>
		</tr>
		<tr>
			<th colspan="3">유의사항</th>
			<td colspan="5"><input type="text" name="atpn"/></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class='left middle' colspan="7">
				<label>
					<input type="file" name='file' id='attach-file' />
					<a><img src='imgs/select.png' class='file-img' /></a>
				</label>
				<span id='file-name'></span>
				<!-- 이미지 파일인 경우 미리보기 적용 -->
				<span id="preview"></span>
				<a id='delete-file'><i class='font-img fas fa-minus-circle'></i></a>
			</td>
		</tr>
	</table>

</form>
<div class='btnSet'>
	<a class='btn-fill' onclick ="go_save()">저장</a>
<!-- 	<a class='btn-fill' onclick = ' if ( emptyCheck() ) $("form").submit() '>저장</a> -->
	<a class='btn-empty' href='list.bo' onclick="go_board_list()">취소</a>
</div>

<!-- 다음 주소 검색 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- DatePicker 사용하기 위한 js -->
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<!-- TimePicker 사용하기 위한 js -->
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<script type="text/javascript" src='js/file_check.js?v<%=new Date().getTime()%>'></script>
<script type="text/javascript">
function go_board_list(){
	if(confirm("글쓰기를 취소하시겠습니까?")){
		location.href="list.bo";
		return true;
	}else{
		return false;
	}
}

function go_save(){
	$("form").submit();
	if(alert("글이 등록되었습니다.")){
		location.href="insert.bo";
		return true;
	}else{
		return false;
	}
}




 $( function() {
    $('[name=eventstartdate]').datepicker({
    	dayNamesMin : ['일','월','화','수','목','금','토']
    ,	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    ,	changeMonth : true
    ,	changeYear : true
    ,	dateFormat : 'yy-mm-dd'
    , 	showMonthAfterYear : true
//    ,	beforeShowDay : after		/* after 함수를 호출 */
    });
} ); 
 
 $( function() {
	    $('[name=eventenddate]').datepicker({
	    	dayNamesMin : ['일','월','화','수','목','금','토']
	    ,	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
	    ,	changeMonth : true
	    ,	changeYear : true
	    ,	dateFormat : 'yy-mm-dd'
	    , 	showMonthAfterYear : true
//	    ,	beforeShowDay : after		/* after 함수를 호출 */
	    });
	} ); 
 
 $('[name=eventstarttime]').timepicker({
	 timeFormat: 'h:mm p',
	    interval: 30,
	    minTime: '00',
	    maxTime: '11:30pm',
	    startTime: '09:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});

 $('[name=eventendtime]').timepicker({
	 timeFormat: 'h:mm p',
	    interval: 30,
	    minTime: '00',
	    maxTime: '11:30pm',
	    startTime: '13:00',
	    dynamic: false,
	    dropdown: true,
	    scrollbar: true
	});
 
function daum_post() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 조회된 우편번호를 입력하기 위한 선언
            // name 이 post 인 태그의 val(값)을 받아온 변수 (data) 내 zonecode 값을 담음
            //$('[name=post]').val( data.zonecode);
            
            // 지번 주소 : J  도로명 주소 : R
            var addr =data.userSelectedType == 'J' ? data.jibunAddress : data.roadAddress;
            
            // 건물명이 있을 경우 추가
            if ( data.buildingName != '') addr += ' ('+ data.buildingName + ')'; 
            	$('[name=rdnmadr]').eq(0).val( addr );
        }
    }).open();
}


</script>

</body>
</html>
