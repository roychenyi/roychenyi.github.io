// 引入 events 模块
var events = require('events');
// 创建 eventEmitter 对象
var eventEmitter = new events.EventEmitter();

var fs=require("fs");

// 创建事件处理程序
var connectHandler = function connected() {
    console.log('连接成功。');

    // 触发 data_received 事件
    eventEmitter.emit('data_received');
}

// 绑定 connection 事件处理程序
eventEmitter.on('connection', connectHandler);

// 使用匿名函数绑定 data_received 事件
eventEmitter.on('data_received', function(){
    console.log('数据接收成功。');
});

// 触发 connection 事件 
eventEmitter.emit('connection');

/*events.once('connection', function (stream) {
    console.log('Ah, we have our first user!');
});*/

fs.readFile('app.js',function(err,data){
    if(err){
        console.log(err.stack);
        return
    }else{
        console.log(data.toString());
    }
});
console.log("程序执行完毕。");

var buf= new Buffer(234);
buf.write("你好！")
console.log(buf.toString(0,4));
var json=buf.toJSON(buf);
console.log(json)