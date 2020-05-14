package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class ShenQingListBean extends CommonBean {


    /**
     * dataList : [{"applyDate":"","chatApplyStatus":"1","company":{"city":{"id":"","name":"名称"},"district":{"id":"","name":"名称"},"id":"","lat":"","lng":"","logo":"","name":""},"id":"","position":{"duty":"","education":{"id":"","name":"名称"},"experienceYear":{"id":"","name":"名称"},"id":"","maxSalary":"","minSalary":"","name":"职位名称","positionType":"1","workfare":""}}]
     * totalCount : 10
     * totalPage : 2
     */

    private int totalCount;
    private int totalPage;
    private List<DataListBean> dataList;

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
         * applyDate :
         * chatApplyStatus : 1
         * company : {"city":{"id":"","name":"名称"},"district":{"id":"","name":"名称"},"id":"","lat":"","lng":"","logo":"","name":""}
         * id :
         * position : {"duty":"","education":{"id":"","name":"名称"},"experienceYear":{"id":"","name":"名称"},"id":"","maxSalary":"","minSalary":"","name":"职位名称","positionType":"1","workfare":""}
         */

        private String applyDate;
        private String chatApplyStatus;
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
             * city : {"id":"","name":"名称"}
             * district : {"id":"","name":"名称"}
             * id :
             * lat :
             * lng :
             * logo :
             * name :
             */

            private CityBean city;
            private DistrictBean district;
            private String id;
            private String lat;
            private String lng;
            private String logo;
            private String name;

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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
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

            public static class CityBean {
                /**
                 * id :
                 * name : 名称
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

            public static class DistrictBean {
                /**
                 * id :
                 * name : 名称
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

        public static class PositionBean {
            /**
             * duty :
             * education : {"id":"","name":"名称"}
             * experienceYear : {"id":"","name":"名称"}
             * id :
             * maxSalary :
             * minSalary :
             * name : 职位名称
             * positionType : 1
             * workfare :
             */

            private String duty;
            private EducationBean education;
            private ExperienceYearBean experienceYear;
            private String id;
            private String maxSalary;
            private String minSalary;
            private String name;
            private String positionType;
            private String workfare;
            private String opened;

            public String getOpened() {
                return opened;
            }

            public void setOpened(String opened) {
                this.opened = opened;
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

            public static class EducationBean {
                /**
                 * id :
                 * name : 名称
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
                 * id :
                 * name : 名称
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
