package network.search.dao;

import java.sql.SQLException;

import network.user.domain.User;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class SearchDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User searchByUsername(String username) {
		try {
			String sql = "select * from user where name=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
}
