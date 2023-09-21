INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (0, 1, 'coupon-customer-serv.yml', 'DEFAULT_GROUP', '', 'disableCouponRequest: true',
        'e5f007e47b5870be0ca6d2b4eb96efd8', '2023-09-06 07:05:14', '2023-09-06 07:05:14', null, '172.16.30.95', 'I',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (1, 2, 'coupon-customer-serv.yml', 'DEFAULT_GROUP', '', 'disableCouponRequest: true',
        'e5f007e47b5870be0ca6d2b4eb96efd8', '2023-09-06 07:56:32', '2023-09-06 07:56:32', null, '172.16.30.95', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (0, 3, 'coupon-config.yml', 'EXT_GROUP', '', 'a:b', 'd8160c9b3dc20d4e931aeb4f45262155', '2023-09-06 08:32:21',
        '2023-09-06 08:32:22', null, '172.16.30.95', 'I', 'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (0, 4, 'rabbitmq-config.yml', 'EXT_GROUP', '', 'a:b', 'd8160c9b3dc20d4e931aeb4f45262155', '2023-09-06 08:32:45',
        '2023-09-06 08:32:46', null, '172.16.30.95', 'I', 'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (0, 5, 'coupon-template-serv-flow-rules', 'SENTINEL_GROUP', '',
        '[{"app":"coupon-template-serv","clusterConfig":{"acquireRefuseStrategy":0,"clientOfflineTime":2000,"fallbackToLocalWhenFail":true,"resourceTimeout":2000,"resourceTimeoutStrategy":0,"sampleCount":10,"strategy":0,"thresholdType":0,"windowIntervalMs":1000},"clusterMode":false,"controlBehavior":0,"count":10.0,"gmtCreate":1694164118679,"gmtModified":1694164118679,"grade":1,"id":11,"ip":"172.16.30.95","limitApp":"coupon-customer-serv","port":8720,"resource":"getTemplate","strategy":0}]',
        '6fa45cb3ab16c60cfe5039663211a033', '2023-09-08 09:08:38', '2023-09-08 09:08:39', null, '172.16.30.95', 'I',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (5, 6, 'coupon-template-serv-flow-rules', 'SENTINEL_GROUP', '',
        '[{"app":"coupon-template-serv","clusterConfig":{"acquireRefuseStrategy":0,"clientOfflineTime":2000,"fallbackToLocalWhenFail":true,"resourceTimeout":2000,"resourceTimeoutStrategy":0,"sampleCount":10,"strategy":0,"thresholdType":0,"windowIntervalMs":1000},"clusterMode":false,"controlBehavior":0,"count":10.0,"gmtCreate":1694164118679,"gmtModified":1694164118679,"grade":1,"id":11,"ip":"172.16.30.95","limitApp":"coupon-customer-serv","port":8720,"resource":"getTemplate","strategy":0}]',
        '6fa45cb3ab16c60cfe5039663211a033', '2023-09-08 09:09:08', '2023-09-08 09:09:08', null, '172.16.30.95', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (0, 7, 'routes-config.json', 'DEFAULT_GROUP', '', '[
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
        "uri": "lb://coupon-customer-serv"
    }
]', 'ad7736b9611a5dc11461c8da330dfba1', '2023-09-18 09:19:58', '2023-09-18 09:19:59', null, '0:0:0:0:0:0:0:1', 'I',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (7, 8, 'routes-config.json', 'DEFAULT_GROUP', '', '[
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
        "uri": "lb://coupon-customer-serv"
    }
]', 'ad7736b9611a5dc11461c8da330dfba1', '2023-09-18 09:24:05', '2023-09-18 09:24:05', null, '0:0:0:0:0:0:0:1', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (7, 9, 'routes-config.json', 'DEFAULT_GROUP', '', '[
    {
        "id": "customer-dynamic-router",
        "order": 0,
        "predicates": [
            {
                "args": {
                    "pattern": "/dynamic1/**"
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
        "uri": "lb://coupon-customer-serv"
    }
]', '3f2424d7aea9afe044e4c0bdb3191822', '2023-09-18 09:24:33', '2023-09-18 09:24:33', null, '0:0:0:0:0:0:0:1', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (7, 10, 'routes-config.json', 'DEFAULT_GROUP', '', '[
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
        "uri": "lb://coupon-customer-serv"
    }
]', '6c5f9d40927118d9bd138fa1d9d71abf', '2023-09-18 09:35:16', '2023-09-18 09:35:17', null, '0:0:0:0:0:0:0:1', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (7, 11, 'routes-config.json', 'DEFAULT_GROUP', '', '[
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
        "uri": "lb://coupon-customer-serv"
    }
]', '6c5f9d40927118d9bd138fa1d9d71abf', '2023-09-18 09:40:50', '2023-09-18 09:40:50', null, '0:0:0:0:0:0:0:1', 'U',
        'dev', '');
INSERT INTO nacos.his_config_info (id, nid, data_id, group_id, app_name, content, md5, gmt_create, gmt_modified,
                                   src_user, src_ip, op_type, tenant_id, encrypted_data_key)
VALUES (7, 12, 'routes-config.json', 'DEFAULT_GROUP', '', '[
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
        "metadata":{"delete":1}
    }
]', '84a7e13bf3196f78b4042bfd84934d1c', '2023-09-18 09:41:03', '2023-09-18 09:41:04', null, '0:0:0:0:0:0:0:1', 'U',
        'dev', '');
