package com.imm.kk.result;

public class StatusCode {

    /**
     * 成功
     */
    protected final static int OK = 0;
    /**
     * 授权401 需要登录
     */
    public final static int LOGIN_REQUIRE = 4010;
    /**
     * 登录密码错误
     */
    public final static int LOGIN_PWD_ERROR = 4011;
    /**
     * 登录帐号已禁用
     */
    public final static int LOGIN_DISABLED_ERROR = 4012;
    /**
     * 登录次数过多
     */
    public final static int LOGIN_TOO_MANY_ERROR = 4013;
    /**
     * 没有权限
     */
    public final static int LOGIN_NON_PRIVILEGE_ERROR = 4014;
    /**
     * token为空
     */
    public final static int TOKEN_EMPTY = 4015;
    /**
     * token验证失败
     */
    public final static int TOKEN_VERIFY_ERROR = 4016;
    /**
     * 签名验证失败
     */
    public final static int SIGN_VERIFY_ERROR = 4017;
    /**
     * 密码错误
     */
    public final static int WRONG_PASSWORD = 4018;
    /**
     * 不存在404
     */
    public final static int NOT_FOUND = 4040;
    /**
     * 用户不存在
     */
    public final static int USER_NOT_FOUND = 4041;
    /**
     * 参数不存在
     */
    public final static int PARAM_NOT_FOUND = 4042;
    /**
     * 错误的参数值
     */
    public final static int WRONG_PARAMETER_VALUE = 4043;
    /**
     * 参数为空
     */
    public final static int PARAM_EMPTY = 4044;
    /**
     * 参数不能为空
     */
    public final static int PARAM_NOT_EMPTY = 4045;

    /**
     * 服务器错误 内部500
     */
    public final static int SERVER_ERROR = 5000;
    /**
     * 验证码错误
     */
    public final static int CAPTCHA_ERROR = 5001;
    /**
     * 数据重复
     */
    public final static int EXISTS = 5002;
    /**
     * 数据长度超长
     */
    public static final int DATA_TO_LONG = 5003;
    /**
     * 文件超大
     */
    public static final int FILE_TO_BIG = 5004;
    /**
     * 删除数据有引用
     */
    public static final int DATA_REFERENCE = 5005;

    public final static int OPERATOR_MSG = 1000;
    /**
     * 请选择透析方式
     */
    public final static int CHOSE_DIALYSIS_MODE = 1001;
    /**
     * 未选择任何班次
     */
    public final static int NO_SHIFTS_SELECTED = 1002;
    /**
     * 组织机构代码为空
     */
    public final static int ORGANIZATION_CODE_IS_EMPTY = 1003;
    /**
     * 紧急排床-床位被占用(未透析或者透析完成了但是加上长期医嘱影响到下班次了排班)
     */
    public final static int BED_USEING = 1004;
    /**
     * 紧急排床-床位被占用并且在透析中
     */
    public final static int BED_USE_DIALYSISING = 1005;
    /**
     * 患者未做长期医嘱，不能排床
     */
    public final static int UNDO_LONG_TIME_MEDICAL = 1006;
    /**
     * 该患者已签到
     */
    public final static int PATIENT_HAS_SIGNED_IN = 1007;
    /**
     * 床位信息没找到
     */
    public final static int BED_INFO_NOT_FOUND = 1008;
    /**
     * 班次信息没找到
     */
    public final static int FLIGHT_INFO_NOT_FOUND = 1009;
    /**
     * 排班模板基础数据表数据没找到
     */
    public final static int SCHEDULING_TEMPLATE_BASIC_DATA_NOT_FOUND = 1010;
    /**
     * 排班模板基础数据表数据没找到
     */
    public final static int DIALYSIS_CYCLES_NOT_BE_EMPTY = 1011;
    /**
     * 旧密码输入错误
     */
    public static final int OLD_PASSWORD_IS_ERROR = 1012;
    /**
     * 过期数据不能操作
     */
    public static final int OUTDATED_DATA_CANNOT_BE_MANIPULATED = 1013;
    /**
     * 床未绑定机器
     */
    public static final int BED_NOT_BOUND_MACHINE = 1014;
    /**
     * 床绑定的机器异常
     */
    public static final int MACHINE_ABNORMAL = 1015;
    /**
     * 患者传染病信息与床位分区不匹配
     */
    public static final int INFECTIOUS_DISEASE_NOT_MATCH_WITH_BED_ZONING = 1016;
    /**
     * 该患者未做长期医嘱
     */
    public static final int PATIENT_DID_NOT_OPEN_LTMO = 1017;
    /**
     * 患者不存在对应的长期医嘱，请重新开立
     */
    public static final int PATIENT_NOT_CORRESPONDING_LTMO = 1018;
    /**
     * 患者已排床
     */
    public static final int PATIENT_ALREADY_ROW_BED = 1019;
    /**
     * 透析中或者已透析完成的数据不能操作
     */
    public static final int DIALYSISING_OR_FINISH_CANNOT_BE_MANIPULATED = 1020;
    /**
     * 其他设备信息已存在
     */
    public static final int OTHER_DEVICE_EXISTS = 1021;
    /**
     * 已存在请假中的数据
     */
    public static final int PATIENT_HOLIDAY_EXISTS = 1022;
    /**
     * 已有患者不能排班
     */
    public static final int PATIENT_SCHEDULE_EXISTS = 1023;
    /**
     * 床号已存在
     */
    public static final int BEDNUMBER_EXISTS = 1024;
    /**
     * 当天班次的记录已存在
     */
    public static final int CURRDAYSHIFT_RECORDS_EXISTS = 1025;
    /**
     * 已上机患者不能取消透析
     */
    public static final int PATIENTS_ON_BOARD_UNCANCEL = 1026;
    /**
     * 请假中的患者不能排班
     */
    public static final int PATIENTS_ON_LEAVE_UNSCHEDULED = 1027;
    /**
     * 该班次患者已签到不能修改透析方式
     */
    public static final int DIALYSISING_UNCHANGE_DIALYSISMODE = 1028;
    /**
     * 患者存在未透析“HDF”透析医嘱排床，是否继续取消“HDF”透析医嘱
     */
    public static final int PATIENT_LTMO_SCHEDULE_EXISTS = 1029;
    /**
     * 请假中的患者在透析监控不能签到
     */
    public static final int PATIENTS_ON_LEAVE_UNSINGIN = 1030;
    /**
     * 一个患者一种卡类型只有一张生效的卡
     */
    public static final int PATIENT_DIALYSIS_CARD_ONLY_ONE_VALID = 1031;
    /**
     * 该卡号已经存在请重新切换卡号
     */
    public static final int PATIENT_DIALYSIS_CARD_EXISTS = 1032;
    /**
     * 该类型已存在，请进行编辑的操作
     */
    public static final int TYPE_ALREADY_EXISTS_DOEDIT = 1033;
    /**
     * 该类型已存在，请进行编辑的操作
     */
    public static final int INSTITUTION_CODE_EMPTY = 1034;
    /**
     * 单泵的床位不能选双泵的透析方式
     */
    public static final int BED_DIALYSISMODE_NOT_SAME = 1035;
    /**
     * 至少有一种透析方式
     */
    public static final int AT_LEAST_ONE_DIALYSIS_METHOD = 1036;
    /**
     * 患者今天已做干体重评估
     */
    public static final int PATIENT_DRY_WEIGHT_EVALUATION_TO_DAY = 1037;
    /**
     * 过敏透析器：18UC已在长期医嘱存在，请先修改长期医嘱信息
     */
    public static final int ALLERGY_TXQ_EXISTS_PATIENTLTMO = 1038;
    /**
     * 患者透析方式与床位泵数不匹配
     */
    public static final int BED_DIALYSIS_PUMPNUMBER_DIFF = 1039;
    /**
     * 患者传染病由XX修改为DD
     */
    public static final int INFECTIOUS_DISEASE_CHECK_CHANGE = 1040;
    /**
     * 患者在当班次的其它床位已签到
     */
    public static final int PATIENT_CURRDAY_SHIFT_SINGIN = 1041;
    /**
     * 错误的签到时间
     */
    public static final int ERROR_SIGN_TIME = 1042;
    /**
     * 维修项目金额不能为空
     */
    public static final int ITEM_AMOUNT_IS_EMPTY = 1043;

    /**
     * 核对和执行不能为同一个人
     */
    public static final int CHECK_EXECUTE_SAME = 1044;
    /**
     * 数量不能为空且不能不小于1
     */
    public static final int QUANTITY_EMPTY_OR_LESS_ONE = 1045;
    /**
     * 透析用药医嘱已存在过敏药品({0})请先做修改后再操作
     */
    public static final int DMO_EXISTS_ALLERGY_DRUG = 1046;
    /**
     * 临时药医嘱已存在过敏药品({0})请先做修改后再操作
     */
    public static final int TMO_EXISTS_ALLERGY_DRUG = 1047;
    /**
     * 该班次床位已有患者在透析，请调整床位
     */
    public static final int CURR_SHIFT_BED_SINGIN = 1048;
    /**
     * 排班类型不能为空
     */
    public static final int SCHEDULING_TYPE_CANNOT_BE_EMPTY = 1049;
    /**
     * 错误的排班类型
     */
    public static final int WRONG_SCHEDULING_TYPE = 1050;
    /**
     * 请先设置医疗机构透析周期
     */
    public static final int SET_UP_DIALYSIS_CYCLE = 1051;
    /**
     * 该患者已在紧急排班排了当班次的床位
     */
    public static final int ALREADY_EMERGENCY_EXISTS = 1052;
    /**
     * 该患者已在周排排班排了当班次的床位
     */
    public static final int ALREADY_SCHEDULED_EXISTS = 1053;
    /**
     * 请先清空患者周排班数据和模板数据
     */
    public static final int SCHEDULE_BED_PATIENT_EXISTS = 1054;
    /**
     * 请填写门诊号或者住院号
     */
    public static final int OUTPATIENT_INPATIENT_NUMBER = 1055;

    /**
     * 该卡号已被绑定
     */
    public static final int PATIENT_DIALYSIS_CARD_NOT_UNIQUE = 1056;
    /**
     * 体重测量异常,请您重新刷卡称重,谢谢
     */
    public static final int WEIGHT_EXCEPTION = 1057;

    /**
     * 该卡未绑定患者
     */
    public static final int PATIENT_NOT_EXIST = 1058;
    /**
     * 该药品已在血管通路封管液药品使用
     */
    public static final int SEALING_FLUID_GROUP_DRUG_USEING = 1059;
    /**
     * 请假时还要判断下是否已经签到，签到的话当天就不允许请假了
     */
    public static final int SIGNED_IN_NO_LEAVE_ALLOWED  = 1060;

    /**
     *
     * 患者血管通路封管液组合药品已存在过敏药品({0})请先做修改后再操作
     */
    public static final int VA_SEALING_FLUID_EXISTS_ALLERGY_DRUG = 1061;
    /**
     * 不是有效的证件类型
     */
    public static final int CREDTYPE_NOT_VALID = 1062;

    /**
     * 下机后的数据不能操作
     */
    public static final int LOWER_CANNOT_BE_MANIPULATED = 1063;
    /**
     * 是否要在当天继续排床
     */
    public static final int CONTINUE_SCHEDULED_CURRDAY = 1064;
    /**
     * 患者已上机，确定是否调整床位
     */
    public static final int UPMACHINE_SCHEDULED_CONTINUE = 1065;
    /**
     * 已签到或已上机,只能换到同一天、同一班次的床位，否则提示不能调换
     */
    public static final int UN_CURR_DAY_SHIFT = 1066;
    /**
     * 当前班次的床位已存在,请选择其它床位进行调换
     */
    public static final int BED_ISEXISTS_SELECT_OTHERS = 1067;
    /**
     * 当天班次的记录已存在,确定是否调整床位
     */
    public static final int CURRDAY_SHIFT_ISEXISTS_CHANGE = 1068;
    /**
     * 病案号重复
     */
    public static final int MR_NO_EXISTS = 1069;

    /**
     * 规律排班，未检测患者不允许自动排床,请手工安排
     */
    public static final int LOWSCHEDULE_AUTO_UNCHECK = 1070;

    /**
     * 当前医嘱已修改请刷新页面
     */
    public final static int UPDATA_MEDICAL_ORDER = 1071;
}
