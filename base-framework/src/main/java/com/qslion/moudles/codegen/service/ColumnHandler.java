package com.qslion.moudles.codegen.service;

import com.qslion.moudles.codegen.entity.DdColumn;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2018/11/10 10:31.
 */
public interface ColumnHandler {

    /**
     * 对生成的列模型的后续处理操作。譬如可以做一些不同编程语言的数据列的类型转换操作
     *
     * @param columnModel DdColumn
     */
    void handle(DdColumn columnModel);
}
