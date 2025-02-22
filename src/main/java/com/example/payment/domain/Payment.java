package com.example.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String impUid;
	private Integer amount;
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_Id")
	private Member member;

	// 결제 승인
	@Builder
	public Payment(String impUid, Integer amount) {
		this.impUid = impUid;
		this.amount = amount;
		this.status = "paid";
	}
}
