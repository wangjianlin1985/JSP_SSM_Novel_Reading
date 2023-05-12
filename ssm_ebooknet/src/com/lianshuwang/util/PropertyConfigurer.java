// 
// 
// 

package com.lianshuwang.util;

import java.util.Hashtable;
import org.springframework.beans.BeansException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Properties;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import java.util.Map;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer
{
    private static Map<String, String> propertyMap;
    
    protected void processProperties(final ConfigurableListableBeanFactory beanFactoryToProcess, final Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        PropertyConfigurer.propertyMap = new HashMap<String, String>();
        for (final Object key : ((Hashtable<Object, V>)props).keySet()) {
            final String keyStr = key.toString();
            final String value = props.getProperty(keyStr);
            PropertyConfigurer.propertyMap.put(keyStr, value);
        }
    }
    
    public static Object getProperty(final String key) {
        return PropertyConfigurer.propertyMap.get(key);
    }
}
