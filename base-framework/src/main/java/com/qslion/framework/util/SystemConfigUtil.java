package com.qslion.framework.util;

import com.qslion.framework.bean.SystemConfig;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;


/**
 * @author zhangrg
 * @version V1.0
 * @Title: Qslion商城系统
 * @Package admin
 * @Description: 工具类 - 系统配置
 * @date 2011-05-23
 */
public class SystemConfigUtil {

    public static final String CONFIG_FILE_NAME = "systemConfig.xml";// 系统配置文件名称
    public static final String SYSTEM_CONFIG_CACHE_KEY = "systemConfig";// systemConfig缓存Key

    /**
     * 获取系统配置信息
     *
     * @return SystemConfig对象
     */
    public static SystemConfig getSystemConfig() {
        SystemConfig systemConfig = null;
        File configFile = null;
        Document document = null;
        try {
            String configFilePath = Thread.currentThread().getContextClassLoader().getResource("/").toURI().getPath() + CONFIG_FILE_NAME;
            configFile = new File(configFilePath);
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Node systemNameNode = document.selectSingleNode("/system/systemConfig/systemName");
        Node systemVersionNode = document.selectSingleNode("/system/systemConfig/systemVersion");
        Node systemDescriptionNode = document.selectSingleNode("/system/systemConfig/systemDescription");
        Node isInstalledNode = document.selectSingleNode("/system/systemConfig/isInstalled");
        Node shopNameNode = document.selectSingleNode("/system/systemConfig/shopName");
        Node shopUrlNode = document.selectSingleNode("/system/systemConfig/shopUrl");
        Node shopLogoNode = document.selectSingleNode("/system/systemConfig/shopLogo");
        Node hotSearchNode = document.selectSingleNode("/system/systemConfig/hotSearch");
        Node metaKeywordsNode = document.selectSingleNode("/system/systemConfig/metaKeywords");
        Node metaDescriptionNode = document.selectSingleNode("/system/systemConfig/metaDescription");
        Node addressNode = document.selectSingleNode("/system/systemConfig/address");
        Node phoneNode = document.selectSingleNode("/system/systemConfig/phone");
        Node zipCodeNode = document.selectSingleNode("/system/systemConfig/zipCode");
        Node emailNode = document.selectSingleNode("/system/systemConfig/email");
        Node currencyTypeNode = document.selectSingleNode("/system/systemConfig/currencyType");
        Node currencySignNode = document.selectSingleNode("/system/systemConfig/currencySign");
        Node currencyUnitNode = document.selectSingleNode("/system/systemConfig/currencyUnit");
        Node priceScaleNode = document.selectSingleNode("/system/systemConfig/priceScale");
        Node priceRoundTypeNode = document.selectSingleNode("/system/systemConfig/priceRoundType");
        Node orderScaleNode = document.selectSingleNode("/system/systemConfig/orderScale");
        Node orderRoundTypeNode = document.selectSingleNode("/system/systemConfig/orderRoundType");
        Node certtextNode = document.selectSingleNode("/system/systemConfig/certtext");
        Node storeAlertCountNode = document.selectSingleNode("/system/systemConfig/storeAlertCount");
        Node storeFreezeTimeNode = document.selectSingleNode("/system/systemConfig/storeFreezeTime");
        Node uploadLimitNode = document.selectSingleNode("/system/systemConfig/uploadLimit");
        Node isLoginFailureLockNode = document.selectSingleNode("/system/systemConfig/isLoginFailureLock");
        Node loginFailureLockCountNode = document.selectSingleNode("/system/systemConfig/loginFailureLockCount");
        Node loginFailureLockTimeNode = document.selectSingleNode("/system/systemConfig/loginFailureLockTime");
        Node isRegisterNode = document.selectSingleNode("/system/systemConfig/isRegister");
        Node watermarkImagePathNode = document.selectSingleNode("/system/systemConfig/watermarkImagePath");
        Node watermarkPositionNode = document.selectSingleNode("/system/systemConfig/watermarkPosition");
        Node watermarkAlphaNode = document.selectSingleNode("/system/systemConfig/watermarkAlpha");
        Node bigProductImageWidthNode = document.selectSingleNode("/system/systemConfig/bigProductImageWidth");
        Node bigProductImageHeightNode = document.selectSingleNode("/system/systemConfig/bigProductImageHeight");
        Node smallProductImageWidthNode = document.selectSingleNode("/system/systemConfig/smallProductImageWidth");
        Node smallProductImageHeightNode = document.selectSingleNode("/system/systemConfig/smallProductImageHeight");
        Node thumbnailProductImageWidthNode = document.selectSingleNode("/system/systemConfig/thumbnailProductImageWidth");
        Node thumbnailProductImageHeightNode = document.selectSingleNode("/system/systemConfig/thumbnailProductImageHeight");
        Node defaultBigProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultBigProductImagePath");
        Node defaultSmallProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultSmallProductImagePath");
        Node defaultThumbnailProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultThumbnailProductImagePath");
        Node allowedUploadImageExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadImageExtension");
        Node allowedUploadMediaExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadMediaExtension");
        Node allowedUploadFileExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadFileExtension");
        Node smtpFromMailNode = document.selectSingleNode("/system/systemConfig/smtpFromMail");
        Node smtpHostNode = document.selectSingleNode("/system/systemConfig/smtpHost");
        Node smtpPortNode = document.selectSingleNode("/system/systemConfig/smtpPort");
        Node smtpUsernameNode = document.selectSingleNode("/system/systemConfig/smtpUsername");
        Node smtpPasswordNode = document.selectSingleNode("/system/systemConfig/smtpPassword");
        Node pointTypeNode = document.selectSingleNode("/system/systemConfig/pointType");
        Node pointScaleNode = document.selectSingleNode("/system/systemConfig/pointScale");
        Node captchaImagePathNode = document.selectSingleNode("/system/systemConfig/captchaImagePath");

        systemConfig = new SystemConfig();
        systemConfig.setSystemName(systemNameNode.getText());
        systemConfig.setSystemVersion(systemVersionNode.getText());
        systemConfig.setSystemDescription(systemDescriptionNode.getText());
        systemConfig.setIsInstalled(Boolean.valueOf(isInstalledNode.getText()));
        systemConfig.setShopName(shopNameNode.getText());
        systemConfig.setShopUrl(shopUrlNode.getText());
        systemConfig.setShopLogo(shopLogoNode.getText());
        systemConfig.setHotSearch(hotSearchNode.getText());
        systemConfig.setMetaKeywords(metaKeywordsNode.getText());
        systemConfig.setMetaDescription(metaDescriptionNode.getText());
        systemConfig.setAddress(addressNode.getText());
        systemConfig.setPhone(phoneNode.getText());
        systemConfig.setZipCode(zipCodeNode.getText());
        systemConfig.setEmail(emailNode.getText());
        systemConfig.setCurrencyType(SystemConfig.CurrencyType.valueOf(currencyTypeNode.getText()));
        systemConfig.setCurrencySign(currencySignNode.getText());
        systemConfig.setCurrencyUnit(currencyUnitNode.getText());
        systemConfig.setPriceScale(Integer.valueOf(priceScaleNode.getText()));
        systemConfig.setPriceRoundType(SystemConfig.RoundType.valueOf(priceRoundTypeNode.getText()));
        systemConfig.setOrderScale(Integer.valueOf(orderScaleNode.getText()));
        systemConfig.setOrderRoundType(SystemConfig.RoundType.valueOf(orderRoundTypeNode.getText()));
        systemConfig.setCerttext(certtextNode.getText());
        systemConfig.setStoreAlertCount(Integer.valueOf(storeAlertCountNode.getText()));
        systemConfig.setStoreFreezeTime(SystemConfig.StoreFreezeTime.valueOf(storeFreezeTimeNode.getText()));
        systemConfig.setUploadLimit(Integer.valueOf(uploadLimitNode.getText()));
        systemConfig.setIsLoginFailureLock(Boolean.valueOf(isLoginFailureLockNode.getText()));
        systemConfig.setLoginFailureLockCount(Integer.valueOf(loginFailureLockCountNode.getText()));
        systemConfig.setLoginFailureLockTime(Integer.valueOf(loginFailureLockTimeNode.getText()));
        systemConfig.setIsRegister(Boolean.valueOf(isRegisterNode.getText()));
        systemConfig.setWatermarkImagePath(watermarkImagePathNode.getText());
        systemConfig.setWatermarkPosition(SystemConfig.WatermarkPosition.valueOf(watermarkPositionNode.getText()));
        systemConfig.setWatermarkAlpha(Integer.valueOf(watermarkAlphaNode.getText()));
        systemConfig.setBigProductImageWidth(Integer.valueOf(bigProductImageWidthNode.getText()));
        systemConfig.setBigProductImageHeight(Integer.valueOf(bigProductImageHeightNode.getText()));
        systemConfig.setSmallProductImageWidth(Integer.valueOf(smallProductImageWidthNode.getText()));
        systemConfig.setSmallProductImageHeight(Integer.valueOf(smallProductImageHeightNode.getText()));
        systemConfig.setThumbnailProductImageWidth(Integer.valueOf(thumbnailProductImageWidthNode.getText()));
        systemConfig.setThumbnailProductImageHeight(Integer.valueOf(thumbnailProductImageHeightNode.getText()));
        systemConfig.setDefaultBigProductImagePath(defaultBigProductImagePathNode.getText());
        systemConfig.setDefaultSmallProductImagePath(defaultSmallProductImagePathNode.getText());
        systemConfig.setDefaultThumbnailProductImagePath(defaultThumbnailProductImagePathNode.getText());
        systemConfig.setAllowedUploadImageExtension(allowedUploadImageExtensionNode.getText());
        systemConfig.setAllowedUploadMediaExtension(allowedUploadMediaExtensionNode.getText());
        systemConfig.setAllowedUploadFileExtension(allowedUploadFileExtensionNode.getText());
        systemConfig.setSmtpFromMail(smtpFromMailNode.getText());
        systemConfig.setSmtpHost(smtpHostNode.getText());
        systemConfig.setSmtpPort(Integer.valueOf(smtpPortNode.getText()));
        systemConfig.setSmtpUsername(smtpUsernameNode.getText());
        systemConfig.setSmtpPassword(smtpPasswordNode.getText());
        systemConfig.setPointType(SystemConfig.PointType.valueOf(pointTypeNode.getText()));
        systemConfig.setPointScale(Double.valueOf(pointScaleNode.getText()));
        systemConfig.setCaptchaImagePath(captchaImagePathNode.getText());
        return systemConfig;
    }

    /**
     * 更新系统配置信息
     *
     * @param systemConfig SystemConfig对象
     */
    public static void update(SystemConfig systemConfig) {
        File configFile = null;
        Document document = null;
        try {
            String configFilePath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + CONFIG_FILE_NAME;
            configFile = new File(configFilePath);
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        Element systemConfigElement = rootElement.element("systemConfig");
        Node systemNameNode = document.selectSingleNode("/system/systemConfig/systemName");
        Node systemVersionNode = document.selectSingleNode("/system/systemConfig/systemVersion");
        Node systemDescriptionNode = document.selectSingleNode("/system/systemConfig/systemDescription");
        Node isInstalledNode = document.selectSingleNode("/system/systemConfig/isInstalled");
        Node shopNameNode = document.selectSingleNode("/system/systemConfig/shopName");
        Node shopUrlNode = document.selectSingleNode("/system/systemConfig/shopUrl");
        Node shopLogoNode = document.selectSingleNode("/system/systemConfig/shopLogo");
        Node hotSearchNode = document.selectSingleNode("/system/systemConfig/hotSearch");
        Node metaKeywordsNode = document.selectSingleNode("/system/systemConfig/metaKeywords");
        Node metaDescriptionNode = document.selectSingleNode("/system/systemConfig/metaDescription");
        Node addressNode = document.selectSingleNode("/system/systemConfig/address");
        Node phoneNode = document.selectSingleNode("/system/systemConfig/phone");
        Node zipCodeNode = document.selectSingleNode("/system/systemConfig/zipCode");
        Node emailNode = document.selectSingleNode("/system/systemConfig/email");
        Node currencyTypeNode = document.selectSingleNode("/system/systemConfig/currencyType");
        Node currencySignNode = document.selectSingleNode("/system/systemConfig/currencySign");
        Node currencyUnitNode = document.selectSingleNode("/system/systemConfig/currencyUnit");
        Node priceScaleNode = document.selectSingleNode("/system/systemConfig/priceScale");
        Node priceRoundTypeNode = document.selectSingleNode("/system/systemConfig/priceRoundType");
        Node orderScaleNode = document.selectSingleNode("/system/systemConfig/orderScale");
        Node orderRoundTypeNode = document.selectSingleNode("/system/systemConfig/orderRoundType");
        Node certtextNode = document.selectSingleNode("/system/systemConfig/certtext");
        Node storeAlertCountNode = document.selectSingleNode("/system/systemConfig/storeAlertCount");
        Node storeFreezeTimeNode = document.selectSingleNode("/system/systemConfig/storeFreezeTime");
        Node uploadLimitNode = document.selectSingleNode("/system/systemConfig/uploadLimit");
        Node isLoginFailureLockNode = document.selectSingleNode("/system/systemConfig/isLoginFailureLock");
        Node loginFailureLockCountNode = document.selectSingleNode("/system/systemConfig/loginFailureLockCount");
        Node loginFailureLockTimeNode = document.selectSingleNode("/system/systemConfig/loginFailureLockTime");
        Node isRegisterNode = document.selectSingleNode("/system/systemConfig/isRegister");
        Node watermarkImagePathNode = document.selectSingleNode("/system/systemConfig/watermarkImagePath");
        Node watermarkPositionNode = document.selectSingleNode("/system/systemConfig/watermarkPosition");
        Node watermarkAlphaNode = document.selectSingleNode("/system/systemConfig/watermarkAlpha");
        Node bigProductImageWidthNode = document.selectSingleNode("/system/systemConfig/bigProductImageWidth");
        Node bigProductImageHeightNode = document.selectSingleNode("/system/systemConfig/bigProductImageHeight");
        Node smallProductImageWidthNode = document.selectSingleNode("/system/systemConfig/smallProductImageWidth");
        Node smallProductImageHeightNode = document.selectSingleNode("/system/systemConfig/smallProductImageHeight");
        Node thumbnailProductImageWidthNode = document.selectSingleNode("/system/systemConfig/thumbnailProductImageWidth");
        Node thumbnailProductImageHeightNode = document.selectSingleNode("/system/systemConfig/thumbnailProductImageHeight");
        Node defaultBigProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultBigProductImagePath");
        Node defaultSmallProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultSmallProductImagePath");
        Node defaultThumbnailProductImagePathNode = document.selectSingleNode("/system/systemConfig/defaultThumbnailProductImagePath");
        Node allowedUploadImageExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadImageExtension");
        Node allowedUploadMediaExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadMediaExtension");
        Node allowedUploadFileExtensionNode = document.selectSingleNode("/system/systemConfig/allowedUploadFileExtension");
        Node smtpFromMailNode = document.selectSingleNode("/system/systemConfig/smtpFromMail");
        Node smtpHostNode = document.selectSingleNode("/system/systemConfig/smtpHost");
        Node smtpPortNode = document.selectSingleNode("/system/systemConfig/smtpPort");
        Node smtpUsernameNode = document.selectSingleNode("/system/systemConfig/smtpUsername");
        Node smtpPasswordNode = document.selectSingleNode("/system/systemConfig/smtpPassword");
        Node pointTypeNode = document.selectSingleNode("/system/systemConfig/pointType");
        Node pointScaleNode = document.selectSingleNode("/system/systemConfig/pointScale");

        if (systemNameNode == null) {
            systemNameNode = systemConfigElement.addElement("systemName");
        }
        if (systemVersionNode == null) {
            systemVersionNode = systemConfigElement.addElement("systemVersion");
        }
        if (systemDescriptionNode == null) {
            systemDescriptionNode = systemConfigElement.addElement("systemDescription");
        }
        if (isInstalledNode == null) {
            isInstalledNode = systemConfigElement.addElement("isInstalled");
        }
        if (shopNameNode == null) {
            shopNameNode = systemConfigElement.addElement("shopName");
        }
        if (shopUrlNode == null) {
            shopUrlNode = systemConfigElement.addElement("shopUrl");
        }
        if (shopLogoNode == null) {
            shopLogoNode = systemConfigElement.addElement("shopLogo");
        }
        if (hotSearchNode == null) {
            hotSearchNode = systemConfigElement.addElement("hotSearch");
        }
        if (metaKeywordsNode == null) {
            metaKeywordsNode = systemConfigElement.addElement("metaKeywords");
        }
        if (metaDescriptionNode == null) {
            metaDescriptionNode = systemConfigElement.addElement("metaDescription");
        }
        if (addressNode == null) {
            addressNode = systemConfigElement.addElement("address");
        }
        if (phoneNode == null) {
            phoneNode = systemConfigElement.addElement("phone");
        }
        if (zipCodeNode == null) {
            zipCodeNode = systemConfigElement.addElement("zipCode");
        }
        if (emailNode == null) {
            emailNode = systemConfigElement.addElement("email");
        }
        if (currencyTypeNode == null) {
            currencyTypeNode = systemConfigElement.addElement("currencyType");
        }
        if (currencySignNode == null) {
            currencySignNode = systemConfigElement.addElement("currencySign");
        }
        if (currencyUnitNode == null) {
            currencyUnitNode = systemConfigElement.addElement("currencyUnit");
        }
        if (priceScaleNode == null) {
            priceScaleNode = systemConfigElement.addElement("priceScale");
        }
        if (priceRoundTypeNode == null) {
            priceRoundTypeNode = systemConfigElement.addElement("priceRoundType");
        }
        if (orderScaleNode == null) {
            orderScaleNode = systemConfigElement.addElement("orderScale");
        }
        if (orderRoundTypeNode == null) {
            orderRoundTypeNode = systemConfigElement.addElement("orderRoundType");
        }
        if (certtextNode == null) {
            certtextNode = systemConfigElement.addElement("certtext");
        }
        if (storeAlertCountNode == null) {
            storeAlertCountNode = systemConfigElement.addElement("storeAlertCount");
        }
        if (storeFreezeTimeNode == null) {
            storeFreezeTimeNode = systemConfigElement.addElement("storeFreezeTime");
        }
        if (uploadLimitNode == null) {
            uploadLimitNode = systemConfigElement.addElement("uploadLimit");
        }
        if (isLoginFailureLockNode == null) {
            isLoginFailureLockNode = systemConfigElement.addElement("isLoginFailureLock");
        }
        if (loginFailureLockCountNode == null) {
            loginFailureLockCountNode = systemConfigElement.addElement("loginFailureLockCount");
        }
        if (loginFailureLockTimeNode == null) {
            loginFailureLockTimeNode = systemConfigElement.addElement("loginFailureLockTime");
        }
        if (isRegisterNode == null) {
            isRegisterNode = systemConfigElement.addElement("isRegister");
        }
        if (watermarkImagePathNode == null) {
            watermarkImagePathNode = systemConfigElement.addElement("watermarkImagePath");
        }
        if (watermarkPositionNode == null) {
            watermarkPositionNode = systemConfigElement.addElement("watermarkPosition");
        }
        if (watermarkAlphaNode == null) {
            watermarkAlphaNode = systemConfigElement.addElement("watermarkAlpha");
        }
        if (bigProductImageWidthNode == null) {
            bigProductImageWidthNode = systemConfigElement.addElement("bigProductImageWidth");
        }
        if (bigProductImageHeightNode == null) {
            bigProductImageHeightNode = systemConfigElement.addElement("bigProductImageHeight");
        }
        if (smallProductImageWidthNode == null) {
            smallProductImageWidthNode = systemConfigElement.addElement("smallProductImageWidth");
        }
        if (smallProductImageHeightNode == null) {
            smallProductImageHeightNode = systemConfigElement.addElement("smallProductImageHeight");
        }
        if (thumbnailProductImageWidthNode == null) {
            thumbnailProductImageWidthNode = systemConfigElement.addElement("thumbnailProductImageWidth");
        }
        if (thumbnailProductImageHeightNode == null) {
            thumbnailProductImageHeightNode = systemConfigElement.addElement("thumbnailProductImageHeight");
        }
        if (defaultBigProductImagePathNode == null) {
            defaultBigProductImagePathNode = systemConfigElement.addElement("defaultBigProductImagePath");
        }
        if (defaultSmallProductImagePathNode == null) {
            defaultSmallProductImagePathNode = systemConfigElement.addElement("defaultSmallProductImagePath");
        }
        if (defaultThumbnailProductImagePathNode == null) {
            defaultThumbnailProductImagePathNode = systemConfigElement.addElement("defaultThumbnailProductImagePath");
        }
        if (allowedUploadImageExtensionNode == null) {
            allowedUploadImageExtensionNode = systemConfigElement.addElement("allowedUploadImageExtension");
        }
        if (allowedUploadMediaExtensionNode == null) {
            allowedUploadMediaExtensionNode = systemConfigElement.addElement("allowedUploadMediaExtension");
        }
        if (allowedUploadFileExtensionNode == null) {
            allowedUploadFileExtensionNode = systemConfigElement.addElement("allowedUploadFileExtension");
        }
        if (smtpFromMailNode == null) {
            smtpFromMailNode = systemConfigElement.addElement("smtpFromMail");
        }
        if (smtpHostNode == null) {
            smtpHostNode = systemConfigElement.addElement("smtpHost");
        }
        if (smtpPortNode == null) {
            smtpPortNode = systemConfigElement.addElement("smtpPort");
        }
        if (smtpUsernameNode == null) {
            smtpUsernameNode = systemConfigElement.addElement("smtpUsername");
        }
        if (smtpPasswordNode == null) {
            smtpPasswordNode = systemConfigElement.addElement("smtpPassword");
        }
        if (pointTypeNode == null) {
            pointTypeNode = systemConfigElement.addElement("pointType");
        }
        if (pointScaleNode == null) {
            pointScaleNode = systemConfigElement.addElement("pointScale");
        }

        systemNameNode.setText(systemConfig.getSystemName());
        systemVersionNode.setText(systemConfig.getSystemVersion());
        systemDescriptionNode.setText(systemConfig.getSystemDescription());
        isInstalledNode.setText(systemConfig.getIsInstalled().toString());
        shopNameNode.setText(systemConfig.getShopName());
        shopUrlNode.setText(StringUtils.removeEnd(systemConfig.getShopUrl(), "/"));
        shopLogoNode.setText(systemConfig.getShopLogo());
        hotSearchNode.setText(systemConfig.getHotSearch());
        metaKeywordsNode.setText(systemConfig.getMetaKeywords());
        metaDescriptionNode.setText(systemConfig.getMetaDescription());
        addressNode.setText(systemConfig.getAddress());
        phoneNode.setText(systemConfig.getPhone());
        zipCodeNode.setText(systemConfig.getZipCode());
        emailNode.setText(systemConfig.getEmail());
        currencyTypeNode.setText(String.valueOf(systemConfig.getCurrencyType()));
        currencySignNode.setText(systemConfig.getCurrencySign());
        currencyUnitNode.setText(systemConfig.getCurrencyUnit());
        priceScaleNode.setText(String.valueOf(systemConfig.getPriceScale()));
        priceRoundTypeNode.setText(String.valueOf(systemConfig.getPriceRoundType()));
        orderScaleNode.setText(String.valueOf(systemConfig.getOrderScale()));
        orderRoundTypeNode.setText(String.valueOf(systemConfig.getOrderRoundType()));
        certtextNode.setText(systemConfig.getCerttext());
        storeAlertCountNode.setText(String.valueOf(systemConfig.getStoreAlertCount()));
        storeFreezeTimeNode.setText(String.valueOf(systemConfig.getStoreFreezeTime()));
        uploadLimitNode.setText(String.valueOf(systemConfig.getUploadLimit()));
        isLoginFailureLockNode.setText(String.valueOf(systemConfig.getIsLoginFailureLock()));
        loginFailureLockCountNode.setText(String.valueOf(systemConfig.getLoginFailureLockCount()));
        loginFailureLockTimeNode.setText(String.valueOf(systemConfig.getLoginFailureLockTime()));
        isRegisterNode.setText(String.valueOf(systemConfig.getIsRegister()));
        watermarkImagePathNode.setText(systemConfig.getWatermarkImagePath());
        watermarkPositionNode.setText(String.valueOf(systemConfig.getWatermarkPosition()));
        watermarkAlphaNode.setText(String.valueOf(systemConfig.getWatermarkAlpha()));
        bigProductImageWidthNode.setText(String.valueOf(systemConfig.getBigProductImageWidth()));
        bigProductImageHeightNode.setText(String.valueOf(systemConfig.getBigProductImageHeight()));
        smallProductImageWidthNode.setText(String.valueOf(systemConfig.getSmallProductImageWidth()));
        smallProductImageHeightNode.setText(String.valueOf(systemConfig.getSmallProductImageHeight()));
        thumbnailProductImageWidthNode.setText(String.valueOf(systemConfig.getThumbnailProductImageWidth()));
        thumbnailProductImageHeightNode.setText(String.valueOf(systemConfig.getThumbnailProductImageHeight()));
        defaultBigProductImagePathNode.setText(systemConfig.getDefaultBigProductImagePath());
        defaultSmallProductImagePathNode.setText(systemConfig.getDefaultSmallProductImagePath());
        defaultThumbnailProductImagePathNode.setText(systemConfig.getDefaultThumbnailProductImagePath());
        allowedUploadImageExtensionNode.setText(systemConfig.getAllowedUploadImageExtension());
        allowedUploadMediaExtensionNode.setText(systemConfig.getAllowedUploadMediaExtension());
        allowedUploadFileExtensionNode.setText(systemConfig.getAllowedUploadFileExtension());
        smtpFromMailNode.setText(systemConfig.getSmtpFromMail());
        smtpHostNode.setText(systemConfig.getSmtpHost());
        if (systemConfig.getSmtpPort() == null) {
            smtpPortNode.setText("25");
        } else {
            smtpPortNode.setText(String.valueOf(systemConfig.getSmtpPort()));
        }
        smtpUsernameNode.setText(systemConfig.getSmtpUsername());
        smtpPasswordNode.setText(systemConfig.getSmtpPassword());
        pointTypeNode.setText(systemConfig.getPointType().toString());
        pointScaleNode.setText(systemConfig.getPointScale().toString());
        try {
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
            outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
            outputFormat.setIndent(true);// 设置是否缩进
            outputFormat.setIndent("	");// 以TAB方式实现缩进
            outputFormat.setNewlines(true);// 设置是否换行
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(configFile), outputFormat);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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