package pl.wsikora.successbudget.v3.liability.ui.edit;

import pl.wsikora.successbudget.v3.liability.application.command.LiabilityAttributes;

import java.math.BigDecimal;


public class LiabilityEditForm implements LiabilityAttributes {

	static final String F_NAME = "name";
	static final String F_DESCRIPTION = "description";
	static final String F_CREDITOR = "creditor";
	static final String F_TOTAL_AMOUNT = "totalAmount";
	static final String F_TOTAL_CURRENCY = "totalCurrency";
	static final String F_MINIMUM_MONTHLY_AMOUNT = "minimumMonthlyAmount";
	static final String F_MINIMUM_MONTHLY_CURRENCY = "minimumMonthlyCurrency";
	static final String F_ALREADY_REPAID_AMOUNT = "alreadyRepaidAmount";
	static final String F_ALREADY_REPAID_CURRENCY = "alreadyRepaidCurrency";

	private Long id;
	private Long userId;
	private String name;
	private String description;
	private String creditor;
	private BigDecimal totalAmount;
	private Integer totalCurrency;
	private BigDecimal minimumMonthlyAmount;
	private Integer minimumMonthlyCurrency;
	private BigDecimal alreadyRepaidAmount;
	private Integer alreadyRepaidCurrency;

	public LiabilityEditForm(Long userId) {

		this.userId = userId;
	}

	@Override
	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	@Override
	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	@Override
	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@Override
	public String getCreditor() {

		return creditor;
	}

	public void setCreditor(String creditor) {

		this.creditor = creditor;
	}

	@Override
	public BigDecimal getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;
	}

	public Integer getTotalCurrency() {

		return totalCurrency;
	}

	public void setTotalCurrency(Integer totalCurrency) {

		this.totalCurrency = totalCurrency;
	}

	@Override
	public BigDecimal getMinimumMonthlyAmount() {

		return minimumMonthlyAmount;
	}

	public void setMinimumMonthlyAmount(BigDecimal minimumMonthlyAmount) {

		this.minimumMonthlyAmount = minimumMonthlyAmount;
	}

	@Override
	public Integer getMinimumMonthlyCurrency() {

		return minimumMonthlyCurrency;
	}

	public void setMinimumMonthlyCurrency(Integer minimumMonthlyCurrency) {

		this.minimumMonthlyCurrency = minimumMonthlyCurrency;
	}

	@Override
	public BigDecimal getAlreadyRepaidAmount() {

		return alreadyRepaidAmount;
	}

	public void setAlreadyRepaidAmount(BigDecimal alreadyRepaidAmount) {

		this.alreadyRepaidAmount = alreadyRepaidAmount;
	}

	@Override
	public Integer getAlreadyRepaidCurrency() {

		return alreadyRepaidCurrency;
	}

	public void setAlreadyRepaidCurrency(Integer alreadyRepaidCurrency) {

		this.alreadyRepaidCurrency = alreadyRepaidCurrency;
	}
}
