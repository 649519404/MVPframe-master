package com.crazypeople.fuc.main.entity;

public  class DataBean {
            private int uid;
            private int rid;
            private String title;
            public int getUid() {
                return uid;
            }
            public void setUid(int uid) {
                this.uid = uid;
            }
            public int getRid() {
                return rid;
            }
            public void setRid(int rid) {
                this.rid = rid;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getInfo() {
                return info;
            }
            public void setInfo(String info) {
                this.info = info;
            }
            public String getStatus() {
                return status;
            }
            public void setStatus(String status) {
                this.status = status;
            }
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            private String info;
            private String status;
            private String type;
            private String nickName;
            private String count;
            private String img;
            public String getImg() {
                return img;
            }
            public void setImg(String img) {
                this.img = img;
            }
            public String getCount() {
                return count;
            }
            public void setCount(String count) {
                this.count = count;
            }
            public String getNickName() {
                return nickName;
            }
            public void setNickName(String nickName) {
                this.nickName = nickName;
            }
        }