INSERT INTO sys_menu ( id, created_by, created_time, disabled, modified_by, modified_time, version, menus_name, url) VALUES
(1, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Dashboard', NULL),
(2, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Dashboard V1', '/Dashboard/V1'),
(3, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Dashboard V2', '/Dashboard/V2'),
(4, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Utility', NULL),
(5, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Setting User', NULL),
(6, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'User', '../../admin/v1/user'),
(7, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Authorization', '../../admin/v1/authorization'),
(8, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Profile Setting', 'admin/v1/setting/calendar'),
(9, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Report Setting', 'admin/v1/reportsetting '),
(10, 'ari', '2017-02-08 00:00:00', 0, 'ari', '2017-02-08 00:00:00', '1.0', 'Calendar Setting', 'admin/v1/setting/calendar'),
(11, 'NULL', '2017-04-19 00:00:00', 0, 'NULL', '2017-04-19 00:00:00', '1.0', 'Application Setting', NULL),
(12, 'NULL', '2017-04-19 00:00:00', 0, 'NULL', '2017-04-19 00:00:00', '1.0', 'Add Menu', '../../admin/v1/menu'),
(13, 'NULL', '2017-04-19 00:00:00', 0, 'NULL', '2017-04-19 00:00:00', '1.0', 'User Group', '../../admin/v1/usergroup'),
(14, 'NULL', '2017-04-19 00:00:00', 0, 'NULL', '2017-04-19 00:00:00', '1.0', 'Basic Web', '../../admin/v1/basicweb'),
(15, NULL, '2017-11-04 12:07:08', 1, NULL, '2017-11-04 12:09:21', '1.0', 'basic SPA', '../../admin/v1/main');


INSERT INTO sys_roles (id, created_by, created_time, disabled, modified_by, modified_time, version, role_name) VALUES
(1, NULL, '2016-11-20 01:38:52', 0, NULL, '2016-11-20 01:38:52', '1.0', 'admin'),
(2, NULL, '2016-11-20 01:38:52', 0, NULL, '2016-11-20 01:38:52', '1.0', 'approval'),
(3, NULL, '2016-11-20 01:38:52', 0, NULL, '2016-11-20 01:38:52', '1.0', 'user'),
(4, NULL, '2016-11-20 01:38:52', 0, NULL, '2016-11-20 01:38:52', '1.0', 'public');



INSERT INTO sys_authorization (id, created_by, created_time, disabled, modified_by, modified_time, version, sys_roles_id, is_delete, is_insert, is_read, is_update, parent_id, sys_menu_id) VALUES
(69, NULL, '2016-12-11 22:01:14', 1, NULL, '2016-12-11 22:01:14', '1.0', 1, 1, 1, 1, 1, NULL, 1),
(70, NULL, '2016-12-11 22:01:14', 1, NULL, '2016-12-11 22:01:14', '1.0', 1, 1, 1, 1, 0, 69, 2),
(71, NULL, '2016-12-11 22:01:14', 0, NULL, '2016-12-11 22:01:14', '1.0', 1, 0, 1, 1, 1, 69, 3),
(72, NULL, '2016-12-11 22:01:14', 0, NULL, '2016-12-11 22:01:14', '1.0', 1, 0, 0, 1, 1, NULL, 4),
(73, NULL, '2016-12-11 22:01:14', 1, NULL, '2016-12-11 22:01:14', '1.0', 1, 1, 1, 1, 1, 72, 5),
(74, NULL, '2016-12-11 22:01:14', 0, NULL, '2016-12-11 22:01:14', '1.0', 1, 0, 1, 1, 1, 73, 6),
(75, NULL, '2016-12-11 22:01:14', 0, NULL, '2016-12-11 22:01:14', '1.0', 1, 0, 1, 1, 1, 73, 7),
(76, NULL, '2016-12-11 22:01:14', 1, NULL, '2016-12-11 22:01:14', '1.0', 1, 1, 1, 1, 1, 72, 8),
(77, NULL, '2016-12-11 22:01:15', 1, NULL, '2016-12-11 22:01:15', '1.0', 1, 0, 1, 1, 1, 72, 9),
(78, NULL, '2016-12-11 22:01:15', 1, NULL, '2016-12-11 22:01:15', '1.0', 1, 0, 1, 1, 1, 72, 10),
(90, NULL, '2017-02-11 19:21:30', 0, NULL, '2017-02-11 19:21:30', '1.0', 3, 0, 0, 1, 0, NULL, 1),
(132, NULL, '2017-02-12 17:16:01', 1, NULL, '2017-02-12 17:16:01', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(139, NULL, '2017-02-12 22:09:17', 1, NULL, '2017-02-12 22:09:17', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(140, NULL, '2017-02-13 22:05:11', 1, NULL, '2017-02-13 22:05:11', '1.0', 2, 1, 1, 1, 1, 132, 1),
(142, NULL, '2017-02-15 09:56:44', 1, NULL, '2017-02-15 09:56:44', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(143, NULL, '2017-02-15 09:59:49', 0, NULL, '2017-02-15 09:59:49', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(155, NULL, '2017-02-15 11:24:54', 0, NULL, '2017-02-15 11:24:54', '1.0', 2, 0, 0, 1, 0, 132, 2),
(157, NULL, '2017-02-15 11:55:37', 1, NULL, '2017-02-15 11:55:37', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(163, NULL, '2017-02-15 18:36:54', 1, NULL, '2017-02-15 18:36:54', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(165, NULL, '2017-02-15 18:55:11', 1, NULL, '2017-02-15 18:55:11', '1.0', 2, 1, 1, 1, 1, NULL, 2),
(166, NULL, '2017-02-15 18:56:32', 1, NULL, '2017-02-15 18:56:32', '1.0', 2, 1, 1, 1, 1, NULL, 2),
(176, NULL, '2017-02-15 19:49:04', 1, NULL, '2017-02-15 19:49:04', '1.0', 2, 1, 1, 1, 1, 132, 1),
(177, NULL, '2017-02-15 19:49:45', 1, NULL, '2017-02-15 19:49:45', '1.0', 2, 1, 1, 1, 1, 132, 1),
(179, NULL, '2017-02-15 19:56:30', 1, NULL, '2017-02-15 19:56:30', '1.0', 2, 1, 1, 1, 1, 132, 1),
(181, NULL, '2017-02-15 20:01:36', 1, NULL, '2017-02-15 20:01:36', '1.0', 2, 1, 1, 1, 1, 132, 1),
(183, NULL, '2017-02-15 22:07:43', 1, NULL, '2017-02-15 22:07:43', '1.0', 2, 1, 1, 1, 1, NULL, 2),
(189, NULL, '2017-02-15 22:19:18', 1, NULL, '2017-02-15 22:19:18', '1.0', 2, 1, 1, 1, 1, NULL, 2),
(197, NULL, '2017-02-16 22:40:37', 1, NULL, '2017-02-16 22:40:37', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(198, NULL, '2017-02-16 22:41:28', 1, NULL, '2017-02-16 22:41:28', '1.0', 2, 1, 1, 1, 1, 181, 1),
(203, NULL, '2017-02-18 14:37:32', 1, NULL, '2017-02-18 14:37:32', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(204, NULL, '2017-02-18 14:38:04', 1, NULL, '2017-02-18 14:38:04', '1.0', 2, 1, 1, 1, 1, 189, 1),
(209, NULL, '2017-02-18 17:10:14', 1, NULL, '2017-02-18 17:10:14', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(237, NULL, '2017-02-21 22:44:45', 1, NULL, '2017-02-21 22:44:45', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(238, NULL, '2017-02-21 22:46:25', 1, NULL, '2017-02-21 22:46:25', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(239, NULL, '2017-02-21 22:49:03', 1, NULL, '2017-02-21 22:49:03', '1.0', 2, 1, 1, 1, 1, NULL, 1),
(240, NULL, '2017-04-19 15:52:41', 1, NULL, '2017-04-19 15:52:41', '1.0', 1, 1, 1, 1, 1, 73, 13),
(243, NULL, '2017-04-19 15:56:24', 1, NULL, '2017-04-19 15:56:24', '1.0', 1, 1, 1, 1, 1, 72, 11),
(244, NULL, '2017-04-19 15:57:01', 1, NULL, '2017-04-19 15:57:01', '1.0', 1, 1, 1, 1, 1, 243, 12),
(245, NULL, '2017-04-19 18:32:40', 1, NULL, '2017-04-19 18:32:40', '1.0', 1, 1, 1, 1, 1, NULL, 14);


INSERT INTO sys_user (id, created_by, created_time, disabled, modified_by, modified_time, version, email, is_active, name, no_hp, password, username) VALUES
(1, NULL, '2017-04-16 08:14:39', 0, NULL, '2017-04-16 08:14:39', '1.0', 'prasetiyooo@gmail.com', 1, '', '085645480401', '1234', 'ari'),
(2, NULL, '2017-04-04 22:15:20', 0, NULL, '2017-04-04 22:15:20', '1.0', 'coba@coba', 1, 'coba', '1111111111', '12344', 'coba');


INSERT INTO sys_user_roles (id, created_by, created_time, disabled, modified_by, modified_time, version, sys_roles_id, sys_user_id) VALUES
(1, NULL, '2017-04-15 21:02:17',0, NULL, '2017-04-15 21:02:17', '1.0', 1, 1),
(2, NULL, '2017-04-19 22:08:47',0, NULL, '2017-04-19 22:08:47', '1.0', 4, 2);