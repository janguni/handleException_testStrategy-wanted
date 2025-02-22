package com.example.payment.service.command.commandService;

import com.example.payment.common.exception.ErrorMessage;
import com.example.payment.common.exception.LinkException;
import com.example.payment.repository.PortOneHttpRepository;
import com.example.payment.repository.dto.PortOneCheckSinglePaymentResDto;
import com.example.payment.service.command.dto.PortOneSinglePaymentQueryResult;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentQueryService {

	private final PortOneHttpRepository portOneHttpRepository;

	/**
	 * 결제 내역 단건 조회
	 * @param paymentNo	결제번호
	 * @return (주문 번호, 결제 금액, 취소 금액, 결제통화 구분코드, 결제 상태)
	 */
	public PortOneSinglePaymentQueryResult getPaymentFromPortOne(String paymentNo) {
		try {
			// 결제 내역 단건 조회 호출
			return new PortOneSinglePaymentQueryResult(
				portOneHttpRepository.getPayment(paymentNo)
			);
		} catch (FeignException exception) {
			log.error("결제 내역 단건 조회 연동 실패");
			throw new LinkException(ErrorMessage.LINK_ERROR);
		}
	}

}
