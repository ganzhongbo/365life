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
public class User implements Serializable {


    /**
     * 会员id
     */
    @TableId(value = "user_id",type = IdType.ID_WORKER)
    private Long userId;
    /**
     * 用户角色
     */
    private Integer userRole;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    private String userIdcard;

    private String password;

    @TableField(exist = false)
    private String rePassword;//确认密码
    /**
     * 姓名
     */
    private String realName;
    /**
     * 员工编号
     * user_code
     */
    private String userCode;
    /**
     * 头像
     */
    private String headImg;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 婚姻状况
     */
    private Integer isMarry;

    /**
     * 居住地址
     */
    private String address;

    /**
     * 手机号
     */
    private String tellphone;
    /**
     * 民族
     */
    private String nation;

    /**
     * 家庭座机
     */
    private String homeTellphone;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 微信账号
     */
    private  String wxAccount;
    /**
     * QQ账号
     */
    private String qqAccount;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 最高学历
     */
    private String hightEducation;
    /**
     * 工作年限
     */
    private String workAge;
    /**
     * 有无房产经验
     */
    private Integer isExperience;

    private String emergencyContacOne;//第一紧急联系人
    private String contacOneTell;//联系电话

    private String emergencyContacTwo;//第二紧急联系人
    private String contacTwoTell;//联系电话

    /**
     * 部门主键
     */
    private String departmentId;
    /**
     * 岗位级别
     */
    private Integer postLevel;
    /**
     *服务商圈
     */
    private  String serviceBusiness;

    /**
     * 经纪人资格编号
     */
    private String brokerNumber;

    /**
     * 开户行
     */
    private String openBank;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 个人简介
     */
    private String personalProfile;
    /**
     * 个人喜好
     */
    private String personalPreferences;
    /**
     * 招聘渠道
     */
    private String recruitmentChannels;
    /**
     * 招聘渠道备注
     */
    private String channelsRemark;

    /**
     * 工作经历
     */
    private String workExperience;
    /**
     * 教育经历
     */
    private String studyExperience;

    /**
     * 合伙人
     */
    private Long partner;
    /**
     * 上级
     */
    private Long superior;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 上上级
     */
    private Long upSuperior;

    /**
     * 绑定经纪人id(针对信息录入员)
     */
    private Long agentId;
    /**
     * 绑定经纪人姓名(针对信息录入员)
     */
    private String agentName;
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
    private List<String> resouceList;

    @TableField(exist = false)
    private String identifyingCode;//验证码

}
