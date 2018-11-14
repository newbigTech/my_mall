package com.d2c.order.support;

public class LogisticsResource {

	public static String convertName(String logisticsName) {
		if (logisticsName != null) {
			if (logisticsName.contains("EMS")) {
				return "ems";
			} else if (logisticsName.contains("圆通")) {
				return "yuantong";
			} else if (logisticsName.contains("韵达")) {
				return "yunda";
			} else if (logisticsName.contains("申通")) {
				return "shentong";
			} else if (logisticsName.contains("中通")) {
				return "zhongtong";
			} else if (logisticsName.contains("德邦")) {
				return "debangwuliu";
			} else if (logisticsName.contains("汇通")) {
				return "huitongkuaidi";
			} else if (logisticsName.contains("京东")) {
				return "jd";
			} else if (logisticsName.contains("快捷")) {
				return "kuaijiesudi";
			} else if (logisticsName.contains("天天")) {
				// 天天快递之前订单有，后期不提供
				return "tiantian";
			} else if (logisticsName.contains("顺丰")) {
				return "shunfeng";
			} else {
				return "other";
			}
		}
		return "other";
	}

	public static String convertCode(String code) {
		if (code != null) {
			switch (code) {
			case "ems":
				return "EMS";
			case "yuantong":
				return "圆通快递";
			case "yunda":
				return "韵达快递";
			case "shentong":
				return "申通快递";
			case "zhongtong":
				return "中通快递";
			case "huitongkuaidi":
				return "百世汇通";
			case "debangwuliu":
				return "德邦快递";
			case "jd":
				return "京东快递";
			case "kuaijiesudi":
				return "快捷快递";
			case "tiantian":
				return "天天快递";
			case "shunfeng":
				return "顺丰快递";
			default:
				return "其他";
			}
		}
		return "其他";
	}

	public static String convertStatus(Integer status) {
		if (status != null)
			switch (status) {
			case 1:
				return "已揽件";
			case 2:
				return "疑难";
			case 3:
				return "已签收";
			case 4:
				return "退签";
			case 5:
				return "同城派送中";
			case 6:
				return "退回";
			case 7:
				return "转单";
			default:
				return "在途中";
			}
		return "在途中";
	}

	public static String convertToTel(String code) {
		if (code != null) {
			switch (code) {
			case "ems":
				return "11183";
			case "yuantong":
				return "95554";
			case "yunda":
				return "95546";
			case "shentong":
				return "95543";
			case "zhongtong":
				return "95311";
			case "huitongkuaidi":
				return "400-956-5656";
			case "debangwuliu":
				return "95353";
			case "shunfeng":
				return "95338";
			case "kuaijiesudi":
				return "4008-333-666";
			case "jd":
				return "400-603-3600";
			case "tiantian":
				return "400-188-8888";
			default:
				return "";
			}
		}
		return "";
	}

	public static boolean vaildLogistics(String com) {
		if (com.equals("ems") || com.equals("yuantong") || com.equals("yunda") || com.equals("shentong")
				|| com.equals("zhongtong") || com.equals("huitongkuaidi") || com.equals("debangwuliu")
				|| com.equals("jd") || com.equals("kuaijiesudi") || com.equals("tiantian") || com.equals("shunfeng")) {
			return true;
		}
		return false;
	}

}
