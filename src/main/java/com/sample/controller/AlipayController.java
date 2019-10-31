package com.sample.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.alipay.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 支付宝
 * @author chuanyang.zcy
 */
@Slf4j
@Controller
@RequestMapping(path = "/alipay")
public class AlipayController {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 手机网站支付
     * http://47.98.187.163:8080/alipay/wap_pay?subject=wap测试订单&total_amount=10.00
     */
    @RequestMapping(path = "/wap_pay")
    public void wapPay(HttpServletRequest request, HttpServletResponse response) {
        // 商户订单号，商户网站订单系统中唯一订单号 必填
        String outTradeNo = genOutTradeNo();
        log.info("wap pay outTradeNo: {}", outTradeNo);
        // 订单名称 必填
        String subject = request.getParameter("subject");
        // 付款金额 必填
        String totalAmount = request.getParameter("total_amount");
        // 商品描述 可空
        String body = request.getParameter("body");
        // 超时时间 可空
        String timeoutExpress = "2m";
        // 销售产品码 必填
        String productCode = "QUICK_WAP_WAY";

        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        // 调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount(totalAmount);
        model.setBody(body);
        model.setTimeoutExpress(timeoutExpress);
        model.setProductCode(productCode);
        alipayRequest.setBizModel(model);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form;
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            // 直接将完整的表单html输出到页面
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 电脑网站支付
     * http://47.98.187.163:8080/alipay/page_pay?subject=page测试订单&total_amount=10.00
     */
    @RequestMapping(path = "/page_pay")
    public void pagePay(HttpServletRequest request, HttpServletResponse response) {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号 必填
        String outTradeNo = genOutTradeNo();
        log.info("page pay outTradeNo: {}", outTradeNo);
        //付款金额 必填
        String totalAmount = request.getParameter("total_amount");
        //订单名称 必填
        String subject = request.getParameter("subject");
        //商品描述 可空
        String body = request.getParameter("body");
        // 超时时间 可空
        String timeoutExpress = "2m";
        // 销售产品码 必填 注：目前仅支持FAST_INSTANT_TRADE_PAY
        String productCode = "FAST_INSTANT_TRADE_PAY";

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        model.setBody(body);
        model.setTimeoutExpress(timeoutExpress);
        model.setProductCode(productCode);
        alipayRequest.setBizModel(model);

        // form表单生产
        String form;
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            // 直接将完整的表单html输出到页面
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 查询支付结果
     * http://47.98.187.163:8080/alipay/query?out_trade_no=1
     */
    @RequestMapping(path = "/query")
    @ResponseBody
    public String query(HttpServletRequest request) {
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String outTradeNo = request.getParameter("out_trade_no");
        //支付宝交易号
        String tradeNo = request.getParameter("trade_no");

        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setTradeNo(tradeNo);
        alipayRequest.setBizModel(model);

        AlipayTradeQueryResponse alipayResponse;
        try {
            alipayResponse = client.execute(alipayRequest);
        } catch (AlipayApiException e) {
            log.error(e.getMessage());
            return "";
        }

        return alipayResponse.getBody();
    }

    /**
     * 接受支付宝notify
     */
    @RequestMapping(path = "/notify")
    @ResponseBody
    public String receiveNotify(HttpServletRequest request) throws AlipayApiException, JsonProcessingException {
        // 获取支付宝POST过来反馈信息
        Map<String,String> params = getParamsFromAlipayRequest(request);

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
        // 商户订单号
        String outTradeNo = request.getParameter("out_trade_no");
        // 支付宝交易号
        String tradeNo = request.getParameter("trade_no");
        // 交易状态
        String tradeStatus = request.getParameter("trade_status");

        // 计算得出通知验证结果
        boolean verifyResult = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);

        String notifyParams = mapper.writeValueAsString(params);
        log.info("notify params: {} verifyResult: {}", notifyParams, verifyResult);

        if(verifyResult){
            // 校验通知数据的正确性
            // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
            // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
            // 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
            // 4、验证app_id是否为该商户本身

            if(StringUtils.equals("WAIT_BUYER_PAY", tradeStatus)) {
                // TODO 交易创建，等待买家付款

            } else if (StringUtils.equals("TRADE_CLOSED", tradeStatus)) {
                // TODO 未付款交易超时关闭，或支付完成后全额退款

            } else if (StringUtils.equals("TRADE_SUCCESS", tradeStatus)) {
                // TODO 交易支付成功

            } else if (StringUtils.equals("TRADE_FINISHED", tradeStatus)) {
                // TODO 交易结束，不可退款

            }

            //验证成功
            return "success";
        } else {
            //验证失败
            return "fail";
        }
    }

    /**
     * 接收支付宝支return
     */
    @RequestMapping(path = "/return")
    @ResponseBody
    public String receiveReturn(HttpServletRequest request) throws AlipayApiException, JsonProcessingException {
        // 获取支付宝GET过来反馈信息
        Map<String,String> params = getParamsFromAlipayRequest(request);

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
        // 商户订单号
        String outTradeNo = request.getParameter("out_trade_no");
        // 支付宝交易号
        String tradeNo = request.getParameter("trade_no");

        //计算得出通知验证结果
        boolean verifyResult = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

        String returnParams = mapper.writeValueAsString(params);
        log.info("return params: {} verifyResult: {}", returnParams, verifyResult);

        if(verifyResult){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("outTradeNo", outTradeNo);
            jsonObject.put("tradeNo", tradeNo);
            // 验证成功
            return "验证成功" + jsonObject.toString();
        }else{
            // 验证成功
            return "验证失败";
        }
    }

    private String genOutTradeNo() {
        DateTime dateTime = new DateTime(System.currentTimeMillis());
        return dateTime.toString("yyyyMMddHHmmssSSS", Locale.CHINESE);
    }

    private Map<String,String> getParamsFromAlipayRequest(HttpServletRequest request) {
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    public static void main(String[] args) {
        System.out.println(new AlipayController().genOutTradeNo());
    }

}
