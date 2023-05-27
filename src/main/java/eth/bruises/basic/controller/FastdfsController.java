package eth.bruises.basic.controller;

import eth.bruises.basic.exception.GlobalExceptionEnum;
import eth.bruises.basic.utils.AjaxResult;
import eth.bruises.basic.utils.FastdfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * fastdfs接口层
 *
 * @author bruises
 */
@RestController
@RequestMapping("/fastdfs")
@Api(value = "文件操作的API", description = "文件相关功能")
public class FastdfsController {

    /**
     * fastdfs文件上传接口
     *
     * @param file MultipartFile是复杂文件对象
     * @return
     */

    @PostMapping
    @ApiOperation(value = "文件上传")
    public AjaxResult upload(@RequestPart(value = "file", required = true) MultipartFile file) throws IOException {
        // 1.得到文件扩展名
        String filename = file.getOriginalFilename();
        String suffix = FilenameUtils.getExtension(filename);
        // 2.上传图片，根据字节+文件扩展名，得到文件访问路径
        // /group1/M00/00/05/oYYBAGKCO-6AdKyoAAEOykzR2Hc97_350x350.jpeg
        String path = FastdfsUtil.upload(file.getBytes(), suffix);
        // 3.返回成功信息+文件访问路径
        return AjaxResult.success(path);
    }


    /**
     * fastdfs删除接口
     */
    @DeleteMapping
    @ApiOperation(value = "文件删除")
    public AjaxResult delete(@RequestParam("path") String path) {

        // 因为删除需要组名+图片路径，所以需要把前端传递进来的图片路径进行分割
        // /group1/M00/00/05/oYYBAGKCO-6AdKyoAAEOykzR2Hc97_350x350.jpeg
        // 1.得到组名和路径名称
        // 1.1.得到组名
        // 去除第一个'/'，得到 group1/M00/00/05/oYYBAGKCO-6AdKyoAAEOykzR2Hc97_350x350.jpeg
        String pathTmp = path.substring(1);
        // 获取分组，通过从开始到第一个'/'得到 goup1
        String groupName = pathTmp.substring(0, pathTmp.indexOf("/"));
        // 1.2.得到图片路径
        // 从第一个'/'开始得到 M00/00/05/oYYBAGKCO-6AdKyoAAEOykzR2Hc97_350x350.jpeg
        String remotePath = pathTmp.substring(pathTmp.indexOf("/") + 1);
        // 2.调用工具类方法进行删除，传递组名和图片路径名称
        FastdfsUtil.delete(groupName, remotePath);
        // 3.返回成功信息+文件访问路径
        return AjaxResult.success();
    }

}