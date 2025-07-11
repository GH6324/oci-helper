package com.yohann.ocihelper.service;

import com.yohann.ocihelper.bean.dto.SysUserDTO;
import com.yohann.ocihelper.bean.params.sys.*;
import com.yohann.ocihelper.bean.response.sys.GetGlanceRsp;
import com.yohann.ocihelper.bean.response.sys.GetSysCfgRsp;
import com.yohann.ocihelper.bean.response.sys.LoginRsp;

public interface ISysService {

    void sendMessage(String message);

    LoginRsp login(LoginParams params);

    void updateSysCfg(UpdateSysCfgParams params);

    GetSysCfgRsp getSysCfg();

    boolean getEnableMfa();

    void backup(BackupParams params);

    void recover(RecoverParams params);

    GetGlanceRsp glance();

    SysUserDTO getOciUser(String ociCfgId);

    SysUserDTO getOciUser(String ociCfgId, String region, String compartmentId);

    void checkMfaCode(String mfaCode);

    void updateVersion();
}
