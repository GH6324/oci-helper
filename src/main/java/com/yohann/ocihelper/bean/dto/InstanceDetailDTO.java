package com.yohann.ocihelper.bean.dto;

import com.oracle.bmc.core.model.Instance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * InstanceDetailDTO
 * </p >
 *
 * @author yuhui.fan
 * @since 2024/11/7 14:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstanceDetailDTO {

    private String taskId;
    private boolean isNoShape = false;
    private boolean isSuccess = false;
    private boolean isOut = false;
    private boolean isNoPubVcn = false;
    private boolean isTooManyReq = false;
    private boolean isDie = false;
    private String publicIp;
    private String image;
    private String shape;
    private String architecture;
    private String username;
    private String region;
    private Float ocpus = 1F;
    private Float memory = 6F;
    private Long disk = 50L;
    private String rootPassword;
    private long createNumbers = 0;
    Instance instance;

}
