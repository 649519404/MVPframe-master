package com.crazypeople.common.sugar;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by 曲志强 on 2017/1/28.
 */

public class RoomType extends SugarRecord implements Serializable {

    String name;
    String type;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
