package cc.gukeer.smartRing.persistence.entity.extension;

import cc.gukeer.smartRing.persistence.entity.DeviceRing;
import cc.gukeer.smartRing.persistence.entity.DeviceStation;
import cc.gukeer.smartRing.persistence.entity.Student;

/**
 * Created by conn on 16-10-11.
 */
public class ExtensionDeviceRing extends DeviceRing {
    private Student student;

    private DeviceStation deviceStation;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public DeviceStation getDeviceStation() {
        return deviceStation;
    }

    public void setDeviceStation(DeviceStation deviceStation) {
        this.deviceStation = deviceStation;
    }
}
