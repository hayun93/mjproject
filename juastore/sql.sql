create view product_inventory_view as select
product.product_code as product_code,
product.product_name as product_name,
product.product_price as product_price,
product.image as image,
product.etc as etc,
((select sum(inventory_amount) from inventory where inventory_state='in' and product.product_code=inventory.product_code)-
(select sum(inventory_amount) from inventory where inventory_state='out' and product.product_code=inventory.product_code)) as inventory_amount
from inventory left join product on product.product_code=inventory.product_code group by product_code;