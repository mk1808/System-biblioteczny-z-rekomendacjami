# -*- coding: utf-8 -*-
"""
Created on Sun Jun 19 21:27:26 2022

@author: monik
"""


#import pandas as pd
from pandas import concat, read_json, read_csv, DataFrame
import random
import math
from copy import deepcopy
from numpy import delete, array, where
import sys
import multiprocessing
#from joblib import Parallel
import threading
#from threading import Thread, Lock
from datetime import datetime
from multiprocessing import Process

users_ids2 = array(read_json("users_ids.json")[0])
all_books_ids_mapped2 = array(read_json("all_books_ids_mapped.json")[0])
book_indexes2 = array(read_json("book_indexes.json")[0])
all_books_ids_list2 = array(read_json("all_books_ids_list.json")[0])

#users_ids = list(pd.read_json("users_ids.json")[[0]][0])
#all_books_ids_mapped = np.array(list(pd.read_json("all_books_ids_mapped.json")[[0]][0]))
#book_indexes = pd.read_json("book_indexes.json")[[0]][0]
#all_books_ids_list = list(pd.read_json("all_books_ids_list.json")[[0]][0])

similarity2 = read_csv("new_file_all_simil.csv").to_numpy()
similarity2 = delete(similarity2, 0, axis=1)

user_ratings_from_json = read_json("ratings_for_users_copy.json")
ratings_for_users_copy2 = []
for user_rating in user_ratings_from_json[0]:
    ratings_for_users_copy2.append(DataFrame(user_rating))
   
df2 = DataFrame([[None,None, None]], columns=list(["user_id", "book_id", "predicted_rating"]))
for_all_books_df2 = df2
for user_recom in all_books_ids_mapped2:
    #for_all_books_df2.append(pd.DataFrame( columns=list(["user_id", "book_id", "predicted_rating"])))
    for_all_books_df2 = concat([for_all_books_df2, deepcopy(df2)])

recommendations2=[]
for user in ratings_for_users_copy2:
    recommendations2.append(deepcopy(for_all_books_df2))    #??????????????
 
def create_recom(ratings_for_users):
    ik=0
    while ik<len(ratings_for_users):  
  #  for ratings_of_user in ratings_for_users:
        ratings_of_user =  ratings_for_users[ik]
        user_id=ratings_of_user["user_id"][0]
        i = where(users_ids2 == user_id)[0][0]
        known_books_ids = list(ratings_of_user["book_id"]) #??????????????
        print(i)  
        j=0
        jk=0
        while jk<len(all_books_ids_mapped2): 
            # for book_id in all_books_ids_mapped:#grid
            book_id = all_books_ids_mapped2[jk]
            if not (book_id in known_books_ids): #id
                sum_value=0
       
                book_in_tab_index = book_indexes2[j]
                kk=0
                while kk<len(known_books_ids): 
                #for known_book_id in known_books_ids:
                    known_book_id=known_books_ids[kk] #??????????????
                    known_book_in_tab = all_books_ids_mapped2[all_books_ids_mapped2==known_book_id][0] #??????????????
                    known_book_in_tab_index = where(all_books_ids_list2 == known_book_in_tab)[0][0]
                    
                    
                    books_similarity = similarity2[known_book_in_tab_index, book_in_tab_index]
                    mf = ratings_of_user[ratings_of_user["book_id"]==known_book_id]["mf"].iloc[0]
                    sum_value = sum_value + mf * books_similarity
                    kk=kk+1
                    
                #mutex.acquire()  
                recommendations2[i].iloc[j]["user_id"]=user_id
                recommendations2[i].iloc[j]["book_id"]=book_id
                recommendations2[i].iloc[j]["predicted_rating"]=sum_value
                #mutex.release()
                
            j=j+1
            jk=jk+1
        ik=ik+1
    return
    
print(datetime.now())
before=datetime.now()
create_recom(ratings_for_users_copy2[0:10])
print(datetime.now())
print(datetime.now()-before)

# if __name__ == "__main__":
#     before=datetime.now()
#     #pararel
#     t1 = Process(target=create_recom, args=(ratings_for_users_copy[0:5],))
#     t2 = Process(target=create_recom, args=(ratings_for_users_copy[5:10],))
#     t1.start()
#     t2.start()
#     # wait for the processes to finish
#     t1.join()
#     t2.join()
#     # print a newline
#     print("END")
#     after=datetime.now()
#     print(after-before)