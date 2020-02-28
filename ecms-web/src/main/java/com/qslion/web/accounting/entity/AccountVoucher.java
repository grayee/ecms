package com.qslion.web.accounting.entity;

import com.qslion.framework.bean.DisplayField;
import com.qslion.framework.entity.BaseEntity;
import com.qslion.web.accounting.enums.AccountDir;
import com.qslion.web.accounting.enums.VoucherType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 会计凭证
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "account_voucher")
public class AccountVoucher extends BaseEntity<Long> {

    /**
     * 会计分录
     */
    private List<AccountVoucherEntry> voucherEntries;

    /**
     * 业务日期
     */
    @DisplayField(id = 1, title = "日期")
    private Date businessDate;

    /**
     * 借方金额合计
     */
    @DisplayField(id = 2, title = "借方合计")
    private BigDecimal debitAmtSum;

    /**
     * 贷方金额合计
     */
    @DisplayField(id = 3, title = "贷方合计")
    private BigDecimal creditAmtSum;

    /**
     * 余额
     */
    @DisplayField(id = 4, title = "余额")
    private BigDecimal balance;

    /**
     * 余额方向
     */
    @DisplayField(id = 5, title = "余额方向")
    private AccountDir balanceDir;

    /**
     * 凭证字
     */
    @DisplayField(id = 6, title = "凭证字")
    private VoucherType voucherType;

    /**
     * 凭证号
     */
    @DisplayField(id = 7, title = "凭证号")
    private String voucherNo;

    /**
     * 制单人
     */
    @DisplayField(id = 8, title = "制单人")
    private String maker;
    /**
     * 备注信息
     */
    private String remark;


    @OneToMany
    @JoinColumn(name = "VOUCHER_ID")
    public List<AccountVoucherEntry> getVoucherEntries() {
        return voucherEntries;
    }

    public void setVoucherEntries(List<AccountVoucherEntry> voucherEntries) {
        this.voucherEntries = voucherEntries;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public BigDecimal getDebitAmtSum() {
        return debitAmtSum;
    }

    public void setDebitAmtSum(BigDecimal debitAmtSum) {
        this.debitAmtSum = debitAmtSum;
    }

    public BigDecimal getCreditAmtSum() {
        return creditAmtSum;
    }

    public void setCreditAmtSum(BigDecimal creditAmtSum) {
        this.creditAmtSum = creditAmtSum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Enumerated
    public AccountDir getBalanceDir() {
        return balanceDir;
    }

    public void setBalanceDir(AccountDir balanceDir) {
        this.balanceDir = balanceDir;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
