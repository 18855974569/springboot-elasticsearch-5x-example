package com.wjx.restapi;

import com.google.common.collect.Lists;
import com.wjx.document.UserDocument;
import com.wjx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * @Description:
 * @Author: dingguo
 * @Date: 2019/6/27 上午10:14
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDocument> findUserById(@PathVariable("id") String id) {
        Optional<UserDocument> document = userRepository.findById(id);
        return new ResponseEntity<>(document.get(), HttpStatus.OK);
    }

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity findAll() {
        Iterable<UserDocument> documents = userRepository.findAll();
        return new ResponseEntity<>(Lists.newArrayList(documents), HttpStatus.OK);
    }

    /**
     * 新增用户信息
     *
     * @param userDocument
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDocument> savetUser(@RequestBody UserDocument userDocument) {
        UserDocument document = userRepository.save(userDocument);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    /**
     * 更新用户信息
     *
     * @param userDocument
     * @return
     */
    @PutMapping
    public ResponseEntity<UserDocument> updateUser(@RequestBody UserDocument userDocument) {
        UserDocument document = userRepository.save(userDocument);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("删除成功：" + id, HttpStatus.OK);
    }

}
