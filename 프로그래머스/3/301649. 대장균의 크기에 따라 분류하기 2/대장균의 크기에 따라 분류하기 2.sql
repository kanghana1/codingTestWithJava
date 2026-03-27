-- 코드를 작성해주세요
select id,
case when (percent_rank() over(order by size_of_colony desc) * 100 between 0 and 25) then 'CRITICAL'
when (percent_rank() over(order by size_of_colony desc) * 100 between 25 and 50) then 'HIGH'
when (percent_rank() over(order by size_of_colony desc) * 100 between 50 and 75) then 'MEDIUM'
ELSE 'LOW' END AS COLONY_NAME
from ecoli_data
order by id
;