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
tags_for_books = []
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
all_users_ids = ratings['user_id'].unique()

ratings_for_users = []
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

all_tags_ids_copy = copy.deepcopy(all_tags_ids)

book_tags_df = pd.DataFrame(np.zeros([1, len(all_tags_ids_copy)]), columns=all_tags_ids_copy)  
i=0
final_books_tags=[]
short_book_tags=[]
for book_test in tags_for_books_copy:    
    book_tags_df_copy = copy.deepcopy(book_tags_df)
    
    bt=book_test
    my_df = pd.DataFrame(np.zeros([1, len(book_test)]), columns=list(book_test['tag_id']))  
    j=0
    for tag in book_test.iterrows():
        tag1=tag
        book_tags_df_copy[tag[1]['tag_id']] = tag[1]['mf']
        my_df[tag[1]['tag_id']] = tag[1]['mf']
    final_books_tags.append(book_tags_df_copy)
    short_book_tags.append(my_df)
    i=i+1
    print(i)
    
    
    
#####################################3SIMILARITIES



i=0
j=0
single_row = []
no_of_books = len(final_books_tags)
similarity = np.zeros([no_of_books, no_of_books])
for book_i in short_book_tags:
    j=0
    for book_j in short_book_tags:
        if similarity[i,j] !=0:
            j=j+1
            #print("continued")
            continue
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
    

    
    
    
    ##################################################################wyłonienie lubianych + fix danych

    liked_ratings_for_users=copy.deepcopy(ratings_for_users)


    i=0
    for any_rating in liked_ratings_for_users:
        i=0
        for single_any_rating in any_rating.iterrows():  
            if single_any_rating[1]["mf"]==None:
                single_any_rating[1]["mf"]=1
                any_rating.iloc[i]= single_any_rating[1]
            i=i+1  
            
            
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
        
        
        
        
######################################################################################przewidywane oceny


#liczone po lubianych!
ratings_for_users_copy = copy.deepcopy(ratings_for_users)
all_books_ids_mapped = []
for b_id in all_books_ids:
    #print(b_id)
    all_books_ids_mapped.append(goodreads_to_id(b_id))

    
all_books_ids_mapped = np.array(all_books_ids_mapped)
all_books_ids_list = list(all_books_ids_mapped)

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

#######przygotowanie uprosczonych macierzy##########################    
no_of_books = []
for user in ratings_for_users_copy:
    no_of_books.append(len(user))
max_no_of_books = max(no_of_books)



matr_mf = np.zeros(([len(ratings_for_users_copy), max_no_of_books])) 
matr_book_id=np.zeros(([len(ratings_for_users_copy), max_no_of_books]), dtype=int) 
i=0
for user in ratings_for_users_copy:
    print(i)
    j=0
    for rating1 in user.iterrows() :
        a=1
        matr_book_id[i,j]=rating1[1]["book_id"]
        matr_mf[i,j]=rating1[1]["mf"]
        j=j+1
    i=i+1

##################czytanie z tablicy zapisanej##################################
similarity = pd.read_csv("new_file_all_simil.csv").to_numpy()
similarity = np.delete(similarity, 0, axis=1)

#############3
max_id = max(all_books_ids_mapped)
ids_mapped_reverted = [0] * (max_id+1)
ii=0
for id1 in all_books_ids_mapped:
    ids_mapped_reverted[id1] = ii
    ii=ii+1
    
 
recom_books=[]
recom_users=[]
recom_predictions=[]
recoms1=np.zeros([len(users_ids), len(all_books_ids_mapped)])
max_predictions=np.zeros([len(users_ids), ])
def create_recom2(ratings_for_users):
    ik = 0
    while ik < len(ratings_for_users):
        user_id = users_ids[ik]

        known_books_ids = matr_book_id[ik]
        known_books_count = no_of_books[ik]
        print(ik)
        j = 0
        jk = 0
        while jk < len(all_books_ids_mapped):
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0

               
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]] , jk]
                    mf = matr_mf[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1
                    

                recoms1[ik][jk]=sum_value


            j = j+1
            jk = jk+1
            
        ik = ik+1
    return


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
            # for book_id in all_books_ids_mapped:#grid
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                sum_value = 0

                book_in_tab_index = book_indexes[j]
                kk = 0
                while kk < len(known_books_ids):
                    #for known_book_id in known_books_ids:
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

                #mutex.acquire()
                recommendations[i].iloc[j]["user_id"] = user_id
                recommendations[i].iloc[j]["book_id"] = book_id
                recommendations[i].iloc[j]["predicted_rating"] = sum_value
                #mutex.release()

            j = j+1
            jk = jk+1
        ik = ik+1
    return


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



user_limit=100
print(datetime.now())
create_recom(ratings_for_users_copy[0:user_limit])
print(datetime.now())

user_limit=1000
print(datetime.now())
create_recom2(ratings_for_users_copy[0:user_limit])
print(datetime.now())

print(datetime.now())
create_max_recom_value(ratings_for_users_copy[0:user_limit])
print(datetime.now())

############################################################ normalization
max_predictions=np.zeros([len(users_ids), ])
i=0
for recom in recoms1[0:user_limit]:
    max_predictions[i] = max(recom)    
    i=i+1 

pd.DataFrame(recoms1).to_csv("recoms1.csv")


normalized_recoms1=np.zeros([len(users_ids), len(all_books_ids_mapped)])
def normalize():
    i=0
    for recom in recoms1[0:user_limit]:
        j=0
        print(i)
        max_value = max_predictions[i]
        for single_recom in recom:
            if not single_recom==None:     
                normalized_recoms1[i][j]=single_recom/float(max_value)
            j=j+1
        i=i+1


normalize()
top = 100
sorted_recoms=np.zeros([len(users_ids), top])
################### top n
i=0
for recom in normalized_recoms1[0:user_limit]:    
    sorted_recoms[i]=np.array(all_books_ids_mapped)[np.argsort(recom)[::-1][:100]]
    for_checking = normalized_recoms1[i][np.argsort(recom)[::-1][:100]]     
    i=i+1
    print(i) 
    
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