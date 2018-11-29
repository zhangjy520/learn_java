package nettySDK.API;

/**
 * Created by lx on 2017/9/15.
 */
public class RegisterProtocol {
    private String id;
    private String name;
    private Long time;
    private int protocolName;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(int protocolName) {
        this.protocolName = protocolName;
    }
}
