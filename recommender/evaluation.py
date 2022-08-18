# -*- coding: utf-8 -*-
import math
#################################EWALUACJA ###################

no_of_users_for_test = 100
min_num_of_user_books = 15
learn_to_test_ratio = 0.5
top_users=100
top=100

############################################################ Losowanie indeksow uzytkownikow ###################################3

####### przygotowanie 3 uprosczonych macierzy ##########################    
no_of_books_learn = [0] * no_of_users_for_test # liczby ksiazek oceniionych przez uzytkownikow
no_of_books_test = [0] * no_of_users_for_test 
matr_mf_learn = np.zeros(([no_of_users_for_test, max_no_of_books]))
matr_mf_test = np.zeros(([no_of_users_for_test, max_no_of_books]))
matr_book_id_learn = np.zeros(([no_of_users_for_test, max_no_of_books]), dtype=int)
matr_book_id_test = np.zeros(([no_of_users_for_test, max_no_of_books]), dtype=int)
matr_book_id_test_liked = np.zeros(([no_of_users_for_test, max_no_of_books]), dtype=int)


def get_random_list(planned_length, start, stop):
    randoms1=[]
    while(len(randoms1)<planned_length):
        random_number = random.randint(start, stop-1)
        if not random_number in randoms1 and no_of_books[random_number]>=min_num_of_user_books:
            randoms1.append(random_number)
    return randoms1  

random_user_indexes = get_random_list(no_of_users_for_test, 0, len(no_of_books))

i=0
for user_index in random_user_indexes:
    user_no_of_books = no_of_books[user_index]
   
    no_of_books_learn[i]=math.ceil(learn_to_test_ratio*user_no_of_books)
    no_of_books_test[i]=user_no_of_books-no_of_books_learn[i]
    rand_indexes = random.sample(range(0,user_no_of_books),user_no_of_books)
    mf_learn = matr_mf[user_index][rand_indexes[0:no_of_books_learn[i]]]
    mf_test = matr_mf[user_index][rand_indexes[no_of_books_learn[i]:user_no_of_books]]
    book_id_learn = matr_book_id[user_index][rand_indexes[0:no_of_books_learn[i]]]
    book_id_test = matr_book_id[user_index][rand_indexes[no_of_books_learn[i]:user_no_of_books]]
    
    if(no_of_books_learn[i]<max_no_of_books):
        to_add_num=max_no_of_books - no_of_books_learn[i]
        mf_learn = np.append(mf_learn, [-1] * to_add_num)
        book_id_learn = np.append(book_id_learn, [-1] * to_add_num)

        
    if(no_of_books_test[i]<max_no_of_books):
        to_add_num=max_no_of_books - no_of_books_test[i]
        mf_test = np.append(mf_test, [-1] * to_add_num)
        book_id_test = np.append(book_id_test, [-1] * to_add_num)
    
    j=0
    for book_id in book_id_test:
        if mf_test[j] >= 0.5:
            matr_book_id_test_liked[i][j] = book_id
        j=j+1
    
    matr_mf_learn[i]= mf_learn
    matr_mf_test[i]= mf_test  
    matr_book_id_learn[i]= book_id_learn
    matr_book_id_test[i]= book_id_test

    
    i=i+1
    

    
    
    
    
    
################################### Wyznaczenie top rekomendacji CB #####################################################    
    
    
recoms_cb=create_recom_cb(no_of_books_learn, matr_mf_learn, matr_book_id_learn)    
sorted_recoms_cb = get_top_recoms_cb(top, recoms_cb)      
    

################################### Wyznaczenie top rekomendacji CF #####################################################  

users_for_selected_user = get_users_for_user(matr_book_id_learn, no_of_books_learn)
users_selected_similarity = calculate_users_similarity(users_for_selected_user, no_of_books_learn, matr_mf_learn, matr_book_id_learn)
sorted_similar_users, sorted_similar_users_similarity = get_top_n_users(users_selected_similarity, users_for_selected_user, top_users)  
recoms_cf = get_recom_by_nearest_users(sorted_similar_users, sorted_similar_users_similarity, top, no_of_books_learn, matr_book_id_learn)


################################### Wyznaczenie top rekomendacji H #####################################################   
selected_users_nearest_books = get_books_by_nearest_users(sorted_similar_users)
recoms_h = create_recom_h(no_of_books_learn, matr_mf_learn, matr_book_id_learn, selected_users_nearest_books)
sorted_recoms_h = get_top_recoms_h(top, recoms_h)  
    
################################## Ewaluacja ##########################################################################

true_positive=[]  #prawidłowo rekomendowane
false_positive=[] #nieprawidłowo rekomendowane
false_negative=[] #brak rekomendacji choc byc powinna
precision=[]
recall=[]
f1_measure=[]
i=0
n=30
for recom in sorted_recoms_h:
    liked_user_books = set(matr_book_id_test_liked[i][matr_book_id_test_liked[i] != 0])
    recom_book_ids = set(recom)
    
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
    




    
    
    
    
    
    
max_no_of_books = max(no_of_books)

matr_mf = np.zeros(([len(ratings_for_users_copy), max_no_of_books]))  # macierz z mf
matr_book_id=np.zeros(([len(ratings_for_users_copy), max_no_of_books]), dtype=int)  # macierz z id ksiązek
i=0
for user in ratings_for_users_copy:
    print(i)
    j=0
    for rating1 in user.iterrows():
        matr_book_id[i,j]=rating1[1]["book_id"]
        matr_mf[i,j]=rating1[1]["mf"]
        j=j+1
    i=i+1



 
        
   
ratings_for_users_active=[]  # posiadający więcej niż min_no_of_books książek
min_no_of_books=15
for rating in ratings_for_users:
    if len(rating)>min_no_of_books:
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