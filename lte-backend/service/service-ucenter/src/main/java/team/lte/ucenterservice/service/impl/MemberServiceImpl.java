package team.lte.ucenterservice.service.impl;

import team.lte.ucenterservice.entity.Member;
import team.lte.ucenterservice.mapper.MemberMapper;
import team.lte.ucenterservice.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lte
 * @since 2022-04-23
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
