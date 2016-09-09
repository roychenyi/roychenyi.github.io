/**
 * Created by dfjk on 2016/9/9.
 */
var server = require("./server");
var router = require("./router");

server.start(router.route);