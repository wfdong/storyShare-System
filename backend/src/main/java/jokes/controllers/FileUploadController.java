package jokes.controllers;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;

import jokes.beans.response.AsyncTask;
import jokes.controllers.FileUploadController;
import jokes.controllers.UserController;
import jokes.utils.AsyncTaskManager;
import jokes.utils.RestUtil;
import jokes.utils.UploadFileManager;

@RestController
public class FileUploadController {

	private static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private static final long DELAY_120_MINITUES = 120 * 60 * 1000;

    @Autowired
    UploadFileManager uploadFileManager;

    @Autowired
    AsyncTaskManager asyncTaskManager;

    @Autowired
    UserController userController;

    @RequestMapping(value = "/rest/v1/file/preview", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
        try {
            logger.info("Receive upload file {}", file.getOriginalFilename());
            Preconditions.checkArgument(file.isEmpty() == false, file.getName() + " is empty");

            UserController.UserInfo userInfo = userController.getUserInfo(httpServletRequest);

            File uploadedFile = uploadFileManager.save(userInfo.name, file);

            AsyncTask asyncTask = new AsyncTask();
            asyncTask.setTaskId(UUID.randomUUID().toString());
            asyncTask.setCommitTs(System.currentTimeMillis());
            asyncTask.setExpireTs(System.currentTimeMillis() + DELAY_120_MINITUES);
            asyncTask.setContent(uploadedFile.getParentFile().getName() + "\\" + uploadedFile.getName());
            asyncTask.setMsg("成功");
            asyncTaskManager.putTask(asyncTask);
            logger.info("File {} save to local done", uploadedFile);
            return RestUtil.toSuccessResult(asyncTask);
        } catch (Exception e) {
            logger.error("ERROR ", e);
            return RestUtil.toFailedResult(RestUtil.ERROR_CODE_EXCEPTION, e.getMessage(), null);
        }

    }

    @RequestMapping(value = "/rest/v1/file/base64", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileBase64(HttpServletRequest httpServletRequest, @RequestBody JSONObject data) {
        try {
            Preconditions.checkArgument(data != null, "file can not be null");

            logger.info("Receive upload file {}", data.size());

            UserController.UserInfo userInfo = userController.getUserInfo(httpServletRequest);

//            String str = java.net.URLDecoder.decode(data.getString("data"), "utf-8");

            byte[] asBytes = Base64.getDecoder().decode(data.getString("data"));

            File uploadedFile = uploadFileManager.save(userInfo.name, asBytes);

            AsyncTask asyncTask = new AsyncTask();
            asyncTask.setTaskId(UUID.randomUUID().toString());
            asyncTask.setCommitTs(System.currentTimeMillis());
            asyncTask.setExpireTs(System.currentTimeMillis() + DELAY_120_MINITUES);
            asyncTask.setContent(uploadedFile.getParentFile().getName() + "\\" + uploadedFile.getName());
            asyncTask.setMsg("成功");
            asyncTaskManager.putTask(asyncTask);
            logger.info("File {} save to local done", uploadedFile);
            return RestUtil.toSuccessResult(asyncTask);
        } catch (Exception e) {
            logger.error("ERROR ", e);
            return RestUtil.toFailedResult(RestUtil.ERROR_CODE_EXCEPTION, e.getMessage(), null);
        }

    }

    private String getRealDirName(File uploadedFile) {
        String absPath = uploadedFile.getAbsolutePath();
        absPath = absPath.replace(uploadFileManager.getUploadDir(), "");
        return absPath;
    }

    // 此处的参数也可以是ServletRequestDataBinder类型
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        // 注册自定义的属性编辑器
        // 1、日期
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        df.setTimeZone("GMT");
        //CustomDateEditor类是系统内部自带的类
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        // 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
        binder.registerCustomEditor(Date.class, dateEditor);
    }

//    @RequestMapping(value = "/rest/v1/file/confirmupload", method = RequestMethod.POST)
//    @ResponseBody
//    public String createIndex(UploadFileMeta uploadFileMeta, HttpServletRequest request) {
//        try {
//            logger.info("Create index for {}", JSON.toJSONString(uploadFileMeta));
//            long start = System.currentTimeMillis();
//
//
//            AsyncTask asyncTask = null;
//            synchronized (uploadFileMeta.getBizType().intern()) {
//                //针对一个bizType做同步，范围较大，但也可接受
//                asyncTask = asyncTaskManager.getTask(uploadFileMeta.getTaskId());
//                if(asyncTask == null) {
//                    throw new IllegalArgumentException("找不到TaskID " + uploadFileMeta.getTaskId() + ", 请确认是否上传过文件");
//                }
//            }
//
//            if(DataFormat.BINARY.getName().equals(uploadFileMeta.getDataFormat())) {
//                SpacexUtil.checkNullEmptyString(uploadFileMeta.getProtocol(), "protocol can not be null");
//            }
//
//            File uploadedFile = (File) asyncTask.getContent();
//
//
//
//            asyncTaskManager.removeTask(asyncTask);
//
//            logger.info("Upload finish, cost {}", System.currentTimeMillis() - start);
//
//            return SpacexUtil.toResultJson("Upload " + uploadedFile.getName() + "success", "success");
//        } catch (Exception e) {
//            logger.error("ERROR ", e);
//            return SpacexUtil.toResultErrorJson(e.getMessage(), e.getMessage());
//        }
//
//    }

  

  

 
    /**
     * 获得用户真实IP
     *
     * @param request 请求对象
     * @return 真实IP地址
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
