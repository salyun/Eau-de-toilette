package com.kkkj.eaude.controller;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kkkj.eaude.domain.MainVO;
import com.kkkj.eaude.service.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mService;

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, ModelAndView mv) {
		logger.info("main start");
		HttpSession session = request.getSession();
		String my_name = (String) session.getAttribute("my_name");
		mv.addObject("regInfo", mService.regInfo(my_name));
		mv.setViewName("main");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		logger.info("register start");

		return "register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, ModelAndView mv) {
		logger.info("login start");

		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/findid", method = RequestMethod.GET)
	public ModelAndView findid(HttpServletRequest request, ModelAndView mv) {
		logger.info("findid start");

		mv.setViewName("findid");
		return mv;
	}

	@RequestMapping(value = "/findpwd", method = RequestMethod.GET)
	public ModelAndView findpwd(HttpServletRequest request, ModelAndView mv) {
		logger.info("findid start");

		mv.setViewName("findpwd");
		return mv;
	}

	@RequestMapping(value = "/finishreg", method = RequestMethod.GET)
	public ModelAndView finishreg(HttpServletRequest request, ModelAndView mv) {
		logger.info("finishreg start");
		HttpSession session = request.getSession();
		String my_name = (String) session.getAttribute("my_name");
		mv.addObject("regInfo", mService.regInfo(my_name));
		mv.setViewName("finishreg");
		return mv;
	}

	@RequestMapping(value = "/findidresult", method = RequestMethod.GET)
	public ModelAndView findidresult(HttpServletRequest request, ModelAndView mv, MainVO vo) {
		logger.info("findidresult start");
		String result = mService.findidresult(vo);
		String result1 = result.substring(0, 3);
		int resultlength = result.length() - 3;
		String result2 = String.format("%" + resultlength + "s", "").replace(' ', '*');
		String findresult = result1 + result2;
		System.out.println(findresult);
		mv.addObject("findidresult", findresult);

		mv.setViewName("findidresult");
		return mv;
	}

	@RequestMapping(value = "/findpwresult", method = RequestMethod.GET)
	public ModelAndView findpwresult(HttpServletRequest request, ModelAndView mv, MainVO vo) {
		logger.info("findpwresult start");
		String[] splitEmail = vo.getM_email().split("@");
		String result = splitEmail[0];
		String str2 = splitEmail[1];
		String result1 = result.substring(0, 4);
		int resultlength = result.length() - 4;
		String result2 = String.format("%" + resultlength + "s", "").replace(' ', '*');
		String findresult = result1 + result2;
		mv.addObject("findpwresult", findresult + "@" + str2);
		mv.addObject("hiddenid", vo.getM_id());
		mv.addObject("hiddenemail", vo.getM_email());
		mv.setViewName("findpwresult");
		return mv;
	}


	@RequestMapping(value = "/findpwdcg", method = RequestMethod.GET)
	public ModelAndView findpwdcg(HttpServletRequest request, ModelAndView mv,
			@RequestParam(name = "m_id") String m_id) {
		logger.info("findpwdcg start");
		mv.addObject("hiddenid", m_id);
		mv.setViewName("findpwdcg");
		return mv;
	}

	// 아이디 중복 검사
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int idCheck(String m_id) {
		int result = mService.idCheck(m_id);
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	// 이메일 중복 검사
	@ResponseBody
	@RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
	public int emailCheck(String m_email) {
		int result = mService.emailCheck(m_email);
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	// 회원 가입 + 세션 생성
	@RequestMapping(value = "/insertMember", method = RequestMethod.POST)
	public String join(MainVO vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			result = mService.insertMember(vo);
			session = request.getSession();
			String my_name = request.getParameter("m_id");
			session.setAttribute("my_name", my_name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/finishreg";
	}

	// 로그인
	@ResponseBody
	@RequestMapping(value = "/enterlogin", method = RequestMethod.POST)
	public int enterlogin(MainVO vo) {
		int resultid = mService.idExist(vo);
		int resultpw = mService.pwExist(vo);
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return -10;
		}
		return resultid + resultpw;
	}

	// 로그인 세션 생성
	@RequestMapping(value = "/loginSuccess")
	public String loginSuccess(String m_id, HttpSession session, HttpServletRequest request) {
		try {
			session = request.getSession();
			String my_name = request.getParameter("m_id");
			session.setAttribute("my_name", my_name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/main";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", produces = "application/json; charset=utf8")
	public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
		try {
			PrintWriter out = response.getWriter();
			// 등록된 아이디가 없으면
			if (session.getAttribute("my_name") == null) {
			} else {
				session.removeAttribute("my_name");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 아이디 찾기
	@ResponseBody
	@RequestMapping(value = "/findidajax", method = RequestMethod.POST)
	public int findidajax(MainVO vo) {
		int result = mService.findid(vo);
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return -10;
		}
		return result;
	}

	// 비밀번호 찾기
	@ResponseBody
	@RequestMapping(value = "/findpwajax", method = RequestMethod.POST)
	public int findpwajax(MainVO vo) {
		int result = mService.findpw(vo);
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return -10;
		}
		return result;
	}

	// 비밀번호 업데이트
	@RequestMapping(value = "/changepw", method = RequestMethod.POST)
	public String changepw(MainVO vo, HttpSession session, HttpServletRequest request) {
		int result = 0;
		try {
			result = mService.changepw(vo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}

//	// 문자를 보낼때 맵핑되는 메소드
//	@RequestMapping(value = "/sendSms")
//	public String sendSms(HttpServletRequest request) throws Exception {
//
//		String api_key = "NCS0VWZPZQPVXEXX"; // 위에서 받은 api key를 추가
//		String api_secret = "DKTPX4RYWG5GEDPL6DMRRNKOJMATK24X"; // 위에서 받은 api secret를 추가
//
//		Coolsms coolsms = new Coolsms(api_key, api_secret);
//
//		HashMap<String, String> set = new HashMap<String, String>();
//		set.put("to", (String) request.getParameter("to")); // 수신번호
//
//		set.put("from", "01083595008"); // 발신번호, jsp에서 전송한 발신번호를 받아 map에 저장한다.
//		set.put("text", "안녕하세요 인증번호는 [" + (String) request.getParameter("text") + "]입니다"); // 문자내용, jsp에서 전송한 문자내용을 받아
//																							// map에 저장한다.
//		set.put("type", "sms"); // 문자 타입
//
//		System.out.println(set);
//
//		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
//
//		if ((boolean) result.get("status") == true) {
//
//			// 메시지 보내기 성공 및 전송결과 출력
//			System.out.println("성공");
//			System.out.println(result.get("group_id")); // 그룹아이디
//			System.out.println(result.get("result_code")); // 결과코드
//			System.out.println(result.get("result_message")); // 결과 메시지
//			System.out.println(result.get("success_count")); // 메시지아이디
//			System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
//		} else {
//
//			// 메시지 보내기 실패
//			System.out.println("실패");
//			System.out.println(result.get("code")); // REST API 에러코드
//			System.out.println(result.get("message")); // 에러메시지
//		}
//
//		return "member/number"; // 문자 메시지 발송 성공했을때 number페이지로 이동함
//	}

	@RequestMapping(value = "/mailSender")
	public ModelAndView mailSender(HttpServletRequest request, ModelMap mo, ModelAndView mv,
			@RequestParam(name = "m_email") String m_email, @RequestParam(name = "m_id") String m_id, @RequestParam(name = "chk") String chk)
			throws AddressException, MessagingException {
		String host = "smtp.gmail.com";
		final String username = "mbonaegi@gmail.com";
		final String password = "bonaegi~!";
		int port = 465;

		String recipient = m_email;
		String subject = "오 드 뚜왈렛 비밀번호 찾기 서비스입니다.";
		String body = "인증번호입니다." + chk;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;

			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("mbonaegi@gmail.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);
		mv.addObject("hiddenid", m_id);
		mv.addObject("chk", chk);
		mv.addObject("hiddenemail", m_email);
		mv.setViewName("findpwresultchk");
		return mv;
	}

}