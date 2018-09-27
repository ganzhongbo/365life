package cn.vpclub.sinotrans.sailor.feign.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chentao on 2018/6/6.
 */
@Data
@TableName("user_info")
public class User extends Model<User> {

    /**
     * update by zhanchenggui on 2018/7/9.
     */

    /**
     * 会员id
     */
    @TableId(value = "user_id",type = IdType.ID_WORKER)
    private Long userId;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 会员等级
     */
    private Integer grade;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 证件类型
     */
    @TableField("id_type")
    private Integer idType;
    /**
     * 证件号
     */
    @TableField("id_number")
    private String idNumber;
    /**
     * 密码
     */
    private String password;

    /**
     * 民族
     */
    private String nation;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 出生省份
     */
    private String nativeProvince;
    /**
     * 出生市
     */
    private String nativeCity;
    /**
     * 出生区
     */
    private String nativeArea;
    /**
     * 出生详细地址
     */
    private String nativeDetailsAddr;
    /**
     * 家庭住址省份
     */
    private String familyProvince;
    /**
     * 家庭住址市
     */
    private String familyCity;
    /**
     * 家庭住址区
     */
    private String familyArea;
    /**
     * 家庭详细住址
     */
    private String familyDetailsAddr;
    /**
     * 在册单位
     */
    private String workUnit;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 所学专业
     */
    private String major;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 微信号
     */
    @TableField("wechat_no")
    private String wechatNo;
    /**
     * QQ号
     */
    @TableField("qq_no")
    private String qqNo;
    /**
     * 家属姓名
     */
    @TableField("family_members_name")
    private String familyMembersName;
    /**
     * 家属手机号
     */
    @TableField("family_members_mobile")
    private String familyMembersMobile;
    /**
     * 职务
     */
    private String position;
    /**
     * 航区
     */
    private String navigationArea;
    /**
     * 长江驾驶高级船员航线
     */
    @TableField("ss_changjiang_route")
    private String ssChangjiangRoute;
    /**
     * 创建人
     */
    private Long createdBy;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 修改人
     */
    private Long updatedBy;
    /**
     * 修改时间
     */
    private Long updatedTime;
    /**
     * 是否失效
     */
    private Integer deleted;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 船员分类
     */
    @TableField("sailor_type")
    private String sailorType;
    /**
     * 扩展字段1
     */
    private String field01;
    /**
     * 扩展字段2
     */
    private String field02;
    /**
     * 扩展字段3
     */
    private String field03;
    /**
     * 扩展字段4
     */
    private String field04;
    /**
     * 扩展字段5
     */
    private String field05;

    @TableField(exist = false)
    private String token;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }
}
