package generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openingo.mp.ext.MpModelExt;

/**
 * 自动生成 Mybatis Entity和Mapper, xml
 *
 * @author Qicz
 */
public class MySQLGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        String projectName = "";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + projectName +"/src/main/java");
        gc.setAuthor("Qicz");
        gc.setActiveRecord(true);
        gc.setOpen(false);
        //gc.setFileOverride(true); // 重写生成时覆盖旧文件
        gc.setEntityName("%sDO");// Entity后缀
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/db?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("dbadmin");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("module");
        pc.setParent("org.openingo");

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        String templatePath = "mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + projectName +"/src/main/resources/mapper.mysql/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("entity.java");
        templateConfig.setMapper("mapper.java");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        templateConfig.setController("");

        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("t_");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(MpModelExt.class);
        strategy.setEntityLombokModel(true);
        //strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
       // strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
       // strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");

        //strategy.setEntityTableFieldAnnotationEnable(true);

        //TableFill createTimeFill = new TableFill("create_time", FieldFill.INSERT);
        //TableFill updateTimeFill = new TableFill("update_time", FieldFill.INSERT_UPDATE);

        //strategy.setTableFillList(Arrays.asList(createTimeFill, updateTimeFill));

        mpg.setStrategy(strategy);

        //mpg.setTemplateEngine(new VelocityTemplateEngine());


        mpg.execute();
    }
}
