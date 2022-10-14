-- 코드를 입력하세요
select p.PRODUCT_CODE, o.total*p.PRICE as SALES
from PRODUCT as p
join (
    select PRODUCT_ID, sum(SALES_AMOUNT) as total
    from OFFLINE_SALE
    group by PRODUCT_ID
) as o
on p.PRODUCT_ID = o.PRODUCT_ID
group by p.PRODUCT_CODE
order by SALES desc, p.PRODUCT_CODE

;




# SELECT SUBSTR(PRODUCT_CODE,1,2)
# FROM PRODUCT