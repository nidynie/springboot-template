package com.xxxx.web;


import com.xxxx.web.schedule.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.xxxx.web.mapper")
@SpringBootApplication
public class Application implements CommandLineRunner {


//    @Autowired
//    private CacheService cacheService ;

    @Autowired
    private ScheduleService scheduleService;


    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println(TimeUtil.format(new Date(),"yyyyMMddHHmmssSSS"));

//        LambdaQueryWrapper<BillEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(BillEntity::getType, BillTypeEnum.fee);
//        List<BillEntity> billEntityList = billService.list(lambdaQueryWrapper);
//
//        if (CollectionUtils.isNotEmpty(billEntityList)) {
//            billEntityList.forEach(e -> {
//                String html = billService.getPrintCode(e.getId());
////                IOUtils.write(new ByteArrayInputStream(html.getBytes()),new FileOutputStream("/Users/markin/Desktop/11111/pdfs/" + e.getSubmission() + ".pdf"));
//
//                try {
//                    IOUtils.write(html.getBytes(), new FileOutputStream("/Users/markin/Desktop/11111/pdfs/" + e.getSubmission() + ".html"));
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//
//            });
//        }

    }

}
