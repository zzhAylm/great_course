package com.graduate.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduate.base.exception.MyException;
import com.graduate.ucenter.pojo.Member;
import com.graduate.ucenter.mapper.MemberMapper;
import com.graduate.ucenter.pojo.vo.LoginInfoVo;
import com.graduate.ucenter.pojo.vo.LoginVo;
import com.graduate.ucenter.pojo.vo.RegisterVo;
import com.graduate.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.ucenter.utils.MD5;
import com.graduate.utils.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 张紫韩
 * @since 2021-11-16
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 会员登录
     *
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password)) {
            throw new MyException(500, "参数不完整");
        }
        //获取会员
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if (null == member) {
            throw new MyException(500, "会员不存在");
        }
        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new MyException(500, "密码错误");
        }
        //校验是否被禁用
        if (member.getIsDisabled()) {
            throw new MyException(500, "校验被禁止");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }


    /**
     * 会员注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {

        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new MyException(500, "参数不全");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(mobileCode)) {
            throw new MyException(500, "验证码错误");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if (count > 0) {
            throw new MyException(500, "手机号已存在");
        }
        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        Member member = baseMapper.selectById(memberId);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }


    @Override
    public Member getByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        Member member = baseMapper.selectOne(queryWrapper);
        return member;
    }
}
