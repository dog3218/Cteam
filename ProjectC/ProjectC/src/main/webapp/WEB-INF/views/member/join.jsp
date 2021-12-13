<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<!-- DatePicker css 부분 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">

</head>
<body>
	<h3>회원가입</h3>
	<p class='w-pct40' style = 'margin:0 auto; text-align: right; padding-bottom: 10px font-size: 12px;'>* 은 필수 입력 항목입니다.</p>	
	<form action="join"  method="post">
		<table class='w-pct40'>
			<tr>
				<th class='w-px120'>*성명</th>
				<td><input type="text" name='name' /></td>
			</tr>
			<tr>
				<th>*이메일</th>
				<td>
					<input type="text" name='email' class='chk' /><br/>		
					<a class='btn-fill-s' id='btn-email'>이메일 중복확인</a>	
					<div class='valid'>이메일을 입력하세요</div>	 
				</td>
			</tr>
			<tr>
				<th>*비밀번호</th>
				<td>
					<input type="password" name='pw' class='chk' /><br/>
					<div class='valid'>비밀번호를 입력하세요(영문 대/소문자, 숫자를 모두 포함)</div>
				</td>
			</tr>
			<tr>
				<th>*비밀번호확인</th>
				<td>
					<input type="password" name='pw_ck' class='chk' /><br/>
					<div class='valid'>비밀번호를 다시 입력하세요.</div>
				</td>
			</tr>
			<tr>
				<th>*닉네임</th>
				<td>
					<input type="text" name='nickname' class='chk' />
					<a class='btn-fill-s' id='btn-nickname'>닉네임 중복확인</a>
					<div class='valid'>닉네임을 입력하세요</div>
				</td>
			</tr>
			
			<tr>
				<th>*주민등록번호</th>
				<td>
					<input type="text" name='idnumber' class = 'w-px60' maxlength="6" />
					- <input type="text" name='idnumber' class = 'w-px10' maxlength="1" />
				</td>
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
		<a class='btn-fill' onclick = 'go_join()'>회원가입</a>
		<a class='btn-empty' href='<c:url value="/" />'>취소</a>		
	</div>
	
	<!-- 다음 주소 검색 API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<!-- DatePicker 사용하기 위한 js -->
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/join-check.js"></script> 
 
	<script>
	
	function go_join() {
		if ($('[name=name]').val() == '') {
			$('[name=name]').focus();
			return;
		}
		
		// 중복확인 해서 이미 사용 중인 경우
		if ($('[name=email]').hasClass('checked') ){
			if ( ($('[name=email]').siblings('div').hasClass('invalid')) ) {
				alert('회원가입 불가! \n' + join.email.unUsable.desc);
				$('[name=email]').focus();
				return;
			}
		} else {
		// 중복확인 하지 않은 경우
			if (! item_check ($('[name=email]')) ) return;
			else {
				alert('회원가입 불가!\n' + join.email.valid.desc);
				$('[name=email]').focus();
				return;
			}
		}
		
		if ( ! item_check ( $('[name=pw]')) ) return;
		if ( ! item_check ( $('[name=pw_ck]')) ) return;
		
		$('form').submit();
		
	}
	
	// 각 유효성 검사 항목을 체크할 함수
	function item_check( item ) {
		var data = join.tag_status ( item );
		if ( data.code == 'invalid' ) {
			alert('회원가입 불가! \n' + data.desc );
			item.focus();
			return false;
		} else return true;
	}
	
	$('#btn-email').on('click', function () {
		email_check();
	})
	
	function email_check() {
		var $email = $('[name=email]');
		if ($email.hasClass('checked'))	return;
		var data = join.tag_status( $email );
		if ( data.code == 'invalid') {
			alert ('이메일 중복확인 불필요\n' + data.desc);
			$email.focus();
			return;
		}
		
		$.ajax({
			url : 'email_check'
			, data : {id:$email.val()}
			, success : function ( res ) {
				var data = join.email_usable (res);	
				$email.siblings('div').text (data.desc).removeClass().addClass(data.code);
				$email.addClass('checked');
			}, error: function ( req, text ) {
				alert(text + ':' + req.status);
			}
		});
		
	}
	
	
	// class=chk 에 대한 유효성 검사
	$('.chk').on('keyup', function (e) {
		
		if ($(this).attr('name') == 'email') {
			if (e.keyCode == 13) email_check();
			else $(this).removeClass('checked');
		}
		
		var data = join.tag_status ( $(this) );
		$(this).siblings('div').text (data.desc).removeClass().addClass(data.code);
	});
	
/* 	// 나이 제한을 설정하여 생년월일 선택할 수 있도록...
	var today = new Date();	// 오늘 날짜 선언 (today)
	// 오늘 날짜의 연도 데이터를 가지고 13년 뺌. 
	// 월은 그대로 유지, 일은 오늘 날짜로부터 1일을 뺌.
	var endDay = new Date( today.getFullYear() - 13, today.getMonth(), today.getDate() -1); 
	
	// 특정 날짜 이후 날짜 선택이 되지 않게끔.
	function after(date) {
		if ( date > new Date() ) return [false];
		else return [true];
	}
	
	// 생일이 선택되면 기호 나타냄
	$('[name=birth]').change(function () {
		$('#delete').css('display', 'inline');
	})
	
	// #delete 클릭시 생일 값 삭제
	$('#delete').click(function () {
		$('[name=birth]').val('');
		$('#delete').css('display', 'none');	
	})
	
	$( function() {
	    $( "[name=birth]" ).datepicker({
	    	dayNamesMin : ['일','월','화','수','목','금','토']
	    ,	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
	    ,	changeMonth : true
	    ,	changeYear : true
	    ,	dateFormat : 'yy-mm-dd'
	    , 	showMonthAfterYear : true
//	    ,	beforeShowDay : after		/* after 함수를 호출 */
//	    ,	maxDate : endDay			/* 달력에 나타날 최대 일자 지정 */
//	    });
//	} ); */
	
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