/**
 * Created by dfjk on 2016/9/22.
 */
var express = require('express');
var app = express();
var bodyParser = require('body-parser');

app.use(express.static(__dirname));

// 创建 application/x-www-form-urlencoded 编码解析
var urlencodedParser = bodyParser.urlencoded({ extended: false })

//  主页输出 "Hello World"
app.get('/', function (req, res) {
    console.log("主页 GET 请求");
    res.send('Hello GET');
})


//  POST 请求
app.post('/', function (req, res) {
    console.log("主页 POST 请求");
    res.send('Hello POST');
})

//  /del_user 页面响应
app.get('/del_user', function (req, res) {
    console.log("/del_user 响应 DELETE 请求");
    res.send('删除页面');
})

//  /list_user 页面 GET 请求
app.get('/list_user', function (req, res) {
    console.log("/list_user GET 请求");
    res.send('用户列表页面');
})

// 对页面 abcd, abxcd, ab123cd, 等响应 GET 请求
app.get('/ab*cd', function(req, res) {
    console.log("/ab*cd GET 请求");
    res.send('正则匹配');
})

app.get('/index',function(req,res){
    console.log(__dirname);
    console.log(__filename);
    res.sendFile( __dirname + "/"+'index.html');
});

app.get('/express',function(req,res){
    console.log(__dirname);
    console.log(__filename);
    res.sendFile( __dirname + "/"+'express_demo.html');
});

app.get('/process_get', function (req, res) {

    // 输出 JSON 格式
    response = {
        first_name:req.query.first_name,
        last_name:req.query.last_name
    };
    console.log(response);
    res.end(JSON.stringify(response));
});

app.post('/process_get',urlencodedParser, function (req, res) {

    // 输出 JSON 格式
    console.log(req.body);
    console.log("Cookies: ", req.cookies);
    response = {
        first_name:req.body.first_name,
        last_name:req.body.last_name
    };
    console.log(response);
    res.end(JSON.stringify(response));
})


var server = app.listen(8081, function () {

    var host = server.address().address
    var port = server.address().port

    console.log("应用实例，访问地址为 http://%s:%s", host, port)

})