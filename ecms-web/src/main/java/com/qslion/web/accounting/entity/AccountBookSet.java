package com.qslion.web.accounting.entity;

import com.qslion.framework.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 账套（set of books SOB）
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
@Entity
@Table(name = "account_book_set")
public class AccountBookSet extends BaseEntity<Long> {

    //https://blog.csdn.net/qq_32423845/article/details/88384263

}
