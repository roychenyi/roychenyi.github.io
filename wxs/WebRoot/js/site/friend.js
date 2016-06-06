/**
 * Created by chenyi on 2016/06/04.
 */
    $(document).ready(function(){
        friend.show();
        friend.list();
    });
var friend={
    userId: $('#U_userId').val(),
    authorId:$('#author_id').val(),
    //显示添加好友的状态
    show:function(){
        if(this.userId!=this.authorId){
            var url=getRootPath()+"/friend/status",
                data="UUserDto.userId="+this.userId+"&FFriendDto.userId="+this.authorId;
            ajax(url,data,friend.show_result);

        }
    },
    show_result:function(data){
      $('#friend').removeClass('hidden');
        if(data.statusFlag==1){
            $('#follow').addClass('hidden');
            $('#friendId').val(data.id);
        }else{
            $('#followed').addClass('hidden');
        }
    },
    //添加好友
    add:function(){
        if(this.userId==''||this.userId==null){
         return false;
        }
        var url=getRootPath()+"/friend/add",
            data="UUserDto.userId="+this.userId+"&FFriendDto.userId="+this.authorId;
        ajax1(url,data,friend.add_result);
    },
    add_result:function(data){
        if(data.statusFlag==1) {
            $('#follow').addClass('hidden');
            $('#followed').removeClass('hidden');
            $('#friendId').val(data.id);
        }
    },
    //删除好友|取消关注
    nullfiy:function(){
        if(this.userId==''||this.userId==null){
            return false;
        }
        var url=getRootPath()+"/friend/delete",
            data="id="+$('#friendId').val();
        ajax1(url,data,friend.nullfiy_result);
    },
    nullfiy_result:function(data){
        if(data.statusFlag==1) {
            $('#followed').addClass('hidden');
            $('#follow').removeClass('hidden');
        }
    },
    //获取好友列表
    list:function(){
        var url=getRootPath()+"/friend/list1",
            data="UUserDto.userId="+this.authorId;
        ajax(url,data,friend.list_result);
    },
    list_result:function(data){
        if(data.length>0){
            var html="";
            for(var i=0;i<data.length;i++){
                var url=getRootPath()+"/"+data[i].fFriendDto.nickname,
                    name=data[i].fFriendDto.nickname,
                    img='';
                    if(data[i].fFriendDto.portrait==''||data[i].fFriendDto.portrait==null){
                    img=getRootPath()+"/images/face.png"
                    }else{
                    img=getRootPath()+"/"+data[i].fFriendDto.portrait;
                    }
                html+="<div class='col-sm-4 text-center'>"
                   +"<a href='"+url+"' target='_blank'>"
                   +"<img class='img-rounded' style='height: 50px;width: 50px;' src='"+img+"' title='"+name+"'>"
                   +"<p>"+name+"</p></a></div>"
            }
           $('#friendList').html(html);
        }else{
            $('#friendList').html("您还有关注其他人");
        }
    },
    //获取好友分组
    group:function(url){
        ajax(url,'',friend.group_result);
    },
    group_result: function (data) {

    },
    changeGroup:function(url){
        ajax(url,'',friend.changeGroup_result);
    },
    changeGroup_result:function(data){

    }



};
