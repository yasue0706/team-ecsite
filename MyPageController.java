package jp.co.internous.plum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.plum.model.domain.MstUser;
import jp.co.internous.plum.model.mapper.MstUserMapper;
import jp.co.internous.plum.model.session.LoginSession;

@Controller
@RequestMapping("/plum/mypage")
public class MyPageController {
	@Autowired
	private MstUserMapper userMapper;
	@Autowired
	private LoginSession loginSession;

	@RequestMapping("/")
	public String index(Model m) {
		// セッションからログイン中のユーザーIDを取得

		MstUser user = userMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());

		// MstUserMapperから該当するユーザー情報を取得
		m.addAttribute("user", user);
		m.addAttribute("loginSession", loginSession);
		// Modelにユーザー情報をセットしてmy_page.htmlに渡す
		return "my_page";
	}
}
