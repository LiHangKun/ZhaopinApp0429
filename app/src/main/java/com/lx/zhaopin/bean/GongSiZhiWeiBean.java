package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

import java.util.List;

public class GongSiZhiWeiBean extends CommonBean {


    private List<DataListBean> dataList;

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * city : {"id":"","name":"名称"}
         * company : {"city":{"id":"","name":"名称"},"district":{"id":"","name":"名称"},"id":"","lat":"纬度","lng":"经度","location":"","logo":"","name":""}
         * education : {"id":"","name":"名称"}
         * experienceYear : {"id":"","name":"名称"}
         * id :
         * location :
         * maxSalary :
         * minSalary :
         * name : 职位名称
         * positionType : 1
         */

        private CityBean city;
        private CompanyBean company;
        private EducationBean education;
        private ExperienceYearBean experienceYear;
        private String id;
        private String location;
        private String maxSalary;
        private String minSalary;
        private String workfare;

        public String getWorkfare() {
            return workfare;
        }

        public void setWorkfare(String workfare) {
            this.workfare = workfare;
        }

        private String name;
        private String positionType;

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

        public String getPositionType() {
            return positionType;
        }

        public void setPositionType(String positionType) {
            this.positionType = positionType;
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

        public static class CompanyBean {
            /**
             * city : {"id":"","name":"名称"}
             * district : {"id":"","name":"名称"}
             * id :
             * lat : 纬度
             * lng : 经度
             * location :
             * logo :
             * name :
             */

            private CityBeanX city;
            private DistrictBean district;
            private String id;
            private String lat;
            private String lng;
            private String location;
            private String logo;
            private String name;

            public CityBeanX getCity() {
                return city;
            }

            public void setCity(CityBeanX city) {
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

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
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

            public static class CityBeanX {
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
