package com.qslion.web.accounting.entity;

import com.qslion.framework.entity.BaseEntity;
import com.qslion.framework.enums.CurrencyType;
import com.qslion.web.accounting.enums.AccountDir;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 会计凭证分录
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "account_voucher_entry")
public class AccountVoucherEntry extends BaseEntity<Long> {


    private AccountVoucher voucher;
    /**
     * 会计科目
     */
    private AccountSubject subject;
    /**
     * 摘要信息
     */
    private String remark;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 借贷方向
     */
    private AccountDir amtDir;

    /**
     * 币别
     */
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(name = "VOUCHER_ID")
    public AccountVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(AccountVoucher voucher) {
        this.voucher = voucher;
    }

    @OneToOne
    @JoinColumn(name = "SUBJECT_CODE", referencedColumnName = "SUBJECT_CODE")
    public AccountSubject getSubject() {
        return subject;
    }

    public void setSubject(AccountSubject subject) {
        this.subject = subject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountDir getAmtDir() {
        return amtDir;
    }

    public void setAmtDir(AccountDir amtDir) {
        this.amtDir = amtDir;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
