-- use wxs

create table u_user
(
user_id int   not null  auto_increment,
site_id int   not null ,
username varchar(20) not null,
password varchar(50) not null,
nickname varchar(20) not null,
role_id  int not null,
sex smallint not null,
birth datetime ,
email varchar(50),
homepage varchar(100),
qq   varchar(16),
mobile varchar(16),
province_id int,
city_id int,
school_id int,
college_id int,
class varchar(10),
portrait varchar(100),
sign  int not null,
regtime datetime,
last_time datetime,
last_ip  varchar(48),
keep_day smallint,
online_status smallint,
rank_id int,
article_count int,
article_reply_count int,
topic_count int,
topic_reply_count int,
image_count int,
image_reply_count int,
guestbook_count  int,
tag_count int,
popedom int,
code_id  int,
primary key(user_id)
)ENGINE=innodb;



create index user_id on u_user
(
user_id
);

create table u_friend
(
id  int not null  auto_increment,
user_id int  not null,
friend_id int  not null,
friend_type int   not null,
friend_role int ,
add_time datetime not null,
group_id int not null,
status smallint,
primary key(id)
)engine=innodb;

create index user_id on u_friend
(
user_id
);

create index friend_id on u_friend
(
friend_id
);

create table u_friend_group
(
group_id int not null auto_increment,
name varchar(10) not null,
user_id int not null,
group_type int,
group_count int,
primary key(group_id)
)engine=innodb;

create index group_id on u_friend_group
(
group_id
);

create index user_id on u_friend_group
(
user_id
);

create table u_sign 
(
sign_id int not null auto_increment,
user_id int not null,
content varchar(140) not null,
create_time datetime,
status smallint,
primary key(sign_id)
)engine=innodb;

create index sign_id on u_sign
(
sign_id
);
create index user_id on u_sign
(
user_id
);

create table u_book
(
ubook_id int not null auto_increment,
user_id  int not null,
name int not null,
book_detaik text,
cover varchar(50),
time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(ubook_id) 
)ENGINE=INNODB;

create index ubook_id on u_book
(
ubook_id
);

create index user_id on u_book
(
user_id
);

create table b_site
(
site_id int not null auto_increment,
user_id int not null,
name varchar(20) not null,
title varchar(10) not null,
site_url varchar(100) not null,
Site_detail varchar(250),
logo  varchar(50),
style_id int  not null,
flag smallint ,
create_time datetime,
last_time datetime,
mode smallint,
access_code varchar(50),
index_name varchar(10),
diary_name varchar(10),
sound_name varchar(10),
image_name varchar(10),
friend_name varchar(10),
site_type int,
status smallint,
primary key(site_id)
)ENGINE=INnodb;

create index site_id on b_site
(
site_id
);
create index user_id on b_site
(
user_id 
);
create index name on b_site
(
name
);

create table b_config
(
bconfig_id int not null auto_increment,
site_id int not null,
config_key varchar(20),
value varchar(36),
create_time datetime,
last_time datetime,
primary key(bconfig_id)
)engine=innodb;

create index bconfig_id on b_config
(
bconfig_id
);
create index site_id on b_config 
(
site_id
);

create table b_tag
(
tag_id int not null auto_increment,
site_id int not null,
ref_id int not null,
ref_type smallint ,
time datetime,
tag_name varchar(20),
primary key(tag_id)
)engine=innodb;

create index tag_id on b_tag
(
tag_id
);
create index  site_id on b_tag(
site_id
);

create table b_status
(
bstatus_id int not null auto_increment,
site_id int not null,
time datetime not null,
uv_count int,
pv_count int,
type smallint,
update_time datetime,
primary key(bstatus_id)
)engine=innodb;

create index bstatus_id on b_status
(
bstatus_id
);

create index site_id on b_status
(
site_id
);

create table b_access
(
baccess_id bigint not null auto_increment,
site_id int not null,
user_id int not null,
time datetime,
client_agent varchar(250),
client_ip varchar(50),
client_type smallint ,
primary key(baccess_id)
)engine=innodb;

create index baccess_id on b_access(
baccess_id
);

create index site_id on b_access(
site_id
);

create index user_id on b_access(
user_id
);

create table b_backlist
(
blacklist_id int not null auto_increment,
user_id int not null,
back_id int not null,
bl_type int ,
add_time datetime,
primary key(blacklist_id)
)engine=innodb;

create index blacklist_id on b_backlist
(
blacklist_id
);
create index user_id on b_backlist
(
user_id
);
create index back_id on b_backlist
(
back_id
);

create table d_diary
(
diary_id int not null auto_increment,
user_id int not null,
site_id int not null,
catalog_id int not null,
title varchar(50) not null,
content text not null,
drary_size int,
reply_count int,
view_count int,
client_type smallint,
client_ip varchar(50),
client_agent varchar(250),
write_time datetime,
last_read_time datetime,
last_reply_time datetime,
modify_time datetime,
reply_notify smallint,
mood_level smallint,
locked smallint,
contribute smallint,
role smallint,
tag varchar(100),
annex_id int ,
ext1 int,
ext2 int,
ext3 int,
ext4 int,
primary key(diary_id)

)engine=innodb;

create index diary_id on d_diary
(
diary_id
);
create index site_id on d_diary
(site_id);
create index user_id on d_diary
(user_id);

create table d_reply1
(
dreply_id int not null auto_increment,
diary_id int not null,
user_id  int not null,
client_type smallint,
owner_only smallint,
write_time  datetime,
status smallint,
primary key(dreply_id)
)engine=innodb;

create index dreply_id on d_reply1(
dreply_id
);
create index diary_id on d_reply1(
diary_id
);
create index user_id on d_reply1(
user_id
);

create table d_reply2
(
dreply1_id int not null auto_increment,
dreply_id int not null,
user_id int not null,
commentator int not null,
content text not null,
client_angent varchar(250),
client_ip varchar(50),
client_type smallint,
reply_time datetime,
reply_id int,
sort_order smallint,
ext1 int ,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(dreply1_id)
)engine=innodb;

create index dreply1_id on d_reply2(
dreply1_id
);
create index dreply_id on d_reply2(
dreply_id
);
create index user_id on d_reply2(
user_id
);

create table d_upvote
(
dupvote_id int not null auto_increment,
diary_id int not null,
user_id int not null,
client_agent varchar(250),
client_ip  varchar(50),
client_type smallint,
time datetime ,
status smallint,
primary key(dupvote_id)
)engine=INNODB;

create index dupvote_id on d_upvote
(
dupvote_id 
);

create index diary_id on d_upvote
(
diary_id
);
create index user_id on d_upvote
(
user_id
);

create table d_access
(
daccess_id bigint not null auto_increment,
diary_id int not null,
user_id int not null,
client_agent varchar(250) ,
client_ip varchar(50),
client_type smallint,
time datetime,
primary key(daccess_id)
)engine=innodb;

create index daccess_id on d_access
(
daccess_id
);
create index diary_id on d_access
(
diary_id
);
create index user_id on d_access
(
user_id
);

create table d_catalog
(
catalog_id int not null auto_increment,
site_id int not null,
user_id int not null,
catalog_name varchar(20) not null,
description varchar(200) ,
create_time datetime ,
article_count int,
catalog_type int,
verifyCode varchar(20),
sort_order smallint,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(catalog_id)
)engine=innodb;

create index catalog_id on d_catalog
(
catalog_id
);
create index site_id on d_catalog
(
site_id
);

/**
创建附件表
**/
create table d_annex
(
annex_id int not null auto_increment,
user_id int not null ,
site_id int not null,
diary_id int not null,
upload_time datetime,
download_count int,
status smallint,
validate smallint,
url varchar(256),
path varchar(256),
file_name varchar(256),
file_description varchar(1024),
size int,
last_time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(annex_id)
)engine=innodb;

create index annex_id on d_annex
(
annex_id
);
create index site_id on d_annex
(
site_id
);
create index user_id on d_annex
(
user_id
);
create index diary_id on d_annex
(
diary_id
);

create table m_guestbook
(
guestbook_id int not null auto_increment,
site_id int not null,
user_id int not null,
visitor_id int not null,
content varchar(1024) not null,
client_type smallint,
client_ip  varchar(50),
client_agent varchar(250),
time datetime,
primary key(guestbook_id)
)engine=innodb;

create index guestbook_id on m_guestbook
(
guestbook_id
);
create index site_id on m_guestbook
(
site_id
);
create index user_id on m_guestbook
(
user_id
);

/**
留言回复表
*/

create table m_reply
(
mreply_id int not null auto_increment,
guestbook_id int not null,
user_id int not null ,
content varchar(1024),
time datetime,
reply_id int ,
client_type smallint,
client_ip varchar(50),
client_agent varchar(250),
primary key(mreply_id)
)engine=innodb;
create index mreply_id on m_reply
(
mreply_id
);
create index guestbook_id on m_reply
(
guestbook_id
);
create index user_id on m_reply
(
user_id
);

/*
创建消息表
*/
create table m_message
(
message_id int not null auto_increment,
user_id int not null,
receive_id int not null,
content varchar(1024),
send_time datetime,
expire_time datetime,
read_time datetime,
status smallint ,
client_type smallint,
client_ip varchar(50),
client_agent varchar(250),
primary key(message_id)
)engine=innodb;
create index message_id on m_message
(
message_id
);
create index user_id on m_message
(
user_id
);
create index receive_id on m_message(
receive_id
);


create table m_affiche 
(
affiche_id int not null auto_increment,
user_id int not null ,
title varchar(20),
content varchar(1024),
send_time datetime,
expire_time datetime,
status smallint,
primary key(affiche_id)
)engine=innodb;

create index affiche_id on m_affiche
(
affiche_id
);
create index user_id on m_affiche
(
user_id
);

create table s_sound
(
sound_id int not null auto_increment,
box_id int not null,
user_id int not null,
title varchar(20) ,
content varchar(1024),
url varchar(256),
file_name varchar(100),
file_path varchar(200),
time datetime,
status smallint,
view_count int,
download_count int,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(sound_id)
)engine=innodb;

create index sound_id on s_sound
(
sound_id
);
create index box_id on s_sound
(
box_id
);
create index user_id on s_sound
(
user_id
);

/*声音-盒子*/
create table s_box
(
box_id int not null auto_increment,
user_id int not null,
name varchar(20),
description text,
create_time datetime,
sound_count int,
sort_order smallint,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(box_id) 
)engine=innodb;

create index box_id on s_box(
box_id
);
create index user_id on s_box(
user_id
);

create table s_reply1
(
sreply_id int not null auto_increment,
sound_id int not null,
user_id  int not null,
client_type smallint,
owner_only smallint,
write_time  datetime,
status smallint,
primary key(sreply_id)
)engine=innodb;

create index sreply_id on s_reply1(
sreply_id
);
create index sound_id on s_reply1(
sound_id
);
create index user_id on s_reply1(
user_id
);

create table s_reply2
(
sreply1_id int not null auto_increment,
sreply_id int not null,
user_id int not null,
commentator int not null,
content text not null,
client_angent varchar(250),
client_ip varchar(50),
client_type smallint,
reply_time datetime,
reply_id datetime,
sort_order smallint,
ext1 int ,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(sreply1_id)
)engine=innodb;

create index sreply1_id on s_reply2(
sreply1_id
);
create index sreply_id on s_reply2(
sreply_id
);
create index user_id on s_reply2(
user_id
);

create table s_upvote
(
supvote_id int not null auto_increment,
sound_id int not null,
user_id int not null,
client_agent varchar(250),
client_ip  varchar(50),
client_type smallint,
time datetime ,
status smallint,
primary key(supvote_id)
)engine=INNODB;

create index supvote_id on s_upvote
(
supvote_id 
);

create index sound_id on s_upvote
(
sound_id
);
create index user_id on s_upvote
(
user_id
);

create table s_access
(
saccess_id bigint not null auto_increment,
sound_id int not null,
user_id int not null,
client_agent varchar(250) ,
client_ip varchar(50),
client_type smallint,
time datetime,
primary key(saccess_id)
)engine=innodb;

create index saccess_id on s_access
(
saccess_id
);
create index sound_id on s_access
(
sound_id
);
create index user_id on s_access
(
user_id
);

create table i_image
(
image_id int not null auto_increment,
album_id int not null,
user_id int not null,
site_id int not null,
name varchar(20),
content varchar(1024),
file_name varchar(100),
file_path varchar(256),
url varchar(256),
preview_url varchar(256),
tag varchar(100),
time datetime,
year smallint,
mount smallint,
day smallint,
width smallint,
height smallint,
image_type int,
client_type smallint,
client_ip varchar(50),
client_agent varchar(250),
status smallint,
reply_count int,
view_count int,
download_count int,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(image_id)
)engine=innodb;
create index image_id on i_image
(
image_id
);
create index album_id on i_image
(
album_id
);
create index user_id on i_image
(
user_id
);
create index site_id on i_image
(
site_id
);

create table i_album
(
album_id int not null auto_increment,
site_id int not null,
user_id int not null,
name varchar(20),
description text,
ablum_type int,
ablum_count int,
access_question varchar(200)
verifycode varchar(50),
create_time datetime,
sort_order smallint,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(album_id)
)engine=innodb;
create index album_id on i_album
(album_id);
create  index site_id on i_album
(
site_id
);
create index user_id on i_album
(
user_id
);

create table i_reply1
(
ireply_id int not null auto_increment,
image_id int not null,
user_id  int not null,
client_type smallint,
owner_only smallint,
write_time  datetime,
status smallint,
primary key(ireply_id)
)engine=innodb;

create index ireply_id on i_reply1(
ireply_id
);
create index image_id on i_reply1(
image_id
);
create index user_id on i_reply1(
user_id
);

create table i_reply2
(
ireply1_id int not null auto_increment,
ireply_id int not null,
user_id int not null,
commentator int not null,
content text not null,
client_angent varchar(250),
client_ip varchar(50),
client_type smallint,
reply_time datetime,
reply_id datetime,
sort_order smallint,
ext1 int ,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(ireply1_id)
)engine=innodb;

create index ireply1_id on i_reply2(
ireply1_id
);
create index ireply_id on i_reply2(
ireply_id
);
create index user_id on i_reply2(
user_id
);

create table i_upvote
(
iupvote_id int not null auto_increment,
image_id int not null,
user_id int not null,
client_agent varchar(250),
client_ip  varchar(50),
client_type smallint,
time datetime ,
status smallint,
primary key(iupvote_id)
)engine=INNODB;

create index iupvote_id on i_upvote
(
iupvote_id 
);

create index image_id on i_upvote
(
image_id
);
create index user_id on i_upvote
(
user_id
);

create table i_access
(
iaccess_id bigint not null auto_increment,
image_id int not null,
user_id int not null,
client_agent varchar(250) ,
client_ip varchar(50),
client_type smallint,
time datetime,
primary key(iaccess_id)
)engine=innodb;

create index iaccess_id on i_access
(
iaccess_id
);
create index image_id on i_access
(
image_id
);
create index user_id on i_access
(
user_id
);

/*投票*/
create table v_vote 
(
vote_id int not null auto_increment,
user_id int not null,
vconfig_id int ,
content varchar(1024),
create_time datetime,
update_time datetime,
primary key(vote_id)
)engine=innodb;

create index vote_id on v_vote
(
vote_id
);
create index user_id on v_vote
(
user_id
);

create table v_item
(
item_id int not null auto_increment,
vote_id int not null,
content varchar(1024) not null,
create_time datetime,
udapte_time datetime,
count int,
sort_order smallint,
ext1 int ,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(item_id)
)engine=innodb;
create index item_id on v_item
(
item_id
);
create index vote_id on v_item
(
vote_id
);

create table v_count
(
vcount_id int not null auto_increment,
vote_id int not null,
item_id int not null,
user_id int not null,
client_agent varchar(250),
client_ip varchar(50),
client_type smallint,
time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(vcount_id)
)engine=innodb;
create index vcount_id on v_count
(
vcount_id 
);
create index vote_id on v_count
(
vote_id
);
create index item_id on v_count
(
item_id
);
create index user_id on v_count
(
user_id
);

create table v_reply1
(
vreply_id int not null auto_increment,
vote_id int not null,
user_id  int not null,
client_type smallint,
owner_only smallint,
write_time  datetime,
status smallint,
primary key(vreply_id)
)engine=innodb;

create index vreply_id on v_reply1(
vreply_id
);
create index vote_id on v_reply1(
vote_id
);
create index user_id on v_reply1(
user_id
);

create table v_reply2
(
vreply1_id int not null auto_increment,
vreply_id int not null,
user_id int not null,
commentator int not null,
content text not null,
client_angent varchar(250),
client_ip varchar(50),
client_type smallint,
reply_time datetime,
reply_id datetime,
sort_order smallint,
ext1 int ,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(vreply1_id)
)engine=innodb;

create index vreply1_id on v_reply2(
vreply1_id
);
create index vreply_id on v_reply2(
vreply_id
);
create index user_id on v_reply2(
user_id
);

create table v_upvote
(
vupvote_id int not null auto_increment,
vote_id int not null,
user_id int not null,
client_agent varchar(250),
client_ip  varchar(50),
client_type smallint,
time datetime ,
status smallint,
primary key(vupvote_id)
)engine=INNODB;

create index vupvote_id on v_upvote
(
vupvote_id 
);

create index vote_id on v_upvote
(
vote_id
);
create index user_id on v_upvote
(
user_id
);

create table v_access
(
vaccess_id bigint not null auto_increment,
vote_id int not null,
user_id int not null,
client_agent varchar(250) ,
client_ip varchar(50),
client_type smallint,
time datetime,
primary key(vaccess_id)
)engine=innodb;

create index vaccess_id on v_access
(
vaccess_id
);
create index vote_id on v_access
(
vote_id
);
create index user_id on v_access
(
user_id
);

create table w_wxs
(
wxs_id int not null auto_increment,
account int not null,
password varchar(48) not null,
name varchar(20),
title varchar(10),
wxs_url varchar(100),
wxs_detail varchar(250),
logo  varchar(200),
style_id int ,
flag smallint,
create_time datetime,
last_time datetime,
mode smallint,
access_mode varchar(50),
index_name varchar(10),
diary_name varchar(10),
sound_name varchar(10),
image_name varchar(10),
site_type int,
status smallint,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(wxs_id)
);
create index wxs_id on w_wxs
(
wxs_id
);
create index account on w_wxs
(
account
);
create index name on w_wxs
(
name
);

create table w_config
(
wconfig_id int not null auto_increment,
wxs_id int not null,
config_key varchar(20),
value varchar(36),
create_time datetime,
last_time datetime,
primary key(wconfig_id)
)engine=innodb;

create index wconfig_id on w_config
(
wconfig_id
);
create index wxs_id on w_config
(
wxs_id
);

create table w_status
(
wstatus_id int not null auto_increment,
wxs_id int not null,
time datetime,
uv_count int,
pv_count int,
type int,
update_time datetime,
primary key(wstatus_id) 
)engine=innodb;
create index wstatus_id on w_status
(
wstatus_id
);
create index wxs_id on w_status
(
wxs_id
);

create table w_book
(
wbook_id int not null auto_increment,
wxs_id int not null,
name varchar(50),
book_dateil text,
cover varchar(50),
create_time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(wbook_id)
)engine=innodb;
create index wbook_id on w_book
(
wbook_id
);

create index wxs_id on w_book
(
wxs_id
);

create table w_link
(
wlink_id int not null auto_increment,
wxs_id int not null,
name varchar(10),
link_url varchar(200),
logo  varchar(50),
link_type int,
create_time datetime,
status smallint,
sort_order smallint,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(wlink_id)
)engine=innodb;
create index wlink_id on w_link
(
wlink_id
);
create index wxs_id on w_link
(
wxs_id
);

create table w_access
(
waccess_id bigint not null auto_increment,
user_id int not null,
client_agent varchar(250),
client_ip varchar(50),
client_type smallint,
time datetime,
primary key(waccess_id)
)engine=innodb;

create index waccess on w_access
(
waccess_id
);
create index user_id on w_access
(
user_id
);

alter table w_access add CONSTRAINT FK_w_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;




/*系统设置*/
create table sys_invitation_code
(
code_id int not null auto_increment,
user_id int not null,
wxs_id int not null,
code varchar(16),
reg_time datetime,
client_agent varchar(250),
client_ip varchar(50),
client_type smallint ,
status smallint,
flag smallint,
primary key(code_id)
)engine=innodb;
create index code_id on sys_invitation_code
(
code_id
);
create index  wxs_id on sys_invitation_code
(
wxs_id
);

create table sys_style
(
style_id int not null auto_increment,
name varchar(20) ,
path varchar(256),
status smallint,
create_time datetime,
cover varchar(50),
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(style_id)
)engine=innodb;
create index style_id on sys_style
(
style_id
);

create table sys_blocked_ip
(
blockedip_id int not null auto_increment,
ip varchar(50),
ipmask varchar(50),
blocked_type int,
time datetime,
status smallint,
update_time datetime,
primary key(blockedip_id)
)engine=innodb;
create index blockedip_id on sys_blocked_ip
(
blockedip_id
);

create table sys_config
(
config_id int not null auto_increment,
config_name varchar(20),
value varchar(36),
create_time datetime,
last_time datetime,
primary key(config_id)
)engine=innodb;
create index config_id on sys_config
(
config_id
);
create index config_name on sys_config
(
config_name
);

create table sys_rank
(
rank_id int not null auto_increment,
name varchar(20),
rank_high int not null,
rank_low int not null,
rank_logo varchar(200),
time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(rank_id)
)engine=innodb;
create index rank_id on sys_rank
(
rank_id
);
create index name on sys_rank
(
name
);

create table sys_school
(
school_id int not null auto_increment,
name varchar(20),
description text,
address varchar(100),
province_id int,
city_id int,
post smallint,
primary key(school_id)
)engine=innodb;
create index school_id on sys_school
(
school_id
);

create table sys_college
(
college_id int not null auto_increment,
school_id int,
name varchar(20),
description text,
primary key(college_id)
)engine=innodb;
create index college_id on sys_college
(
college_id
);
create index school_id on sys_college
(
school_id
);

create table sys_province
(
province_id int not null auto_increment,
name varchar(20),
description text,
primary key(province_id)
)engine=innodb;
create index province_id on sys_province
(
province_id
);

create table sys_city
(
city_id int not null auto_increment,
province_id int not null,
name varchar(20) not null,
description text,
primary key(city_id)
)engine=innodb;
create index city_id on sys_city
(
city_id
);
create index province_id on sys_city
(
province_id
);

create table  sys_login_record
(
record_id int not null auto_increment,
user_id int,
create_time datetime,
client_agent varchar(250),
client_ip varchar(50),
client_type smallint,
primary key(record_id)
)engine=innodb;
create index record_id on sys_login_record
(
record_id
);
create index user_id on sys_login_record
(
user_id
);

create table sys_role
(
role_id int not null auto_increment,
name varchar(20),
value varchar(36),
create_time datetime,
last_time datetime,
primary key(role_id)
)engine=innodb;
create index role_id on sys_role 
(
role_id
);
create index name on sys_role
(
name
);

create table sys_type 
(
type_id int not null  auto_increment,
name varchar(20),
value varchar(36),
create_time datetime,
last_time datetime,
primary key(type_id)
)engine=innodb;
create index type_id on sys_type 
(
type_id
);
create table sys_topic 
(
topic_id int not null auto_increment,
user_id int , 
topic_key  varchar(20),
description text,
topic_index int,
create_time datetime,
ext1 int,
ext2 int,
ext3 varchar(1024),
ext4 varchar(1024),
primary key(topic_id)
)engine=innodb;
create index  topic_id on sys_topic
(
topic_id
);
create index user_id on sys_topic
(
user_id
);

create index topic_key on sys_topic
(
topic_key
);

create table sys_link
(
link_id int  not null auto_increment,
name varchar(10),
link_url varchar(200),
link_type int ,
create_time datetime,
status smallint,
sort_order smallint,
primary key(link_id)
)engine=innodb;
create index link_id on sys_link
(
link_id
);
create index name on sys_link
(
name
);

alter table u_user add constraint FK_u_user_site foreign key (user_id)
      references b_site (user_id) on delete restrict on update restrict;

alter table u_friend_group add constraint FK_u_friend_group_user foreign key (user_id) 
      references u_user (user_id) on delete restrict on update restrict;

alter table u_book add constraint FK_u_book_user foreign key (user_id) 
     references u_user (user_id) on delete restrict on update restrict;

alter table b_site add constraint FK_b_site_user foreign key (user_id)
     references u_user (user_id) on delete restrict on update restrict;

alter table b_site add constraint FK_u_site_style foreign key (style_id)
     REFERENCES sys_style (style_id) on delete RESTRICT on update restrict;

alter table b_config add CONSTRAINT FK_b_config_site foreign key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update restrict;

alter table b_tag add CONSTRAINT FK_b_tag_site foreign key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update RESTRICT;

alter table b_tag  add CONSTRAINT FK_b_tag_diary foreign key (ref_id)
     REFERENCES d_diary (diary_id) on delete RESTRICT on update RESTRICT;

alter table b_status add CONSTRAINT FK_b_status_site foreign key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update RESTRICT;

alter table b_access add CONSTRAINT FK_b_access_site foreign key (site_id)
     REFERENCES b_site (site_id) on delete RESTrict on update RESTRICT;

alter table b_access add CONSTRAINT FK_b_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table b_backlist add CONSTRAINT FK_b_backlist_user1 foreign key (user_id)
     REFERENCES u_user  (user_id) on delete RESTRICT on update RESTRICT;

alter table b_backlist add CONSTRAINT FK_b_backlistt_user2 foreign key (back_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_diary add CONSTRAINT FK_d_diary_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_diary add CONSTRAINT FK_d_diary_site foreign key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update RESTRICT;

alter table d_diary add CONSTRAINT FK_d_diary_catalog foreign key (catalog_id)
     REFERENCES d_catalog (catalog_id) on delete RESTRICT on update RESTRICT;

alter table d_reply1 add CONSTRAINT FK_d_reply1_diary foreign key (diary_id)
     REFERENCES d_diary (diary_id) on delete RESTRICT on update RESTRICT;

alter table d_reply1 add CONSTRAINT FK_d_reply1_user FOREIGN key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;


alter table d_reply2 add CONSTRAINT FK_d_reply2_reply1 FOREIGN key (dreply_id)
     REFERENCES d_reply1 (dreply_id) on delete RESTRICT on update RESTRICT;

alter table d_reply2 add CONSTRAINT FK_d_reply2_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_upvote add CONSTRAINT FK_d_upvote_diary foreign key (diary_id)
     REFERENCES d_diary (diary_id) on delete RESTRICT on update RESTRICT;

alter table d_upvote add CONSTRAINT FK_d_upvote_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_access add CONSTRAINT FK_d_access_diary foreign key (diary_id)
     REFERENCES d_diary (diary_id) on delete RESTRICT on update RESTRICT;

alter table d_access add CONSTRAINT FK_d_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_catalog add  CONSTRAINT FK_d_catalog_site FOREIGN key (site_id)
     REFERENCES b_site (site_id) on delete RESTrict on update RESTRICT;

alter table d_catalog add CONSTRAINT FK_d_catalog_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;



alter table d_annex add CONSTRAINT FK_d_annex_diary foreign key (diary_id)
     REFERENCES d_diary (diary_id) on delete RESTRICT on update RESTRICT;

alter table d_annex add CONSTRAINT FK_d_annex_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_annex add CONSTRAINT FK_d_annex_site FOREIGN key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update RESTRICT;

alter table m_guestbook add CONSTRAINT FK_m_guestbook_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table m_guestbook add CONSTRAINT FK_m_guestbook_site FOREIGN key (site_id)
     REFERENCES b_site (site_id) on delete RESTRICT on update RESTRICT;

alter table m_reply add CONSTRAINT FK_m_reply_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table m_reply add CONSTRAINT FK_m_reply_guestbook foreign key (guestbook_id)
     REFERENCES m_guestbook (guestbook_id) on delete RESTRICT on update RESTRICT;

alter table m_message add CONSTRAINT FK_m_message_user1 foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table m_message add CONSTRAINT FK_m_message_user2 foreign key (receive_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table m_affiche add CONSTRAINT FK_m_affiche_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_sound add CONSTRAINT FK_s_sound_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_sound add CONSTRAINT FK_s_sound_box foreign key (box_id)
     REFERENCES s_box (box_id) on delete RESTRICT on update RESTRICT;

alter table s_box add CONSTRAINT FK_s_box_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_reply1 add CONSTRAINT FK_s_reply1_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_reply1 add CONSTRAINT FK_s_reply1_sound foreign key (sound_id)
     REFERENCES s_sound (sound_id) on delete RESTRICT on update RESTRICT;

alter table s_reply2 add CONSTRAINT FK_s_reply2_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_reply2 add CONSTRAINT FK_s_reply2_sreply1 foreign key (sreply_id)
     REFERENCES s_reply1 (sreply_id) on delete RESTRICT on update RESTRICT;

alter table s_upvote add CONSTRAINT FK_s_upvote_sound foreign key (sound_id)
     REFERENCES s_sound (sound_id) on delete RESTRICT on update RESTRICT;

alter table s_upvote add CONSTRAINT FK_s_upvote_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_access add CONSTRAINT FK_s_access_sound foreign key (sound_id)
     REFERENCES s_sound (sound_id) on delete RESTRICT on update RESTRICT;

alter table s_access add CONSTRAINT FK_s_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_image add CONSTRAINT FK_i_image_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_image add CONSTRAINT FK_i_image_album FOREIGN key (album_id)
     REFERENCES i_album (album_id) on delete RESTRICT on update RESTRICT;

alter table i_image  add CONSTRAINT FK_i_image_site FOREIGN key (site_id)
     REFERENCES b_site (user_id) on delete RESTRICT on update RESTRICT;

alter table i_album add CONSTRAINT FK_i_album_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_album  add CONSTRAINT FK_i_album_site FOREIGN key (site_id)
     REFERENCES b_site (user_id) on delete RESTRICT on update RESTRICT;

alter table i_reply1 add CONSTRAINT FK_i_reply1_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_reply1 add CONSTRAINT FK_i_reply1_image foreign key (image_id)
     REFERENCES i_image (image_id) on delete RESTRICT on update RESTRICT;

alter table i_reply2 add CONSTRAINT FK_i_reply2_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_reply2 add CONSTRAINT FK_i_reply2_ireply1 foreign key (ireply_id)
     REFERENCES i_reply1 (ireply_id) on delete RESTRICT on update RESTRICT;

alter table i_upvote add CONSTRAINT FK_i_upvote_image foreign key (image_id)
     REFERENCES i_image (image_id) on delete RESTRICT on update RESTRICT;

alter table i_upvote add CONSTRAINT FK_i_upvote_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_access add CONSTRAINT FK_i_access_sound foreign key (image_id)
     REFERENCES i_image (image_id) on delete RESTRICT on update RESTRICT;

alter table i_access add CONSTRAINT FK_i_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_vote add CONSTRAINT FK_v_vote_user FOREIGN key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_item add CONSTRAINT FK_v_item_vote foreign key (vote_id)
     REFERENCES v_vote (vote_id) on delete RESTRICT on update RESTRICT;


alter table v_count add CONSTRAINT FK_v_count_vote foreign key (vote_id)
     REFERENCES v_vote (vote_id) on delete RESTRICT on update RESTRICT;

alter table v_count add CONSTRAINT FK_v_count_item foreign key (item_id)
     REFERENCES v_item (item_id) on delete RESTRICT on update RESTRICT;

alter table v_count add CONSTRAINT FK_v_count_user FOREIGN key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_reply1 add CONSTRAINT FK_v_reply1_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_reply1 add CONSTRAINT FK_v_reply1_image foreign key (vote_id)
     REFERENCES v_vote (vote_id) on delete RESTRICT on update RESTRICT;

alter table v_reply2 add CONSTRAINT FK_v_reply2_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_reply2 add CONSTRAINT FK_v_reply2_vreply1 foreign key (vreply_id)
     REFERENCES v_reply1 (vreply_id) on delete RESTRICT on update RESTRICT;

alter table v_upvote add CONSTRAINT FK_v_upvote_image foreign key (vote_id)
     REFERENCES v_vote (vote_id) on delete RESTRICT on update RESTRICT;

alter table v_upvote add CONSTRAINT FK_v_upvote_user foreign key (vote_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_access add CONSTRAINT FK_v_access_sound foreign key (vote_id)
     REFERENCES v_vote (vote_id) on delete RESTRICT on update RESTRICT;

alter table v_access add CONSTRAINT FK_v_access_user foreign key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table w_config add CONSTRAINT FK_w_cofig_wxs FOREIGN key (wxs_id)
     REFERENCES w_wxs (wxs_id) on delete RESTRICT on update RESTRICT;

alter table w_status add CONSTRAINT FK_w_status_wxs FOREIGN key (wxs_id)
     REFERENCES w_wxs (wxs_id) on delete RESTRICT on update RESTRICT;

alter table w_book add CONSTRAINT FK_w_book_wxs FOREIGN key (wxs_id)
     REFERENCES w_wxs (wxs_id) on delete RESTRICT on update RESTRICT;

alter table w_link add CONSTRAINT FK_w_link_wxs FOREIGN key (wxs_id)
     REFERENCES w_wxs (wxs_id) on delete RESTRICT on update RESTRICT;

/*alter table sys_invitation_code add CONSTRAINT FK_sys_invitation_code_wxs FOREIGN key (wxs_id)
     REFERENCES w_wxs (wxs_id) on delete RESTRICT on update RESTRICT;

alter table sys_invitation_code add CONSTRAINT FK_sys_invitation_code_user FOREIGN key (user_id)
     REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;
*/
alter table sys_school add CONSTRAINT FK_sys_school_province FOREIGN key (province_id)
     REFERENCES sys_province (province_id) on delete RESTRICT on update RESTRICT;

alter table sys_school add CONSTRAINT FK_sys_school_city FOREIGN key (city_id)
     REFERENCES sys_city (city_id) on delete RESTRICT on update RESTRICT;

alter table sys_college add CONSTRAINT FK_sys_college_school foreign key (school_id)
     REFERENCES sys_school (school_id) on delete RESTRICT on update RESTRICT;

alter table sys_city add CONSTRAINT FK_sys_city_province FOREIGN key (province_id)
     REFERENCES sys_province (province_id) on delete RESTRICT on update RESTRICT;

alter table sys_login_record add CONSTRAINT FK_sys_login_record_user FOREIGN key (user_id)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table u_user add CONSTRAINT FK_u_user_rank FOREIGN key (rank_id)
    REFERENCES sys_rank (rank_id) on delete RESTRICT on update RESTRICT;

alter table u_user add CONSTRAINT FK_u_user_sign FOREIGN key (sign_id)
    REFERENCES u_sign (sign_id) on delete RESTRICT on update RESTRICT;

alter table u_sign add CONSTRAINT FK_u_sign_user FOREIGN KEY (user_id)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table d_reply2 add CONSTRAINT FK_d_reply2_user1 FOREIGN KEY (Commentator)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table i_reply2 add CONSTRAINT FK_i_reply2_user1 FOREIGN KEY (Commentator)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table s_reply2 add CONSTRAINT FK_s_reply2_user1 FOREIGN KEY (Commentator)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table v_reply2 add CONSTRAINT FK_v_reply2_user1 FOREIGN KEY (Commentator)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table u_friend add CONSTRAINT FK_u_fenrid_f_group FOREIGN KEY (group_id)
    REFERENCES u_friend_group (group_id) on delete RESTRICT on update RESTRICT;

alter table u_friend add CONSTRAINT FK_u_fenrid_u_user FOREIGN KEY (user_id)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;

alter table u_friend add CONSTRAINT FK_u_fenrid_f_user FOREIGN KEY (friend_id)
    REFERENCES u_user (user_id) on delete RESTRICT on update RESTRICT;


alter table u_user add CONSTRAINT FK_u_user_city FOREIGN key (city_id)
    REFERENCES sys_city (city_id) on delete RESTRICT on update RESTRICT;

alter table u_user add CONSTRAINT FK_u_user_province FOREIGN key (province_id)
    REFERENCES sys_province (province_id) on delete RESTRICT on update RESTRICT;

alter table u_user add CONSTRAINT FK_u_user_school  FOREIGN key (school_id) 
    REFERENCES sys_school (school_id) on delete RESTRICT on update RESTRICT;

alter table u_user add CONSTRAINT FK_u_user_college FOREIGN key (college_id)
    REFERENCES  sys_college (college_id) on delete RESTRICT on update  RESTRICT;

ALTER table u_user add CONSISTENT FK_u_user_invitation_code FOREIGN KEY (code_id)
    REFERENCES sys_invitation_code (code_id) on DELETE RESTRICT ON update RESTRICT;





