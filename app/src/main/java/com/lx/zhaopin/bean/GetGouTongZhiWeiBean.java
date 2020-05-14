package com.lx.zhaopin.bean;

import com.lx.zhaopin.http.CommonBean;

public class GetGouTongZhiWeiBean extends CommonBean {


    /**
     * company : {"city":{"id":"","name":"名称"},"district":{"id":"","name":"名称"},"id":"","lat":"纬度","lng":"经度","location":"","logo":"","name":""}
     * id : 1
     * name : 职位名称
     * positionType : 1
     */

    private CompanyBean company;
    private String id;
    private String name;
    private String positionType;

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

        private CityBean city;
        private DistrictBean district;
        private String id;
        private String lat;
        private String lng;
        private String location;
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
}
