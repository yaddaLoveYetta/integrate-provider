切换到项目目录运行mybatis-generator生成mybatis dao mapper model代码
java -jar mybatis-generator-core-1.3.5.jar -configfile generatorConfig.xml -overwrite

项目中使用到的主要框架

spring
dubbo
mybatis
redis

项目说明

1：mybatis采用接口方式编程，dao接口，model实体，mapper映射脚本 全部通过mybatis-generator自动生成，不要去修改这三部分位置及内容。
2：分页查询采用com.github.pagehelper插件实现