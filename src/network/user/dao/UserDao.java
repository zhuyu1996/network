package network.user.dao;

import java.sql.SQLException;

import network.user.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;



import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		try {
			String sql = "select * from user where name=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

/**
 * 插入User
 * @param user
 */
public void add(User user) {
	try {
		String sql = "insert into user values(?,?,?,?)";
		Object[] params = {user.getPassword(),user.getName(),user.getGrade(),user.getDescrible()};
		qr.update(sql, params);
	} catch(SQLException e) {
		throw new RuntimeException(e);
	}
}

}