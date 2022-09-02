# -*- coding: utf-8 -*-
"""
Created on Tue Aug 23 23:05:55 2022

@author: monik
"""

start =1
stop = 10 + 0.001
step = 0.25
x = np.arange(start, stop, 1)
foot = 1.0
ceiling = 9.0
smf = 1- skfuzzy.smf(x, foot, ceiling)
smf1 = 1- skfuzzy.smf(np.arange(1, 2, 1), foot, ceiling)
matplotlib.pyplot.plot(x, smf, label="S-function")

trimf = skfuzzy.trimf(x, [0, 5, 10])

matplotlib.pyplot.plot(x, trimf, label="triangular")

def get_S_membership(index, min, max ):
    if min <= index and index <= ((min+max)/2):
        return 2 * pow(((index-min)/(max-min)),2)
    elif index<=min:
        return 0
    elif index>=max:
        return 1
    return 1-( 2 * pow(((index-max)/(max-min)),2))

def get_S_membership_desc(index, min, max ):
    return 1-get_S_membership(index, min, max)
    
get_S_membership(2, 1, 10)


y = [0]*len(x)
i = 0
for xi in x:
    y[i] = get_S_membership_desc(xi, 1, 10)
    i=i+1
    
    
matplotlib.pyplot.plot(x, y, label="S-function")


y1 = [0]*len(x)
i = 0
for xi in x:
    y1[i] = get_gaussian_membership(xi, 10)
    i=i+1
    
        
matplotlib.pyplot.plot(x, y1, label="gauss")
    

book_j = 
 
def get_euclidean_similarity(book_i, book_j):
   col_j = book_j.columns
   col_i = book_i.columns
   diff_ij = col_i.difference(col_j)
   diff_ji = col_j.difference(col_i)
   common = col_i.intersection(col_j)
   sum1=0
   
   for single_col in diff_ij:
       sum1 = sum1 + book_i[single_col][0]**2
   
   for single_col in diff_ji:
       sum1 = sum1 + book_j[single_col][0]**2 
       
   for single_col in common:
       val_i=book_i[single_col][0]
       val_j=book_j[single_col][0]
       sum1=sum1+(val_i-val_j)**2
   return 1-math.sqrt(sum1)
   
	
def dummy(v, u):
   s = 0
   for v_i, u_i in zip(v, u):
       s += (v_i - u_i)**2
   return s ** 0.5

