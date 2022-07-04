-- 코드를 입력하세요
SELECT distinct(CART_ID)
FROM CART_PRODUCTS
WHERE NAME = 'Milk'
INTERSECT 
SELECT distinct(CART_ID)
FROM CART_PRODUCTS
WHERE NAME = 'Yogurt'
ORDER BY CART_ID
;
