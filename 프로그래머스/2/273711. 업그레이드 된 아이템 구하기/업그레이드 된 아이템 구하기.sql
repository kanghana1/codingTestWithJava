-- 코드를 작성해주세요
select item_id, item_name, rarity
from item_info
where item_id in (select item_id from item_tree where parent_item_id in 
                  (select item_id from item_info where rarity = 'RARE'))
order by 1 desc
;