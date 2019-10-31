package com.sample.alipay;

/**
 * 支付宝配置类
 * @author chuanyang.zcy
 */
public class AlipayConfig {

    /*-------------------------------------------
    |                沙箱环境配置                |
    ============================================*/

    /**
     * 商户APPID
     */
    public static String APPID = "2016091700534734";

    /**
     * 商户应用私钥 商户向支付宝发起请求支付宝验签
     */
    public static String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVy5QL/Js4Fp0A/YBBxa/WeptP1R0wo2GtchxdxhDpUh7NAEN6Yuc2TUALxjS51KvD7YslvxJcO4zxxrVwKKYAD7b0osgFpltxn+lGe4II0x3YP9uxgb11DEnzRKYNtCME4afBrK7RZkFtAHnZN8MUjKS0Iol1BIkBN2T+0rU00D7d8cTWZ0WruaKLCVReQ/kmioaSiRX/9IIq+5ISz2cAWC+TSiJ/Fsdso2p9/9q1JW4XsaGIHWZ/PmHkzHlj5M4SpxblgoU1mhuVRktaFG2bAtEKDHxhfhQcumjhlW1YWMC5rAEXrmrH/G0E77z+o0tkHxU/NXPmGTj6YXxu/qnNAgMBAAECggEBAIkxLcOYtLS3CU7Z3jjfDL+8BTklr5lXWeOYCmDcQarf2nPZFPQnVynFsK3X93xnSnWkvvdwOymcJOdfHde+cuLFgcQmvllmVoQHeIhkH+FjMgdX3SeC8vkbqzMRoaAVZqlW3uUj+eyw4aUqdW67goD0mvicVRaGc9BYYrh8VRgrYWHo4KW1F8Pgj4XmEtkx5VOpQGO09heYeqm2dols1SugLrNVo+BVBw317Deox2L+dG8pyzRv9frG0MczVqCmw6VVYwA7/kxIovZEWsVD7eWqyrbVtHQMQV7+WT1rf7saHG70/hdbtrQ8Ksi0ZiiX2SrCw7ab9EXCof8ERxakCUECgYEA6xob2iQNKg1wD6qPrfplZtWaBRUvEfET4hi3yGZR7IyrVDrmWH+8koT9dY2Xx87IS8SucgpfFg2qaAqtXNQUGk0OUiDqgSMLDL0doaxpheIozhSzSnZZvXSy7SbDwxNpslZwl/fo9Wqos6L0t0pJIz0QGZfbldVF7Ony5Xb6+NECgYEAoxxC64r9uawHJxbJrzI+GyUAvzEp8yYu85RxCejv1p4DP3b1yNIIVV23nP70XzGXUwhVxItmOyQVUblzbX68g7rpp01tyRpdNiWlA4+woeWOAuZ4XwOFqRTQd7nTjLOWIvQMpgxwUuqGlr4hSwHNBPnsB3DPrTlK2wRJzJJzYD0CgYEA1w9FFyEPYb51Gf+d+zoodA9FGw0rx6/Nzm9p/smKe2aaeyx99cqzhxxcdqIVi9NeaeD66psOLDBIB3Bs8ZOW6BBA32P44DRcMUT1zCTZSn19cjaBY8YrhSExY7qoLNSfRWHJEVrYIVd2Bw0z6k7r5G5BQggl+EPl+Thw1vMl1WECgYAAwbDNxudGZTXjSIfG+ESxe4p1WJUJO7QcDSQ5F4uFQ1ZghPy+4bqIKtcfM0+O4XwCNpevQbZHr/MpXeLbLytQsR1EWWeY9zZXefU5/wCiv53ZBZipWCrWjfIpnAMPNAYOQ3OLkaMzv0LAkEE3R9rbmw0tO6i69PkT6wEsocFHQQKBgEfuEUi0FjT6Yq2bjqeXLSIiWiOWRKkRaDta+6Ok6hGnopBxZDwDdxGJySDqAYpQTp4nPmT207sterKIiHOl3Qx9N8H+1Hhx88QBjr+QWlNYl0C8nLesd7tS1CrzVOqlYqcHDfELkGKcnNve2SH5APa/QqhEOSpdVF3e2oMygJ31";

    /**
     * 商户应用公钥 商户上传支付宝 获取支付宝公钥
     */
    public static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlcuUC/ybOBadAP2AQcWv1nqbT9UdMKNhrXIcXcYQ6VIezQBDemLnNk1AC8Y0udSrw+2LJb8SXDuM8ca1cCimAA+29KLIBaZbcZ/pRnuCCNMd2D/bsYG9dQxJ80SmDbQjBOGnwayu0WZBbQB52TfDFIyktCKJdQSJATdk/tK1NNA+3fHE1mdFq7miiwlUXkP5JoqGkokV//SCKvuSEs9nAFgvk0oifxbHbKNqff/atSVuF7GhiB1mfz5h5Mx5Y+TOEqcW5YKFNZoblUZLWhRtmwLRCgx8YX4UHLpo4ZVtWFjAuawBF65qx/xtBO+8/qNLZB8VPzVz5hk4+mF8bv6pzQIDAQAB";


    /**
     * 支付宝公钥 支付宝通知或查询支付结果时 支付宝返回信息商户端验签
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvTpue54bMn0k1O81gsC5GxyMMTycbr+Lf0ErSrZXxvYCqbQZXOlANZoUVhYxjkoI0jg06scgsnhy91ycC9VeebQgMQox0AbSWuWPuo39b83dfljcxCtp7WgRl7CYtpPteEf9mlBZuzzVCBRxGf5jfBgkTbNxps5NFYsuLhdRtptDXLe1F8okReNDQdU3ssuKbnKQl34KjZTWbKOO4+BIj76JN2CV76+7Kl5LMf/+/fl8FS4FFYsrgd0ndEABNSIj9CB6ukd/vEUcu3GfSlM2zO5F3JZFG9FMKGJn2javTDF6g5vrN28jqvtMY3Yuvps13Dzi9MnsbElfhlThB+ZROQIDAQAB";

    /**
     * 签名类型
     */
    public static String SIGNTYPE = "RSA2";

    /**
     * 请求网关地址
     */
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://47.98.187.163:8080/alipay/notify";

    /**
     * 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    public static String return_url = "http://47.98.187.163:8080/alipay/return";

    /**
     * 编码
     */
    public static String CHARSET = "UTF-8";

    /**
     * 返回格式
     */
    public static String FORMAT = "json";

}
