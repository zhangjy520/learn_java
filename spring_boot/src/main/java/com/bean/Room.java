package com.bean;

/**
 * Created by Administrator on 2017/6/27.
 * 教室
 */
public class Room {
    private String roomId;//1
    private String roomName;//301
    private Integer renShu;

    public Room(String roomId, String roomName, Integer renShu) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.renShu = renShu;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRenShu() {
        return renShu;
    }

    public void setRenShu(Integer renShu) {
        this.renShu = renShu;
    }
}
