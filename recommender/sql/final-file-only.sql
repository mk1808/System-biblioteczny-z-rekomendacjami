
SELECT tn.goodreads_book_id, tn.tag_id, tn.count  FROM tags_numbers_grouped tn join tags t 
on tn.tag_id=t.tag_id
where tn.tag_id IN (SELECT tng.tag_id  from tags_numbers_grouped tng group by tng.tag_id 
HAVING COUNT(*)>15)
order by tn.goodreads_book_id asc, tn.count desc
