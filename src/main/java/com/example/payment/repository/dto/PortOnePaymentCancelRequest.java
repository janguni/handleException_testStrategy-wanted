package com.example.payment.repository.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortOnePaymentCancelRequest {
	private String imp_uid;			// 포트원 거래번호
	private String merchant_uid;
	private Integer amount;
	private Integer tax_free;
	private Integer vat_amount;
	private Integer checksum;
	private String reason;
	private String refund_holder;
	private String refund_bank;
	private String refund_account;
	private String refund_tel;
	private Boolean retain_promotion;
	private List extra[];
}
