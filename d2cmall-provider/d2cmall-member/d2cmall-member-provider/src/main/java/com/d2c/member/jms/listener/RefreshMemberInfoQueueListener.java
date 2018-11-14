package com.d2c.member.jms.listener;

import javax.jms.MapMessage;
import javax.jms.Message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.d2c.common.mq.enums.MqEnum;
import com.d2c.common.mq.listener.AbsMqListener;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.service.CommentService;
import com.d2c.member.service.ConsultService;
import com.d2c.member.service.MemberAttentionService;
import com.d2c.member.service.MemberCollectionService;
import com.d2c.member.service.MemberFollowService;
import com.d2c.member.service.MemberInfoService;
import com.d2c.member.service.MemberLikeService;
import com.d2c.member.service.MemberShareCommentService;
import com.d2c.member.service.MemberShareService;
import com.d2c.member.service.PartnerService;
import com.d2c.member.service.PiliLiveService;

@Component
public class RefreshMemberInfoQueueListener extends AbsMqListener {

	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberShareService memberShareService;
	@Autowired
	private MemberShareCommentService memberShareCommentService;
	@Autowired
	private MemberCollectionService memberCollectionService;
	@Autowired
	private MemberAttentionService memberAttentionService;
	@Autowired
	private MemberFollowService memberFollowService;
	@Autowired
	private MemberLikeService memberLikeService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ConsultService consultService;
	@Autowired
	private PiliLiveService piliLiveService;
	@Autowired
	private PartnerService partnerService;

	@Override
	public void onMessage(Message message) {
		MapMessage mapMsg = (MapMessage) message;
		try {
			Long memberInfoId = mapMsg.getLong("memberInfoId");
			String headPic = mapMsg.getString("headPic");
			String nickName = mapMsg.getString("nickName");
			if (memberInfoId != null && (StringUtils.isNotBlank(headPic) || StringUtils.isNotBlank(nickName))) {
				this.process(memberInfoId, headPic, nickName);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void process(Long memberInfoId, String headPic, String nickName) {
		MemberInfo memberInfo = memberInfoService.findById(memberInfoId);
		if ((headPic != null && headPic.equals(memberInfo.getHeadPic()))
				|| (nickName != null && nickName.equals(memberInfo.getNickname()))) {
			memberAttentionService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			memberCollectionService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			memberFollowService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			memberLikeService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			memberShareService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			memberShareCommentService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			commentService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			consultService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			piliLiveService.doRefreshHeadPic(memberInfoId, headPic, nickName);
			partnerService.doRefreshHeadPic(memberInfoId, headPic, nickName);
		}
	}

	@Override
	public MqEnum getMqEnum() {
		return MqEnum.REFRESH_MEMBER;
	}

}
