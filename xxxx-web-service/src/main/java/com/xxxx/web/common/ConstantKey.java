package com.xxxx.web.common;

public class ConstantKey {


    /**
     * token 相关，通过用户id查询当前用户的token
     */
    public static String REDIS_TOKEN_STRING_USER_TOKEN = "redis:token:user-token:token-id-%s";

    /**
     * token 相关，通过token获取当前用户对象信息
     */
    public static String REDIS_TOKEN_STRING_TOKEN_USER = "redis:token:token-user:user-id-%s";


    /**
     * 用户角色 通过角色id获取角色对应的资源
     */
    public static String REDIS_ROLE_STRING_ROLE = "redis_role_string_role_%s";

    /**
     * 系统资源，菜单和请求uri，存储结构 string
     */
    public static String REDIS_ALL_RESOURCE = "redis_all_resource";


    /**
     * 记录当前用户id最大值
     */
    public static String REDIS_USER_ID_BASE = "redis_user_id_base";


    /**
     * 所有游戏区服信息
     */
    public static String REDIS_GAME_SERVICE_STRING = "redis_game_service_string";


    /**
     * 所有游戏基础信息集合
     */
    public static String REDIS_ALL_GAME_INFO_SET = "redis_all_game_info_set";


    /**
     * 所有游戏大区基础信息集合
     */
    public static String REDIS_ALL_GAME_AREA_SET = "redis_all_game_area_set";

    /**
     * 所有游戏区服基础信息集合
     */
    public static String REDIS_ALL_GAME_SERVICE_SET = "redis_all_game_service_set";


    /**
     * 用户已发订单列表
     */
    public static String REDIS_PUBLISHED_ORDER_SET = "published:order:set:userId:user-id-%s";


}
