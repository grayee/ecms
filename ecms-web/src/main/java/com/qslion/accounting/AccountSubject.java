package com.qslion.accounting;

/**
 * 会计科目表
 *
 * @author Gray.Z
 * @date 2019/8/22 20:50.
 */
public class AccountSubject {

    /**
     * 科目编码
     */
    private String subjectCode;
    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 科目简称
     */
    private String subjectShortName;

    /**
     * 科目英文名称
     */
    private String subjectEnName;
    /**
     * 余额方向
     */
    private String balanceDir;

    /**
     * 4222科目层级：一级科目(4位)，二级科目，三级科目，四级科目
     */
    private Integer subjectLevel;

    /**
     * 科目类型
     */
    private String subject_type;

    /**
     * https://baijiahao.baidu.com/s?id=1620159106860707000&wfr=spider&for=pc
     * 帐簿类型：普通多栏、增值税多栏、三栏
     */
    private Integer acctBookType;
}
