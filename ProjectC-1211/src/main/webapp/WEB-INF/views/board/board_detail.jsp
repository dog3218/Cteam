<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#popup {
	width: 450px;
	height: 450px;
	border: 2px solid #777;
	display: none;
}

.popup {
	width: 100%;
	height: 100%;
}

.comment {
	margin: 0 auto;
	padding-top: 20px;
	width: 500px;
}

#comment_regist {
	width: 100%;
}

#comment_regist span {
	width: 50%;
	float: left;
}

textarea#comment {
	width: 96%;
	height: 60px;
	margin-top: 5px;
	resize: none;
}
</style>


</head>
<body>
	<h2>공연정보</h2>
	<table>
		<tr>
			<th colspan="2">제목</th>
			<td class='left middle' colspan="3">${vo.eventnm}</td>
			<th>공연유형</th>
			<td colspan="2">${vo.eventco}</td>
		</tr>
		<tr>
			<th colspan="2">작성자</th>
			<td class='left' colspan="1">${vo.writer}</td>
			<th class='w-px120' >작성일자</th>
			<td class='w-px120' colspan="2">${vo.referencedate}</td>
			<th class='w-px80' >조회수</th>
			<td class='w-px80'>${vo.readcnt }</td>
		</tr>

		<tr>
			<th>시작 날짜</th>
			<td>${vo.eventstartdate}</td>
			<th>종료 날짜</th>
			<td>${vo.eventenddate}</td>
			<th>시작 시간</th>
			<td>${vo.eventstarttime}</td>
			<th>종료 시간</th>
			<td>${vo.eventendtime }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td class='addr' colspan="7">${vo.rdnmadr}</td>
		</tr>
		<tr>
			<th>주관 기관</th>
			<td colspan="2">${vo.mnnst}</td>
			<th>주최 기관</th>
			<td colspan="2">${vo.auspcinstt}</td>
			<th>후원 기관</th>
			<td colspan="2">${vo.suprtinstt}</td>
		</tr>
		<tr>
			<th>좌석수</th>
			<td colspan="2">${vo.seatnumber}</td>
			<th>입장연령</th>
			<td colspan="2">${vo.entncage}</td>
			<th>주차장</th>
			<td colspan="2">
				<label><input type="radio" name='prkplceyn' value='Y' ${vo.prkplceyn eq 'Y' ? "checked" : '' } onclick="return(false);"/>Y</label>
				<label><input type="radio" name='prkplceyn' value='N' ${vo.prkplceyn eq 'N' ? "checked" : '' } onclick="return(false);"/>N</label>
			</td>
		</tr>
		<tr>
			<th>유/무료</th>
			<td colspan="2">
				<label><input type="radio" name='chrgeinfo' value='유료' ${vo.chrgeinfo eq '유료' ? "checked" : '' } onclick="return(false);"/>유료</label>
				<label><input type="radio" name='chrgeinfo' value='무료' ${vo.chrgeinfo eq '무료' ? "checked" : '' } onclick="return(false);"/>무료</label>
			</td>
			<th>입장료</th>
			<td colspan="2">${vo.admfee}</td>
			<th>온/오프</th>
			<td colspan="2">
			 <label><input type="radio" name='on_offline' value='n' ${vo.on_offline eq 'n' ? "checked" : '' } onclick="return(false);"/>온라인</label>
			 <label><input type="radio" name='on_offline' value='f' ${vo.on_offline eq 'f' ? "checked" : '' } onclick="return(false);"/>오프라인</label> 
			 <label><input type="radio" name='on_offline' value='d' ${vo.on_offline eq 'd' ? "checked" : '' } onclick="return(false);"/>둘다</label></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td colspan="2">${vo.phonenumber }</td>
			<th>할인정보</th>
			<td colspan="2">${vo.dscntinfo }</td>
			<th>홈페이지</th>
			<td colspan="2">${vo.homepageurl }</td>
		</tr>
		<tr>
			<th>유의사항</th>
			<td colspan="7">${vo.atpn }</td>
		</tr>
		<tr>
		<tr>
			<th>첨부파일</th>
			<td class='left middle' colspan="7">
				 <th>첨부파일</th>
					<td colspan="7" class="left">${vo.filepath }
					<%-- <td colspan="5" class="left">${fn:substring(vo.filename1, fn:indexOf(vo.filename1, '_') +1, fn:length(vo.filename1)) } --%>
						<c:if test="${! empty vo.filepath }">
							<a id = 'preview'></a>
							<a href='download.co?no=${vo.no }'><i class="fas fa-download font-img"></i></a>		
						</c:if>
					</td>
		</tr>
	</table>

	<div class='btnSet'>
		<a class='btn-fill' onclick='$("form").submit()'>목록으로</a>
		<c:if test="${loginInfo.email eq vo.writer }">
			<a class='btn-fill'
				onclick='$("form").attr("action", "modify.bo"); 
		$("form").submit()'>수정</a>
			<a class='btn-fill'
				onclick="if (confirm ('정말 삭제하시겠습니까?') )
		 {href='delete.bo?no=${vo.no}'}">삭제</a>
		</c:if>
	</div>


	<!-- 목록 요청에 필요한 데이터를 post 방식으로 전달하는 방법 -->
	<form method="post" action="list.bo">
		<input type="hidden" name='no' value="${vo.no }">
		<!-- 글 id -->
		<input type="hidden" name='search' value='${page.search }' />
		<!-- 검색조건 -->
		<input type="hidden" name='keyword' value='${page.keyword }' />
		<!-- 검색어 -->
		<input type="hidden" name='curPage' value='${page.curPage }' />
		<!-- 현재 페이지 -->
		<input type="hidden" name='viewType' value='${page.viewType }' />
		<!-- 게시판 형태 -->
		<input type="hidden" name='pageList' value='${page.pageList }' />
		<!-- 페이지당 보여질 목록수 -->
	</form>

</body>
</html>









