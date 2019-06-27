package com.wjx.repository;

import com.wjx.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @Author: dingguo
 * @Date: 2019/6/27 上午10:08
 */
public interface UserRepository extends ElasticsearchRepository<UserDocument, String> {


}
