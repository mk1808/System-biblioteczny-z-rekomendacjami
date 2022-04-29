class User(object):
    def __init__(self, obj):  # id, userId, action, bookId, value, date
        self.id = obj["_id"]
        self.get_field(obj, "userId")
        self.get_field(obj, "action")
        self.get_field(obj, "bookId")
        self.get_field(obj, "date")
        self.get_field(obj, "value")

        
    def get_field(self, obj, field):
        if field in obj:
            setattr(self, field, obj[field])
        else:
            setattr(self, field, None)






    # def __init__(self, my_dict):

    #     for key in my_dict:
    #         setattr(self, key, my_dict[key])


# if __name__ == "__main__":

#     my_dict = {"Name": "Geeks",
#                "Rank": "1223",
#                "Subject": "Python"}

#     result = User(my_dict)

#     # printing the result
#     print("After Converting Dictionary to Class : ")
#     print(result.Name, result.Rank, result.Subject)
#     print(type(result))
