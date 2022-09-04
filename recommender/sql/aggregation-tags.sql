SELECT goodreads_book_id, count, tag_id
	FROM public.book_tags order by tag_id;
	
	
	SELECT bt.goodreads_book_id, bt.count, bt.tag_id, t.tag_name
	FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id;
	
	SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM
 public.book_tags bt  right join tags t on bt.tag_id=t.tag_id
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number;

	SELECT  COUNT(*) as number, bt.tag_id FROM
 public.book_tags bt 
GROUP BY   bt.tag_id
ORDER BY number;

SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id
where length(t.tag_name)<4 --t.tag_name like '%journal%'
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number 

call delDel(30413); call delDel(8917); call delDel(9642); call delDel(4062); call delDel(25557); call delDel(28098); call delDel(20920); call delDel(21235); call delDel(4425); call delDel(18693); call delDel(10944); call delDel(24870); call delDel(32437); call delDel(22388); call delDel(1171); call delDel(22112); call delDel(28788); call delDel(22230); call delDel(27805); call delDel(15902); call delDel(1231); call delDel(3686); call delDel(9220); call delDel(29595); call delDel(18194); call delDel(4793); call delDel(2400); call delDel(24917); call delDel(17898); call delDel(21676); call delDel(23164); call delDel(10018); call delDel(11999); call delDel(7571); call delDel(5904); call delDel(1459); call delDel(17915); call delDel(10550); call delDel(9658); call delDel(3698); call delDel(8739); call delDel(20240); call delDel(11102); call delDel(21049); call delDel(4913); call delDel(2755); call delDel(2390); call delDel(26120); call delDel(21075); call delDel(31288); call delDel(31662); call delDel(10566); call delDel(20602); call delDel(29207); call delDel(31684); call delDel(22063); call delDel(11083); call delDel(9304); call delDel(9973); call delDel(9473); call delDel(26430); call delDel(14658); call delDel(12733); call delDel(10103); call delDel(19599); call delDel(23431); call delDel(10924); call delDel(22846); call delDel(20251); call delDel(17738); call delDel(32429); call delDel(23129); call delDel(1407); call delDel(1591); call delDel(22437); call delDel(17930); call delDel(10299); call delDel(17837); call delDel(8985); call delDel(31994); call delDel(31354); call delDel(6047); call delDel(20228); call delDel(25474); call delDel(27049); call delDel(17187); call delDel(17075); call delDel(15590); call delDel(14325); call delDel(32960); call delDel(21519); call delDel(12174); call delDel(17509); call delDel(16257); call delDel(15820); call delDel(17064); call delDel(1057); call delDel(33254); call delDel(33228); call delDel(20209); call delDel(31047); call delDel(24100); call delDel(33237); call delDel(27941); call delDel(21261); call delDel(2765); call delDel(7573); call delDel(10625); call delDel(6462); call delDel(21070); call delDel(15940); call delDel(15206); call delDel(17741); call delDel(25982); call delDel(10588); call delDel(21611); call delDel(16896); call delDel(8625); call delDel(1852); call delDel(22082); call delDel(1262); call delDel(27986); call delDel(6470); call delDel(2498); call delDel(11714); call delDel(31999); call delDel(14664); call delDel(26726); call delDel(10921); call delDel(16426); call delDel(9079); call delDel(1113); call delDel(26439); call delDel(9005); call delDel(27987); call delDel(16819); call delDel(15228); call delDel(7572); call delDel(15963); call delDel(18448); call delDel(12642); call delDel(27147); call delDel(9106); call delDel(27739); call delDel(17835); call delDel(29209); call delDel(11438); call delDel(1882); call delDel(23786); call delDel(29315); call delDel(3722); call delDel(12720); call delDel(10775); call delDel(16661); call delDel(25945); call delDel(1309); call delDel(1224); call delDel(28493); call delDel(26241); call delDel(1900); call delDel(4771); call delDel(24692); call delDel(12440); call delDel(22468); call delDel(28979); call delDel(1094); call delDel(23773); call delDel(33189); call delDel(23582); call delDel(20267); call delDel(30998); call delDel(10037); call delDel(15267); call delDel(18254); call delDel(1570); call delDel(19864); call delDel(10362); call delDel(30501); call delDel(2752); call delDel(2032); call delDel(28843); call delDel(12148); call delDel(29160); call delDel(4054); call delDel(20029); call delDel(24487); call delDel(13123); call delDel(22848); call delDel(15244); call delDel(32244); call delDel(5860); call delDel(22580); call delDel(24375); call delDel(19866); call delDel(20586); call delDel(1358); call delDel(9991); call delDel(20645); call delDel(21363); call delDel(10300); call delDel(3205); call delDel(14965); call delDel(26307); call delDel(10724); call delDel(22518); call delDel(6467); call delDel(5492); call delDel(29571); call delDel(15776); call delDel(18471); call delDel(10910); call delDel(12285); call delDel(29262); call delDel(1103); call delDel(24607); call delDel(22852); call delDel(11608); call delDel(19688); call delDel(31050); call delDel(27416); call delDel(1061); call delDel(9102); call delDel(1635); call delDel(12644); call delDel(23874); call delDel(11087); call delDel(31764); call delDel(6658); call delDel(6465); call delDel(13218); call delDel(10413); call delDel(21602); call delDel(14956); call delDel(15266); call delDel(23386); call delDel(14119); call delDel(22914); call delDel(22851); call delDel(13824); call delDel(15426); call delDel(3658); call delDel(8862); call delDel(1907); call delDel(3979); call delDel(28495); call delDel(15444); call delDel(18752); call delDel(27921); call delDel(17623); call delDel(17894); call delDel(4479); call delDel(33090); call delDel(30277); call delDel(3779); call delDel(26292); call delDel(1525); call delDel(14120); call delDel(10726); call delDel(7593); call delDel(15218); call delDel(3639); call delDel(19872); call delDel(22235); call delDel(5943); call delDel(10856); call delDel(17065); call delDel(29314); call delDel(22081); call delDel(27905); call delDel(33093); call delDel(2610); call delDel(31290); call delDel(31209); call delDel(33072); call delDel(6352); call delDel(22415); call delDel(23429); call delDel(16823); call delDel(22636); call delDel(31435); call delDel(32736); call delDel(16430); call delDel(5576); call delDel(24567); call delDel(32961); call delDel(33185); call delDel(21234); call delDel(23634); call delDel(3703); call delDel(5535); call delDel(1172); call delDel(16313); call delDel(16821); call delDel(21047); call delDel(29379); call delDel(32330); call delDel(17743); call delDel(13465); call delDel(1020); call delDel(4041); call delDel(1234); call delDel(31454); call delDel(1151); call delDel(2381); call delDel(9634); call delDel(16586); call delDel(11264); call delDel(1258); call delDel(16404); call delDel(23071); call delDel(23690); call delDel(3201); call delDel(20585); call delDel(26516); call delDel(1221); call delDel(4839); call delDel(4253); call delDel(16146); call delDel(23800); call delDel(10187); call delDel(3716); call delDel(22178); call delDel(12714); call delDel(31964); call delDel(10043); call delDel(1188); call delDel(19865); call delDel(21038); call delDel(1405); call delDel(9371); call delDel(17053); call delDel(1209); call delDel(30431); call delDel(26357); call delDel(6526); call delDel(21609); call delDel(28158); call delDel(32853); call delDel(30499); call delDel(22539); call delDel(17000); call delDel(21605); call delDel(13897); call delDel(30973); call delDel(26337); 
select * from book_tags where tag_id=4;
select * from tags where tag_id=11246;
select * from tags;
	
SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM
 public.book_tags bt join tags t on bt.tag_id=t.tag_id
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number 

SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id
where t.tag_name like '%19%'
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number 
--DELETE
delete  from book_tags where tag_id=32487;
delete  from tags where tag_id=32487;


select * from book_tags where tag_id=27095 order by goodreads_book_id;
select * from book_tags where tag_id=15933; 

select * from tags where tag_id=15933;

30626
3828

select * from tags where tag_name like '%health%';

SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt 
join tags t on bt.tag_id=t.tag_id
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number desc
call updDel(22034,21989); 

update book_tags set tag_id = 2938 where tag_id=2989;
delete  from tags where tag_id=2989;

CREATE PROCEDURE updDel(
    _main_id int,
    _replaced_id int
) LANGUAGE plpgsql
AS $$
BEGIN 
	update book_tags set tag_id = _main_id where tag_id=_replaced_id;
	delete from tags where tag_id=_replaced_id;
END
$$

CREATE PROCEDURE delDel(
    _main_id int
) LANGUAGE plpgsql
AS $$
BEGIN 
	delete from tags where tag_id=_main_id;
	delete from book_tags where tag_id=_main_id;
END
$$

select COUNT(*) from tags; --31221 27413 25541 23769

 call delDel(3358);

1872 ai
755 sf
ya

\_

delete audiobook audio favourites e-book 
books audio-books abandoned "have" audio-book did-not-finish 
maybe unfinished didn-t-finish books-i-have other faves mine favs      
call updDel(33004,8084);  

call delDel(6405); call delDel(6404); call delDel(6406); call delDel(6402); call delDel(6403); call delDel(30508); call delDel(19584); 

SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id
where t.tag_name like '%action%' 
GROUP BY  t.tag_name, bt.tag_id
ORDER BY number 

call updDel(1540,11747); call updDel(1540,1546); call updDel(1540,1550); call updDel(1540,14182); call updDel(1540,11019); call updDel(1540,1636); call updDel(1540,1541); 

call updDel(1599,1600); call updDel(1599,1603); call updDel(1599,1604); call updDel(1599,1961); call updDel(1599,29120); call updDel(1599,9960); call updDel(1599,1596); call updDel(1599,1602); call updDel(1599,1601); 

call updDel(15626,15624); call updDel(15626,15642); call updDel(15626,15630); call updDel(15626,15632); call updDel(15626,15635); call updDel(15626,15636); call updDel(15626,15637); call updDel(15626,15638); call updDel(15626,15640); call updDel(15626,15641); call updDel(15626,15643); call updDel(15626,15644); call updDel(15626,17759); call updDel(15626,32878); call updDel(15626,14722); call updDel(15626,15622); call updDel(15626,15623); call updDel(15626,15625); call updDel(15626,15639); call updDel(15626,25693); call updDel(15626,15628); call updDel(15626,16159); call updDel(15626,3448); 

SELECT  COUNT(*) as number, t.tag_name, bt.tag_id FROM public.book_tags bt join tags t on bt.tag_id=t.tag_id
GROUP BY  t.tag_name, bt.tag_id
HAVING COUNT(*)>15
ORDER BY number ;

call updDel(32341,32344); call updDel(32341,32342); call updDel(6443,32190); call updDel(6443,32186); call updDel(6443,23387); call updDel(6443,4483); call updDel(6443,4762); call updDel(6443,6400); call updDel(6443,29694); call updDel(6443,4691); call updDel(6443,6399); 
--'%\_%'
 historical_fiction
 "star_wars"
 


call updDel(14487,14522); call updDel(14487,14486); call updDel(14487,14532); call updDel(14487,14515); call updDel(14487,14468); call updDel(14487,14514); call updDel(14487,14533); call updDel(14487,14480); call updDel(14487,14481); call updDel(14487,3964); call updDel(14487,14537); call updDel(14487,14482); call updDel(14487,14516); call updDel(14487,14526); call updDel(14487,14473); 

select * from book_tags;




á í ç ł ó ñ ń ã é 

â, ê, î, ô, û
ä, ë, i, ö, ü
à, è, ì, ò, ù,
ć


--read, to read, favorite, genre, best, books, historic-historical, new, literature, want-to-read
--zamiana kolejnosci, tbr, lit
--horror, religion, business, art, career, fantasy, ya(young-adult), romance, education, comedy, health, tech, drama,
--humour, children, college,psychology,comics,manga , family, sport, computer, teen, economics, school, history, kindergarten, 
--weird, paranormal, magic/magical, autobiography, poems, criminal, classics, programming, poetry, non-fiction, cookbook
--biography, guide,  hobb, engine (inz), celebrity, pirate, wizard, prince, animal, nature, erotic, law, diy, crafts, witch, fit, coffee, medieval
--ancient, renaissance, universe, women
--food, environment, teach, games, medicine, fairy, university, adventure, architecture, diary, adolesc(adolescence),young, crime,
--biology, physics, chemistry, 
--graphic-novel,novel, journal, mystery, math, paranormal-romance, speculative, science-fiction, superhero, utopian, dystopian, supernatural
--memoir, noir, picture, philosophy, prayer, politic, thriller, political-thriller, spirituality, new-age, satire, true-crime, true-story,
--short, suspense (suspence), self-help, western,  travel, essay, christian, faith,money, contemporary, gay, lesbian, lgbt, historical-fiction, sex
--love, marriage, baby, parent, scary, funny, youth, award, war,spy, pet,
--learn, theatre, relation, music, creativity, motivation
--europe murder adult, realistic, detective cry culture america work creepy romantic emotional fun
--startup management knowledge social
graphic-novel action addict age aliens inspiration internt 
invest journey
dragon letter level librarian interest diet death modern 
del alex
7563


CREATE VIEW tags_numbers AS
  select goodreads_book_id, tag_id, count(*) as number, 
	sum(to_number(count, '9999')) from book_tags 
	group by goodreads_book_id, tag_id;
  
select * from  book_tags ;

select goodreads_book_id, tag_id, count(*) as number, 
sum(to_number(count, '9999')) from book_tags 
group by goodreads_book_id, tag_id


select goodreads_book_id, tag_id, 
sum(to_number(count, '9999')) as count from book_tags 
group by goodreads_book_id, tag_id


select * from book_tags where 
goodreads_book_id = '1' AND tag_id = 6857;

--6850

having count(*)>2 


order by goodreads_book_id, tag_id

select * from tags_numbers;


