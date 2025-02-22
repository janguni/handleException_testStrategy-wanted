package com.example.payment.service.command.dto;

import com.example.payment.repository.dto.PortOneCheckSinglePaymentResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * PortOne에서 조회한 결제 단건 QueryResult
 */
@Getter
@ToString
@AllArgsConstructor
public class PortOneSinglePaymentQueryResult {
	private String paymentNo;		// 결제 번호
	private String orderNo;			// 주문 번호
	private int amount;				// 결제 금액        (default: 0)
	private int cancelAmount;		// 취소 금액		  (default: 0)
	private String currentCode;		// 결제통화 구분코드
	private String status;			// 결제 상태

	public PortOneSinglePaymentQueryResult(PortOneCheckSinglePaymentResDto resDto) {
		this.paymentNo = resDto.getImp_uid();
		this.orderNo = resDto.getMerchant_uid();
		this.amount = resDto.getAmount()==null ? 0 : resDto.getAmount();
		this.cancelAmount = resDto.getCancelAmount()==null ? 0 : resDto.getCancelAmount();
		this.currentCode = resDto.getCurrency();
		this.status = resDto.getStatus();
	}
}
