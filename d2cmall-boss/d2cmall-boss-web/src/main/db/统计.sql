-- 品牌数据
SELECT crm_orderitem.`brand_code`,
       crm_orderitem.`brand_name`,
       year(crm_orderitem.`create_date`),
       month(crm_orderitem.`create_date`),
       sum(crm_orderitem.`quantity`),
       sum(crm_orderitem.`sale_price`*crm_orderitem.`quantity`-crm_orderitem.`promotion_amount`-crm_orderitem.`coupon_amount`-crm_orderitem.`order_promotion_amount`)
  FROM crm_orderitem,
       `crm_orders`
 where `crm_orderitem`.`order_sn`= `crm_orders`.`sn`
  -- and `crm_orders`.`source`<> '线下' and  `crm_orders`.`status` >=2
 and `crm_orders`.`source`= '线下' 
   and crm_orderitem.`create_date`>= '2016-01-01 00:00:00'
   and crm_orderitem.`create_date`<= '2017-01-01 00:00:00'
   and brand_code is not null
 GROUP BY crm_orderitem.`brand_code`,
         crm_orderitem.`brand_name`,
         year(crm_orderitem.`create_date`),
         month(crm_orderitem.`create_date`)
 ORDER BY crm_orderitem.`brand_name`;

-- 品牌数据
SELECT crm_orderitem.`brand_code`,
       crm_orderitem.`brand_name`,
       year(crm_orderitem.`create_date`),
       month(crm_orderitem.`create_date`),
       sum(crm_orderitem.`quantity`),
       sum(crm_orderitem.`sale_price`*crm_orderitem.`quantity`-crm_orderitem.`promotion_amount`-crm_orderitem.`coupon_amount`-crm_orderitem.`order_promotion_amount`)
  FROM crm_orderitem,
       `crm_orders`
 where `crm_orderitem`.`order_sn`= `crm_orders`.`sn`
  -- and `crm_orders`.`source`<> '线下' and  `crm_orders`.`status` >=2
 and (`crm_orders`.`source`= '线下' or ( `crm_orders`.`source`<> '线下' and  `crm_orders`.`status` >=2))
   and crm_orderitem.`create_date`>= '2016-01-01 00:00:00'
   and crm_orderitem.`create_date`<= '2017-01-01 00:00:00'
   and brand_code is not null
 GROUP BY crm_orderitem.`brand_code`,
         crm_orderitem.`brand_name`,
         year(crm_orderitem.`create_date`),
         month(crm_orderitem.`create_date`)
 ORDER BY crm_orderitem.`brand_name`;
 
 -- 商品数据
SELECT crm_orderitem.`brand_name`,
       crm_orderitem.product_sn,
       sum(crm_orderitem.`quantity`),
       sum(crm_orderitem.`sale_price`*crm_orderitem.`quantity`-crm_orderitem.`promotion_amount`-crm_orderitem.`coupon_amount`-crm_orderitem.`order_promotion_amount`)
  FROM crm_orderitem,
       `crm_orders`
 where `crm_orderitem`.`order_sn`= `crm_orders`.`sn`
   and `crm_orders`.`source`= '线下'
   and crm_orderitem.`create_date`>= '2016-01-01 00:00:00'
   and crm_orderitem.`create_date`<= '2017-01-01 00:00:00'
   and brand_code is not null
 GROUP BY crm_orderitem.`brand_name`,
         crm_orderitem.product_sn
 ORDER BY crm_orderitem.`brand_name`;
 
 -- 商品数据
SELECT crm_orderitem.`brand_name`,
       crm_orderitem.product_sn,
       sum(crm_orderitem.`quantity`),
       sum(crm_orderitem.`sale_price`*crm_orderitem.`quantity`-crm_orderitem.`promotion_amount`-crm_orderitem.`coupon_amount`-crm_orderitem.`order_promotion_amount`)
  FROM crm_orderitem,
       `crm_orders`
 where `crm_orderitem`.`order_sn`= `crm_orders`.`sn`
   and `crm_orders`.`source`<> '线下'
   and crm_orderitem.`create_date`>= '2016-01-01 00:00:00'
   and crm_orderitem.`create_date`<= '2017-01-01 00:00:00'
   and brand_code is not null
 GROUP BY crm_orderitem.`brand_name`,
         crm_orderitem.product_sn
 ORDER BY crm_orderitem.`brand_name`;
 