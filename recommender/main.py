from pymongo import MongoClient
from pprint import pprint
from user import User
from book import Book

def connect_to_db():
    client = MongoClient(
        "mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority"
    )
    db = client.mlibrary
    server_status_result = db.command("serverStatus")
    pprint(server_status_result)
    return db;

def get_users(db):
    users = db["users"]

    all_users = users.find()
    users_tab = []
    for data in all_users:
        users_tab.append(User(data))

    return users_tab;

def get_books(db):
    books = db["books"]

    all_books = books.find()
    books_tab = []
    for data in all_books:
        books_tab.append(Book(data))

    return books_tab;

db = connect_to_db();
users = get_users(db);
books = get_books(db);







# printing the result
# print("After Converting Dictionary to Class : ")
# print(user.userId, user.action, user.bookId)
# print(type(user))
