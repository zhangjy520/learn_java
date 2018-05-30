title: java导入文件视频文件并使用nginx做静态资源代理
author: zjy
date: 2018-05-30 16:09:07
tags:
---
## 1 前端

		<div class="unit">
                <label>上传文件：</label>
                <input type="hidden" name="fileUrl" id="sourceUrl" value="">
                <input type="hidden" name="fileName" id="sourceName" value="">
                <input type="hidden" name="ctx" value="${ctx}">
                <div style="display: inline-block;vertical-align: top" id="sourceUpload" data-flag='schoolCulture' data-flagtype='schoolculturevideo' class="webuploader-container ">
                    <div class="webuploader-pick">上传资源</div>
                    <div style="position: absolute; top: 0px; left: 0px; width: 126px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
                        <input type="file" name="file" class="webuploader-element-invisible">
                        <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
                    </div>
                </div>
           </div>
           
           
 	<script>
        $('#sourceUpload').diyUpload({
            url: '${ctx}/file/upload',
            success: function (data) {
                $('#sourceUrl').val(data.data.fileRequestPath);
                $('#sourceName').val(data.data.fileName);
                $("input[name='suffix']").val(data.data.suffix);
                $("input[name='url']").val("已经上传，不需要填写路径");
                $("input[name='url']").attr("disabled",true);
            },
            error: function (err) {
                console.info(err);
            },
            buttonText: '上传资源',
            chunked: false,
            // 分片大小
            chunkSize: 50 * 1024 * 1024,
            //最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
            fileNumLimit: 1,
            fileSizeLimit: 250 * 1024 * 1024,
            fileSingleSizeLimit: 250 * 1024 * 1024,
            accept: {
                title: "files",
                extensions: "avi,rmvb,rm,flv,mp4,mov,pdf,wps,rtf,doc,txt,docx,ppt,xlsx,bmp,gif,jpg,pic,png,tif"
            },
        });
    </script>
    
    
# 2 资源文件
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/js/diyUpload/css/diyUpload.css">
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/js/diyUpload/css/webuploader.css">
    <script type="text/javascript" src="${ctxStatic}/js/diyUpload/js/diyUploadmore.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/diyUpload/js/webuploader.html5only.min.js"></script>
  
  
  # 3 后端
  ## 上传到了本地磁盘路径，注意访问的时候走nginx代理访问
  	 @ResponseBody
      @RequestMapping(value = "/upload", method = RequestMethod.POST)
      public ResultEntity uploads(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
          FileOutputStream fos = null;
          InputStream fis = null;
          try {
              String fullPath = FileUtils.VFS_ROOT_PATH + FileUtils.SOURCE_ATTACH;
              File dir = new File(fullPath);
              if (!dir.exists()) {
                  dir.mkdirs();
              }
              String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
              String fileName = System.currentTimeMillis()  + suffix;
              String absPath = FileUtils.VFS_ROOT_PATH + FileUtils.SOURCE_ATTACH + fileName;
              String fileRequestPath = FileUtils.SOURCE_ATTACH + fileName;

              fis = file.getInputStream();
              File f = new File(absPath);
              fos = new FileOutputStream(f);
              byte[] b = new byte[1024];
              int nRead = 0;
              while ((nRead = fis.read(b)) != -1) {
                  fos.write(b, 0, nRead);
              }
              fos.flush();

              Map<String, String> pathMap = new HashMap<>();
              pathMap.put("fileName", file.getOriginalFilename());
              pathMap.put("fileRequestPath", fileRequestPath);
              pathMap.put("suffix", suffix);
              return ResultEntity.newResultEntity(pathMap);
          } catch (Exception e) {
              logger.error("上传文件失败", e);
          } finally {
              fos.close();
              fis.close();
          }
          return ResultEntity.newErrEntity();
      }
  
  
  # 4 nginx配置
  
    server{
       listen 8085;
          location / {
          #配置成本地文件路径和对应的端口nginx.conf里加这个server节点即可
              alias C:/smartBoard/vfsroot/;
              autoindex on;
        }

      }
  