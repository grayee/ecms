package com.qslion.framework.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import com.qslion.framework.bean.TreeNode;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * ecms
 *
 * @author Gray.Z
 * @date 2019/1/17 0017.
 */
public class CopyUtils {

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[emptyNames.size()]);
    }

    public static void copyProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void main(String[] args) {
        TreeNode tn = new TreeNode();
        tn.setId(111L);
        tn.setText("222");

        TreeNode tn2 = new TreeNode();
        CopyUtils.copyProperties(tn,tn2);
        System.out.println(JSONUtils.writeValueAsString(CopyUtils.getNullPropertyNames(tn)));
        Assert.assertEquals("test","222",tn2.getText());
    }
}
