/**
 * Created by chenyi on 2016/05/11.
 */
$(document).ready(function(){
/*    $(document).scroll(function(){
        var  scrollTop =  $(document).scrollTop(),
             top1=$('#leftMenu').offset().top,
             top2=$('#articleLoading').offset().top,
              Y = $('#leftMenu').offset().left; 
        if(scrollTop>top1){
            var navbarH=$('#navbar').height();
            var width=$('#leftMenu').width();
            var footerH=$('footer').height();
            var height=$(document).height();
            $('#leftMenu>div').css({position: 'fixed',top:navbarH,left:Y,bottom:footerH,width:width});
            if(footerH>(height-scrollTop)){
                $('#leftMenu>div').removeAttr('style');
            }
        }else{
            $('#leftMenu>div').removeAttr('style');
        }
        if(scrollTop>top2-500){
            UserMain.loadArticle();
        }
    });*/
    UserMain.loadArticle();
});

var UserMain={
    a:1,
  loadArticle:function(){
      this.a;
    var url=$('#articleLoading').data('url'),
        data='',
        page=Number($('#page').val()),
        pageCount=Number($('#pageCount').val());
      if(page==null||page<1||page==''){
         data='page=1';
          ajax(url,data,UserMain.loadArticle_result);
      }else if(page==pageCount){
       //   layer.msg('已经到底了…………亲',{icon:5,time:1000});
      	if($('#loadFlag').val()==='1'){
      	  data='page=1';
          ajax(url,data,UserMain.loadArticle_result);
      	}
          return;
      }else{
          page+=1;
          data='page='+page;
          ajax(url,data,UserMain.loadArticle_result);
      }
  },
  loadArticle_result:function(data){
      var $load=$('#articleLoading');
      if(data==null||data.length<1){
          $load.addClass('hidden');
      }else{
          var page=Number(data[0].page),
              pageCount=Number(data[0].pageCount),
              html='',
              d_url=getRootPath()+"/"+data[0].uUserDto.nickname+"/article/details/";
          $('#page').val(page);
          $('#pageCount').val(pageCount);
          for(var i=0;i<data.length;i++){
              html+="<div class='ibox no-padding animated fadeInUp'><div class='ibox-title'><h3>"
                    +"<a href='"+d_url+data[i].diaryId+"' target='_blank'>"+data[i].title+"</a>"
                    +"<span class='pull-right small'>("+new Date(data[i].writeTime).format('yyyy/MM/dd hh:mm:ss')+")</span>"
                  +"</h3></div>"
                  +"<div class='ibox-content'>"+data[i].content+"<hr>"
                  +"<div class='article_tool'><ul>"
                  +"<li><i class='fa fa-eye'></i>阅读<span>("+data[i].viewCount+")</span>&nbsp;&nbsp;&nbsp;</li>"
                  +"<li><i class=' fa  fa-thumbs-o-up '></i>点赞<span>("+data[i].upvoteCount+")</span>&nbsp;&nbsp;&nbsp;</li>"
                  +"<li><i class='fa fa-comment-o'></i>评论<span>("+data[i].replyCount+")</span>&nbsp;&nbsp;&nbsp;</li>"
                  +"<li><i class=' fa  fa-star-o '></i>收藏<span>("+data[i].favoriteCount+")</span></li> "
                  +"<li class='pull-right'><a href='"+d_url+data[i].diaryId+"' target='_blank'>查看更多&nbsp;&gt;&gt;&gt;</a></li>"
                  +"<ul></div></div></div>";
          }
          $load.before(html);
          if(page==pageCount){
              $load.html("<span class='text-info'>已经到底了…………</span>");
          }
      }
  }
};