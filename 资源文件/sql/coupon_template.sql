INSERT INTO coupon.coupon_template (id, available, created_time, description, name, rule, shop_id, type)
VALUES (2, true, '2023-07-04 08:04:31.820000', '满50随机立减最多5元，晚间减10元', '晚间双倍立减券',
        '{"discount":{"quota":500,"threshold":5000},"limitation":10}', null, '4');
INSERT INTO coupon.coupon_template (id, available, created_time, description, name, rule, shop_id, type)
VALUES (3, true, '2023-07-04 08:04:53.701000', '满减券描述，每人限制最多10张', '全场满10减1元',
        '{"discount":{"quota":100,"threshold":1000},"limitation":10}', null, '1');
INSERT INTO coupon.coupon_template (id, available, created_time, description, name, rule, shop_id, type)
VALUES (4, true, '2023-07-04 08:08:48.246000', '满50随机立减最多5元', '随机立减券',
        '{"discount":{"quota":500,"threshold":5000},"limitation":10}', null, '3');
INSERT INTO coupon.coupon_template (id, available, created_time, description, name, rule, shop_id, type)
VALUES (5, true, '2023-07-04 08:09:55.744000', '每人限制最多10张', '打折券满10元打9折',
        '{"discount":{"quota":90,"threshold":1000},"limitation":10}', null, '2');
INSERT INTO coupon.coupon_template (id, available, created_time, description, name, rule, shop_id, type)
VALUES (6, true, '2023-07-04 08:12:34.025000', '满50随机立减最多5元，晚间减10元', '晚间双倍立减券',
        '{"discount":{"quota":500,"threshold":5000},"limitation":10}', null, '4');
