package com.yohann.ocihelper.bean.response.oci.cfg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: oci-helper
 * @package: com.yohann.ocihelper.bean.response
 * @className: OciCfgDetailsRsp
 * @author: Yohann
 * @date: 2024/11/13 23:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OciCfgDetailsRsp {

    private String userId;
    private String tenantId;
    private String fingerprint;
    private String privateKeyPath;
    private String region;
    private List<InstanceInfo> instanceList;
    private List<CfCfg> cfCfgList;
    private List<NetLoadBalancer> nlbList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InstanceInfo {
        private String ocId;
        private String region;
        private String name;
        private List<String> publicIp;
        private String shape;
        private Integer enableChangeIp = 0;
        private String ocpus;
        private String memory;
        private String bootVolumeSize;
        private String createTime;
        private String state;
        private String availabilityDomain;
        private List<InstanceVnicInfo> vnicList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InstanceVnicInfo {
        private String vnicId;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CfCfg {
        private String cfCfgId;
        private String domain;
    }

    @Data
    public static class NetLoadBalancer {
        private String name;
        private String status;
        private String publicIp;
    }
}
