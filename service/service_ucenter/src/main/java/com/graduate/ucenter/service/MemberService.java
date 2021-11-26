package com.graduate.ucenter.service;

import com.graduate.ucenter.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.ucenter.pojo.vo.LoginInfoVo;
import com.graduate.ucenter.pojo.vo.LoginVo;
import com.graduate.ucenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-16
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    Member getByOpenid(String openid);
}
