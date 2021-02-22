package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class GuanZhuGongSiBean extends CommonBean {


    /**
     * pageNo : 1
     * totalPage : 1
     * dataList : [{"companyName":"市金玉物业管理","companyId":"e8dff1b5f7c4420099c22da81df533e1","companyLogo":"http://39.96.78.51/userfiles/company/2020/7/t0fwjrpsad.jpg","industryName":"房地产/建筑","financingName":"未融资","positionNum":3,"companyIntro":"    金玉物业成立于2006年2月，已于2010年顺利通过ISO9001-2008质量体系认证，是一家提供秩序维护、保洁服务、绿化养管、设施设备运行维护、会议服务、员工餐厅管理服务、停车场管理等特约服务的专业化物业管理服务公司。公司通过积极拓展项目占领市场，强化管理机制，理顺工作流程，增强品质控制，优化作业指导，持续改进服务，夯实了公司发展的基础，累积了丰富的管理经验，锻炼出一支专业化的管理服务团队。项目现场管理服务成绩斐然，得到客户的赞誉，在区域市场具有较高的知名度和良好的美誉度，已逐步形成\u201c金玉物业品牌服务\u201d的良好口碑。","positionName":"塘沽餐厅食品安全员","companyNum":"1000-9999人"},{"companyName":"测试","companyId":"2c8a9f17078b4330ad2b73e9ffaa61ae","companyLogo":"http://39.96.78.51/userfiles/company/2020/8/jn1zujyn19.png","industryName":"互联网/IT/电子/通信","financingName":"不需要融资","positionNum":8,"companyIntro":"成都我的错2121","positionName":"csss","companyNum":"0-49人"}]
     * pageSize : 10
     * totalCount : 2
     */

    private int pageNo;
    private int totalPage;
    private int pageSize;
    private int totalCount;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * companyName : 市金玉物业管理
         * companyId : e8dff1b5f7c4420099c22da81df533e1
         * companyLogo : http://39.96.78.51/userfiles/company/2020/7/t0fwjrpsad.jpg
         * industryName : 房地产/建筑
         * financingName : 未融资
         * positionNum : 3
         * companyIntro :     金玉物业成立于2006年2月，已于2010年顺利通过ISO9001-2008质量体系认证，是一家提供秩序维护、保洁服务、绿化养管、设施设备运行维护、会议服务、员工餐厅管理服务、停车场管理等特约服务的专业化物业管理服务公司。公司通过积极拓展项目占领市场，强化管理机制，理顺工作流程，增强品质控制，优化作业指导，持续改进服务，夯实了公司发展的基础，累积了丰富的管理经验，锻炼出一支专业化的管理服务团队。项目现场管理服务成绩斐然，得到客户的赞誉，在区域市场具有较高的知名度和良好的美誉度，已逐步形成“金玉物业品牌服务”的良好口碑。
         * positionName : 塘沽餐厅食品安全员
         * companyNum : 1000-9999人
         */

        private String companyName;
        private String companyId;
        private String companyLogo;
        private String industryName;
        private String financingName;
        private int positionNum;
        private String companyIntro;
        private String positionName;
        private String companyNum;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyLogo() {
            return companyLogo;
        }

        public void setCompanyLogo(String companyLogo) {
            this.companyLogo = companyLogo;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getFinancingName() {
            return financingName;
        }

        public void setFinancingName(String financingName) {
            this.financingName = financingName;
        }

        public int getPositionNum() {
            return positionNum;
        }

        public void setPositionNum(int positionNum) {
            this.positionNum = positionNum;
        }

        public String getCompanyIntro() {
            return companyIntro;
        }

        public void setCompanyIntro(String companyIntro) {
            this.companyIntro = companyIntro;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getCompanyNum() {
            return companyNum;
        }

        public void setCompanyNum(String companyNum) {
            this.companyNum = companyNum;
        }
    }
}
