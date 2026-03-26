-- 코드를 작성해주세요
with recursive rc as(

    select id, 1 as generation
    from ecoli_data
    where parent_id is null
    
    union all
    
    select e.id, rc.generation + 1
    from ecoli_data e
    join rc on e.parent_id = rc.id
)


select e.id from ecoli_data e 
inner join rc on e.id = rc.id
where generation = 3
order by id;