package eth.bruises.sys.service.impl;

import eth.bruises.basic.annotation.PreAuthorize;
import eth.bruises.basic.utils.ClassUtils;
import eth.bruises.sys.domain.Permission;
import eth.bruises.sys.mapper.PermissionMapper;
import eth.bruises.sys.service.IPermissionService;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 业务实现类：
 *
 * @author bruises
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService {
    private static final String PKG_PREFIX = "eth.bruises.";
    private static final String PKG_SUFFIX = ".controller";

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public void scanPermission() {
        //获取eth.bruises下面所有的模块目录
        String path = this.getClass().getResource("/").getPath() + "/eth/bruises/";
        File file = new File(path);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            //只要目录
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        //eth.bruises.*.controller里面所有的类
        Set<Class> clazzes = new HashSet<>();
        for (File fileTmp : files) {
            System.out.println("===============权限注解解析：获取所有的包==============");
            System.out.println(fileTmp.getName());
            clazzes.addAll(ClassUtils.getClasses(PKG_PREFIX + fileTmp.getName() + PKG_SUFFIX));
        }


        //遍历所有controller包下的所有类【字节码】
        for (Class clazz : clazzes) {
            //获取当前类中的所有方法
            Method[] methods = clazz.getMethods();
            //如果controller中没有方法 - 直接结束
            if (methods == null || methods.length < 1) {
                return;
            }
            for (Method method : methods) {
                //获取url：1个数据
                String uri = getUri(clazz, method);
                try {
                    //从方法上去获取PreAuthorize注解
                    PreAuthorize preAuthorizeAnno = method.getAnnotation(PreAuthorize.class);
                    if (preAuthorizeAnno == null) {
                        continue;
                    }
                    //获取PreAuthorize注解上的name属性值
                    String name = preAuthorizeAnno.name();
                    //获取PreAuthorize注解上的sn属性值
                    String permissionSn = preAuthorizeAnno.sn();
                    //通过sn去查询数据库
                    Permission permissionTmp = permissionMapper.loadBySn(permissionSn);
                    //如果不存在就添加
                    if (permissionTmp == null) { //添加
                        Permission permission = new Permission();
                        permission.setName(name);       //t_permission表中的权限名
                        permission.setSn(permissionSn); //t_permission表中的权限编号
                        permission.setUrl(uri);           //t_permission表中的权限路径
                        permissionMapper.add(permission);
                    } else {
                        //如果存在就修改
                        permissionTmp.setName(name);
                        permissionTmp.setSn(permissionSn);
                        permissionTmp.setUrl(uri);
                        permissionMapper.update(permissionTmp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //获取t_permission表中的url  //@RequestMapping("/department") //@GetMapping("/{id}")
    private String getUri(Class clazz, Method method) {
        //获取类上的请求路径：/department
        String classPath = "";
        //从类上去找RequestMapping注解
        Annotation annotation = clazz.getAnnotation(RequestMapping.class);
        if (annotation != null) {
            RequestMapping requestMapping = (RequestMapping) annotation;
            //获取value
            String[] values = requestMapping.value();
            if (values != null && values.length > 0) {
                classPath = values[0];
                if (!"".equals(classPath) && !classPath.startsWith("/")) {
                    classPath = "/" + classPath;
                }
            }
        }
        //以下是获取方法上的请求路径：/{id}
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        String methodPath = "";
        if (getMapping != null) {
            //获取方法上的GetMapping注解的value值
            String[] values = getMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }
        }
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            String[] values = postMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }
        }
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            String[] values = deleteMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }
        }
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            String[] values = putMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }

        }
        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
        if (patchMapping != null) {
            String[] values = patchMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }
        }
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            String[] values = requestMapping.value();
            if (values != null && values.length > 0) {
                methodPath = values[0];
                if (!"".equals(methodPath) && !methodPath.startsWith("/")) {
                    methodPath = "/" + methodPath;
                }
            }
        }
        return classPath + methodPath;
    }
}
