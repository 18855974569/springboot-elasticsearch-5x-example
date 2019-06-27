package com.wjx.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Description: 模拟用户信息
 * @Author: dingguo
 * @Date: 2019/6/27 上午10:09
 */
@Document(indexName = UserDocument.INDEX, type = UserDocument.TYPE)
public class UserDocument {

    public static final String INDEX = "users";
    public static final String TYPE = "user";

    /**
     * 用户id
     */
    @Id
    @Field(type = FieldType.keyword, fielddata = true)
    private String id;

    /**
     * 用户姓名
     */
    @Field(type = FieldType.text)
    private String name;

    /**
     * 用户昵称
     */
    @Field(type = FieldType.text, analyzer = "ik")
    private String nickName;

    /**
     * 手机号码
     */
    @Field(type = FieldType.text)
    private String phone;

    /**
     * 性别
     */
    @Field(type = FieldType.Integer)
    private Integer gender;

    /**
     * 省
     */
    @Field(type = FieldType.text)
    private String province;

    /**
     * 市
     */
    @Field(type = FieldType.text)
    private String city;

    /**
     * 区
     */
    @Field(type = FieldType.text)
    private String area;

    /**
     * 详细地址
     */
    @Field(type = FieldType.text, analyzer = "ik")
    private String detailAddress;

    /**
     * 自我介绍
     */
    @Field(type = FieldType.text, analyzer = "ik")
    private String selfIntroduction;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Long)
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
