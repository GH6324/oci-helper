package com.yohann.ocihelper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.oracle.bmc.core.ComputeClient;
import com.oracle.bmc.core.VirtualNetworkClient;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.identity.model.*;
import com.oracle.bmc.identity.requests.*;
import com.oracle.bmc.identity.responses.ListDomainsResponse;
import com.oracle.bmc.identitydomains.IdentityDomainsClient;
import com.oracle.bmc.identitydomains.model.PasswordPolicies;
import com.oracle.bmc.identitydomains.model.PasswordPolicy;
import com.oracle.bmc.identitydomains.requests.ListPasswordPoliciesRequest;
import com.oracle.bmc.identitydomains.requests.PutPasswordPolicyRequest;
import com.oracle.bmc.identitydomains.responses.ListPasswordPoliciesResponse;
import com.oracle.bmc.identitydomains.responses.PutPasswordPolicyResponse;
import com.yohann.ocihelper.bean.dto.SysUserDTO;
import com.yohann.ocihelper.bean.entity.OciUser;
import com.yohann.ocihelper.config.OracleInstanceFetcher;
import com.yohann.ocihelper.service.IInstanceService;
import com.yohann.ocihelper.utils.CommonUtils;
import com.yohann.ocihelper.utils.CustomExpiryGuavaCache;
import com.yohann.ocihelper.utils.OciUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import jakarta.annotation.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@MockBean(ServerEndpointExporter.class) // Mock 掉，不让它真正注册
class OciHelperApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(OciHelperApplicationTests.class);

    @Resource
    private IInstanceService instanceService;
    @Resource
    private CustomExpiryGuavaCache<String, Object> customCache;

    @Test
    void contextLoads() throws IOException {
        String baseDir = "C:\\Users\\yohann_fan\\Desktop\\test\\oci-helper\\";
//        String baseDir = "C:\\Users\\Yohann\\Desktop\\";
        String s = FileUtil.readString(baseDir + "frankfurt.txt", Charset.defaultCharset());
        List<OciUser> ociUsers = CommonUtils.parseConfigContent(s);
        OciUser ociUser = ociUsers.get(0);

//        System.out.println(ociUser);

//        String instanceId = "ocid1.instance.oc1.sa-saopaulo-1.xxx";

        SysUserDTO sysUserDTO = SysUserDTO.builder()
                .ociCfg(SysUserDTO.OciCfg.builder()
                        .userId(ociUser.getOciUserId())
                        .tenantId(ociUser.getOciTenantId())
                        .region(ociUser.getOciRegion())
                        .fingerprint(ociUser.getOciFingerprint())
                        .privateKeyPath(baseDir + ociUser.getOciKeyPath())
                        .build())
                .username(ociUser.getUsername())
                .build();

        System.out.println(sysUserDTO);

        try (OracleInstanceFetcher fetcher = new OracleInstanceFetcher(sysUserDTO);) {
            IdentityClient identityClient = fetcher.getIdentityClient();
            String compartmentId = fetcher.getCompartmentId();
            ComputeClient computeClient = fetcher.getComputeClient();
            VirtualNetworkClient virtualNetworkClient = fetcher.getVirtualNetworkClient();
            IdentityDomainsClient identityDomainsClient = fetcher.getIdentityDomainsClient();

            OciUtils.Result currentRecipients = OciUtils.getCurrentRecipients(fetcher);
            System.out.println(currentRecipients);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void test2() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1000; i++) {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println("任务完成");
                    return "结果";
                });
            }
        }

        System.out.println(CommonUtils.getLatestVersion());
    }

    @Test
    void test3() throws InterruptedException {
        // 添加键值对，分别设置不同的过期时间
        customCache.put("key1", "value1", 2000); // 2秒
        customCache.put("key2", "value2", 5000); // 5秒

        // 获取值
        System.out.println("Key1: " + customCache.get("key1")); // 立即获取
        Thread.sleep(3000); // 等待3秒
        System.out.println("Key1: " + customCache.get("key1")); // 过期，返回null
        System.out.println("Key2: " + customCache.get("key2")); // 未过期，返回value2
    }

}
