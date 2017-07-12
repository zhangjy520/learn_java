package cn.gukeer.test;

/**
 * Created by alpha on 17-6-23.
 */
public enum State {
    Normal("正常态", 0), Update("已更新", 2), Deleted("已删除", 3), Fired("已屏蔽", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private State(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (State c : State .values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    static class Test {
        public static void main(String[] args) {
            System.out.println("EnumTest.FRI 的 value = " + State.getName(2));
        }
    }
}
