
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


###############################################Pobranie i przygotowanie danych#########################
books = pd.read_csv('data/books.csv', low_memory=False)
book_tags = pd.read_csv('data/book_tags.csv', low_memory=False)
ratings = pd.read_csv('data/ratings.csv', low_memory=False)
tags = pd.read_csv('data/tags.csv', low_memory=False)
to_read = pd.read_csv('data/to_read.csv', low_memory=False)

def get_gaussian_membership(rk, L):
    alpha=1.2
    sqr = math.sqrt(alpha*L*(rk-1))
    mf = rk/float(pow(2, sqr))
    #round(mf, 2)
    return mf

def get_half_traingular_membership(min, max, r):
    return (r-min)/float(max-min)
# Print the first three rows
#books.head(3)

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
    
    
    
    
############################################################################
"""
new_book_tags = book_tags.sort_values(by=['tag_id'], inplace=False)
all_tags_ids = new_book_tags['tag_id'].unique()
tags_for_books_copy = copy.deepcopy(tags_for_books)

all_tags_ids_copy = copy.deepcopy(all_tags_ids)

book_tags_df = pd.DataFrame(np.zeros([1, len(all_tags_ids_copy)]), columns=all_tags_ids_copy)


i=0
final_books_tags=[]
for book_test in tags_for_books_copy:    
    book_tags_df_copy = copy.deepcopy(book_tags_df)
    
    bt=book_test
    for tag in book_test.iterrows():
        tag1=tag
        
        book_tags_df_copy[tag[1]['tag_id']] = tag[1]['mf']
    final_books_tags.append(book_tags_df_copy)
    i=i+1
    print(i)
    """
#####################################################################
  
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
    
    
    
####################################################old##################333
        
'''       

data_test=tags_for_books_copy[0].iloc[0]
books_for_comparison=[]
test_tab = [0,0,0,0]
#len(tags_for_books_copy)
for i in range(0, len(all_tags_ids)):
    books_for_comparison.append(test_tab)
    print (i)
    

df1=pd.DataFrame(data_test)
df1 = df1.transpose()
df1 = pd.DataFrame(books_for_comparison, columns=list(df1.columns))
empty_book_tag_matrix = df1

books_with_all_tags=[]
i=0
for book in tags_for_books_copy:
    books_with_all_tags.append(empty_book_tag_matrix)

book_no=0    
for book in tags_for_books_copy:   
    tag_no=0
    ids2 = book['tag_id']
    for tag_id in all_tags_ids:
        found_tag_id = book[book['tag_id']==tag_id]
        if found_tag_id.empty == False:
            books_with_all_tags[book_no].iloc[tag_no]=found_tag_id.transpose()
            b=0
        tag_no=tag_no+1
    book_no=book_no+1  
    print(book_no)
        
''' 
#df1=[]
#df1=pd.DataFrame(books_for_comparison)
#df1 = df1.append(data_test)




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
        col_j = list(book_j.columns)
        col_i = list(book_i.columns)
        all_col = list(set(col_j + col_i))
        sum_min=0
        sum_max=0
        for col in all_col:
            it=0
            try:
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
    
    
    
##################################################################wyłonienie lubianych

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
def book_id_to_goodreads(old_id):
    return books[books["id"]==old_id]["book_id"].iloc[0]

def goodreads_to_id(b_id):
    result = books[books["book_id"]==b_id]["id"]
    if result.size  < 1:
        print(result)
    return result.iloc[0] if result.size>0 else 0

#liczone po lubianych!
ratings_for_users_copy = copy.deepcopy(liked_ratings_for_users)
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
for book_id in all_books_ids_mapped:
    book_in_tab = all_books_ids_mapped[all_books_ids_mapped==book_id][0]
    book_indexes.append(all_books_ids_list.index(book_in_tab))
    
users_ids=[]
for user in ratings_for_users_copy:
    users_ids.append(user["user_id"].iloc[[0]].iloc[0])
    
mutex = Lock()

#####################################REKOMENDACJE z WHILE################
    
def create_recom(ratings_for_users):
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
        while jk<len(all_books_ids_mapped): 
            # for book_id in all_books_ids_mapped:#grid
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids): #id
                sum_value=0
       
                book_in_tab_index = book_indexes[j]
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
                    
                #mutex.acquire()  
                recommendations[i].iloc[j]["user_id"]=user_id
                recommendations[i].iloc[j]["book_id"]=book_id
                recommendations[i].iloc[j]["predicted_rating"]=sum_value
                #mutex.release()
                
            j=j+1
            jk=jk+1
        ik=ik+1
    return
    
print(datetime.now())
create_recom(ratings_for_users_copy[0:300])
print(datetime.now())

if __name__ == "__main__":
    before=datetime.now()
    #pararel
    t1 = Process(target=create_recom, args=(ratings_for_users_copy[0:5],))
    t2 = Process(target=create_recom, args=(ratings_for_users_copy[5:10],))
    t1.start()
    t2.start()
    # wait for the processes to finish
    t1.join()
    t2.join()
    # print a newline
    print("END")
    after=datetime.now()
    print(after-before)








2 wą♣tki po 5 - 49:00 - 51:00 - 2 min
funkcja 10 elem 51:30 - 53:30 - 2 min


while - 01:25 - 03:20
#####################################REKOMENDACJE z FOR################

i=0
for ratings_of_user in ratings_for_users_copy[0:10]: #id
    
    user_id=ratings_of_user["user_id"].iloc[[0]].iloc[0]
   
    known_books_ids = list(ratings_of_user["book_id"])
    print(i)  
    j=0
    for book_id in all_books_ids_mapped:#grid
        
        if not (book_id in known_books_ids): #id
            sum_value=0
   
            book_in_tab_index = book_indexes[j]
            
            for known_book_id in known_books_ids:
                known_book_in_tab = all_books_ids_mapped[all_books_ids_mapped==known_book_id][0]
                known_book_in_tab_index = all_books_ids_list.index(known_book_in_tab)
                
                books_similarity = similarity[known_book_in_tab_index, book_in_tab_index]
                mf = ratings_of_user[ratings_of_user["book_id"]==known_book_id]["mf"].iloc[0]
                sum_value = sum_value + mf * books_similarity
                
            recommendations[i].iloc[j]["user_id"]=user_id
            recommendations[i].iloc[j]["book_id"]=book_id
            recommendations[i].iloc[j]["predicted_rating"]=sum_value
            
        j=j+1
    i=i+1  
      


# df3 = pd.DataFrame([[user_id, book_id, sum_value]], columns=list(["user_id", "book_id", "predicted_rating"]))
 
 #recommendations[i].iloc[j]["user_id"]=user_id


 pd.DataFrame(liked_ratings_for_users).to_json("liked.json")


########################pd.DataFrame(similarity).to_csv("new_file_all_simil.csv")


user_ratings_from_json = pd.read_json("json_file_ratings_for_user.json")
test_ratings_for_users = []
for user_rating in user_ratings_from_json[0]:
    test_ratings_for_users.append(pd.DataFrame(user_rating))
    

similarity = pd.read_csv("new_file_all_simil.csv").to_numpy()
similarity = np.delete(similarity, 0, axis=1)
  


pd.DataFrame(users_ids).to_json("users_ids.json")
users_ids2 = pd.read_json("users_ids.json")[[0]][0]


pd.DataFrame(all_books_ids_mapped).to_json("all_books_ids_mapped.json")
all_books_ids_mapped2 = pd.read_json("all_books_ids_mapped.json")[[0]][0]



pd.DataFrame(book_indexes).to_json("book_indexes.json")
book_indexes2 = pd.read_json("book_indexes.json")[[0]][0]


pd.DataFrame(all_books_ids_list).to_json("all_books_ids_list.json")
all_books_ids_list2 = pd.read_json("all_books_ids_list.json")[[0]][0]




pd.DataFrame(ratings_for_users_copy).to_json("ratings_for_users_copy.json")
user_ratings_from_json = pd.read_json("ratings_for_users_copy.json")
test_ratings_for_users = []
for user_rating in user_ratings_from_json[0]:
    test_ratings_for_users.append(pd.DataFrame(user_rating))



 pd.DataFrame(recommendations).to_json("recommendations.json")

#################3
user_ratings_from_json = pd.read_json("json_file_ratings_for_user.json")
ratings_for_users = []
for user_rating in user_ratings_from_json[0]:
    ratings_for_users.append(pd.DataFrame(user_rating))

similarity = pd.read_csv("new_file_all_simil.csv").to_numpy()
similarity = np.delete(similarity, 0, axis=1)

################################################################3

  
recoms = []
for user_rating in recommendations[0:300]:
    recoms.append(user_rating.reset_index().to_json())
pd.DataFrame(recoms).to_json("test_recoms.json")

recoms2 = pd.read_json("test_recoms.json")[[0]][0]
recoms3 = []
for recom in recoms2:
    recoms3.append(pd.read_json(recom).set_index("index"))
    
################top n

for recom in recoms3:    
    recom.sort_values(by=['predicted_rating'], ascending=False, inplace=True)
    
    
recoms4 = copy.deepcopy(recoms3)
i=0
n=15
for recom in recoms4:
    recoms4[i] = recom[0:15]
    i=i+1
    

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
    

    

  




df4 = pd.DataFrame([[None,None, None, None]], columns=list(ratings_for_user.columns))


for_books_df = copy.deepcopy(df4)
for user_recom in range(0, 10):
    for_books_df=for_books_df.append(df4, ignore_index = True)
    #= pd.concat([for_all_books_df, copy.deepcopy(df2)])



#########################################################################################################



liked_ratings_for_users=copy.deepcopy(ratings_for_users_learn)


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
    
#############
#liczone po lubianych!
ratings_for_users_copy = copy.deepcopy(liked_ratings_for_users)
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
for book_id in all_books_ids_mapped:
    book_in_tab = all_books_ids_mapped[all_books_ids_mapped==book_id][0]
    book_indexes.append(all_books_ids_list.index(book_in_tab))
    
users_ids=[]
for user in ratings_for_users_chosen:
    users_ids.append(user["user_id"].iloc[[0]].iloc[0])
    
#mutex = Lock()
    
def create_recom(ratings_for_users):
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
        while jk<len(all_books_ids_mapped): 
            # for book_id in all_books_ids_mapped:#grid
            book_id = all_books_ids_mapped[jk]
            if not (book_id in known_books_ids): #id
                sum_value=0
       
                book_in_tab_index = book_indexes[j]
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
                    
                #mutex.acquire()  
                recommendations[i].iloc[j]["user_id"]=user_id
                recommendations[i].iloc[j]["book_id"]=book_id
                recommendations[i].iloc[j]["predicted_rating"]=sum_value
                #mutex.release()
                
            j=j+1
            jk=jk+1
        ik=ik+1
    return
  

max_values_for_users=[]
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
create_recom(ratings_for_users_copy[10:user_limit])
print(datetime.now())

print(datetime.now())
create_max_recom_value(ratings_for_users_copy[0:user_limit])
print(datetime.now())

############################################################ normalization
i=0
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
    


#################### evaluation


ratings_for_users_test_liked = copy.deepcopy(ratings_for_users_test)

i=0
for any_rating in ratings_for_users_test_liked:
    i=0
    for single_any_rating in any_rating.iterrows():  
        if single_any_rating[1]["mf"]==None:
            single_any_rating[1]["mf"]=1
            any_rating.iloc[i]= single_any_rating[1]
        i=i+1  
        
        
i=0
for any_rating in ratings_for_users_test_liked:
    print(i)
    any_rating = any_rating.reset_index()
    j=0
    for single_any_rating in any_rating.iterrows():  
        
        if not ((single_any_rating[1]["mf"])>0.5):
            any_rating = any_rating.drop(j)
        j=j+1
    
    ratings_for_users_test_liked[i] = any_rating
    i=i+1   
    


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