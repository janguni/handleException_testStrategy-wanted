package com.example.payment.repository;

import com.example.payment.repository.dto.PortOneCheckSinglePaymentResDto;

import com.example.payment.repository.dto.PortOnePaymentCancelRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PortOneOpenFeign", url = "${portone.api.uri}")
public interface PortOneHttpRepository {

	// 결제 내역 단건 조회 API
	@GetMapping(value = "/payments/{imp_uid}", consumes = "application/json")
	PortOneCheckSinglePaymentResDto getPayment(@PathVariable("imp_uid") String impPid);

	// 결제 취소 API
	@PostMapping(value = "/payments/cancel")
	PortOneCheckSinglePaymentResDto cancelPayment(@RequestBody PortOnePaymentCancelRequest request);

}
