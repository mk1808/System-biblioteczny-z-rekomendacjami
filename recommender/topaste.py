
def create_recom_cb(no_of_books_selected, matr_mf_selected, matr_book_id_selected):
     
    recoms1=np.zeros([len(no_of_books_selected), len(all_books_ids_mapped)], dtype=int)
    ik = 0
    while ik < len(no_of_books_selected):
        
        known_books_ids = matr_book_id_selected[ik]
        known_books_count = no_of_books_selected[ik]
        print(ik)
        max_prediction=0.001
        jk = 1
        while jk < len(all_books_ids_mapped):
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    

                recoms1[ik][jk]=sum_value
                '''
            else:
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    
                if sum_value>max_prediction:
                    max_prediction=sum_value
'''
            jk = jk+1
        '''
        jk = 0
        while jk < len(all_books_ids_mapped): 
            recoms1[ik][jk]=recoms1[ik][jk]/max_prediction
            jk = jk+1 
            '''
        ik = ik+1
    return recoms1


top = 100

def get_top_recoms_cb(top, recoms1):
    sorted_recoms=np.zeros([len(recoms1), top], dtype=int)
    i=0
    for recom in recoms1:    
        sorted_recoms[i]=np.array(all_books_ids_mapped)[np.argsort(recom)[::-1][:top]]
        #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]     
        i=i+1
        print(i) 
    return sorted_recoms



def get_recom_by_nearest_users(sorted_users, sorted_users_similarity, top, no_of_books_selected, matr_book_id_selected):
    
    recoms_cf=np.zeros([len(sorted_users), top],  dtype=int)
    i=0
    for user_sorted_users in sorted_users:   # sorted_users - indexy najbliższych uzytkownikow
        j=0
        books_recom = [0] * (len(short_book_tags) + 1)
 
        known_books_ids = matr_book_id_selected[i][0:no_of_books_selected[i]]
        
        for similar_user in user_sorted_users[user_sorted_users>-1]: 
            similar_users_similarity = sorted_users_similarity[i][j]
            
            k=0
            simil_user_books_ids = matr_book_id[similar_user][0:no_of_books[similar_user]]
            simil_user_books_mf =  matr_mf[similar_user][0:no_of_books[similar_user]]
            for book_mf in simil_user_books_mf:             
                book_id = simil_user_books_ids[k]
                if not (book_id in known_books_ids):
                    books_recom[book_id] = books_recom[book_id] + book_mf * similar_users_similarity
                k=k+1
            
            j=j+1  
            
            
        recoms_cf[i]=np.argsort(books_recom)[::-1][:top_recoms]

        i=i+1
        print(i) 
    return recoms_cf






################################################# liczenie predykcji ######################################################
def create_recom_h(no_of_books_selected, matr_mf_selected, matr_book_id_selected, selected_users_nearest_books):
    recoms_h=np.zeros([len(no_of_books_selected), len(all_books_ids_mapped)])
    ik = 0
    while ik < len(no_of_books_selected):
        
        known_books_ids = matr_book_id_selected[ik]
        known_books_count = no_of_books_selected[ik]
        print(ik)
        max_prediction=0.001
        jk = 0
        while jk < len(all_books_ids_mapped):
            if selected_users_nearest_books[ik][jk] == 0:
                jk = jk+1
                continue
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    

                recoms_h[ik][jk]=sum_value
            else:
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    
                if sum_value>max_prediction:
                    max_prediction=sum_value

            jk = jk+1
        
        jk = 0
        while jk < len(all_books_ids_mapped): 
            recoms_h[ik][jk]=recoms_h[ik][jk]/max_prediction
            jk = jk+1            
        ik = ik+1
    return recoms_h


user_limit=5000
print(datetime.now())
recoms_h=np.zeros([len(ratings_for_users_copy), len(all_books_ids_mapped)])
create_recom_h(ratings_for_users_copy[0:user_limit])
print(datetime.now())


############################################################# liczenie top n ##############################################

top = 100
sorted_recoms_h=np.zeros([len(ratings_for_users_copy), top],  dtype=int)

def get_top_recoms_h(top, recoms1):
    sorted_recoms_h=np.zeros([len(recoms1), top],  dtype=int)
    i=0
    for recom in recoms1:    
        sorted_recoms_h[i]=np.array(all_books_ids_mapped)[np.argsort(recom)[::-1][:top]]
        #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]     
        i=i+1
        print(i) 
    return sorted_recoms_h 




















def get_random_list(planned_length, start, stop):
    randoms1=[]
    while(len(randoms1)<planned_length):
        random_number = random.randint(start, stop-1)
        if not random_number in randoms1 and no_of_books[random_number]>=min_num_of_user_books:
            randoms1.append(random_number)
    return randoms1  




################################### Wyznaczenie top rekomendacji CB #####################################################    
    
def get_recoms_cb(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked):
    
    recoms_cb=create_recom_cb(no_of_books_learn, matr_mf_learn, matr_book_id_learn)    
    sorted_recoms_cb = get_top_recoms_cb(top, recoms_cb)      
    return sorted_recoms_cb

################################### Wyznaczenie top rekomendacji CF #####################################################  
def get_recoms_cf(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked):
    
    users_for_selected_user = get_users_for_user(matr_book_id_learn, no_of_books_learn)
    users_selected_similarity = calculate_users_similarity(users_for_selected_user, no_of_books_learn, matr_mf_learn, matr_book_id_learn)
    sorted_similar_users, sorted_similar_users_similarity = get_top_n_users(users_selected_similarity, users_for_selected_user, top_users)  
    recoms_cf = get_recom_by_nearest_users(sorted_similar_users, sorted_similar_users_similarity, top, no_of_books_learn, matr_book_id_learn)
    return recoms_cf

################################### Wyznaczenie top rekomendacji H #####################################################   
def get_recoms_h(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked):
    users_for_selected_user = get_users_for_user(matr_book_id_learn, no_of_books_learn)
    users_selected_similarity = calculate_users_similarity(users_for_selected_user, no_of_books_learn, matr_mf_learn, matr_book_id_learn)
    sorted_similar_users, sorted_similar_users_similarity = get_top_n_users(users_selected_similarity, users_for_selected_user, top_users)  
 
    selected_users_nearest_books = get_books_by_nearest_users(sorted_similar_users)
    recoms_h = create_recom_h(no_of_books_learn, matr_mf_learn, matr_book_id_learn, selected_users_nearest_books)
    sorted_recoms_h = get_top_recoms_h(top, recoms_h)  
    return sorted_recoms_h    






################################## Ewaluacja ##########################################################################
def evaluate(recoms, matr_book_id_test_liked, no_of_books_learn, no_of_books_test):
    learning_set_size = [] 
    testing_set_size = [] 
    
    true_positive=[]  #prawidłowo rekomendowane
    false_positive=[] #nieprawidłowo rekomendowane
     #prawidłowo niezarekomendowane
    false_negative=[] #brak rekomendacji choc byc powinna
    precision=[]
    recall=[]
    f1_measure=[]
    i=0
    for recom in recoms:
        print(i)
        liked_user_books = set(matr_book_id_test_liked[i][matr_book_id_test_liked[i] != 0])
        if len(liked_user_books)==0:
            continue
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
        
        learning_set_size.append(no_of_books_learn[i])
        testing_set_size.append(no_of_books_test[i])
        i=i+1
    
    mean_precision=np.mean(precision)
    mean_recall=np.mean(recall)
    mean_f1_measure=np.mean(f1_measure)    
        
    final_results = []
    final_results.append(true_positive)
    final_results.append(false_positive)
    final_results.append(false_negative)
    final_results.append(precision)
    final_results.append(recall)
    final_results.append(f1_measure)
    final_results.append(list([mean_precision]))
    final_results.append(list([mean_recall]))
    final_results.append(list([mean_f1_measure]))
    final_results.append(learning_set_size)
    final_results.append(testing_set_size)
    return final_results


test_type_name="cb_halftriang_fst"
os.mkdir(test_type_name)
for test_no in range(0,nof_of_tests):
   no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked = prepare_random_users()
   recoms = get_recoms_cb(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked)
  # recoms = get_recoms_cf(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked)
  # recoms = get_recoms_h(no_of_books_learn, no_of_books_test, matr_mf_learn, matr_mf_test, matr_book_id_learn, matr_book_id_test, matr_book_id_test_liked)   
   final_results1 = evaluate(recoms, matr_book_id_test_liked, no_of_books_learn, no_of_books_test)
   path = os.path.join(test_type_name,"final_results_"+str(test_no)+".csv")
   pd.DataFrame(final_results1).to_csv(path)  
   print(path)







