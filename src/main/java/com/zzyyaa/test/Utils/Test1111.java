package com.zzyyaa.test.Utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class Test1111 {
	//最好从配置文件读取
	private static String url = "jdbc:sqlserver://10.110.200.86:1433;DatabaseName=test";
	private static String username = "sa";
	private static String password = "Fiber@19";

	private static Connection getCoon() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// 关闭连接
	public static void close(Object o) {
		if (o == null) {
			return;
		}
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection) {
			Connection c = (Connection) o;
			try {
				if (!c.isClosed()) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 獲取数据库元数据
	 */
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = getCoon();
			DatabaseMetaData data = connection.getMetaData();
			System.out.println(data.getDefaultTransactionIsolation());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(connection);
		}

	}
}
