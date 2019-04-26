package com.qslion.framework.util;

import com.qslion.framework.bean.SystemConfig;
import com.qslion.moudles.ddic.entity.DictDataValue;
import com.qslion.moudles.ddic.service.DictionaryService;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;


/**
 * 系统配置工具类
 *
 * @author Gray.Z
 * @date 2018/11/16.
 */
public class SystemConfigUtil {


    /**
     * systemConfig 数据字典编码
     */
    private static final String SYSTEM_CONFIG_DICT_CODE = "SYSTEM_CONFIG";

    /**
     * 获取系统配置信息
     *
     * @return SystemConfig对象
     */
    public static SystemConfig getSystemConfig() {
        SystemConfig systemConfig = new SystemConfig();
        DictionaryService dictionaryService = (DictionaryService) SpringUtil.getBean("dictionaryServiceImpl");
        List<DictDataValue> dictDataValues = dictionaryService.findByCode(SYSTEM_CONFIG_DICT_CODE);
        if (CollectionUtils.isNotEmpty(dictDataValues)) {
            for (DictDataValue dictDataValue : dictDataValues) {
                switch (dictDataValue.getCode()) {
                    case "Is_Login_Failure_Lock":
                        systemConfig.setIsLoginFailureLock(Boolean.valueOf(dictDataValue.getValue()));
                        break;
                    case "login_failure_lock_count":
                        systemConfig.setLoginFailureLockCount(Integer.valueOf(dictDataValue.getValue()));
                        break;
                    case "login_failure_lock_time":
                        systemConfig.setLoginFailureLockTime(Integer.valueOf(dictDataValue.getValue()));
                        break;
                    default:
                        break;
                }
            }
        }
        return systemConfig;
    }


    /**
     * 获取精度处理后的商品价格
     */
    public static BigDecimal getPriceScaleBigDecimal(BigDecimal price) {
        Integer priceScale = getSystemConfig().getPriceScale();
        SystemConfig.RoundType priceRoundType = getSystemConfig().getPriceRoundType();
        if (priceRoundType == SystemConfig.RoundType.roundHalfUp) {
            return price.setScale(priceScale, BigDecimal.ROUND_HALF_UP);
        } else if (priceRoundType == SystemConfig.RoundType.roundUp) {
            return price.setScale(priceScale, BigDecimal.ROUND_UP);
        } else {
            return price.setScale(priceScale, BigDecimal.ROUND_DOWN);
        }
    }

    /**
     * 获取精度处理后的订单额
     */
    public static BigDecimal getOrderScaleBigDecimal(BigDecimal orderAmount) {
        Integer orderScale = getSystemConfig().getOrderScale();
        SystemConfig.RoundType orderRoundType = getSystemConfig().getOrderRoundType();
        if (orderRoundType == SystemConfig.RoundType.roundHalfUp) {
            return orderAmount.setScale(orderScale, BigDecimal.ROUND_HALF_UP);
        } else if (orderRoundType == SystemConfig.RoundType.roundUp) {
            return orderAmount.setScale(orderScale, BigDecimal.ROUND_UP);
        } else {
            return orderAmount.setScale(orderScale, BigDecimal.ROUND_DOWN);
        }
    }

    /**
     * 获取商品价格货币格式字符串
     */
    public static String getPriceCurrencyFormat() {
        Integer priceScale = getSystemConfig().getPriceScale();
        String currencySign = getSystemConfig().getCurrencySign();
        if (priceScale == 0) {
            return currencySign + "#0";
        } else if (priceScale == 1) {
            return currencySign + "#0.0";
        } else if (priceScale == 2) {
            return currencySign + "#0.00";
        } else if (priceScale == 3) {
            return currencySign + "#0.000";
        } else if (priceScale == 4) {
            return currencySign + "#0.0000";
        } else {
            return currencySign + "#0.00000";
        }
    }

    /**
     * 获取商品价格货币格式字符串（包含货币单位）
     */
    public static String getPriceUnitCurrencyFormat() {
        Integer priceScale = getSystemConfig().getPriceScale();
        String currencySign = getSystemConfig().getCurrencySign();
        String currencyUnit = getSystemConfig().getCurrencyUnit();
        if (priceScale == 0) {
            return currencySign + "#0" + currencyUnit;
        } else if (priceScale == 1) {
            return currencySign + "#0.0" + currencyUnit;
        } else if (priceScale == 2) {
            return currencySign + "#0.00" + currencyUnit;
        } else if (priceScale == 3) {
            return currencySign + "#0.000" + currencyUnit;
        } else if (priceScale == 4) {
            return currencySign + "#0.0000" + currencyUnit;
        } else {
            return currencySign + "#0.00000" + currencyUnit;
        }
    }

    /**
     * 获取订单价格货币格式字符串
     */
    public static String getOrderCurrencyFormat() {
        Integer orderScale = getSystemConfig().getOrderScale();
        String currencySign = getSystemConfig().getCurrencySign();
        if (orderScale == 0) {
            return currencySign + "#0";
        } else if (orderScale == 1) {
            return currencySign + "#0.0";
        } else if (orderScale == 2) {
            return currencySign + "#0.00";
        } else if (orderScale == 3) {
            return currencySign + "#0.000";
        } else if (orderScale == 4) {
            return currencySign + "#0.0000";
        } else {
            return currencySign + "#0.00000";
        }
    }

    /**
     * 获取订单价格货币格式字符串（包含货币单位）
     */
    public static String getOrderUnitCurrencyFormat() {
        Integer orderScale = getSystemConfig().getOrderScale();
        String currencySign = getSystemConfig().getCurrencySign();
        String currencyUnit = getSystemConfig().getCurrencyUnit();
        if (orderScale == 0) {
            return currencySign + "#0" + currencyUnit;
        } else if (orderScale == 1) {
            return currencySign + "#0.0" + currencyUnit;
        } else if (orderScale == 2) {
            return currencySign + "#0.00" + currencyUnit;
        } else if (orderScale == 3) {
            return currencySign + "#0.000" + currencyUnit;
        } else if (orderScale == 4) {
            return currencySign + "#0.0000" + currencyUnit;
        } else {
            return currencySign + "#0.00000" + currencyUnit;
        }
    }

}