-- 코드를 작성해주세요
with recursive rc as(
    select id, 1 as generation from ecoli_data
    where parent_id is null
    
    union
    
    select e1.id, rc.generation + 1
    from ecoli_data e1
    join rc on rc.id = e1.parent_id
)

select count(*) as count, generation from ecoli_data e, rc
where e.id = rc.id and e.id not in
(select e1.id 
from ecoli_data e1, ecoli_data e2
where e1.id = e2.parent_id)
group by generation
order by generation
;
