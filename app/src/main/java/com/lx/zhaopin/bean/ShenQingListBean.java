package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class ShenQingListBean extends CommonBean {


    /**
     * dataList : [{"applyDate":"2020-05-20 14:26:51","chatApplyStatus":"1","company":{"id":"7f246b2831254cb1b9ba5c2441668541","logo":"http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信"},"id":"5e0c0d39be2f4a1fa5ac82b36e6d58fc","position":{"city":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":""},"district":{"children":[],"id":"410105","name":"金水区","parentId":"0","sort":""},"duty":"java开发","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"experienceYear":{"id":"09262bb793484743af54627c3ee55283","name":"2-5"},"id":"091495aff3d94094ba3d1b410abc389f","maxSalary":"","minSalary":"","name":"java开发","opened":"1","positionType":"1","workfare":"双休,五险一金"}}]
     * pageNo : 1
     * pageSize : 10
     * totalCount : 1
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
         * applyDate : 2020-05-20 14:26:51
         * chatApplyStatus : 1
         * company : {"id":"7f246b2831254cb1b9ba5c2441668541","logo":"http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg","name":"立信"}
         * id : 5e0c0d39be2f4a1fa5ac82b36e6d58fc
         * position : {"city":{"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":""},"district":{"children":[],"id":"410105","name":"金水区","parentId":"0","sort":""},"duty":"java开发","education":{"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"},"experienceYear":{"id":"09262bb793484743af54627c3ee55283","name":"2-5"},"id":"091495aff3d94094ba3d1b410abc389f","maxSalary":"","minSalary":"","name":"java开发","opened":"1","positionType":"1","workfare":"双休,五险一金"}
         */

        private String applyDate;
        private String chatApplyStatus;
        private String hrID;
        private String hrName;
        private String hrAvatar;

        public String getHrID() {
            return hrID;
        }

        public void setHrID(String hrID) {
            this.hrID = hrID;
        }

        public String getHrName() {
            return hrName;
        }

        public void setHrName(String hrName) {
            this.hrName = hrName;
        }

        public String getHrAvatar() {
            return hrAvatar;
        }

        public void setHrAvatar(String hrAvatar) {
            this.hrAvatar = hrAvatar;
        }

        private CompanyBean company;
        private String id;
        private PositionBean position;

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getChatApplyStatus() {
            return chatApplyStatus;
        }

        public void setChatApplyStatus(String chatApplyStatus) {
            this.chatApplyStatus = chatApplyStatus;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
        }

        public static class CompanyBean {
            /**
             * id : 7f246b2831254cb1b9ba5c2441668541
             * logo : http://39.96.78.51/userfiles/company/2020/5/yqhwombvot.jpg
             * name : 立信
             */

            private String id;
            private String logo;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

        public static class PositionBean {
            /**
             * city : {"children":[],"id":"410100","name":"郑州市","parentId":"0","sort":""}
             * district : {"children":[],"id":"410105","name":"金水区","parentId":"0","sort":""}
             * duty : java开发
             * education : {"id":"899919864ee84faba953e6c435bfa0e8","name":"本科"}
             * experienceYear : {"id":"09262bb793484743af54627c3ee55283","name":"2-5"}
             * id : 091495aff3d94094ba3d1b410abc389f
             * maxSalary :
             * minSalary :
             * name : java开发
             * opened : 1
             * positionType : 1
             * workfare : 双休,五险一金
             */

            private CityBean city;
            private DistrictBean district;
            private String duty;
            private EducationBean education;
            private ExperienceYearBean experienceYear;
            private String id;
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

            public DistrictBean getDistrict() {
                return district;
            }

            public void setDistrict(DistrictBean district) {
                this.district = district;
            }

            public String getDuty() {
                return duty;
            }

            public void setDuty(String duty) {
                this.duty = duty;
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
                 * id : 410100
                 * name : 郑州市
                 * parentId : 0
                 * sort :
                 */

                private String id;
                private String name;
                private String parentId;
                private String sort;
                private List<?> children;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

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

            public static class DistrictBean {
                /**
                 * children : []
                 * id : 410105
                 * name : 金水区
                 * parentId : 0
                 * sort :
                 */

                private String id;
                private String name;
                private String parentId;
                private String sort;
                private List<?> children;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

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
                 * id : 899919864ee84faba953e6c435bfa0e8
                 * name : 本科
                 */

                private String id;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class ExperienceYearBean {
                /**
                 * id : 09262bb793484743af54627c3ee55283
                 * name : 2-5
                 */

                private String id;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
