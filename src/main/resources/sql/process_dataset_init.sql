
-- 流程 - 数据集 关联表
DROP TABLE IF EXISTS `process_dataset`;
CREATE TABLE `process_dataset` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `creator` varchar(255) DEFAULT NULL COMMENT '数据集申请人',
  `process_status` int(11) unsigned NOT NULL COMMENT '流程状态',
  `dataset_id` int(11) NOT NULL  COMMENT '数据集ID',
  `proc_inst_id` varchar(255) DEFAULT NULL COMMENT '流程实例ID',
  `dataset_name` varchar(255) DEFAULT NULL COMMENT '数据集名称',
  `priority` int(11) DEFAULT NULL COMMENT '申请任务的优先级',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='数据集';


-- 任务处理记录信息，方便扩展和展示数据
DROP TABLE IF EXISTS `process_task`;
CREATE TABLE `process_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` varchar(255) NOT NULL COMMENT 'taskid',
  `task_name` varchar(255) NOT NULL COMMENT 'task名称',
  `proc_inst_id` varchar(255) DEFAULT NULL COMMENT '流程实例ID',
  `approval_user` varchar(255) NOT NULL COMMENT '审批人',
  `approval_result` int(11) unsigned NOT NULL COMMENT '审批结果：1 同意,2 驳回,3 结束流程',
  `approval_comment` text DEFAULT NULL COMMENT '审批意见',
  `approval_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='任务处理记录';




