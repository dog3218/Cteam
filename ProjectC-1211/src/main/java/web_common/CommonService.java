package web_common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class CommonService {
	
	//파일 다운로드 처리
	public void fileDownload(String filename, String filepath, 
			HttpSession session, HttpServletResponse response) {
		
		//실제 파일의 위치와 파일을 찾음 
		File file = new File(session.getServletContext().getRealPath("resources")+"/"+filepath);
		//파일의 형태를 확인
		String mime = session.getServletContext().getMimeType(filename);
		//클라이언트에 파일을 첨부하여 쓰기 작업을 하는데 파일을 첨부하는 건
		//header에 첨부파일 정보를 넘겨줘야 함
		
		try {
			filename = URLEncoder.encode(filename, "utf-8").replaceAll("\\+", "%20");
		response.setHeader("content-disposition", "attachment; filename= "+filename);		
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	// 파일 업로드 처리
		public String fileUpload(String category, MultipartFile file, HttpSession session) {
			// 업로드 할 위치
			String resources = session.getServletContext().getRealPath("resources");
	// D:\Study_Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources
			// upload/notice/2021/10/27/OOOOOOOO_123.png
			String folder = resources + "/upload/" + category + "/"
				+ new SimpleDateFormat("yyyy/MM/dd").format(new Date() );
			String uuid =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			
			File dir = new File ( folder );
			
			if (! dir.exists() ) dir.mkdir();
				try {
					file.transferTo(new File( folder, uuid ));
				} catch (Exception e) {
					e.printStackTrace();
				}
			// upload/notice/2021/10/27/OOOOOOOO_123.png
			return folder.substring(resources.length() + 1) + "/" + uuid;	
		}
		
	
	
	
	//이메일 전송 메소드
	public void sendEmail(String name, String email, HttpSession session) {
		//기본 형태 이메일 전송
		//simpleSend(name, email);
		
		// 파일 첨부하는 이메일 전송
		//attachSend(name, email, session);
		
		htmlSend(name, email, session);
		
	}
	
	//html 태그 형태(파일)의 이메일을 보내는 메소드
	private void htmlSend(String name, String email, HttpSession session)  {
		HtmlEmail mail = new HtmlEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인 하기 위한 아이디/비번 지정
		mail.setAuthentication("1995eve", "TJS6XPXLSWC2");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("1995eve@naver.com", "우루루롹끼");
			mail.addTo("1995eve@naver.com", "민지");
			mail.setSubject("우르롹끼 우루루루롺끼");
			
			StringBuffer msg = new StringBuffer("<hmtl>");
			msg.append("<body>");
			msg.append("<a href ='https://google.com'>"
					+"구글<img src='https://www.google.com/url?sa=i&url=https%3A%2F%2Fnews.nate.com%2Fview%2F20210305n00020&psig=AOvVaw2yaHwwdFY2fAdOK1WOhuC1&ust=1635232665244000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCKiC8MKC5fMCFQAAAAAdAAAAABAf' /></a>");
			msg.append("<hr>");
			msg.append("<h3>한울 IoT 과정 가입 축하</h3>");
			msg.append("<p>가입을 축하합니다.</p>");
			msg.append("<p>프로젝트까지 마무리하고 모두 취업하세요.</p>");
			msg.append("</body></html>");
			
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment(); 
			file.setPath(session.getServletContext().getRealPath("resources/css/common.css"));
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setURL(new URL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fnews.nate.com%2Fview%2F20210305n00020&psig=AOvVaw2yaHwwdFY2fAdOK1WOhuC1&ust=1635232665244000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCKiC8MKC5fMCFQAAAAAdAAAAABAf"));
			mail.attach(file);
			

			mail.send();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//파일 첨부하여 이메일 보내는 메소드
	private void attachSend(String name, String email, HttpSession session) {
		MultiPartEmail mail = new MultiPartEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인 하기 위한 아이디/비번 지정
		mail.setAuthentication("1995eve", "TJS6XPXLSWC2");
		mail.setSSLOnConnect(true);
		
		try {
//			mail.setFrom("이메일주소", "한울IoT관리");
			mail.setFrom("1995eve@naver.com", "우루루롹끼");
//			mail.addTo(email, name);
			mail.addTo("1995eve@naver.com", "민지");
			
			mail.setSubject("우르롹끼 우루루루롺끼");
			mail.setMsg("첨부파일 확인하기로, 약속.");
			
			//이메일 파일 첨부 클래스 (EmailAttachment) 생성
			EmailAttachment file = new EmailAttachment();
			//1. 현재 쓰고있는 컴퓨터의 파일을 보내는 형태
			file.setPath("C:\\Users\\admin\\Downloads\\choi1.jpeg");
			mail.attach(file);
			
			//2. 현재 작업중인 프로젝트 내부 파일을 보낼 때 
			file = new EmailAttachment();
			file.setPath(session.getServletContext().getRealPath("resources/images/")
					+ "choi2.jpg");
			mail.attach(file);
			
			//3. URL로 파일 첨부
			file = new EmailAttachment();
			file.setURL(new URL("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.insight.co.kr%2Fnews%2F338089&psig=AOvVaw2yaHwwdFY2fAdOK1WOhuC1&ust=1635232665244000&source=images&cd=vfe&ved=0CAgQjRxqFwoTCKiC8MKC5fMCFQAAAAAdAAAAABAa"));
			mail.attach(file);
			
			mail.send();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//기본 형태 이메일 보내는 메소드
	private void simpleSend(String name, String email) {
		SimpleEmail mail = new SimpleEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인 하기위한 아이디/비번 지정
//		mail.setAuthentication("아이디", "비밀번호");
		mail.setAuthentication("1995eve", "TJS6XPXLSWC2");
										//2차인증 비밀번호 발급받은것
		mail.setSSLOnConnect(true);
		
		try {
//			mail.setFrom("이메일주소", "한울IoT관리");
			mail.setFrom("1995eve@naver.com", "우루루롹끼");
//			mail.addTo(email, name);
			mail.addTo("1995eve@naver.com", "민지");
			
			mail.setSubject("우르롹끼");
			mail.setMsg(name+"님! IoT 과정 가입을 축하드립니다.");
			
			mail.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	// 접근 토큰을 이용하여 프로필 API 호출하기 위하여 {access_token 과 token_type 값을 파라미터 전달}
	public String requestAPI(StringBuffer url, String property) {
		String result = "";		// result 변수 초기화 선언
		try {
//		      URL url = new URL(apiURL);
			// 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 생성
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString() ).openConnection();
		      con.setRequestMethod("GET");
		    // 요청 헤더명이 "Authorization" 이므로 property 를 바꾸고, 
  
		      con.setRequestProperty("Authorization", property);
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode); 
		      if(responseCode >= 200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); // 한글 깨짐 처리
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();		// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();				// BufferedReader 닫기
		      con.disconnect();			// HTTP 통신 연결 종료
		      result = res.toString();		 
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return result;
	}
	
	
	
	// API 명세 > 로그인 API 명세
	
	public String requestAPI(StringBuffer url) {
		String result = "";		// result 변수 초기화 선언
		try {
//		      URL url = new URL(apiURL);
			// 연결할 개체가 HTTP 통신을 할 예정이므로 HTTP 간의 연결 개체 생성
		      HttpURLConnection con = (HttpURLConnection) new URL(url.toString() ).openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode); 
		      if(responseCode >= 200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); // 한글 깨짐 처리
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();		// 실제 값이 담겨진 변수 res 값을 리턴하여 보내줌.
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();				// BufferedReader 닫기
		      con.disconnect();			// HTTP 통신 연결 종료
		      result = res.toString();		 
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return result;
	}
	
}
