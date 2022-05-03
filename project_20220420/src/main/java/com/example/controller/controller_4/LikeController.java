package com.example.controller.controller_4;

import javax.servlet.http.HttpSession;

import com.example.repository.repository_4.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/like")
public class LikeController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    LikeRepository lRepository;

    // 127.0.0.1:9090/ROOT/like/selectone
    @GetMapping(value = "/selectone")
    public String cartGET(){
        return "/4/like_selectone";
    }
    
    // @PostMapping(value = "/selectlist")
    // public String cartPOST(
    //     @RequestParam(name="btn") String btn,
	// 	@RequestParam(name = "code") long code,
	// 	@RequestParam(name = "cnt") long cnt ){

    //     String em = (String) httpSession.getAttribute("M_EMAIL");
	// 	if(em == null){ // 로그인 되지 않았다면

	// 		// 로그인 하고 되돌아 올 수 있도록 url 정보를 세션에 저장 => but, 모든 게시판에 찾아서 넣어야 함. 번거로움. 스프링의 기능을 쓰기
	// 		// 공통으로 짜야 하는 것은 컨트롤러를 통과하는 통로에 쓰기. 컨트롤러에 진입할껀데 이럴때는 여기 지나가세요. ex)aop
	// 		// 쇼핑몰에서 장바구니 넣고 나서 로그인 하고 나서 다시 홈으로 돌아오면 구매욕구를 떨어지게 함

	// 		return "redirect:/member/login";
	// 	}
	// 	if(btn.equals("찜")){
			
	// 		// 로그인 되었다면
	// 		CartDTO cart = new CartDTO();
	// 		cart.setCcnt(cnt); // 수량
	// 		cart.setIcode(code); //물품코드
	// 		cMapper.insertCartOne(cart);

	// 		return "redirect:/shop/cartlist";

	// 	}
	// 	else if(btn.equals("주문하기")){

	// 		BuyDTO buy = new BuyDTO();
	// 		buy.setBcnt(cnt);
	// 		buy.setIcode(code);
	// 		buy.setUemail(em);

	// 		bMapper.insertBuyOne(buy);
	// 		return "redirect:/shop/buylist";
	// 	}
	// 	return "redirect:/";
            
    // }

    // // 원래는 member에 넣어야 함. 마이페이지.
	// // 3개 join (MEMBER, ITEM, BUY ) => BUYLIST_VIEW 생성
	// // view-> 조회만 가능한 테이블. insert,update,delete 이런건 원래 테이블에서 가능
	// @GetMapping(value="/buylist")
	// public String buylistGET(Model model) {
	// 	String em = (String) httpSession.getAttribute("M_EMAIL");
	// 	if(em == null){ // 로그인 되지 않았다면
	// 		return "redirect:/member/login";
	// 	}

	// 	// 로그인 되었을 때
	// 	List< Map <String, Object> > list = bMapper.selectBuyListMap(em);
	// 	model.addAttribute("list", list);
	
	// 	return "/shop/buylist";
	// }
    
}
