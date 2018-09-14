package com.julu.pubApi;

import com.julu.config.FileConfig;
import com.julu.dto.CodeMessage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "文件上传.文件下载")
@RestController
public class pubController {

    @Autowired
    private FileConfig fc;
    @Autowired
    //private EmailLog emailLog;
    private final ResourceLoader resourceLoader;
    @Autowired
    public pubController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    /**
     * 上传文件。
     *
     * @param file,
     * 文件字节流， 其中filename是multipart中的变量名称
     * @return
     */
    @ApiOperation("上传文件服务")
    @PostMapping("/app/fileserver/upload")
    @ResponseBody
    public CodeMessage<FileConfig> uploadFile(@ApiParam(type = "MultipartFile") MultipartFile file) {
        // System.out.println(request.getHeader("Content-Type"));
        //文件分片或断点续传需要Range头，表示字节流对应原始文件的位置
        CodeMessage codeMessage=new CodeMessage();
        String dir = getDir(fc.getUrl());
        String fileName = getFileName(file.getOriginalFilename());
        //FileObject resp = new FileObject();
        try {
            byte[] bytes = file.getBytes(); // 文件字节流
            File fileToSave = new File(dir + "/" + fileName);
            FileCopyUtils.copy(bytes, fileToSave); // 保存文件
            // 数据表里要存储原始文件名、用户id、时间戳、服务器生成的文件名， 客户端文件起始位置start、客户端文件终止位置end、客户端文件大小total等
            FileConfig fileConfig=new FileConfig();
            fileConfig.setUrl(fileName);
            //resp.setStatus(0);
            //resp.setSize(fileToSave.length());
            //截掉配置的路径
            //dir = dir.replaceAll(fc.getDir(), "");
            //resp.setFileUrl("/fileserver/app/"+fileName);
            codeMessage.setCode(200);
            codeMessage.setMsg("文件上传成功");
            codeMessage.setData(fileConfig);
        } catch (IOException ex) {
            ex.printStackTrace();
            codeMessage.setCode(500);
            codeMessage.setMsg("文件上传失败");
        }
        return codeMessage;
    }

    //显示图片的方法关键 匹配路径像
    @ApiOperation("获取文件服务")
    @PostMapping(value = "/app/fileserver/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            System.out.println(Paths.get(fc.getUrl(), filename).toString());
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(fc.getUrl(), filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 构造文件路径
     *
     * @return
     */
    private String getDir(String dir) {
        // 设定日期的格式
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
        // 得到系统的时间，以及我们要保存的文件所在位置
        if(!dir.endsWith("/")) {
            dir = dir +"/";
        }
        //	String realpathdir = dir + "upload"+"/" + dateformat.format(new Date());
        String realpathdir = dir;
        File savedir = new File(realpathdir);
        // 如果文件夹不存在的话
        if (!savedir.exists()) {
            // 创建相应的文件夹
            savedir.mkdirs();
        }
        return realpathdir;
    }
    /**
     * 获取文件名
     * @param fileName
     * @return
     */
    private String getFileName(String fileName) {
        int pos = fileName.lastIndexOf(".");
        String file = UUID.randomUUID().toString().replaceAll("-", "");
        return file + fileName.substring(pos);
    }

    public Boolean isLogin(HttpServletRequest request){
        String login_token=request.getHeader("login_token");
        if(login_token!=null && !"".equals(login_token)){
            return true;
        }else{
            return false;
        }
    }


}
