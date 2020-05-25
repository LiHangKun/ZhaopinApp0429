package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class ShouCangZhiWeiBean extends CommonBean {


    /**
     * dataList : [{"city":{"children":[],"name":"郑州市","parentId":"0","sort":"1-2"},"company":{"financingName":"","id":"861f394f272b4ad39e8ce8646021c8d6","industry2Name":"","intro":"居家实业有限公司是一家经营1． 百货（日用百货、服装服饰、皮革制品、鞋帽、洗涤用品、化妆品、护肤用品、摄影器材、玩具、音响设备及器材） 2． 文化办公用品（纸制品） 3． 包装材料 4． 工艺品（工艺礼品、金银首饰） 5． 化工原料及产品（除危险品） 6． 五金交电（家用电器、自行车、钢丝绳、阀门、管道配件、轴承、电线电缆） 7． 电子产品、通讯器材（除卫星天线）、通信设备、通讯设备（除卫星天线）、仪器仪表。 8． 机电设备及配件（电动工具、制冷设备、压缩机及配件、工量刀具）、机械设备及配件。 9． 计算机软硬件及配件","logo":"http://39.96.78.51/userfiles/company/2020/5/ilzaxxhwct.png","name":"居家实业有限公司"},"district":{"children":[],"name":"金水区","parentId":"0","sort":"1-2"},"education":{"name":"专科"},"experienceYear":{"name":"1-2"},"id":"0e3a39496f5049a1a52ccae386583398","location":"商都世贸中心","maxSalary":"1-2","minSalary":"1-2","name":"硬件工程师","opened":"1","positionType":"2","workfare":"五险一金,生日祝福,项目奖金,年休假,十三薪,双休"},{"city":{"children":[],"name":"郑州市","parentId":"0","sort":"1-2"},"company":{"financingName":"","id":"7f246b2831254cb1b9ba5c2441668541","industry2Name":"","intro":"服务好每一个客户","logo":"http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信"},"district":{"children":[],"name":"金水区","parentId":"0","sort":"1-2"},"education":{"name":"本科"},"experienceYear":{"name":"1-5"},"id":"045fc44509444842ad27d4a7ee30a021","location":"商都世贸中心","maxSalary":"1-2","minSalary":"1-2","name":"前端开发","opened":"1","positionType":"2","workfare":"双休,五险一金"}]
     * pageNo : 1
     * pageSize : 10
     * totalCount : 2
     * totalPage : 1
     */

    private int pageNo;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * city : {"children":[],"name":"郑州市","parentId":"0","sort":"1-2"}
         * company : {"financingName":"","id":"861f394f272b4ad39e8ce8646021c8d6","industry2Name":"","intro":"居家实业有限公司是一家经营1． 百货（日用百货、服装服饰、皮革制品、鞋帽、洗涤用品、化妆品、护肤用品、摄影器材、玩具、音响设备及器材） 2． 文化办公用品（纸制品） 3． 包装材料 4． 工艺品（工艺礼品、金银首饰） 5． 化工原料及产品（除危险品） 6． 五金交电（家用电器、自行车、钢丝绳、阀门、管道配件、轴承、电线电缆） 7． 电子产品、通讯器材（除卫星天线）、通信设备、通讯设备（除卫星天线）、仪器仪表。 8． 机电设备及配件（电动工具、制冷设备、压缩机及配件、工量刀具）、机械设备及配件。 9． 计算机软硬件及配件","logo":"http://39.96.78.51/userfiles/company/2020/5/ilzaxxhwct.png","name":"居家实业有限公司"}
         * district : {"children":[],"name":"金水区","parentId":"0","sort":"1-2"}
         * education : {"name":"专科"}
         * experienceYear : {"name":"1-2"}
         * id : 0e3a39496f5049a1a52ccae386583398
         * location : 商都世贸中心
         * maxSalary : 1-2
         * minSalary : 1-2
         * name : 硬件工程师
         * opened : 1
         * positionType : 2
         * workfare : 五险一金,生日祝福,项目奖金,年休假,十三薪,双休
         */

        private CityBean city;
        private CompanyBean company;
        private DistrictBean district;
        private EducationBean education;
        private ExperienceYearBean experienceYear;
        private String id;
        private String location;
        private String maxSalary;
        private String minSalary;
        private String name;
        private String opened;
        private String positionType;
        private String workfare;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public DistrictBean getDistrict() {
            return district;
        }

        public void setDistrict(DistrictBean district) {
            this.district = district;
        }

        public EducationBean getEducation() {
            return education;
        }

        public void setEducation(EducationBean education) {
            this.education = education;
        }

        public ExperienceYearBean getExperienceYear() {
            return experienceYear;
        }

        public void setExperienceYear(ExperienceYearBean experienceYear) {
            this.experienceYear = experienceYear;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(String maxSalary) {
            this.maxSalary = maxSalary;
        }

        public String getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(String minSalary) {
            this.minSalary = minSalary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpened() {
            return opened;
        }

        public void setOpened(String opened) {
            this.opened = opened;
        }

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(String positionType) {
            this.positionType = positionType;
        }

        public String getWorkfare() {
            return workfare;
        }

        public void setWorkfare(String workfare) {
            this.workfare = workfare;
        }

        public static class CityBean {
            /**
             * children : []
             * name : 郑州市
             * parentId : 0
             * sort : 1-2
             */

            private String name;
            private String parentId;
            private String sort;
            private List<?> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class CompanyBean {
            /**
             * financingName :
             * id : 861f394f272b4ad39e8ce8646021c8d6
             * industry2Name :
             * intro : 居家实业有限公司是一家经营1． 百货（日用百货、服装服饰、皮革制品、鞋帽、洗涤用品、化妆品、护肤用品、摄影器材、玩具、音响设备及器材） 2． 文化办公用品（纸制品） 3． 包装材料 4． 工艺品（工艺礼品、金银首饰） 5． 化工原料及产品（除危险品） 6． 五金交电（家用电器、自行车、钢丝绳、阀门、管道配件、轴承、电线电缆） 7． 电子产品、通讯器材（除卫星天线）、通信设备、通讯设备（除卫星天线）、仪器仪表。 8． 机电设备及配件（电动工具、制冷设备、压缩机及配件、工量刀具）、机械设备及配件。 9． 计算机软硬件及配件
             * logo : http://39.96.78.51/userfiles/company/2020/5/ilzaxxhwct.png
             * name : 居家实业有限公司
             */

            private String financingName;
            private String id;
            private String industry2Name;
            private String intro;
            private String logo;
            private String name;

            public String getFinancingName() {
                return financingName;
            }

            public void setFinancingName(String financingName) {
                this.financingName = financingName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIndustry2Name() {
                return industry2Name;
            }

            public void setIndustry2Name(String industry2Name) {
                this.industry2Name = industry2Name;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class DistrictBean {
            /**
             * children : []
             * name : 金水区
             * parentId : 0
             * sort : 1-2
             */

            private String name;
            private String parentId;
            private String sort;
            private List<?> children;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class EducationBean {
            /**
             * name : 专科
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ExperienceYearBean {
            /**
             * name : 1-2
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
