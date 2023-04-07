-- 코드를 입력하세요

SELECT car_id, 
    case when sum(availability) > 0 then "대여중" else "대여 가능" end as availability
FROM (
    select car_id,
        case when '2022-10-16' between start_date and end_date then 1 else 0
        end as availability
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
)as temp
group by car_id
order by car_id desc
;