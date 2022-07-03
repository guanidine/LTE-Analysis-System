package team.lte.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import team.lte.aclservice.entity.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface IndexService {

    Map<String, Object> getUserInfo(String username);

    List<JSONObject> getAsyncRoutes(String username);

    void register(UserVO userVO);

}
