package cc.gukeer.support.layout.persistence.entity;

import java.io.Serializable;

public class Headbar implements Serializable {
    private Integer id;

    private String css;

    private String html;

    private String js;

    private Integer isLogin;

    private String appName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css == null ? null : css.trim();
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }

    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js == null ? null : js.trim();
    }

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Headbar other = (Headbar) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCss() == null ? other.getCss() == null : this.getCss().equals(other.getCss()))
            && (this.getHtml() == null ? other.getHtml() == null : this.getHtml().equals(other.getHtml()))
            && (this.getJs() == null ? other.getJs() == null : this.getJs().equals(other.getJs()))
            && (this.getIsLogin() == null ? other.getIsLogin() == null : this.getIsLogin().equals(other.getIsLogin()))
            && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCss() == null) ? 0 : getCss().hashCode());
        result = prime * result + ((getHtml() == null) ? 0 : getHtml().hashCode());
        result = prime * result + ((getJs() == null) ? 0 : getJs().hashCode());
        result = prime * result + ((getIsLogin() == null) ? 0 : getIsLogin().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", css=").append(css);
        sb.append(", html=").append(html);
        sb.append(", js=").append(js);
        sb.append(", isLogin=").append(isLogin);
        sb.append(", appName=").append(appName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}