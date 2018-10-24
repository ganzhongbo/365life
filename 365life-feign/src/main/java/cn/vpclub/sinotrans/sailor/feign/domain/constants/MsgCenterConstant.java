package cn.vpclub.sinotrans.sailor.feign.domain.constants;

/**
 * @author 南政
 * @className MsgCenterConstant
 * @desc
 * @since 2018/10/22 14:39
 */
public class MsgCenterConstant {

    /**
     * 消息所属-私有消息
     */
    public static final int PRIVATE_MESSAGE = 1;

    /**
     * 消息所属-公有消息
     */
    public static final int PUBLIC_MESSAGE = 2;

    /**
     * 消息状态-未处理
     */
    public static final int NOT_DEAL = 1;

    /**
     * 消息状态-已处理
     */
    public static final int HAVE_DEAL = 2;

    /**
     * 消息类型-房源类型
     */
    public static final int HOUSE_SOURCE = 1;


    /**
     * 是否抢单-是
     */
    public static final int IS_GRAB = 1;

    /**
     * 是否抢单-否
     */
    public static final int NOT_GRAB = 2;
}

