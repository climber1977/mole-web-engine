/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50612
Source Host           : 127.0.0.1:3306
Source Database       : web_engine

Target Server Type    : MYSQL
Target Server Version : 50612
File Encoding         : 65001

Date: 2017-12-05 16:32:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wb_authorized_server
-- ----------------------------
DROP TABLE IF EXISTS `wb_authorized_server`;
CREATE TABLE `wb_authorized_server` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(64) NOT NULL DEFAULT '',
  `secret_key` varchar(512) NOT NULL DEFAULT '',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_authorized_server
-- ----------------------------
INSERT INTO `wb_authorized_server` VALUES ('1', '127.0.0.1', '123456', '0');
INSERT INTO `wb_authorized_server` VALUES ('2', '0:0:0:0:0:0:0:1', '123456', '0');
INSERT INTO `wb_authorized_server` VALUES ('3', '0:0:0:0:0:0:0:1', '', '0');
INSERT INTO `wb_authorized_server` VALUES ('4', '127.0.0.1', '', '0');
INSERT INTO `wb_authorized_server` VALUES ('5', '10.12.6.17', '123456', '0');

-- ----------------------------
-- Table structure for wb_button
-- ----------------------------
DROP TABLE IF EXISTS `wb_button`;
CREATE TABLE `wb_button` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `caption` varchar(128) NOT NULL,
  `url` varchar(256) NOT NULL,
  `hide` tinyint(1) NOT NULL,
  `template` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_button
-- ----------------------------
INSERT INTO `wb_button` VALUES ('1', 'test_btn_query', 'query', 'query', '', '0', '');
INSERT INTO `wb_button` VALUES ('2', 'test_btn_delete', 'delete', 'delete', '', '0', '');

-- ----------------------------
-- Table structure for wb_button_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_button_role_relation`;
CREATE TABLE `wb_button_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buttonid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_button_role_relation
-- ----------------------------
INSERT INTO `wb_button_role_relation` VALUES ('1', '1', '1');
INSERT INTO `wb_button_role_relation` VALUES ('2', '2', '2');
INSERT INTO `wb_button_role_relation` VALUES ('3', '1', '2');

-- ----------------------------
-- Table structure for wb_column_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_column_role_relation`;
CREATE TABLE `wb_column_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(64) NOT NULL,
  `field_name` varchar(64) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_column_role_relation
-- ----------------------------
INSERT INTO `wb_column_role_relation` VALUES ('1', 'wb_user', 'en_name', '2');
INSERT INTO `wb_column_role_relation` VALUES ('2', 'wb_user', 'item_state', '2');

-- ----------------------------
-- Table structure for wb_compent_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_compent_role_relation`;
CREATE TABLE `wb_compent_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `compentid` varchar(64) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_compent_role_relation
-- ----------------------------
INSERT INTO `wb_compent_role_relation` VALUES ('1', 'securityid', '1');
INSERT INTO `wb_compent_role_relation` VALUES ('2', 'securityAccount', '2');
INSERT INTO `wb_compent_role_relation` VALUES ('3', 'securityid', '3');
INSERT INTO `wb_compent_role_relation` VALUES ('4', 'securityQuery', '2');
INSERT INTO `wb_compent_role_relation` VALUES ('5', 'securityAdd', '2');
INSERT INTO `wb_compent_role_relation` VALUES ('6', 'securityQuery', '1');

-- ----------------------------
-- Table structure for wb_execute_sql
-- ----------------------------
DROP TABLE IF EXISTS `wb_execute_sql`;
CREATE TABLE `wb_execute_sql` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `request_type` int(11) NOT NULL,
  `expression` varchar(4096) NOT NULL,
  `dbs` varchar(64) DEFAULT NULL,
  `where_group` bigint(11) NOT NULL DEFAULT '-1',
  `keep_original` tinyint(4) DEFAULT NULL,
  `cache_second` int(11) DEFAULT '0',
  `refresh_time` time DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_execute_sql
-- ----------------------------
INSERT INTO `wb_execute_sql` VALUES ('1', 'unit_test_query_where_and', '0', 'select * from wb_user', 'hr', '1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('2', 'unit_test_query_where_or', '0', 'select id, work_code, name, ch_name from wb_user', 'hr', '2', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('3', 'unit_test_query_where_and2', '0', 'select id, work_code, Name from wb_user', 'hr', '3', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('4', 'unit_test_update', '1', 'update wb_user set name=\'{name}\',en_name=\'{en_name}\'', 'hr', '4', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('5', 'unit_test_update2', '1', 'update wb_user set name=\'{name}\',en_name=\'{en_name}\'', 'hr', '5', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('6', 'unit_test_query_page', '0', 'select * from wb_user', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('7', 'update_item_in_web', '0', 'select * from wb_user', 'hr', '7', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('8', 'no_user_96700059831238656', '0', 'select * from wb_user', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('9', 'ajax_datatable_get_user', '0', 'SELECT\r\n	concat(\r\n		concat(\r\n			\'<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"id[]\" type=\"checkbox\" class=\"checkboxes\" value=\"\',\r\n			CAST(id AS CHAR)\r\n		),\r\n		\'\"><span></span></label>\'\r\n	) AS chk,\r\n	id,\r\n	concat(\r\n		concat(\'<h1>\', work_code),\r\n		\'</h1>\'\r\n	) AS work_code,\r\n	NAME,\r\n	ch_name,\r\n	en_name,\r\n	item_state\r\nFROM\r\n	wb_user', 'hr', '-1', null, '500', null, null);
INSERT INTO `wb_execute_sql` VALUES ('10', 'myweb_login', '0', 'select * from wb_user', 'hr', '8', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('11', 'json_datatable_get_user', '0', 'SELECT\r\n	concat(\r\n		concat(\r\n			\'<label class=\"mt-checkbox mt-checkbox-single mt-checkbox-outline\"><input name=\"id[]\" type=\"checkbox\" class=\"checkboxes\" value=\"\',\r\n			CAST(id AS CHAR)\r\n		),\r\n		\'\"><span></span></label>\'\r\n	) AS chk,\r\n	id,\r\n	concat(\r\n		concat(\'<h1>\', work_code),\r\n		\'</h1>\'\r\n	) AS work_code,\r\n	NAME,\r\n	ch_name,\r\n	en_name,\r\n	item_state\r\nFROM\r\n	wb_user', 'hr', '14', null, '0', '17:35:00', null);
INSERT INTO `wb_execute_sql` VALUES ('12', 'json_tree_get_menu', '0', 'SELECT t.id,  \'fa fa-folder icon-lg icon-state-info\' AS icon, t.caption AS TEXT,  \'root\' AS \nTYPE , (CASE WHEN (\nSELECT COUNT( t2.id ) AS haschild\nFROM wb_menu t2 WHERE t2.parentid = t.id) >0\nTHEN 1 ELSE 0 END\n) AS children\nFROM wb_menu t', 'hr', '10', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('13', 'query_item_by_param', '0', 'select * from wb_user', 'hr', '11', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('14', 'update_item_in_db', '1', 'UPDATE wb_user\r\nSET work_code = \'{work_code}\',\r\n name = \'{name}\',\r\n ch_name = \'{ch_name}\',\r\n en_name = \'{en_name}\',\r\n update_date_time = \'{curDataTime}\'\r\nWHERE\r\n	id ={id}', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('15', 'delete_item_in_db', '1', 'delete from wb_user where id={id}', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('16', 'add_item_in_db', '1', 'INSERT INTO wb_user (account, work_code, name, ch_name, en_Name, item_state) VALUES\r\n(\'{account}\', \'{work_code}\', \'{name}\', \'{ch_name}\', \'{en_Name}\', 1)', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('17', 'ajax_tree_get_department', '0', 'select id,\r\n       \'fa fa-folder icon-lg icon-state-info\' as icon,\r\n       t.short_name as text,\r\n       \'root\' as type,\r\n       (select if(count(t2.id)>=1, 1, 0) as haschild\r\n                     from depart t2\r\n                    where t2.parentid = t.id) as children from depart t\r\n', 'wt', '13', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('18', 'curve_chart_climate', '0', 'select month, city, temperature from climate', 'wt', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('19', 'pie_chart_climate', '0', 'select month, temperature from climate', 'wt', '15', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('20', 'custom_query_1_1', '0', 'select id, name from wb_user', 'hr', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('21', 'custom_query_1_2', '0', 'select id, en_name from wb_user', 'hr', '16', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('22', 'no_subaccount_agent', '0', 'select t2.AGENTERName, t2.AGENTERemail, t2.BEAGENTERName, t2.BEAGENTERemail\r\n  from (select t1.AGENTERID,\r\n               h1.lastname    as AGENTERName,\r\n               h1.status      as AGENTERstatus,\r\n               h1.email       as AGENTERemail,\r\n               t1.BEAGENTERID,\r\n               h2.lastname    as BEAGENTERName,\r\n               h2.status      as BEAGENTERStatus,\r\n               h2.email       as BEAGENTERemail\r\n          from (select AGENTERID, BEAGENTERID\r\n                  from workflow_agent t\r\n                 where t.agenttype = 1\r\n                   and ((to_date(t.begindate, \'yyyy-mm-dd\') >= sysdate and\r\n                       to_date(t.enddate, \'yyyy-mm-dd\') <= sysdate) or\r\n                       t.enddate is null)\r\n                 group by AGENTERID, BEAGENTERID) t1\r\n          left join hrmresource h1\r\n            on t1.AGENTERID = h1.id\r\n          left join hrmresource h2\r\n            on t1.BEAGENTERID = h2.id) t2\r\n where t2.AGENTERstatus <= 4\r\n   and t2.BEAGENTERStatus <= 4\r\n   and t2.AGENTERemail != t2.BEAGENTERemail', 'oa', '18', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('23', 'dege_no_approval', '0', 'select \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       ttt.requestid || \'\" target=\"_blank\">\' || requestid || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       ttt.requestid || \'\" target=\"_blank\">\' || requestmark || \'</a>\',\r\n        \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       ttt.requestid || \'\" target=\"_blank\">\' || requestname || \'</a>\',\r\n       ttt.createrName,\r\n       ttt.createdate,\r\n       h3.lastname as lastOperatorName,\r\n       ttt.lastoperatedate\r\n  from (select tt.*, h2.lastname as createrName\r\n          from (select wb.requestid,\r\n                       wb.requestmark,\r\n                       wb.requestname,\r\n                       h.lastname as approvalName,\r\n                       wb.createdate,\r\n                       wb.createtime,\r\n                       wb.creater,\r\n                       wb.lastoperatedate,\r\n                       wb.lastoperatetime,\r\n                       wb.lastoperator\r\n                  from workflow_currentoperator w4\r\n                  left join workflow_requestbase wb\r\n                    on w4.requestid = wb.requestid\r\n                   and wb.STATUS != \'归档\'\r\n                  left join HrmResource h\r\n                    on h.id = w4.userid\r\n                 where w4.IsRemark != \'2\'\r\n                   and w4.IsRemark != \'4\'\r\n                   and w4.requestid in\r\n                       (select w2.requestid\r\n                          from workflow_currentoperator w2,\r\n                               (select w.requestid, w.nodeid\r\n                                  from workflow_currentoperator w\r\n                                 where w.IsRemark != \'1\'\r\n                                   and w.IsRemark != \'2\'\r\n                                   and w.IsRemark != \'4\'\r\n                                   and w.userid in\r\n                                       (select id\r\n                                          from HrmResource t\r\n                                         where t.email =\r\n                                               \'chendewen@mole.com\'\r\n                                           and t.status <= 4)) w3\r\n                         where w2.requestid = w3.requestid\r\n                           and w2.nodeid = w3.nodeid\r\n                           and w2.IsRemark != \'2\'\r\n                           and w2.IsRemark != \'4\'\r\n                         group by w2.requestid, w2.nodeid\r\n                        HAVING count(w2.requestid) = 1)\r\n                   and w4.userid in (select id\r\n                                       from HrmResource t\r\n                                      where t.email = \'chendewen@mole.com\'\r\n                                        and t.status <= 4)) tt,\r\n               HrmResource h2\r\n         where tt.creater = h2.id) ttt,\r\n       HrmResource h3\r\n where ttt.lastoperator = h3.id\r\n', 'oa', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('24', 'agent_avalid', '0', 'select t2.AGENTERName, t2.AGENTERemail, t2.BEAGENTERName, t2.BEAGENTERemail\r\n  from (select t1.AGENTERID,\r\n               h1.lastname    as AGENTERName,\r\n               h1.status      as AGENTERstatus,\r\n               h1.email       as AGENTERemail,\r\n               t1.BEAGENTERID,\r\n               h2.lastname    as BEAGENTERName,\r\n               h2.status      as BEAGENTERStatus,\r\n               h2.email       as BEAGENTERemail\r\n          from (select AGENTERID, BEAGENTERID\r\n                  from workflow_agent t\r\n                 where t.agenttype = 1\r\n                   and ((to_date(t.begindate, \'yyyy-mm-dd\') >= sysdate and\r\n                       to_date(t.enddate, \'yyyy-mm-dd\') <= sysdate) or\r\n                       t.enddate is null)\r\n                 group by AGENTERID, BEAGENTERID) t1\r\n          left join hrmresource h1\r\n            on t1.AGENTERID = h1.id\r\n          left join hrmresource h2\r\n            on t1.BEAGENTERID = h2.id) t2\r\n where t2.AGENTERstatus <= 4\r\n   and t2.BEAGENTERStatus <= 4\r\n', 'oa', '18', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('25', 'leave_workflow', '0', 'select \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       a.requestid || \'\" target=\"_blank\">\' || a.requestid || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       a.requestid || \'\" target=\"_blank\">\' || a.requestmark || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       a.requestid || \'\" target=\"_blank\">\' || a.requestname || \'</a>\',\r\n       h1.lastname   as createname,\r\n       a.createdate|| \' \' ||a.createtime,\r\n       a.status,\r\n       b.code        as leavercode,\r\n       h2.lastname   as leavername\r\n  from workflow_requestbase a\r\n  left join hrmresource h1\r\n    on a.creater = h1.id, formtable_main_117 b\r\n  left join hrmresource h2\r\n    on b.name = h2.id\r\n where a.requestid = b.requestid\r\n   and a.workflowid = 3980', 'oa', '19', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('26', 'likun_approvaled_wk', '0', 'select \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || t.requestid || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || r.requestmark || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || r.requestname || \'</a>\',\r\n       r.createdate || \' \' || r.createtime as createtime,\r\n       t.operatedate || \' \' || t.operatetime as operatetime\r\n  from workflow_requestlog t\r\n  left join workflow_requestbase r\r\n    on t.requestid = r.requestid\r\n where t.operator = 158913', 'oa', '20', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('27', 'likun_no_approval_wk', '0', 'select \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || t.requestid || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || r.requestmark || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       t.requestid || \'\" target=\"_blank\">\' || r.requestname || \'</a>\',\r\n       t.receivedate || \' \' || t.receivetime as receivetime\r\n  from workflow_currentoperator t\r\n  left join workflow_requestbase r\r\n    on t.requestid = r.requestid\r\n where t.userid = 158913\r\n   and t.requestid >= 106114\r\n   and t.isremark = 0\r\n order by t.requestid desc\r\n', 'oa', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('28', 'erp_query_emp', '0', 'SELECT\r\n	t.workerinfo_id,\r\n	t.workerinfo_No,\r\n	t.workerinfo_name,\r\n	w.worker_no,\r\n	t.out_email,\r\n	t.workerinfo_add_date,\r\n	t.workerinfo_status,\r\n	t.attendance_type\r\nFROM\r\n	apollo_erp_workerinfo t,\r\n	apollo_erp_worker w\r\nWHERE\r\n	t.workerinfo_no = w.workerinfo_no', 'erp', '22', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('29', 'hp_query_emp', '0', 'select t.emp_id,\r\n       t.emp_code,\r\n       t.emp_name,\r\n       t.emp_no,\r\n       t.work_email,\r\n       t.mobile_no,\r\n       p.post_name,\r\n       to_char(t.entry_date, \'yyyy-mm-dd\') as entry_date,\r\n       to_char(t.birthday, \'yyyy-mm-dd\') as birthday,\r\n       d.dept_short_name\r\n  from hr_person t, hr_person_post pp, hr_post p, hr_depart d\r\n where t.emp_code = pp.emp_code\r\n   and p.post_code = pp.post_code\r\n   and p.dept_code = d.dept_code\r\n   and d.dept_code in (\'2312\', \'2313\', \'2314\')\r\n   and t.work_status = 2\r\n   and pp.major_post_flag=1', 'hp', '23', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('30', 'oa_query_request', '0', 'select \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       r.requestid || \'\" target=\"_blank\">\' || r.requestid || \'</a>\',\r\n       \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       r.requestid || \'\" target=\"_blank\">\' || r.requestmark || \'</a>\',\r\n        \'<a href=\"http://workflow.mole.com/login/Login.jsp?gopage=/workflow/request/ViewRequest.jsp?requestid=\' ||\r\n       r.requestid || \'\" target=\"_blank\">\' || r.requestname || \'</a>\',\r\n       h1.lastname as createname,\r\n       r.createdate || \' \' || r.createtime as createtime,\r\n       h2.lastname as lastoperatorname,\r\n       r.lastoperatedate || \' \' || r.lastoperatetime as lastoperatetime,\r\n       n1.nodename as lastnodename,\r\n       n2.nodename as currentnodename,\r\n       r.status\r\n  from workflow_requestbase r\r\n  left join hrmresource h1\r\n    on r.creater = h1.id\r\n  left join hrmresource h2\r\n    on r.lastoperator = h2.id\r\n  left join workflow_nodebase n1\r\n    on r.lastnodeid = n1.id\r\n  left join workflow_nodebase n2\r\n    on r.currentnodeid = n2.id', 'oa', '24', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('31', 'oa_department_query', '0', 'select t.id, t.departmentcode, t.dept_full_name, h.lastname\r\n  from HrmDepartment t\r\n  left join hrmresource h\r\n    on h.id = t.bmfzr\r\n where (t.canceled = 0 or t.canceled is null)\r\n   and t.subcompanyid1 = 202\r\n   and t.dept_full_name not like \'%/17173%\'\r\n   and t.dept_full_name not like \'%/瑞科%\'', 'oa', '26', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('32', 'erp_dege_no_approval', '0', 'SELECT\r\n	a.bill_code billCode,\r\n	a.flow_subject flowSubject,\r\n	a.starter_name starterName,\r\n	a.flow_name flowName,\r\n	a.flow_start_time flowStartTime,\r\n	b.CREATE_TIME_ taskCreateTime\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND c.USER_ID_ = 266', 'erp', '28', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('33', 'erp_likun_no_approval', '0', 'SELECT\r\n	a.bill_code billCode,\r\n	a.flow_subject flowSubject,\r\n	a.starter_name starterName,\r\n	a.flow_name flowName,\r\n	a.flow_start_time flowStartTime,\r\n	b.CREATE_TIME_ taskCreateTime\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND c.USER_ID_ = 1068', 'erp', '28', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('34', 'hp_query_cy_emp', '0', 'select t.emp_id,\r\n       t.emp_code,\r\n       t.emp_name,\r\n       t.emp_no,\r\n       t.work_email,\r\n       t.mobile_no,\r\n       p.post_name,\r\n       to_char(t.entry_date, \'yyyy-mm-dd\') as entry_date,\r\n       to_char(t.birthday, \'yyyy-mm-dd\') as birthday,\r\n       d.dept_full_name\r\n  from hr_person t, hr_person_post pp, hr_post p, hr_depart d\r\n where t.emp_code = pp.emp_code\r\n   and p.post_code = pp.post_code\r\n   and p.dept_code = d.dept_code\r\n   and t.work_status = 2\r\n   and pp.major_post_flag=1', 'hp', '23', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('35', 'hp_all_emp', '0', 'select t.emp_id,\r\n       t.emp_code,\r\n       t.emp_name,\r\n       t.emp_no,\r\n       t.work_email,\r\n       t.mobile_no,\r\n       p.post_name,\r\n       to_char(t.entry_date, \'yyyy-mm-dd\') as entry_date,\r\n       to_char(t.birthday, \'yyyy-mm-dd\') as birthday,\r\n       d.dept_full_name\r\n  from hr_person t, hr_person_post pp, hr_post p, hr_depart d\r\n where t.emp_code = pp.emp_code\r\n   and p.post_code = pp.post_code\r\n   and p.dept_code = d.dept_code', 'hp', '23', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('36', 'hp_depart', '0', 'select d.dept_id, d.dept_code, d.dept_full_name, p.emp_name\r\n  from hr_depart d\r\n  left join hr_person p\r\n    on p.emp_code = d.dept_lead_code\r\n', 'hp', '29', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('37', 'erp_qyxxh_attendance_statistics', '0', 'SELECT\r\n	t.worker_no_new,\r\n	t.worker_name,\r\n	t.attendance_year,\r\n	t.attendance_quarter,\r\n	t.attendance_month,\r\n	t.worktime_total,\r\n	t.need_work_day,\r\n	t.work_day,\r\n	t.worktime_per_workday,\r\n	t.overtime_total\r\nFROM\r\n	at_attendance_statistic_worktime t,\r\n	apollo_worker_post w,\r\n	apollo_post_depart p,\r\n	apollo_erp_depart d\r\nWHERE\r\n	t.worker_num = w.workerinfo_No\r\nAND p.post_no = w.post_no\r\nAND d.depart_No = p.depart_no\r\nAND w.worker_post_delete = \'no\'\r\nAND w.is_main = \'Y\'\r\nAND d.depart_index_num LIKE \'1/2464/24/2312%\'', 'erp', '31', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('38', 'erp_qyxxh_attendance_column', '0', 'SELECT\r\n	t.worker_name,\r\n	t.attendance_month,\r\n	t.worktime_total\r\nFROM\r\n	at_attendance_statistic_worktime t,\r\n	apollo_worker_post p,\r\n	apollo_post_depart dp,\r\n	apollo_erp_depart d\r\nWHERE\r\n	t.worker_num = p.workerinfo_No\r\nAND dp.post_no = p.post_no\r\nAND d.depart_No = dp.depart_no\r\nAND p.worker_post_delete = \'no\'\r\nAND p.is_main = \'Y\'\r\nAND d.depart_index_num LIKE \'1/2464/24/2312%\'', 'erp', '32', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('39', 'erp_qyxxh_attendance_quanqin', '0', 'SELECT\r\n	t.id,\r\n	t.worker_name,\r\n	t.worker_no_new,\r\n	t.attendance_year,\r\n	t.attendance_quarter,\r\n	t.attendance_month,\r\n	t.work_day,\r\n	t.need_work_day\r\nFROM\r\n	at_attendance_statistic_worktime t,\r\n	apollo_worker_post w,\r\n	apollo_post_depart p,\r\n	apollo_erp_depart d\r\nWHERE\r\n	t.worker_num = w.workerinfo_No\r\nAND p.post_no = w.post_no\r\nAND d.depart_No = p.depart_no\r\nAND t.work_day = t.need_work_day\r\nAND w.worker_post_delete = \'no\'\r\nAND w.is_main = \'Y\'\r\nAND d.depart_index_num LIKE \'1/2464/24/2312%\'', 'erp', '33', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('40', 'erp_employee_attendance', '0', 'SELECT\r\n	w.workerinfo_name,\r\n	date_format(\r\n		t.attendance_date,\r\n		\'%Y-%m-%d\'\r\n	)attendance_date,\r\n	t.attendance_week,\r\n	t.on_work_time,\r\n	t.off_work_time,\r\n	t.all_work_time,\r\n	t.attendance_state_current,\r\n	t.remark,\r\n	t.attendance_count\r\nFROM\r\n	at_attendance_days_result t,\r\n	apollo_erp_workerinfo w,\r\n	apollo_erp_worker w2\r\nWHERE\r\n	t.worker_num = w.workerinfo_no\r\nAND w.workerinfo_no = w2.workerinfo_no ', 'erp', '30', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('41', 'qyxxh_unfinished_task', '0', 'SELECT\r\n	tt.TASK_CODE,\r\n	\'<a href=\"http://task.mole.com:8080/task/manager/ppTaskNeedDoAction/viewPPTaskDetail.do?taskid=\' || tt.tid || \'&empcode=\' || task_acceptid || \'\" target= _blank>\' || task_name || \'</a>\' task_name,\r\n	tt.project_name,\r\n	tt.task_appler,\r\n	tt.task_accept,\r\n	tt.startdate,\r\n	tt.enddate\r\nFROM\r\n	(\r\n		SELECT\r\n			T .tid,\r\n			T .TASK_CODE,\r\n			T .task_name,\r\n			DECODE (\r\n				T .modular_code,\r\n				\'0\',\r\n				\'日常任务\',\r\n				T .label_name\r\n			) project_name,\r\n			T .task_appler,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T .task_receive\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_accept\r\n					ELSE\r\n						T .task_appler\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A .username)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_accept,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T .TASK_RECEIVEID\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_acceptid\r\n					ELSE\r\n						T .task_applerid\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A .userid)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_acceptid,\r\n			T .startdate,\r\n			T .enddate,\r\n			T .currentflow\r\n		FROM\r\n			TASK_BASEINFO T\r\n		WHERE\r\n			T .dept_full_code LIKE \'%1/2464/24/2312%\'\r\n		AND (\r\n			T .currentflow IN (\'0\', \'1\', \'2\', \'10\', \'01\')\r\n			OR LENGTH (T .currentflow) > 3\r\n		)\r\n		AND (\r\n			T .deleteflag IS NULL\r\n			OR T .deleteflag = 0\r\n		)\r\n	) tt', 'task', '34', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('42', 'first_depart_unfinished_task', '0', 'SELECT\r\n	tt.TASK_CODE,\r\n	\'<a href=\"http://task.mole.com:8080/task/manager/ppTaskNeedDoAction/viewPPTaskDetail.do?taskid=\' || tt.tid || \'&empcode=\' || task_acceptid || \'\" target= _blank>\' || task_name || \'</a>\' task_name,\r\n	tt.project_name,\r\n	tt.task_appler,\r\n	tt.task_accept,\r\n	tt.startdate,\r\n	tt.enddate\r\nFROM\r\n	(\r\n		SELECT\r\n			T .tid,\r\n			T .TASK_CODE,\r\n			T .task_name,\r\n			DECODE (\r\n				T .modular_code,\r\n				\'0\',\r\n				\'日常任务\',\r\n				T .label_name\r\n			) project_name,\r\n			T .task_appler,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T .task_receive\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_accept\r\n					ELSE\r\n						T .task_appler\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A .username)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_accept,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T .TASK_RECEIVEID\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_acceptid\r\n					ELSE\r\n						T .task_applerid\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A .userid)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_acceptid,\r\n			T .startdate,\r\n			T .enddate,\r\n			T .currentflow\r\n		FROM\r\n			TASK_BASEINFO T\r\n		WHERE\r\n			T .dept_full_code LIKE \'%1/2464/24/2312/2314%\'\r\n		AND (\r\n			T .currentflow IN (\'0\', \'1\', \'2\', \'10\', \'01\')\r\n			OR LENGTH (T .currentflow) > 3\r\n		)\r\n		AND (\r\n			T .deleteflag IS NULL\r\n			OR T .deleteflag = 0\r\n		)\r\n	) tt', 'task', '34', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('43', 'second_depart_unfinished_task', '0', 'select tt.TASK_CODE,\r\n      \'<a href=\"http://task.mole.com:8080/task/manager/ppTaskNeedDoAction/viewPPTaskDetail.do?taskid=\' || tt.tid || \'&empcode=\' || task_acceptid || \'\" target= _blank>\' || task_name || \'</a>\' task_name,\r\n       tt.project_name,\r\n       tt.task_appler,\r\n       tt.task_accept,\r\n       tt.startdate,\r\n       tt.enddate\r\n  from (SELECT T.tid,\r\n               t.TASK_CODE,\r\n               t.task_name,\r\n               decode(t.modular_code, \'0\', \'日常任务\', t.label_name) project_name,\r\n               t.task_appler,\r\n               decode(t.task_processid,\r\n                      \'0\',\r\n                      (case\r\n                        when t.currentflow = \'1\' then\r\n                         t.task_receive\r\n                        when t.currentflow = \'2\' then\r\n                         t.task_accept\r\n                        else\r\n                         t.task_appler\r\n                      end),\r\n                      decode(t.currentflow,\r\n                             \'01\',\r\n                             t.task_appler,\r\n                             (select wm_concat(a.username)\r\n                                from task_assignment a\r\n                               where a.taskid = t.tid))) task_accept,\r\n               DECODE(T.task_processid,\r\n                      \'0\',\r\n                      (CASE\r\n                        WHEN T.currentflow = \'1\' THEN\r\n                         T.TASK_RECEIVEID\r\n                        WHEN T.currentflow = \'2\' THEN\r\n                         T.task_acceptid\r\n                        ELSE\r\n                         T.task_applerid\r\n                      END),\r\n                      DECODE(T.currentflow,\r\n                             \'01\',\r\n                             T.task_appler,\r\n                             (SELECT wm_concat(A.userid)\r\n                                FROM task_assignment A\r\n                               WHERE A.taskid = T.tid))) task_acceptid,\r\n               t.startdate,\r\n               t.enddate,\r\n               t.currentflow\r\n          FROM TASK_BASEINFO t\r\n         where t.dept_full_code like \'%1/2464/24/2312/2313%\'\r\n           and (t.currentflow in (\'0\', \'1\', \'2\', \'10\', \'01\') or\r\n               length(t.currentflow) > 3)\r\n           and (t.deleteflag is null or t.deleteflag = 0)) tt\r\n', 'task', '34', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('44', 'qyxxh_overtime_task', '0', 'SELECT\r\n	tt.TASK_CODE,\r\n	\'<a href=\"http://task.mole.com:8080/task/manager/ppTaskNeedDoAction/viewPPTaskDetail.do?taskid=\' || tt.tid || \'&empcode=\' || task_acceptid || \'\" target= _blank>\' || task_name || \'</a>\' task_name,\r\n	tt.project_name,\r\n	tt.task_appler,\r\n	tt.task_accept,\r\n	tt.startdate,\r\n	tt.enddate\r\nFROM\r\n	(\r\n		SELECT\r\n			T .tid, T .TASK_CODE,\r\n			T .task_name,\r\n			DECODE (\r\n				T .modular_code,\r\n				\'0\',\r\n				\'日常任务\',\r\n				T .label_name\r\n			) project_name,\r\n			T .task_appler,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T .task_receive\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_accept\r\n					ELSE\r\n						T .task_appler\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A .username)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_accept,\r\n			DECODE (\r\n				T .task_processid,\r\n				\'0\',\r\n				(\r\n					CASE\r\n					WHEN T .currentflow = \'1\' THEN\r\n						T.TASK_RECEIVEID\r\n					WHEN T .currentflow = \'2\' THEN\r\n						T .task_acceptid\r\n					ELSE\r\n						T .task_applerid\r\n					END\r\n				),\r\n				DECODE (\r\n					T .currentflow,\r\n					\'01\',\r\n					T .task_appler,\r\n					(\r\n						SELECT\r\n							wm_concat (A.userid)\r\n						FROM\r\n							task_assignment A\r\n						WHERE\r\n							A .taskid = T .tid\r\n					)\r\n				)\r\n			) task_acceptid,\r\n			T .startdate,\r\n			T .enddate\r\n		FROM\r\n			TASK_BASEINFO T\r\n		WHERE\r\n			T .dept_full_code LIKE \'%1/2464/24/2312%\'\r\n		AND (\r\n			T .currentflow IN (\'0\', \'1\', \'2\', \'10\', \'01\')\r\n			OR LENGTH (T .currentflow) > 3\r\n		)\r\n		AND (\r\n			T .deleteflag IS NULL\r\n			OR T .deleteflag = 0\r\n		)\r\n		AND TO_DATE (TRUNC(SYSDATE)) - TO_DATE (T .enddate, \'yyyy-MM-dd\') >= 10\r\n	) tt', 'task', '34', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('45', 'task_qyxxh_curve_chart', '0', 'SELECT\r\n	TO_CHAR (\r\n		T .iw_week_date,\r\n		\'yyyy-mm-dd\'\r\n	) iw_week_date,\r\n	(\r\n		CASE\r\n		WHEN T .OPERATE_TYPE = 0 THEN\r\n			\'创建\'\r\n		WHEN T .OPERATE_TYPE = \'2\' THEN\r\n			\'归档\'\r\n		ELSE\r\n			\'进行中\'\r\n		END\r\n	) OPERATE_TYPE,\r\n	SUM (T .TASK_COUNT) AS cnt\r\nFROM\r\n	TASK_REPORT_WEEK2 T\r\nWHERE\r\n	(\r\n		T .dept_full_code = \'1/2464/24/2312\'\r\n		OR T .dept_full_code LIKE \'1/2464/24/2312/%\'\r\n	)\r\nAND T .iw_week_date >= TRUNC (\r\n	ADD_MONTHS (SYSDATE, - 6),\r\n	\'iw\'\r\n)\r\nAND T .iw_week_date <= SYSDATE\r\nGROUP BY\r\n	T .iw_week_date,\r\n	T .OPERATE_TYPE\r\nORDER BY\r\n	T .OPERATE_TYPE,\r\n	T .iw_week_date', 'task', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('46', 'task_first_dept_curve_chart', '0', 'SELECT\r\n	TO_CHAR (\r\n		T .iw_week_date,\r\n		\'yyyy-mm-dd\'\r\n	) iw_week_date,\r\n	(\r\n		CASE\r\n		WHEN T .OPERATE_TYPE = 0 THEN\r\n			\'创建\'\r\n		WHEN T .OPERATE_TYPE = \'2\' THEN\r\n			\'归档\'\r\n		ELSE\r\n			\'进行中\'\r\n		END\r\n	) OPERATE_TYPE,\r\n	SUM (T .TASK_COUNT) AS cnt\r\nFROM\r\n	TASK_REPORT_WEEK2 T\r\nWHERE\r\n	T .dept_full_code = \'1/2464/24/2312/2314\'\r\nAND T .iw_week_date >= TRUNC (\r\n	ADD_MONTHS (SYSDATE, - 6),\r\n	\'iw\'\r\n)\r\nAND T .iw_week_date <= SYSDATE\r\nGROUP BY\r\n	T .iw_week_date,\r\n	T .OPERATE_TYPE\r\nORDER BY\r\n	T .OPERATE_TYPE,\r\n	T .iw_week_date', 'task', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('47', 'task_second_dept_curve_chart', '0', 'SELECT\r\n	TO_CHAR (\r\n		T .iw_week_date,\r\n		\'yyyy-mm-dd\'\r\n	) iw_week_date,\r\n	(\r\n		CASE\r\n		WHEN T .OPERATE_TYPE = 0 THEN\r\n			\'创建\'\r\n		WHEN T .OPERATE_TYPE = \'2\' THEN\r\n			\'归档\'\r\n		ELSE\r\n			\'进行中\'\r\n		END\r\n	) OPERATE_TYPE,\r\n	SUM (T .TASK_COUNT) AS cnt\r\nFROM\r\n	TASK_REPORT_WEEK2 T\r\nWHERE\r\n	T .dept_full_code = \'1/2464/24/2312/2313\'\r\nAND T .iw_week_date >= TRUNC (\r\n	ADD_MONTHS (SYSDATE, - 6),\r\n	\'iw\'\r\n)\r\nAND T .iw_week_date <= SYSDATE\r\nGROUP BY\r\n	T .iw_week_date,\r\n	T .OPERATE_TYPE\r\nORDER BY\r\n	T .OPERATE_TYPE,\r\n	T .iw_week_date', 'task', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('48', 'erp_query_hxj_approval', '0', 'SELECT\r\n	c.TASK_ID_ task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	a.dept_full_name,\r\n	b.create_time_\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nAND c.TASK_ID_ NOT IN (\r\n	SELECT\r\n		t.TASK_ID_\r\n	FROM\r\n		act_ru_identitylink t\r\n	WHERE\r\n		t.TYPE_ = \'candidate\'\r\n	AND t.USER_ID_ = \'1068\'\r\n)\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND c.USER_ID_ = \'275\'\r\nORDER BY\r\n	b.CREATE_TIME_ DESC', 'erp', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('49', 'erp_bx_months_curve', '0', 'SELECT\r\n	concat(t.month_, \'月\'),\r\n	concat(t.year_, \'年\'),\r\n	convert(SUM(sum_money), decimal)\r\nFROM\r\n	bx_report_03_for_happened_depart t\r\nWHERE\r\n	t.year_ >= 2016\r\nAND t.month_ >= 1\r\nGROUP BY\r\n	t.year_,\r\n	month_', 'erp', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('50', 'erp_bx_year_pie', '0', 'SELECT\r\n	(\r\n		CASE\r\n		WHEN t.bill_type = \'ExReDail\' THEN\r\n			\'日常费用报销\'\r\n		WHEN t.bill_type = \'ExReEmBe\' THEN\r\n			\' 员工福利费用报销\'\r\n		WHEN t.bill_type = \'ExReEmCa\' THEN\r\n			\' 员工关怀费用报销\'\r\n		WHEN t.bill_type = \'ExReOthe\' THEN\r\n			\' 其他费用报销\'\r\n		WHEN t.bill_type = \'ExReTeBu\' THEN\r\n			\' TB费用报销\'\r\n		WHEN t.bill_type = \'ExReTrav\' THEN\r\n			\' 差旅费用报销\'\r\n		WHEN t.bill_type = \'ExReHRRe\' THEN\r\n			\' HR招聘相关费用报销\'\r\n		WHEN t.bill_type = \'ExReBuDe\' THEN\r\n			\' 设备采买报销\'\r\n		WHEN t.bill_type IS NULL THEN\r\n			\' 滴滴\'\r\n		ELSE\r\n			\' 未知\'\r\n		END\r\n	) bill_type,\r\n	CONVERT (SUM(sum_money), DECIMAL)\r\nFROM\r\n	bx_report_03_for_happened_depart t\r\nWHERE\r\n	t.year_ = 2017\r\nGROUP BY\r\n	t.bill_type', 'erp', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('51', 'task_unfinished_task', '0', 'SELECT count(1) as count FROM ( SELECT tt.*, ROWNUM AS rowno FROM ( SELECT * FROM ( SELECT * FROM ( SELECT t.task_code, t.task_name, t.task_appler, t.currentflow, t.task_processid, t.modular_code, t.task_node_name, t.createdate, t.tid, ( SELECT DISTINCT ve.user_name FROM task_user tu LEFT JOIN view_ehr_employee ve ON tu.userid = ve.emp_code WHERE ( tu.deleteflag IS NULL OR tu.deleteflag = \'0\' ) AND tu.userid = \'{empCode}\' AND tu.taskid = t.tid ) emp_db_name1, ( SELECT DISTINCT ve.user_name FROM task_assignment ta1 LEFT JOIN view_ehr_employee ve ON ta1.userid = ve.emp_code WHERE ta1.deleteflag = \'0\' AND ta1.userid = \'{empCode}\' AND ta1.taskid = t.tid ) emp_db_name2, ( SELECT DISTINCT ve.user_name AS taskid FROM task_baseinfo tb1 LEFT JOIN view_ehr_employee ve ON tb1.task_applerid = ve.emp_code WHERE tb1.currentflow = \'01\' AND tb1.task_applerid = \'{empCode}\' AND tb1.tid = t.tid ) emp_db_name3 FROM task_baseinfo t WHERE ( t.deleteflag IS NULL OR t.deleteflag = \'0\' ) AND t.tid IN ( SELECT to_number (tu.taskid) AS taskid FROM task_user tu WHERE tu.userid = \'{empCode}\' AND ( tu.deleteflag IS NULL OR tu.deleteflag = \'0\' ) UNION SELECT to_number (ta1.taskid) AS taskid FROM task_assignment ta1 WHERE ta1.deleteflag = \'0\' AND ta1.userid = \'{empCode}\' UNION SELECT tb1.tid AS taskid FROM Task_Baseinfo Tb1 WHERE Tb1.Currentflow = \'01\' AND Tb1.Task_Applerid = \'{Empcode}\' ) AND t.currentflow != \'4\' AND t.currentflow != \'00\' AND t.currentflow != \'02\' ))', 'task', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('52', 'test_execute_select_sql', '0', 'select * from wb_user', 'hr', '36', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('53', 'test_execute_select_sql_oracle', '0', 'select t.id, t.account, t.name from wb_user t', 'hr', '37', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('54', 'find_portal_erp_yb', '0', 'SELECT * from (\r\nSELECT\r\n	a.flow_id flowId,\r\n	a.flow_key flowKey,\r\n	a.flow_name flowName,\r\n	a.flow_state flowState,\r\n	a.bill_code billCode,\r\n	a.starter_num starterNum,\r\n	a.starter_name starterName,\r\n	a.flow_start_time flowStartTime,\r\n	a.flow_end_time flowEndTime,\r\n	a.flow_subject flowSubject,\r\n	a.flow_remark flowRemark,\r\n	c.operate_time operate_time,\r\n	a.flow_entrust flowEntrust,\r\n	c.task_name taskName\r\nFROM\r\n	flow_list_variable a,\r\n	(\r\n		SELECT\r\n			b.bill_code bill_code,\r\n			max(b.operate_time) operate_time,\r\n			b.all_operator_worker_no,\r\n			b.task_name\r\n		FROM\r\n			flow_list_operate b\r\n		WHERE\r\n			b.all_operator_worker_no LIKE \'%669%\'\r\n		AND b.operate_state IN (\r\n			\'已审批\',\r\n			\'委托\',\r\n			\'驳回\'\r\n		)\r\n		GROUP BY\r\n			b.bill_code\r\n	) c\r\nWHERE\r\n	a.bill_code = c.bill_code) main ', '', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('55', 'erp_query_bill', '0', 'SELECT\r\n	t.bill_code,\r\n	t.starter_name,\r\n	t.flow_subject,\r\n	t.flow_start_time\r\nFROM\r\n	flow_list_variable t\r\nWHERE\r\n	t.flow_start_time >= \'2017-01-01\'', 'erp', '38', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('56', 'erp_query_lj_approval', '0', 'SELECT\r\n	c.TASK_ID_ task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	a.dept_full_name,\r\n	a.flow_start_time\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nAND c.TASK_ID_ NOT IN (\r\n	SELECT\r\n		t.TASK_ID_\r\n	FROM\r\n		act_ru_identitylink t\r\n	WHERE\r\n		t.TYPE_ = \'candidate\'\r\n	AND t.USER_ID_ = \'1068\'\r\n)\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND c.USER_ID_ = \'281\'\r\nORDER BY\r\n	a.starter_name,\r\n	a.flow_start_time', 'erp', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('57', 'erp_query_attachment', '0', 'select * from means_books_list t', 'erptest', '39', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('58', 'erp_query_outleave_detail', '0', 'SELECT\r\n	t.start_time, t.end_time, \'外出请假\' remark\r\nFROM	\r\n	at_leave_bill_detail t', 'erp', '39', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('59', 'erp_query_leave_bill_main', '0', 'select * from at_leave_bill_main t ', 'erp', '39', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('60', 'erp_query_backout_leave_bill_main', '0', 'select * from at_leave_backout_bill_main t', 'erp', '39', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('61', 'test_execute_select_where_in', '0', 'select * from wb_user', 'hr', '40', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('62', 'erp_query_authority_detail', '0', 'SELECT\r\n	system_name, authority_path\r\nFROM	\r\n	authority_apply_cancel_bill_detail t where t.bill_code=\'{bill_code}\'\r\nunion \r\nSELECT\r\n	system_name, authority_path\r\nFROM	\r\n	system_authority_apply_cancel_bill_detail t where t.bill_code=\'{bill_code}\'', 'erp', '-1', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('63', 'erp_query_bx_detail', '0', 'SELECT\r\n        t.bill_code,\r\n        t.item_name,\r\n        t.money,\r\n        t.detail_time,\r\nCONCAT(t.remark1, \';\', t.remark2) remark,\r\n-1 task_id,\r\n\'bxdetail\' time_\r\nFROM\r\n        bx_bill_summary t', 'erp', '41', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('64', 'erp_report_bx_by_emp', '0', 'SELECT\r\n				if(month_ < 10, concat(\'0\', t.month_, \'月\'),concat(t.month_, \'月\')),\r\n        concat(t.year_, \'年\'),\r\n        CONVERT (SUM(sum_money), DECIMAL)\r\nFROM\r\n        bx_report_01_for_emp t', 'erp', '42', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('65', 'task_qyxxh_unfinished_column', '0', 'SELECT\r\n	T .task_deal_emp,\r\n\'代办\' unfinished,\r\n	COUNT (1) as cnt\r\nFROM\r\n	VIEW_TASK_ALL_ASSIGNMENT T\r\nWHERE\r\n	T .assignment_dept_full_code = \'{department}\'\r\nOR T .assignment_dept_full_code like \'{department}%\'\r\nGROUP BY\r\n	T .task_deal_emp order by cnt desc', 'task', '-1', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('66', 'erp_query_hxj_approval_likun', '0', 'SELECT\r\n	c.TASK_ID_ task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	a.dept_full_name,\r\n	b.create_time_\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND c.USER_ID_ = \'1068\'\r\nORDER BY\r\n	b.CREATE_TIME_ DESC', 'erp', '-1', null, '3000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('67', 'query_cur_user', '0', 'select * from wb_user', 'hr', '43', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('68', 'erp_query_approval_by_emp', '0', 'SELECT\r\n  a.flow_id,\r\n	c.TASK_ID_ task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	a.dept_full_name,\r\n	b.create_time_ time_\r\nFROM\r\n	flow_list_variable a,\r\n	act_ru_task b,\r\n	act_ru_identitylink c,\r\n	apollo_erp_workerinfo w\r\nWHERE\r\n	a.flow_id = b.PROC_INST_ID_\r\nAND b.ID_ = c.TASK_ID_\r\nand w.workerinfo_No=c.USER_ID_\r\nAND c.TYPE_ = \'candidate\'\r\nAND a.flow_state = \'待审批\'\r\nAND w.out_email=\'{out_email}\'\r\nand w.workerinfo_status=\'onjob\'\r\nORDER BY\r\n	b.CREATE_TIME_ DESC', 'erp', '-1', null, '5', null, null);
INSERT INTO `wb_execute_sql` VALUES ('69', 'erp_query_phone_quota', '0', 'SELECT\r\n	*\r\nFROM\r\n	(\r\n		SELECT\r\n			q.bill_id,\r\n			q.applicant_No,\r\n			q.applicant_deptno,\r\n			q.apply_money,\r\n			q.time_start,\r\n			q.time_end,\r\n			d.dept_full_name,\r\n			d.depart_index_num\r\n		FROM\r\n			apollo_bill_phone_quota q,\r\n			apollo_erp_depart d\r\n		WHERE\r\n			q.applicant_deptno = d.depart_No\r\n		AND q.applicant_No = \'{applicant_No}\'\r\n	) t\r\nWHERE\r\n	t.applicant_deptno = \'{applicant_deptno}\'\r\nOR t.depart_index_num LIKE \'%/{applicant_deptno}/%\'\r\nORDER BY\r\n	t.bill_id DESC', 'erp', '-1', null, '6000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('70', 'test_execute_select_where_in2', '0', 'select * from wb_user  where account in ({accounts})', 'hr', '-1', '1', '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('71', 'erp_workflow_monitor', '0', 'select b.ID_ id,\r\na.bill_code       billCode,\r\n       a.flow_name       flowName,\r\n       a.flow_subject    flowSubject,\r\n       a.starter_name    starterName,\r\n       a.flow_start_time flowStartTime,\r\n       b.NAME_           taskName,\r\n       b.CREATE_TIME_    createTime,\r\n       a.flow_state      flowState,\r\n       b.ID_             taskId,\r\n       a.flow_id         flowId,\r\n       a.flow_key        flowKey\r\n  from flow_list_variable a,\r\n       act_ru_task b,\r\n       act_ru_identitylink c,\r\n       apollo_erp_workerinfo d,\r\n       apollo_erp_worker worker0,\r\n       apollo_erp_worker worker2,\r\n       (SELECT bill_code, finance_code\r\n          from bx_bill_bx\r\n        UNION\r\n        SELECT bill_code, finance_code from bx_bill_clbx) bx_bill\r\n where a.flow_id = b.PROC_INST_ID_\r\n   and b.ID_ = c.TASK_ID_\r\n   and a.flow_state not in (\'撤销\', \'驳回\')     \r\n   and c.USER_ID_ = d.workerinfo_No\r\n   and c.USER_ID_ = worker0.workerinfo_No\r\n   and a.starter_num = worker2.workerinfo_No\r\n   and a.bill_code = bx_bill.bill_code', 'erp', '44', null, '0', null, null);
INSERT INTO `wb_execute_sql` VALUES ('72', 'erp_query_submit_by_emp', '0', 'SELECT\r\n  a.flow_id,\r\n  -1 task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	a.starter_num,\r\n	a.flow_state,\r\n  a.flow_start_time  time_\r\nFROM\r\n	flow_list_variable a,\r\n	apollo_erp_workerinfo w\r\nWHERE\r\n	w.workerinfo_No = a.starter_num\r\nAND w.out_email = \'{out_email}\'\r\nAND w.workerinfo_status = \'onjob\'\r\nAND a.flow_state != \'删除\'\r\nORDER BY\r\n	a.flow_start_time DESC\r\nLIMIT 30', null, '-1', null, '3000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('73', 'erp_query_approved_by_emp', '0', 'SELECT\r\n	a.flow_id,\r\n	- 1 task_id,\r\n	a.bill_code,\r\n	a.starter_name,\r\n	a.flow_subject,\r\n	a.flow_remark,\r\n	o.operate_time time_,\r\n	a.flow_state\r\nFROM\r\n	flow_list_operate o,\r\n	flow_list_variable a,\r\n	apollo_erp_workerinfo w\r\nWHERE\r\n	o.bill_code = a.bill_code\r\nAND o.operator_worker_no = w.workerinfo_No\r\nAND a.starter_num != w.workerinfo_No\r\nAND w.out_email = \'{out_email}\'\r\nAND w.workerinfo_status = \'onjob\'\r\nORDER BY\r\n	o.id DESC\r\nLIMIT 20', null, '-1', null, '3000', null, null);
INSERT INTO `wb_execute_sql` VALUES ('74', 'erp_query_real_card', '0', 'SELECT\r\n	r.id,\r\n	t.bill_code,\r\n	t.starter_name,\r\n	t.starter_num,\r\n	r.on_work_time,\r\n	r.off_work_time\r\nFROM\r\n	at_leave_bill_main t,\r\n	at_attendance_days_result r\r\nWHERE\r\n	t.starter_num = r.worker_num\r\nAND t.bill_code = \'{billCode}\'\r\nAND r.attendance_date = \'{forgetDate}\';', 'erp', '-1', null, '6000', null, '查询实际的打卡时间');

-- ----------------------------
-- Table structure for wb_execute_where_sql
-- ----------------------------
DROP TABLE IF EXISTS `wb_execute_where_sql`;
CREATE TABLE `wb_execute_where_sql` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `where_group` bigint(20) NOT NULL,
  `conj` varchar(16) NOT NULL,
  `expression` varchar(1024) NOT NULL,
  `keep_original` tinyint(4) DEFAULT '0',
  `show_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_execute_where_sql
-- ----------------------------
INSERT INTO `wb_execute_where_sql` VALUES ('1', '1', '', 'id = {id} and work_code=\'{work_code}\' ', '0', '1');
INSERT INTO `wb_execute_where_sql` VALUES ('3', '2', '', 'id = {id}', '0', '3');
INSERT INTO `wb_execute_where_sql` VALUES ('4', '2', 'or', 'work_code=\'{work_code}\'', '0', '4');
INSERT INTO `wb_execute_where_sql` VALUES ('5', '3', '', 'id = {id}', '0', '5');
INSERT INTO `wb_execute_where_sql` VALUES ('6', '3', 'and', 'name  like \'%{name}%\'', '0', '6');
INSERT INTO `wb_execute_where_sql` VALUES ('7', '4', '', 'id = {id}', '0', '7');
INSERT INTO `wb_execute_where_sql` VALUES ('8', '5', '', 'id = {id} ', '0', '8');
INSERT INTO `wb_execute_where_sql` VALUES ('9', '5', 'and ', 'work_code=\'{work_code}\'', '0', '9');
INSERT INTO `wb_execute_where_sql` VALUES ('10', '3', 'or', '(name is null and id is null)', '0', '10');
INSERT INTO `wb_execute_where_sql` VALUES ('11', '7', '', 'name  like \'%{name}%\'', '0', '11');
INSERT INTO `wb_execute_where_sql` VALUES ('12', '8', '', 'account=\'{account}\'', '0', '12');
INSERT INTO `wb_execute_where_sql` VALUES ('13', '9', 'null', 'order by id', '0', '13');
INSERT INTO `wb_execute_where_sql` VALUES ('14', '10', '', 't.parentid={parent} ', '0', '14');
INSERT INTO `wb_execute_where_sql` VALUES ('15', '10', 'null', 'order by id', '0', '15');
INSERT INTO `wb_execute_where_sql` VALUES ('16', '11', '', 'id ={id}', '0', '16');
INSERT INTO `wb_execute_where_sql` VALUES ('17', '11', 'or', 'name like \'%{name}%\'', '0', '17');
INSERT INTO `wb_execute_where_sql` VALUES ('18', '11', 'or', 'work_code like \'%{work_code}%\'', '0', '18');
INSERT INTO `wb_execute_where_sql` VALUES ('19', '12', '', 'id={id}', '0', '19');
INSERT INTO `wb_execute_where_sql` VALUES ('20', '13', '', 't.parentid={parent} ', '0', '20');
INSERT INTO `wb_execute_where_sql` VALUES ('21', '13', 'null', 'order by full_name', '0', '21');
INSERT INTO `wb_execute_where_sql` VALUES ('22', '11', 'or', 'create_date_time>\'{createData}\'', '0', '22');
INSERT INTO `wb_execute_where_sql` VALUES ('23', '11', 'or', '(update_date_time>=\'{updateData_from}\' and update_date_time<=\'{updateData_to}\')', '0', '23');
INSERT INTO `wb_execute_where_sql` VALUES ('24', '11', 'or', 'update_date_time like \'%{updateTime}%\'', '0', '24');
INSERT INTO `wb_execute_where_sql` VALUES ('25', '14', '', 'name like \'%{name}%\'', '0', '25');
INSERT INTO `wb_execute_where_sql` VALUES ('26', '14', 'or', 'id ={id}', '0', '26');
INSERT INTO `wb_execute_where_sql` VALUES ('27', '14', 'or', 'work_code like \'%{work_code}%\'', '0', '27');
INSERT INTO `wb_execute_where_sql` VALUES ('28', '15', '', 'city=\'Berlin\'', '0', '28');
INSERT INTO `wb_execute_where_sql` VALUES ('29', '16', '', 'id in ({t_ids})', '0', '29');
INSERT INTO `wb_execute_where_sql` VALUES ('30', '17', '', 'id%2=1', '0', '30');
INSERT INTO `wb_execute_where_sql` VALUES ('31', '18', 'null', ' and (', '0', '31');
INSERT INTO `wb_execute_where_sql` VALUES ('32', '18', '', 't2.AGENTERName like \'%{AGENTERName}%\' ', '0', '32');
INSERT INTO `wb_execute_where_sql` VALUES ('33', '18', 'or', 't2.AGENTERemail like \'%{AGENTERemail}%\'', '0', '33');
INSERT INTO `wb_execute_where_sql` VALUES ('34', '18', 'or', 't2.BEAGENTERName like \'%{BEAGENTERName}%\'', '0', '34');
INSERT INTO `wb_execute_where_sql` VALUES ('35', '18', 'or', 't2.BEAGENTERemail like \'%{BEAGENTERemail}%\'', '0', '35');
INSERT INTO `wb_execute_where_sql` VALUES ('36', '18', 'null', ')', '0', '36');
INSERT INTO `wb_execute_where_sql` VALUES ('37', '19', 'null', 'and (', '0', '37');
INSERT INTO `wb_execute_where_sql` VALUES ('38', '19', '', 'a.requestmark like \'%{requestmark}%\' ', '0', '38');
INSERT INTO `wb_execute_where_sql` VALUES ('39', '19', 'or ', 'h1.lastname like \'%{createname}%\' ', '0', '39');
INSERT INTO `wb_execute_where_sql` VALUES ('40', '19', 'or', 'b.code like \'%{leavercode}%\' ', '0', '40');
INSERT INTO `wb_execute_where_sql` VALUES ('41', '19', 'or', 'h2.lastname like \'%{leavername}%\' ', '0', '41');
INSERT INTO `wb_execute_where_sql` VALUES ('42', '19', 'null', 'or (', '0', '42');
INSERT INTO `wb_execute_where_sql` VALUES ('43', '19', '', 'a.createdate >= \'{startcreatedate}\' ', '0', '43');
INSERT INTO `wb_execute_where_sql` VALUES ('44', '19', 'and ', 'a.createdate <= \'{endcreatedate}\' ', '0', '44');
INSERT INTO `wb_execute_where_sql` VALUES ('45', '19', 'null', ')', '0', '45');
INSERT INTO `wb_execute_where_sql` VALUES ('46', '19', 'null', ')', '0', '46');
INSERT INTO `wb_execute_where_sql` VALUES ('47', '20', 'null', 'and r.requestmark like \'%{requestmark}%\'', '0', '47');
INSERT INTO `wb_execute_where_sql` VALUES ('48', '20', 'null', ' order by logid desc', '0', '48');
INSERT INTO `wb_execute_where_sql` VALUES ('49', '21', 'null', 'and r.requestmark like \'%{requestmark}%\'', '0', '49');
INSERT INTO `wb_execute_where_sql` VALUES ('50', '21', 'null', 'order by id desc', '0', '50');
INSERT INTO `wb_execute_where_sql` VALUES ('51', '22', 'null', 'and w.worker_no like \'%{workerno}%\'', '0', '51');
INSERT INTO `wb_execute_where_sql` VALUES ('52', '22', 'null', 'and t.out_email like  \'%{email}%\'', '0', '52');
INSERT INTO `wb_execute_where_sql` VALUES ('53', '22', 'null', 'and t.workerinfo_name like \'%{name}%\'', '0', '53');
INSERT INTO `wb_execute_where_sql` VALUES ('54', '23', 'null', 'and t.emp_name like \'%{name}%\'', '0', '54');
INSERT INTO `wb_execute_where_sql` VALUES ('55', '23', 'null', 'and t.emp_no like \'%{workerno}%\'', '0', '55');
INSERT INTO `wb_execute_where_sql` VALUES ('56', '23', 'null', 'and t.work_email like \'%{email}%\'', '0', '56');
INSERT INTO `wb_execute_where_sql` VALUES ('57', '23', 'null', 'and d.dept_short_name like \'%{deptshortnamel}%\'', '0', '57');
INSERT INTO `wb_execute_where_sql` VALUES ('58', '24', '', 'r.requestmark like \'%{requestmark}%\'', '0', '58');
INSERT INTO `wb_execute_where_sql` VALUES ('59', '24', 'and', 'r.requestname like \'%{requestname}%\'', '0', '59');
INSERT INTO `wb_execute_where_sql` VALUES ('60', '24', 'and', 'h1.lastname like \'%{createname}%\'', '0', '60');
INSERT INTO `wb_execute_where_sql` VALUES ('61', '24', 'and ', 'h2.lastname like \'%{lastoperatorname}%\'', '0', '61');
INSERT INTO `wb_execute_where_sql` VALUES ('62', '24', 'null', 'order by r.requestid desc', '0', '62');
INSERT INTO `wb_execute_where_sql` VALUES ('63', '25', '', 'imagefilename like \'%{imagefilename1}%\'', '0', '63');
INSERT INTO `wb_execute_where_sql` VALUES ('64', '25', 'and', 'imagefilename like \'%{imagefilename2}%\'', '0', '64');
INSERT INTO `wb_execute_where_sql` VALUES ('65', '-1', 'null ', 'order by DOCID desc', '0', '65');
INSERT INTO `wb_execute_where_sql` VALUES ('66', '26', 'null', 'and t.id={departmentid}', '0', '66');
INSERT INTO `wb_execute_where_sql` VALUES ('67', '26', 'null', 'and t.departmentcode = {departmentcode}', '0', '67');
INSERT INTO `wb_execute_where_sql` VALUES ('68', '26', 'null', 'and h.lastname like \'%{bmfzr}%\'', '0', '68');
INSERT INTO `wb_execute_where_sql` VALUES ('69', '26', 'null', 'and t.dept_full_name like  \'%{departmentname}%\'', '0', '69');
INSERT INTO `wb_execute_where_sql` VALUES ('70', '26', 'null', 'order by t.dept_full_name', '0', '70');
INSERT INTO `wb_execute_where_sql` VALUES ('71', '27', '', 'BMFZR = -1 and CANCELED = 0 and id != 4285 order by dept_full_name', '0', '71');
INSERT INTO `wb_execute_where_sql` VALUES ('72', '28', 'null', 'AND a.bill_code LIKE \'%{billCode}%\'', '0', '72');
INSERT INTO `wb_execute_where_sql` VALUES ('73', '28', 'null', 'AND a.starter_name LIKE \'%{starterName}%\'', '0', '73');
INSERT INTO `wb_execute_where_sql` VALUES ('74', '28', 'null', 'AND a.flow_name LIKE \'%{flowName}%\'', '0', '74');
INSERT INTO `wb_execute_where_sql` VALUES ('75', '23', 'null', 'and t.mobile_no like \'%{mobileno}%\'', '0', '75');
INSERT INTO `wb_execute_where_sql` VALUES ('76', '29', '', 'DEPT_FULL_NAME like  \'%{name}%\'', '0', '76');
INSERT INTO `wb_execute_where_sql` VALUES ('77', '29', 'null', 'order by DEPT_FULL_NAME', '0', '77');
INSERT INTO `wb_execute_where_sql` VALUES ('78', '30', 'null', 'and w.workerinfo_name = \'{name}\'', '0', '78');
INSERT INTO `wb_execute_where_sql` VALUES ('79', '30', 'null', 'and w2.worker_no = \'{workno}\'', '0', '79');
INSERT INTO `wb_execute_where_sql` VALUES ('80', '30', 'null', 'and t.attendance_year = {year}', '0', '80');
INSERT INTO `wb_execute_where_sql` VALUES ('81', '30', 'null', 'and t.attendance_quarter = {quarter}', '0', '81');
INSERT INTO `wb_execute_where_sql` VALUES ('82', '30', 'null', 'and t.attendance_month = {month}', '0', '82');
INSERT INTO `wb_execute_where_sql` VALUES ('83', '30', 'null', 'order by id', '0', '83');
INSERT INTO `wb_execute_where_sql` VALUES ('84', '31', 'null', 'AND t.attendance_year = {year}', '0', '84');
INSERT INTO `wb_execute_where_sql` VALUES ('85', '31', 'null', '                                                  ', '0', '85');
INSERT INTO `wb_execute_where_sql` VALUES ('86', '31', 'null', 'AND t.attendance_month = {month}', '0', '86');
INSERT INTO `wb_execute_where_sql` VALUES ('87', '31', 'null', 'AND t.worker_no_new LIKE \'%{workno}%\'', '0', '87');
INSERT INTO `wb_execute_where_sql` VALUES ('88', '31', 'null', 'AND t.worker_name LIKE \'%{name}%\'', '0', '88');
INSERT INTO `wb_execute_where_sql` VALUES ('89', '32', 'null', 'AND t.attendance_year = {year}', '0', '89');
INSERT INTO `wb_execute_where_sql` VALUES ('90', '32', 'null', 'AND t.attendance_month = {month}', '0', '90');
INSERT INTO `wb_execute_where_sql` VALUES ('91', '32', 'null', 'ORDER BY t.worktime_total desc', '0', '91');
INSERT INTO `wb_execute_where_sql` VALUES ('92', '33', 'null', 'AND t.worker_name LIKE \'%{name}%\'', '0', '92');
INSERT INTO `wb_execute_where_sql` VALUES ('93', '33', 'null', 'AND t.worker_no_new LIKE \'%{workno}%\'', '0', '93');
INSERT INTO `wb_execute_where_sql` VALUES ('94', '33', 'null', 'and t.attendance_year = {year}', '0', '94');
INSERT INTO `wb_execute_where_sql` VALUES ('95', '33', 'null', 'and t.attendance_quarter = {quarter}', '0', '95');
INSERT INTO `wb_execute_where_sql` VALUES ('96', '33', 'null', 'and t.attendance_month = {month}', '0', '96');
INSERT INTO `wb_execute_where_sql` VALUES ('97', '34', 'and', 'task_name like \'%{task_name}%\' ', '0', '97');
INSERT INTO `wb_execute_where_sql` VALUES ('98', '34', 'and', 'task_appler like \'%{task_appler}%\' ', '0', '98');
INSERT INTO `wb_execute_where_sql` VALUES ('99', '34', 'and', 'task_accept like \'%{task_accept}%\'', '0', '99');
INSERT INTO `wb_execute_where_sql` VALUES ('100', '35', '', 'task_name like \'%{title}%\'', '0', '100');
INSERT INTO `wb_execute_where_sql` VALUES ('101', '35', 'and', 'task_code like \'%{flowNo}%\'', '0', '101');
INSERT INTO `wb_execute_where_sql` VALUES ('102', '35', 'and', 'task_appler like \'%{applyName}%\'', '0', '102');
INSERT INTO `wb_execute_where_sql` VALUES ('103', '35', 'and', 'task_appler like \'%{applyName}%\'', '0', '103');
INSERT INTO `wb_execute_where_sql` VALUES ('104', '35', 'and', 'to_char(createdate,\'yyyy-mm-dd\') between \'{applyStartDate}\' and \'{applyEndDate}\'', '0', '104');
INSERT INTO `wb_execute_where_sql` VALUES ('105', '35', 'and', 'modular_code = \'{rc}\'', '0', '105');
INSERT INTO `wb_execute_where_sql` VALUES ('106', '35', 'and', 'modular_code != \'{xm}\'', '0', '106');
INSERT INTO `wb_execute_where_sql` VALUES ('107', '35', 'and', 'ORDER BY createdate DESC) tt WHERE ROWNUM <= {endCount}) table_alias WHERE table_alias.rowno >= {startCount}', '0', '107');
INSERT INTO `wb_execute_where_sql` VALUES ('108', '36', 'null', 'order by id', '0', '108');
INSERT INTO `wb_execute_where_sql` VALUES ('109', '-1', '', 't.loginid is not null', '0', '109');
INSERT INTO `wb_execute_where_sql` VALUES ('110', '37', 'null', 'order by t.id', '0', '110');
INSERT INTO `wb_execute_where_sql` VALUES ('111', '38', 'null', 'and t.bill_code like \'%{bill_code}%\'', '0', '111');
INSERT INTO `wb_execute_where_sql` VALUES ('112', '38', 'null', 'and t.starter_name like \'%{starter_name}%\'', '0', '112');
INSERT INTO `wb_execute_where_sql` VALUES ('113', '38', 'null', 'and t.flow_subject like \'%{flow_subject}%\'', '0', '113');
INSERT INTO `wb_execute_where_sql` VALUES ('114', '38', 'null', 'LIMIT 20', '0', '114');
INSERT INTO `wb_execute_where_sql` VALUES ('115', '39', '', 't.bill_code=\'{bill_code}\'', '0', '115');
INSERT INTO `wb_execute_where_sql` VALUES ('116', '40', '', ' account in ({accounts})', '1', '116');
INSERT INTO `wb_execute_where_sql` VALUES ('117', '41', '', 't.applicant_No = \'{applicant_No}\'', '0', '117');
INSERT INTO `wb_execute_where_sql` VALUES ('118', '41', 'null', 'and t.bill_type=\'{bill_type}\'', '0', '118');
INSERT INTO `wb_execute_where_sql` VALUES ('119', '41', 'null', 'order by t.id DESC', '0', '119');
INSERT INTO `wb_execute_where_sql` VALUES ('120', '41', 'null', 'LIMIT 50', '0', '120');
INSERT INTO `wb_execute_where_sql` VALUES ('121', '42', '', 't.year_>=2016', '0', '121');
INSERT INTO `wb_execute_where_sql` VALUES ('122', '42', 'null', 'and t.applicant_No = \'{applicant_No}\'', '0', '122');
INSERT INTO `wb_execute_where_sql` VALUES ('123', '42', 'null', 'and t.bill_type=\'{bill_type}\'', '0', '123');
INSERT INTO `wb_execute_where_sql` VALUES ('124', '42', 'null', 'GROUP BY\r\n	t.year_,\r\n	month_', '0', '124');
INSERT INTO `wb_execute_where_sql` VALUES ('125', '42', 'null', 'order by   t.year_, month_', '0', '125');
INSERT INTO `wb_execute_where_sql` VALUES ('126', '43', '', 'id={s_userid}', '0', '126');
INSERT INTO `wb_execute_where_sql` VALUES ('127', '44', 'null', 'and a.starter_name like \'%{name}%\'', '0', null);

-- ----------------------------
-- Table structure for wb_i18n_dict
-- ----------------------------
DROP TABLE IF EXISTS `wb_i18n_dict`;
CREATE TABLE `wb_i18n_dict` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(64) NOT NULL,
  `chinese` varchar(256) NOT NULL,
  `engilish` varchar(256) NOT NULL,
  `other` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_i18n_dict
-- ----------------------------
INSERT INTO `wb_i18n_dict` VALUES ('1', 'id', '序号', 'id', 'id');
INSERT INTO `wb_i18n_dict` VALUES ('2', 'name', '姓名', 'name', 'name');
INSERT INTO `wb_i18n_dict` VALUES ('3', 'chname', '中文姓名', 'Name in Chinese', 'Name in Chinese');
INSERT INTO `wb_i18n_dict` VALUES ('4', 'enname', '英文姓名', 'Name in English', 'Name in English');
INSERT INTO `wb_i18n_dict` VALUES ('5', 'workcode', '工号', 'Work Code', 'Work Code');
INSERT INTO `wb_i18n_dict` VALUES ('6', 'query', '查询', 'query', 'query');
INSERT INTO `wb_i18n_dict` VALUES ('7', 'delete', '删除', 'delete', 'delete');

-- ----------------------------
-- Table structure for wb_import_config
-- ----------------------------
DROP TABLE IF EXISTS `wb_import_config`;
CREATE TABLE `wb_import_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(64) NOT NULL,
  `id_field` varchar(64) NOT NULL,
  `generate_id_sql` varchar(128) NOT NULL,
  `unique_field` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_import_config
-- ----------------------------
INSERT INTO `wb_import_config` VALUES ('1', 'wb_user', 'id', '', 'work_code');

-- ----------------------------
-- Table structure for wb_menu
-- ----------------------------
DROP TABLE IF EXISTS `wb_menu`;
CREATE TABLE `wb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentid` bigint(11) NOT NULL,
  `caption` varchar(64) NOT NULL,
  `url` varchar(1024) NOT NULL,
  `params` varchar(128) NOT NULL,
  `templet` varchar(256) NOT NULL,
  `show_order` int(11) NOT NULL,
  `is_delete` tinyint(3) unsigned zerofill DEFAULT '000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_menu
-- ----------------------------
INSERT INTO `wb_menu` VALUES ('1', '0', '测试页面', 'left_menu.jsp?id=1&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '30000', '000');
INSERT INTO `wb_menu` VALUES ('2', '1', '控件', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '9999', '000');
INSERT INTO `wb_menu` VALUES ('3', '2', '控件示例', 'controls/controls_example.jsp', '', 'default_left_menu_li_li.html', '9998', '000');
INSERT INTO `wb_menu` VALUES ('4', '2', '控件权限示例', 'controls/security_compent.jsp', '', 'default_left_menu_li_li.html', '9997', '000');
INSERT INTO `wb_menu` VALUES ('5', '126', '逐级演进', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '9996', '000');
INSERT INTO `wb_menu` VALUES ('6', '5', 'jsp 标签分页查询', 'datatables/jsp_tab_datatable.jsp', '', 'default_left_menu_li_li.html', '9994', '000');
INSERT INTO `wb_menu` VALUES ('7', '5', 'ajax 分页查询', 'datatables/ajax_datatable.html', '', 'default_left_menu_li_li.html', '9995', '000');
INSERT INTO `wb_menu` VALUES ('8', '5', 'jsong 分页查询', 'datatables/json_datatable.jsp', '', 'default_left_menu_li_li.html', '9993', '000');
INSERT INTO `wb_menu` VALUES ('9', '1', 'jstree', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9992', '000');
INSERT INTO `wb_menu` VALUES ('10', '9', 'jsp 标签西游记组织', 'jstree/jsp_tab_tree.jsp', '', 'default_left_menu_li_li.html', '9990', '000');
INSERT INTO `wb_menu` VALUES ('11', '9', 'ajax 西游记组织', 'jstree/ajax_tree.html', '', 'default_left_menu_li_li.html', '9991', '000');
INSERT INTO `wb_menu` VALUES ('12', '9', 'jsong 西游记组织', 'jstree/json_tree.jsp', '', 'default_left_menu_li_li.html', '9989', '000');
INSERT INTO `wb_menu` VALUES ('13', '9', '菜单测试', 'jstree/jsp_tab_menu.jsp', '', 'default_left_menu_li_li.html', '9988', '000');
INSERT INTO `wb_menu` VALUES ('14', '1', '按钮', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9987', '000');
INSERT INTO `wb_menu` VALUES ('15', '14', '自定义标签按钮', 'button/btn_blue.jsp', '', 'default_left_menu_li_li.html', '9986', '000');
INSERT INTO `wb_menu` VALUES ('16', '1', '权限演示', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9985', '000');
INSERT INTO `wb_menu` VALUES ('17', '16', '权限说明', 'security/readme.html', '', 'default_left_menu_li_li.html', '9984', '000');
INSERT INTO `wb_menu` VALUES ('18', '1', '更新页面值演示', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9983', '000');
INSERT INTO `wb_menu` VALUES ('19', '18', '更新表单数据', 'updateitem/update_item.jsp', '', 'default_left_menu_li_li.html', '9982', '000');
INSERT INTO `wb_menu` VALUES ('20', '18', '更新一组item更新值', 'updateitem/update_group_item.jsp', '', 'default_left_menu_li_li.html', '9981', '000');
INSERT INTO `wb_menu` VALUES ('21', '18', '带有查询条件', 'updateitem/update_item_by_param.jsp', '', 'default_left_menu_li_li.html', '9980', '000');
INSERT INTO `wb_menu` VALUES ('22', '1', '数据库操作', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9979', '000');
INSERT INTO `wb_menu` VALUES ('23', '22', '修改记录', 'updatedb/update.jsp', '', 'default_left_menu_li_li.html', '9978', '000');
INSERT INTO `wb_menu` VALUES ('24', '22', '删除记录', 'updatedb/delete.jsp', '', 'default_left_menu_li_li.html', '9977', '000');
INSERT INTO `wb_menu` VALUES ('25', '22', '添加记录', 'updatedb/insert.jsp', '', 'default_left_menu_li_li.html', '9976', '000');
INSERT INTO `wb_menu` VALUES ('26', '0', '个人中心', 'left_menu.jsp?id=26&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '9975', '000');
INSERT INTO `wb_menu` VALUES ('27', '26', '我的信息', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '9974', '000');
INSERT INTO `wb_menu` VALUES ('28', '27', '基本信息', 'module/person_basic_info.jsp', '', 'default_left_menu_li_li.html', '9973', '000');
INSERT INTO `wb_menu` VALUES ('29', '26', '退出', 'page_user_login_1.html', '', 'default_left_menu_li_li_self.html', '9972', '000');
INSERT INTO `wb_menu` VALUES ('30', '0', 'ERP', 'left_menu.jsp?id=30&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '19000', '001');
INSERT INTO `wb_menu` VALUES ('31', '30', '人员信息', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '9970', '000');
INSERT INTO `wb_menu` VALUES ('32', '31', '人员查询', 'erp/erp_emp.jsp', '', 'default_left_menu_li_li.html', '9969', '000');
INSERT INTO `wb_menu` VALUES ('33', '0', 'OA', 'left_menu.jsp?id=33&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '20000', '001');
INSERT INTO `wb_menu` VALUES ('34', '33', '代理', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9967', '000');
INSERT INTO `wb_menu` VALUES ('35', '34', '非子账号代理', 'oa/no_subaccount_agent.jsp', '', 'default_left_menu_li_li.html', '9800', '000');
INSERT INTO `wb_menu` VALUES ('36', '0', 'home', '/myweb/', '', 'default_hor_menu_li.html', '9700', '000');
INSERT INTO `wb_menu` VALUES ('37', '1', '图表', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9964', '000');
INSERT INTO `wb_menu` VALUES ('38', '37', '曲线图', 'chart/chart_curve.jsp', '', 'default_left_menu_li_li.html', '9963', '000');
INSERT INTO `wb_menu` VALUES ('40', '37', '柱状图1', 'chart/chart_column1.jsp', '', 'default_left_menu_li_li.html', '9961', '000');
INSERT INTO `wb_menu` VALUES ('41', '37', '柱状图2', 'chart/chart_column2.jsp', '', 'default_left_menu_li_li.html', '9960', '000');
INSERT INTO `wb_menu` VALUES ('42', '37', '横向柱状图', 'chart/chart_bar.jsp', '', 'default_left_menu_li_li.html', '9959', '000');
INSERT INTO `wb_menu` VALUES ('43', '37', '饼状图', 'chart/chart_pie.jsp', '', 'default_left_menu_li_li.html', '9958', '000');
INSERT INTO `wb_menu` VALUES ('44', '127', '自定义sql查询1', 'datatables/custom_query1.jsp', '', 'default_left_menu_li_li.html', '9957', '000');
INSERT INTO `wb_menu` VALUES ('45', '127', '自定义sql查询2', 'datatables/custom_query2.jsp', '', 'default_left_menu_li_li.html', '9956', '000');
INSERT INTO `wb_menu` VALUES ('46', '127', '自定义sql查询3', 'datatables/custom_query3.jsp', '', 'default_left_menu_li_li.html', '9955', '000');
INSERT INTO `wb_menu` VALUES ('47', '48', '德哥代办', 'oa/dege_no_approval.jsp', '', 'default_left_menu_li_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('48', '33', '流程', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9800', '000');
INSERT INTO `wb_menu` VALUES ('49', '34', '全部有效代理', 'oa/agent_avalid.jsp', '', 'default_left_menu_li_li.html', '9810', '000');
INSERT INTO `wb_menu` VALUES ('50', '48', '被动离职流程', 'oa/leave_workflow.jsp', '', 'default_left_menu_li_li.html', '8080', '000');
INSERT INTO `wb_menu` VALUES ('51', '48', '李坤审批过', 'oa/likun_approvaled_wk.jsp', '', 'default_left_menu_li_li.html', '8070', '000');
INSERT INTO `wb_menu` VALUES ('52', '48', '李坤未审批的', 'oa/likun_no_approvaled_wk.jsp', '', 'default_left_menu_li_li.html', '8060', '000');
INSERT INTO `wb_menu` VALUES ('53', '0', '人事平台', 'left_menu.jsp?id=53&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '11000', '001');
INSERT INTO `wb_menu` VALUES ('54', '53', '人事信息（在职）', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '10100', '000');
INSERT INTO `wb_menu` VALUES ('55', '54', '企业信息化人员', 'hp/hp_emp.jsp', '', 'default_left_menu_li_li.html', '10010', '000');
INSERT INTO `wb_menu` VALUES ('56', '48', '流程查询', 'oa/oa_request.jsp', '', 'default_left_menu_li_li.html', '8090', '000');
INSERT INTO `wb_menu` VALUES ('57', '48', '流程附件查询', 'oa/oa_file.jsp', '', 'default_left_menu_li_li.html', '8050', '000');
INSERT INTO `wb_menu` VALUES ('58', '33', '人员、部门', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '12000', '000');
INSERT INTO `wb_menu` VALUES ('59', '58', '部门信息', 'oa/oa_department.jsp', '', 'default_left_menu_li_li.html', '11900', '000');
INSERT INTO `wb_menu` VALUES ('60', '58', '没有部门负责人', 'oa/oa_department_no_bmfzr.jsp', '', 'default_left_menu_li_li.html', '11800', '000');
INSERT INTO `wb_menu` VALUES ('61', '30', '流程', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9800', '000');
INSERT INTO `wb_menu` VALUES ('62', '61', '德哥代办', 'erp/erp_dege_approval.jsp', '', 'default_left_menu_li_li.html', '9700', '000');
INSERT INTO `wb_menu` VALUES ('63', '61', '李坤代办', '../request/erp_dege_no_approval_query?dbs=erp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '9600', '000');
INSERT INTO `wb_menu` VALUES ('64', '54', '畅游人员', 'hp/hp_cy_emp.jsp', '', 'default_left_menu_li_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('65', '53', '人事信息（全部）', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '10000', '000');
INSERT INTO `wb_menu` VALUES ('66', '65', '畅游人员', 'hp/hp_all_emp.jsp', '', 'default_left_menu_li_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('67', '65', '部门', '../request/hr_depart?dbs=hp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('68', '65', '部门2', '../request/hr_depart2?dbs=hp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '7000', '000');
INSERT INTO `wb_menu` VALUES ('69', '30', '统计', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('70', '69', '企业信息化月考勤统计', '../request/erp_qyxxh_attendance_statistics?dbs=erp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8900', '000');
INSERT INTO `wb_menu` VALUES ('71', '69', '企业信息化月考勤统计图', 'erp/erp_qyxxh_attendance_column.jsp', '', 'default_left_menu_li_li.html', '8800', '000');
INSERT INTO `wb_menu` VALUES ('72', '69', '企业信息化全勤', '../request/erp_qyxxh_attendance_quanqin?dbs=erp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8700', '000');
INSERT INTO `wb_menu` VALUES ('73', '69', '个人考勤查询', '../request/erp_employee_attendance?dbs=erp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8600', '000');
INSERT INTO `wb_menu` VALUES ('74', '128', '使用扩展插件返回结果', 'datatables/ajax_datatable_plugin.html', '', 'default_left_menu_li_li.html', '7000', '000');
INSERT INTO `wb_menu` VALUES ('75', '30', '接口测试', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('76', '75', '今天是否是工作日', 'erp/today_is_holiday.jsp', '', 'default_left_menu_li_li.html', '7900', '000');
INSERT INTO `wb_menu` VALUES ('77', '75', '是否在时间范围内', 'erp/time_is_range_except_holiday.jsp', '', 'default_left_menu_li_li.html', '7800', '000');
INSERT INTO `wb_menu` VALUES ('78', '0', '任务', 'left_menu.jsp?id=78&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '10100', '001');
INSERT INTO `wb_menu` VALUES ('79', '78', '企业信息化', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '10090', '000');
INSERT INTO `wb_menu` VALUES ('80', '79', '进行中任务', '../request/qyxxh_unfinished_task?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '10080', '000');
INSERT INTO `wb_menu` VALUES ('81', '79', '一部进行中任务', '../request/first_depart_unfinished_task?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '10070', '000');
INSERT INTO `wb_menu` VALUES ('82', '79', '二部进行中任务', '../request/second_depart_unfinished_task?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '10060', '000');
INSERT INTO `wb_menu` VALUES ('83', '79', '过期10天任务', '../request/qyxxh_overtime_task?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '10050', '000');
INSERT INTO `wb_menu` VALUES ('84', '1', '数据库配置图表', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9800', '000');
INSERT INTO `wb_menu` VALUES ('85', '84', '横向柱状图', '../request/chart_bar?act=page', '', 'default_left_menu_li_li.html', '9700', '000');
INSERT INTO `wb_menu` VALUES ('86', '84', '曲线图', '../request/chart_curve?act=page', '', 'default_left_menu_li_li.html', '9790', '000');
INSERT INTO `wb_menu` VALUES ('87', '84', '柱状图1', '../request/chart_column1?act=page', '', 'default_left_menu_li_li.html', '9780', '000');
INSERT INTO `wb_menu` VALUES ('88', '84', '柱状图2', '../request/chart_column2?act=page', '', 'default_left_menu_li_li.html', '9770', '000');
INSERT INTO `wb_menu` VALUES ('89', '84', '饼状图', '../request/chart_pie?act=page', '', 'default_left_menu_li_li.html', '9600', '000');
INSERT INTO `wb_menu` VALUES ('90', '84', '数据库中配置图标说明', 'chart/chart_config_db_remark.jsp', '', 'default_left_menu_li_li.html', '9795', '000');
INSERT INTO `wb_menu` VALUES ('91', '5', '数据库中配置datatable说明', 'datatables/datatable_config_db_remark.jsp', '', 'default_left_menu_li_li.html', '6900', '000');
INSERT INTO `wb_menu` VALUES ('92', '5', '数据库中配置datatable', '../request/config_db_get_user?dbs=hr&rtype=1&act=page', '', 'default_left_menu_li_li.html', '6800', '000');
INSERT INTO `wb_menu` VALUES ('93', '94', '统计曲线', '../request/task_qyxxh_curve_chart?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8900', '000');
INSERT INTO `wb_menu` VALUES ('94', '78', '企业信息化报表', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('95', '94', '一部统计曲线', '../request/task_first_dept_curve_chart?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8890', '000');
INSERT INTO `wb_menu` VALUES ('96', '94', '二部统计曲线', '../request/task_second_dept_curve_chart?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '8880', '000');
INSERT INTO `wb_menu` VALUES ('97', '128', '使用js插件返回结果', 'datatables/ajax_datatable_jsplugin.html', '', 'default_left_menu_li_li.html', '6700', '000');
INSERT INTO `wb_menu` VALUES ('98', '1', 'java和js交互测试', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9700', '000');
INSERT INTO `wb_menu` VALUES ('99', '98', '数据this对象内容', 'javajs/js_out_this.html', '', 'default_left_menu_li_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('100', '2', '数据库中配置组件说明', 'controls/datatable_config_db_remark.jsp', '', 'default_left_menu_li_li.html', '9900', '000');
INSERT INTO `wb_menu` VALUES ('101', '2', '数据库中配置组件', '../request/example_config_compent?dbs=system&act=page', '', 'default_left_menu_li_li.html', '9890', '000');
INSERT INTO `wb_menu` VALUES ('102', '22', '分页查询list', 'updatedb/selectlist.jsp', '', 'default_left_menu_li_li.html', '9990', '000');
INSERT INTO `wb_menu` VALUES ('103', '22', '分页查询map', 'updatedb/selectmap.jsp', '', 'default_left_menu_li_li.html', '9985', '000');
INSERT INTO `wb_menu` VALUES ('104', '22', '分页查询list(oracle)', 'updatedb/selectlist_oracle.jsp', '', 'default_left_menu_li_li.html', '9983', '000');
INSERT INTO `wb_menu` VALUES ('105', '22', '分页查询map(oracle)', 'updatedb/selectmap_oracle.jsp', '', 'default_left_menu_li_li.html', '9980', '000');
INSERT INTO `wb_menu` VALUES ('106', '22', '验证sql', 'updatedb/testsql.jsp', '', 'default_left_menu_li_li.html', '10000', '000');
INSERT INTO `wb_menu` VALUES ('107', '78', '个人统计', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('108', '107', '企业信息化柱状图', '../request/task_qyxxh_unfinished_column?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '7000', '000');
INSERT INTO `wb_menu` VALUES ('109', '107', '一部柱状图', '../request/task_first_unfinished_column?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '6900', '000');
INSERT INTO `wb_menu` VALUES ('110', '107', '二部柱状图', '../request/task_second_unfinished_column?dbs=task&rtype=1&act=page', '', 'default_left_menu_li_li.html', '6800', '000');
INSERT INTO `wb_menu` VALUES ('111', '84', '多图标', '../request/muti_chart?act=page', '', 'default_left_menu_li_li.html', '9500', '000');
INSERT INTO `wb_menu` VALUES ('112', '0', 'H5', 'left_menu.jsp?id=112&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '29000', '000');
INSERT INTO `wb_menu` VALUES ('113', '112', '图标', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '28000', '000');
INSERT INTO `wb_menu` VALUES ('114', '113', '曲线图', '../h5/test/charts.jsp', '', 'default_left_menu_li_li_blank.html', '27000', '000');
INSERT INTO `wb_menu` VALUES ('115', '0', '帮助', 'left_menu.jsp?id=115&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('116', '115', '概述', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '27000', '000');
INSERT INTO `wb_menu` VALUES ('117', '116', '设计思想', '/myweb/help/01-design-idea.htm', '', 'default_left_menu_li_li.html', '26000', '000');
INSERT INTO `wb_menu` VALUES ('118', '116', '开发工具', '/myweb/help/02-development-tools.htm', '', 'default_left_menu_li_li.html', '25900', '000');
INSERT INTO `wb_menu` VALUES ('119', '116', '典型示例', '/myweb/help/03-typical-case.htm', '', 'default_left_menu_li_li.html', '25800', '000');
INSERT INTO `wb_menu` VALUES ('120', '129', '模拟从其他服务器获取数据', 'datatables/get_data_from_server.html', '', 'default_left_menu_li_li.html', '6600', '000');
INSERT INTO `wb_menu` VALUES ('121', '129', '模拟从其他服务器获取数据2', 'datatables/get_data_from_server_clone_param.html', '', 'default_left_menu_li_li.html', '6500', '000');
INSERT INTO `wb_menu` VALUES ('122', '130', 'html源码中内嵌全动态页', 'datatables/dynamic_div_in_html.html', '', 'default_left_menu_li_li.html', '6400', '000');
INSERT INTO `wb_menu` VALUES ('123', '130', 'DIV弹出窗口', 'datatables/div_dialog_in_html.html', '', 'default_left_menu_li_li.html', '6300', '000');
INSERT INTO `wb_menu` VALUES ('124', '130', 'DIV弹出窗口2', 'datatables/div_dialog_in_html2.html', '', 'default_left_menu_li_li.html', '6200', '000');
INSERT INTO `wb_menu` VALUES ('125', '130', 'Dialog弹出窗口', 'datatables/pop_dialog_in_html.html?uuid=div_dialog_in_html2&title=人员选择器', '', 'default_left_menu_li_li.html', '6100', '000');
INSERT INTO `wb_menu` VALUES ('126', '0', 'datatables', 'left_menu.jsp?id=126&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '40000', '000');
INSERT INTO `wb_menu` VALUES ('127', '126', '自定义查询', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '9000', '000');
INSERT INTO `wb_menu` VALUES ('128', '126', 'Java插件/js插件', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '8000', '000');
INSERT INTO `wb_menu` VALUES ('129', '126', '从其他服务器获取数据', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '7000', '000');
INSERT INTO `wb_menu` VALUES ('130', '126', 'DIV窗口/Dialog/Iframe', 'javascript:;', '{\"style\":\"none\"}', 'default_left_menu_li.html', '6000', '000');
INSERT INTO `wb_menu` VALUES ('131', '130', 'Dialog单选框', 'datatables/pop_dialog_in_html.html?uuid=div_dialog_radio_in_html&title=人员选择器，单选框', '', 'default_left_menu_li_li.html', '6000', '000');
INSERT INTO `wb_menu` VALUES ('132', '130', 'Ifrmae', 'datatables/datatable_iframe.html', '', 'default_left_menu_li_li.html', '5900', '000');
INSERT INTO `wb_menu` VALUES ('133', '113', 'iframe', 'chart/charts_iframe.html', '', 'default_left_menu_li_li.html', '26000', '000');
INSERT INTO `wb_menu` VALUES ('134', '61', '流程监控', '../request/erp_workflow_monitor?dbs=erp&rtype=1&act=page', '', 'default_left_menu_li_li.html', '6900', '000');
INSERT INTO `wb_menu` VALUES ('135', '0', '系统设置', 'left_menu.jsp?id=135&sub=1', '{\"cssclass\":\"first-menu\"}', 'default_hor_menu_li.html', '29100', '000');
INSERT INTO `wb_menu` VALUES ('136', '135', '配置缓存管理', 'javascript:;', '{\"cssclass\":\"start active open\",\"style\":\"block\"}', 'default_left_menu_li.html', '29000', '000');
INSERT INTO `wb_menu` VALUES ('137', '136', '刷新配置缓存', 'system/refresh_cache.html', '', 'default_left_menu_li_li.html', '28000', '000');
INSERT INTO `wb_menu` VALUES ('138', '22', '强制刷新数据', '../request/jsp_tab_datatable_get_user?dbs=hr&showColumn=0&rtype=1&act=datatable&forcequery=fromdatabase', '', 'default_left_menu_li_li_blank.html', '10100', '000');

-- ----------------------------
-- Table structure for wb_menu_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_menu_role_relation`;
CREATE TABLE `wb_menu_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menuid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_menu_role_relation
-- ----------------------------
INSERT INTO `wb_menu_role_relation` VALUES ('1', '30', '2');
INSERT INTO `wb_menu_role_relation` VALUES ('2', '13', '2');

-- ----------------------------
-- Table structure for wb_page
-- ----------------------------
DROP TABLE IF EXISTS `wb_page`;
CREATE TABLE `wb_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `zone_uuid` varchar(64) DEFAULT NULL,
  `zone_type` varchar(32) DEFAULT NULL,
  `show_order` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uuid_index` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page
-- ----------------------------
INSERT INTO `wb_page` VALUES ('1', 'erp_dege_no_approval_query', 'erp_dege_no_approval_query', 'port', '0');
INSERT INTO `wb_page` VALUES ('2', 'erp_dege_no_approval_query', 'erp_likun_no_approval', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('3', 'hr_depart', 'hr_depart_query', 'port', '0');
INSERT INTO `wb_page` VALUES ('4', 'hr_depart', 'hr_depart', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('5', 'hr_depart2', 'hr_depart_query', 'port', '0');
INSERT INTO `wb_page` VALUES ('6', 'hr_depart2', 'hr_depart2', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('7', 'erp_qyxxh_attendance_statistics', 'erp_qyxxh_attendance_statistics', 'port', '0');
INSERT INTO `wb_page` VALUES ('8', 'erp_qyxxh_attendance_statistics', 'erp_qyxxh_attendance_statistics', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('9', 'erp_qyxxh_attendance_quanqin', 'erp_qyxxh_attendance_quanqin_query', 'port', '0');
INSERT INTO `wb_page` VALUES ('10', 'erp_qyxxh_attendance_quanqin', 'erp_qyxxh_attendance_quanqin', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('11', 'erp_employee_attendance', 'erp_employee_attendance', 'port', '0');
INSERT INTO `wb_page` VALUES ('12', 'erp_employee_attendance', 'erp_employee_attendance', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('13', 'qyxxh_unfinished_task', 'qyxxh_unfinished_task', 'port', '0');
INSERT INTO `wb_page` VALUES ('14', 'qyxxh_unfinished_task', 'qyxxh_unfinished_task', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('15', 'first_depart_unfinished_task', 'qyxxh_unfinished_task', 'port', '0');
INSERT INTO `wb_page` VALUES ('16', 'first_depart_unfinished_task', 'first_depart_unfinished_task', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('17', 'second_depart_unfinished_task', 'qyxxh_unfinished_task', 'port', '0');
INSERT INTO `wb_page` VALUES ('18', 'second_depart_unfinished_task', 'second_depart_unfinished_task', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('19', 'qyxxh_overtime_task', 'qyxxh_unfinished_task', 'port', '0');
INSERT INTO `wb_page` VALUES ('20', 'qyxxh_overtime_task', 'qyxxh_overtime_task', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('21', 'chart_bar', 'chart_bar', 'chart', '0');
INSERT INTO `wb_page` VALUES ('22', 'chart_curve', 'chart_curve', 'chart', '0');
INSERT INTO `wb_page` VALUES ('23', 'chart_column1', 'chart_column1', 'chart', '0');
INSERT INTO `wb_page` VALUES ('24', 'chart_column2', 'chart_column2', 'chart', '0');
INSERT INTO `wb_page` VALUES ('25', 'chart_pie', 'chart_pie', 'chart', '0');
INSERT INTO `wb_page` VALUES ('26', 'config_db_get_user', 'config_db_get_user', 'port', '0');
INSERT INTO `wb_page` VALUES ('27', 'config_db_get_user', 'config_db_get_user', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('28', 'task_qyxxh_curve_chart', 'task_qyxxh_curve_chart', 'chart', '0');
INSERT INTO `wb_page` VALUES ('29', 'task_first_dept_curve_chart', 'task_first_dept_curve_chart', 'chart', '0');
INSERT INTO `wb_page` VALUES ('30', 'task_second_dept_curve_chart', 'task_second_dept_curve_chart', 'chart', '0');
INSERT INTO `wb_page` VALUES ('31', 'example_config_compent', 'example_config_compent', 'port', '0');
INSERT INTO `wb_page` VALUES ('32', 'task_qyxxh_unfinished_column', 'task_qyxxh_unfinished_column', 'chart', '0');
INSERT INTO `wb_page` VALUES ('33', 'task_first_unfinished_column', 'task_first_unfinished_column', 'chart', '0');
INSERT INTO `wb_page` VALUES ('34', 'task_second_unfinished_column', 'task_second_unfinished_column', 'chart', '0');
INSERT INTO `wb_page` VALUES ('35', 'muti_chart', 'chart_curve', 'chart', '0');
INSERT INTO `wb_page` VALUES ('36', 'muti_chart', 'chart_column1', 'chart', '1');
INSERT INTO `wb_page` VALUES ('37', 'muti_chart', 'chart_column2', 'chart', '2');
INSERT INTO `wb_page` VALUES ('38', 'muti_chart', 'chart_bar', 'chart', '3');
INSERT INTO `wb_page` VALUES ('39', 'muti_chart', 'chart_pie', 'chart', '4');
INSERT INTO `wb_page` VALUES ('40', 'config_db_get_user', 'config_db_get_user_rear_button', 'port', '2');
INSERT INTO `wb_page` VALUES ('41', 'div_dialog_in_html', 'div_dialog_in_html', 'port', '0');
INSERT INTO `wb_page` VALUES ('42', 'div_dialog_in_html', 'div_dialog_in_html', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('43', 'div_dialog_in_html', 'div_dialog_in_html_rear_button', 'port', '2');
INSERT INTO `wb_page` VALUES ('44', 'config_db_get_user2', 'config_db_get_user', 'port', '0');
INSERT INTO `wb_page` VALUES ('45', 'config_db_get_user2', 'config_db_get_user', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('46', 'config_db_get_user2', 'config_db_get_user_rear_button2', 'port', '2');
INSERT INTO `wb_page` VALUES ('47', 'div_dialog_in_html2', 'div_dialog_in_html', 'port', '0');
INSERT INTO `wb_page` VALUES ('48', 'div_dialog_in_html2', 'div_dialog_in_html', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('49', 'div_dialog_in_html2', 'dialog_portal_rear_button', 'port', '2');
INSERT INTO `wb_page` VALUES ('50', 'div_dialog_radio_in_html', 'div_dialog_in_html', 'port', '0');
INSERT INTO `wb_page` VALUES ('51', 'div_dialog_radio_in_html', 'div_dialog_radio_in_html', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('52', 'div_dialog_radio_in_html', 'div_dialog_radio_in_html_rear_button', 'port', '2');
INSERT INTO `wb_page` VALUES ('53', 'datatable_iframe', 'div_dialog_in_html', 'port', '0');
INSERT INTO `wb_page` VALUES ('54', 'datatable_iframe', 'div_dialog_radio_in_html', 'datatable', '1');
INSERT INTO `wb_page` VALUES ('55', 'datatable_iframe', 'datatable_iframe_rear_button', 'port', '2');
INSERT INTO `wb_page` VALUES ('56', 'erp_workflow_monitor', 'erp_workflow_monitor', 'port', '0');
INSERT INTO `wb_page` VALUES ('57', 'erp_workflow_monitor', 'erp_workflow_monitor', 'datatable', '1');

-- ----------------------------
-- Table structure for wb_page_chart
-- ----------------------------
DROP TABLE IF EXISTS `wb_page_chart`;
CREATE TABLE `wb_page_chart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) DEFAULT NULL,
  `excute_uuid` varchar(64) DEFAULT NULL,
  `chartid` varchar(64) DEFAULT NULL,
  `database` varchar(64) DEFAULT NULL,
  `height` varchar(64) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `y_itle` varchar(128) DEFAULT NULL,
  `subtitle` varchar(128) DEFAULT NULL,
  `templet` varchar(64) DEFAULT NULL,
  `tooltip` varchar(128) DEFAULT NULL,
  `func` varchar(64) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page_chart
-- ----------------------------
INSERT INTO `wb_page_chart` VALUES ('1', 'chart_bar', 'curve_chart_climate', 'chart_bar', 'wt', '500px', '月平均气温', '温度 (°C)', '来源: WorldClimate.com', 'default_chart_curve.html', '{valueSuffix: \'°C\'}', 'barChartAjax', '横向柱状图');
INSERT INTO `wb_page_chart` VALUES ('2', 'chart_curve', 'curve_chart_climate', 'chart_curve', 'wt', '500px', '月平均气温', '温度 (°C)', '来源: WorldClimate.com', 'default_chart_curve.html', '{valueSuffix: \'°C\'}', 'curveChartAjax', '曲线图');
INSERT INTO `wb_page_chart` VALUES ('3', 'chart_column1', 'curve_chart_climate', 'chart_column1', 'wt', '500px', '月平均气温', '温度 (°C)', '来源: WorldClimate.com', 'default_chart_curve.html', '{valueSuffix: \'°C\'}', 'columnChartAjax', '柱状图1');
INSERT INTO `wb_page_chart` VALUES ('4', 'chart_column2', 'curve_chart_climate', 'chart_column2', 'wt', '500px', '月平均气温', '温度 (°C)', '来源: WorldClimate.com', 'default_chart_curve.html', '{valueSuffix: \'°C\'}', 'columnChart2Ajax', '柱状图2');
INSERT INTO `wb_page_chart` VALUES ('5', 'chart_pie', 'pie_chart_climate', 'chart_pie', 'wt', '500px', '月平均气温', '温度 (°C)', '来源: WorldClimate.com', 'default_chart_curve.html', '{ formatter: function () { return this.y > 1 ? \'\' + this.point.name + \': \' + this.y + \'%\'  : null; } }', 'pieChartAjax', '饼状图');
INSERT INTO `wb_page_chart` VALUES ('6', 'task_qyxxh_curve_chart', 'task_qyxxh_curve_chart', 'chart_curve', 'task', '500px', '企业信息化任务完成情况', '数量', null, 'default_chart_curve.html', '{valueSuffix: \'\'}', 'curveChartAjax', null);
INSERT INTO `wb_page_chart` VALUES ('7', 'task_first_dept_curve_chart', 'task_first_dept_curve_chart', 'chart_curve', 'task', '500px', '企业信息化一部任务完成情况', '数量', null, 'default_chart_curve.html', '{valueSuffix: \'\'}', 'curveChartAjax', null);
INSERT INTO `wb_page_chart` VALUES ('8', 'task_second_dept_curve_chart', 'task_second_dept_curve_chart', 'chart_curve', 'task', '500px', '企业信息化二部任务完成情况', '数量', null, 'default_chart_curve.html', '{valueSuffix: \'\'}', 'curveChartAjax', null);
INSERT INTO `wb_page_chart` VALUES ('9', 'task_qyxxh_unfinished_column', 'task_qyxxh_unfinished_column?department=1/2464/24/2312', 'chart_column1', 'task', '500px', '企业信息化代办柱状图', null, null, 'default_chart_curve.html', '{valueSuffix: \'个\'}', 'columnChartAjax', '柱状图1');
INSERT INTO `wb_page_chart` VALUES ('10', 'task_first_unfinished_column', 'task_qyxxh_unfinished_column?department=1/2464/24/2312/2314', 'chart_column1', 'task', '500px', '一部代办柱状图', null, null, 'default_chart_curve.html', '{valueSuffix: \'个\'}', 'columnChartAjax', '柱状图1');
INSERT INTO `wb_page_chart` VALUES ('11', 'task_second_unfinished_column', 'task_qyxxh_unfinished_column?department=1/2464/24/2312/2313', 'chart_column1', 'task', '500px', '二部代办柱状图', null, null, 'default_chart_curve.html', '{valueSuffix: \'个\'}', 'columnChartAjax', '柱状图1');

-- ----------------------------
-- Table structure for wb_page_datatables
-- ----------------------------
DROP TABLE IF EXISTS `wb_page_datatables`;
CREATE TABLE `wb_page_datatables` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `desc_groupid` bigint(20) DEFAULT NULL,
  `templet` varchar(64) DEFAULT NULL,
  `js_verify_param` varchar(64) DEFAULT NULL,
  `display_query` varchar(64) DEFAULT NULL,
  `display_import` varchar(64) DEFAULT NULL,
  `display_export` varchar(64) DEFAULT NULL,
  `executeid` bigint(20) NOT NULL DEFAULT '-1',
  `where_group` bigint(11) NOT NULL DEFAULT '-1',
  `myjs` varchar(4096) DEFAULT NULL,
  `dbs` varchar(64) DEFAULT NULL,
  `cache_second` int(11) DEFAULT '0',
  `refresh_time` time DEFAULT NULL,
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page_datatables
-- ----------------------------
INSERT INTO `wb_page_datatables` VALUES ('1', 'jsp_tab_datatable_get_user', 'wb_user', '1', 'default_table.html', null, 'inline-block', 'inline-block', 'inline-block', '-1', '11', null, 'hr', '1000', null, null);
INSERT INTO `wb_page_datatables` VALUES ('2', 'ajax_datatable_get_user', '', null, null, null, null, null, null, '9', '-1', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('3', 'json_datatable_get_user', '', null, null, null, null, null, null, '11', '-1', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('4', 'no_use_96710228753514500', 'personel_main', null, null, null, null, null, null, '-1', '-1', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('5', 'no_use_96718173050503171', 'hrmdepartment', null, null, null, null, null, null, '-1', '12', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('6', 'no_subaccount_agent', '', null, null, null, null, null, null, '22', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('7', 'dege_no_approval', '', null, null, null, null, null, null, '23', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('8', 'agent_avalid', '', null, null, null, null, null, null, '24', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('9', 'leave_workflow', '', null, null, null, null, null, null, '25', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('10', 'likun_approvaled_wk', '', null, null, null, null, null, null, '26', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('11', 'likun_no_approval_wk', '', null, null, null, null, null, null, '27', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('12', 'erp_query_emp', '', null, null, null, null, null, null, '28', '-1', null, 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('13', 'hp_query_emp', '', null, null, null, null, null, null, '29', '-1', null, 'hp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('14', 'oa_query_request', '', null, null, null, null, null, null, '30', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('15', 'oa_query_file', 'DOCIMAGEFILE', '15', 'default_table.html', null, null, null, null, '-1', '25', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('16', 'oa_department_query', '', null, null, null, null, null, null, '31', '-1', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('17', 'oa_department_no_bmfzr', 'hrmdepartment', '17', 'default_table.html', null, null, null, null, '-1', '27', null, 'oa', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('18', 'erp_dege_no_approval', '', '18', 'default_table.html', null, 'inline-block', 'none', 'none', '32', '-1', null, 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('19', 'erp_likun_no_approval', '', '19', 'default_table.html', null, 'inline-block', 'none', 'none', '33', '-1', null, 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('20', 'hp_query_cy_emp', '', null, null, null, null, null, null, '34', '-1', null, 'hp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('21', 'hp_all_emp', '', null, null, null, null, null, null, '35', '-1', null, 'hp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('22', 'hr_depart', 'hr_depart', '22', 'default_table.html', null, 'inline-block', 'none', 'none', '-1', '29', null, 'hp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('23', 'hr_depart2', '', '23', 'default_table.html', null, 'inline-block', 'none', 'none', '36', '-1', null, 'hp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('24', 'erp_qyxxh_attendance_statistics', '', '24', 'default_table.html', null, 'inline-block', 'none', 'none', '37', '-1', null, 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('25', 'erp_qyxxh_attendance_quanqin', '', '25', 'default_table.html', null, 'inline-block', 'none', 'none', '39', '-1', null, 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('26', 'erp_employee_attendance', '', '26', 'default_table.html', 'verifyparam', 'inline-block', 'none', 'none', '40', '30', '$(\"input[name=\'name\']\")[0].value = \"请输入\"\r\nfunction verifyparam(){\r\n	if($(\"input[name=\'name\']\")[0].value == \"\" && $(\"input[name=\'workno\']\")[0].value ==\"\"){\r\n	 	alert(\"员工姓名、工号，不能同时为空，并且精确匹配\");\r\n	 	return false;\r\n	}\r\n\r\n	return true;\r\n}', 'erp', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('27', 'qyxxh_unfinished_task', 'TASK_BASEINFO', '27', 'default_table.html', null, 'inline-block', 'none', 'none', '41', '-1', null, 'task', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('28', 'first_depart_unfinished_task', 'TASK_BASEINFO', '27', 'default_table.html', null, 'inline-block', 'none', 'none', '42', '-1', null, 'task', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('29', 'second_depart_unfinished_task', 'TASK_BASEINFO', '27', 'default_table.html', null, 'inline-block', 'none', 'none', '43', '-1', null, 'task', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('30', 'qyxxh_overtime_task', 'TASK_BASEINFO', '27', 'default_table.html', null, 'inline-block', 'none', 'none', '44', '-1', null, 'task', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('31', 'config_db_get_user', 'wb_user', '1', 'default_table.html', null, 'inline-block', 'inline-block', 'inline-block', '-1', '11', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('32', 'div_dialog_in_html', 'wb_user', '28', 'default_table.html', null, 'inline-block', 'none', 'none', '-1', '11', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('33', 'div_dialog_radio_in_html', 'wb_user', '29', 'default_table.html', null, 'inline-block', 'none', 'none', '-1', '11', null, 'hr', '0', null, null);
INSERT INTO `wb_page_datatables` VALUES ('34', 'erp_workflow_monitor', '', '30', 'default_table.html', null, 'inline-block', 'none', 'none', '71', '-1', null, 'erp', '0', null, null);

-- ----------------------------
-- Table structure for wb_page_datatables_desc
-- ----------------------------
DROP TABLE IF EXISTS `wb_page_datatables_desc`;
CREATE TABLE `wb_page_datatables_desc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `groupid` bigint(20) NOT NULL,
  `checkbox` tinyint(1) NOT NULL,
  `sorting` tinyint(1) NOT NULL,
  `width` varchar(64) NOT NULL,
  `field` varchar(128) NOT NULL,
  `alias` varchar(128) NOT NULL,
  `html` varchar(1024) NOT NULL,
  `tr_template` varchar(64) DEFAULT '',
  `td_template` varchar(64) DEFAULT '',
  `show_order` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page_datatables_desc
-- ----------------------------
INSERT INTO `wb_page_datatables_desc` VALUES ('1', '1', '1', '0', '5%', 'id', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('2', '1', '0', '1', '10%', 'work_code', 'workcode', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('3', '1', '0', '1', '10%', 'name', 'name', '<h3>{name}</h3>', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('4', '1', '0', '1', '10%', 'ch_name', 'chname', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('5', '1', '0', '1', '10%', 'en_name', 'enname', '', '', '', '5');
INSERT INTO `wb_page_datatables_desc` VALUES ('6', '1', '0', '0', '5%', 'item_state', 'item_state', '', '', '', '1200');
INSERT INTO `wb_page_datatables_desc` VALUES ('7', '1', '0', '0', '10%', '', '删除', '<a href=\"javascript:void(0)\" onclick=\"alert(\'删除-->{id}:{name}\')\">删除</a>', '', '', '900');
INSERT INTO `wb_page_datatables_desc` VALUES ('8', '4', '1', '0', '10%', 'id', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('9', '4', '0', '0', '20%', 'work_code', 'workcode', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('10', '4', '0', '0', '20%', 'name', 'name', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('11', '4', '0', '0', '20%', 'ch_name', 'chname', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('12', '4', '0', '0', '20%', 'en_name', 'enname', '', '', '', '4');
INSERT INTO `wb_page_datatables_desc` VALUES ('13', '5', '0', '0', '10%', 'id', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('14', '5', '0', '0', '50%', 'dept_full_name', 'dept_full_name', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('15', '5', '0', '0', '10%', 'departmentname', 'departmentname', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('16', '1', '0', '0', '10%', '', '修改', '<a href=\"javascript:void(0)\" onclick=\"alert(\'修改-->{id}:{name}\')\">修改</a>\r\n', '', '', '1000');
INSERT INTO `wb_page_datatables_desc` VALUES ('17', '1', '0', '0', '10%', '', '<h1>测试</h1>', '<input type=\"TEXT\" value=\"{ch_name}\"></input>', '', '', '1100');
INSERT INTO `wb_page_datatables_desc` VALUES ('18', '1', '0', '1', '10%', 'create_date_time', '创建时间', '', '', '', '700');
INSERT INTO `wb_page_datatables_desc` VALUES ('19', '1', '0', '1', '10%', 'update_date_time', '修改时间', '', '', '', '800');
INSERT INTO `wb_page_datatables_desc` VALUES ('20', '15', '0', '0', '5%', 'DOCID', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('21', '15', '0', '0', '40%', 'IMAGEFILENAME', '附件名称', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('22', '17', '0', '0', '5%', 'id', '部门Id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('23', '17', '0', '0', '5%', 'departmentcode', '部门编码', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('24', '17', '0', '0', '50%', 'dept_full_name', '部门名称', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('25', '18', '0', '1', '15%', 'billCode', '流程编号', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('26', '18', '0', '1', '15%', 'flowSubject', '流程主题', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('27', '18', '0', '1', '5%', 'starterName', '创建人', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('28', '18', '0', '1', '10%', 'flowName', '流程类型', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('29', '18', '0', '1', '10%', 'flowStartTime', '流程创建时间', '', '', '', '4');
INSERT INTO `wb_page_datatables_desc` VALUES ('30', '18', '0', '1', '10%', 'taskCreateTime', '任务到达时间', '', '', '', '5');
INSERT INTO `wb_page_datatables_desc` VALUES ('31', '19', '0', '1', '15%', 'billCode', '流程编号', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('32', '19', '0', '1', '15%', 'flowSubject', '流程主题', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('33', '19', '0', '1', '5%', 'starterName', '创建人', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('34', '19', '0', '1', '10%', 'flowName', '流程类型', '', '', '', '4');
INSERT INTO `wb_page_datatables_desc` VALUES ('35', '19', '0', '1', '10%', 'flowStartTime', '流程创建时间', '', '', '', '5');
INSERT INTO `wb_page_datatables_desc` VALUES ('36', '19', '0', '1', '10%', 'taskCreateTime', '任务到达时间', '', '', '', '6');
INSERT INTO `wb_page_datatables_desc` VALUES ('37', '22', '0', '0', '5%', 'DEPT_ID', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('38', '22', '0', '0', '5%', 'DEPT_CODE', '编码', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('39', '22', '0', '0', '80%', 'DEPT_FULL_NAME', '全路径', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('40', '23', '0', '0', '5%', '', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('41', '23', '0', '0', '5%', '', '编码', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('42', '23', '0', '0', '80%', '', '全路径', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('43', '23', '0', '0', '10%', '', '部门负责人', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('44', '24', '0', '0', '10%', '', '工号', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('45', '24', '0', '0', '10%', '', '姓名', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('46', '24', '0', '1', '10%', '', '总工时', '', '', '', '100');
INSERT INTO `wb_page_datatables_desc` VALUES ('47', '24', '0', '0', '5%', '', '应出勤天数', '', '', '', '110');
INSERT INTO `wb_page_datatables_desc` VALUES ('48', '24', '0', '1', '5%', '', '实际出勤天数', '', '', '', '120');
INSERT INTO `wb_page_datatables_desc` VALUES ('49', '24', '0', '1', '5%', '', '日均工时', '', '', '', '130');
INSERT INTO `wb_page_datatables_desc` VALUES ('50', '24', '0', '1', '5%', '', '加班工时', '', '', '', '140');
INSERT INTO `wb_page_datatables_desc` VALUES ('51', '25', '0', '1', '5%', '', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('52', '25', '0', '1', '10%', '', '姓名', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('53', '25', '0', '1', '10%', '', '工号', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('54', '25', '0', '1', '10%', '', '实际出勤天数', '', '', '', '300');
INSERT INTO `wb_page_datatables_desc` VALUES ('55', '25', '0', '1', '10%', '', '应出勤天数', '', '', '', '400');
INSERT INTO `wb_page_datatables_desc` VALUES ('56', '26', '0', '0', '5%', '', '姓名', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('57', '26', '0', '0', '7%', '', '日期', '', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('58', '26', '0', '0', '5%', '', '星期', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('59', '26', '0', '0', '10%', '', '上班时间', '', '', '', '3');
INSERT INTO `wb_page_datatables_desc` VALUES ('60', '26', '0', '0', '10%', '', '下班时间', '', '', '', '4');
INSERT INTO `wb_page_datatables_desc` VALUES ('61', '26', '0', '0', '5%', '', '出勤时长', '', '', '', '5');
INSERT INTO `wb_page_datatables_desc` VALUES ('62', '26', '0', '0', '5%', '', '状态', '', '', '', '6');
INSERT INTO `wb_page_datatables_desc` VALUES ('63', '26', '0', '0', '15%', '', '备注', '', '', '', '7');
INSERT INTO `wb_page_datatables_desc` VALUES ('64', '26', '0', '0', '5%', '', '出勤天数', '', '', '', '8');
INSERT INTO `wb_page_datatables_desc` VALUES ('65', '24', '0', '0', '5%', '', '年份', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('66', '24', '0', '0', '5%', '', '季度', '', '', '', '20');
INSERT INTO `wb_page_datatables_desc` VALUES ('67', '24', '0', '0', '5%', '', '月度', '', '', '', '30');
INSERT INTO `wb_page_datatables_desc` VALUES ('68', '25', '0', '0', '5%', '', '年份', '', '', '', '100');
INSERT INTO `wb_page_datatables_desc` VALUES ('69', '25', '0', '0', '5%', '', '季度', '', '', '', '110');
INSERT INTO `wb_page_datatables_desc` VALUES ('70', '25', '0', '0', '5%', '', '月度', '', '', '', '120');
INSERT INTO `wb_page_datatables_desc` VALUES ('71', '27', '0', '0', '8%', '', 'ID', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('72', '27', '0', '0', '50%', '', '名称', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('73', '27', '0', '1', '15%', '', '项目', '', '', '', '20');
INSERT INTO `wb_page_datatables_desc` VALUES ('74', '27', '0', '1', '5%', '', '创建人', '', '', '', '30');
INSERT INTO `wb_page_datatables_desc` VALUES ('75', '27', '0', '1', '5%', '', '处理人', '', '', '', '40');
INSERT INTO `wb_page_datatables_desc` VALUES ('76', '27', '0', '1', '8%', '', '开始时间', '', '', '', '50');
INSERT INTO `wb_page_datatables_desc` VALUES ('77', '27', '0', '1', '8%', '', '完成时间', '', '', '', '60');
INSERT INTO `wb_page_datatables_desc` VALUES ('78', '28', '1', '0', '5%', 'id', 'id', '', null, null, '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('79', '28', '0', '0', '10%', 'work_code', 'workcode', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('80', '28', '0', '1', '10%', 'name', 'name', '', '', '', '20');
INSERT INTO `wb_page_datatables_desc` VALUES ('81', '-1', '0', '1', '5%', '', '创建人', '', '', '', '30');
INSERT INTO `wb_page_datatables_desc` VALUES ('82', '-1', '0', '1', '5%', '', '处理人', '', '', '', '40');
INSERT INTO `wb_page_datatables_desc` VALUES ('83', '-1', '0', '1', '8%', '', '开始时间', '', '', '', '50');
INSERT INTO `wb_page_datatables_desc` VALUES ('84', '-1', '0', '1', '8%', '', '完成时间', '', '', '', '60');
INSERT INTO `wb_page_datatables_desc` VALUES ('85', '-1', '0', '1', '8%', '', 'ID', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('86', '-1', '0', '1', '50%', '', '名称', '', '', '', '20');
INSERT INTO `wb_page_datatables_desc` VALUES ('87', '-1', '0', '1', '15%', '', '项目', '', '', '', '30');
INSERT INTO `wb_page_datatables_desc` VALUES ('88', '-1', '0', '1', '5%', '', '创建人', '', '', '', '40');
INSERT INTO `wb_page_datatables_desc` VALUES ('89', '-1', '0', '1', '5%', '', '处理人', '', '', '', '50');
INSERT INTO `wb_page_datatables_desc` VALUES ('90', '-1', '0', '1', '8%', '', '开始时间', '', '', '', '60');
INSERT INTO `wb_page_datatables_desc` VALUES ('91', '-1', '0', '1', '8%', '', '完成时间', '', '', '', '70');
INSERT INTO `wb_page_datatables_desc` VALUES ('92', '-1', '0', '1', '8%', '', 'ID', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('93', '-1', '0', '1', '50%', '', '名称', '', '', '', '20');
INSERT INTO `wb_page_datatables_desc` VALUES ('94', '-1', '0', '1', '15%', '', '项目', '', '', '', '30');
INSERT INTO `wb_page_datatables_desc` VALUES ('95', '-1', '0', '1', '5%', '', '创建人', '', '', '', '40');
INSERT INTO `wb_page_datatables_desc` VALUES ('96', '-1', '0', '1', '5%', '', '处理人', '', '', '', '50');
INSERT INTO `wb_page_datatables_desc` VALUES ('97', '-1', '0', '1', '8%', '', '开始时间', '', '', '', '50');
INSERT INTO `wb_page_datatables_desc` VALUES ('98', '-1', '0', '1', '8%', '', '完成时间', '', '', '', '70');
INSERT INTO `wb_page_datatables_desc` VALUES ('99', '-1', '0', '0', '', '', '', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('100', '29', '1', '0', '5%', 'id', 'id', '', 'default_table_th_radio.html', 'default_table_td_radio.html', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('101', '29', '0', '0', '10%', 'work_code', 'workcode', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('102', '29', '0', '0', '10%', 'name', 'name', '', '', '', '10');
INSERT INTO `wb_page_datatables_desc` VALUES ('103', '30', '0', '0', '5%', 'id', 'id', '', '', '', '0');
INSERT INTO `wb_page_datatables_desc` VALUES ('104', '30', '0', '0', '5%', 'billCode', '表单号', '<a href=\"http://apollo.mole.com/erp/personal/showERPById.do?billCode={billCode}\">{billCode} </a>', '', '', '1');
INSERT INTO `wb_page_datatables_desc` VALUES ('105', '30', '0', '0', '10%', 'flowName', '流程类型', '', '', '', '2');
INSERT INTO `wb_page_datatables_desc` VALUES ('106', '30', '0', '0', '10%', 'flowSubject', '流程主题', '', '', '', '4');
INSERT INTO `wb_page_datatables_desc` VALUES ('107', '30', '0', '0', '10%', 'starterName', '申请人', '', '', '', '3');

-- ----------------------------
-- Table structure for wb_page_portal
-- ----------------------------
DROP TABLE IF EXISTS `wb_page_portal`;
CREATE TABLE `wb_page_portal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) NOT NULL,
  `port_group_id` bigint(20) DEFAULT '0',
  `content` varchar(64) DEFAULT NULL,
  `params` varchar(128) DEFAULT NULL,
  `myjs` varchar(4096) DEFAULT NULL,
  `lable_column` int(11) DEFAULT NULL,
  `item_column` int(11) DEFAULT NULL,
  `portal_templet` varchar(64) DEFAULT NULL,
  `group_templet` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page_portal
-- ----------------------------
INSERT INTO `wb_page_portal` VALUES ('1', 'erp_dege_no_approval_query', '1', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('2', 'hr_depart_query', '2', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('3', 'erp_qyxxh_attendance_quanqin_query', '3', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('4', 'erp_employee_attendance', '3', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('5', 'erp_qyxxh_attendance_statistics', '3', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('6', 'erp_qyxxh_attendance_column', '6', 'groups', null, 'function verifyparam(){\r\n	if(getSelectItemByName(\'year\') == \"\" || getSelectItemByName(\'month\') ==\"\"){\r\n	 	alert(\"必须选择年份和月份\");\r\n	 	return false;\r\n	}\r\n\r\n	return true;\r\n}', '1', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('7', 'qyxxh_unfinished_task', '7', 'groups', null, null, '1', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('8', 'config_db_get_user', '8', 'groups', null, null, '1', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('9', 'example_config_compent', '9', 'groups', null, null, '2', '3', 'default_portal.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('10', 'config_db_get_user_rear_button', '-1', 'groups', '{\"datatableUuid\":\"tconfig_db_get_user\"}', null, '0', '0', 'default_portal_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('11', 'config_db_get_user_rear_button2', '-1', 'groups', '{\"datatableUuid\":\"tconfig_db_get_user\"}', null, '1', '3', 'default_portal_rear_button2.html', null);
INSERT INTO `wb_page_portal` VALUES ('12', 'div_dialog_in_html_rear_button', '-1', null, '{\"datatableUuid\":\"tdiv_dialog_in_html\"}', null, '0', '0', 'div_dialog_in_html_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('13', 'div_dialog_in_html', '10', 'groups', null, null, '1', '5', 'default_portal2.html', 'default_groups.html');
INSERT INTO `wb_page_portal` VALUES ('14', 'div_dialog_in_html_rear_button', '10', null, '{\"datatableUuid\":\"tdiv_dialog_in_html\"}', null, '0', '0', 'div_dialog_in_html_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('15', 'dialog_portal_rear_button', '-1', null, '{\"datatableUuid\":\"tdiv_dialog_in_html\"}', null, '0', '0', 'dialog_portal_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('16', 'div_dialog_radio_in_html_rear_button', '-1', null, '{\"datatableUuid\":\"tdiv_dialog_radio_in_html\"}', null, '0', '0', 'dialog_portal_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('17', 'datatable_iframe_rear_button', '-1', null, '{\"datatableUuid\":\"tdiv_dialog_radio_in_html\"}', null, '0', '0', 'datatable_iframe_rear_button.html', null);
INSERT INTO `wb_page_portal` VALUES ('18', 'erp_workflow_monitor', '11', 'groups', null, null, '1', '3', 'default_portal.html', 'default_groups.html');

-- ----------------------------
-- Table structure for wb_page_portal_item
-- ----------------------------
DROP TABLE IF EXISTS `wb_page_portal_item`;
CREATE TABLE `wb_page_portal_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `port_group_id` bigint(20) NOT NULL,
  `lable` varchar(128) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `content` varchar(64) DEFAULT NULL,
  `selectitem_groupid` int(11) DEFAULT '-1',
  `dbs` varchar(32) DEFAULT '',
  `data_source_sql` varchar(1024) DEFAULT '',
  `style` varchar(128) DEFAULT NULL,
  `templet` varchar(64) DEFAULT '',
  `item_templet` varchar(64) DEFAULT '',
  `css_class` varchar(64) DEFAULT NULL,
  `myjson` varchar(1024) DEFAULT NULL,
  `lable_column` int(11) DEFAULT NULL,
  `item_column` int(11) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `show_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_page_portal_item
-- ----------------------------
INSERT INTO `wb_page_portal_item` VALUES ('1', '1', '流程编号', 'billCode', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('2', '1', '申请人', 'starterName', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('3', '1', '流程类型', 'flowName', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '1', '0');
INSERT INTO `wb_page_portal_item` VALUES ('4', '2', '部门名称', 'name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('5', '3', '姓名', 'name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('6', '3', '工号', 'workno', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('7', '-1', '姓名', 'name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('8', '-1', '工号', 'workno', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('9', '-1', '年份', 'year', 'items', '1', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '1', '0');
INSERT INTO `wb_page_portal_item` VALUES ('10', '-1', '月份', 'month', 'items', '3', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '1', '1');
INSERT INTO `wb_page_portal_item` VALUES ('11', '-1', '季度', 'quarter', 'items', '2', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '2', '0');
INSERT INTO `wb_page_portal_item` VALUES ('12', '-1', '姓名', 'name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '1', '0');
INSERT INTO `wb_page_portal_item` VALUES ('13', '-1', '工号', 'workno', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '1', '2');
INSERT INTO `wb_page_portal_item` VALUES ('14', '-1', '年份', 'year', 'items', '1', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '2', '3');
INSERT INTO `wb_page_portal_item` VALUES ('15', '-1', '月份', 'month', 'items', '3', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '2', '3');
INSERT INTO `wb_page_portal_item` VALUES ('16', '-1', '季度', 'quarter', 'items', '2', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '3', '4');
INSERT INTO `wb_page_portal_item` VALUES ('17', '6', '年份', 'year', 'items', '-1', 'system', 'select * from wb_select_item where selectitem_groupid=1 order by show_order', null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('18', '6', '月份', 'month', 'items', '3', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('19', '6', '查询', 'query', null, '-1', '', null, null, 'default_button.html', null, null, '{\r\n            \"id\":\"query\",\r\n            \"color\":\"blue\",\r\n            \"onClick\":\"onClick=\'query(verifyparam)\'\"                 \r\n        }    ', null, null, '0', '2');
INSERT INTO `wb_page_portal_item` VALUES ('20', '3', '年份', 'year', 'items', '1', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '1', '2');
INSERT INTO `wb_page_portal_item` VALUES ('21', '3', '月份', 'month', 'items', '3', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '1', '3');
INSERT INTO `wb_page_portal_item` VALUES ('22', '3', '季度', 'quarter', 'items', '2', '', null, null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '2', '4');
INSERT INTO `wb_page_portal_item` VALUES ('23', '7', '名称', 'task_name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '0');
INSERT INTO `wb_page_portal_item` VALUES ('24', '7', '创建人', 'task_appler', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('25', '7', '处理人', 'task_accept', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '2');
INSERT INTO `wb_page_portal_item` VALUES ('26', '8', '用户ID', 'id', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('27', '8', '工号', 'work_code', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '2');
INSERT INTO `wb_page_portal_item` VALUES ('28', '8', '姓名', 'name', null, '-1', '', null, null, 'default_text.html', null, 'param', null, null, null, '0', '3');
INSERT INTO `wb_page_portal_item` VALUES ('29', '9', '姓名(文本框)', 'name', null, '-1', '', '', null, 'default_text.html', '', 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('30', '9', '年份（在sys_select_item表中配置值）', 'year', 'items', '1', '', '', null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '0', '2');
INSERT INTO `wb_page_portal_item` VALUES ('31', '9', '月份（使用sql获取值）', 'month', 'items', '-1', 'system', 'select * from wb_select_item where selectitem_groupid=3 order by show_order', null, 'default_select.html', 'default_select_item.html', 'param', null, null, null, '1', '1');
INSERT INTO `wb_page_portal_item` VALUES ('32', '9', '预约日期', 'start_end_date', null, '-1', '', '', 'date-picker', 'default_daterange.html', '', 'param', null, null, null, '1', '2');
INSERT INTO `wb_page_portal_item` VALUES ('33', '9', '预约时间', 'start_end_time', null, '-1', '', '', 'form_datetime', 'default_daterange.html', '', 'param', null, null, null, '2', '1');
INSERT INTO `wb_page_portal_item` VALUES ('34', '9', '修改时间', 'update_time', null, '-1', '', '', null, 'default_time.html', '', 'param', null, null, null, '2', '2');
INSERT INTO `wb_page_portal_item` VALUES ('35', '9', '开始时间', 'start_time', null, '-1', '', '', 'form_datetime', 'default_datetimepicker.html', '', 'param', null, null, null, '3', '1');
INSERT INTO `wb_page_portal_item` VALUES ('36', '9', '开始日期', 'start_date', null, '-1', '', '', 'date-picker', 'default_datetimepicker.html', '', 'param', null, null, null, '3', '2');
INSERT INTO `wb_page_portal_item` VALUES ('37', '9', '人员标签1', 'person_mark1', 'items', '4', '', '', null, 'default_checkbox.html', 'default_checkbox_item.html', 'param', null, null, null, '4', '1');
INSERT INTO `wb_page_portal_item` VALUES ('38', '9', '人员标签2', 'person_mark2', 'items', '4', '', '', null, 'default_radio.html', 'default_radio_item.html', 'param', null, null, null, '4', '2');
INSERT INTO `wb_page_portal_item` VALUES ('39', '9', '人员标签3', 'person_mark3', 'items', '4', '', '', 'mt-checkbox-list', 'default_checkbox.html', 'default_checkbox_item.html', 'param', null, null, null, '5', '1');
INSERT INTO `wb_page_portal_item` VALUES ('40', '9', '人员标签4', 'person_mark5', 'items', '4', '', '', 'mt-radio-list', 'default_radio.html', 'default_radio_item.html', null, null, null, null, '5', '2');
INSERT INTO `wb_page_portal_item` VALUES ('41', '9', '地点1', 'locality1', 'items', '5', '', '', null, 'default_select.html', 'default_select_item.html', null, null, null, null, '6', '1');
INSERT INTO `wb_page_portal_item` VALUES ('42', '9', '地点2', 'locality2', 'items', '5', '', '', null, 'default_multselect.html', 'default_multselect_item.html', null, null, null, null, '6', '1');
INSERT INTO `wb_page_portal_item` VALUES ('43', '10', '工号', 'work_code', null, '-1', '', '', null, 'default_text.html', '', 'param', null, null, null, '0', '1');
INSERT INTO `wb_page_portal_item` VALUES ('44', '10', '姓名', 'name', null, '-1', '', '', null, 'default_text.html', '', 'param', null, null, null, '0', '2');
INSERT INTO `wb_page_portal_item` VALUES ('45', '11', '姓名', 'name', null, '-1', '', '', null, 'default_text.html', '', 'param', null, null, null, '0', '1');

-- ----------------------------
-- Table structure for wb_params
-- ----------------------------
DROP TABLE IF EXISTS `wb_params`;
CREATE TABLE `wb_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `value` varchar(128) NOT NULL,
  `type` int(11) NOT NULL,
  `desc` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_params
-- ----------------------------
INSERT INTO `wb_params` VALUES ('1', 'useSysCacheFlag', '0', '0', 'type,0:整数（long），1:浮点数数(double)，2:字符串。\n是否启用系统cache，启用后国际化、权限等只在系统启动时从数据库加载一次。');
INSERT INTO `wb_params` VALUES ('2', 'mainurl', '/myweb/request/', '2', '发布的请求主地址');
INSERT INTO `wb_params` VALUES ('3', 'langue', '1', '0', '默认的语言');
INSERT INTO `wb_params` VALUES ('4', 'usePrivilege', '1', '0', '是否使用权限管理');
INSERT INTO `wb_params` VALUES ('5', 'useProxy', '0', '0', '是否使用代理转发');
INSERT INTO `wb_params` VALUES ('6', 'homeUrl1', '/myweb/my/', '2', '首页地址');
INSERT INTO `wb_params` VALUES ('7', 'homeUrl2', '/myweb/my/index.jsp', '2', '首页地址');
INSERT INTO `wb_params` VALUES ('8', 'loginUrl', '/myweb/my/page_user_login_1.html', '2', '登录页');
INSERT INTO `wb_params` VALUES ('9', 'useSelectCahce', '1', '0', '查询缓存');
INSERT INTO `wb_params` VALUES ('10', 'securityKey', '123456', '2', '后台请求时的安全秘钥');
INSERT INTO `wb_params` VALUES ('11', 'useColumnPrivilege', '1', '0', '是否启用字段权限控制');
INSERT INTO `wb_params` VALUES ('12', 'testServerUrl', 'http://test.mole.com:8080', '2', '从其他服务器获取数据时的测试地址');
INSERT INTO `wb_params` VALUES ('13', 'useServerPrivilege', '1', '0', '是否启用服务验证权限');
INSERT INTO `wb_params` VALUES ('14', 'refreshResSeconds', '5', '0', '配置表整体刷新的时间差，例如wb_params、wb_res、wb_res_role_relation');
INSERT INTO `wb_params` VALUES ('15', 'refreshTablesSeconds', '5', '0', 'wb_res表的刷新秒数');

-- ----------------------------
-- Table structure for wb_res
-- ----------------------------
DROP TABLE IF EXISTS `wb_res`;
CREATE TABLE `wb_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) NOT NULL,
  `is_white_list` tinyint(4) NOT NULL,
  `login_permite` tinyint(4) NOT NULL,
  `priority` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `desc` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_res
-- ----------------------------
INSERT INTO `wb_res` VALUES ('1', '/myweb/my/', '0', '1', '0', 'ajax_datatables', 'ajax请求datatables数据');
INSERT INTO `wb_res` VALUES ('2', '/myweb/my/page_user_login_1.html', '1', '0', '100', 'jsp_datatables', 'jsp标签生成datatable及数据');
INSERT INTO `wb_res` VALUES ('3', '/myweb/my/datatables/jsp_tab_datatable.jsp', '0', '0', '50', 'ajax_i18n', '通过ajax实现国际化');
INSERT INTO `wb_res` VALUES ('4', '/myweb/assets/', '1', '0', '100', 'jsp_i18n', '通过jsp标签实现国际化');
INSERT INTO `wb_res` VALUES ('5', '/myweb/myassets/', '1', '0', '100', 'requestReurnList', '返回list');
INSERT INTO `wb_res` VALUES ('6', '/myweb/my/index.jsp', '0', '1', '100', 'button_jsp_tab', '按钮权限');
INSERT INTO `wb_res` VALUES ('7', '/myweb/request/', '0', '1', '0', 'jsp_tab_oracle', 'oracle分页');
INSERT INTO `wb_res` VALUES ('8', '/myweb/request/96700059831238657', '0', '0', '50', '', '');
INSERT INTO `wb_res` VALUES ('9', '/myweb/my/components_select2.jsp', '1', '0', '100', '', '');
INSERT INTO `wb_res` VALUES ('10', '/myweb/my/datatables/pop_dialog.html', '1', '0', '200', '', '');
INSERT INTO `wb_res` VALUES ('11', '/myweb/request/div_dialog_in_html?dbs=hr&rtype=1&act=page', '1', '0', '200', '', '');

-- ----------------------------
-- Table structure for wb_res_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_res_role_relation`;
CREATE TABLE `wb_res_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_res_role_relation
-- ----------------------------
INSERT INTO `wb_res_role_relation` VALUES ('1', '3', '2');
INSERT INTO `wb_res_role_relation` VALUES ('2', '8', '2');
INSERT INTO `wb_res_role_relation` VALUES ('3', '1', '2');
INSERT INTO `wb_res_role_relation` VALUES ('4', '3', '1');

-- ----------------------------
-- Table structure for wb_res_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_res_user_relation`;
CREATE TABLE `wb_res_user_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_res_user_relation
-- ----------------------------
INSERT INTO `wb_res_user_relation` VALUES ('1', '4', '1');
INSERT INTO `wb_res_user_relation` VALUES ('2', '4', '2');
INSERT INTO `wb_res_user_relation` VALUES ('3', '6', '1');
INSERT INTO `wb_res_user_relation` VALUES ('4', '6', '2');
INSERT INTO `wb_res_user_relation` VALUES ('5', '7', '2');

-- ----------------------------
-- Table structure for wb_role
-- ----------------------------
DROP TABLE IF EXISTS `wb_role`;
CREATE TABLE `wb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `desc` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_role
-- ----------------------------
INSERT INTO `wb_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `wb_role` VALUES ('2', 'develope', '开发人员');

-- ----------------------------
-- Table structure for wb_router
-- ----------------------------
DROP TABLE IF EXISTS `wb_router`;
CREATE TABLE `wb_router` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `regex` varchar(256) NOT NULL,
  `remote_prefix_url` varchar(256) NOT NULL,
  `remote_ip` varchar(64) NOT NULL,
  `remote_port` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_router
-- ----------------------------
INSERT INTO `wb_router` VALUES ('1', 'http://.*?/myweb/metronic', 'http://ittest.mole.com:8080/metronic', '10.1.1.206', '8080');

-- ----------------------------
-- Table structure for wb_select_item
-- ----------------------------
DROP TABLE IF EXISTS `wb_select_item`;
CREATE TABLE `wb_select_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `selectitem_groupid` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `value` varchar(128) DEFAULT NULL,
  `caption` varchar(128) DEFAULT NULL,
  `templet` varchar(64) DEFAULT NULL,
  `show_order` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_select_item
-- ----------------------------
INSERT INTO `wb_select_item` VALUES ('1', '1', 'y2014', '2014', '2014', 'default_select_item.html', '0');
INSERT INTO `wb_select_item` VALUES ('2', '1', 'y2015', '2015', '2015', 'default_select_item.html', '1');
INSERT INTO `wb_select_item` VALUES ('3', '1', 'y2016', '2016', '2016', 'default_select_item.html', '2');
INSERT INTO `wb_select_item` VALUES ('4', '1', 'y2017', '2017', '2017', 'default_select_item.html', '3');
INSERT INTO `wb_select_item` VALUES ('5', '2', '  ', '  ', '  ', 'default_select_item.html', '0');
INSERT INTO `wb_select_item` VALUES ('6', '2', 'quarter1', '1', '1', 'default_select_item.html', '1');
INSERT INTO `wb_select_item` VALUES ('7', '2', 'quarter2', '2', '2', 'default_select_item.html', '2');
INSERT INTO `wb_select_item` VALUES ('8', '2', 'quarter3', '3', '3', 'default_select_item.html', '3');
INSERT INTO `wb_select_item` VALUES ('9', '2', 'quarter4', '4', '4', 'default_select_item.html', '4');
INSERT INTO `wb_select_item` VALUES ('10', '3', '  ', '  ', '  ', 'default_select_item.html', '0');
INSERT INTO `wb_select_item` VALUES ('11', '3', 'month01', '01', '01', 'default_select_item.html', '1');
INSERT INTO `wb_select_item` VALUES ('12', '3', 'month02', '02', '02', 'default_select_item.html', '2');
INSERT INTO `wb_select_item` VALUES ('13', '3', 'month03', '03', '03', 'default_select_item.html', '3');
INSERT INTO `wb_select_item` VALUES ('14', '3', 'month04', '04', '04', 'default_select_item.html', '4');
INSERT INTO `wb_select_item` VALUES ('15', '3', 'month05', '05', '05', 'default_select_item.html', '5');
INSERT INTO `wb_select_item` VALUES ('16', '3', 'month06', '06', '06', 'default_select_item.html', '6');
INSERT INTO `wb_select_item` VALUES ('17', '3', 'month07', '07', '07', 'default_select_item.html', '7');
INSERT INTO `wb_select_item` VALUES ('18', '3', 'month08', '08', '08', 'default_select_item.html', '8');
INSERT INTO `wb_select_item` VALUES ('19', '3', 'month09', '09', '09', 'default_select_item.html', '9');
INSERT INTO `wb_select_item` VALUES ('20', '3', 'month10', '10', '10', 'default_select_item.html', '10');
INSERT INTO `wb_select_item` VALUES ('21', '3', 'month11', '11', '11', 'default_select_item.html', '11');
INSERT INTO `wb_select_item` VALUES ('22', '3', 'month12', '12', '12', 'default_select_item.html', '12');
INSERT INTO `wb_select_item` VALUES ('23', '4', 'brave', 'brave', '勇敢', null, '1');
INSERT INTO `wb_select_item` VALUES ('24', '4', 'loyal', 'loyal', '忠诚', null, '2');
INSERT INTO `wb_select_item` VALUES ('25', '4', 'noble', 'noble', '高尚', null, '3');
INSERT INTO `wb_select_item` VALUES ('26', '5', 'nverguo', 'nverguo', '女儿国', null, '1');
INSERT INTO `wb_select_item` VALUES ('27', '5', 'gaolaozhuang', 'gaolaozhuang', '高老庄', null, '2');
INSERT INTO `wb_select_item` VALUES ('28', '5', 'liushahe', 'liushahe', '流沙河', null, '3');

-- ----------------------------
-- Table structure for wb_user
-- ----------------------------
DROP TABLE IF EXISTS `wb_user`;
CREATE TABLE `wb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `work_code` varchar(16) NOT NULL,
  `name` varchar(48) NOT NULL,
  `ch_name` varchar(48) NOT NULL,
  `en_name` varchar(48) NOT NULL,
  `create_date_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `update_date_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `item_state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_user
-- ----------------------------
INSERT INTO `wb_user` VALUES ('1', 'sunwukong', 'CY000001', '孙悟空', '孙悟空', 'sunwukong', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('2', 'zhubajie', 'CY000002', '猪八戒', '猪八戒', 'zhubajie', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('3', 'shaheshang', 'CY000003', '沙和尚', '沙和尚', 'shaheshang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('4', 'bailongma', 'CY000004', '白龙马', '白龙马', 'bailongma', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('5', 'tangzeng', 'CY000005', '唐曾', '唐三藏', 'tangzeng', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('6', 'yuhuangdadi', 'CY000006', '玉皇大帝', '玉皇大帝', 'yuhuangdadi', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('7', 'wangmuniangniang', 'CY000007', '王母娘娘', '王母娘娘', 'wangmuniangniang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('8', 'lijing', 'CY000008', '李靖', '李靖', 'lijing', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('9', 'nezha', 'CY000009', '哪吒', '哪吒', 'nezha', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('10', 'change', 'CY000010', '嫦娥', '嫦娥', 'change', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('11', 'taishanglaojun', 'CY000011', '太上老君', '太上老君', 'taishanglaojun', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('12', 'taiyizhenren', 'CY000012', '太乙真人', '太乙真人', 'taiyizhenren', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('13', 'donghailongwang', 'CY000013', '东海龙王', '东海龙王', 'donghailongwang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('14', 'nanhailongwang', 'CY000014', '南海龙王', '南海龙王', 'nanhailongwang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('15', 'xihailongwang', 'CY000015', '西海龙王', '西海龙王', 'xihailongwang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('16', 'beihailongwang', 'CY000016', '北海龙王', '北海龙王', 'beihailongwang', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('17', 'taibaijinxing', 'CY000017', '太白金星', '太白金星', 'taibaijinxing', '2017-12-01 15:12:43', '2017-12-01 15:12:43', '1');
INSERT INTO `wb_user` VALUES ('18', 'mudexingguan', 'CY000018', '木德星官', '木德星官', 'mudexingguan', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('19', 'chijiaodaxian', 'CY000019', '赤脚大仙', '赤脚大仙', 'chijiaodaxian', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('20', 'jinxigui', 'CY000020', '精细鬼', '精细鬼', 'jinxigui', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('21', 'linglichong', 'CY000021', '伶俐虫', '伶俐虫', 'linglichong', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('22', 'baigujing', 'CY000022', '白骨精 ', '白骨精 ', 'baigujing', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('23', 'huangfenggui', 'CY000023', '黄风怪', '黄风怪', 'huangfenggui', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('24', 'niumowang', 'CY000024', '牛魔王', '牛魔王', 'niumowang', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('25', 'liuernihou', 'CY000025', '六耳猕猴 ', '六耳猕猴 ', 'liuernihou', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');
INSERT INTO `wb_user` VALUES ('29', 'qingmaoshiziguai', 'CY000026', '青毛狮子怪', '青毛狮子怪', 'qingmaoshiziguai', '2017-12-01 15:12:44', '2017-12-01 15:12:44', '1');

-- ----------------------------
-- Table structure for wb_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wb_user_role_relation`;
CREATE TABLE `wb_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wb_user_role_relation
-- ----------------------------
INSERT INTO `wb_user_role_relation` VALUES ('1', '1', '1');
INSERT INTO `wb_user_role_relation` VALUES ('2', '2', '2');
DROP TRIGGER IF EXISTS `sys_user_insert_before_tri`;
DELIMITER ;;
CREATE TRIGGER `sys_user_insert_before_tri` BEFORE INSERT ON `wb_user` FOR EACH ROW BEGIN

SET new.create_date_time = now();
SET new.update_date_time = new.create_date_time;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `sys_user_update_before_tri`;
DELIMITER ;;
CREATE TRIGGER `sys_user_update_before_tri` BEFORE UPDATE ON `wb_user` FOR EACH ROW BEGIN

SET new.update_date_time = now();
END
;;
DELIMITER ;
