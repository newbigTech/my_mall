
ALTER TABLE `sys_admin_log`
MODIFY COLUMN `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ,
MODIFY COLUMN `create_date`  datetime NULL DEFAULT NULL COMMENT '创建日期' AFTER `id`,
MODIFY COLUMN `modify_date`  datetime NULL DEFAULT NULL COMMENT '修改日期' AFTER `create_date`,
MODIFY COLUMN `ip`  varchar(255)   NULL DEFAULT NULL COMMENT 'ip' AFTER `modify_date`,
MODIFY COLUMN `name`  varchar(255)   NULL DEFAULT NULL COMMENT '登录名' AFTER `ip`,
MODIFY COLUMN `admin_id`  int(11) NULL DEFAULT NULL COMMENT '登录人ID' AFTER `name`,
ADD COLUMN `creator`  varchar(255) NULL COMMENT '创建人' AFTER `admin_id`,
ADD COLUMN `last_modify_man`  varchar(255) NULL COMMENT '最后修改人' AFTER `creator`;


ALTER TABLE `sys_admin`
MODIFY COLUMN `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ,
MODIFY COLUMN `department`  varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门' AFTER `id`,
MODIFY COLUMN `email`  varchar(190)  NOT NULL COMMENT '邮箱' AFTER `department`,
MODIFY COLUMN `name`  varchar(190)   NULL DEFAULT NULL COMMENT '名称' AFTER `loginIp`,
MODIFY COLUMN `password`  varchar(190)   NOT NULL COMMENT '密码' AFTER `name`,
MODIFY COLUMN `username`  varchar(190)  NOT NULL COMMENT '用户名' AFTER `password`,
ADD COLUMN `is_account_enabled`  bit(1) NOT NULL COMMENT '账号是否启用' AFTER `email`,
ADD COLUMN `is_account_expired`  bit(1) NOT NULL COMMENT '账号是否过期' AFTER `isAccountEnabled`,
ADD COLUMN `is_account_locked`  bit(1) NOT NULL COMMENT '账号是否锁定' AFTER `isAccountExpired`,
ADD COLUMN `is_credentials_expired`  bit(1) NOT NULL COMMENT '凭证是否过期' AFTER `isAccountLocked`,
ADD COLUMN `locked_date`  datetime NULL COMMENT '锁定日期' AFTER `isCredentialsExpired`,
ADD COLUMN `login_date`  datetime NULL COMMENT '登录日期' AFTER `lockedDate`,
ADD COLUMN `login_failure_count`  int(11) NULL COMMENT '登录失败次数' AFTER `loginDate`,
ADD COLUMN `login_ip`  varchar(190) NULL COMMENT '登录的ip' AFTER `loginFailureCount`,
ADD COLUMN `create_date`  datetime NULL COMMENT '创建日期' AFTER `username`,
ADD COLUMN `modify_date`  datetime NULL COMMENT '修改日期' AFTER `createDate`,
ADD COLUMN `creator`  varchar(190) NULL COMMENT '创建人' AFTER `modifyDate`,
ADD COLUMN `last_modify_man`  varchar(190) NULL COMMENT '最后修改人' AFTER `creator`,
ADD COLUMN `tgt`  varchar(190) NULL COMMENT '票据，登入相关';
UPDATE sys_admin SET is_account_enabled=isAccountEnabled,is_account_expired=isAccountExpired,is_account_locked=isAccountLocked,is_credentials_expired=isCredentialsExpired,
locked_date=lockedDate,login_date=loginDate,login_ip=loginIp,login_failure_count=loginFailureCount,create_date=createDate,modify_date=modifyDate;




ALTER TABLE `sys_admin_role`
MODIFY COLUMN `admin_id`  bigint(20) NOT NULL COMMENT '登录用户id' FIRST ,
MODIFY COLUMN `role_id`  bigint(20) NOT NULL COMMENT '角色id' AFTER `admin_id`,
ADD COLUMN `modify_date`  datetime NULL COMMENT '修改日期' AFTER `modifyDate`,
ADD COLUMN `last_modify_man`  varchar(255) NULL COMMENT '最后修改人' AFTER `modify_date`,
ADD COLUMN `creator`  varchar(255) NULL COMMENT '创建人' AFTER `last_modify_man`,
ADD COLUMN `create_date`  datetime NULL COMMENT '创建日期' AFTER `creator`;
UPDATE sys_admin_role SET modify_date=modifyDate;



ALTER TABLE `sys_resource`
MODIFY COLUMN `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ,
MODIFY COLUMN `create_date`  datetime NULL DEFAULT NULL COMMENT '创建日期' AFTER `id`,
MODIFY COLUMN `modify_date`  datetime NULL DEFAULT NULL COMMENT '修改日期' AFTER `create_date`,
MODIFY COLUMN `name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称' AFTER `modify_date`,
MODIFY COLUMN `value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值' AFTER `name`,
MODIFY COLUMN `internal`  int(1) NOT NULL COMMENT '是否内置' AFTER `value`,
MODIFY COLUMN `sequence`  int(20) NOT NULL DEFAULT 0 COMMENT '排序' AFTER `internal`,
MODIFY COLUMN `type`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源类型' AFTER `sequence`,
MODIFY COLUMN `path`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '树路径' AFTER `type`,
MODIFY COLUMN `parent_id`  int(11) NULL DEFAULT NULL COMMENT '父级ID' AFTER `path`,
MODIFY COLUMN `depth`  int(11) NOT NULL COMMENT '深度' AFTER `parent_id`,
ADD COLUMN `creator`  varchar(255) NULL COMMENT '创建人' AFTER `depth`,
ADD COLUMN `last_modify_man`  varchar(255) NULL COMMENT '最后修改人' AFTER `creator`;


ALTER TABLE `sys_role`
MODIFY COLUMN `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ,
MODIFY COLUMN `description`  text   NULL COMMENT '描述' AFTER `modifyDate`,
MODIFY COLUMN `name`  varchar(190)   NOT NULL COMMENT '名称' AFTER `isSystem`,
MODIFY COLUMN `value`  varchar(190)  NOT NULL COMMENT '值' AFTER `name`,
ADD COLUMN `create_date`  datetime NULL COMMENT '创建时间' AFTER `id`,
ADD COLUMN `modify_date`  datetime NULL COMMENT '修改日期' AFTER `createDate`,
ADD COLUMN `is_system`  bit(1) NOT NULL COMMENT '是否是系统的' AFTER `description`,
ADD COLUMN `creator`  varchar(190) NULL COMMENT '创建者' AFTER `value`,
ADD COLUMN `last_modify_man`  varchar(190) NULL COMMENT '最后修改者' AFTER `creator`;
UPDATE sys_role SET create_date=createDate,modify_date=modifyDate,is_system=isSystem;



ALTER TABLE `sys_role_resource`
MODIFY COLUMN `role_id`  bigint(20) NOT NULL COMMENT '角色id' FIRST ,
MODIFY COLUMN `resource_id`  bigint(20) NOT NULL COMMENT '资源id' AFTER `role_id`,
ADD COLUMN `modify_date`  datetime NULL COMMENT '修改日期' AFTER `resource_id`,
ADD COLUMN `last_modify_man`  varchar(255) NULL COMMENT '最后修改人' AFTER `modifyDate`,
ADD COLUMN `create_date`  datetime NULL COMMENT '创建日期' AFTER `last_modify_man`,
ADD COLUMN `creator`  varchar(255) NULL COMMENT '创建人' AFTER `create_date`;
UPDATE sys_role_resource SET modify_date=modifyDate;
