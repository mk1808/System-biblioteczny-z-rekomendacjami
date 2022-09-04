--grupowanie po tagach
CREATE OR REPLACE VIEW tags_numbers_grouped AS
	select goodreads_book_id, tag_id, sum(to_number(count, '99999')) as count from book_tags 
	group by goodreads_book_id, tag_id 
	order by goodreads_book_id, tag_id;

select * from tags_numbers_grouped
where goodreads_book_id='3581' order by tag_id
;

select * from book_tags where goodreads_book_id='3581' order by tag_id
;

SELECT  COUNT(*) as count, t.tag_name, tn.tag_id FROM tags_numbers_grouped tn join tags t 
on tn.tag_id=t.tag_id
GROUP BY  t.tag_name, tn.tag_id
HAVING COUNT(*)>15
ORDER BY count ;


SELECT tn.goodreads_book_id, tn.tag_id, tn.count  FROM tags_numbers_grouped tn join tags t 
on tn.tag_id=t.tag_id
where tn.tag_id IN (SELECT tng.tag_id  from tags_numbers_grouped tng group by tng.tag_id 
HAVING COUNT(*)>15)
order by tn.goodreads_book_id asc, tn.count desc

select * from book_tags;

--SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id
--where  bt.tag_id = 20656
--GROUP BY  t.tag_name, bt.tag_id
--HAVING COUNT(*)>15
--ORDER BY number ;

--select * from public.book_tags where tag_id = 20656 order by goodreads_book_id;