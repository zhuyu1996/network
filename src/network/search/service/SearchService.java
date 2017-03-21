package network.search.service;

import network.search.dao.SearchDao;
import network.user.domain.User;

public class SearchService {
	private SearchDao searchdao=new SearchDao();
	
	/**
	 * 按姓名查询
	 */
	public void search(User form1)throws Exception{
	User user = searchdao.searchByUsername(form1.getName());
	if (user ==null)throw new Exception("用户不存在！");
	
}
}