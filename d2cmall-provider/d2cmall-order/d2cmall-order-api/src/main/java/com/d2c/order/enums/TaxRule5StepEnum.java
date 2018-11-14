package com.d2c.order.enums;

import java.math.BigDecimal;

public enum TaxRule5StepEnum {

	STEP1(0, 3500) {
		@Override
		public BigDecimal formula(BigDecimal count) {
			return new BigDecimal(0);
		}
	},
	STEP2(3500, 5000) {
		@Override
		public BigDecimal formula(BigDecimal count) {
			return new BigDecimal((count.doubleValue() - 3500) * 0.03).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	},
	STEP3(5000, 8000) {
		@Override
		public BigDecimal formula(BigDecimal count) {
			return new BigDecimal((count.doubleValue() - 3500) * 0.1 - 105).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	},
	STEP4(8000, 10000) {
		@Override
		public BigDecimal formula(BigDecimal count) {
			return new BigDecimal((count.doubleValue() - 3500) * 0.2 - 555).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	},
	STEP5(10000, Integer.MAX_VALUE) {
		@Override
		public BigDecimal formula(BigDecimal count) {
			return new BigDecimal(count.doubleValue() * 0.0536).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	};

	private int openStart;
	private int closeEnd;

	TaxRule5StepEnum(int openStart, int closeEnd) {
		this.openStart = openStart;
		this.closeEnd = closeEnd;
	}

	public BigDecimal formula(BigDecimal count) {
		return new BigDecimal(0);
	}

	/**
	 * 计算提现金额应缴的税款
	 * 
	 * @param count
	 * @return
	 */
	public static BigDecimal calculateTax(BigDecimal count) {
		for (TaxRule5StepEnum item : TaxRule5StepEnum.values()) {
			if (count.compareTo(new BigDecimal(item.getOpenStart())) > 0
					&& count.compareTo(new BigDecimal(item.getCloseEnd())) <= 0) {
				return item.formula(count);
			}
		}
		return new BigDecimal(0);
	}

	public int getOpenStart() {
		return openStart;
	}

	public void setOpenStart(int openStart) {
		this.openStart = openStart;
	}

	public int getCloseEnd() {
		return closeEnd;
	}

	public void setCloseEnd(int closeEnd) {
		this.closeEnd = closeEnd;
	}

}
