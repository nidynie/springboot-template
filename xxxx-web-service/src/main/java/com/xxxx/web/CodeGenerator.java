package com.xxxx.web;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    public static void main(String args[]) {
        new CodeGenerator().generateCode();
    }


    private static final String projectPath = System.getProperty("user.dir") + "/xxxx-web-service";
    private static final String codeFilePath = projectPath + "/src/main/java/com/xxxx/web";
    private static final String packagePath = "com.xxxx.web";


    public void generateCode() {
        String author = "xxxx";
        String[] tables = "table_name".split(",");

        //获取数据库连接
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://xxx.xxx.xxx.xxx:3306/xxxx?useUnicode=true&characterEncoding=UTF-8&useSSL=false&rewriteBatchedStatements=true";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("xxxx")
                .setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setLogicDeleteFieldName("rec_status")

                .setNaming(NamingStrategy.underline_to_camel)
//                .setTablePrefix("eff_","ent_i_","ent_s_","ent_r_","eqp_i_","eqp_r_","eqp_s_","prod_i_","prod_r_","prod_s_","sys_i_")
                .setInclude(tables)//修改替换成你需要的表名，多个表名传数组
                .setEntityLombokModel(true);//是否使用lombok
        config.setActiveRecord(true)
                .setAuthor(author)
                .setOutputDir(projectPath + "/src/main/java")
                .setDateType(DateType.ONLY_DATE)
                .setEnableCache(false)
                .setBaseColumnList(false)
                .setBaseColumnList(false)
                .setIdType(IdType.UUID)//主键类型
                .setFileOverride(true)
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")


                .setSwagger2(true)
        ;  //是否使用Swagger

        //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("basePackage", packagePath);
                this.setMap(map);
            }
        };
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null)
                .setServiceImpl("/code/serviceImpl.java")
                .setController("/code/controller.java")
                .setMapper("/code/mapper.java")
                .setEntity(null)
                .setService("/code/service.java");

        //添加add 请求对象
        List<FileOutConfig> foc = new ArrayList<>();
        foc.add(new FileOutConfig("/code/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return codeFilePath + "/entity/" + tableInfo.getEntityName() + "Entity.java";
            }
        });
        foc.add(new FileOutConfig("/code/addDto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return codeFilePath + "/dto/" + tableInfo.getEntityName() + "AddDto.java";
            }
        });
        //添加update 请求对象
        foc.add(new FileOutConfig("/code/updateDto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return codeFilePath + "/dto/" + tableInfo.getEntityName() + "UpdateDto.java";
            }
        });
        //添加list请求对象
        foc.add(new FileOutConfig("/code/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return codeFilePath + "/vo/" + tableInfo.getEntityName() + "Vo.java";
            }
        });
        //分页请求pageDto
        foc.add(new FileOutConfig("/code/pageDto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return codeFilePath + "/dto/" + tableInfo.getEntityName() + "PageDto.java";
            }
        });
        ic.setFileOutConfigList(foc);
        new AutoGenerator()
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setTemplate(tc)
                .setCfg(ic)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packagePath)
//                                .setModuleName("test")
                                .setController("controller")
                                .setEntity("entity")
                                .setService("service")
                                .setServiceImpl("service.impl")

                ).execute();
    }
}