package network.user.service;


import network.user.dao.UserDao;
import network.user.domain.User;

public class UserService {
private UserDao userDao=new UserDao();

	
	/**
	 * 注册功能
	 * @param form
	 */
	public void regist(User form) throws Exception{
		
		// 校验用户名
		User user = userDao.findByUsername(form.getName());
		if(user != null) throw new Exception("用户名已被注册！");
		
		// 插入用户到数据库
		userDao.add(form);
	}
	/**
	 * 登录功能
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	public User login(User form) throws Exception {
		/*
		 * 1. 使用name查询，得到User
		 * 2. 如果user为null，抛出异常（用户名不存在）
		 * 3. 比较form和user的密码，若不同，抛出异常（密码错误）
		 * 4. 查看用户的状态，若为false，抛出异常（尚未激活）
		 * 5. 返回user
		 */
		User user = userDao.findByUsername(form.getName());
		if(user == null) throw new Exception("用户名不存在！");
		if(!user.getPassword().equals(form.getPassword()))
			throw new Exception("密码错误！");
		

		return user;
	}
	
}

