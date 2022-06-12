
import pandas as pd
import random
import math
import copy
import numpy as np

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
    for tag in book.iterrows():
        #random_num = random.randint(0, 1000)   
        tag[1]["mf"] = get_gaussian_membership(rk, len(book))
        book.iloc[j] = tag[1]
        rk=rk+1
        j=j+1
        
    tags_for_books[i] = book
    i=i+1
    

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
new_book_tags = book_tags.sort_values(by=['tag_id'], inplace=False)
all_tags_ids = new_book_tags['tag_id'].unique()
tags_for_books_copy = copy.deepcopy(tags_for_books)

data_test=tags_for_books_copy[0].iloc[0]
books_for_comparison=[]
test_tab = [0,0,0,0]#len(tags_for_books_copy)
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
            books_with_all_tags[book_no].iloc[tag_no]=found_tag_id.iloc[0]
            b=0
        tag_no=tag_no+1
    book_no=book_no+1  
    print(book_no)
        

#df1=[]
#df1=pd.DataFrame(books_for_comparison)
#df1 = df1.append(data_test)


i=0
j=0
single_row = []
no_of_books = len(books_with_all_tags)
similarity = np.zeros([no_of_books, no_of_books])
for book_i in books_with_all_tags:  
    single_row = []    
    for book_j in books_with_all_tags:
        it=0
        sum_min=0
        sum_max=0
        for tag_i in book_i:
            
            val_i=book_i.iloc[it]["mf"]
            val_j=book_j.iloc[it]["mf"]
            min1 = min(val_i, val_j)
            max1 = max(val_i, val_j)
            sum_min=sum_min+min1
            sum_max=sum_max+max1
            
            
        similarity[i,j] = sum_min/float(sum_max)
        it=it+1
        
    i=i+1        
    
j=j+1
print(j)




































