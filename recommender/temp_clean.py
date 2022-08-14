import pandas as pd
import random
import math
import copy
import numpy as np
import sys
import multiprocessing
#from joblib import Parallel
import threading
#from threading import Thread, Lock
from datetime import datetime
from multiprocessing import Process

def get_gaussian_membership(rk, L):
    alpha=1.2
    sqr = math.sqrt(alpha*L*(rk-1))
    mf = rk/float(pow(2, sqr))
    #round(mf, 2)
    return mf

def get_half_traingular_membership(min, max, r):
    return (r-min)/float(max-min)

def book_id_to_goodreads(old_id):
    return books[books["id"]==old_id]["book_id"].iloc[0]

def goodreads_to_id(b_id):
    result = books[books["book_id"]==b_id]["id"]
    if result.size  < 1:
        print(result)
    return result.iloc[0] if result.size>0 else 0

###############################################Pobranie i przygotowanie danych#########################
books = pd.read_csv('data/books.csv', low_memory=False)
book_tags = pd.read_csv('data/book_tags.csv', low_memory=False)
ratings = pd.read_csv('data/ratings.csv', low_memory=False)
tags = pd.read_csv('data/tags.csv', low_memory=False)
to_read = pd.read_csv('data/to_read.csv', low_memory=False)

book_tags.sort_values(by=['goodreads_book_id'], inplace=True)
book_tags.groupby('goodreads_book_id')
all_books_ids = book_tags['goodreads_book_id'].unique()



#ids_only = book_tags['goodreads_book_id']
#only_one = book_tags[book_tags['goodreads_book_id']==1]
#tags_for_books.append(only_one)
###########################################Obliczanie mf dla gatunku w książce########################3
tags_for_books = []  #dla każdej książki tagi jakie w niej są 
for single_id in all_books_ids:
    group = book_tags[book_tags['goodreads_book_id']==single_id]
    group.sort_values(by=['count'], ascending=False, inplace=True)
    tags_for_books.append(group)

i=0
for book in tags_for_books:
    #book = book.reset_index()
    book["mf"]=0.0
    rk=1
    j=0
    print(i)
    for tag in book.iterrows():###########################################Obliczanie mf dla gatunku w książce########################3
        #random_num = random.randint(0, 1000)   
        tag[1]["mf"] = get_gaussian_membership(rk, len(book))
        book.iloc[j] = tag[1]
        rk=rk+1
        j=j+1
        
    tags_for_books[i] = book
    i=i+1
    
    
    

########################################### Obliczanie mf dla preferencji uzytkownika wobec książki ########################3
############################################users#########################################################

ratings.sort_values(by=['user_id'], inplace=True)
ratings.groupby('user_id')
all_users_ids = ratings['user_id'].unique() #wszystkie id uzytkownikow

ratings_for_users = [] #dla każdego użytkownika lista książek przez niego ocenionych
for single_user_id in all_users_ids:
    one_group = ratings[ratings['user_id']==single_user_id]
    ratings_for_users.append(one_group)
    

i=0
for rating in ratings_for_users:
    print(i)

    #rating = rating.reset_index()
    rating.sort_values(by=['rating'], ascending=False, inplace=True)
    user_max = rating.iloc[0]['rating']
    user_min = rating.iloc[len(rating)-1]['rating']
    
    if user_max == user_min:
        rating["mf"]=1.0
    else:
        rating["mf"]=0.0
        j=0
        for single_book_rating in rating.iterrows():   
            single_book_rating[1]["mf"] = get_half_traingular_membership(user_min, user_max, single_book_rating[1]["rating"])
            rating.iloc[j] = single_book_rating[1]
            j=j+1
  
    ratings_for_users[i] = rating
    i=i+1   
    
    
    ######################macierz ze wszystkimi tagami i mf dla kazdej ksiązki, short - tylko z wystepujacymi
new_book_tags = book_tags.sort_values(by=['tag_id'], inplace=False)
all_tags_ids = new_book_tags['tag_id'].unique()
tags_for_books_copy = copy.deepcopy(tags_for_books)

#book_tags_df = pd.DataFrame(np.zeros([1, len(all_tags_ids)]), columns=all_tags_ids)  
i=0
#final_books_tags=[]
short_book_tags=[]
for book_test in tags_for_books_copy:    
    #book_tags_df_copy = copy.deepcopy(book_tags_df)
    
    my_df = pd.DataFrame(np.zeros([1, len(book_test)]), columns=list(book_test['tag_id']))  
    j=0
    for tag in book_test.iterrows():
        #book_tags_df_copy[tag[1]['tag_id']] = tag[1]['mf']
        my_df[tag[1]['tag_id']] = tag[1]['mf']
    #final_books_tags.append(book_tags_df_copy)
    short_book_tags.append(my_df)
    i=i+1
    print(i)
    
    
    
#####################################3SIMILARITIES



i=0
j=0
single_row = []
no_of_books = len(short_book_tags)
similarity = np.zeros([no_of_books, no_of_books])
for book_i in short_book_tags:
    j=0
    for book_j in short_book_tags[i:no_of_books]:
        #if similarity[i,j] !=0:
            #j=j+1
            #print("continued")
            #continue
        col_j = book_j.columns
        col_i = book_i.columns
        diff_ij = col_i.difference(col_j)
        diff_ji = col_j.difference(col_i)
        common = col_i.intersection(col_j)
        sum_min=0
        sum_max=0
      
        for single_col in diff_ij:
            sum_max = sum_max + book_i[single_col][0]
        
        for single_col in diff_ji:
            sum_max = sum_max + book_j[single_col][0]    
            
        for single_col in common:
            val_i=book_i[single_col][0]
            val_j=book_j[single_col][0]
            min1 = min(val_i, val_j)
            max1 = max(val_i, val_j)
            sum_min=sum_min+min1
            sum_max=sum_max+max1
        '''     
    sum_max
        all_col = list(set(col_j + col_i))#
    
        for col in all_col:
            it=0
            try:#
                val_i=book_i[col][0]
            except:
                val_i=float(0.0)
            try:
                val_j=book_j[col][0]
            except:
                val_j=float(0.0)
           # break;
            min1 = min(val_i, val_j)
            max1 = max(val_i, val_j)
            sum_min=sum_min+min1
            sum_max=sum_max+max1
        '''
        #if sum_min != 0 and sum_max != 0:
        #    print("a")
        if sum_max == 0:
            simil = 0
        else: 
            simil = float(sum_min)/float(sum_max)
        similarity[i,j] = simil
        similarity[j,i] = simil
        
        j=j+1
        
    
    i=i+1    
    print(i)    
    

    
    
    
    ##################################################################wyłonienie lubianych + usunięcie indexu
    liked_ratings_for_users=copy.deepcopy(ratings_for_users)


    # i=0
    # for any_rating in liked_ratings_for_users:
    #     i=0
    #     for single_any_rating in any_rating.iterrows():  
    #         if single_any_rating[1]["mf"]==None:
    #             single_any_rating[1]["mf"]=1
    #             any_rating.iloc[i]= single_any_rating[1]
    #         i=i+1  
            
            
    i=0
    for any_rating in liked_ratings_for_users:
        print(i)
        any_rating = any_rating.reset_index()
        j=0
        for single_any_rating in any_rating.iterrows():  
            
            if not ((single_any_rating[1]["mf"])>0.5):
                any_rating = any_rating.drop(j) 
            j=j+1
        
        liked_ratings_for_users[i] = any_rating
        i=i+1   
        

    i=0
    for any_rating in liked_ratings_for_users:
        print(i)
        if 'index' in any_rating.columns:
            liked_ratings_for_users[i]=any_rating.drop('index', 1)
        i=i+1 
        
        
        
        
#############################################################mapowanie id ksiazek i uzytkownikow#########


ratings_for_users_copy = copy.deepcopy(ratings_for_users)
all_books_ids_mapped = []
for b_id in all_books_ids:
    #print(b_id)
    all_books_ids_mapped.append(goodreads_to_id(b_id))

    
all_books_ids_mapped = np.array(all_books_ids_mapped)
all_books_ids_list = list(all_books_ids_mapped)

max_id = max(all_books_ids_mapped)
ids_mapped_reverted = [0] * (max_id+1) #przechowuje indeks pod ktorym dane id jest w tablicy all_books_ids_mapped
ii=0
for id1 in all_books_ids_mapped:
    ids_mapped_reverted[id1] = ii
    ii=ii+1
    
    
max_id = max(all_users_ids)
ids_users_mapped_reverted = [0] * (max_id+1) #przechowuje indeks pod ktorym dane id jest w tablicy all_users_ids
ii=0
for id1 in all_users_ids:
    ids_users_mapped_reverted[id1] = ii
    ii=ii+1


####TODO refactor start (sprawdzic czy to jest uzywane)
df2 = pd.DataFrame([[None,None, None]], columns=list(["user_id", "book_id", "predicted_rating"]))


i=0
for_all_books_df = df2
for user_recom in all_books_ids_mapped[0:len(all_books_ids_mapped)-1]:
    for_all_books_df = pd.concat([for_all_books_df, copy.deepcopy(df2)])
    

recommendations=[]
for user in ratings_for_users_copy:
  
    recommendations.append(copy.deepcopy(for_all_books_df))    
    

book_indexes=[]
index=0
for book_id in all_books_ids_mapped:
    
    book_in_tab = all_books_ids_mapped.index(book_id)
    book_indexes.append(all_books_ids_list.index(book_in_tab))
    index=index+1
    
users_ids=[]
for user in ratings_for_users_copy:
    if len(user["user_id"]) > 0:
        users_ids.append(user["user_id"].iloc[[0]].iloc[0])
####TODO refactor end

#######przygotowanie 3 uprosczonych macierzy##########################    
no_of_books = [] # liczby ksiazek oceniionych przez uzytkownikow
for user in ratings_for_users_copy:
    no_of_books.append(len(user))
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

##################czytanie z tablicy zapisanej##################################
similarity = pd.read_csv("new_file_all_simil.csv").to_numpy()
similarity = np.delete(similarity, 0, axis=1)

############rekomendacja##################################################3

    
 
recoms1=np.zeros([len(ratings_for_users_copy), len(all_books_ids_mapped)])
def create_recom2(ratings_for_users):
    ik = 0
    while ik < len(ratings_for_users):
        user_index = ids_users_mapped_reverted[ratings_for_users[ik]["user_id"].iloc[0]]
        known_books_ids = matr_book_id[user_index]
        known_books_count = no_of_books[user_index]
        print(ik)
        max_prediction=0.001
        jk = 0
        while jk < len(all_books_ids_mapped):
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf[user_index][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    

                recoms1[ik][jk]=sum_value
            else:
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf[user_index][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    
                if sum_value>max_prediction:
                    max_prediction=sum_value

            jk = jk+1
        
        jk = 0
        while jk < len(all_books_ids_mapped): 
            recoms1[ik][jk]=recoms1[ik][jk]/max_prediction
            jk = jk+1            
        ik = ik+1
    return

'''
def create_recom(ratings_for_users):
    ik = 0
    while ik < len(ratings_for_users):
      #  for ratings_of_user in ratings_for_users:
        ratings_of_user = ratings_for_users[ik]
        user_id = ratings_of_user["user_id"].iloc[[0]].iloc[0]
        i = users_ids.index(user_id)
        known_books_ids = list(ratings_of_user["book_id"])
        print(i)
        j = 0
        jk = 0
        
        while jk < len(all_books_ids_mapped):
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0

                book_in_tab_index = book_indexes[j]
                kk = 0
                while kk < len(known_books_ids):
                    known_book_id = known_books_ids[kk]
                    known_book_in_tab = all_books_ids_mapped[all_books_ids_mapped ==
                                                              known_book_id][0]
                    known_book_in_tab_index = all_books_ids_list.index(
                        known_book_in_tab)

                    books_similarity = similarity[known_book_in_tab_index,
                                                  book_in_tab_index]
                    mf = ratings_of_user[ratings_of_user["book_id"]
                                          == known_book_id]["mf"].iloc[0]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1

                recommendations[i].iloc[j]["user_id"] = user_id
                recommendations[i].iloc[j]["book_id"] = book_id
                recommendations[i].iloc[j]["predicted_rating"] = sum_value
            

            j = j+1
            jk = jk+1
        ik = ik+1
    return
'''

'''
max_values_for_users = []
def create_max_recom_value(ratings_for_users):
    
    ik=0
    while ik<len(ratings_for_users):  
  #  for ratings_of_user in ratings_for_users:
        ratings_of_user =  ratings_for_users[ik]
        user_id=ratings_of_user["user_id"].iloc[[0]].iloc[0]
        i = users_ids.index(user_id)
        known_books_ids = list(ratings_of_user["book_id"])
        print(i)  
        j=0
        jk=0
        max_prediction=0.001
        while jk<len(known_books_ids): 
            # for book_id in all_books_ids_mapped:#grid
           
            known_book_id=known_books_ids[jk]
            known_book_in_tab = all_books_ids_mapped[all_books_ids_mapped==known_book_id][0]
            book_in_tab_index = all_books_ids_list.index(known_book_in_tab) 
           
            sum_value=0
   
            kk=0
            while kk<len(known_books_ids): 
            #for known_book_id in known_books_ids:
                known_book_id=known_books_ids[kk]
                known_book_in_tab = all_books_ids_mapped[all_books_ids_mapped==known_book_id][0]
                known_book_in_tab_index = all_books_ids_list.index(known_book_in_tab)
                
                books_similarity = similarity[known_book_in_tab_index, book_in_tab_index]
                mf = ratings_of_user[ratings_of_user["book_id"]==known_book_id]["mf"].iloc[0]
                sum_value = sum_value + mf * books_similarity
                kk=kk+1
                if sum_value>max_prediction:
                    max_prediction=sum_value
            
            j=j+1
            jk=jk+1
        max_values_for_users.append(max_prediction)
        ik=ik+1
    return
'''


user_limit=5000
print(datetime.now())
recoms1=np.zeros([len(ratings_for_users_copy), len(all_books_ids_mapped)])
create_recom2(ratings_for_users_copy[0:user_limit])
print(datetime.now())
pd.DataFrame(recoms1).to_csv("recoms2.csv")

################### top n
top = 100
sorted_recoms=np.zeros([len(ratings_for_users_copy), top])

i=0
for recom in recoms1[0:user_limit]:    
    sorted_recoms[i]=np.array(all_books_ids_mapped)[np.argsort(recom)[::-1][:top]]
    #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]     
    i=i+1
    print(i) 

    
#########################################################CF###################################3
users_similar = []

max_id = max(all_books_ids_mapped)
ids_mapped_reverted = [0] * (max_id+1)
ii=0
for id1 in all_books_ids_mapped:
    ids_mapped_reverted[id1] = ii
    ii=ii+1
    
    
books_for_user = [0] * (len(liked_ratings_for_users)) 
mf_books_for_user = [0] * (len(liked_ratings_for_users)) 
i=0
for user2 in liked_ratings_for_users:
    print(i)
    for_single_user=[]
    mf_for_single_user=[]
    for user_book in user2.iterrows():
        for_single_user.append(user_book[1]["book_id"])
        mf_for_single_user.append([user_book[1]["book_id"], user_book[1]["mf"]])
        
    books_for_user[i]=np.array(for_single_user)
    mf_books_for_user[i]=np.array(mf_for_single_user)
    i=i+1
    
users_for_user = [0] * (len(liked_ratings_for_users)) 
i=0
for user3 in books_for_user:
    print(i)
    for_single_user=[]
    j=0
    for user4 in books_for_user:
        if list(set(user3).intersection(user4)):
            for_single_user.append(j)
        j=j+1    
        
    users_for_user[i]=for_single_user
    i=i+1

max_id = max(all_users_ids)
ids_users_mapped_reverted = [0] * (max_id+1)
ii=0
for id1 in all_users_ids:
    ids_users_mapped_reverted[id1] = ii
    ii=ii+1

i=0
j=0
single_row = []
no_of_users = len(liked_ratings_for_users)
similarity_u = copy.deepcopy(users_for_user)

for user_i in users_for_user:
    j=0
    ind_i=ids_users_mapped_reverted[i]
    liked_i = books_for_user[ind_i]
    mf_i = mf_books_for_user[ind_i]
    for user_j in user_i:
        ind_j=ids_users_mapped_reverted[user_j]
        liked_j = books_for_user[ind_j]
        mf_j = mf_books_for_user[ind_j]

        diff_ij = np.setdiff1d(liked_i, liked_j, assume_unique=False)
       # liked_i.difference(liked_j)
        diff_ji = np.setdiff1d(liked_j, liked_i, assume_unique=False)
        common = liked_i[np.in1d(liked_i, liked_j)]
         #liked_i.intersection(liked_j)
        
        sum_min=0
        sum_max=0
        k=0
       
        for liked_book_j in diff_ij:           
            sum_max = sum_max + np.array(mf_i)[np.array(mf_i)[:,0]==liked_book_j][0][1]
        for liked_book_j in diff_ji:
            sum_max = sum_max + np.array(mf_j)[np.array(mf_j)[:,0]==liked_book_j][0][1]
            
        for single_col in common:
            val_i=np.array(mf_i)[np.array(mf_i)[:,0]==single_col][0][1]
            val_j=np.array(mf_j)[np.array(mf_j)[:,0]==single_col][0][1] 
            min1 = min(val_i, val_j)
            max1 = max(val_i, val_j)
            sum_min=sum_min+min1
            sum_max=sum_max+max1

        if sum_max == 0:
            simil = 0
        else: 
            simil = float(sum_min)/float(sum_max)
        similarity_u[i][j] = simil
       
        
       
        
        j=j+1
        
    
    i=i+1    
    print(i) 
    break





    
max_users=0
ids_books_for_user = [0] * (len(liked_ratings_for_users)) 
i=0
for user5 in users_for_user:
    from_one_user = []
    for single_user2 in user5: 
        from_one_user.append(liked_ratings_for_users[single_user2])
    ids_books_for_user[i]=from_one_user
    i=i+1
    print(i)

    
book_index = ids_mapped_reverted[no]
single_user[book_index] = 1

    
'''        
    recom.sort_values(by=['normalized'], ascending=False, inplace=True)
    
recoms5 = copy.deepcopy(recommendations)
i=0
n=100
for recom in recoms5:
    recoms5[i] = recom[0:n]
    i=i+1
    
for recom in recommendations[0:user_limit]:
    j=0
    print(i)
    recom["normalized"]=0.0
    max_value = max_values_for_users[i]
    for single_recom in recom.iterrows():
        if not single_recom[1]["book_id"]==None: 
            predicted_value = single_recom[1]["predicted_rating"]
            single_recom[1]["normalized"]=predicted_value/float(max_value)
            recom.iloc[j]=single_recom[1]
        j=j+1
    recommendations[i] = recom
    i=i+1


################### top n
for recom in recommendations[0:user_limit]:    
    recom.sort_values(by=['normalized'], ascending=False, inplace=True)
    
    
recoms5 = copy.deepcopy(recommendations)
i=0
n=100
for recom in recoms5:
    recoms5[i] = recom[0:n]
    i=i+1
'''