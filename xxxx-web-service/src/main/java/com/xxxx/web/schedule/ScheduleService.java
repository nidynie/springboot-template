package com.xxxx.web.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@EnableScheduling
public class ScheduleService {

//
//    @Autowired
//    private AccountChangeDetailService accountChangeDetailService;
//
//    @Autowired
//    private AgentAccountService agentAccountService;
//
//
//    /**
//     * 每天晚上零点计算 叠加代理商收入
//     */
//    @Scheduled(cron = "0 0 0 * * ?")
//    public void dealAccountDetail() {
//
//        log.info("开始统计代理商微信收入");
//
//        LambdaQueryWrapper<AccountChangeDetailEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(AccountChangeDetailEntity::getChangeType, AccountChangeTypeEnum.income.getType())
//                .eq(AccountChangeDetailEntity::getStatus, AccountChangeStatusEnum.un_count.getDesc())
//                .le(AccountChangeDetailEntity::getReleaseTime, new Date());
//
//        List<AccountChangeDetailEntity> changeDetailEntities = accountChangeDetailService.getBaseMapper()
//                .selectList(lambdaQueryWrapper);
//
//        if (CollectionUtils.isEmpty(changeDetailEntities)) {
//            log.info("未查询到符合条件的微信收入记录，程序结束执行");
//            return ;
//        }
//
//        log.info("成功读取微信订单记录:{}", JSON.toJSONString(changeDetailEntities));
//
//        Map<String, BigDecimal> agentAccountMap = new HashMap<>();
//
//        Map<String, List<AccountChangeDetailEntity>> accountMap = changeDetailEntities.stream().collect(Collectors.groupingBy(AccountChangeDetailEntity::getAccountId));
//
//        accountMap.forEach((k, v) -> {
//
//            if (v.size() == 1) {
//                agentAccountMap.put(k, v.get(0).getChangeCount());
//                return;
//            }
//            BigDecimal total = new BigDecimal("0");
//            for (AccountChangeDetailEntity detailEntity : v) {
//                total = total.add(detailEntity.getChangeCount());
//            }
//            agentAccountMap.put(k, total);
//        });
//
//        agentAccountMap.forEach((k, v) -> {
//            try {
//                log.info("准备给账户:{} 加钱，金额:{}", k, v.toPlainString());
//                agentAccountService.addWxChangeCount(k, v);
//                log.info("账户:{} 金钱处理完成", k);
//            } catch (Exception e) {
//                log.error("代理商账户加钱时出错,代理商id：{}，金额：{}，错误信息:{}", k, v.toPlainString(), e.getMessage(), e);
//            }
//        });
//
//        log.info("当前所有账户的金额已经记录完毕,准备标记变更记录状态");
//
//        List<AccountChangeDetailEntity> updateEntities = changeDetailEntities.stream().map(e -> e.getId()).map(s -> {
//            AccountChangeDetailEntity updateEntity = new AccountChangeDetailEntity();
//            updateEntity.setStatus(AccountChangeStatusEnum.counted.getType());
//            updateEntity.setId(s);
//            return updateEntity;
//        }).collect(Collectors.toList());
//
//        accountChangeDetailService.updateBatchById(updateEntities);
//        log.info("标记变更记录状态完成");
//
//    }


}
