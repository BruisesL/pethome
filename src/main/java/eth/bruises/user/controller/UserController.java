package eth.bruises.user.controller;

import eth.bruises.user.dto.EmailRegisterDto;
import eth.bruises.user.dto.PhoneRegisterDto;
import eth.bruises.user.service.IUserService;
import eth.bruises.user.domain.User;
import eth.bruises.user.query.UserQuery;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* 后端接口类；
*/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public IUserService userService;

    /**
     * 接口：添加或修改
     * @param user  传递的实体
     * @return AjaxResult 响应给前端
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody User user){ 
        if( user.getId()!=null){
		userService.update(user);
        }else{
                userService.add(user);
	}
        return AjaxResult.success();
       
    }

    /**
    * 接口：删除
    * @param id
    * @return AjaxResult 响应给前端
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        userService.delete(id);
        return AjaxResult.success();
    }

    /**
    * 接口：批量删除
    * @param ids
    * @return AjaxResult 响应给前端
    */
    @PatchMapping
    public AjaxResult batchDel(@RequestBody List<Long> ids){
        userService.batchDel(ids);
        return AjaxResult.success();
    }

    /**
    * 接口：查询单个对象
    * @param id
    */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable("id")Long id) {
        return userService.selectOne(id);
    }


    /**
    * 接口：查询所有
    * @return
    */
    @GetMapping
    public List<User> selectAll(){
        return userService.selectAll();
    }


   /**
    * 接口：分页查询或高级查询
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public PageInfo<User> page(@RequestBody UserQuery query) {
        return userService.page(query);
    }

    /**
     * 手机号注册
     * @param dto
     * @return
     */
    @PostMapping("/phoneRegister")
    public AjaxResult phoneRegister(@Valid @RequestBody PhoneRegisterDto dto) {
        userService.phoneRegister(dto);
        return AjaxResult.success();
    }

    /**
     * 邮箱注册
     * @param dto
     * @return
     */
    @PostMapping("/emailRegister")
    public AjaxResult emailRegister(@Valid @RequestBody EmailRegisterDto dto) {
        userService.emailRegister(dto);
        return AjaxResult.success();
    }

}
