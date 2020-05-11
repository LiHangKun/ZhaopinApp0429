package com.lx.zhaopin.net;

public class NetCuiMethod {

    //   params.put("pageCount", YunDongSP.pageCount);

    public static final String checkPhone = "member/exist";//接口中涉及到”是/否”的字段都是：1表示是，0表示否
    public static final String sendPhoneCode = "member/auth/code";//类型：1:注册,2:登录,3:修改绑定前验证身份,4:修改绑定手机号,5:修改或找回密码,6.注销账户
    public static final String registerMethod = "member/register";//注册
    public static final String retrievePassword = "member/password";//找回或修改密码
    public static final String loginMethod = "member/login";//登录
    public static final String memberCity = "member/province/list";//选择省份
    public static final String aboutMe = "common/about";//关于我们
    public static final String feedback = "member/feedback";//意见反馈
    public static final String wenTiPage = "common/question/page";//常见问题


    /*------------TODO-----求职者-----------------*/
    public static final String qiuZhiMyInfo = "member/jobhunter/info";//求职者个人信息
    public static final String xiugaiQiuZhiMyInfo = "member/jobhunter/modify";//修改个人信息
    public static final String myJianLi = "member/jobhunter/resume";//个人简历
    public static final String xueLiList = "member/education/list";//学历列表
    public static final String addJiaoYuJIngLi = "member/jobhunter/resume/education/add";//新增教育信息
    public static final String chaEidInfo = "member/jobhunter/resume/education/get";//根据EID查询学历信息  TODO  还没有写
    public static final String xiuGaiEidInfo = "member/jobhunter/resume/education/edit";//修改教育经历
    public static final String delEidInfo = "member/jobhunter/resume/education/delete";//删除教育经历

    /*------------TODO-----求职者-----------------*/


    public static final String logout = "logout";//退出登录
    public static final String memberLogout = "member/logout";//退出登录
    public static final String zhuXiaoUID = "member/account/delete";//注销账户
    public static final String versionUpdate = "member/version/update";//更新


}
