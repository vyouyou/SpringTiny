package com.qiyouyou.helper;

import com.google.common.collect.Lists;
import com.qiyouyou.domain.util.CollenctionUtil;
import com.qiyouyou.domain.util.PropsUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseHelper {
    static Logger log = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final BasicDataSource DATA_SOURCE;

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        String driver = conf.getProperty("jdbc.driver");
        String url = conf.getProperty("jdbc.url");
        String username = conf.getProperty("jdbc.username");
        String password = conf.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(driver);
        DATA_SOURCE.setUrl(url);
        DATA_SOURCE.setUsername(username);
        DATA_SOURCE.setPassword(password);
    }

    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("get connection failure",e);
            }finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    public static void closeConnection() {
        Connection coon = CONNECTION_HOLDER.get();
        if (coon == null) return;
        try {
            coon.close();
        } catch (SQLException e) {
            log.info(e.getMessage());
        }
    }

    public static <T> List<T> queryList(Class<T> tClass, String sql, Object... params) {
        Connection conn = getConnection();
        List<T> entityList = Lists.newArrayList();
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(tClass), params);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return entityList;
    }

    public static <T> T queryEntity(Class<T> tClass, String sql, Object... params) {
        T entity;
        Connection conn = getConnection();
        try {
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(tClass), params);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return entity;
    }

    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        Connection connection = getConnection();
        try {
            rows = QUERY_RUNNER.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }


    public static String getTableName(Class clazz) {
        return clazz.getSimpleName().toLowerCase();
    }

    public static <T> boolean deleteEntity(Class<T> tClass, long id) {
        String sql = "DELETE FROM " + getTableName(tClass) + "WHERE id=?";
        return executeUpdate(sql, id) == 1;
    }

    public static <T> boolean insertEntity(Class<T> tClass,Map<String,Object> fieldMap){
        if(fieldMap == null){
            log.error("fieldmap can not be empty");
            return false;
        }
        String sql = "INSERT INTO " + getTableName(tClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        fieldMap.keySet().stream().forEach(key->{
            columns.append(key).append(", ");
            values.append("?,");
        });
        columns.replace(columns.lastIndexOf(", "),columns.length(),")");
        values.replace(values.lastIndexOf("?,"),values.length(),"?)");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();
        return executeUpdate(sql,params) == 1;
    }
}
