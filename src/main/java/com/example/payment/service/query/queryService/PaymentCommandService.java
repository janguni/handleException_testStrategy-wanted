package com.example.payment.service.query.queryService;

import static com.example.payment.common.exception.ErrorMessage.VALIDATE_PAYMENT_ERROR;

import com.example.payment.common.exception.ValidateException;
import com.example.payment.domain.Order;
import com.example.payment.domain.Payment;
import com.example.payment.repository.MemberRepository;
import com.example.payment.repository.OrderRepository;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.dto.PortOneCheckSinglePaymentResDto;
import com.example.payment.service.command.commandService.PaymentQueryService;
import com.example.payment.service.command.dto.PaymentApproveCommand;
import com.example.payment.service.command.dto.PortOneSinglePaymentQueryResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 결제 CommandService
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentCommandService {
	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;
	private final PaymentQueryService paymentQueryService;

	/**
	 * 결제 승인
	 * <p>
	 *     포트원에서 받은 결제 승인 이벤트 정보를 받아 처리 한다.<br>
	 *     결제 단건 조회와 주문 조회을 통해, 검증을 한 후 결제를 저장 한다.<br>
	 * </p>
	 * @param command (결제 번호, 주문 번호)
	 */
	public void approvePayment(PaymentApproveCommand command) {

		// 결제 단건 조회
		PortOneSinglePaymentQueryResult singlePayment = paymentQueryService.getPaymentFromPortOne(command.getPaymentNo());

		// 주문 조회
		Order order = orderRepository.findById(command.getOrderNo()).orElseThrow(() -> new ValidateException(VALIDATE_PAYMENT_ERROR));

		// 주문 검증
		if (!order.isPossiblePayment(singlePayment.getAmount())) {
			throw new ValidateException(VALIDATE_PAYMENT_ERROR);
		}

		// 결제 저장
		Payment payment = new Payment(singlePayment.getOrderNo(), 4000);
		paymentRepository.save(payment);
	}
}
