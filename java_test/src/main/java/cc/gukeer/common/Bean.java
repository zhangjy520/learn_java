/**
 * Copyright 2017 bejson.com
 */
package cc.gukeer.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.*;

class JsonRootBean implements Serializable {

    public String message;
    public String status;
    public List<Special> data;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setData(List<Special> data) {
        this.data = data;
    }

    public List<Special> getData() {
        return data;
    }

}

class Special implements Serializable {
    public List<Special> children;
    public Data data;

    public List<Special> getChildren() {
        return children;
    }

    public void setChildren(List<Special> children) {
        this.children = children;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}

class Data implements Serializable {

    public String id;
    public String groupId;
    public String text;
    public String readOnly;
    public String level;
    public String status;
    public String created;
    public String lastUpdatePerson;
    public String owner;
    public String type;
    public String note;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly;
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreated() {
        return created;
    }

    public void setLastUpdatePerson(String lastUpdatePerson) {
        this.lastUpdatePerson = lastUpdatePerson;
    }

    public String getLastUpdatePerson() {
        return lastUpdatePerson;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

}


class Children implements Serializable {

    public Data data;
    public List<Special> children;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setChildren(List<Special> children) {
        this.children = children;
    }

    public List<Special> getChildren() {
        return children;
    }

}

class TestB {
    private static List<Data> fullList = new ArrayList();

    public static void main(String[] args) {
        String dataList = "{\"message\":\"success\",\"status\":0,\"data\":[{\"data\":{\"id\":279809,\"groupId\":789,\"text\":\"建站平台\",\"readOnly\":\"\",\"level\":\"\",\"status\":0,\"created\":1509950097000,\"lastUpdatePerson\":\"jiangyaruo\",\"owner\":\"\",\"type\":\"root\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":253368,\"groupId\":613,\"text\":\"智能拼接-素材库获取原图\",\"readOnly\":\"\",\"level\":\"\",\"status\":0,\"created\":1501500395000,\"lastUpdatePerson\":\"v_douyali\",\"owner\":\"\",\"type\":\"root\",\"note\":\"\"},\"children\":[{\"data\":{\"id\":253372,\"groupId\":613,\"text\":\"SETUP\",\"readOnly\":\"\",\"level\":\"\",\"status\":0,\"created\":1501062581000,\"lastUpdatePerson\":\"\",\"owner\":\"\",\"type\":\"setup\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":253369,\"groupId\":613,\"text\":\"test\",\"readOnly\":2,\"level\":1,\"status\":1,\"created\":1501063836000,\"lastUpdatePerson\":\"v_douyali\",\"owner\":\"v_douyali\",\"type\":\"case\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":253391,\"groupId\":613,\"text\":\"id不存在\",\"readOnly\":2,\"level\":1,\"status\":1,\"created\":1501066315000,\"lastUpdatePerson\":\"v_douyali\",\"owner\":\"v_douyali\",\"type\":\"case\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":253397,\"groupId\":613,\"text\":\"ucid不合法\",\"readOnly\":2,\"level\":1,\"status\":1,\"created\":1501063554000,\"lastUpdatePerson\":\"v_douyali\",\"owner\":\"v_douyali\",\"type\":\"case\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":256232,\"groupId\":613,\"text\":\"onlie\",\"readOnly\":2,\"level\":1,\"status\":1,\"created\":1501500395000,\"lastUpdatePerson\":\"v_douyali\",\"owner\":\"v_douyali\",\"type\":\"case\",\"note\":\"\"},\"children\":[]},{\"data\":{\"id\":253374,\"groupId\":613,\"text\":\"TEARDOWN\",\"readOnly\":\"\",\"level\":\"\",\"status\":0,\"created\":1501062581000,\"lastUpdatePerson\":\"\",\"owner\":\"\",\"type\":\"teardown\",\"note\":\"\"},\"children\":[]}]}]}";

        Gson gson = new Gson();
        JsonRootBean viewList = gson.fromJson(dataList,
                new TypeToken<JsonRootBean>() {
                }.getType());

        List<Special> dataListI = viewList.getData();
        addBean(dataListI);

        Map<String, List<Data>> map = new HashMap<>();

        for (Data data : fullList) {
            if (map.containsKey(data.getType())) {
                map.get(data.getType()).add(data);
            } else {
                List<Data> l = new ArrayList<>();
                l.add(data);
                map.put(data.getType(), l);
            }
        }

        for (String key : map.keySet()) {
            System.out.println("case=" + key + "的人是：" + map.get(key).size() + "人");
        }

    }

    public static void addBean(List<Special> speList) {

        for (Special sp : speList) {
            fullList.add(sp.getData());
            if (sp.getChildren().size() > 0) {
                addBean(sp.getChildren());
            }
        }


    }
}