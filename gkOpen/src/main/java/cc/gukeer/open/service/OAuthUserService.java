package cc.gukeer.open.service;


import cc.gukeer.open.persistence.entity.ChangeStateUser;

public interface OAuthUserService {

    ChangeStateUser getByAccountAndPwd(String account, String pwd);

}
