package cn.gukeer.platform.persistence.dao;

import java.util.List;
import java.util.Set;

import cn.gukeer.platform.persistence.entity.sync.SyncUser;

public interface SyncUserMapper {
   List<SyncUser> findInsertSyncUser();
   
   List<SyncUser> findModifySyncUser();
   
   List<SyncUser> findDeleteSyncUser();
   
   void  deleteUsered(List ids);
}
