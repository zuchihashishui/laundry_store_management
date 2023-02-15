package laundry.com.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommenderService {
	
	@Autowired
	private SqlSession sqlSession;

	public List<LinkedHashMap<String, String>> getRecomendsByUserId(Integer userId) {
		List<LinkedHashMap<String, String>> recommends = sqlSession.selectList("Recommender.getRecommendsByUserId", userId);
		return recommends;
	}
	
	public void insert(Map<String, String> recommender) {
		sqlSession.insert("Recommender.insert", recommender);
	}
	
}
