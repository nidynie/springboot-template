//package com.meiliyaya.web.component;
//
//import com.github.qcloudsms.SmsSingleSender;
//import com.github.qcloudsms.SmsSingleSenderResult;
//import com.meiliyaya.web.common.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Slf4j
//@Component
//public class TencentSMSComponent {
//
//    @Value("${sms.tencent.appId}")
//    private int appId;
//
//    @Value("${sms.tencent.appKey}")
//    private String appKey;
//
//    @Value("${sms.smsSign}")
//    private String smsSign;
//
//    @Value("${sms.tencent.bindTemplateId}")
//    private int bindTemplateId;
//
//    @Value("${sms.tencent.resetTemplateId}")
//    private int resetTemplateId;
//
//    @Value("${sms.tencent.modifyTemplateId}")
//    private int modifyTemplateId;
//
//    @Value("${sms.tencent.noticeLetterId}")
//    private int noticeLetterId;
//
//
//    /**
//     * 绑定手机号码
//     *
//     * @param code
//     * @param phone
//     * @return
//     * @throws Exception
//     */
//    public boolean bindPhone(String code, String phone) throws Exception {
//        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
//        SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
//                bindTemplateId, new String[]{code}, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//        log.info("sms send result:{}", result);
//        return true;
//    }
//
//
//    /**
//     * 重置密码
//     *
//     * @param code
//     * @param phone
//     * @return
//     * @throws Exception
//     */
//    public boolean resetPWPhone(String code, String phone) throws Exception {
//        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
//        SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
//                resetTemplateId, new String[]{code}, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//        log.info("sms send result:{}", result);
//        return true;
//    }
//
//
//    /**
//     * 修改密码
//     *
//     * @param code
//     * @param phone
//     * @return
//     * @throws Exception
//     */
//    public boolean modifyPWPhone(String code, String phone) throws Exception {
//        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
//        SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
//                modifyTemplateId, new String[]{code}, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//        log.info("sms send result:{}", result);
//        return true;
//    }
//
//
//    public boolean noticeLetter(String params[], String phone) throws Exception {
//        if (ArrayUtils.isEmpty(params) || StringUtils.isBlank(phone)) {
//            log.error("通知书短信发送异常:params:{},phone:{}", params, phone);
//            return false;
//        }
//        return send(params, phone, noticeLetterId);
//    }
//
//    /**
//     * 通用短信发送
//     *
//     * @param params
//     * @param phone
//     * @return
//     */
//    public boolean send(String params[], String phone, Integer templateId) throws Exception {
//        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
//        SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
//                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
//        log.info("sms send result:{},phone:{},params:{},templateId:{}", result);
//        return true;
//    }
//
//
////    /**
////     * 短信单发
////     *
////     * @param params
////     * @param phone
////     * @return
////     * @throws Exception
////     */
////    public boolean SmsSingle(String[] params, String phone) throws Exception {
////
////        SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
////        SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
////                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
////        log.info("sms send result:{}", result);
////        return true;
////    }
//
////    /**
////     * 短信群发 (群发内容相同)
////     *
////     * @param params
////     * @param phone
////     * @return
////     * @throws Exception
////     */
////    public boolean SmsMulti(String params[], String phone[]) throws Exception {
////        SmsMultiSender msender = new SmsMultiSender(appId, appKey);
////        SmsMultiSenderResult result = msender.sendWithParam("86", phone,
////                templateId, params, smsSign, "", "");
////        log.info("sms multi send result:{}", result);
////        return true;
////    }
//}
