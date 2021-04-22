package mwt.testsysback.controller;

import mwt.testsysback.common.CommonResult;
import mwt.testsysback.entity.Records;
import mwt.testsysback.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
public class FileController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    RecordService recordService;


    //单文件上传
    @RequestMapping("/file/upload")
    @ResponseBody
    public CommonResult fileUpload(@RequestBody MultipartFile file){
        if(file.isEmpty()){
            return CommonResult.failed("不能上传空文件");
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "/Users/apple/Downloads/file/upload" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            String file_path = dest.getPath();
            return CommonResult.success(file_path);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return CommonResult.failed("文件上传失败");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return CommonResult.failed("文件上传失败");
        }
    }

    //多文件上传
    @RequestMapping(value="/multiFileUpload", method=RequestMethod.POST)
    @ResponseBody
    public CommonResult multiFileUpload(@RequestBody List<MultipartFile> files) {

        if(files.isEmpty()){
            return CommonResult.failed("不能上传空文件");
        }

        String path = "/Users/apple/Downloads/file/upload" ;

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return CommonResult.failed("不能上传空文件");
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return CommonResult.failed("文件上传失败");
                }
            }
        }
        return CommonResult.success("文件上传成功");
    }

    //文件下载
    @RequestMapping("/download")

    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="2.xlsx";
        String filePath = "/Users/apple/Downloads/file/download" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
