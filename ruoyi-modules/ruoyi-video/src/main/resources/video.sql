-- ----------------------------
-- 视频表结构
-- ----------------------------
drop table if exists video;
create table video
(
    video_id     bigint       not null                   comment '视频ID',
    title        varchar(100) not null                   comment '视频标题',
    description  varchar(500) default null               comment '视频描述',
    cover_url    varchar(255) default null               comment '封面URL',
    video_url    varchar(255) default null               comment '原始视频URL',
    m3u8_url     varchar(255) default null               comment 'm3u8播放地址',
    duration     int          default 0                  comment '视频时长(秒)',
    size         bigint       default 0                  comment '视频大小(字节)',
    status       char(1)      default '0'                comment '状态（0正常 1停用）',
    del_flag     char(1)      default '0'                comment '删除标志（0代表存在 2代表删除）',
    create_by    bigint       default null               comment '创建者',
    create_time  datetime                                comment '创建时间',
    update_by    bigint       default null               comment '更新者',
    update_time  datetime     default null               comment '更新时间',
    remark       varchar(500) default null               comment '备注',
    primary key (video_id)
) engine=innodb comment = '视频表';

-- ----------------------------
-- 初始化-菜单和权限
-- ----------------------------
-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) values 
(1900, '视频管理', 3, 10, 'video', 'video/index', null, 1, 0, 'C', '0', '0', 'video:list', 'video', 1, sysdate(), null, null, '视频菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) values 
(1901, '视频查询', @parentId, 1, '#', '', null, 1, 0, 'F', '0', '0', 'video:query', '#', 1, sysdate(), null, null, ''),
(1902, '视频新增', @parentId, 2, '#', '', null, 1, 0, 'F', '0', '0', 'video:add', '#', 1, sysdate(), null, null, ''),
(1903, '视频修改', @parentId, 3, '#', '', null, 1, 0, 'F', '0', '0', 'video:edit', '#', 1, sysdate(), null, null, ''),
(1904, '视频删除', @parentId, 4, '#', '', null, 1, 0, 'F', '0', '0', 'video:remove', '#', 1, sysdate(), null, null, ''),
(1905, '视频导出', @parentId, 5, '#', '', null, 1, 0, 'F', '0', '0', 'video:export', '#', 1, sysdate(), null, null, ''),
(1906, '视频上传', @parentId, 6, '#', '', null, 1, 0, 'F', '0', '0', 'video:upload', '#', 1, sysdate(), null, null, ''),
(1907, '视频播放', @parentId, 7, '#', '', null, 1, 0, 'F', '0', '0', 'video:play', '#', 1, sysdate(), null, null, ''); 