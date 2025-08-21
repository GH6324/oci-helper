package com.yohann.ocihelper.bean.constant;

/**
 * <p>
 * CacheConstant
 * </p >
 *
 * @author yuhui.fan
 * @since 2025/1/2 18:54
 */
public class CacheConstant {
    public static final String OCI_TRAFFIC_NAMESPACE = "oci_vcn";
    public static final String OCI_TRAFFIC_QUERY_IN = "VnicToNetworkBytes[1440m]{resourceId = \"%s\"}.sum()";
    public static final String OCI_TRAFFIC_QUERY_OUT = "VnicFromNetworkBytes[1440m]{resourceId = \"%s\"}.sum()";
    public static final String TASK_CRON = "0 0 0 * * ?";
    public static final String DAILY_BROADCAST_TASK_ID = "daily_broadcast_task";
    public static final String PREFIX_BOOT_VOLUME_PAGE = "bootVolume_page_";
    public static final String PREFIX_INSTANCE_PAGE = "instance_page_";
    public static final String PREFIX_NETWORK_LOAD_BALANCER = "network_load_balancer_";
    public static final String PREFIX_VCN_PAGE = "vcn_page_";
    public static final String PREFIX_TENANT_INFO = "tenant_info_";
    public static final String PREFIX_CF_DNS_RECORDS = "cf_dns_records_";
    public static final String PREFIX_INGRESS_SECURITY_RULE_PAGE = "ingress_security_rule_page_";
    public static final String PREFIX_EGRESS_SECURITY_RULE_PAGE = "egress_security_rule_page_";
    public static final String PREFIX_INGRESS_SECURITY_RULE_MAP = "ingress_security_rule_map_";
    public static final String PREFIX_EGRESS_SECURITY_RULE_MAP = "egress_security_rule_map_";
    public static final String PREFIX_DAILY_BROADCAST_CRON_ID = "daily_broadcast_cron_id_";
    public static final String PREFIX_PUSH_VERSION_UPDATE_MSG = "push_version_update_msg_";
    public static final String PREFIX_TENANT_REGION = "tenant_region_";
    public static final String PREFIX_TENANT_COMPARTMENT_ID = "tenant_compartment_id_";
}
