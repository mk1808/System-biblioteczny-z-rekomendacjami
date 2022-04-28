from pymongo import MongoClient
from pprint import pprint
from user import User
from book import Book

def connectToDb():
    client = MongoClient(
        "mongodb+srv://admin:admin@cluster0.l4evp.mongodb.net/mlibrary?retryWrites=true&w=majority"
    )
    db = client.mlibrary
    serverStatusResult = db.command("serverStatus")
    pprint(serverStatusResult)
    return db;

def getUsers(db):
    users = db["users"]

    allUsers = users.find()
    usersTab = []
    for data in allUsers:
        usersTab.append(User(data))

    return usersTab;

def getBooks(db):
    books = db["books"]

    allBooks = books.find()
    booksTab = []
    for data in allBooks:
        booksTab.append(Book(data))

    return booksTab;

db = connectToDb();
users = getUsers(db);
books = getBooks(db);







# printing the result
# print("After Converting Dictionary to Class : ")
# print(user.userId, user.action, user.bookId)
# print(type(user))
