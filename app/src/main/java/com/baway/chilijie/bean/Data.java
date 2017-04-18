package com.baway.chilijie.bean;

import java.util.List;

/**
 * Created by MacBook- on 2017/4/12.
 */
public class Data {
    private MyInfo activityInfo;
    private List<Ad1Info> ad1;
    private List<Ad5Info> ad5;
    private List<BestSellers> bestSellers;
    private List<DefaultGoodsList> defaultGoodsList;
    private List<Subjects> subjects;

    public MyInfo getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(MyInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    public class MyInfo{
        private List<MyInfoList> activityInfoList;

        public List<MyInfoList> getActivityInfoList() {
            return activityInfoList;
        }

        public void setActivityInfoList(List<MyInfoList> activityInfoList) {
            this.activityInfoList = activityInfoList;
        }
        public class MyInfoList{
           private String activityImg;

            public String getActivityImg() {
                return activityImg;
            }

            public void setActivityImg(String activityImg) {
                this.activityImg = activityImg;
            }
        }
    }
    public List<Ad1Info> getAd1() {
        return ad1;
    }

    public void setAd1(List<Ad1Info> ad1) {
        this.ad1 = ad1;
    }

    public List<Ad5Info> getAd5() {
        return ad5;
    }

    public void setAd5(List<Ad5Info> ad5) {
        this.ad5 = ad5;
    }

    public List<BestSellers> getBestSellers() {
        return bestSellers;
    }

    public void setBestSellers(List<BestSellers> bestSellers) {
        this.bestSellers = bestSellers;
    }

    public List<DefaultGoodsList> getDefaultGoodsList() {
        return defaultGoodsList;
    }

    public void setDefaultGoodsList(List<DefaultGoodsList> defaultGoodsList) {
        this.defaultGoodsList = defaultGoodsList;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }
    public class Ad1Info{
    private String image;
    private String ad_type_dynamic_data;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAd_type_dynamic_data() {
            return ad_type_dynamic_data;
        }

        public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
            this.ad_type_dynamic_data = ad_type_dynamic_data;
        }
    }
    public class Ad5Info{
        private String image;
        private String ad_type_dynamic_data;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAd_type_dynamic_data() {
            return ad_type_dynamic_data;
        }

        public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
            this.ad_type_dynamic_data = ad_type_dynamic_data;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    public class BestSellers{
     private List<GoodsList> goodsList;
     private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<GoodsList> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsList> goodsList) {
            this.goodsList = goodsList;
        }
        public class GoodsList{
            private String goods_name;
            private String shop_price;
            private String market_price;
            private String goods_img;

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }
        }
    }
    public class DefaultGoodsList{
        private String efficacy;
        private String goods_name;
        private String shop_price;
        private String market_price;
        private String goods_img;

        public String getEfficacy() {
            return efficacy;
        }

        public void setEfficacy(String efficacy) {
            this.efficacy = efficacy;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }
    }
    public class Subjects{
        private List<GoodsList> goodsList;
        private String image;

        public List<GoodsList> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsList> goodsList) {
            this.goodsList = goodsList;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
        public class GoodsList{
            private String goods_name;
            private String shop_price;
            private String market_price;
            private String goods_img;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
