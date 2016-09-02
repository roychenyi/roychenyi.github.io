// Custom scripts
$(document).ready(function () {

    // MetsiMenu
    $('#side-menu').metisMenu();

    // Collapse ibox function
    $('.collapse-link').click( function() {
        var ibox = $(this).closest('div.ibox');
        var button = $(this).find('i');
        var content = ibox.find('div.ibox-content');
        content.slideToggle(200);
        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
        ibox.toggleClass('').toggleClass('border-bottom');
        setTimeout(function () {
            ibox.resize();
            ibox.find('[id^=map-]').resize();
        }, 50);
    });

    // Close ibox function
    $('.close-link').click( function() {
        var content = $(this).closest('div.ibox');
        content.remove();
    });

    // Small todo handler
    $('.check-link').click( function(){
        var button = $(this).find('i');
        var label = $(this).next('span');
        button.toggleClass('fa-check-square').toggleClass('fa-square-o');
        label.toggleClass('todo-completed');
        return false;
    });

    // Append config box / Only for demo purpose
//    $.get("skin-config.html", function (data) {
//        $('body').append(data);
//    });

    // minimalize menu
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    })

    // tooltips
    $('.tooltip-demo').tooltip({
        selector: "[data-toggle=tooltip]",
        container: "body"
    })

    // Move modal to body
    // Fix Bootstrap backdrop issu with animation.css
    $('.modal').appendTo("body")

    // Full height of sidebar
    function fix_height() {
        var heightWithoutNavbar = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
    }
    fix_height();

    // Fixed Sidebar
    // unComment this only whe you have a fixed-sidebar
    //    $(window).bind("load", function() {
    //        if($("body").hasClass('fixed-sidebar')) {
    //            $('.sidebar-collapse').slimScroll({
    //                height: '100%',
    //                railOpacity: 0.9,
    //            });
    //        }
    //    })

    $(window).bind("load resize click scroll", function() {
        if(!$("body").hasClass('body-small')) {
            fix_height();
        }
    })

    $('#top-search').click(function(){
        location.href="search.html";
    });

    $('#user').click(function(){
        location.href="login.html";
    });

    $(document).scroll(function(){
        var  scrollTop =  $(document).scrollTop(),bodyHeight = $(window).height();
        if(scrollTop > bodyHeight){
            $('#gotop').css('display','block');
        }else{
            $('#gotop').css('display','none');
        }
    })


    $("[data-toggle=popover]").popover();
    //关闭点击触发导航栏下拉，实现鼠标移入触发
    //   $(document).off('click.bs.dropdown.data-api');
    dropdownOpen();
});

/**
 * 鼠标划过就展开子菜单，免得需要点击才能展开
 */
function dropdownOpen() {

    var $dropdownLi = $('li.dropdown');

    $dropdownLi.mouseover(function() {
        $(this).addClass('open');
    }).mouseout(function() {
        $(this).removeClass('open');
    });

    var $dropdownSpan=$('span.dropdown');
    $dropdownSpan.mouseover(function() {
        $(this).addClass('gray-bg open');
    }).mouseout(function() {
        $(this).removeClass('gray-bg open');
    });

}

function dropdown_open(_this){
    $(_this).addClass('gray-bg open');
}

function dropdown_close(_this){
    $(_this).removeClass('gray-bg open');
}

// For demo purpose - animation css script
function animationHover(element, animation){
    element = $(element);
    element.hover(
        function() {
            element.addClass('animated ' + animation);
        },
        function(){
            //wait for animation to finish before removing classes
            window.setTimeout( function(){
                element.removeClass('animated ' + animation);
            }, 2000);
        });
}

// Minimalize menu when screen is less than 768px
$(function() {
    $(window).bind("load resize", function() {
        if ($(this).width() < 769) {
            $('body').addClass('body-small')
        } else {
            $('body').removeClass('body-small')
        }
    })
})

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 100);
    } else if ($('body').hasClass('fixed-sidebar')){
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 300);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
}

// Dragable panels
function WinMove() {
    var element = "[class*=col]";
    var handle = ".ibox-title";
    var connect = "[class*=col]";
    $(element).sortable(
        {
            handle: handle,
            connectWith: connect,
            tolerance: 'pointer',
            forcePlaceholderSize: true,
            opacity: 0.8
        })
        .disableSelection();
};

var Main=function(){
this.a="test";
};
Main.prototype={

    loading:function(){
        var html="<div id='model-loading' style='position: fixed; top: 0;left: 0;width: 100%;height: 100%; z-index: 200000;'>"
            +"<div style='position: relative;top:calc(50% - 2em);left:calc(50% - 2em) ;color: #1c84c6'>" +
            "<i class='fa fa-spinner fa-spin fa-4x'></i></div></div>";
        $('body').append(html);
    },
    loadingMove:function(){
        $('#model-loading').remove();
    },
    submitStart:function(){
        this.loading();
    },
    submitEnd:function(){
        this.loadingMove();
    },
    submitFail:function(msg){
        bootbox.alert(msg);
    }
}

//提交开始
function submitStart() {
    layer.load();
}
//提交结束
function submitEnd(){
    layer.closeAll('loading')
}
//提交失败
function submitFail(msg){
    layer.alert(msg);
}
//ajax 提交函数，不带成功处理函数
function ajax(url,data_str){

    $.ajax({
        url:url,
        type:"POST",
        data:data_str,
        dataType:"json",
        async: false,
        beforeSend: function () {
            submitStart();
        },

        success: function (data) {
            if(data["statusFlag"]==-1){

            }else if(data["statusFlag"]==1){
                $.cookie("flag","true");
                flag=true;
            }
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            submitFail(textStatus || errorThrown);
            a=false;
        },

        complete: function () {
            submitEnd();
        }

    });
    return false;
}
//ajax带成功处理函数
function ajax1(url,data_str,success_function){
    var a=false;
    $.ajax({
        url:url,
        type:"POST",
        data:data_str,
        dataType:"json",
        async: false,
        beforeSend: function () {
            submitStart();
        },

        success: function(data){
            success_function(data)
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            submitFail(textStatus || errorThrown);
            a=false;
        },

        complete: function () {
            submitEnd();
        }

    });
    return a;
}
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht);
}
