package com.d2c.flame.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.d2c.common.api.page.PageModel;
import com.d2c.common.api.page.PageResult;
import com.d2c.common.base.exception.BusinessException;
import com.d2c.flame.controller.base.BaseController;
import com.d2c.member.dto.MemberDto;
import com.d2c.member.model.Member;
import com.d2c.member.model.MemberInfo;
import com.d2c.member.service.MemberService;
import com.d2c.order.model.RedPacketsItem;
import com.d2c.order.model.RedPacketsItem.BusinessTypeEnum;
import com.d2c.order.mongo.model.BargainPriceDO;
import com.d2c.order.mongo.service.BargainHelpService;
import com.d2c.order.mongo.service.BargainPriceService;
import com.d2c.order.service.RedPacketsItemService;
import com.d2c.order.service.tx.BargainPriceTxService;
import com.d2c.product.search.model.SearcherBargainPromotion;
import com.d2c.product.search.model.SearcherProduct;
import com.d2c.product.search.query.BargainPromotionSearchBean;
import com.d2c.product.search.service.BargainPromotionSearcherService;
import com.d2c.product.search.service.ProductSearcherQueryService;
import com.d2c.product.service.BargainPromotionService;
import com.d2c.util.date.DateUtil;
import com.d2c.util.string.StringUtil;

/**
 * 砍价活动
 * 
 * @author wwn
 *
 */
@Controller
@RequestMapping("/bargain")
public class BargainController extends BaseController {

	@Autowired
	private BargainPromotionService bargainPromotionService;
	@Reference
	private BargainPromotionSearcherService bargainPromotionSearcherService;
	@Autowired
	private BargainPriceService bargainPriceService;
	@Autowired
	private BargainHelpService bargainHelpService;
	@Reference
	private ProductSearcherQueryService productSearcherQueryService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private RedPacketsItemService redPacketsItemService;
	@Reference
	private BargainPriceTxService bargainPriceTxService;

	/**
	 * 砍价活动列表
	 * 
	 * @param searcher
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/promotion/list", method = RequestMethod.GET)
	public String promotionList(BargainPromotionSearchBean searcher, PageModel page, ModelMap model) {
		PageResult<SearcherBargainPromotion> pager = bargainPromotionSearcherService.search(searcher, page);
		model.put("list", pager);
		model.put("searcher", searcher);
		return "product/bargain_list";
	}

	/**
	 * 砍价活动明细
	 * 
	 * @param promotionId
	 * @return
	 */
	@RequestMapping(value = "/detail/{promotionId}", method = RequestMethod.GET)
	public String promotionDetail(@PathVariable("promotionId") Long promotionId, ModelMap model) {
		SearcherBargainPromotion bargainPromotion = bargainPromotionSearcherService.findById(promotionId);
		model.put("bargainPromotion", bargainPromotion);
		return "product/bargain_product";
	}

	/**
	 * 创建个人砍价
	 * 
	 * @param promotionId
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Long promotionId, ModelMap model) {
		MemberInfo memberInfo = this.getLoginMemberInfo();
		BargainPriceDO bargainPriceDTO = bargainPriceService.create(memberInfo, promotionId);
		model.put("bargainPriceDO", bargainPriceDTO);
		model.put("isBind", true);
		return "product/bargain_product_detail";
	}

	/**
	 * 获得我的砍价清单
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/mine/list", method = RequestMethod.GET)
	public String myBargainList(PageModel page, ModelMap model) {
		MemberInfo memberInfo = this.getLoginMemberInfo();
		PageResult<BargainPriceDO> pager = bargainPriceService.findMyBargainList(memberInfo.getId(), page);
		model.put("pager", pager);
		return "";
	}

	/**
	 * 个人砍价明细
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/personal/{id}", method = RequestMethod.GET)
	public String myBargainDetail(@PathVariable("id") String id, ModelMap model) {
		// 朋友点进来也可以，所以没有登录设置
		BargainPriceDO bargainPriceDO = bargainPriceService.getBargainPriceById(id);
		if (bargainPriceDO == null) {
			throw new BusinessException("砍价活动不存在");
		}
		model.put("bargainPriceDO", bargainPriceDO);
		// 砍价活动明细
		SearcherProduct product = productSearcherQueryService
				.findById(bargainPriceDO.getBargain().getProductId().toString());
		model.put("product", product);
		return "product/bargain_product_detail";
	}

	/**
	 * 好友帮忙砍价
	 * 
	 * @param bargainPriceId
	 * @return
	 */
	@RequestMapping(value = "/add/help", method = RequestMethod.POST)
	public String addBargainHelp(String bargainPriceId, ModelMap model) {
		// 游客登录
		MemberDto memberDto = this.getLoginDto();
		if (memberDto.isD2c()) {
			Member member = memberService.findWeixinMember(memberDto.getId());
			if (member != null) {
				memberDto.setUnionId(member.getUnionId());
				memberDto.setSource(member.getSource());
			}
		}
		if (StringUtil.isNotBlank(memberDto.getUnionId())) {
			Integer limit = 3;
			if (limit.intValue() > 0) {
				int totalCount = 0;
				try {
					totalCount = bargainHelpService.countByUnionId(memberDto.getUnionId(),
							DateUtil.getStartOfDay(new Date()), DateUtil.getEndOfDay(new Date()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (totalCount >= limit.intValue()) {
					throw new BusinessException("每人一天只能帮助" + limit.intValue() + "次，您已超过上限了哦！");
				}
			}
			BigDecimal price = bargainPriceService.addBargainHelp(memberDto.getUnionId(), bargainPriceId,
					memberDto.getNickname(), memberDto.getHeadPic(), memberDto.getId());
			if (price != null && price.compareTo(new BigDecimal(0)) > 0) {
				BigDecimal ratio = new BigDecimal("0.025");
				model.put("result", 1);
				model.put("price", price);
				model.put("ratio", ratio);
				return "";
			}
		}
		model.put("result", 0);
		return "";

	}

	/**
	 * 开抢提醒
	 * 
	 * @param promotionId
	 * @return
	 */
	@RequestMapping(value = "/remind/{promotionId}", method = RequestMethod.GET)
	public String remindMe(@PathVariable("promotionId") Long promotionId, ModelMap model) {
		MemberInfo memberInfo = this.getLoginMemberInfo();
		SearcherBargainPromotion bargainPromotion = bargainPromotionSearcherService.findById(promotionId);
		if (bargainPromotion == null) {
			throw new BusinessException("该砍价商品已下架，看看其他砍价商品吧！");
		}
		int result = bargainPromotionService.doRemind(memberInfo.getId(), bargainPromotion);
		model.put("result", result);
		return "";

	}

	/**
	 * 获得红包
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/receive/redpackets", method = RequestMethod.POST)
	public String getRedPackets(ModelMap model) {
		MemberInfo memberInfo = this.getLoginMemberInfo();
		Member member = memberService.findWeixinMember(memberInfo.getId());
		MemberDto dto = new MemberDto();
		BeanUtils.copyProperties(memberInfo, dto);
		if (member != null) {
			dto.setUnionId(member.getUnionId());
		}
		int result = bargainPriceTxService.doRedPackets(dto);
		model.put("status", result);
		return "";
	}

	/**
	 * 是否已领取红包
	 * 
	 * @return
	 */
	@RequestMapping(value = "/got/redpackets", method = RequestMethod.GET)
	public String hasGetRedPackets(ModelMap model) {
		MemberInfo memberInfo = this.getLoginMemberInfo();
		List<RedPacketsItem> list = redPacketsItemService.findByTypeAndMember(memberInfo.getId(),
				BusinessTypeEnum.BARGAIN.name());
		model.put("redPackets", list.size() > 0 ? 1 : 0);
		return "";
	}

	/**
	 * 商品活动跑马灯
	 * 
	 * @param promotionId
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newest/{promotionId}", method = RequestMethod.GET)
	public String latestPrice(@PathVariable("promotionId") Long promotionId, PageModel page, ModelMap model) {
		List<BargainPriceDO> list = bargainPriceService.findLatest(promotionId, page, null);
		JSONArray array = new JSONArray();
		for (BargainPriceDO priceDo : list) {
			if (priceDo.getPrice() < priceDo.getOriginalPrice()) {
				JSONObject obj = new JSONObject();
				obj.put("nickname", priceDo.getNickname());
				obj.put("headPic", priceDo.getPic());
				obj.put("price", priceDo.getPrice());
				array.add(obj);
			}
		}
		model.put("list", array);
		return "";
	}

}
