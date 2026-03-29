-- 코드를 작성해주세요
select e.id, e.genotype, ee.genotype as parent_genotype
from ecoli_data e, ecoli_data ee
where e.parent_id = ee.id and
(e.genotype & ee.genotype) = ee.genotype
order by 1
;