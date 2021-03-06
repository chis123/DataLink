package com.ucar.datalink.flinker.core.admin.util;

import com.ucar.datalink.flinker.api.zookeeper.ZkClientx;
import com.ucar.datalink.flinker.core.admin.AdminConstants;
import com.ucar.datalink.flinker.core.util.container.CoreConstant;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by yang.wang09 on 2018-11-20 14:18.
 */
public final class DataSourceInitController {

    private static final DataSourceInitController INSTANCE = new DataSourceInitController();

    private DataSourceInitController() {

    }

    public static DataSourceInitController getInstance() {
        return INSTANCE;
    }

    public void initialize() throws IOException {
        //initialize admin.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(CoreConstant.DATAX_ADMIN_CONF));
        final String zkServers = getProperty(properties, AdminConstants.DATAX_ZKSERVERS);
        ZkClientx zkClient = ZkClientx.getZkClientForDatalink(zkServers);
        DataSourceController.getInstance().initialize(properties,zkClient);
    }

    public void destroy() {
        DataSourceController.getInstance().destroy();
    }

    private String getProperty(Properties properties, String key) {
        return StringUtils.trim(properties.getProperty(StringUtils.trim(key)));
    }
}
