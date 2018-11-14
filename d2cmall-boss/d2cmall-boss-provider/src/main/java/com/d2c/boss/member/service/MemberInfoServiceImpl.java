package com.d2c.boss.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.d2c.boss.member.dao.MemberInfoMapper;
import com.d2c.boss.member.dto.MemberInfoDto;
import com.d2c.boss.member.model.Member;
import com.d2c.boss.member.model.MemberInfo;
import com.d2c.boss.sys.support.ProQuery;
import com.d2c.boss.sys.util.BeanUtils;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.mybatis.service.ListServiceImpl;

@Service("memberInfoService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MemberInfoServiceImpl extends ListServiceImpl<MemberInfo> implements MemberInfoService {

	@Autowired
	private MemberInfoMapper memberInfoMapper;
	@Autowired
	private MemberService memberService;

	@Override
	public PageResult<MemberInfo> findMemberInfosByQuery(PageModel page, ProQuery query) {
		PageResult<MemberInfo> pager = new PageResult<MemberInfo>(page);
		int count = memberInfoMapper.countMemberInfosByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		}
		pager.setList(memberInfoMapper.findMemberInfosByQuery(page, query));
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public MemberInfo insert(MemberInfo memberInfo) {
		MemberInfo result = this.save(memberInfo);
		return result;
	}

	@Override
	public PageResult<MemberInfoDto> findMemberDtosByQuery(PageModel page, ProQuery query) {
		PageResult<MemberInfoDto> pager = new PageResult<MemberInfoDto>(page);
		int count = memberInfoMapper.countMemberInfosByQuery(query);
		pager.setTotalCount(count);
		if (count <= 0) {
			return pager;
		} else {
			List<MemberInfoDto> memberInfoDtos = new ArrayList<MemberInfoDto>();
			List<MemberInfo> memberInfos = memberInfoMapper.findMemberInfosByQuery(page, query);
			for (MemberInfo memberInfo : memberInfos) {
				MemberInfoDto dto = new MemberInfoDto();
				List<Member> members = memberService.findMembersByLoginCode(memberInfo.getLoginCode());
				BeanUtils.copyProperties(memberInfo, dto);
				dto.setMembers(members);
				memberInfoDtos.add(dto);
			}
			pager.setList(memberInfoDtos);
		}
		return pager;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateDevices() {
		memberInfoMapper.updateIos();
		memberInfoMapper.updateAndroid();
		memberInfoMapper.updatePc();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateOnlineStatus() {
		memberInfoMapper.updateOnlineStatus();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertOnlineMemberInfo() {
		memberInfoMapper.insertOnlineMemberInfo();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void insertOfflineMemberInfo() {
		memberInfoMapper.insertOfflineMemberInfo();
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void updateOfflineStatus() {
		memberInfoMapper.updateOfflineStatus();
	}

}
