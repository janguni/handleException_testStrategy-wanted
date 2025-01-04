package com.example.payment.repository;

import com.example.payment.repository.dto.PortOneCheckSinglePaymentResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "PortOneOpenFeign", url = "${portone.api.uri}")
public interface PortOneHttpRepository {

	// 결제내역 단건조회 API
	@GetMapping("/payments/{imp_uid}")
	PortOneCheckSinglePaymentResponse getPayment(@RequestHeader String impPid);


	// 결제취소 API
//	public void cancelPayment() {}

}
