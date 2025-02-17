package com.example.payment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")	// 예약어 회피
public class Order {

	@Id
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private Integer totalAmount;

	private String items;

	private String status;

	// 결제 승인 전 금액 일치 확인
	public boolean isPossiblePayment(Integer amount) {

		if (amount == null || this.totalAmount == null) {
			return false;
		}

		return this.totalAmount.equals(amount);

	}


}
