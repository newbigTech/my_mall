ALTER TABLE `sys_admin`
DROP COLUMN `isAccountEnabled`,
DROP COLUMN `isAccountExpired`,
DROP COLUMN `isAccountLocked`,
DROP COLUMN `isCredentialsExpired`,
DROP COLUMN `lockedDate`,
DROP COLUMN `loginDate`,
DROP COLUMN `loginFailureCount`,
DROP COLUMN `loginIp`,
DROP COLUMN `createDate`,
DROP COLUMN `modifyDate`;


ALTER TABLE `sys_admin_role`
DROP COLUMN `modifyDate`;



ALTER TABLE `sys_role`
DROP COLUMN `createDate`,
DROP COLUMN `modifyDate`,
DROP COLUMN `isSystem`;



ALTER TABLE `sys_role_resource`
DROP COLUMN `modifyDate`;