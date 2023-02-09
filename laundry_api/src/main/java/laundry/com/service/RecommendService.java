package laundry.com.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendService {
	
	@Autowired
	private SqlSession sqlSession;

	public List<LinkedHashMap<String, String>> getRecomendsByCustomerId(Integer customerId) {
		List<LinkedHashMap<String, String>> recommends = sqlSession.selectList("Recommend.getRecommendsByCustomerId", customerId);
		return recommends;
	}
	
}
