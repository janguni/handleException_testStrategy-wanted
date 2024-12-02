package com.example.payment.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PortOnePaymentResponse {
	private String imp_uid;
	private String pg_tid;
	private String apply_num;
	private Integer amount;
	private String status;
	private Integer started_at;
	private Integer paid_at;
	private Integer failed_at;
	private Integer cancelled_at;
	private String fail_reason;
}
