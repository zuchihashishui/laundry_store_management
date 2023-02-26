package laundry.com.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StoreService extends CommonService {
	
	@Autowired
	private SqlSession sqlSession;
	
	public LinkedHashMap<String, Object> getStoreByStoreId(Integer storeId) {
		LinkedHashMap<String, Object> store = sqlSession.selectOne("Store.getStoreByStoreId", storeId);
		return store;
	}
	
	public void insert(Map<String, Object> store) {
		store.put("createdId", currentUser().getUsername());
		sqlSession.insert("Store.insert", store);
	}
	
	public void update(Map<String, Object> store) {
		store.put("updatedId", currentUser().getUsername());
		sqlSession.update("Store.update", store);
	}
	
	public void delete(Integer storeId) {
		sqlSession.delete("Store.delete", storeId);
	}

}
