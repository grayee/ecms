package com.qslion.moudles.codegen.service;

import java.io.IOException;

/**
 * 代码生成接口
 *
 * @author Gray.Z
 * @date 2018/11/4 20:23.
 */
public interface CodeCreator {

    /**
     * 生成实体模型
     *
     * @param tableName 表名称
     * @throws IOException io
     */
    void generateEntity(String tableName) throws IOException;

    /**
     * 生成Repository
     *
     * @param tableName 表名称
     * @throws IOException io
     */
    void generateDao(String tableName) throws IOException;

    /**
     * 生成Service
     *
     * @param tableName 表名称
     * @throws IOException io
     */
    void generateService(String tableName) throws IOException;

    /**
     * 生成ServiceImpl
     *
     * @param tableName 表名称
     * @throws IOException io
     */
    void generateServiceImpl(String tableName) throws IOException;

    /**
     * 生成Controller
     *
     * @param tableName 表名称
     * @throws IOException io
     */
    void generateController(String tableName) throws IOException;
}
