$(function () {

    $('#pickfiles1').on('click', function () {
        uploader.start();
        $("#hide2").hide();
        $("#hide1").show();
    });
    $('#stop_load').on('click', function () {
        uploader.stop();
        $("#hide1").hide();
        $("#hide2").show();
    });

    var imgLink = Qiniu.imageView2({
        mode: 3,  // 缩略模式，共6种[0-5]
        w: 100,   // 具体含义由缩略模式决定
        h: 100,   // 具体含义由缩略模式决定
        q: 100,   // 新图的图像质量，取值范围：1-100
        format: 'jpg'  // 新图的输出格式，取值范围：jpg，gif，png，webp等
    });


    var uploader = Qiniu.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles1',
        container: 'container1',
        drop_element: 'container1',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
                $('#indicatorContainer').radialIndicator();
                $('#indicatorContainer').radialIndicator({
                    barColor: '#87CEEB',
                    barWidth: 10,
                    initValue: 40,
                    roundCorner: true,
                    percentage: true
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);
                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm1").attr("src", url);
                var input = document.getElementById('tosql1');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader.bind('FileUploaded', function () {
        console.log('hello man,a file is uploaded');
    });

    var Q2 = new QiniuJsSDK();
    var uploader2 = Q2.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles2',
        container: 'container2',
        drop_element: 'container2',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm2").attr("src", url);
                var input = document.getElementById('tosql2');
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader2.bind('FileUploaded', function () {
        console.log('hello man 2,a file is uploaded');
    });
    var Q3 = new QiniuJsSDK();
    var uploader3 = Q3.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles3',
        container: 'container3',
        drop_element: 'container3',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm3").attr("src", url);
                var input = document.getElementById('tosql3');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader3.bind('FileUploaded', function () {
        console.log('hello man 3,a file is uploaded');
    });
    var Q4 = new QiniuJsSDK();
    var uploader4 = Q4.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles4',
        container: 'container4',
        drop_element: 'container4',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#wm4').attr("data-url", "loading.gif");
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
                $('#indicatorContainer').radialIndicator();
                $('#indicatorContainer').radialIndicator({
                    barColor: '#87CEEB',
                    barWidth: 10,
                    initValue: 40,
                    roundCorner: true,
                    percentage: true
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);
                $('#wm4').remove("data-url", "2.gif");
                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);

                // if($(".multifyInput").length == 0 ){
                //     $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg"/>').insertAfter($("#multifySpan"));
                // }else {
                //     $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg"/>').
                //     insertAfter(document.getElementsByClassName("multifyImg")[document.getElementsByClassName("multifyImg").length-1]);
                // }
                // var imgs =document.getElementsByClassName("multifyImg"),img=imgs[imgs.length-1];
                // // var inputs =document.getElementsByClassName("multifyInput"),input =inputs[inputs.length-1];
                //
                // $(img).attr("src",url);
                // // $(input).attr("src",url);
                // var _url = $("#urls").val()+url+",";
                // $("#urls").val(_url);
                $("#wm4").attr("src", url);
                var input = document.getElementById('tosql4');
                input.setAttribute('value', url);

            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader4.bind('FileUploaded', function () {
        console.log('hello man 4,a file is uploaded');
    });


    var Q5 = new QiniuJsSDK();
    var uploader5 = Q5.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles5',
        container: 'container5',
        drop_element: 'container5',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        get_new_uptoken: false,
        // downtoken_url: '/downtoken',
        unique_names: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm5").attr("src", url);
                var input = document.getElementById('tosql5');
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader5.bind('FileUploaded', function () {
        console.log('hello man 5,a file is uploaded');
    });


    var Q6 = new QiniuJsSDK();
    var uploader6 = Q6.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles6',
        container: 'container6',
        drop_element: 'container6',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm6").attr("src", url);
                var input = document.getElementById('tosql6');
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader6.bind('FileUploaded', function () {
        console.log('hello man 6,a file is uploaded');
    });


    var Q7 = new QiniuJsSDK();
    var uploader7 = Q7.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles7',
        container: 'container7',
        drop_element: 'container7',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var F
                key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm7").attr("src", url);
                var input = document.getElementById('tosql7');
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader7.bind('FileUploaded', function () {
        console.log('hello man 7,a file is uploaded');
    });


    var Q8 = new QiniuJsSDK();
    var uploader8 = Q8.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles8',
        container: 'container8',
        drop_element: 'container8',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm8").attr("src", url);
                var input = document.getElementById('tosql8');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader8.bind('FileUploaded', function () {
        console.log('hello man 8,a file is uploaded');
    });


    var Q9 = new QiniuJsSDK();
    var uploader9 = Q9.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles9',
        container: 'container9',
        drop_element: 'container9',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm9").attr("src", url);
                var input = document.getElementById('tosql9');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader9.bind('FileUploaded', function () {
        console.log('hello man 9,a file is uploaded');
    });


    var Q10 = new QiniuJsSDK();
    var uploader10 = Q2.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles10',
        container: 'container10',
        drop_element: 'container10',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm10").attr("src", url);
                var input = document.getElementById('tosql10');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader10.bind('FileUploaded', function () {
        console.log('hello man 10,a file is uploaded');
    });


    var Q11 = new QiniuJsSDK();
    var uploader11 = Q11.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles11',
        container: 'container11',
        drop_element: 'container11',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm11").attr("src", url);
                var input = document.getElementById('tosql11');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader11.bind('FileUploaded', function () {
        console.log('hello man 11,a file is uploaded');
    });


    var Q12 = new QiniuJsSDK();
    var uploader12 = Q12.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles12',
        container: 'container12',
        drop_element: 'container12',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm12").attr("src", url);
                var input = document.getElementById('tosql12');
                input.setAttribute('src', url);
                input.setAttribute('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader12.bind('FileUploaded', function () {
        console.log('hello man 12,a file is uploaded');
    });


    var Q13 = new QiniuJsSDK();
    var uploader13 = Q13.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles13',
        container: 'container13',
        drop_element: 'container13',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等 待...");
                });
                // layui.use('layui-progress', function(){
                //     var element = layui.element();
                // });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                progress.setComplete(up, info);

                var res = $.parseJSON(info);
                var key = res.key;
                var domain = up.getOption('domain');
                var url = domain + encodeURI(res.key);
                $("#wm13").attr("src", url);
                $("#tosql3").attr('value', url);
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader13.bind('FileUploaded', function () {
        console.log('hello man 13,a file is uploaded');
    });


    var Q14 = new QiniuJsSDK();
    var uploader14 = Q14.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles14',
        container: 'container14',
        drop_element: 'container14',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        unique_name: true,
        domain: $('#domain').val(),
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    var imgLink = Q14.imageView2({
                        mode: 3,  // 缩略模式，共6种[0-5]
                        w: 100,   // 具体含义由缩略模式决定
                        h: 100,   // 具体含义由缩略模式决定
                        q: 100,   // 新图的图像质量，取值范围：1-100
                        format: 'jpg'  // 新图的输出格式，取值范围：jpg，gif，png，webp等
                    }, file);
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                var imgLink = Q14.imageView2({
                    mode: 3,  // 缩略模式，共6种[0-5]
                    w: 100,   // 具体含义由缩略模式决定
                    h: 100,   // 具体含义由缩略模式决定
                    q: 100,   // 新图的图像质量，取值范围：1-100
                    format: 'jpg'  // 新图的输出格式，取值范围：jpg，gif，png，webp等
                }, file);
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
                draggg();
                //
                // $(".item").each(function(i){
                //     var arrs = [];
                //     var ind = $($(".item")[i]).attr("index");
                //     console.log(ind);
                // });
            },
            'FileUploaded': function (up, file, info) {
                if ((/\.(png|jpg|jpeg|gif|bmp)$/i).test(file.name)) {
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setComplete(up, info);

                    var res = $.parseJSON(info);
                    var key = res.key;
                    var domain = up.getOption('domain');
                    var url = domain + encodeURI(res.key);
                    // $("#wm14").attr("src",url);
                    // var input=document.getElementById('tosql14');

                    var imgLink = Q14.imageView2({
                        mode: 3,  // 缩略模式，共6种[0-5]
                        w: 100,   // 具体含义由缩略模式决定
                        h: 100,   // 具体含义由缩略模式决定
                        q: 100,   // 新图的图像质量，取值范围：1-100
                        format: 'jpg'  // 新图的输出格式，取值范围：jpg，gif，png，webp等
                    }, file);
                    // if($(".multifyImg2").length == 0 ){
                    // $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg2"/>').insertAfter($("#multifySpan2"));
                    // $("#wm00").hide();
                    // $('<li style="float: left"><img width="144px" height="90px" class="multifyImg2"/></li>').appendTo($("#multifyUl"));
                    // $(".no-img").css({"float":"none","margin-left":"80px"});
                    $("#wm00").hide();
                    $("gallery").hide();
                    $(".updateScreenShotShowLi").hide();

                    // }else {
                    // $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg2"/>').
                    // insertAfter(document.getElementsByClassName("multifyImg2")[document.getElementsByClassName("multifyImg2").length-1]);
                    var divs = $(".item").length;
                    if (divs >= 9) {
                        webToast("已经超过最大上传张数，此处最多可上传9张图片", "top",5000);
                        // alert("");
                        return false;
                    }
                    $('<li class="item-containt" style="float: left;width: 150px;height: 100px;margin: 5px;background: #ccc;"><div class="item"style="width: 150px;height: 100px;"><img style="width: 150px;height: 100px;" class="multifyImg2"/></div></li>').appendTo($(".multifyUl"));
                    $(".no-img").css({"margin-left": "105px"});
                    // }
                    var imgs = document.getElementsByClassName("multifyImg2"), img = imgs[imgs.length - 1];
                    // var inputs =document.getElementsByClassName("multifyInput"),input =inputs[inputs.length-1];
// =======
//                 if($(".multifyImg2").length == 0 ){
//                     $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg2"/>').insertAfter($("#multifySpan2"));
//                     $("#wm00").hide();
//                 }else {
//                     $('<!--<input type="hidden" name="accessories.worksScan" class="multifyInput"/>--><img width="144px" height="90px" class="multifyImg2"/>').
//                     insertAfter(document.getElementsByClassName("multifyImg2")[document.getElementsByClassName("multifyImg2").length-1]);
//                 }
//                 var imgs =document.getElementsByClassName("multifyImg2"),img=imgs[imgs.length-1];
//                 // var inputs =document.getElementsByClassName("multifyInput"),input =inputs[inputs.length-1];
// >>>>>>> 051f86a0a7e901a1571bd5eb000cb59975aefa55


                    $(img).attr("src", url);
                    // $(input).attr("src",url);


                    var _url = $("#urls").val() + url + ",";
                    $("#urls").val(_url);
                } else {
                    alert("图片上传格式有误");
                    return false;
                }
            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader14.bind('FileUploaded', function () {
        console.log('hello man 14,a file is uploaded');

    });

    $('#container').on(
        'dragenter',
        function (e) {
            e.preventDefault();
            $('#container').addClass('draging');
            e.stopPropagation();
        }
    ).on('drop', function (e) {
        e.preventDefault();
        $('#container').removeClass('draging');
        e.stopPropagation();
    }).on('dragleave', function (e) {
        e.preventDefault();
        $('#container').removeClass('draging');
        e.stopPropagation();
    }).on('dragover', function (e) {
        e.preventDefault();
        $('#container').addClass('draging');
        e.stopPropagation();
    });


    $('#show_code').on('click', function () {
        $('#myModal-code').modal();
        $('pre code').each(function (i, e) {
            hljs.highlightBlock(e);
        });
    });


    $('body').on('click', 'table button.btn', function () {
        $(this).parents('tr').next().toggle();
    });


    var getRotate = function (url) {
        if (!url) {
            return 0;
        }
        var arr = url.split('/');
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i] === 'rotate') {
                return parseInt(arr[i + 1], 10);
            }
        }
        return 0;
    };

    $('#myModal-img .modal-body-footer').find('a').on('click', function () {
        var img = $('#myModal-img').find('.modal-body img');
        var key = img.data('key');
        var oldUrl = img.attr('src');
        var originHeight = parseInt(img.data('h'), 10);
        var fopArr = [];
        var rotate = getRotate(oldUrl);
        if (!$(this).hasClass('no-disable-click')) {
            $(this).addClass('disabled').siblings().removeClass('disabled');
            if ($(this).data('imagemogr') !== 'no-rotate') {
                fopArr.push({
                    'fop': 'imageMogr2',
                    'auto-orient': true,
                    'strip': true,
                    'rotate': rotate,
                    'format': 'png'
                });
            }
        } else {
            $(this).siblings().removeClass('disabled');
            var imageMogr = $(this).data('imagemogr');
            if (imageMogr === 'left') {
                rotate = rotate - 90 < 0 ? rotate + 270 : rotate - 90;
            } else if (imageMogr === 'right') {
                rotate = rotate + 90 > 360 ? rotate - 270 : rotate + 90;
            }
            fopArr.push({
                'fop': 'imageMogr2',
                'auto-orient': true,
                'strip': true,
                'rotate': rotate,
                'format': 'png'
            });
        }

        $('#myModal-img .modal-body-footer').find('a.disabled').each(function () {

            var watermark = $(this).data('watermark');
            var imageView = $(this).data('imageview');
            var imageMogr = $(this).data('imagemogr');

            if (watermark) {
                fopArr.push({
                    fop: 'watermark',
                    mode: 1,
                    image: 'http://www.b1.qiniudn.com/images/logo-2.png',
                    dissolve: 100,
                    gravity: watermark,
                    dx: 100,
                    dy: 100
                });
            }

            if (imageView) {
                var height;
                switch (imageView) {
                    case 'large':
                        height = originHeight;
                        break;
                    case 'middle':
                        height = originHeight * 0.5;
                        break;
                    case 'small':
                        height = originHeight * 0.1;
                        break;
                    default:
                        height = originHeight;
                        break;
                }
                fopArr.push({
                    fop: 'imageView2',
                    mode: 3,
                    h: parseInt(height, 10),
                    q: 100,
                    format: 'png'
                });
            }

            if (imageMogr === 'no-rotate') {
                fopArr.push({
                    'fop': 'imageMogr2',
                    'auto-orient': true,
                    'strip': true,
                    'rotate': 0,
                    'format': 'png'
                });
            }
        });

        var newUrl = Qiniu.pipeline(fopArr, key);

        // var newImg = new Image();
        // img.attr('src', postPath+'/static/images/loading.gif');
        // newImg.onload = function() {
        //     img.attr('src', newUrl);
        //     img.parent('a').attr('href', newUrl);
        // };
        // newImg.src = newUrl;
        // return false;
    });


    var Q15 = new QiniuJsSDK();
    var uploader15 = Q15.uploader({
        runtimes: 'html5,flash,html4',
        browse_button: 'pickfiles15',
        container: 'container15',
        drop_element: 'container15',
        max_file_size: '100mb',
        flash_swf_url: 'js/plupload/Moxie.swf',
        dragdrop: true,
        chunk_size: '4mb',
        uptoken_url: $('#uptoken_url').val(),
        // uptoken_url: $('#uptoken_url').val(),  //当然建议这种通过url的方式获取token
        domain: $('#domain').val(),
        multi_selection: true,
        auto_start: true,
        init: {
            'FilesAdded': function (up, files) {
                $('table').show();
                $('#success').hide();
                plupload.each(files, function (file) {
                    file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setStatus("等待...");
                });
            },
            'BeforeUpload': function (up, file) {
                file.name = file.name.replace(/\,/g, "").replace(/\=/g, "");
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
                if (up.runtime === 'html5' && chunk_size) {
                    progress.setChunkProgess(chunk_size);
                }
            },
            'UploadProgress': function (up, file) {
                var progress = new FileProgress(file, 'fsUploadProgress');
                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));

                progress.setProgress(file.percent + "%", file.speed, chunk_size);
            },
            'UploadComplete': function () {
                $('#success').show();
            },
            'FileUploaded': function (up, file, info) {
                if ((/\.(png|jpg|jpeg|gif|bmp)$/i).test(file.name)) {
                    var progress = new FileProgress(file, 'fsUploadProgress');
                    progress.setComplete(up, info);

                    var res = $.parseJSON(info);
                    var key = res.key;
                    var domain = up.getOption('domain');
                    var url = domain + encodeURI(res.key);
                    // $("#wm14").attr("src",url);
                    // var input=document.getElementById('tosql14');
                    $("#beforeUpdateImg").attr("src", "");
                    if ($(".multifyInput3").length == 0) {
                        $('<input type="hidden" name="app.appScreenshot" class="multifyInput3"/><!--<img width="144px" height="90px" class="multifyImg2"/>-->').insertAfter($("#multifySpan3"));
                        // $("#wm00").hide();
                    } else {
                        $('<input type="hidden" name="app.appScreenshot" class="multifyInput3"/><!--<img width="144px" height="90px" class="multifyImg2"/>-->').insertAfter(document.getElementsByClassName("multifyInput3")[document.getElementsByClassName("multifyInput3").length - 1]);
                    }
                    var inputs = document.getElementsByClassName("multifyInput3"), input = inputs[inputs.length - 1];
                    // var inputs =document.getElementsByClassName("multifyInput"),input =inputs[inputs.length-1];

                    $(input).attr("value", url);
                    // $(input).attr("src",url);
                    var _url = $("#urls3").val() + url + ",";
                    $("#urls3").val(_url);
                    var id = $("#id").val();
                    $.post(postPath + "/app/sreen", {
                        id: id,
                        multifyInput2: _url
                    }, function (data) {
                        //window.location.replace(postPath+"/manager/app/update?id="+id)
                    });

                } else {
                    alert("图片上传格式有误");
                    return false;
                }


            },
            'Error': function (up, err, errTip) {
                $('table').show();
                var progress = new FileProgress(err.file, 'fsUploadProgress');
                progress.setError();
                progress.setStatus(errTip);
            }
        }
    });

    uploader15.bind('FileUploaded', function () {
        console.log('hello man 15,a file is uploaded');
    });
})

var strPath = window.document.location.pathname;
var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);


