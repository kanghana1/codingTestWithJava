-- 코드를 작성해주세요
select e.emp_no, e.emp_name, (case when avg(g.score) >= 96 then 'S'
                            when avg(g.score) < 96 and avg(g.score) >= 90 then 'A'
                            when avg(g.score) < 90 and avg(g.score) >= 80 then 'B'
                            else 'C' end) as grade, (case when avg(g.score) >= 96 then e.sal * 0.2
                                                    when avg(g.score) < 96 and avg(g.score) >= 90 then e.sal * 0.15
                                                    when avg(g.score) < 90 and avg(g.score) >= 80 then e.sal * 0.1
                                                    else 0 end) as bonus
from hr_department d
inner join hr_employees e on d.dept_id = e.dept_id
inner join hr_grade g on g.emp_no = e.emp_no
group by emp_no

order by emp_no;