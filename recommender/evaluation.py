# -*- coding: utf-8 -*-

#################################EWALUACJA ###################
def get_random_list(planned_length, start, stop):
    randoms1=[]
    while(len(randoms1)<planned_length):
        random_number = random.randint(start, stop-1)
        if not random_number in randoms1:
            randoms1.append(random_number)
    return randoms1   


ratings_for_users_active=[]
ratings_for_users_cp = copy.deepcopy(ratings_for_users)
n=15
for rating in ratings_for_users_cp:
    if len(rating)>n:
        ratings_for_users_active.append(copy.deepcopy(rating))
        
        
i=0  
n=500
random_indexes = get_random_list(n, 0, len(ratings_for_users_active))   


ratings_for_users_chosen=[]
ratings_for_users_active_final = copy.deepcopy(ratings_for_users_active)
n=15
for index in random_indexes:
    ratings_for_users_chosen.append(copy.deepcopy(ratings_for_users_active_final[index]))
    



ratings_for_users_learn = []
ratings_for_users_test = []
df4 = pd.DataFrame([[None,None, None, None]], columns=list(ratings_for_user.columns))


i=0

for ratings_for_user in ratings_for_users_chosen:
    i=i+1
    print(i)
    length = len(ratings_for_user)
    half = math.ceil(length/2)
    
    randoms = get_random_list(half, 0, length-1)
    for_single_user_learn=copy.deepcopy(df4)
    for one_random in randoms:
        for_single_user_learn=for_single_user_learn.append(ratings_for_user.iloc[one_random],  ignore_index = True)
    
    for_single_user_learn=for_single_user_learn.drop(0)
    ratings_for_users_learn.append(for_single_user_learn)
    
    for_single_user_test=copy.deepcopy(df4)
    all_indexes = range(0, length)
    remainings = list(set(all_indexes) - set(randoms))
    for remaining in remainings:
        for_single_user_test=for_single_user_test.append(ratings_for_user.iloc[remaining],  ignore_index = True)
    
    for_single_user_test=for_single_user_test.drop(0)
    ratings_for_users_test.append(for_single_user_test)
    

        




############################################################################################################################







true_positive=[]  #prawidłowo rekomendowane
false_positive=[] #nieprawidłowo rekomendowane
false_negative=[] #brak rekomendacji choc byc powinna
precision=[]
recall=[]
f1_measure=[]
i=0
n=30
for recom in recoms5[0:user_limit]:
    liked_user_books = set(ratings_for_users_test_liked[i]["book_id"])
    recom_book_ids = set(recom["book_id"])
    
    tp=liked_user_books.intersection(recom_book_ids)
    fp=recom_book_ids.difference(liked_user_books)
    fn=liked_user_books.difference(recom_book_ids)
    true_positive.append(tp)
    false_positive.append(fp)
    false_negative.append(fn)
    prec=len(tp)/(len(tp)+len(fp))
    precision.append(prec)
    rec=len(tp)/(len(tp)+len(fn))
    recall.append(rec)
    f1_m=0
    if prec+rec > 0:
        f1_m=2*(prec*rec)/(prec+rec)
        
    f1_measure.append(f1_m)
    i=i+1

mean_precision=np.mean(precision)
mean_recall=np.mean(recall)
mean_f1_measure=np.mean(f1_measure)