-- 코드를 입력하세요
SELECT b.category, sum(bs.sales) as total_sales
from book b inner join book_sales bs
on b.book_id = bs.book_id
where bs.SALES_DATE < '2022-02-01'
and bs.SALES_DATE > '2021-12-31'
group by b.category
order by b.category