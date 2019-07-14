package top.westyle.manager.config.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建用来保存数据源名字的线程安全的类。通过ThreadLocal栈封闭方式保证线程安全
 */
public class DataSourceContextHolder {
    public static final String Mater = "master";
    public static final String Slave1 = "slave1";
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    //存放数据源id
    public static List<String> dataSourceIds = new ArrayList<String>();
    /**
     * 获取当前数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 设置数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource){
        contextHolder.set(dataSource);
    }

    /**
     * 清除数据源
     */
    public static void clear() {
        contextHolder.remove();
    }
    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
