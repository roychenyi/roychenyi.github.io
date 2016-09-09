/**
 * Created by dfjk on 2016/9/9.
 */
var fs = require("fs");
var data ="当前时间："+  new Date().toLocaleString();
var net=require("net");

// 创建一个可以写入的流，写入到文件 output.txt 中
var writerStream = fs.createWriteStream('output.txt');

var readStream = fs.createReadStream('app.js');

// 使用 utf8 编码写入数据
//writerStream.write(data,'UTF8');
readStream.pipe(writerStream);

// 标记文件末尾
//writerStream.end();

readStream.on('data',function(trunk){
    data+= trunk;
});


// 处理流事件 --> data, end, and error
writerStream.on('finish', function() {
    console.log("写入完成。");
});

writerStream.on('error', function(err){
    console.log(err.stack);
});

console.log("程序执行完毕");
console.log(__filename);

// 输出当前目录
console.log('当前目录: ' + process.cwd());

// 输出当前版本
console.log('当前版本: ' + process.version);

// 输出内存使用情况
console.log(process.memoryUsage());

function app2(){
    var val;
    var val2;
    this.val=readStream.toString();
    this.val2=data+"test";
}

module.exports=app2;