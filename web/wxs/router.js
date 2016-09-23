/**
 * Created by dfjk on 2016/9/9.
 */
var http = require("http");
var url = require("url");
var fs = require('fs');

function start(route) {
    function onRequest(request, response) {
        var pathname = url.parse(request.url).pathname;
        console.log("Request for " + pathname + " received.");
        fs.readFile(pathname.substr(1),function(err,data){
            if (err) {
                console.log(err);
                // HTTP 状态码: 404 : NOT FOUND
                // Content Type: text/plain
                response.writeHead(404, {'Content-Type': 'text/html'});
            }else{
                // HTTP 状态码: 200 : OK
                // Content Type: text/plain
                response.writeHead(200, {'Content-Type': 'text/html'});

                // 响应文件内容
                response.write(data.toString());
                console.log(data.toString());
            }
            //  发送响应数据
            response.end();
        })
        route(pathname);


      /*  response.writeHead(200, {"Content-Type": "text/plain"});
        response.write("Hello World"+__filename);
        response.end();*/
    }

    http.createServer(onRequest).listen(8000);
    console.log("Server has started.");
}

exports.start = start;