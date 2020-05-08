package com.lx.zhaopin.net;

public class NetCuiMethod {

    //   params.put("pageCount", YunDongSP.pageCount);

    public static final String checkPhone = "member/exist";//接口中涉及到”是/否”的字段都是：1表示是，0表示否
    public static final String sendPhoneCode = "member/auth/code";//获取验证码  类型：1:注册,2:找回密码,3:绑定手机号,4:修改支付密码
    public static final String registerMethod = "member/register";//注册
    public static final String retrievePassword = "member/retrieve/password";//找回密码
    public static final String loginMethod = "member/login";//登录


    /*-----------------我的-----------------*/
    public static final String memberInfo = "member/info";//我的
    public static final String memberChange = "member/change";//修改用户信息
    public static final String memberAuthentication = "member/authentication";//用户认证
    public static final String memberJoinImage = "member/join/image";//入驻大图
    public static final String memberCity = "member/city";//城市列表
    public static final String memberJoin = "member/join";//入驻申请
    public static final String memberWithdrawPage = "member/withdraw/page";//提现记录
    public static final String memberBalance = "member/balance";//账户余额   //TODO -----会经常用到 提现购物支付
    public static final String memberPayPass = "member/pay/pass";//修改支付密码   修改和设置  //TODO -----会经常用到 提现购物支付
    public static final String memberWithdrawInfo = "member/withdraw/info";//获取提现信息
    public static final String memberWithdrawAdd = "member/withdraw/add";//申请提现
    public static final String memberFriendPage = "member/friend/page";//好友列表
    public static final String memberCollectPage = "member/collect/page";//收藏列表
    public static final String memberCartAdd = "member/cart/add";//加入购物车
    public static final String memberGoodsCollect = "member/goods/collect";//商品收藏/取消收藏
    public static final String memberAddressList = "member/address/list";//地址列表
    public static final String memberAddressDetail = "member/address/detail";//地址详情
    public static final String memberAddressEdit = "member/address/edit";//编辑地址和新增加
    public static final String memberCouponPage = "member/coupon/page";//优惠券列表
    public static final String memberMessagePage = "member/message/page";//消息列表
    public static final String memberKeyword = "member/keyword";//热门搜索关键词
    public static final String memberGoodsPage = "member/goods/page";//商品列表
    public static final String memberOrderPage = "member/order/page";//订单列表
    public static final String memberGoodsDetail = "member/goods/detail";//商品详情
    public static final String memberHome = "member/home";//首页数据

    public static final String memberCategoryList = "member/category/list";//商品分类列表
    public static final String memberCartPage = "member/cart/page";//购物车列表
    public static final String memberShop = "member/shop";//门店列表
    public static final String memberSeckillSession = "member/seckill/session";//秒杀场次
    public static final String editCart = "member/cart/edit";//修改数量
    public static final String delMyAdd = "member/address/delete";//删除地址
    public static final String jieSuan = "member/order/settle";//结算
    public static final String xiaDanOrder = "member/order/place";//下单
    public static final String withdrawEdit = "member/withdraw/edit";//设置提现信息
    public static final String orderDetail = "member/order/detail";//订单详情
    public static final String yuEZhiFu = "member/order/pay";//余额支付
    public static final String yuFuBiLi = "member/prepay/ratio";//预付比例
    public static final String shouHuo = "member/order/confirm";//确认收货
    public static final String delOrder = "member/order/delete";//删除订单
    public static final String PingJiaOrder = "member/order/comment";//订单评价
    public static final String shouHouOrder = "member/order/appeal";//申请售后
    public static final String pingJiaList = "member/comment/page";//评价列表
    public static final String KanpingJiaOrder = "member/comment/detail";//查看评价
    public static final String gongGaoList = "member/announcement/page";//公告列表
    public static final String clearMessageList = "member/message/clear";//清空消息列表
    public static final String memberHot = "member/hot";//热销推荐
    public static final String memberCartCount = "member/cart/count";//购物车数量
    public static final String delShopCarList = "member/cart/delete";//删除购物车
    public static final String miaoShaShopList = "member/seckill/goods/page";//秒杀商品列表
    public static final String sahnchuShouCang = "member/collect/delete";//删除收藏
    public static final String firstLogin = "member/firstLogin";//是否是第一次登录
    public static final String moneyPage = "member/money/page";//账户明细

    /*-----------------我的-----------------*/


    public static final String logout = "logout";//退出登录
    public static final String memberLogout = "member/logout";//退出登录
    public static final String zhuXiaoUID = "member/account/delete";//注销账户
    public static final String versionUpdate = "member/version/update";//更新


}
