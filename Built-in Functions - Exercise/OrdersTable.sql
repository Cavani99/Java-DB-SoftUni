SELECT product_name, order_date,
date_format(date_add(order_date, interval 3 day), "%Y-%m-%d %H:%i:%s") as pay_due,
date_format(date_add(order_date, interval 1 month), "%Y-%m-%d %H:%i:%s") as deliver_due
from orders;