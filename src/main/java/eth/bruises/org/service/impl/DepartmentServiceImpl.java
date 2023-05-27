package eth.bruises.org.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import eth.bruises.basic.service.impl.BaseServiceImpl;
import eth.bruises.org.domain.Department;
import eth.bruises.org.mapper.DepartmentMapper;
import eth.bruises.org.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author bruises
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 用redis处理新增时的数据一致性
     * @param ids
     */
//    @Transactional
//    @Override
//    public void add(Department department) {
//        int add = departmentMapper.add(department);
//        // 如果sql执行成功，即受影响的行数大于0，则删除缓存数据
//        if (add > 0) {
//            redisTemplate.delete("deptTree");
//        }
//    }
    /**
     * 用SpringCache保证新增时的数据一致性
     * @param department
     */
    @Transactional
    @Override
    @CacheEvict(cacheNames = "dept")
    public void add(Department department) {
        departmentMapper.add(department);
    }
    /**
     * 用redis处理修改时的数据一致性
     * @param department
     */
    //    @Transactional
//    @Override
//    public void update(Department department) {
//        int update = departmentMapper.update(department);
//        if (update > 0) {
//            redisTemplate.delete("deptTree");
//        }
//    }

    /**
     * 用SpringCache保证修改时的数据一致性
     * @param department
     */
    @Transactional
    @Override
    @CacheEvict(cacheNames = "dept")
    public void update(Department department) {
        departmentMapper.update(department);
    }
    /**
     * 用redis处理删除时的数据一致性
     * @param id
     */
    //    @Transactional
//    @Override
//    public void delete(Long id) {
//        int delete = departmentMapper.delete(id);
//        if (delete > 0) {
//            redisTemplate.delete("deptTree");
//        }
//    }

    /**
     * 用SpringCache保证删除时的数据一致性
     * @param id
     */
    @Transactional
    @Override
    @CacheEvict(cacheNames = "dept")
    public void delete(Long id) {
        departmentMapper.delete(id);
    }

    /**
     * 用redis处理批量删除时的数据一致性
     * @param ids
     */
    //    @Transactional
//    @Override
//    public void batchDel(List<Long> ids) {
//        int batchDel = departmentMapper.batchDel(ids);
//        if (batchDel > 0) {
//            redisTemplate.delete("deptTree");
//        }
//    }

    /**
     * 用SpringCache保证批量删除时的数据一致性
     *
     * @param ids
     */
    @Transactional
    @Override
    @CacheEvict(cacheNames = "dept")
    public void batchDel(List<Long> ids) {
        departmentMapper.batchDel(ids);
    }

    /**
     * 获取部门树方法
     * @return
     */
//    @Override
//    public List<Department> tree() {
//        // 获取全量部门数据
//        List<Department> departments = selectAll();
//        // 放入map key:id value:Department
//        Map<Long, Department> map = departments.stream().collect(Collectors.toMap(Department::getId, d -> d));
//        List<Department> result = new ArrayList<>();
//        // 遍历判断是否有上级部门，若没有则为顶级部门，放入List，否则寻找父级放入
//        for (Department department:departments) {
//            if (null == department.getParentId()) {
//                result.add(department);
//            } else {
//                Department parent = map.get(department.getParentId());
//                if (null != parent) {
//                    // 判断父部门中的Children是否为空
//                    if (CollectionUtils.isEmpty(parent.getChildren())) {
//                        parent.setChildren(new ArrayList<>());
//                    }
//                    parent.getChildren().add(department);
//                }
//            }
//        }
//        return result;
//    }

    /**
     * 创建本地缓存容器
     */
    private Map<String, Object> treeMap = new HashMap<>();

    /**
     * 本地缓存获取部门树
     * @return List<Department>
     */
//    @Override
//    public List<Department> tree() {
//        // 1.从本地缓存中获取是否有要查询的参数
//        Object deptTree = treeMap.get("deptTree");
//        // 2.判断是否是第一次
//        if (deptTree == null){
//            // 1.第一次获取，走数据库
//            System.out.println("第一次查询：走MySql....");
//            // 2.查询部门树数据
//            List<Department> departments = getDepartments();
//            // 3.放入到本地缓存中
//            treeMap.put("deptTree", departments);
//            return departments;
//        } else {
//            // 1.不是第一次获取，缓存中有那么直接走缓存响应
//            System.out.println("第N次查询：走Redis....");
//            // fastjson：alibaba开源过一个JSON处理工具，专门针对各种JSON字符串转换为对象、JSON字符串转换为集合对象、JSON字符串转换为JSON对象
//            String jsonStr = JSONObject.toJSONString(deptTree);
//            // JSON字符串转换为List的集合对象
//            List<Department> departments = JSONArray.parseArray(jsonStr, Department.class);
//            // JSON字符串转换为普通JavaBean对象
//            //Department department = JSONObject.parseObject(jsonStr, Department.class);
//            return departments;
//        }
//    }

    /**
     * redis缓存做分布式缓存
     */
//    public List<Department> tree() {
//        // 1.从redis获取部门树数据
//        Object deptTree = redisTemplate.opsForValue().get("deptTree");
//        if (deptTree == null) {
//            // 1.第一次获取，走数据库
//            System.out.println("第一次查询：走MySql....");
//            // 2.查询部门树数据
//            List<Department> departments = getDepartments();
//            // 3.放入到redis缓存中
//            redisTemplate.opsForValue().set("deptTree", departments);
//            return departments;
//        } else {
//            // 1.不是第一次获取，缓存中有那么直接走缓存响应
//            System.out.println("第N次查询：走Redis....");
//            // fastjson：alibaba开源过一个JSON处理工具，专门针对各种JSON字符串转换为对象、JSON字符串转换为集合对象、JSON字符串转换为JSON对象
//            String jsonStr = JSONObject.toJSONString(deptTree);
//            // JSON字符串转换为List的集合对象
//            List<Department> departments = JSONArray.parseArray(jsonStr, Department.class);
//            // JSON字符串转换为普通JavaBean对象
//            //Department department = JSONObject.parseObject(jsonStr, Department.class);
//            return departments;
//        }
//    }

    /**
     * 用SpringCache开启tree数据缓存
     *
     * @return
     */
    @Cacheable(cacheNames = "dept", key = "'tree'")
    public List<Department> tree() {
        return getDepartments();
    }

    /**
     * 抽取出获得部门树的方法
     *
     * @return
     */
    private List<Department> getDepartments() {
        // 获取全量部门数据
        List<Department> departments = selectAll();
        // 放入map key:id value:Department
        Map<Long, Department> map = departments.stream().collect(Collectors.toMap(Department::getId, d -> d));
        List<Department> result = new ArrayList<>();
        // 遍历判断是否有上级部门，若没有则为顶级部门，放入List，否则寻找父级放入
        for (Department department : departments) {
            if (null == department.getParentId()) {
                result.add(department);
            } else {
                Department parent = map.get(department.getParentId());
                if (null != parent) {
                    // 判断父部门中的Children是否为空
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(department);
                }
            }
        }
        return result;
    }
}
