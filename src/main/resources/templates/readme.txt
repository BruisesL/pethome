代码生成器生成某个模块的三层代码步骤：
一：拷贝
1. 将*.vm文件拷贝的templates中，可以放在src/test/resources/templates中
2. 将mybatitsPlus-config.properties拷贝到src/test/resources中
3. GenteratorCode生成代码【main生成】放在src/main/test下【自己新建包名】

二：修改
1. mybatitsPlus-config.properties中需要
    #此处为本项目src所在路径（代码生成器输出路径）,注意一定是当前项目所在的目录哟
    OutputDir=F:\\workspace_idea_v8.1\\pethome\\src\\main\\java
    #mapper.xml SQL映射文件目录 - 前端的路径要拷贝自己工程空间的路径
    OutputDirXml=F:\\workspace_idea_v8.1\\pethome\\src\\main\\resources
    #自定义包路径 - 模块生成在那个包下，例如Role角色是属于system系统模块
    parent=cn.itsource.system
    #数据库密码 - 修改成自己的
    jdbc.pwd=123456
2. GenteratorCode生成代码中43行左右要修改一下
     strategy.setTablePrefix(new String[] { "t_" });// 此处可以修改为您的表前缀
     strategy.setInclude(new String[]{"t_permission","t_role"}); // 需要生成的表 - 可以一次性写多个
3. 由于每个人的包名不一样
	所以GenteratorCode类，配置文件mybatitsPlus-config.properties，模板中的.vm都要批量替换
	将cn.itsource   替换成   自己的包名
4. 模板controller.java.vm需要根据自己以前的Controller修改下方法名，和调用方法名
5. 执行：GenteratorCode直接执行

三：生成之后修改
	实体类中的时间要用java.util.Date - 还要重新生成getter和setter方法
