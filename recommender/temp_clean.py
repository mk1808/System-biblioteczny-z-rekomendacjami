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
from sklearn.metrics import roc_curve
from sklearn.metrics import roc_auc_score
import matplotlib
import skfuzzy
#skfuzzy.membership.smf(list(range(1,10)), 1, 10)
#roc_auc_score()


def get_gaussian_membership(rk, L):
  #  alpha=1.2

    alpha = 0.22
    sqr = math.sqrt(alpha*L*(rk-1))
    mf = rk/float(pow(2, sqr))
    #round(mf, 2)
    return mf


def get_half_traingular_membership(min, max, r):
    return (r-min)/float(max-min)


def get_half_traingular_membership_desc(min, max, r):
    return (1 - get_half_traingular_membership(min, max, r))


def get_S_membership(index, min, max):
    if min <= index and index <= ((min+max)/2):
        return 2 * pow(((index-min)/(max-min)), 2)
    elif index <= min:
        return 0
    elif index >= max:
        return 1
    return 1-(2 * pow(((index-max)/(max-min)), 2))


def get_S_membership_desc(index, min, max):
    return 1-get_S_membership(index, min, max)


def book_id_to_goodreads(old_id):
    return books[books["id"] == old_id]["book_id"].iloc[0]


def goodreads_to_id(b_id):
    result = books[books["book_id"] == b_id]["id"]
    if result.size < 1:
        print(result)
    return result.iloc[0] if result.size > 0 else 0


###############################################Pobranie i przygotowanie danych#########################
books = pd.read_csv('data/books.csv', low_memory=False)
book_tags = pd.read_csv('data/book_tags2.csv', low_memory=False)
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
tags_for_books = []  # dla każdej książki tagi jakie w niej są
for single_id in all_books_ids:
    group = book_tags[book_tags['goodreads_book_id'] == single_id]
    group.sort_values(by=['count'], ascending=False, inplace=True)
    tags_for_books.append(group)


i = 0
for book in tags_for_books:
    #book = book.reset_index()
    book["mf"] = 0.0
    rk = 1
    j = 0
    print(i)
    for tag in book.iterrows():  # Obliczanie mf dla gatunku w książce########################3
        #random_num = random.randint(0, 1000)
       # tag[1]["mf"] = get_gaussian_membership(rk, len(book))
        tag[1]["mf"] = get_half_traingular_membership_desc(0, len(book), rk)

        book.iloc[j] = tag[1]
        rk = rk+1
        j = j+1

    tags_for_books[i] = book
    i = i+1


########################################### Obliczanie mf dla preferencji uzytkownika wobec książki ########################3
############################################users#########################################################

ratings.sort_values(by=['user_id'], inplace=True)
ratings.groupby('user_id')
all_users_ids = ratings['user_id'].unique()  # wszystkie id uzytkownikow

ratings_for_users = []  # dla każdego użytkownika lista książek przez niego ocenionych
for single_user_id in all_users_ids:
    one_group = ratings[ratings['user_id'] == single_user_id]
    ratings_for_users.append(one_group)


i = 0
for rating in ratings_for_users:
    print(i)

    #rating = rating.reset_index()
    rating.sort_values(by=['rating'], ascending=False, inplace=True)
    user_max = rating.iloc[0]['rating']
    user_min = rating.iloc[len(rating)-1]['rating']

    if user_max == user_min:
        rating["mf"] = 1.0
    else:
        rating["mf"] = 0.0
        j = 0
        for single_book_rating in rating.iterrows():
            single_book_rating[1]["mf"] = get_half_traingular_membership(
                user_min, user_max, single_book_rating[1]["rating"])
            rating.iloc[j] = single_book_rating[1]
            j = j+1

    ratings_for_users[i] = rating
    i = i+1

    ######################macierz ze wszystkimi tagami i mf dla kazdej ksiązki, short - tylko z wystepujacymi
new_book_tags = book_tags.sort_values(by=['tag_id'], inplace=False)
all_tags_ids = new_book_tags['tag_id'].unique()
tags_for_books_copy = copy.deepcopy(tags_for_books)

#book_tags_df = pd.DataFrame(np.zeros([1, len(all_tags_ids)]), columns=all_tags_ids)
i = 0
#final_books_tags=[]
short_book_tags = []
for book_test in tags_for_books_copy:
    #book_tags_df_copy = copy.deepcopy(book_tags_df)

    my_df = pd.DataFrame(
        np.zeros([1, len(book_test)]), columns=list(book_test['tag_id']))
    j = 0
    for tag in book_test.iterrows():
        #book_tags_df_copy[tag[1]['tag_id']] = tag[1]['mf']
        my_df[tag[1]['tag_id']] = tag[1]['mf']
    #final_books_tags.append(book_tags_df_copy)
    short_book_tags.append(my_df)
    i = i+1
    print(i)


#####################################3SIMILARITIES

def get_fst_similarity(book_i, book_j):
    col_j = book_j.columns
    col_i = book_i.columns
    diff_ij = col_i.difference(col_j)
    diff_ji = col_j.difference(col_i)
    common = col_i.intersection(col_j)
    sum_min = 0
    sum_max = 0

    for single_col in diff_ij:
        sum_max = sum_max + book_i[single_col][0]

    for single_col in diff_ji:
        sum_max = sum_max + book_j[single_col][0]

    for single_col in common:
        val_i = book_i[single_col][0]
        val_j = book_j[single_col][0]
        min1 = min(val_i, val_j)
        max1 = max(val_i, val_j)
        sum_min = sum_min+min1
        sum_max = sum_max+max1

    if sum_max == 0:
        simil = 0
    else:
        simil = float(sum_min)/float(sum_max)
    return simil


i = 0
j = 0
single_row = []
no_of_books = len(short_book_tags)
similarity = np.zeros([no_of_books, no_of_books])
for book_i in short_book_tags:
    j = 0
    for book_j in short_book_tags[i:no_of_books]:
        simil = get_fst_similarity(book_i, book_j)

        similarity[i, j] = simil
        similarity[j, i] = simil

        j = j+1

    i = i+1
    print(i)

pd.DataFrame(similarity).to_csv("cb_simil_b_gauss_fst.csv")
pd.DataFrame(similarity).to_csv("cb_simil_half_tirang_fst.csv")


def get_euclidean_similarity(book_i, book_j):
   col_j = book_j.columns
   col_i = book_i.columns
   diff_ij = col_i.difference(col_j)
   diff_ji = col_j.difference(col_i)
   common = col_i.intersection(col_j)
   sum1 = 0

   for single_col in diff_ij:
       sum1 = sum1 + book_i[single_col][0]**2

   for single_col in diff_ji:
       sum1 = sum1 + book_j[single_col][0]**2

   for single_col in common:
       val_i = book_i[single_col][0]
       val_j = book_j[single_col][0]
       sum1 = sum1+(val_i-val_j)**2
   return math.sqrt(sum1)


def get_cosine_similarity(book_i, book_j):
   col_j = book_j.columns
   col_i = book_i.columns

   common = col_i.intersection(col_j)
   sum1 = 0
   sum2 = 0
   sum3 = 0
   for single_col in col_i:
       sum1 = sum1 + book_i[single_col][0]**2

   for single_col in col_j:
       sum2 = sum2 + book_j[single_col][0]**2

   for single_col in common:
       val_i = book_i[single_col][0]
       val_j = book_j[single_col][0]
       sum3 = sum3+(val_i*val_j)

   denominator = math.sqrt(sum1) * math.sqrt(sum2)
   if denominator != 0:
       return sum3/denominator
   return 0


pd.DataFrame(similarity).to_csv("cb_simil_b_halftriang_fst.csv")


##################################################################wyłonienie lubianych + usunięcie indexu
liked_ratings_for_users = copy.deepcopy(ratings_for_users)


# i=0
# for any_rating in liked_ratings_for_users:
#     i=0
#     for single_any_rating in any_rating.iterrows():
#         if single_any_rating[1]["mf"]==None:
#             single_any_rating[1]["mf"]=1
#             any_rating.iloc[i]= single_any_rating[1]
#         i=i+1


i = 0
for any_rating in liked_ratings_for_users:
    print(i)
    any_rating = any_rating.reset_index()
    j = 0
    for single_any_rating in any_rating.iterrows():

        if not ((single_any_rating[1]["mf"]) > 0.5):
            any_rating = any_rating.drop(j)
        j = j+1

    liked_ratings_for_users[i] = any_rating
    i = i+1


i = 0
for any_rating in liked_ratings_for_users:
    print(i)
    if 'index' in any_rating.columns:
        liked_ratings_for_users[i] = any_rating.drop('index', 1)
    i = i+1


#############################################################mapowanie id ksiazek i uzytkownikow#########


ratings_for_users_copy = copy.deepcopy(ratings_for_users)
all_books_ids_mapped = []
for b_id in all_books_ids:
    #print(b_id)
    all_books_ids_mapped.append(goodreads_to_id(b_id))


all_books_ids_mapped = np.array(all_books_ids_mapped)
all_books_ids_list = list(all_books_ids_mapped)

max_id = max(all_books_ids_mapped)
# przechowuje indeks pod ktorym dane id jest w tablicy all_books_ids_mapped
ids_mapped_reverted = [0] * (max_id+1)
ii = 0
for id1 in all_books_ids_mapped:
    ids_mapped_reverted[id1] = ii
    ii = ii+1


max_id = max(all_users_ids)
# przechowuje indeks pod ktorym dane id jest w tablicy all_users_ids
ids_users_mapped_reverted = [0] * (max_id+1)
ii = 0
for id1 in all_users_ids:
    ids_users_mapped_reverted[id1] = ii
    ii = ii+1

'''
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
'''

####### przygotowanie 3 uprosczonych macierzy ##########################
no_of_books = []  # liczby ksiazek oceniionych przez uzytkownikow
for user in ratings_for_users_copy:
    no_of_books.append(len(user))
max_no_of_books = max(no_of_books)


matr_mf = np.zeros(
    ([len(ratings_for_users_copy), max_no_of_books]))  # macierz z mf
# macierz z id ksiązek
matr_book_id = np.zeros(
    ([len(ratings_for_users_copy), max_no_of_books]), dtype=int)
i = 0
for user in ratings_for_users_copy:
    print(i)
    j = 0
    for rating1 in user.iterrows():
        matr_book_id[i, j] = rating1[1]["book_id"]
        matr_mf[i, j] = rating1[1]["mf"]
        j = j+1
    i = i+1

################## czytanie z tablicy zapisanej ##################################


pd.DataFrame(matr_mf).to_csv("matr_mf.csv")
pd.DataFrame(matr_book_id).to_csv("matr_book_id.csv")
pd.DataFrame(no_of_books).to_csv("no_of_books.csv")

similarity = pd.read_csv("new_file_all_simil.csv").to_numpy()
similarity = np.delete(similarity, 0, axis=1)
matr_mf1 = pd.read_csv("matr_mf.csv").to_numpy()
matr_mf1 = np.delete(matr_mf1, 0, axis=1)
matr_book_id1 = pd.read_csv("matr_book_id.csv").to_numpy()
matr_book_id1 = np.delete(matr_book_id1, 0, axis=1)
no_of_books1 = pd.read_csv("no_of_books.csv").to_numpy()
no_of_books1 = np.delete(no_of_books1, 0, axis=1)


############ rekomendacja dla CB dla uzytkownikow ##################################################3


def create_recom_cb(no_of_books_selected, matr_mf_selected, matr_book_id_selected):

    recoms1 = np.zeros(
        [len(no_of_books_selected), len(all_books_ids_mapped)], dtype=int)
    ik = 0
    while ik < len(no_of_books_selected):

        known_books_ids = matr_book_id_selected[ik]
        known_books_count = no_of_books_selected[ik]
        print(ik)
        max_prediction = 0.001
        jk = 1
        while jk < len(all_books_ids_mapped):
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids):  # id
                #recoms1[ik][jk] = get_weighted_sum(known_books_count, known_books_ids, jk, ik, matr_mf_selected)
                recoms1[ik][jk] = get_max_min(known_books_count, known_books_ids, jk, ik, matr_mf_selected)
                
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

def get_weighted_sum(known_books_count, known_books_ids, jk, ik, matr_mf_selected):
    kk = 0
    sum_value = 0
    while kk < known_books_count:
        books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]], jk]
        mf = matr_mf_selected[ik][kk]
        sum_value = sum_value + mf * books_similarity
        kk = kk+1
    return sum_value

def get_max_min(known_books_count, known_books_ids, jk, ik, matr_mf_selected):
    kk = 0
    max_agr = 0
    while kk < known_books_count:
        books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]], jk]
        mf = matr_mf_selected[ik][kk]
        max_agr = max(min(books_similarity, mf), max_agr)
        kk = kk+1
    return max_agr

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


user_limit = 5000
print(datetime.now())
recoms_cb = create_recom_cb(
    no_of_books[0:user_limit], matr_mf[0:user_limit], matr_book_id[0:user_limit])
print(datetime.now())
pd.DataFrame(recoms_cb).to_csv("recoms2.csv")

################### top n
top = 100


def get_top_recoms_cb(top, recoms1):
    sorted_recoms = np.zeros([len(recoms1), top], dtype=int)
    i = 0
    for recom in recoms1:
        sorted_recoms[i] = np.array(all_books_ids_mapped)[
            np.argsort(recom)[::-1][:top]]
        #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]
        i = i+1
        print(i)
    return sorted_recoms


sorted_recoms = get_top_recoms_cb(top, recoms_cb)
#########################################################CF###################################3

''' kopia tego z gory, moze zmienic na liked
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
'''

users_similar = []
'''
max_books_for_user = 0
for user in liked_ratings_for_users:
    if len(user)>max_books_for_user:
       max_books_for_user = len(user)
    
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
'''
'''
max_users_for_user = 0
len_commons = [0] * (len(liked_ratings_for_users)) 
i=0
for user_i in matr_book_id:
    j=0
    user_i_books = user_i[0:no_of_books[i]]
    len_users_for_user=0
    for user_j in matr_book_id:
        user_j_books = user_j[0:no_of_books[j]]
        
        len_sum = len(np.union1d(user_i_books, user_j_books))
        len_common = len(user_i_books) + len(user_j_books) - len_sum
        if len_common/len_sum > 0.25:
            len_users_for_user=len_users_for_user+1
        #len_commons.append(len_common)
        #diff_ij = np.setdiff1d(user[0:no_of_books[i]], user2, assume_unique=False)
        #diff_ji = np.setdiff1d(liked_j, liked_i, assume_unique=False)
        #common = liked_i[np.in1d(liked_i, liked_j)]
        j=j+1
    if len_users_for_user > max_users_for_user:
        max_users_for_user=len_users_for_user
    i=i+1
    print(i)
'''

##################################### Użytkownicy potencjalnie podobni (co najmnjiej 1 wspolna ksiązka) ###############


def get_users_for_user(matr_book_id_selected, no_of_books_selected):
    users_for_user = [0] * (len(no_of_books_selected))
    i = 0
    for user_i in matr_book_id_selected:
        print(i)
        user_i_books = user_i[0:no_of_books_selected[i]]
        for_single_user = []
        j = 0
        for user_j in matr_book_id:
            user_j_books = user_j[0:no_of_books[j]]
            if user_i_books[np.in1d(user_i_books, user_j_books)].size > 0:
                for_single_user.append(j)
            j = j+1

        users_for_user[i] = for_single_user
        i = i+1
    return users_for_user


get_users_for_user(list(range(0, 100)))


##################### Liczenie podobieństwa użytkowników na bazie użytkowników potencjalnie podobnych###################
i = 0
j = 0
single_row = []
no_of_users = len(ratings_for_users_copy)
similarity_u = copy.deepcopy(users_for_user)


def calculate_users_similarity(users_for_selected_user, no_of_books_selected, matr_mf_selected, matr_book_id_selected):
    similarity_u = copy.deepcopy(users_for_selected_user)
    i = 0
    j = 0
    for user_i in users_for_selected_user:
        j = 0
        liked_i = matr_book_id_selected[i][0:no_of_books_selected[i]]
        mf_i = matr_mf_selected[i][0:no_of_books_selected[i]]
        for user_j in user_i:
            liked_j = matr_book_id[user_j][0:no_of_books[user_j]]
            mf_j = matr_mf[user_j][0:no_of_books[user_j]]
            
            similarity_u[i][j] = get_fst_users_similarity(liked_i, liked_j, mf_i, mf_j)
            #similarity_u[i][j] = get_euclidean_users_similarity(liked_i, liked_j, mf_i, mf_j)
            #similarity_u[i][j] = get_cosine_users_similarity(liked_i, liked_j, mf_i, mf_j)

            j = j+1

        i = i+1
        print(i)
    return similarity_u


user_indexes = list(range(0, 100))
calculate_users_similarity(list(range(0, 100)))

def get_fst_users_similarity(liked_i, liked_j, mf_i, mf_j):

    diff_ij = np.setdiff1d(liked_i, liked_j, assume_unique=False)

    diff_ji = np.setdiff1d(liked_j, liked_i, assume_unique=False)
    common = liked_i[np.in1d(liked_i, liked_j)]

    sum_min = 0
    sum_max = 0
    k = 0

    for liked_book_j in diff_ij:
        sum_max = sum_max + mf_i[liked_i == liked_book_j][0]
    for liked_book_j in diff_ji:
        sum_max = sum_max + mf_j[liked_j == liked_book_j][0]

    for single_col in common:
        val_i = mf_i[liked_i == single_col][0]
        val_j = mf_j[liked_j == single_col][0]
        min1 = min(val_i, val_j)
        max1 = max(val_i, val_j)
        sum_min = sum_min+min1
        sum_max = sum_max+max1

    if sum_max == 0:
        simil = 0
    else:
        simil = float(sum_min)/float(sum_max)
    
    return simil

def get_euclidean_users_similarity(liked_i, liked_j, mf_i, mf_j):

    diff_ij = np.setdiff1d(liked_i, liked_j, assume_unique=False)

    diff_ji = np.setdiff1d(liked_j, liked_i, assume_unique=False)
    common = liked_i[np.in1d(liked_i, liked_j)]
    sum1 = 0

    for liked_book_j in diff_ij:
        sum1 = sum1 + mf_i[liked_i == liked_book_j][0]**2
    for liked_book_j in diff_ji:
        sum1 = sum1 + mf_j[liked_j == liked_book_j][0]**2

    for single_col in common:
        val_i = mf_i[liked_i == single_col][0]
        val_j = mf_j[liked_j == single_col][0]
        sum1 = sum1+(val_i-val_j)**2
    
    return math.sqrt(sum1)

def get_cosine_users_similarity(liked_i, liked_j, mf_i, mf_j):

    common = liked_i[np.in1d(liked_i, liked_j)]

    sum1 = 0
    sum2 = 0
    sum3 = 0

    for single in liked_i:
        sum1 = sum1 + mf_i[liked_i == single][0]**2
    for single in liked_j:
        sum2 = sum2 + mf_i[liked_j == single][0]**2

    for single_col in common:
        val_i = mf_i[liked_i == single_col][0]
        val_j = mf_j[liked_j == single_col][0]
        sum3 = sum3+(val_i*val_j)

    denominator = math.sqrt(sum1) * math.sqrt(sum2)
    if denominator != 0:
       return sum3/denominator
    return 0

######################################### Top n użytkowników podobnych ###########################


def get_top_n_users(users_selected_similarity, users_for_selected_user, top_users):

    sorted_users = np.zeros(
        [len(users_selected_similarity), top_users],  dtype=int)
    sorted_users_similarity = np.zeros(
        [len(users_selected_similarity), top_users])

    i = 0
    for user in users_selected_similarity:
        sorted_arr = np.array(users_for_selected_user[i])[
            np.argsort(user)[::-1][:top]]
        sorted_similarity_arr = np.array(user)[np.argsort(user)[::-1][:top]]
        if(len(sorted_arr) < top):
            to_add_num = top-len(sorted_arr)
            sorted_arr = np.append(sorted_arr, [-1] * to_add_num)
            sorted_similarity_arr = np.append(
                sorted_similarity_arr, [-1] * to_add_num)

        sorted_users[i] = sorted_arr
        sorted_users_similarity[i] = sorted_similarity_arr

        i = i+1
        print(i)
    return (sorted_users, sorted_users_similarity)


top = 100
sorted_users = np.zeros([len(ratings_for_users_copy), top],  dtype=int)
sorted_users_similarity = np.zeros([len(ratings_for_users_copy), top])
get_top_n_users(user_indexes)

######################################### Rekomendacje jako top książek według sredniej wazonej ###########################

top_recoms = 100
recoms_cf = np.zeros([len(ratings_for_users_copy), top_recoms],  dtype=int)


def get_recom_by_nearest_users(sorted_users, sorted_users_similarity, top, no_of_books_selected, matr_book_id_selected):

    recoms_cf = np.zeros([len(sorted_users), top],  dtype=int)
    i = 0
    for user_sorted_users in sorted_users:   # sorted_users - indexy najbliższych uzytkownikow
        j = 0
        books_recom = [0] * (len(short_book_tags) + 1)

        known_books_ids = matr_book_id_selected[i][0:no_of_books_selected[i]]

        for similar_user in user_sorted_users[user_sorted_users > -1]:
            similar_users_similarity = sorted_users_similarity[i][j]

            k = 0
            simil_user_books_ids = matr_book_id[similar_user][0:no_of_books[similar_user]]
            simil_user_books_mf = matr_mf[similar_user][0:no_of_books[similar_user]]
            for book_mf in simil_user_books_mf:
                book_id = simil_user_books_ids[k]
                if not (book_id in known_books_ids):
                    #books_recom[book_id] = get_weighted_sum_user(books_recom[book_id], similar_users_similarity, book_mf)
                    books_recom[book_id] = get_max_min_user(books_recom[book_id], similar_users_similarity, book_mf)
                k = k+1

            j = j+1

        recoms_cf[i] = np.argsort(books_recom)[::-1][:top_recoms]

        i = i+1
        print(i)
    return recoms_cf


get_recom_by_nearest_users(user_indexes)

def get_weighted_sum_user(sum_value, users_similarity, mf):
    sum_value = sum_value + mf * users_similarity
    return sum_value

def get_max_min_user(max_agr, users_similarity, mf):
    max_agr = max(min(users_similarity, mf), max_agr)
    return max_agr

############################################################ HYBRID #######################################################

############################################### ksiazki na podstawie najblizszych uzytkownikow #############################


def get_books_by_nearest_users(sorted_users):

    nearest_users_books = np.zeros(
        [len(sorted_users), len(all_books_ids_mapped)],  dtype=int)
    i = 0
    for user_sorted_users in sorted_users:   # sorted_users - indexy najbliższych uzytkownikow
        j = 0
        for similar_user in user_sorted_users[user_sorted_users > -1]:

            simil_user_books_ids = matr_book_id[similar_user][0:no_of_books[similar_user]]
            for book_id in simil_user_books_ids:
                nearest_users_books[i][book_id] = 1

            j = j+1

        i = i+1
        print(i)
    return nearest_users_books


get_books_by_nearest_users(user_indexes)


################################################# liczenie predykcji ######################################################
def create_recom_h(no_of_books_selected, matr_mf_selected, matr_book_id_selected, selected_users_nearest_books):
    recoms_h = np.zeros([len(no_of_books_selected), len(all_books_ids_mapped)])
    ik = 0
    while ik < len(no_of_books_selected):

        known_books_ids = matr_book_id_selected[ik]
        known_books_count = no_of_books_selected[ik]
        print(ik)
        max_prediction = 0.001
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

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]], jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1

                recoms_h[ik][jk] = sum_value
            else:
                sum_value = 0
                kk = 0
                while kk < known_books_count:

                    books_similarity = similarity[ids_mapped_reverted[known_books_ids[kk]], jk]
                    mf = matr_mf_selected[ik][kk]
                    sum_value = sum_value + mf * books_similarity
                    kk = kk+1

                if sum_value > max_prediction:
                    max_prediction = sum_value

            jk = jk+1

        jk = 0
        while jk < len(all_books_ids_mapped):
            recoms_h[ik][jk] = recoms_h[ik][jk]/max_prediction
            jk = jk+1
        ik = ik+1
    return recoms_h


user_limit = 5000
print(datetime.now())
recoms_h = np.zeros([len(ratings_for_users_copy), len(all_books_ids_mapped)])
create_recom_h(ratings_for_users_copy[0:user_limit])
print(datetime.now())


############################################################# liczenie top n ##############################################

top = 100
sorted_recoms_h = np.zeros([len(ratings_for_users_copy), top],  dtype=int)


def get_top_recoms_h(top, recoms1):
    sorted_recoms_h = np.zeros([len(recoms1), top],  dtype=int)
    i = 0
    for recom in recoms1:
        sorted_recoms_h[i] = np.array(all_books_ids_mapped)[
            np.argsort(recom)[::-1][:top]]
        #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]
        i = i+1
        print(i)
    return sorted_recoms_h


#################################
def get_top_recoms_cb(top, recoms1):
    sorted_recoms = np.zeros([len(recoms1), top], dtype=int)
    i = 0
    for recom in recoms1:
        sorted_recoms[i] = np.array(all_books_ids_mapped)[
            np.argsort(recom)[::-1][:top]]
        #for_checking = recoms1[i][np.argsort(recom)[::-1][:top]]
        i = i+1
        print(i)
    return sorted_recoms


sorted_recoms = get_top_recoms_cb(top, recoms_cb)


################################

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
