package com.baway.chilijie.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MacBook- on 2017/4/14.
 */
public class Classify implements Serializable{
    private List<Category> category;
    private List<GoodsBrief> goodsBrief;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<GoodsBrief> getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(List<GoodsBrief> goodsBrief) {
        this.goodsBrief = goodsBrief;
    }
    public class Category implements Serializable {
        private String cat_name;
        private List<Children> children;

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }
        public class Children implements Serializable{
            private String cat_name;
            private String id;

            public Children(String cat_name, String id) {
                this.cat_name = cat_name;
                this.id = id;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }
        }
    }
    public class GoodsBrief implements Serializable{
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
}
