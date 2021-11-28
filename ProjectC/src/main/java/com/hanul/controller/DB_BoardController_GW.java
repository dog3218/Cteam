package com.hanul.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import app_board.BoardDAO_GW;
import app_board.BoardVO;

@Controller
public class DB_BoardController_GW {
	
	@Autowired BoardDAO_GW service;
	
	@Scheduled(cron = "* * * 14 * *")
	//@RequestMapping("/DB_Update")
	public String Board_DB_Update() {
		 
		service.board_clear();
		// json = key, value로 나누어진 데이터 자료 구조
		// { , } 중괄호 안에서 표현되고, 대괄호 [ , ]는 하나의 value
		// 중괄호는 우리가 사용하는 객체(DTO)같은 개념으로 이해를 하고
		// []대괄호는 List, Array의 개념으로 이해를 하면 된다.
		// 1.연결
		Calendar cal = Calendar.getInstance();
		String url = "http://api.data.go.kr/openapi/tn_pubr_public_pblprfr_event_info_api";
		// ?=&perPage=10&serviceKey=
		//?pageNo=1&numOfRows=100&type=json&ServiceKey=
		ArrayList<BoardVO> list = new ArrayList<>();
		String jsonData ="";

		try {
			long miliseconds = System.currentTimeMillis();
			Date date = new Date(miliseconds);
			System.out.println(date);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM");
			String today = transFormat.format(date);
			date.setMonth(date.getMonth()+1);
			String nextDay = transFormat.format(date);
			
			//이번달부터 한달동안 공연정보 업데이트
				for(int k = 1; k <31; k++) {
					if(k<10) {
						jsonData = Jsoup.connect(url).data("pageNo", "1").data("numOfRows", "100").data("type","json").data("eventStartDate", today+"-0"+k)
					.data("ServiceKey",
							"IwgVDDGsxRFdLU7/Xc2DwJj23SLeFr9/UXRabzTddhiV7yUXBC2h3i95Gj+osLiKCHmkdo6882vlR28h2L6QUw==")
					.timeout(100000).userAgent("Chrome").ignoreContentType(true).execute().body();
					}
					if(k>9) {
						jsonData = Jsoup.connect(url).data("pageNo", "1").data("numOfRows", "100").data("type","json").data("eventStartDate", today+"-"+k)
								.data("ServiceKey",
										"IwgVDDGsxRFdLU7/Xc2DwJj23SLeFr9/UXRabzTddhiV7yUXBC2h3i95Gj+osLiKCHmkdo6882vlR28h2L6QUw==")
								.timeout(100000).userAgent("Chrome").ignoreContentType(true).execute().body();
					}
					
			System.out.println(jsonData);
			JSONParser jsonParser = new JSONParser();// JSONDATA 파싱을 위한 객체
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
			System.out.println(jsonObject);
			jsonObject = (JSONObject) jsonObject.get("response");
			jsonObject = (JSONObject) jsonObject.get("body");
			JSONArray ArrayJson = (JSONArray) jsonObject.get("items");
			for (int i = 0; i < ArrayJson.size(); i++) {
				
				JSONObject jsonDTO = (JSONObject) ArrayJson.get(i);
				
				BoardVO vo = new BoardVO();

				vo.setEventnm(jsonDTO.get("eventNm").toString());
				vo.setOpar(jsonDTO.get("opar").toString());
				vo.setEventco(jsonDTO.get("eventCo").toString());
				vo.setEventstartdate(jsonDTO.get("eventStartDate").toString());
				vo.setEventenddate(jsonDTO.get("eventEndDate").toString());
				vo.setEventstarttime(jsonDTO.get("eventStartTime").toString());
				vo.setEventendtime(jsonDTO.get("eventEndTime").toString());
				vo.setChrgeinfo(jsonDTO.get("chrgeInfo").toString());
				vo.setMnnst(jsonDTO.get("mnnst").toString());
				vo.setAuspcinstt(jsonDTO.get("auspcInstt").toString());
				vo.setPhonenumber(jsonDTO.get("phoneNumber").toString());
				vo.setSuprtinstt(jsonDTO.get("suprtInstt").toString());
				vo.setSeatnumber(jsonDTO.get("seatNumber").toString());
				vo.setAdmfee(jsonDTO.get("admfee").toString());
				vo.setEntncage(jsonDTO.get("entncAge").toString());
				vo.setDscntinfo(jsonDTO.get("dscntInfo").toString());
				vo.setAtpn(jsonDTO.get("atpn").toString());
				vo.setHomepageurl(jsonDTO.get("homepageUrl").toString());
				vo.setAdvantkinfo(jsonDTO.get("advantkInfo").toString());
				vo.setPrkplceyn(jsonDTO.get("prkplceYn").toString());
				vo.setRdnmadr(jsonDTO.get("rdnmadr").toString());	
				vo.setLnmadr(jsonDTO.get("lnmadr").toString());
				vo.setLatitude(jsonDTO.get("latitude").toString());
				vo.setLongitude(jsonDTO.get("longitude").toString());
				vo.setReferencedate(jsonDTO.get("referenceDate").toString());
				
				service.board_insert(vo);
				System.out.println(vo.getEventstartdate()+"일 시작하는" +vo.getEventnm()+" DB저장 완료");
				Thread.sleep(200);
				// ArrayList<memberDTO>list 쓰는거랑 똑같음
			}}
				
				for(int k = 1; k <31; k++) {
					if(k<10) {
						jsonData = Jsoup.connect(url).data("pageNo", "1").data("numOfRows", "100").data("type","json").data("eventStartDate", nextDay+"-0"+k)
								.data("ServiceKey",
										"IwgVDDGsxRFdLU7/Xc2DwJj23SLeFr9/UXRabzTddhiV7yUXBC2h3i95Gj+osLiKCHmkdo6882vlR28h2L6QUw==")
								.timeout(100000).userAgent("Chrome").ignoreContentType(true).execute().body();
					}
					if(k>9) {
						jsonData = Jsoup.connect(url).data("pageNo", "1").data("numOfRows", "100").data("type","json").data("eventStartDate", nextDay+"-"+k)
								.data("ServiceKey",
										"IwgVDDGsxRFdLU7/Xc2DwJj23SLeFr9/UXRabzTddhiV7yUXBC2h3i95Gj+osLiKCHmkdo6882vlR28h2L6QUw==")
								.timeout(100000).userAgent("Chrome").ignoreContentType(true).execute().body();
					}
					
					System.out.println(jsonData);
					JSONParser jsonParser = new JSONParser();// JSONDATA 파싱을 위한 객체
					JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonData);
					System.out.println(jsonObject);
					jsonObject = (JSONObject) jsonObject.get("response");
					jsonObject = (JSONObject) jsonObject.get("body");
					JSONArray ArrayJson = (JSONArray) jsonObject.get("items");
					for (int i = 0; i < ArrayJson.size(); i++) {
						
						JSONObject jsonDTO = (JSONObject) ArrayJson.get(i);
						
						BoardVO vo = new BoardVO();
						
						vo.setEventnm(jsonDTO.get("eventNm").toString());
						vo.setOpar(jsonDTO.get("opar").toString());
						vo.setEventco(jsonDTO.get("eventCo").toString());
						vo.setEventstartdate(jsonDTO.get("eventStartDate").toString());
						vo.setEventenddate(jsonDTO.get("eventEndDate").toString());
						vo.setEventstarttime(jsonDTO.get("eventStartTime").toString());
						vo.setEventendtime(jsonDTO.get("eventEndTime").toString());
						vo.setChrgeinfo(jsonDTO.get("chrgeInfo").toString());
						vo.setMnnst(jsonDTO.get("mnnst").toString());
						vo.setAuspcinstt(jsonDTO.get("auspcInstt").toString());
						vo.setPhonenumber(jsonDTO.get("phoneNumber").toString());
						vo.setSuprtinstt(jsonDTO.get("suprtInstt").toString());
						vo.setSeatnumber(jsonDTO.get("seatNumber").toString());
						vo.setAdmfee(jsonDTO.get("admfee").toString());
						vo.setEntncage(jsonDTO.get("entncAge").toString());
						vo.setDscntinfo(jsonDTO.get("dscntInfo").toString());
						vo.setAtpn(jsonDTO.get("atpn").toString());
						vo.setHomepageurl(jsonDTO.get("homepageUrl").toString());
						vo.setAdvantkinfo(jsonDTO.get("advantkInfo").toString());
						vo.setPrkplceyn(jsonDTO.get("prkplceYn").toString());
						vo.setRdnmadr(jsonDTO.get("rdnmadr").toString());	
						vo.setLnmadr(jsonDTO.get("lnmadr").toString());
						vo.setLatitude(jsonDTO.get("latitude").toString());
						vo.setLongitude(jsonDTO.get("longitude").toString());
						vo.setReferencedate(jsonDTO.get("referenceDate").toString());
						
						service.board_insert(vo);
						System.out.println(vo.getEventstartdate()+"일 시작하는" +vo.getEventnm()+" DB저장 완료");

						Thread.sleep(200);
						// ArrayList<memberDTO>list 쓰는거랑 똑같음
					}}
			}catch (Exception e) {
			System.out.println("JSON에러");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			}
		
			System.out.println("DB업데이트완료");		
			return "/";
	}
	
	
	
}
