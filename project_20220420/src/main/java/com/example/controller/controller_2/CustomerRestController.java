package com.example.controller.controller_2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.entity1.Member;
import com.example.entity.entity1.MemberCompany;
import com.example.entity.entity1.MemberPersonal;
import com.example.entity.projection.MemberIdprojection;
import com.example.jwt.JwtUtil;
import com.example.repository.MemberCPRepository;
import com.example.repository.MemberPSRepository;
import com.example.repository.MemberRepository;
import com.example.service.UserDetailsServiceImpl;
import com.example.service.service_2.MemberService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

// backend만 구현함. 화면구현 X, vue.js 또는 react.js 연동

@RestController
@RequestMapping(value = "/member")
public class CustomerRestController {
    
	@Autowired MemberRepository mRepository;
	@Autowired MemberService mService;

	@Autowired JwtUtil jwtUtil;

	@Autowired UserDetailsServiceImpl userDetailservice;

	@Autowired MemberPSRepository mpsRepository;

	@Autowired MemberCPRepository cpRepository;


	// 닉네임 수정
	// 127.0.0.1:9090/ROOT/member/updatenickname
	@RequestMapping(value = "/updatenickname", 
	// {"mpNickname":"수정닉네임"}
			method = { RequestMethod.PUT },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> NicknamePut(
		@RequestBody MemberPersonal memberPersonal,
		@RequestHeader(name = "TOKEN") String token){
		System.out.println(memberPersonal);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		try {
			String username = jwtUtil.extractUsername(token);
			System.out.println(username);
			MemberPersonal member = new MemberPersonal();
			member.setMpnickname(memberPersonal.getMpnickname());

			memberPersonal = mpsRepository.findByMember_Mid(username);
			System.out.println(member.getMpnickname());
			memberPersonal.setMpnickname(member.getMpnickname());
			
			
				
				
			mpsRepository.save(memberPersonal);
			map.put("status", 200); // 0 -> 200
	}
		
		catch (Exception e) {
			e.printStackTrace();
	   }
		return map;
	}
	// 이메일로 아이디찾기
	// 127.0.0.1:9090/ROOT/member/searchid
	@RequestMapping(value = "/searchid", 
	//{"uemail":"c1", "upw":"c1" };
			method = { RequestMethod.GET },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> searchidget(
		@RequestParam(name = "email")String email){
			System.out.println(email);
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0); // 정상적이지 않을때
			try {
				Member member = mRepository.findByMemail(email);
				System.out.println(member);
				// String mid = member.getMId();
				// if(Match(member.getMPw(), user.getPassword()))
				map.put("id", member.getMid());
				map.put("status", 200); 

			}	
			 catch (Exception e) {
				e.printStackTrace();
			}

		return map;
	}

	// 회원정보 수정
	// 127.0.0.1:9090/ROOT/member/updatemember
	@RequestMapping(value = "/updatemember", 
	//{"uemail":"c1", "upw":"c1" };
			method = { RequestMethod.PUT },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> UpdateMemberPut(
		@ModelAttribute Member member,
		@RequestHeader(name = "TOKEN") String token,
		@RequestParam(name = "file",required = false) MultipartFile file){
		System.out.println(member.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		try {
			
			String username = jwtUtil.extractUsername(token);
			System.out.println(username);
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			UserDetails user = userDetailservice.loadUserByUsername(username);
			// Member member1 = new Member();
			
			// UserDetails user = userDetailservice.loadUserByUsername(username);
			// System.out.println("===="+ user);
			// // 현재암호랑 입력한 암호가 맞는지 확인
			// // user.getPassword() 는 암호화 된거  member.getUpw() 는 암호화x
			if(bcpe.matches(member.getMpw(), user.getPassword())){
			Member member1 =mRepository.findById(username).orElse(null);
				
			if(file != null){
					if(!file.isEmpty()){
						member1.setMprofile(file.getBytes());
						member1.setMimagesize(file.getSize());
						member1.setMimagetype(file.getContentType());
						member1.setMimagename(file.getOriginalFilename());
			}
			else{
				member1.setMprofile(null);
				member1.setMimagesize(0L);
				member1.setMimagetype(null);
				member1.setMimagename(null);
			}
		}
				member1.setMname(member.getMname());
				member1.setMphone(member.getMphone());
				member1.setMaddress(member.getMaddress());
				member1.setMemail(member.getMemail());
				System.out.println(member1.getMemail());
				mRepository.save(member1);
				
				
				
			map.put("status", 200); // 0 -> 200
	}
		}
		
		catch (Exception e) {
			e.printStackTrace();
	   }
		return map;
	}


	// 회원탈퇴
	// 127.0.0.1:9090/ROOT/member/delete
	@RequestMapping(value = "/delete", 
            method = { RequestMethod.DELETE },
            consumes = { MediaType.ALL_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public Map<String, Object> DeleteMember(
		@RequestHeader(name = "TOKEN") String token,
       @ModelAttribute Member member){
    Map<String, Object> map = new HashMap<>();
    map.put("status", 0);
	try{
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String username = jwtUtil.extractUsername(token);
		UserDetails user = userDetailservice.loadUserByUsername(username);

		if(bcpe.matches(member.getMpw(), user.getPassword())){


		Member member1 = mRepository.findById(username).orElse(null);
			System.out.println(member1);
		mRepository.delete(member1);
		
		map.put("status", 200);
		}
	}
	catch (Exception e) {
		e.printStackTrace();
	}

    return map;
    }


	// 암호변경 ( 토큰,현재암호, 변경암호)
	// 127.0.0.1:9090/ROOT/member/updatepw
	@RequestMapping(value = "/updatepw", 
	//{"uemail":"c1", "upw":"c1" };
			method = { RequestMethod.PUT },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> UpdatePasswordPut(
		@ModelAttribute Member member,
		@RequestHeader(name = "TOKEN") String token){
		System.out.println(member.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		System.out.println(token);
		try {
			String username = jwtUtil.extractUsername(token);
			System.out.println(username);
			UserDetails user = userDetailservice.loadUserByUsername(username);
			System.out.println("===="+ user);
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
			// // 현재암호랑 입력한 암호가 맞는지 확인
			// // user.getPassword() 는 암호화 된거  member.getUpw() 는 암호화x
			if(bcpe.matches(member.getMpw(), user.getPassword())){
				Member member1 =mRepository.findById(username).orElse(null);

				// 암호화
				member1.setMpw(bcpe.encode(member.getNewpw()));
				// System.out.println(member1.toString());
				// member1 = mRepository.save(member);
				mRepository.save(member1);
				
				
				
			map.put("status", 200); // 0 -> 200
			}
				
		}
		
		catch (Exception e) {
			e.printStackTrace();
	   }
		return map;
	}

	// 마이페이지
	// 127.0.0.1:9090/ROOT/member/mypage
	@RequestMapping(value = "/mypage", 
	//{"uemail":"c1", "upw":"c1" };
			method = { RequestMethod.GET },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> customerMypageGet(
		@RequestHeader(name = "TOKEN") String token){
		String username = jwtUtil.extractUsername(token);
		System.out.println(username);

		// 토큰이 있어야 실행됨
		Map<String, Object> map = new HashMap<>();
		map.put("status", 200); 
		return map;
	}

	// 로그인
	// 127.0.0.1:9090/ROOT/member/login
	//{"mId":"c1", "mPw":"c1" };
	@RequestMapping(value = "/login", 
			method = { RequestMethod.POST },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> customerLoginPost(
		@ModelAttribute Member member){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0); // 정상적이지 않을때

		
		UserDetails user = userDetailservice.loadUserByUsername(member.getMid());
			System.out.println(user);
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// user.getPassword() 는 암호화 된거  member.getUpw() 는 암호화x
		// 암호회 되지 않은 것과 암호화 된것 비교하기
		if(bcpe.matches(member.getMpw(), user.getPassword())){
			String token = jwtUtil.generatorToKen(member.getMid());
			map.put("status", 200); // 0 -> 200
			map.put("token", token);
		}
		return map;

	}

	// 회원가입(고객만customer)
	// 127.0.0.1:9090/ROOT/member/join.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/join.json", 
			method = { RequestMethod.POST },
			consumes = { MediaType.ALL_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> JoinPost(
			@ModelAttribute Member member,
			@RequestParam(name = "file",required = false) MultipartFile file){
			System.out.println(member.toString());
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

		Map<String, Object> map = new HashMap<>();
		try {
			if(
			file != null){
			if(!file.isEmpty()){
				member.setMprofile(file.getBytes());
				member.setMimagesize(file.getSize());
				member.setMimagetype(file.getContentType());
				member.setMimagename(file.getOriginalFilename());
				
			}
		}
	
			member.setMpw(bcpe.encode( member.getMpw()) );
			mRepository.save(member);
			// mService.memberInsert(member);
			map.put("status", 200);
            }
            catch (Exception e) {
                e.printStackTrace();
                map.put("status", 0);
            }
            
            return map;
        }
	// 개인회원가입(개인회원)
	// 127.0.0.1:9090/ROOT/member/psjoin.json
	//{"mid":"c1", "mpw":"c1" };
	@PostMapping(value = "/psjoin.json", 
	consumes = MediaType.ALL_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> PersonalJoinPost(
		@ModelAttribute MemberPersonal psmemberpersonal){
			// 이미지,배열 형식이면 ModelAttribute, 폼데이터 사용
			System.out.println(psmemberpersonal.toString());
			Map<String, Object> map = new HashMap<>();
		try {
			Member member = new Member();
			member.setMid((String) psmemberpersonal.getMember().getMid());
			psmemberpersonal.setMember(member);
			
			mpsRepository.save(psmemberpersonal);
			map.put("status", 200);
            }
            catch (Exception e) {
                e.printStackTrace();
                map.put("status", 0);
            }
            
            return map;
        }
		// 기업회원가입(Company)
	// 127.0.0.1:9090/ROOT/member/cpjoin.json
	//{"mid":"c1", "mpw":"c1" };
	@RequestMapping(value = "/cpjoin.json", 
	method = { RequestMethod.POST },
	consumes = { MediaType.ALL_VALUE },
	produces = { MediaType.APPLICATION_JSON_VALUE })
public Map<String, Object> CompanyJoinPost(
	@ModelAttribute MemberCompany cpmember){
	System.out.println(cpmember.toString());
	Map<String, Object> map = new HashMap<>();
try {

	Member member = new Member();
	member.setMid((String) cpmember.getMember().getMid());
	cpmember.setMember(member);
	System.out.println(member);

	// System.out.println(psmemberpersonal.get("m_id"));
	// member.setMId((String) psmemberpersonal.get("m_id"));
	// System.out.println(memberps.toString());
	// psmember.setMember(member);
	// System.out.println(member);

	
	cpRepository.save(cpmember);
	map.put("status", 200);
	}
	catch (Exception e) {
		e.printStackTrace();
		map.put("status", 0);
	}
	
	return map;
}
}