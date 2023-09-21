INSERT INTO nacos.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip,
                               app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
VALUES (1, 'coupon-customer-serv.yml', 'DEFAULT_GROUP', 'disableCouponRequest: false',
        'd2f1c82a7615266e42b76d5e47293c22', '2023-09-06 07:05:14', '2023-09-06 07:56:32', null, '172.16.30.95', '',
        'dev', '', '', '', 'yaml', '', '');
INSERT INTO nacos.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip,
                               app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
VALUES (3, 'coupon-config.yml', 'EXT_GROUP', 'a:b', 'd8160c9b3dc20d4e931aeb4f45262155', '2023-09-06 08:32:22',
        '2023-09-06 08:32:22', null, '172.16.30.95', '', 'dev', null, null, null, 'yaml', null, '');
INSERT INTO nacos.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip,
                               app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
VALUES (4, 'rabbitmq-config.yml', 'EXT_GROUP', 'a:b', 'd8160c9b3dc20d4e931aeb4f45262155', '2023-09-06 08:32:46',
        '2023-09-06 08:32:46', null, '172.16.30.95', '', 'dev', null, null, null, 'yaml', null, '');
INSERT INTO nacos.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip,
                               app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
VALUES (5, 'coupon-template-serv-flow-rules', 'SENTINEL_GROUP',
        '[{"app":"coupon-template-serv","clusterConfig":{"acquireRefuseStrategy":0,"clientOfflineTime":2000,"fallbackToLocalWhenFail":true,"resourceTimeout":2000,"resourceTimeoutStrategy":0,"sampleCount":10,"strategy":0,"thresholdType":0,"windowIntervalMs":1000},"clusterMode":false,"controlBehavior":0,"count":10.0,"gmtCreate":1694164118679,"gmtModified":1694164118679,"grade":1,"id":11,"ip":"172.16.30.95","limitApp":"coupon-customer-serv","port":8720,"resource":"getTemplate","strategy":0},{"app":"coupon-template-serv","clusterConfig":{"acquireRefuseStrategy":0,"clientOfflineTime":2000,"fallbackToLocalWhenFail":true,"resourceTimeout":2000,"resourceTimeoutStrategy":0,"sampleCount":10,"strategy":0,"thresholdType":0,"windowIntervalMs":1000},"clusterMode":false,"controlBehavior":0,"count":10.0,"gmtCreate":1694164147771,"gmtModified":1694164147771,"grade":1,"id":12,"ip":"172.16.30.95","limitApp":"coupon-customer-serv","port":8720,"resource":"getTemplateBatch","strategy":0}]',
        '70bf0f75bf93af06f4ff57ff2db15203', '2023-09-08 09:08:39', '2023-09-08 09:09:08', null, '172.16.30.95', '',
        'dev', null, null, null, 'text', null, '');
INSERT INTO nacos.config_info (id, data_id, group_id, content, md5, gmt_create, gmt_modified, src_user, src_ip,
                               app_name, tenant_id, c_desc, c_use, effect, type, c_schema, encrypted_data_key)
VALUES (7, 'routes-config.json', 'DEFAULT_GROUP', '[
    {
        "id": "customer-dynamic-router",
        "order": 0,
        "predicates": [
            {
                "args": {
                    "pattern": "/dynamic/**"
                },
                "name": "Path"
            }
        ],
        "filters": [
            {
                "name": "StripPrefix",
                "args": {
                    "parts": 1
                }
            }
        ],
        "uri": "lb://coupon-customer-serv",
        "metadata":{"delete":0}
    }
]', '8fb664775cac122a0d8ab18c7a67a760', '2023-09-18 09:19:59', '2023-09-18 09:41:04', null, '0:0:0:0:0:0:0:1', '',
        'dev', '', '', '', 'json', '', '');
