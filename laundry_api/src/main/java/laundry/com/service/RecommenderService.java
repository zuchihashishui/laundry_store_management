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
public class RecommenderService extends CommonService {

	@Autowired
	private SqlSession sqlSession;

	public List<LinkedHashMap<String, String>> getRecomends() {
		List<LinkedHashMap<String, String>> recommends = sqlSession.selectList("Recommender.getRecommendsByCreatedBy",
				currentUser().get("phoneNumber"));
		return recommends;
	}

	public void insert(Map<String, Object> recommender) {
		recommender.put("createdBy", currentUser().get("phoneNumber"));
		sqlSession.insert("Recommender.insert", recommender);
	}

}
