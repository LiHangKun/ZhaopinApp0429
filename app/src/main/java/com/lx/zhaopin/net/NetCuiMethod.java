package com.lx.zhaopin.net;

public class NetCuiMethod {

    //   params.put("pageCount", YunDongSP.pageCount);

    public static final String checkPhone = "member/exist";//接口中涉及到”是/否”的字段都是：1表示是，0表示否
    public static final String sendPhoneCode = "member/auth/code";//类型：1:注册,2:登录,3:修改绑定前验证身份,4:修改绑定手机号,5:修改或找回密码,6.注销账户
    public static final String registerMethod = "member/register";//注册
    public static final String retrievePassword = "member/password";//找回或修改密码
    public static final String loginMethod = "member/login";//登录
    public static final String memberProvinceCity = "member/province/list";//选择省份
    public static final String memberCity = "member/city/list";//城市列表
    public static final String aboutMe = "common/about";//关于我们
    public static final String feedback = "member/feedback";//意见反馈
    public static final String wenTiPage = "common/question/page";//常见问题


    /*------------TODO-----求职者-----------------*/
    public static final String qiuZhiRToken = "member/jobhunter/rong";//求职者个人信息
    public static final String qiuZhiMyInfo = "member/jobhunter/info";//求职者个人信息
    public static final String xiugaiQiuZhiMyInfo = "member/jobhunter/modify";//修改个人信息
    public static final String myJianLi = "member/jobhunter/resume";//个人简历
    public static final String xueLiList = "member/education/list";//学历列表
    public static final String addJiaoYuJIngLi = "member/jobhunter/resume/education/add";//新增教育信息
    public static final String chaEidInfo = "member/jobhunter/resume/education/get";//根据EID查询学历信息
    public static final String xiuGaiEidInfo = "member/jobhunter/resume/education/edit";//修改教育经历
    public static final String delEidInfo = "member/jobhunter/resume/education/delete";//删除教育经历
    public static final String zhiWeiDetail = "member/position/detail";//职位详情
    public static final String zhiWeiShouCang = "member/position/collect";//职位收藏/取消收藏
    public static final String zhiWeiPageList = "member/position/page";//职位分页列表  TODO  卡片没有写
    public static final String zhiWeiIsHeShi = "member/position/improper";//职位不合适/取消不合适  TODO  还没有写
    public static final String zhiWeiJuBao = "member/position/report";//职位举报
    public static final String mianShiTimeList = "member/position/time";//预约面试时间列表   TODO  还没有写
    public static final String mianShiYuYue = "member/position/appoint";//预约面试   TODO  还没有写
    public static final String companyInfo = "member/company/info";//公司信息
    public static final String guanZhuGongsi = "member/company/collect";//关注公司/取消关注
    public static final String gongSiGangWei = "member/company/position/page";//公司在招岗位


    public static final String jianLiPreview = "member/jobhunter/resume/preview";//简历预览
    public static final String delZyJN = "member/jobhunter/resume/skill/delete";//删除专业技能
    public static final String ADDZyJN = "member/jobhunter/resume/skill/add";//新增专业技能
    public static final String EditZyJN = "member/jobhunter/resume/skill/edit";//修改专业技能
    public static final String delWorkJingLi = "member/jobhunter/resume/work/delete";//删除工作经历
    public static final String getWokJingLi = "member/jobhunter/resume/work/get";//获取工作经历
    public static final String editWokJingLi = "member/jobhunter/resume/work/edit";//修改工作经历  //TODO  可能会有问题
    public static final String addWokJingLi = "member/jobhunter/resume/work/add";//新增工作经历  //TODO  可能会有问题
    public static final String qiuZhiYiXiang = "member/jobhunter/expectation";//求职意向
    public static final String zhuCiZhiWei = "member/jobhunter/expectation/get";//获取主次职位
    public static final String addzhuCiZhiWei = "member/jobhunter/expectation/add";//新增主次职位   TODO  还没有写
    public static final String xiuGaizhuCiZhiWei = "member/jobhunter/expectation/change";//修改主次职位   TODO  还没有写
    public static final String delzhuCiZhiWei = "member/jobhunter/expectation/delete";//删除主次职位   TODO  还没有写
    public static final String shouCangZhiWeiList = "member/postion/collect/page";//收藏职位列表
    public static final String pingBiGangList = "member/postion/improper/page";//不合适职位列表
    public static final String guanzhuGongSi = "member/company/collect/page";//关注公司列表
    public static final String mianshiList = "member/jobhunter/interview/list";//面试记录  //TODO  里面的状态图片资源少,暂时没有替换完  待面试   已到达  不合适
    public static final String mianshiDetail_qiuZhi1 = "member/jobhunter/interview/detail";//面试详情,求职者看到的界面  //TODO  里面的状态图片资源少,暂时没有替换完  待面试   已到达  不合适
    public static final String yiLuQuList = "member/jobhunter/offer/list";//录取记录
    public static final String offerDetail = "member/jobhunter/offer/detail";//offer详情
    public static final String caoZuoOffer = "member/jobhunter/offer/deal";//处理offer
    public static final String qiuZheZheDaoDa = "我已到达";//求职者我已到达  TODO 没写
    public static final String qiuZhiFeedList = "member/jobhunter/chat/feedback/page";//求职反馈消息列表
    public static final String messageDetail = "jobhunter/chat/feedback/detail";//求职反馈消息详情
    public static final String newMessageCount = "member/jobhunter/chat/count";//新消息数量  TODO  还要处理消息已读数量的刷新
    public static final String sysMessageList = "member/jobhunter/chat/system/page";//系统消息列表
    public static final String lookSysMessage = "member/jobhunter/chat/system/view";//查看系统消息
    public static final String seleQiWangType1 = "member/position/category/list";//职位分类列表
    public static final String seleQiWangType2 = "member/industry/list";//行业分类列表
    public static final String getYuyueTimeList = "member/position/time";//预约面试时间列表
    public static final String YuYueMianShi = "member/position/appoint";//预约面试






    /*------------TODO-----求职者-----------------*/


    public static final String quXiaoMianShi_Type1 = "member/jobhunter/interview/cancel";//取消面试,求职者

    public static final String logout = "member/logout";//退出登录
    public static final String memberLogout = "member/logout";//退出登录
    public static final String zhuXiaoUID = "member/account/delete";//注销账户
    public static final String versionUpdate = "member/version/update";//更新


    /*------------TODO-----HRHRHRHRHRHRHRHR-----------------*/
    public static final String getHRRongToken = "member/recruiter/rong";//获取招聘者融云token
    public static final String mianshiDetail_HR = "member/recruiter/interview/detail";//面试详情,HR看到的界面  //TODO  里面的状态图片资源少,暂时没有替换完  待面试   已到达  不合适
    public static final String getHRMyInfo = "member/recruiter/info";//招聘者个人信息
    public static final String HRShouCangRen = "member/jobhunter/collect/page";//收藏求职者列表
    public static final String ShouCangRenCai = "member/resume/collect";//收藏求职者/取消收藏
    public static final String pingBiRen = "member/jobhunter/improper/page";//屏蔽求职者列表
    public static final String HR_mianshiList = "member/recruiter/interview/list";//HR面试记录  TODO  接口没有处理好
    public static final String HR_QuXiao = "member/recruiter/interview/cancel";//HR取消面试
    public static final String HR_yiLuQu = "member/recruiter/offer/list";//HR已录取
    public static final String HR_OfferDetail = "member/recruiter/offer/detail";//HR看到的offer详情
    public static final String renCaiDetail = "member/resume/detail";//人才详情
    public static final String HR_PingBi_renCaiDetail = "member/resume/improper";//屏蔽求职者/取消屏蔽
    public static final String gongSiAllZhiWei = "member/recruiter/position/list";//公司所有的职位
    public static final String getGouTongInfo = "member/chat/position";//获取已沟通职位
    public static final String yaoQingMianShi = "member/recruiter/interview/invite";//发布邀约,邀请面试
    public static final String shenQingJiLu = "member/jobhunter/chat/apply/page";//申请记录
    public static final String chuliGouTongShenQing = "member/jobhunter/chat/apply/deal";//处理沟通申请
    public static final String HRSouRenCai = "member/resume/page";//求职者简历分页数据
    /*------------TODO-----HRHRHRHRHRHRHRHR-----------------*/

}
