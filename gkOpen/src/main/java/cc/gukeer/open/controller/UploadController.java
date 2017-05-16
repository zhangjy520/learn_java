package cc.gukeer.open.controller;

import cc.gukeer.common.controller.BasicController;
import cc.gukeer.common.entity.ResultEntity;
import cc.gukeer.common.utils.DateUtils;
import cc.gukeer.common.utils.HttpRequestUtil;
import cc.gukeer.common.utils.VFSUtil;
import com.qiniu.util.Auth;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/file")
public class UploadController extends BasicController {
    public static final String VFS_ROOT_PATH = VFSUtil.getVFSRootPath();
    private List<String> fileTypeList;
    private String getProjectRootDirPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }
    /**
     * @param file
     * @param request
     * @return 上传图片的方法
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultEntity uploads(@Param("file") MultipartFile file, HttpServletRequest request) throws Exception {
        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            String imgName = getParamVal(request, "imgName");
            // 固定值  APP_DETAIL_PATH、USER_HEADERS_PATH 其他类别额外添加就行
            String imgPath = getParamVal(request, "imgPath");

            imgPath = imgPath + DateUtils.formatDate(new Date(), "yyyyMM") + "/";

            String fullPath = VFS_ROOT_PATH + imgPath;

            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String suffix = imgName.substring(imgName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + suffix;
            String absPath = fullPath + fileName;

            fis = file.getInputStream();
            File f = new File(absPath);
            fos = new FileOutputStream(f);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = fis.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            String imgRequestUrl = imgPath + fileName;
            return ResultEntity.newResultEntity(imgRequestUrl);
        } catch (Exception e) {
            logger.error("上传文件失败", e);
        } finally {
            fos.close();
            fis.close();
        }
        return ResultEntity.newErrEntity();
    }
    private String getPath(HttpServletRequest request, String ext, String param) {
        String filePath = "/images/upload/";
        if (param != null && param.trim().length() > 0) {
            filePath = filePath + param;
        } else {
            filePath = filePath;
        }
        filePath = filePath + "/" + DateUtils.formatDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + "." + ext;
        return filePath;
    }
    /**
     * @param request
     * @param response 展示图片的方法
     * @throws Exception
     */
    @RequestMapping(value = "/pic/show", method = RequestMethod.GET)
    public void showPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String picPath = getParamVal(request, "picPath");
        File file = new File(VFS_ROOT_PATH + picPath);
        if (!file.exists()) {
            logger.error("找不到文件[" + VFS_ROOT_PATH + picPath + "]");
            return;
        }
        response.setContentType("multipart/form-data");
        InputStream reader = null;
        try {
            reader = VFSUtil.getInputStream(file, true);
            byte[] buf = new byte[1024];
            int len = 0;
            OutputStream out = response.getOutputStream();
            while ((len = reader.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (Exception ex) {
            logger.error("显示图片时发生错误:" + ex.getMessage(), ex);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception ex) {
                    logger.error("关闭文件流出错", ex);
                }
            }
        }
    }
    /*
    * 设置AccessKey和SecretKey；
    创建Mac对象；
    创建PutPolicy对象；
    生成UploadToken；
    创建PutExtra对象；
    调用put或putFile方法上传文件；
    * */
    //设置好账号的ACCESS_KEY和SECRET_KEY
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "V6vwVHHdEFsMz-KJy5wrvFRqTUMCmu3fYwxMczqh";
    String SECRET_KEY = "buSxlv3udyiRUdHJIMllTSbjwXBzsJAUj9vZsWYf";
    //要上传的空间
    String bucketname = "image2";
    //上传文件的路径
    String FilePath = "D:\\img";
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    //@RequestMapping(value = "/getuptoken",method = RequestMethod.GET )
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    @RequestMapping(value = "/getuptoken", method = RequestMethod.GET)
    @ResponseBody
    public void makeToken(HttpServletResponse response, HttpServletRequest request) {
        String uptoken = null;
        try {
            uptoken = getUpToken();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uptoken", uptoken);
            System.out.print(jsonObject);
            response.getWriter().print(jsonObject);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/load")
    public void sdk() throws IOException {
        String fileName = "http://omumqy8sj.bkt.clouddn.com/data.zip";
        String domainOfBucket = "http://file3.ckmooc.com/";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String finalUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        HttpRequestUtil.get(finalUrl,null,null);
    }

}
