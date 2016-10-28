package com.yadda.integrate.provider.util;

/**
 * 全局返回码说明：<br>
 * <br>
 * 200:请求成功. <br>
 * <br>
 * 500--600:为业务逻辑错误返回码，例如：入口参数错误,非法操作等. <br>
 * <br>
 * 40001--41000:为全局与业务无关错误返回码，例如登陆时用户名密码错误，手机号码不正确，验证码过期等.<br>
 * <br>
 * 返回码必须引用此类中的常量，方便返回码的统一管理.<br>
 * <br>
 * 当你认为这里的错误代码不足以表述当前错误时，请在此增加错误代码后使用.<br>
 * <br>
 * 
 * @author Administrator
 * 
 */
public class StatusCode {

	/**
	 * 系统繁忙
	 */
	public static final int SYS_BUSY = -1;
	/**
	 * 请求成功
	 */
	public static final int SUCCESS = 200;

	/**
	 * 参数错误
	 */
	public static final int PARAMETER_ERROR = 501;
	/**
	 * 参数不存在
	 */
	public static final int PARAMETER_IS_NOT_EXIST = 502;
	/**
	 * 业务逻辑导致逻辑不能继续
	 */
	public static final int BUSINESS_LOGIC_ERROR = 503;

	/**
	 * 用户名已经存在
	 */
	public static final int USER_ALREADY_EXIST = 601;

	/**
	 * 微信公众号：用户未登录
	 */
	public static final int USER_NOT_BIND = 10001;

	/**
	 * 不合法的access_token
	 */
	public static final int ACCESS_TOKEN_INVALID = 40001;
	/**
	 * 非法用戶
	 */
	public static final int ILLEGAL_USER = 40002;
	/**
	 * 用户名或密码错误
	 */
	public static final int MOBILE_OR_PWAD_ERROR = 40003;
	/**
	 * 手机验证码不正确
	 */
	public static final int MOBILE_CODE_ERROR = 40004;
	/**
	 * 手机验证码已过期
	 */
	public static final int MOBILE_CODE_EXPIRE = 40005;
	/**
	 * 手机号码已被注册
	 */
	public static final int MOBILE_EXIST = 40006;
	/**
	 * 获取验证码过于频繁，至少需要间隔1分钟后才能重发
	 */
	public static final int MOBILE_CODE_FREQUENTLY = 40006;
	/**
	 * 积分不足
	 */
	public static final int INTEGRAL_NOT_ENOUGH = 40007;

	/**
	 * 系统参数不存在
	 */
	public static final int PARAM_IS_NOT_EXIT = 40009;
	/**
	 * 已经是最新版本了
	 */
	public static final int NO_NEWS_VERSION = 40010;

	/**
	 * 原密码不正确
	 */
	public static final int OLD_PASSWORD_ERROR = 40014;
	/**
	 * 图片格式不对
	 */
	public static final int PIC_FORMAT_ERROR = 40015;
	/**
	 * 图片过大
	 */
	public static final int PIC_SIZE_ERROR = 40016;

	/**
	 * 图片上传失败
	 */
	public static final int PIC_UPLOAD_FAIL = 40017;

	/**
	 * 帖子不存在
	 */
	public static final int TOPIC_NOT_FOUND = 40018;
	/**
	 * 手机号不存在
	 */
	public static final int MOBILE_NOT_EXIST = 40019;
	/**
	 * 还没审核通过，请耐心等待
	 */
	public static final int NOT_CHECK_STATUS = 40020;
	/**
	 * 车牌已被绑定
	 */
	public static final int CAR_HAS_BIND = 40021;
	/**
	 * 没有符合条件的结果
	 */
	public static final int LIST_IS_EMPTY = 40022;
	/**
	 * 用户不存在
	 */
	public static final int USER_NOT_ABSENT = 40025;

	/**
	 * 两次输入密码不一致
	 */
	public static final int PASSWORD_NOT_EQUAL = 40026;

	/**
	 * 添加预订单失败
	 */
	public static final int PREORDER_FAIL = 40027;

	/**
	 * 有未完成订单
	 */
	public static final int NO_PERMISSION_ENTER_PARK = 40028;

	/**
	 * 没有空闲车位
	 **/
	public static final int NO_ALLOW_BOOK_COUNT = 40029;

	/**
	 * 添加订单失败
	 */
	public static final int ADDORDER_FAIL = 40030;

	/**
	 * 已获取商家优惠券，请勿重复获取
	 */
	public static final int ORDER_HAS_CONPON = 40031;

	/**
	 * 钱包余额不足
	 */
	public static final int BALANCE_NOT_ENOUGH = 90007;

	public static final int PARK_DEVICE_NOT_SUPORT = 90011;

	public static final int OVER_TIME_OUT_PARK = 80001;

	public static final int HCOIN_NOT_ENOU = 903;
	/**
	 * 账号被冻结
	 */
	public static final int ACCOUNT_LOCK = 40041;

	/**
	 * 未分配权限
	 */
	public static final int PERMISSION_INVALID = 40050;

	/**
	 * 平台优惠券与商家优惠券不能混用
	 */
	public static final int COUPON_NOT_MIX = 20001;

}
